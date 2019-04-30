package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.SysRole;
import com.len.entity.SysRoleMenu;
import com.len.entity.SysRoleUser;
import com.len.exception.MyException;
import com.len.mapper.SysRoleMapper;
import com.len.service.RoleMenuService;
import com.len.service.RoleService;
import com.len.service.RoleUserService;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, String> implements RoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleUserService roleUserService;

    @Override
    public BaseMapper<SysRole, String> getMappser() {
        return roleMapper;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole record) {
        record = super.addValue(record, true);
        return roleMapper.insert(record);
    }

    @Override
    public SysRole selectByPrimaryKey(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysRole> selectListByPage(SysRole sysRole) {
        return roleMapper.selectListByPage(sysRole);
    }

    @Override
    public JsonUtil addRole(SysRole sysRole, String[] menus) {
        JsonUtil j = new JsonUtil();
        try {
            insertSelective(sysRole);
            //操作role-menu data
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(sysRole.getId());

            if (menus != null) {
                for (String menu : menus) {
                    sysRoleMenu.setMenuId(menu);
                    roleMenuService.insert(sysRoleMenu);
                }
            }
            j.setMsg("保存成功");
        } catch (MyException e) {
            j.setMsg("保存失败");
            j.setFlag(false);
            e.printStackTrace();
        }
        return j;
    }

    @Override
    public JsonUtil updateUser(SysRole role, String[] menus) {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setFlag(false);
        try {
            SysRole oldRole = selectByPrimaryKey(role.getId());
            BeanUtil.copyNotNullBean(role, oldRole);
            updateByPrimaryKeySelective(oldRole);

            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getId());
            List<SysRoleMenu> menuList = roleMenuService.selectByCondition(sysRoleMenu);
            for (SysRoleMenu sysRoleMenu1 : menuList) {
                roleMenuService.deleteByPrimaryKey(sysRoleMenu1);
            }
            if (menus != null)
                for (String menu : menus) {
                    sysRoleMenu.setMenuId(menu);
                    roleMenuService.insert(sysRoleMenu);
                }
            jsonUtil.setFlag(true);
            jsonUtil.setMsg("修改成功");
        } catch (MyException e) {
            jsonUtil.setMsg("修改失败");
            e.printStackTrace();
        }
        return jsonUtil;
    }

    @Override
    public JsonUtil del(String id) {
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(id);
        JsonUtil j = new JsonUtil();
        try {
            int count = roleUserService.selectCountByCondition(sysRoleUser);
            if (count > 0) {
                return JsonUtil.error("已分配给用户，删除失败");
            }
            deleteByPrimaryKey(id);
            j.setMsg("删除成功");
        } catch (MyException e) {
            j.setMsg("删除失败");
            j.setFlag(false);
            e.printStackTrace();
        }
        return j;
    }
}
