package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @explain: 自主诊断的Activity
 */

public class SelfDiagnosisActivity extends BaseActivity {

    @BindView(R.id.choose_sex)
    Spinner choose_sex;
    @BindView(R.id.choose_age)
    Spinner choose_age;

    @BindView(R.id.fever)
    CheckBox cb_fever;
    @BindView(R.id.tired)
    CheckBox cb_tired;
    @BindView(R.id.cough)
    CheckBox cb_cough;
    @BindView(R.id.diffbreath)
    CheckBox cb_diffbreath;
    @BindView(R.id.sorethroat)
    CheckBox cb_sorethroat;

    @BindView(R.id.diagnosis_bt)
    Button diagnosis;

    // 将以下数据设置为double类型，方便之后做归一化处理以及矩阵相乘
    double sex = 0.0;
    double age = 0.0;
    String sexx;
    String agee;

    double fever = 0.0;
    double tired = 0.0;
    double cough = 0.0;
    double diffbreath = 0.0;
    double sorethroat = 0.0;

    // 患病概率
    double y;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_diagnosis);

        ButterKnife.bind(SelfDiagnosisActivity.this);

        // 点击标题栏的返回按钮结束该Activity
        Toolbar toolbar = findViewById(R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.self_diagnosis;
    }

    @OnClick(R.id.diagnosis_bt)
    public void onClickDiagnosis(View view) {
        // 获取性别
        sexx = choose_sex.getSelectedItem().toString();
        if (sexx.equals("选择性别▼")) {
            Toast.makeText(this, "未选择性别！", Toast.LENGTH_SHORT).show();
            return;
        } else if (sexx.equals("男")) {
            sex = 1;
        } else {
            sex = 2;
        }

        // 获取年龄
        agee = choose_age.getSelectedItem().toString();
        if(agee.equals("选择年龄▼")) {
            Toast.makeText(this, "未选择年龄！", Toast.LENGTH_SHORT).show();
            return;
        } else if (agee.equals("0-9")) {
            age = 5;
        } else if (agee.equals("10-19")) {
            age = 15;
        } else if (agee.equals("20-24")) {
            age = 22;
        } else if (agee.equals("25-59")) {
            age = 42;
        } else {
            age = 60;
        }

        // 获取症状信息
        if (cb_fever.isChecked()) {
            fever = 1;
        }
        if (cb_tired.isChecked()) {
            tired = 1;
        }
        if (cb_cough.isChecked()) {
            cough = 1;
        }
        if (cb_diffbreath.isChecked()) {
            diffbreath = 1;
        }
        if (cb_sorethroat.isChecked()) {
            sorethroat = 1;
        }

        Toast.makeText(this, "性别：" + sexx + " 年龄：" + agee
                +"\n发烧：" + fever + " 身体疲惫：" + tired
                +"\n干咳：" + cough + " 呼吸困难：" + diffbreath
                +"\n喉咙痛：" + sorethroat, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();

        if (isInfected(sex, age, fever, tired, cough, diffbreath, sorethroat) == true) {
            intent.putExtra("y", y);
            intent.setClass(this, InfectedActivity.class);
            startActivity(intent);
        } else {
            intent.putExtra("y", y);
            intent.setClass(this, NotInfectedActivity.class);
            startActivity(intent);
        }
    }

    public boolean isInfected(double sex, double age, double fever, double tired, double cough, double diffbreath, double sorethroat) {

        // 对数据做归一化处理
        sex = (sex-1) / (2-1);
        age = (age-5) / (60-5);
        fever = (fever-0) / (1-0);
        tired = (tired-0) / (1-0);
        cough = (cough-0) / (1-0);
        diffbreath = (diffbreath-0) / (1-0);
        sorethroat = (sorethroat-0) / (1-0);

        // 根据训练好的模型参数计算患病概率 总权重为2.5460394
//        y = 0.6257912*fever + 0.31883773*tired + 0.08828715*cough
//                + 0.36640504*diffbreath + 0.39356348*sorethroat
//                + 0.3154865*age + 0.4376683*sex - 0.063466;

        y = 0.24579*fever + 0.12523*tired + 0.03468*cough
                + 0.14391*diffbreath + 0.15458*sorethroat
                + 0.12391*age + 0.1719*sex;

        if (y > 0.5) {
            return true;
        } else {
            return false;
        }
    }
}
