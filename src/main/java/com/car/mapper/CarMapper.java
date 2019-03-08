package com.car.mapper;

import com.car.pojo.Car;
import com.car.pojo.Page;

import java.util.List;

public interface CarMapper {

    /**
     * 新增车辆
     * @param car
     */
    void addCar(Car car);

    /**
     * 分页查询车辆列表
     * @param page
     * @return
     */
    List<Car> getCarList(Page<Car> page);

    /**
     * 查询总条数
     * @param page
     * @return
     */
    Integer getCarCount(Page<Car> page);

    /**
     * 根据id获取车辆
     * @param carId
     * @return
     */
    Car getCarById(String carId);

    /**
     * 修改车辆
     * @param car
     */
    void updateCar(Car car);

    /**
     * 根据id删除车辆
     * @param carId
     */
    void deleteCarById(String carId);

    /**
     * 查询所有未匹配教练的车辆
     * @return
     */
    List<Car> getCarListNotTeacher();

    /**
     * 根据车辆名查询
     * @param carName
     * @return
     */
    Car getByName(String carName);
}
