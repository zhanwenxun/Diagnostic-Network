package com.zhanwenxun.dnapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * @explain: 疾病类
 */

public class Disease extends BmobObject {

    private int id;             // 疾病ID
    private int type;           // 类型
    private String keywords;    // 关键词
    private String name;        // 名称：显示
    private String description; // 简介：显示
    private String diseasetext; // 疾病详细介绍
    private String disease;     // 相关疾病
    private String symptom;     // 相关症状：显示
    private String drug;        // 相关药品
    private String cause;       // 相关病因：显示
    private String department;  // 相关科室：显示

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiseasetext() {
        return diseasetext;
    }

    public void setDiseasetext(String diseasetext) {
        this.diseasetext = diseasetext;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
