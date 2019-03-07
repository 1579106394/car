package com.car.mapper;

import com.car.pojo.Appoint;
import com.car.pojo.Page;

import java.util.List;

public interface AppointMapper {

    /**
     * 根据预约时间和教练id查询预约情况是否存在
     * @param appoint
     * @return
     */
    Appoint getByTime(Appoint appoint);

    /**
     * 新增预约
     * @param appoint1
     */
    void addAppoint(Appoint appoint1);

    /**
     * 分页查询预约情况
     * @param page
     * @return
     */
    List<Appoint> getAppointList(Page<Appoint> page);

    /**
     * 查询数量
     * @param page
     * @return
     */
    Integer getAppointCount(Page<Appoint> page);

    /**
     * 根据id删除
     * @param appointId
     */
    void deleteAppointById(String appointId);

    /**
     * 根据id查询
     * @param appointId
     * @return
     */
    Appoint getAppointById(String appointId);
}
