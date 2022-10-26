package com.designby016.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "login.html";
    }

    @RequestMapping("/404")
    public String Error404(){
        return "404.html";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome.html";
    }

    @RequestMapping("/back")
    public String back(){
        return "back.html";
    }

    @RequestMapping("/backIndex")
    public String backIndex(){
        return "backIndex.html";
    }
}
