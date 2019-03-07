package com.car.mapper;

import com.car.pojo.Page;
import com.car.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    /**
     * 查询教练列表
     * @param page
     * @return
     */
    List<Teacher> getTeacherList(Page<Teacher> page);

    /**
     * 查询教练数量
     * @param page
     * @return
     */
    Integer getTeacherCount(Page<Teacher> page);

    /**
     * 新增教练
     * @param teacher
     */
    void addTeacher(Teacher teacher);

    /**
     * 根据id获取教练信息
     * @param teacherId
     * @return
     */
    Teacher getTeacherById(String teacherId);

    /**
     * 更新教练
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);
}
