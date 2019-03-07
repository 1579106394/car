package com.car.service;

import com.car.pojo.Appoint;
import com.car.pojo.Page;

public interface AppointService {

    /**
     * 新增预约
     * @param appoint
     */
    boolean addAppoint(Appoint appoint);

    /**
     * 分页查询预约信息
     * @param page
     * @return
     */
    Page<Appoint> getAppointList(Page<Appoint> page);

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
