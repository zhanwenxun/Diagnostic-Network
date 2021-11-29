package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 确认预约的Activity
 */

public class ReservationConfirmActivity extends BaseActivity {

    @BindView(R.id.rc_hospital)
    TextView rc_hospital;
    @BindView(R.id.rc_department)
    TextView rc_department;
    @BindView(R.id.rc_date)
    TextView rc_date;
    @BindView(R.id.rc_user)
    TextView rc_user;
    @BindView(R.id.rc_sex)
    TextView rc_sex;
    @BindView(R.id.rc_phone)
    TextView rc_phone;

    Date time;

    @BindView(R.id.reservation_confirm)
    Button btn_reserve_confirm;

    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_confirm);
        ButterKnife.bind(ReservationConfirmActivity.this);

        intent = getIntent();
        rc_hospital.setText(intent.getStringExtra("hospitalName"));
        rc_department.setText(intent.getStringExtra("department"));
        rc_date.setText(intent.getStringExtra("date"));

        // 从当前用户获取个人信息
        rc_user.setText((String) BmobUser.getObjectByKey("name"));
        rc_phone.setText((String) BmobUser.getObjectByKey("mobilePhoneNumber"));

        if ((int) BmobUser.getObjectByKey("sex") == 1) {
            rc_sex.setText("男");
        } else {
            rc_sex.setText("女");
        }

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
        return R.layout.reservation_confirm;
    }

    // 点击确认预约按钮跳转至 reservation_successs 界面
    @OnClick(R.id.reservation_confirm)
    public void onClickReserveConfirm(View view) {
        // 设置预约数据
        Reservation reservation = new Reservation();
        reservation.setUserId((int) BmobUser.getObjectByKey("id"));
        reservation.setUserName((String) BmobUser.getObjectByKey("name"));
        reservation.setCity(intent.getStringExtra("hospitalCity"));
        reservation.setHospitalId(intent.getIntExtra("hospitalId", 2));
        reservation.setHospitalName(intent.getStringExtra("hospitalName"));
        reservation.setDepartment(intent.getStringExtra("department"));

        // 将String转换成Date类型
        int day = intent.getIntExtra("days", 1);
        int month = intent.getIntExtra("months", 1);
        int year = intent.getIntExtra("years", 2020);
        String date = year + "-" + month + "-" + day;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
                time = simpleDateFormat.parse(date); }
        catch (Exception e) {
        }
        BmobDate bmobDate = new BmobDate(time);
        reservation.setDate(bmobDate);

        // 添加预约信息
        reservation.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(ReservationConfirmActivity.this, "预约成功！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReservationConfirmActivity.this, "预约失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        intent.setClass(this, ReservationSuccessActivity.class);
        startActivity(intent);
    }
}
