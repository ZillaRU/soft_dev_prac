package com.len.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.len.base.BaseMapper;
import com.len.base.CurrentMenu;
import com.len.base.CurrentRole;
import com.len.base.CurrentUser;
import com.len.base.impl.BaseServiceImpl;
import com.len.core.shiro.Principal;
import com.len.entity.SysMenu;
import com.len.entity.SysRole;
import com.len.entity.SysRoleUser;
import com.len.entity.SysUser;
import com.len.exception.MyException;
import com.len.mapper.SysRoleUserMapper;
import com.len.mapper.SysUserMapper;
import com.len.service.MenuService;
import com.len.service.RoleService;
import com.len.service.RoleUserService;
import com.len.service.SysUserService;
import com.len.util.BeanUtil;
import com.len.util.Checkbox;
import com.len.util.JsonUtil;
import com.len.util.Md5Util;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleUserService roleUserService;

    @Autowired
    MenuService menuService;

    private static final String ADMIN = "admin";

    @Override
    public BaseMapper<SysUser, String> getMappser() {
        return sysUserMapper;
    }


    @Override
    public SysUser login(String username) {
        return sysUserMapper.login(username);
    }


    @Override
    public int deleteByPrimaryKey(String id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {

        String pwd = Md5Util.getMD5(record.getPassword().trim(), record.getUsername().trim());
        record.setPassword(pwd);
        return super.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return super.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser) {
        return sysRoleUserMapper.selectByCondition(sysRoleUser);
    }

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @Override
    public List<SysUser> selectListByPage(SysUser sysUser) {
        return sysUserMapper.selectListByPage(sysUser);
    }

    @Override
    public int count() {
        return sysUserMapper.count();
    }

    @Override
    public int add(SysUser user) {
        //密码加密
        String pwd = Md5Util.getMD5(user.getPassword().trim(), user.getUsername().trim());
        user.setPassword(pwd);
        return sysUserMapper.add(user);
    }

    @Override
    public JsonUtil delById(String id, boolean flag) {
        if (StringUtils.isEmpty(id)) {
            return JsonUtil.error("获取数据失败");
        }
        JsonUtil j = new JsonUtil();
        try {
            SysUser sysUser = selectByPrimaryKey(id);
            if (ADMIN.equals(sysUser.getUsername())) {
                return JsonUtil.error("超管无法删除");
            }
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(id);
            int count = roleUserService.selectCountByCondition(roleUser);
            if (count > 0) {
                return JsonUtil.error("账户已经绑定角色，无法删除");
            }
            if (flag) {
                //逻辑
                sysUser.setDelFlag(Byte.parseByte("1"));
                updateByPrimaryKeySelective(sysUser);
            } else {
                //物理
                sysUserMapper.delById(id);
            }
            j.setMsg("删除成功");
        } catch (MyException e) {
            j.setMsg("删除失败");
            j.setFlag(false);
            e.printStackTrace();
        }
        return j;

    }

    @Override
    public int checkUser(String username) {
        return sysUserMapper.checkUser(username);
    }

    @Override
    public List<Checkbox> getUserRoleByJson(String id) {
        List<SysRole> roleList = roleService.selectListByPage(new SysRole());
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setUserId(id);
        List<SysRoleUser> kList = selectByCondition(sysRoleUser);

        List<Checkbox> checkboxList = new ArrayList<>();
        Checkbox checkbox;
        for (SysRole sysRole : roleList) {
            checkbox = new Checkbox();
            checkbox.setId(sysRole.getId());
            checkbox.setName(sysRole.getRoleName());
            for (SysRoleUser sysRoleUser1 : kList) {
                if (sysRoleUser1.getRoleId().equals(sysRole.getId())) {
                    checkbox.setCheck(true);
                    break;
                }
            }
            checkboxList.add(checkbox);
        }
        return checkboxList;
    }

    @Override
    public int rePass(SysUser user) {
        return sysUserMapper.rePass(user);
    }

    @Override
    public List<SysUser> getUserByRoleId(String roleId) {
        return sysUserMapper.getUserByRoleId(roleId);
    }



    @Override
    public void setMenuAndRoles(String username) {
        SysUser s = new SysUser();
        s.setUsername(username);
        s = this.selectOne(s);
        CurrentUser currentUser = new CurrentUser(s.getId(), s.getUsername(), s.getAge(), s.getEmail(), s.getPhoto(), s.getRealName());
        Subject subject = Principal.getSubject();
        /*角色权限封装进去*/
        //根据用户获取菜单
        Session session = subject.getSession();

        List<SysMenu> menuList = menuService.getUserMenu(s.getId());
        JSONArray json = menuService.getMenuJsonByUser(menuList);
        session.setAttribute("menu", json);


        List<CurrentMenu> currentMenuList = new ArrayList<>();
        Set<SysRole> roleList = new HashSet<>();
        for (SysMenu m : menuList) {
            CurrentMenu currentMenu = new CurrentMenu();
            BeanUtil.copyNotNullBean(m, currentMenu);
            currentMenuList.add(currentMenu);
            roleList.addAll(m.getRoleList());
        }

        List<CurrentRole> currentRoleList = new ArrayList<>();

        for (SysRole r : roleList) {
            CurrentRole role = new CurrentRole();
            BeanUtil.copyNotNullBean(r, role);
            currentRoleList.add(role);
        }
        currentUser.setCurrentRoleList(currentRoleList);
        currentUser.setCurrentMenuList(currentMenuList);
        session.setAttribute("currentPrincipal", currentUser);
    }

    /**
     * 更新session头像
     */
    @Override
    public void updateCurrent(SysUser sysUser) {
        CurrentUser principal = Principal.getPrincipal();
        if(principal.getId().equals(sysUser.getId())){
            //当前用户
            CurrentUser currentUse = Principal.getCurrentUse();
            Session session=Principal.getSession();
            currentUse.setPhoto(sysUser.getPhoto());
            session.setAttribute("currentPrincipal",currentUse);

        }
    }
}
