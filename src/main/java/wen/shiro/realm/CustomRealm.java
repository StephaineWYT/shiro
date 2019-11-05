package wen.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import wen.shiro.bean.Permission;
import wen.shiro.bean.Role;
import wen.shiro.bean.User;
import wen.shiro.service.LoginService;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 根据用户名去数据库查询用户信息
        User user = loginService.getUserByUsername(username);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRole());
            // 添加权限
            for (Permission permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        // 在post请求之前先进行认证
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByUsername(username);
        if (user == null) {
            // 返回后会报出异常
            return null;
        } else {
            //验证 authenticationToken和 simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo
                    = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
