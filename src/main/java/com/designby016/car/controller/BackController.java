package com.designby016.car.controller;

import com.designby016.car.object.Admin;
import com.designby016.car.object.CarInfo;
import com.designby016.car.object.User;
import com.designby016.car.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/back")
public class BackController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ResponseBody
    public String loginResult(@RequestBody Admin admin, HttpSession session){
        Admin t = adminService.login(admin,session);
        if (t!=null){
            session.setAttribute("admin",admin.getUsername());
            return "登陆成功";
        }else {
            return "登陆失败";
        }
    }

    @PostMapping("/insert")
    @ResponseBody
    public String insert(@RequestBody CarInfo carInfo){
        String msg = adminService.insertCar(carInfo);
        return msg;
    }

    @PostMapping("/deleteCar")
    @ResponseBody
    public String deleteCar(@RequestBody CarInfo carInfo){
        String msg = adminService.deleteCar(carInfo);
        return msg;
    }


}
