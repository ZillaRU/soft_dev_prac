package com.len.controller;

import com.len.base.BaseController;
import com.len.core.annotation.Log;
import com.len.entity.SysRoleUser;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.service.RoleUserService;
import com.len.service.SysUserService;
import com.len.util.BeanUtil;
import com.len.util.Checkbox;
import com.len.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhuxiaomeng
 * @date 2017/12/6.
 * @email 154040976@qq.com
 * 用户管理
 */
//@Api(value="user")
@Controller
@RequestMapping(value = "/user")
public class UserController  extends BaseController{

  //private static final Logger

  @Autowired
  SysUserService userService;

  @Autowired
  RoleUserService roleUserService;
  @GetMapping(value = "mainTest")
  public String showTest() {
    return "system/user/mainTest";
  }

  @GetMapping(value = "showUser")
  public String showUser(Model model) {
    return "/system/user/userList";
  }

  @GetMapping(value = "showUserList", produces = "text/json;charset=UTF-8")
  @ResponseBody
  public String showUser(Model model, SysUser user, String page, String limit) {
   // logger.error("show userList");
    return userService.show(user,Integer.valueOf(page),Integer.valueOf(limit));
  }

  @GetMapping(value = "showAddUser")
  public String addUser(Model model) {
    List<Checkbox> checkboxList=userService.getUserRoleByJson(null);
    model.addAttribute("boxJson",checkboxList);
    return "/system/user/add-user";
  }

  @ApiOperation(value = "/addUser", httpMethod = "POST", notes = "添加用户")
  @Log(desc = "添加用户")
  @PostMapping(value = "addUser")
  @ResponseBody
  public String addUser(SysUser user,String[] role) {
    if (user == null) {
      return "获取数据失败";
    }
    if (StringUtils.isBlank(user.getUsername())) {
      return "用户名不能为空";
    }
    if (StringUtils.isBlank(user.getPassword())) {
      return "密码不能为空";
    }
    if(role==null){
      return "请选择角色";
    }
    int result = userService.checkUser(user.getUsername());
    if (result > 0) {
      return "用户名已存在";
    }
    try {
      userService.add(user);
      SysRoleUser sysRoleUser=new SysRoleUser();
      sysRoleUser.setUserId(user.getId());
      for(String r:role){
        sysRoleUser.setRoleId(r);
        roleUserService.insertSelective(sysRoleUser);
      }
    } catch (MyException e) {
      e.printStackTrace();
    }
    return "保存成功";
  }

  @GetMapping(value = "updateUser")
  public String updateUser(String id, Model model, boolean detail) {
    if (StringUtils.isNotEmpty(id)) {
      //用户-角色
     List<Checkbox> checkboxList=userService.getUserRoleByJson(id);
      SysUser user = userService.selectByPrimaryKey(id);
      model.addAttribute("user", user);
      model.addAttribute("boxJson", checkboxList);
    }
    model.addAttribute("detail", detail);
    return "system/user/update-user";
  }

  @ApiOperation(value = "/updateUser", httpMethod = "POST", notes = "更新用户")
  @Log(desc = "更新用户")
  @PostMapping(value = "updateUser")
  @ResponseBody
  public JsonUtil updateUser(SysUser user,String role[]) {
    JsonUtil jsonUtil = new JsonUtil();
    jsonUtil.setFlag(false);
    if (user == null) {
      jsonUtil.setMsg("获取数据失败");
      return jsonUtil;
    }
    try {
      SysUser oldUser = userService.selectByPrimaryKey(user.getId());
      BeanUtil.copyNotNullBean(user, oldUser);
      userService.updateByPrimaryKeySelective(oldUser);

      SysRoleUser sysRoleUser =new SysRoleUser();
      sysRoleUser.setUserId(oldUser.getId());
      List<SysRoleUser> keyList=userService.selectByCondition(sysRoleUser);
      for(SysRoleUser sysRoleUser1 :keyList){
        roleUserService.deleteByPrimaryKey(sysRoleUser1);
      }

      for(String r:role){
        sysRoleUser.setRoleId(r);
        roleUserService.insert(sysRoleUser);
      }
      jsonUtil.setFlag(true);
      jsonUtil.setMsg("修改成功");
    } catch (MyException e) {
      e.printStackTrace();
    }
    return jsonUtil;
  }

 @ApiOperation(value = "/del", httpMethod = "POST", notes = "删除用户")
  @PostMapping(value = "del")
  @ResponseBody
  public String del(String id, boolean flag) {
    if (StringUtils.isEmpty(id)) {
      return "获取数据失败";
    }
    try {
      if (flag) {
        //逻辑
        SysUser sysUser = userService.selectByPrimaryKey(id);
        sysUser.setDelFlag(Byte.parseByte("1"));
        userService.updateByPrimaryKeySelective(sysUser);
      } else {
        //物理
        userService.delById(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "删除成功";
  }

  /**
   * 头像上传 目前首先相对路径
   */
  @PostMapping(value = "upload")
  @ResponseBody
  public JsonUtil imgUpload(HttpServletRequest req, @RequestParam("file") MultipartFile file,
      ModelMap model) {
    JsonUtil j = new JsonUtil();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("hhmmss");

    String fileName = sdf1.format(new Date()) + file.getOriginalFilename();
    String objPath =
        req.getSession().getServletContext().getRealPath("image/") + sdf.format(new Date())
            .toString();
    File targetFile1 = new File(objPath, fileName);
    File file2 = new File(objPath);
    if (!file2.exists()) {
      file2.mkdirs();
    }
    if (!targetFile1.exists()) {
      targetFile1.mkdirs();
    }

    try {
      file.transferTo(targetFile1);
    } catch (Exception e) {
      j.setFlag(false);
      j.setMsg("上传失败");
      e.printStackTrace();
    }
    j.setMsg("image/" + sdf.format(new Date()).toString() + "/" + req.getContextPath() + fileName);
    return j;
  }

  /**
   * 验证用户名是否存在
   */
  @GetMapping(value = "checkUser")
  @ResponseBody
  public JsonUtil checkUser(String uname, HttpServletRequest req) {
    JsonUtil j = new JsonUtil();
    j.setFlag(Boolean.FALSE);
    if (StringUtils.isEmpty(uname)) {
      j.setMsg("获取数据失败");
      return j;
    }
    int result = userService.checkUser(uname);
    if (result > 0) {
      j.setMsg("用户名已存在");
      return j;
    }
    j.setFlag(true);
    return j;

  }


}
