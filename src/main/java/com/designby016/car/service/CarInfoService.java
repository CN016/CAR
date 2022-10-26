package com.designby016.car.service;

import com.designby016.car.object.CarInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CarInfoService {
    List<CarInfo> allInfo();

    String lendCar(CarInfo carInfo, HttpSession session);

    String ret(CarInfo carInfo, HttpSession session);
}
