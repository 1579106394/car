package com.car.service;

import com.car.pojo.Page;
import com.car.pojo.Teacher;

public interface TeacherService {

    /**
     * 查询教练列表
     * @param page
     * @return
     */
    Page<Teacher> getTeacherList(Page<Teacher> page);

    /**
     * 新增教师
     */
    void addTeacher(Teacher teacher);

    /**
     * 根据id获取教师信息
     * @param teacherId
     * @return
     */
    Teacher getTeacherById(String teacherId);

    /**
     * 更新教练信息
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);
}
