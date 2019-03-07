package com.car.service.impl;

import com.car.mapper.UserMapper;
import com.car.pojo.Page;
import com.car.pojo.User;
import com.car.service.UserService;
import com.car.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    /**
     * 熙增用户
     *
     * @param user
     */
    @Override
    public void insertUser(User user) {
        // 设置属性
        user.setUserId(UUID.randomUUID().toString());
        // 状态默认为“未通过”
        user.setUserState(1);
        // 身份默认为“学员”
        user.setUserRole(1);
        // 学时默认是20
        user.setUserTime(20);
        // 学员编号：查询表中最大编号，以20180303001的形式存入
        String now = DateUtils.newDate("yyyyMMdd");
        String maxNumber = userMapper.getMaxNumberByNow(now);
        if (maxNumber == null) {
            // 为空，说明该生是今天第一个注册的
            user.setUserNumber(now + "001");
        } else {
            // 截取最后三位
            int num = Integer.parseInt(maxNumber.substring(8));
            user.setUserNumber(now + (num + 1) + "");
        }
        // 插入数据
        userMapper.insertUser(user);
    }

    @Override
    public Page<User> getUserList(Page<User> page) {
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
        List<User> userList = userMapper.getUserList(page);
        // 查询总条数
        Integer totalCount = userMapper.getUserCount(page);
        // 设置总条数
        page.setTotalCount(totalCount);
        // 设置数据
        page.setList(userList);
        // 计算总页数，总页数=总条数/每页显示条数 向上取整
        int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
        page.setTotalPage(totalPage);
        return page;
    }

    @Override
    public User getByUserTelephone(String userTelephone) {
        return userMapper.getUserByTelephone(userTelephone);
    }

    @Override
    public User getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }
}
