package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

/**
 * @explain: 用户个人信息的Activity
 */

public class UserInfoActivity extends BaseActivity {

    TextView tv_username;
    TextView tv_sex;
    TextView tv_name;
    TextView tv_age;
    TextView tv_email;
    TextView tv_birth;
    TextView tv_phone;
    TextView tv_address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(UserInfoActivity.this);

        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_birth = (TextView) findViewById(R.id.tv_birth);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);

        if (BmobUser.isLogin()) {
            String username = (String) BmobUser.getObjectByKey("username");
            tv_username.setText(username);

            int sex = (int) BmobUser.getObjectByKey("sex");
            if (sex == 1) {
                tv_sex.setText("男");
            } else {
                tv_sex.setText("女");
            }

            String name = (String) BmobUser.getObjectByKey("name");
            tv_name.setText(name);

            int age = (int) BmobUser.getObjectByKey("age");
            tv_age.setText(age + "");

            String email = (String) BmobUser.getObjectByKey("email");
            tv_email.setText(email);

            String birth = (String) BmobUser.getObjectByKey("birth");
            tv_birth.setText(birth);

            String phone = (String) BmobUser.getObjectByKey("mobilePhoneNumber");
            tv_phone.setText(phone);

            String address = (String) BmobUser.getObjectByKey("address");
            tv_address.setText(address);
        } else {
            Toast.makeText(UserInfoActivity.this, "请先登录！", Toast.LENGTH_SHORT);
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
        return R.layout.activity_userinfo;
    }
}
