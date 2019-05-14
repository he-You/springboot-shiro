package com.springboot.shiro.mapper;

import com.springboot.shiro.entity.Permission;

/**
* Created by Mybatis Generator 2019/05/14
*/
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}