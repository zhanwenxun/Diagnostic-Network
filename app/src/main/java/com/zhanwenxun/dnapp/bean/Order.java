package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 订单类
 */

public class Order extends BmobObject {

    private int id;             // 订单ID
    private int userId;         // 购买用户ID
    private int medicineId;     // 购买药品ID
    private int num;            // 购买药品数量：显示
    private double price;       // 订单总金额：显示

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
