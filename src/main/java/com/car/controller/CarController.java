package com.car.controller;

import com.car.pojo.Car;
import com.car.pojo.Page;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/car/")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 分页查询车辆列表
     * @param page
     * @return
     */
    @RequestMapping(value = "carList.html")
    public String carList(Page<Car> page, Model model) {
        Page<Car> p = carService.getCarList(page);
        model.addAttribute("page", p);
        return "/car/carList";
    }

    /**
     * 新增车辆
     * @param car
     * @return
     */
    @RequestMapping("addCar.html")
    public String addCar(Car car, Model model) {
        Car c = carService.getByName(car.getCarName());
        if(c!=null) {
            model.addAttribute("error", "车牌号已存在！");
            return "/car/addCar";
        }
        carService.addCar(car);
        return "redirect:/api/car/carList.html";
    }

    /**
     * 根据id获取车辆
     * @param carId
     * @return
     */
    @RequestMapping("getCar/{carId}.html")
    public String getCarById(@PathVariable String carId, Model model) {
        Car car = carService.getCarById(carId);
        model.addAttribute("car", car);
        return "/car/editCar";
    }

    /**
     * 编辑车辆信息
     * @param car
     * @return
     */
    @RequestMapping("editCar.html")
    public String editCar(Car car) {
        carService.updateCar(car);
        return "redirect:/api/car/carList.html";
    }

    /**
     * 根据id删除车辆
     * @param carId
     * @param carIds
     * @return
     */
    @RequestMapping("deleteCar{carId}.html")
    public String deleteCar(@PathVariable String carId, String[] carIds) {
        if(carIds==null) {
            carService.deleteCarById(carId);
        }else {
            for (String id : carIds) {
                carService.deleteCarById(id);
            }
        }
        return "redirect:/api/car/carList.html";
    }
}
