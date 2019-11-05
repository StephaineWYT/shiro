package wen.shiro.service;

import wen.shiro.bean.User;

public interface LoginService {

    User getUserByUsername(String username);

}
