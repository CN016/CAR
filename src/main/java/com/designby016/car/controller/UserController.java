package com.designby016.car.controller;

import com.designby016.car.object.CarInfo;
import com.designby016.car.object.Tel;
import com.designby016.car.object.User;
import com.designby016.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user ){
        String msg = userService.registerResult(user);
        return msg;
    }

    @PostMapping("/setTel")
    @ResponseBody
    public String setTel(@RequestBody Tel tel , HttpSession session){
        String msg = userService.setTel(tel,session);
        return msg;
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginResult(@RequestBody User user , HttpSession  session){
        User t = userService.loginResult(user);
        if (t!=null){
            session.setAttribute("user",user.getUsername());
            return "登陆成功";
        }else {
            return "登陆失败";
        }
    }

    @PostMapping("/search")
    @ResponseBody
    public List<CarInfo> selectUserAll(@RequestBody User user){
        return userService.search(user);
    }
}
