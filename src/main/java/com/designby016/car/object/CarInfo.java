package com.designby016.car.object;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("car_info")
public class CarInfo {
    private String carId;
    private String carName;
    private Double cost;
    private Integer isLive;

    public CarInfo() {
    }

    public CarInfo(String carId, String carName, Double cost, Integer isLive) {
        this.carId = carId;
        this.carName = carName;
        this.cost = cost;
        this.isLive = isLive;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getIsLive() {
        return isLive;
    }

    public void setIsLive(Integer isLive) {
        this.isLive = isLive;
    }
}
