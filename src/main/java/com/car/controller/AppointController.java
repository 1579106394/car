package com.car.controller;

import com.car.pojo.Appoint;
import com.car.pojo.Page;
import com.car.pojo.Teacher;
import com.car.pojo.User;
import com.car.service.AppointService;
import com.car.service.TeacherService;
import com.car.utils.DateUtils;
import com.car.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/api/appoint/")
public class AppointController {

    @Autowired
    private AppointService appointService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询教师信息，跳转到预约页面
     *
     * @param teacherId
     * @param model
     * @return
     */
    @RequestMapping("toAppoint.html")
    public String toAppoint(String teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "/appoint/addAppoint";
    }

    /**
     * 新增预约
     */
    @RequestMapping("addAppoint.html")
    public String addApoint(Appoint appoint, Model model, HttpSession session) throws ParseException {
        String startDate = appoint.getAppointStartDate();
        Teacher teacher = teacherService.getTeacherById(appoint.getTeacher().getTeacherId());
        if (StringUtils.isBlank(startDate)) {
            model.addAttribute("teacher", teacher);
            model.addAttribute("error", "请选择预约时间!");
            return "/appoint/addAppoint";
        }
        String[] split = startDate.split(" - ");
        appoint.setAppointStartDate(split[0]);
        appoint.setAppointEndDate(split[1]);
        User user = (User) session.getAttribute("user");
        Integer hour = DateUtils.getHour(appoint.getAppointStartDate(), appoint.getAppointEndDate());
        if (user.getUserTime() < hour) {
            // 用户不够
            model.addAttribute("teacher", teacher);
            model.addAttribute("error", "您的学时不足，请及时充值！");
            return "/appoint/addAppoint";
        }
        appoint.setUser(user);
        boolean flag = appointService.addAppoint(appoint);
        if (!flag) {
            // 预约失败，这个时间段有了预约
            model.addAttribute("teacher", teacher);
            model.addAttribute("error", "教练该时间段已有预约!");
            return "/appoint/addAppoint";
        }
        return "redirect:/api/appoint/appointList.html";
    }

    /**
     * 分页查询预约情况列表
     * @param page
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("appointList.html")
    public String appointList(Page<Appoint> page, Model model, HttpSession session) {
        // 获取登录中的用户，如果不是管理员，就只查询这个用户的预约情况
        User user = (User) session.getAttribute("user");
        if(user.getUserRole()==1) {
            page.getParams().put("userId", user.getUserId());
        }
        Page<Appoint> p = appointService.getAppointList(page);
        model.addAttribute("page", p);
        return "/appoint/appointList";
    }


    /**
     * 根据id删除
     * @return
     */
    @RequestMapping("deleteAppoint{appointId}.html")
    public String deleteAppoint(@PathVariable String appointId, String[] appointIds) {
        if(appointIds==null) {
            appointService.deleteAppointById(appointId);
        }else {
            for (String id : appointIds) {
                appointService.deleteAppointById(id);
            }
        }
        return "redirect:/api/appoint/appointList.html";
    }

    /**
     * 查询练车是否结束
     * @param appoint
     * @return
     * @throws ParseException
     */
    @RequestMapping("checkTime.action")
    @ResponseBody
    public Result checkTime(Appoint appoint) throws ParseException {
        Appoint a = appointService.getAppointById(appoint.getAppointId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前时间
        String now = DateUtils.newDate("yyyy-MM-dd HH:mm:ss");
        // 比较在当前时间，这次预约是否已经过了结束时间
        if((sdf.parse(now)).compareTo((sdf.parse(a.getAppointEndDate())))<0) {
            // 没结束
            return Result.build(400, "本次练车还没结束！");
        }
        return Result.ok();
    }

}
