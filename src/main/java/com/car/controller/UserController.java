package com.car.controller;

import com.car.pojo.Page;
import com.car.pojo.User;
import com.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录功能
     * @param user
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("login.html")
    public String login(User user, HttpSession session, Model model) {
        User u = userService.getUserByUsername(user.getUsername());
        if(u==null) {
            // 查询结果为空，说明没有这个用户
            model.addAttribute("error", "用户名或密码错误!");
            return "login";
        }else {
            // 对比密码
            if(!user.getPassword().equals(u.getPassword())) {
                // 密码不一样
                model.addAttribute("error", "用户名或密码错误!");
                return "login";
            }
        }
        // 全部通过，查询到了用户
        session.setAttribute("user", u);
        return "redirect:/index.html";
    }

    /**
     * 注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("register.html")
    public String register(User user, Model model) {
        User u = userService.getUserByUsername(user.getUsername());
        if(u!=null) {
            // 不等于空，用户名已存在
            model.addAttribute("error", "用户已存在！");
            return "register";
        }
        // 根据手机号查询用户
        u = userService.getByUserTelephone(user.getUserTelephone());
        if(u!=null) {
            // 不等于空，手机号已被注册
            model.addAttribute("error", "手机号已被注册！");
            return "register";
        }
        // 用户名不存在，注册
        userService.insertUser(user);
        return "login";
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping("logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    /**
     * 分页查询用户列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("userList.html")
    public String userList(Page<User> page, Model model) {
        Page<User> p = userService.getUserList(page);
        model.addAttribute("page", p);
        return "/user/userList";
    }

    /**
     * 新增用户
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("addUser.html")
    public String addUser(User user, Model model) {
        // 和注册逻辑类似，先查询是否有这个用户，没有再添加
        User u = userService.getUserByUsername(user.getUsername());
        if(u!=null) {
            // 不等于空，用户名已存在
            model.addAttribute("error", "用户已存在！");
            return "/user/addUser";
        }
        // 根据手机号查询用户
        u = userService.getByUserTelephone(user.getUserTelephone());
        if(u!=null) {
            // 不等于空，手机号已被注册
            model.addAttribute("error", "手机号已被注册！");
            return "/user/addUser";
        }
        // 用户名不存在，新增
        userService.insertUser(user);
        return "redirect:/api/user/userList.html";
    }

    /**
     * 根据id获取用户，跳转到编辑界面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("getUser/{userId}.html")
    public String getUser(@PathVariable String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/user/editUser";
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("updateUser.html")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/api/user/userList.html";
    }

    /**
     * 获取学员信息跳转到充值学时页面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("toRecharge/{userId}.html")
    public String toRecharge(@PathVariable String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/user/rechargeUser";
    }

    /**
     * 删除学员
     * @param userId
     * @param userIds
     * @return
     */
    @RequestMapping("deleteUser{userId}.html")
    public String deleteTeacher(@PathVariable String userId, String[] userIds) {
        // 如果ids不为空，就是批量删除，如果为空，就是单个删
        if(userIds==null) {
            userService.deleteById(userId);
        }else {
            for (String id : userIds) {
                userService.deleteById(id);
            }
        }
        return "redirect:/api/user/userList.html";
    }

    /**
     * 充值学时
     * @param user
     * @return
     */
    @RequestMapping("recharge.html")
    public String recharge(User user) {
        // 查询出这个学员
        User u = userService.getUserById(user.getUserId());
        // 学时加上页面传来的学时
        u.setUserTime(u.getUserTime()+user.getUserTime());
        userService.updateUser(u);
        return "redirect:/api/user/userList.html";
    }

    /**
     * 通过考试
     */
    @RequestMapping("adopt/{userId}.html")
    public String adopt(@PathVariable String userId) {
        User u = userService.getUserById(userId);
        u.setUserState(2);
        userService.updateUser(u);
        return "redirect:/api/user/userList.html";
    }


    /**
     * 获取我的信息，跳转到我的信息页面
     * @param model
     * @return
     */
    @RequestMapping("myInfo.html")
    public String myInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/user/myInfo";
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    @RequestMapping("updateMyInfo.html")
    public String updateMyInfo(User user, HttpSession session) {
        userService.updateUser(user);
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }


}
