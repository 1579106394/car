package com.car.pojo;

import java.io.Serializable;

public class Comment implements Serializable {

    private String commentId;

    private String commentArticle;

    private User user;

    private Teacher teacher;

    private String commentTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentArticle() {
        return commentArticle;
    }

    public void setCommentArticle(String commentArticle) {
        this.commentArticle = commentArticle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
