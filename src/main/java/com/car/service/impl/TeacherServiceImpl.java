package com.car.service.impl;

import com.car.mapper.CarMapper;
import com.car.mapper.TeacherMapper;
import com.car.pojo.Car;
import com.car.pojo.Page;
import com.car.pojo.Teacher;
import com.car.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CarMapper carMapper;

    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    /**
     * 分页查询教练列表
     * @param page
     * @return
     */
    @Override
    public Page<Teacher> getTeacherList(Page<Teacher> page) {
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
        List<Teacher> teacherList = teacherMapper.getTeacherList(page);
        // 查询总条数
        Integer totalCount = teacherMapper.getTeacherCount(page);
        // 设置总条数
        page.setTotalCount(totalCount);
        // 设置数据
        page.setList(teacherList);
        // 计算总页数，总页数=总条数/每页显示条数 向上取整
        int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
        page.setTotalPage(totalPage);
        return page;
    }

    /**
     * 添加教练
     * @param teacher
     */
    @Override
    public void addTeacher(Teacher teacher) {
        // 设置属性
        teacher.setTeacherId(UUID.randomUUID().toString());
        teacherMapper.addTeacher(teacher);
        // 设置完之后，将车辆状态改为已配置教练
        String carId = teacher.getCar().getCarId();
        Car car = carMapper.getCarById(carId);
        car.setCarFlag(2);
        carMapper.updateCar(car);
    }

    /**
     * 根据id查询教练
     * @param teacherId
     * @return
     */
    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherMapper.getTeacherById(teacherId);
    }

    /**
     * 更新教练
     * @param teacher
     */
    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
        // 设置完之后，将车辆状态改为已配置教练
        String carId = teacher.getCar().getCarId();
        Car car = carMapper.getCarById(carId);
        car.setCarFlag(2);
        carMapper.updateCar(car);
    }

    @Override
    public void deleteById(String id) {
        // 先查询，用于后面更改车辆状态
        Teacher teacher = teacherMapper.getTeacherById(id);
        // 删除
        teacherMapper.deleteById(id);
        // 设置完之后，将车辆状态改为已配置教练
        String carId = teacher.getCar().getCarId();
        Car car = carMapper.getCarById(carId);
        car.setCarFlag(1);
        carMapper.updateCar(car);
    }
}
