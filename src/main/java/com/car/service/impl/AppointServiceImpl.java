package com.car.service.impl;

import com.car.mapper.AppointMapper;
import com.car.mapper.UserMapper;
import com.car.pojo.Appoint;
import com.car.pojo.Page;
import com.car.pojo.User;
import com.car.service.AppointService;
import com.car.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AppointServiceImpl implements AppointService {

    @Autowired
    private AppointMapper appointMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    /**
     * 新增预约
     * @param appoint
     * @return
     */
    @Override
    public boolean addAppoint(Appoint appoint) {
        // 查询这个时间段，该老师是否已经有了预约，有了返回false
        Appoint appoint1 = appointMapper.getByTime(appoint);
        if(appoint1!=null) {
            // 不为空，说明这个时间段有预约
            return false;
        }
        // 为空，则插入预约信息，并且从学生学时中减去对应的时间
        // 设置属性
        appoint.setAppointId(UUID.randomUUID().toString());
        appointMapper.addAppoint(appoint);
        // 新增结束，获取时间差天数，用户学时减掉
        try {
            Integer hour = DateUtils.getHour(appoint.getAppointStartDate(), appoint.getAppointEndDate());
            User user = userMapper.getUserById(appoint.getUser().getUserId());
            user.setUserTime(user.getUserTime()-hour);
            userMapper.updateUser(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Page<Appoint> getAppointList(Page<Appoint> page) {
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
        List<Appoint> appointList = appointMapper.getAppointList(page);
        // 查询总条数
        Integer totalCount = appointMapper.getAppointCount(page);
        // 设置总条数
        page.setTotalCount(totalCount);
        // 设置数据
        page.setList(appointList);
        // 计算总页数，总页数=总条数/每页显示条数 向上取整
        int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
        page.setTotalPage(totalPage);
        return page;
    }

    @Override
    public void deleteAppointById(String appointId) {
        appointMapper.deleteAppointById(appointId);
    }

    @Override
    public Appoint getAppointById(String appointId) {
        return appointMapper.getAppointById(appointId);
    }
}
