package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 药品类
 */

public class Medicine extends BmobObject {

    private int id;                 // 药品ID
    private String type;            // 药品类型：显示
    private String name;            // 药品名称：显示
    private double price;           // 药品价格：显示
    private int num;                // 药品数量：显示
    private String createDate;      // 生产日期
    private String function;        // 功效
    private String disease;         // 适应症状
    private String taboo;           // 禁忌
    private String format;          // 规格：显示
    private String dosage;          // 用法用量
    private String expiredate;      // 有效期
    private String producter;       // 生产企业：显示

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineType() {
        return type;
    }

    public void setMedicineType(String medicineType) {
        this.type = medicineType;
    }

    public String getMedicineName() {
        return name;
    }

    public void setMedicineName(String medicineName) {
        this.name = medicineName;
    }

    public double getMedicinePrice() {
        return price;
    }

    public void setMedicinePrice(double medicinePrice) {
        this.price = medicinePrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getProducter() {
        return producter;
    }

    public void setProducter(String producter) {
        this.producter = producter;
    }
}
