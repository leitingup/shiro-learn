package org.shiro.demo01.service;

import org.shiro.demo01.mapper.UserMapper;
import org.shiro.demo01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
