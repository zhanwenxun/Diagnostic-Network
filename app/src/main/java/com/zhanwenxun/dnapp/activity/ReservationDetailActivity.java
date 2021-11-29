package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.ButterKnife;

public class ReservationDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_detail);
        ButterKnife.bind(ReservationDetailActivity.this);

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
        return R.layout.reservation_detail;
    }
}
