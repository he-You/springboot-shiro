package com.springboot.shiro.controller;

import com.springboot.shiro.entity.Result;
import com.springboot.shiro.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by heyou on 2019/5/14 0014.
 */
@Slf4j
@Controller
public class DemoController {
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User userInfo) {
        return new Result(true,"完成登录:"+userInfo.getRoles());
    }

    @RequestMapping("/index")
    public String index() {
        log.info("进入index页面");
        return null;
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
            log.info("完成退出");
        }

        return null;
    }

    @RequestMapping("/admin")
    @ResponseBody
    public Result admin(@RequestBody User userInfo) {
        log.info("管理员成功");
        return new Result(true,"欢迎"+userInfo.getRoles()+"登录");
    }

    @RequestMapping("/unauthorize")
    public Result unauthorize() {
        return new Result(false,"无权访问");
    }

    @RequestMapping("/loginUser")
    @ResponseBody
    public Result loginUser(@RequestBody User userInfo) {
       //UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        //System.out.println(token);

        Subject subject = SecurityUtils.getSubject();//得到Subject及创建用户名/密码验证Token(即用户身份/凭证)
        try {
            //subject.login(token);
            User user = (User) subject.getPrincipal();
            log.info("验证通过,进入首页");
            return new Result(true,"验证成功");
        } catch (Exception e) {
            log.error("验证不通过: {}", e.getMessage());
            return new Result(false,"验证失败");
        }
    }
}
