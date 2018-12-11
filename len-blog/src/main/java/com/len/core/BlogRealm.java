package com.len.core;

import com.len.base.CurrentMenu;
import com.len.base.CurrentRole;
import com.len.base.CurrentUser;
import com.len.core.shiro.ShiroUtil;
import com.len.entity.SysUser;
import com.len.service.MenuService;
import com.len.service.RoleMenuService;
import com.len.service.RoleUserService;
import com.len.service.SysUserService;
import com.len.util.JWTUtil;
import com.len.util.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 */
@Service
public class BlogRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String name = (String) principalCollection.getPrimaryPrincipal();
        JWTUtil.getUsername(name);
        //根据用户获取角色 根据角色获取所有按钮权限
        CurrentUser cUser = (CurrentUser) ShiroUtil.getSession().getAttribute("curentUser");
        for (CurrentRole cRole : cUser.getCurrentRoleList()) {
            info.addRole(cRole.getId());
        }
        for (CurrentMenu cMenu : cUser.getCurrentMenuList()) {
            if (!StringUtils.isEmpty(cMenu.getPermission())) {
                info.addStringPermission(cMenu.getPermission());
            }
        }
        return info;
    }

    /**
     * 获取授权
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String username = JWTUtil.getUsername(token.getToken());
        if (StringUtils.isEmpty(username)) {
            throw new UnknownAccountException("令牌无效");
        }
        SysUser s = userService.login(username);
        if (s == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        if (!JWTUtil.verify(token.getToken(), username, s.getPassword())) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token.getToken(), token.getToken(), getName());
    }
}
