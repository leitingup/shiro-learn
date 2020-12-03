package org.shiro.demo01.service;

import org.shiro.demo01.pojo.User;

public interface UserService {

    public User queryUserByName(String username);
}
