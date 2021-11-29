package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import java.text.DecimalFormat;

import butterknife.ButterKnife;

/**
 * @explain: 诊断结果为没有感染的Activity
 */

public class NotInfectedActivity extends BaseActivity {

    DecimalFormat df = new DecimalFormat("######0.00");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_not_infected);

        ButterKnife.bind(NotInfectedActivity.this);

        Intent intent = getIntent();
        double y = intent.getDoubleExtra("y", -1);
        y = y*100;
        Toast.makeText(this, "您的患病概率为：" + df.format(y) + "%", Toast.LENGTH_SHORT).show();

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
        return R.layout.diagnosis_not_infected;
    }
}
