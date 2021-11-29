package com.zhanwenxun.dnapp.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanwenxun.dnapp.MainActivity;
import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 登录的Activity
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_username)
    EditText login_username;
    @BindView(R.id.login_pass)
    EditText login_pass;
    @BindView(R.id.forget_pass)
    TextView forgetpass;
    @BindView(R.id.login_bt)
    Button login_bt;
    @BindView(R.id.regist_bt)
    Button regist_bt;

    private String username;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);

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
        return R.layout.activity_login;
    }

    // 点击忘记密码跳转至 activity_forget 界面
    @OnClick(R.id.forget_pass)
    public void onClickForget(View view) {
        startActivity(new Intent(this, ForgetActivity.class));
    }

    // 点击注册跳转至 activity_register 界面
    @OnClick(R.id.regist_bt)
    public void onClickRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    // 处理用户登录事件
    @OnClick(R.id.login_bt)
    public void onClickLogin(View view) {
        username = login_username.getText().toString().trim();
        password = login_pass.getText().toString().trim();

        if (username.equals("")) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        signIn(view);
    }

    // 登录事件
    private void signIn(final View view) {
        final User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Toast.makeText(LoginActivity.this, "登录成功：" + user.getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
