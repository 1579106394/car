package com.car.pojo;

import java.io.Serializable;

public class Car implements Serializable {

    private String carId;
    private String carName;
    private Integer carNum;
    private Integer carFlag;

    public Integer getCarFlag() {
        return carFlag;
    }

    public void setCarFlag(Integer carFlag) {
        this.carFlag = carFlag;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", carName='" + carName + '\'' +
                ", carNum=" + carNum +
                '}';
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

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }
}
