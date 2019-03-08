package com.car.controller;

import com.car.pojo.Comment;
import com.car.pojo.Page;
import com.car.pojo.Teacher;
import com.car.pojo.User;
import com.car.service.CommentService;
import com.car.service.TeacherService;
import com.car.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/comment/")
public class CommentController{

    @Autowired
    private CommentService commentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 根据教师id查询教师，跳转到评论页面
     * @param teacherId
     * @param model
     * @return
     */
    @RequestMapping("toAddComment/{teacherId}.html")
    public String toAddComment(@PathVariable String teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "/comment/addComment";
    }

    /**
     * 发表评论
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping("addComment.html")
    public String addComment(Comment comment, HttpSession session) {
        // 获取登录用户
        User user = (User) session.getAttribute("user");
        comment.setUser(user);
        commentService.addComment(comment);
        return "redirect:/api/comment/commentList.html";
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "commentList.html")
    public String commentList(Page<Comment> page, Model model, HttpSession session, String teacherId) {
        if(StringUtils.isNoneBlank(teacherId)) {
            page.getParams().put("teacherId", teacherId);
        }
        Page<Comment> p = commentService.getCommentList(page);
        model.addAttribute("page", p);
        return "/comment/commentList";
    }


    /**
     * 根据id删除
     * @return
     */
    @RequestMapping("deleteComment{commentId}.html")
    public String deleteComment(@PathVariable String commentId, String[] commentIds) {
        if(commentIds==null) {
            commentService.deleteCommentById(commentId);
        }else {
            for (String id : commentIds) {
                commentService.deleteCommentById(id);
            }
        }
        return "redirect:/api/comment/commentList.html";
    }

    /**
     * 根据id获取，跳转到修改
     */
    @RequestMapping("toEditComment/{commentId}.html")
    public String toEdit(@PathVariable String commentId, Model model) {
        Comment comment = commentService.getById(commentId);
        model.addAttribute("comment", comment);
        return "/comment/editComment";
    }

    /**
     * 修改
     */
    @RequestMapping("editComment.html")
    public String editComment(Comment comment) {
        commentService.updateCommentArticle(comment);
        return "redirect:/api/comment/commentList.html";
    }

    /**
     * 根据id查询评论，返回json
     */
    @RequestMapping("readComment.action")
    @ResponseBody
    public Result readComment(Comment comment) {
        Comment c = commentService.getById(comment.getCommentId());
        if(c==null || StringUtils.isBlank(c.getCommentArticle())) {
            return Result.build(400, "评论为空");
        }
        return Result.build(200, c.getCommentArticle());
    }

}
