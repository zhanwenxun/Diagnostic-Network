package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.User;
import com.zhanwenxun.dnapp.util.RegularUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 注册的Activity
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_pass1)
    EditText registerPass1;
    @BindView(R.id.register_pass2)
    EditText registerPass2;
    @BindView(R.id.register_email)
    EditText registerEmail;
    @BindView(R.id.register_bt)
    Button registerBt;

    private String username;
    private String password;
    private String password2;
    private String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(RegisterActivity.this);

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
        return R.layout.activity_register;
    }

    // 处理用户注册事件
    @OnClick(R.id.register_bt)
    public void onClickRegister(View view) {

        username = registerName.getText().toString().trim();
        password = registerPass1.getText().toString().trim();
        password2 = registerPass2.getText().toString().trim();
        email = registerEmail.getText().toString().trim();

        if (!RegularUtils.isUsername(username)) {
            Toast.makeText(this, "用户名不符合规范", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!RegularUtils.isPassWord(password)) {
            Toast.makeText(this, "密码不符合规范", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password2.equals(password)) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!RegularUtils.isEmail(email)) {
            Toast.makeText(this, "邮箱不符合规范", Toast.LENGTH_SHORT).show();
            return;
        }

        signUp(view);
    }

    // 注册事件
    private void signUp(final View view) {
        final User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
