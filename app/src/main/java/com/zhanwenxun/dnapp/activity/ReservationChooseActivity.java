package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @explain: 选择预约时间后处理的Activity
 */

public class ReservationChooseActivity extends BaseActivity {

    @BindView(R.id.tv_loc)
    TextView tv_loc;
    @BindView(R.id.tv_hospital)
    TextView tv_hospital;
    @BindView(R.id.tv_department)
    TextView tv_department;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.reservation_submit)
    Button btn_reserve_submit;

    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(ReservationChooseActivity.this);

        // 设置数据
        intent = getIntent();
        tv_loc.setText(intent.getStringExtra("hospitalCity"));
        tv_hospital.setText(intent.getStringExtra("hospitalName"));
        tv_department.setText(intent.getStringExtra("department"));
        tv_date.setText(intent.getStringExtra("date"));

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
        return R.layout.activity_reservation;
    }

    // 点击预约按钮跳转至 reservation_confirm 界面
    @OnClick(R.id.reservation_submit)
    public void onClickReserveSubmit(View view) {
        intent.setClass(this, ReservationConfirmActivity.class);
        startActivity(intent);
    }
}
