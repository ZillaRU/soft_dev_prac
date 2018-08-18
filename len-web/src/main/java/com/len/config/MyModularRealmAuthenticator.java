package com.len.config;

import com.len.util.CustomUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zhuxiaomeng
 * @date 2018/8/18.
 * @email 154040976@qq.com
 * 多模块认证
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;
        String type = token.getType();
        Collection<Realm> realms = getRealms();
        Collection<Realm> realmsList = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(type)) {
                realmsList.add(realm);
            }
        }
        return realmsList.size() == 1 ? doSingleRealmAuthentication(realmsList.iterator().next(), token)
                : doMultiRealmAuthentication(realmsList, token);
    }
}
