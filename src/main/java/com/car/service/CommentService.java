package com.car.service;

import com.car.pojo.Comment;
import com.car.pojo.Page;

public interface CommentService {

    /**
     * 发表评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 分页查询评论
     * @param page
     * @return
     */
    Page<Comment> getCommentList(Page<Comment> page);

    /**
     * 根据id删除
     * @param commentId
     */
    void deleteCommentById(String commentId);

    /**
     * 根据id查询
     * @param commentId
     * @return
     */
    Comment getById(String commentId);

    /**
     * 修改评论内容
     * @param comment
     */
    void updateCommentArticle(Comment comment);
}
