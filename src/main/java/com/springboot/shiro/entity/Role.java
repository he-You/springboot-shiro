package com.springboot.shiro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator 2019/05/14
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer rid;

    private String rname;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();

    private static final long serialVersionUID = 1L;
}