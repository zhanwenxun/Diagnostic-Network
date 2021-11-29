package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.MainActivity;
import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

/**
 * @explain: 预约成功的Activity
 */

public class ReservationSuccessActivity extends BaseActivity {

    @BindView(R.id.rs_hospital)
    TextView rs_hospital;
    @BindView(R.id.rs_date)
    TextView rs_date;
    @BindView(R.id.rs_user)
    TextView rs_user;

    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_success);
        ButterKnife.bind(ReservationSuccessActivity.this);

        // 设置数据
        intent = getIntent();
        String hospital = intent.getStringExtra("hospitalName") + "  " + intent.getStringExtra("department");
        rs_hospital.setText(hospital);
        String date = intent.getStringExtra("date");
        rs_date.setText(date);
        String user = (String) BmobUser.getObjectByKey("name");
        if ((int) BmobUser.getObjectByKey("sex") == 1) {
            user = user + "  " + "男   " + BmobUser.getObjectByKey("age");
        } else {
            user = user + "  " + "女   " + BmobUser.getObjectByKey("age");
        }
        rs_user.setText(user);

        // 点击标题栏的返回按钮结束该Activity
        Toolbar toolbar = findViewById(R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ReservationSuccessActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.reservation_success;
    }
}
