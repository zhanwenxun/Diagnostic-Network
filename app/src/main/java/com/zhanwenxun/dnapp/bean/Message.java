package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 用户消息类
 */

public class Message extends BmobObject {

    private int userId;         // 患者用户ID
    private int doctorId;       // 医生ID
    private String doctorName;  // 医生姓名：显示
    private String contactText; // 消息内容：显示

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getContactText() {
        return contactText;
    }

    public void setContactText(String contactText) {
        this.contactText = contactText;
    }
}
