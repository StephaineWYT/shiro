package wen.shiro.service.impl;

import org.springframework.stereotype.Service;
import wen.shiro.bean.Permission;
import wen.shiro.bean.Role;
import wen.shiro.bean.User;
import wen.shiro.service.LoginService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    Map<String, User> map = new HashMap<>();

    public LoginServiceImpl() {

        // 设置用户权限：query 和 add
        Permission queryPermission = new Permission(1L, "query");
        Permission addPermission = new Permission(2L, "add");

        // 用户一角色权限设置：读和写
        Set<Permission> permissionSetOne = new HashSet<>();
        permissionSetOne.add(queryPermission);
        permissionSetOne.add(addPermission);

        Role role = new Role(1L, "admin", permissionSetOne);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        User user = new User(1L, "admin_user", "123456", roleSet);
        map.put(user.getUsername(), user);

        // 用户二角色权限设置：只读权限
        Set<Permission> permissionSetTwo = new HashSet<>();
        permissionSetTwo.add(queryPermission);

        Role role1 = new Role(2L, "user", permissionSetTwo);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        User user1 = new User(2L, "visitor_user", "123456", roleSet1);
        map.put(user1.getUsername(), user1);

    }

    @Override
    public User getUserByUsername(String username) {
        return map.get(username);
    }

}
