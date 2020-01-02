package com.sun.zq.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private Integer age;

    private String mobile;

    private Date crtTime;

    private Date uptTime;

    public User(Integer id, String name, Integer age, String mobile, Date crtTime, Date uptTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.crtTime = crtTime;
        this.uptTime = uptTime;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }
}