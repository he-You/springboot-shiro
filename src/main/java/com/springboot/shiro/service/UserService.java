package com.springboot.shiro.service;

import com.springboot.shiro.entity.User;


/**
 * Created by heyou on 2019/5/14 0014.
 */
public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return user
     */
    User findByUserName(String username);
}
