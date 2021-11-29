package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 用户反馈类
 */

public class Feedback extends BmobObject {

    private String username;

    private String feedback;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
