package com.car.controller;

import com.car.pojo.Car;
import com.car.pojo.Page;
import com.car.pojo.Teacher;
import com.car.service.CarService;
import com.car.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/teacher/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CarService carService;

    /**
     * 分页查询教练列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("teacherList.html")
    public String teacherList(Page<Teacher> page, Model model) {
        Page<Teacher> p = teacherService.getTeacherList(page);
        model.addAttribute("page", p);
        return "/teacher/teacherList";
    }

    /**
     * 查询车辆信息，跳转到新增教练页面
     * @param model
     * @return
     */
    @RequestMapping("toAdd.html")
    public String toAdd(Model model) {
        // 查询所有未匹配教师的车辆
        List<Car> carList = carService.getCarListNotTeacher();
        model.addAttribute("carList", carList);
        return "/teacher/addTeacher";
    }

    /**
     * 添加教练
     * @param teacher
     * @return
     */
    @RequestMapping("addTeacher.html")
    public String addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/api/teacher/teacherList.html";
    }

    /**
     * 根据id获取教练信息
     * @param teacherId
     * @param model
     * @return
     */
    @RequestMapping("getTeacher/{teacherId}.html")
    public String getTeacher(@PathVariable String teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        List<Car> carList = carService.getCarListNotTeacher();
        model.addAttribute("carList", carList);
        return "/teacher/editTeacher";
    }

    /**
     * 更新教练
     * @param teacher
     * @param oldCar
     * @return
     */
    @RequestMapping("updateTeacher.html")
    public String updateTeacher(Teacher teacher, @RequestParam("oldCar") String oldCar) {
        if(!oldCar.equals(teacher.getCar().getCarId())) {
            // 修改前的车辆id和修改后不相同，说明改了车辆，将修改前的车辆状态改为2
            Car car = carService.getCarById(oldCar);
            car.setCarFlag(1);
            carService.updateCar(car);
        }
        teacherService.updateTeacher(teacher);
        return "redirect:/api/teacher/teacherList.html";
    }

    @RequestMapping("deleteTeacher{teacherId}.html")
    public String deleteTeacher(@PathVariable String teacherId, String[] teacherIds) {
        // 如果ids不为空，就是批量删除，如果为空，就是单个删
        if(teacherIds==null) {
            teacherService.deleteById(teacherId);
        }else {
            for (String id : teacherIds) {
                teacherService.deleteById(id);
            }
        }
        return "redirect:/api/teacher/teacherList.html";
    }
}
