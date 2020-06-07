package com.hopu.phone_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("index.html")
    public String index(Model model){
        model.addAttribute("msg","hello word");
        return "index";
    }


    @RequestMapping("login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("test.html")
    public String test(){
        return "test";
    }
}
