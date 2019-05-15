package com.springboot.shiro.service.impl;

import com.springboot.shiro.entity.User;
import com.springboot.shiro.http.response.ResUser;
import com.springboot.shiro.mapper.UserMapper;
import com.springboot.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by heyou on 2019/5/14 0014.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
