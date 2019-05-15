package com.springboot.shiro.mapper;

import com.springboot.shiro.entity.User;

/**
* Created by Mybatis Generator 2019/05/14
*/
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return user
     */
    User findByUserName(String username);
}