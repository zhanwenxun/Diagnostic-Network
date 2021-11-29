package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 医生类
 */

public class Doctor extends BmobObject {

    private int id;             // 医生ID
    private String name;        // 姓名：显示
    private int sex;            // 性别: 1为男，2为女
    private int old;            // 年龄
    private String job;         // 职称：显示
    private String type;        // 所属科室：显示
    private int hospitalid;     // 所属医院：显示
    private String account;     // 用户名
    private String password;    // 密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(int hospitalid) {
        this.hospitalid = hospitalid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
