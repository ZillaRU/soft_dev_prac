package com.len.controller;

import com.alibaba.fastjson.JSONArray;
import com.len.base.BaseController;
import com.len.core.annotation.Log;
import com.len.core.annotation.Log.LOG_TYPE;
import com.len.entity.SysRole;
import com.len.service.MenuService;
import com.len.service.RoleMenuService;
import com.len.service.RoleService;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 * 角色业务
 */
@Controller
@RequestMapping(value = "/role")
@Api(value = "用户角色管理",description="角色业务处理")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping(value = "showRole")
    @RequiresPermissions(value = "role:show")
    public String showRole(Model model) {
        return "/system/role/roleList";
    }

    @ApiOperation(value = "/showRoleList", httpMethod = "GET", notes = "展示角色")
    @GetMapping(value = "showRoleList")
    @ResponseBody
    @RequiresPermissions("role:show")
    public ReType showRoleList(SysRole role, Model model, String page, String limit) {
        return roleService.show(role, Integer.valueOf(page), Integer.valueOf(limit));
    }

    @ApiOperation(value = "/showaLLRoleList", httpMethod = "GET", notes = "展示角色")
    @GetMapping(value = "showaLLRoleList")
    @ResponseBody
    @RequiresPermissions("role:show")
    public String showRoleList(SysRole role, Model model) {
        return roleService.showAll(role);
    }


    @GetMapping(value = "showAddRole")
    public String goAddRole(Model model) {
        JSONArray jsonArray = menuService.getTreeUtil(null);
        model.addAttribute("menus", jsonArray.toJSONString());
        return "/system/role/add-role";
    }

    @ApiOperation(value = "/addRole", httpMethod = "POST", notes = "添加角色")
    @Log(desc = "添加角色")
    @PostMapping(value = "addRole")
    @ResponseBody
    public JsonUtil addRole(SysRole sysRole, String[] menus) {
        if (StringUtils.isEmpty(sysRole.getRoleName())) {
            JsonUtil.error("角色名称不能为空");
        }
        return roleService.addRole(sysRole, menus);
    }

    @GetMapping(value = "updateRole")
    public String updateRole(String id, Model model, boolean detail) {
        if (StringUtils.isNotEmpty(id)) {
            SysRole role = roleService.selectByPrimaryKey(id);
            model.addAttribute("role", role);
            JSONArray jsonArray = menuService.getTreeUtil(id);
            model.addAttribute("menus", jsonArray.toJSONString());
        }
        model.addAttribute("detail", detail);
        return "system/role/update-role";
    }

    @ApiOperation(value = "/updateRole", httpMethod = "POST", notes = "更新角色")
    @Log(desc = "更新角色")
    @PostMapping(value = "updateRole")
    @ResponseBody
    public JsonUtil updateUser(SysRole role, String[] menus) {
        if (role == null) {
            return JsonUtil.error("获取数据失败");
        }
        return roleService.updateUser(role, menus);
    }

    @ApiOperation(value = "/del", httpMethod = "POST", notes = "删除角色")
    @Log(desc = "删除角色", type = LOG_TYPE.DEL)
    @PostMapping(value = "del")
    @ResponseBody
    @RequiresPermissions("role:del")
    public JsonUtil del(String id) {
        if (StringUtils.isEmpty(id)) {
            return JsonUtil.error("获取数据失败");
        }
        return roleService.del(id);
    }

}
