package com.designby016.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.designby016.car.mapper.CarInfoMapper;
import com.designby016.car.mapper.IoTableMapper;
import com.designby016.car.mapper.UserMapper;
import com.designby016.car.object.CarInfo;
import com.designby016.car.object.IoTable;
import com.designby016.car.object.Tel;
import com.designby016.car.object.User;
import com.designby016.car.service.UserService;
import com.designby016.car.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IoTableMapper ioTableMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public String registerResult(User user) {
        String inputUsername = user.getUsername();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",inputUsername);
        wrapper.last("limit 1");
        User dataUser = userMapper.selectOne(wrapper);
        if (dataUser!=null){
            return "用户名已存在";
        }else {
            String password = user.getPassword();
            password = MD5Utils.MD5EncodeUtf8(password); //加密
            user.setPassword(password);
            userMapper.insert(user);
            return "注册成功";
        }
    }

    @Override
    public User loginResult(User user) {
        String inputUsername = user.getUsername();
        String inputPassword =  MD5Utils.MD5EncodeUtf8(user.getPassword());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",inputUsername);
        wrapper.eq("password",inputPassword);
        User data = userMapper.selectOne(wrapper);
        return data;
    }

    @Override
    public List<CarInfo> search(User user) {  //查询自己借走的车辆
        String username = user.getUsername();
        QueryWrapper<IoTable> IWrapper = new QueryWrapper<>();
        IWrapper.eq("username",username);
        List<IoTable> IList = ioTableMapper.selectList(IWrapper);
        List<CarInfo> CList = new LinkedList<>();
        for (IoTable i : IList) {
            QueryWrapper<CarInfo> CWrapper = new QueryWrapper<>();
            CWrapper.eq("car_id",i.getCarId());
            CList.add( carInfoMapper.selectOne(CWrapper) );
        }
        return CList;
    }

    @Override
    public String setTel(Tel tel , HttpSession session) {
        String username = (String) session.getAttribute("user");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User user = userMapper.selectOne(userQueryWrapper);
        user.setTel(tel.getTel());
        userMapper.update(user, userQueryWrapper);
        System.out.println(user);
        return "修改成功";
    }
}
