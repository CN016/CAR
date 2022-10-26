package com.designby016.car.service;

import com.designby016.car.object.Admin;
import com.designby016.car.object.CarInfo;

import javax.servlet.http.HttpSession;

public interface AdminService {
    Admin login(Admin admin, HttpSession session);

    String insertCar(CarInfo carInfo);

    String deleteCar(CarInfo carInfo);
}
