package com.designby016.car.service;

import com.designby016.car.object.CarInfo;
import com.designby016.car.object.Tel;
import com.designby016.car.object.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    public String registerResult(User user);

    public User loginResult(User user);

    public List<CarInfo> search(User user);

    public String setTel(Tel tel, HttpSession session);
}
