package com.designby016.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.designby016.car.mapper.AdminMapper;
import com.designby016.car.mapper.CarInfoMapper;
import com.designby016.car.mapper.IoTableMapper;
import com.designby016.car.object.Admin;
import com.designby016.car.object.CarInfo;
import com.designby016.car.object.IoTable;
import com.designby016.car.service.AdminService;
import com.designby016.car.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private IoTableMapper ioTableMapper;

    @Override
    public Admin login(Admin admin, HttpSession session) {
        String username = admin.getUsername();
        String password = MD5Utils.MD5EncodeUtf8(admin.getPassword());
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        adminQueryWrapper.eq("password",password);
        Admin data = adminMapper.selectOne(adminQueryWrapper);
        return data;
    }

    @Override
    public String insertCar(CarInfo carInfo) {
        QueryWrapper<CarInfo> carInfoQueryWrapper = new QueryWrapper<>();
        carInfoQueryWrapper.eq("car_id",carInfo.getCarId());
        CarInfo carInfo1 = carInfoMapper.selectOne(carInfoQueryWrapper);
        if (carInfo1 == null){
            carInfoMapper.insert(carInfo);
            return "添加成功"+carInfo.getCarId()+"信息";
        }else {
            carInfoMapper.update(carInfo,carInfoQueryWrapper);
            return "修改成功"+carInfo.getCarId()+"信息";
        }
    }

    @Override
    public String deleteCar(CarInfo carInfo) {
        QueryWrapper<CarInfo> carInfoQueryWrapper = new QueryWrapper<>();
        carInfoQueryWrapper.eq("car_id",carInfo.getCarId());
        CarInfo carInfo1 = carInfoMapper.selectOne(carInfoQueryWrapper);
        QueryWrapper<IoTable> ioTableQueryWrapper = new QueryWrapper<>();
        ioTableQueryWrapper.eq("car_id",carInfo.getCarId());
        if (carInfo1 == null){
            return "没找到"+carInfo.getCarId()+"的信息";
        }else {
            carInfoMapper.delete(carInfoQueryWrapper);
            ioTableMapper.delete(ioTableQueryWrapper);
            return carInfo.getCarId()+"删除成功";
        }
    }
}
