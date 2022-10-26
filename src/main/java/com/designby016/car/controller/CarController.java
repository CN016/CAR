package com.designby016.car.controller;

import com.designby016.car.mapper.IoTableMapper;
import com.designby016.car.object.CarInfo;
import com.designby016.car.object.IoTable;
import com.designby016.car.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private IoTableMapper ioTableMapper;

    @RequestMapping("/all")
    @ResponseBody
    public List<CarInfo> allInfo(){
        List<CarInfo> carInfos = carInfoService.allInfo();
        return carInfos;
    }

    @PostMapping("/lend")
    @ResponseBody
    public String lendCar(@RequestBody CarInfo carInfo , HttpSession session){
        String msg = carInfoService.lendCar(carInfo,session);
        return msg;
    }

    @PostMapping("/ret")
    @ResponseBody
    public String ret(@RequestBody CarInfo carInfo , HttpSession session){
        String msg = carInfoService.ret(carInfo,session);
        return msg;
    }

    @RequestMapping("/allIO")
    @ResponseBody
    public List<IoTable> allTable(){
        List<IoTable> list = ioTableMapper.selectList(null);
        return list;
    }
}
