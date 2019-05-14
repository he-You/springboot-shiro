package com.springboot.shiro.entity;

import java.io.Serializable;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/05/14
*/
@Data
public class PermissionRole implements Serializable {
    private Integer rid;

    private Integer pid;

    private static final long serialVersionUID = 1L;
}