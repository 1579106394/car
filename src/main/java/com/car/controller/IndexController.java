package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 因为没有index页面，所以用controller模拟index页面进行跳转
     */
    @RequestMapping("/index.html")
    public String index() {
        return "redirect:/api/teacher/teacherList.html";
    }

}
