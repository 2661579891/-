package com.hopu.phone_admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    public String login(String userName, String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(userName);
        token.setPassword(password.toCharArray());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("username", userName);
            return "redirect:/index.html";
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
        }
        return "redirect:/login.html";
    }

    @RequiresPermissions("财务管理")
    @ResponseBody
    @RequestMapping("test")
    public String test() {
        return "你好啊！";
    }
}
