package com.car.mapper;

import com.car.pojo.Comment;
import com.car.pojo.Page;

import java.util.List;

public interface CommentMapper {

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
    List<Comment> getCommentList(Page<Comment> page);

    /**
     * 查询数量
     * @param page
     * @return
     */
    Integer getCommentCount(Page<Comment> page);

    /**
     * 根据id删除
     * @param commentId
     */
    void deleteCommentById(String commentId);

    /**
     * 根据id获取
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
