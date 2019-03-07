package com.car.service;

import com.car.pojo.Page;
import com.car.pojo.User;

public interface UserService {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    Page<User> getUserList(Page<User> page);

    /**
     * 根据手机号查询用户
     * @param userTelephone
     * @return
     */
    User getByUserTelephone(String userTelephone);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * 编辑用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteById(String id);
}
