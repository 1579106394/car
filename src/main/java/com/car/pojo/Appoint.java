package com.car.pojo;

import java.io.Serializable;

public class Appoint implements Serializable {

    private String appointId;

    private User user;

    private Teacher teacher;

    private String appointStartDate;

    private String appointEndDate;

    @Override
    public String toString() {
        return "Appoint{" +
                "appointId='" + appointId + '\'' +
                ", user=" + user +
                ", teacher=" + teacher +
                ", appointStartDate='" + appointStartDate + '\'' +
                ", appointEndDate='" + appointEndDate + '\'' +
                '}';
    }

    public String getAppointId() {
        return appointId;
    }

    public void setAppointId(String appointId) {
        this.appointId = appointId;
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

    public String getAppointStartDate() {
        return appointStartDate;
    }

    public void setAppointStartDate(String appointStartDate) {
        this.appointStartDate = appointStartDate;
    }

    public String getAppointEndDate() {
        return appointEndDate;
    }

    public void setAppointEndDate(String appointEndDate) {
        this.appointEndDate = appointEndDate;
    }
}
