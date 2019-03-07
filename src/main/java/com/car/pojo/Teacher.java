package com.car.pojo;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String teacherId;

    private String teacherNumber;

    private String teacherName;

    private Integer teacherAge;

    private String teacherTelephone;

    private String teacherPhoto;

    private Car car;

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getTeacherTelephone() {
        return teacherTelephone;
    }

    public void setTeacherTelephone(String teacherTelephone) {
        this.teacherTelephone = teacherTelephone;
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public void setTeacherPhoto(String teacherPhoto) {
        this.teacherPhoto = teacherPhoto;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
