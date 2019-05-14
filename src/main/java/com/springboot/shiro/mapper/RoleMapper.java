package com.springboot.shiro.mapper;

import com.springboot.shiro.entity.Role;

/**
* Created by Mybatis Generator 2019/05/14
*/
public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}