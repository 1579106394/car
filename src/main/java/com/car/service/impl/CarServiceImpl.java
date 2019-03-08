package com.car.service.impl;

import com.car.mapper.CarMapper;
import com.car.pojo.Car;
import com.car.service.CarService;
import com.car.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    /**
     * 新增车辆
     * @param car
     */
    @Override
    public void addCar(Car car) {
        car.setCarId(UUID.randomUUID().toString());
        carMapper.addCar(car);
    }

    /**
     * 分页查询车辆列表
     * @param page
     * @return
     */
    @Override
    public Page<Car> getCarList(Page<Car> page) {
        // 设置当前页，如果当前页为空，默认是0
        Integer currentPage = page.getCurrentPage();
        if(currentPage==null) {
            currentPage=1;
        }
        // 设置每页显示条数，从resource文件中读取
        Integer currentCount = CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        // 计算索引，index=（当前页-1）*每页条数
        int index = (currentPage-1)*currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        // 根据这些信息，分页查询
        List<Car> carList = carMapper.getCarList(page);
        // 查询总条数
        Integer totalCount = carMapper.getCarCount(page);
        // 设置总条数
        page.setTotalCount(totalCount);
        // 设置数据
        page.setList(carList);
        // 计算总页数，总页数=总条数/每页显示条数 向上取整
        int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
        page.setTotalPage(totalPage);
        return page;
    }

    /**
     * 根据id获取车辆
     * @param carId
     * @return
     */
    @Override
    public Car getCarById(String carId) {
        return carMapper.getCarById(carId);
    }

    /**
     * 修改车辆
     * @param car
     */
    @Override
    public void updateCar(Car car) {
        carMapper.updateCar(car);
    }

    /**
     * 根据id删除车辆
     * @param carId
     */
    @Override
    public void deleteCarById(String carId) {
        carMapper.deleteCarById(carId);
    }

    @Override
    public List<Car> getCarListNotTeacher() {
        return carMapper.getCarListNotTeacher();
    }

    @Override
    public Car getByName(String carName) {
        return carMapper.getByName(carName);
    }
}
