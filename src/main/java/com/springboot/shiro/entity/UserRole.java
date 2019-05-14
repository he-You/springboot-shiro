package com.springboot.shiro.entity;

import java.io.Serializable;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/05/14
*/
@Data
public class UserRole implements Serializable {
    private Integer uid;

    private Integer rid;

    private static final long serialVersionUID = 1L;
}