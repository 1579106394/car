package com.car.mapper;

import com.car.pojo.Page;
import com.car.pojo.User;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 查询今天最大学号
     * @return
     */
    String getMaxNumberByNow(String now);

    /**
     * 插入学生数据
     * @param user
     */
    void insertUser(User user);

    /**
     * 查询用户列表
     * @param page
     * @return
     */
    List<User> getUserList(Page<User> page);

    /**
     * 查询用户数量
     * @param page
     * @return
     */
    Integer getUserCount(Page<User> page);

    /**
     * 根据手机号查询用户
     * @param userTelephone
     * @return
     */
    User getUserByTelephone(String userTelephone);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteById(String id);
}
