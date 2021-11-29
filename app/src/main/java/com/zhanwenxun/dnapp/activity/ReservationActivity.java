package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @explain: 选择预约时间的Activity
 */

public class ReservationActivity extends BaseActivity {

    @BindView(R.id.hospital_name)
    TextView tv_hospitalName;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.calendar)
    CalendarView calendar;

    @BindView(R.id.choose_reservation)
    Button choose_reservation;

    private int hospitalId;
    private String hospitalName;
    private String department;

    // 构件日期
    private int years = 0;
    private int months = 0;
    private int days = 0;
    private String date;

    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_table);
        ButterKnife.bind(ReservationActivity.this);

        // 设置数据
        intent = getIntent();
        hospitalId = intent.getIntExtra("hospitalId", 2);
        hospitalName = intent.getStringExtra("hospitalName");

        // 从Spinner和Calendar获取数据
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //显示用户选择的日期
                years = year;
                month++;
                months = month;
                days = dayOfMonth;
                date = year + "年" + month + "月" + dayOfMonth + "日";
            }
        });

        // 数据初始化
        tv_hospitalName.setText(hospitalName);

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
        return R.layout.reservation_table;
    }

    @OnClick(R.id.choose_reservation)
    public void onClickChooseReservation(View view) {
        department = spinner.getSelectedItem().toString();
        // 若用户未选择科室
        if (department.equals("选择科室▼")) {
            Toast.makeText(this, "未选择预约科室！", Toast.LENGTH_SHORT).show();
        }
        // 若用户未选择日期
        else if (years == 0 || months ==0 || days == 0 ) {
                    Toast.makeText(this, "未选择预约日期！", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("department", department);
                    intent.putExtra("years", years);
                    intent.putExtra("months", months);
                    intent.putExtra("days", days);
                    intent.putExtra("date", date);
                    intent.setClass(this, ReservationChooseActivity.class);
                    startActivity(intent);
        }
    }
}
