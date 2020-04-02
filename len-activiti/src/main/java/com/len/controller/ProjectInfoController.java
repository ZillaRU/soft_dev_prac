package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.core.shiro.Principal;
import com.len.entity.ProjectInfo;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.ProjectInfoService;
import com.len.service.RoleUserService;
import com.len.service.SysUserService;
import com.len.util.*;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.HMProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.len.core.annotation.Log;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Controller
@RequestMapping(value = "/project")
public class ProjectInfoController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SysUserService userService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    IdentityService identityService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    HistoryService historyService;

    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    RoleUserService roleUserService;

    @GetMapping("showApply")
    public String applyProject(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/project/apply-proj";
    }

    @ApiOperation(value = "申报项目", httpMethod = "POST")
    @Log(desc = "申报项目")
    @PostMapping("applyProject")
    @ResponseBody
    public JsonUtil applyProject(ProjectInfo projectInfo) {
        JsonUtil j = new JsonUtil();
        String msg = "项目信息添加成功";
        projectInfo.setProjState(0);
        projectInfo.setUserId(projectInfo.getPmId());
        projectInfo.setUserName(projectInfo.getPmName());
        projectInfo.setPmId(Principal.getPrincipal().getId());
        System.out.println(projectInfo.toString());
        try {
            projectInfoService.insertSelective(projectInfo);
            Map<String, Object> map = new HashMap<>();
            projectInfo.setUrlpath("/project/showProjDetail/" + projectInfo.getId());
            map.put("baseTask", projectInfo);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ProjectApply", map);
            System.out.println("\n" + processInstance + "\n");
            projectInfo.setProcessInstanceId(processInstance.getId());
            ProjectInfo proj_with_proc = projectInfoService.selectByPrimaryKey(projectInfo.getId());
            BeanUtil.copyNotNullBean(projectInfo, proj_with_proc);
            Task task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            System.out.println("taskName " + task.getName());
            SysUser currUser = userService.selectByPrimaryKey(CommonUtil.getUser().getId());
            // 可在监听器中实现
            // 完成：提交项目申请
            Map<String, Object> vars = new HashMap<>();
            // 获取上级的邮箱
            String chiefId = currUser.getChiefId();
            String chiefMailAddr = userService.selectByPrimaryKey(chiefId).getEmail();
            vars.put("chief_mail", chiefMailAddr);
            vars.put("pm_name", currUser.getRealName());
            vars.put("pm_mail", currUser.getEmail());
            vars.put("qa_mail", userService.getUserByRoleId("0d3178de48cb482cb0bb3a9ae9ff3764").get(0).getEmail());
            vars.put("epg_mail", userService.getUserByRoleId("f7376c6bc042419491be758ae2683842").get(0).getEmail());
            vars.put("conf_mail", userService.getUserByRoleId("b1e002ad12ff4d2ebb024d74d27af432").get(0).getEmail());
            vars.put("proj_name", projectInfo.getProjName());
            taskService.complete(task.getId(), vars);
            // taskService.complete(task.getId());
            task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            proj_with_proc.setTaskName(task.getName());
            System.out.println("taskName " + task.getName());
            projectInfoService.updateByPrimaryKeySelective(proj_with_proc);
        } catch (MyException e) {
            msg = "保存失败";
            j.setFlag(false);
            e.printStackTrace();
        }
        j.setMsg(msg);
        return j;
    }

    @GetMapping("showPMproject")
    public String showPMproject(Model model) {
        String id = Principal.getPrincipal().getId();
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "act/project/my-pm-project";
    }

    @ApiOperation(value = "某用户主管的项目", httpMethod = "GET")
    @Log(desc = "主管项目")
    @GetMapping("showPMprojctList")
    @ResponseBody
    public ReType showPMprojctList() {
        List<ProjectInfo> list = projectInfoService.selectByPmId(Principal.getPrincipal().getId());
        return new ReType(list.size(), list);
    }

    @GetMapping("showProjDetail")
    public String showProjDetail(Model model, String projId) {
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projId);
        System.out.println(JSON.toJSONString(projectInfo));
        model.addAttribute("projectDetail", projectInfo);
        return "act/project/projDetail";
    }

    @GetMapping("showApprovals")
    public String showApprovals(Model model, String user) {
        return "act/project/approvals";
    }

    @GetMapping("projFunc")
    public String setProjFuncs(Model model, String projId) {
        model.addAttribute("projectId", projId);
        return "act/project/proj-func";
    }

    @GetMapping("/projApprovalProcess")
    public String showProjProcess(Model model, String processInsId) {
        model.addAttribute("processInstanceId", processInsId);
        return "/act/project/shinePics";
    }

    @GetMapping("/getShineProcImage")
    @ResponseBody
    public String getShineProcImage(HttpServletRequest request, HttpServletResponse resp, String processInstanceId)
            throws IOException {
        JSONObject result = new JSONObject();
        JSONArray shineProImages = new JSONArray();
        BASE64Encoder encoder = new BASE64Encoder();
        InputStream imageStream = generateStream(request, resp, processInstanceId, true);
        if (imageStream != null) {
            String imageCurrentNode = Base64Utils.ioToBase64(imageStream);
            if (StringUtils.isNotBlank(imageCurrentNode)) {
                shineProImages.add(imageCurrentNode);
            }
        }
        InputStream imageNoCurrentStream = generateStream(request, resp, processInstanceId, false);
        if (imageNoCurrentStream != null) {
            String imageNoCurrentNode = Base64Utils.ioToBase64(imageNoCurrentStream);
            if (StringUtils.isNotBlank(imageNoCurrentNode)) {
                shineProImages.add(imageNoCurrentNode);
            }
        }
        result.put("id", UUID.randomUUID().toString());
        result.put("errorNo", 0);
        result.put("images", shineProImages);
        return result.toJSONString();
    }

    public InputStream generateStream(HttpServletRequest request, HttpServletResponse resp, String processInstanceId, boolean needCurrent) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = null;
        List<String> executedActivityIdList = new ArrayList<String>();
        List<String> currentActivityIdList = new ArrayList<>();
        List<HistoricActivityInstance> historicActivityInstanceList = new ArrayList<>();
        if (processInstance != null) {
            processDefinitionId = processInstance.getProcessDefinitionId();
            if (needCurrent) {
                currentActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
            }
        }
        if (historicProcessInstance != null) {
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            historicActivityInstanceList =
                    historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }
        }

        if (StringUtils.isEmpty(processDefinitionId) || executedActivityIdList.isEmpty()) {
            return null;
        }

        //高亮线路id集合
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, historicActivityInstanceList);

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        HMProcessDiagramGenerator diagramGenerator = (HMProcessDiagramGenerator) processEngineConfiguration.getProcessDiagramGenerator();
        //List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());

        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel, "png",
                executedActivityIdList, highLightedFlows,
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                "宋体",
                null, 1.0, currentActivityIdList);

        return imageStream;
    }

    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }


}
