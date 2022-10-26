package com.designby016.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.designby016.car.mapper.CarInfoMapper;
import com.designby016.car.mapper.IoTableMapper;
import com.designby016.car.mapper.UserMapper;
import com.designby016.car.object.CarInfo;
import com.designby016.car.object.IoTable;
import com.designby016.car.object.User;
import com.designby016.car.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo> implements CarInfoService {
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IoTableMapper ioTableMapper;
    @Override
    public List<CarInfo> allInfo() {
        List<CarInfo> carInfos = carInfoMapper.selectList(null); //查询所有车辆信息
        return carInfos;
    }

    @Override
    public String lendCar(CarInfo carInfo, HttpSession session) {
        String username = (String) session.getAttribute("user");  //获取借车的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user.getTel() == null){
            return "请先设置电话号码！";      //数据库查询是否有预留电话号码
        }
        System.out.println(user);
        IoTable ioTable = new IoTable();
        ioTable.setUsername(username);
        ioTable.setLendTime(new Date());
        ioTable.setTel(user.getTel());
        ioTable.setCarId(carInfo.getCarId());    //准备好借车表对象，写入借车信息
        QueryWrapper<CarInfo> carInfoQueryWrapper = new QueryWrapper<>();
        carInfoQueryWrapper.eq("car_id",carInfo.getCarId());
        CarInfo carInfo1 = carInfoMapper.selectOne(carInfoQueryWrapper);
        if (carInfo1.getIsLive() > 0){       //查询可用车辆是否充足
            carInfo1.setIsLive(carInfo1.getIsLive()-1);
            carInfoMapper.update(carInfo1,carInfoQueryWrapper);
            ioTableMapper.insert(ioTable);
            return "出租"+carInfo.getCarId()+"成功";    //借车业务成功执行
        }else {
            return "可用车辆不足";
        }
    }

    @Override
    public String ret(CarInfo carInfo, HttpSession session) {
        String username = (String) session.getAttribute("user");
        QueryWrapper<IoTable> ioTableQueryWrapper = new QueryWrapper<>();
        ioTableQueryWrapper.eq("username",username);
        ioTableQueryWrapper.eq("car_id",carInfo.getCarId());
        System.out.println(username);
        System.out.println(carInfo.getCarId());
        List<IoTable> ioTables = ioTableMapper.selectList(ioTableQueryWrapper);
        System.out.println(ioTables);
        if (ioTables.size() == 0){
            return "未借用"+carInfo.getCarId();
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("username",username);
            map.put("tel",ioTables.get(0).getTel());
            map.put("lend_time",ioTables.get(0).getLendTime());
            map.put("car_id",carInfo.getCarId());
            ioTableMapper.deleteByMap(map);
            QueryWrapper<CarInfo> carInfoQueryWrapper = new QueryWrapper<>();
            carInfoQueryWrapper.eq("car_id",carInfo.getCarId());
            CarInfo carInfo1 = carInfoMapper.selectOne(carInfoQueryWrapper);
            carInfo1.setIsLive(carInfo1.getIsLive()+1);
            carInfoMapper.update(carInfo1,carInfoQueryWrapper);
            return "已归还一辆"+carInfo.getCarId();
        }
    }
}
