package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobUser;

/**
 * @explain: 用户类
 */

public class User extends BmobUser {

    private int id;                     // 用户ID
//    private String username;            // 用户名
//    private String password;            // 密码
    private String name;                // 姓名
    private int sex;                    // 性别: 1为男，2为女
    private int age;                    // 年龄
    private String birth;               // 出生日期
//    private String mobilePhoneNumber;   // 电话号码
//    private String email;               // 邮箱
    private String address;             // 地址

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

    public int getAge() {
        return age;
    }

    public void setAge(int old) {
        this.age = old;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
