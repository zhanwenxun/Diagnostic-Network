package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @explain: 修改密码的Activity
 */

public class ResetpwdActivity extends BaseActivity {

    @BindView(R.id.change_pass1)
    EditText et_change_pass1;
    @BindView(R.id.change_pass2)
    EditText et_change_pass2;
    @BindView(R.id.change_pass3)
    EditText et_change_pass3;
    @BindView(R.id.bt_resetpwd)
    Button bt_resetpassword;

    private String password1;
    private String password2;
    private String password3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpwd);
        ButterKnife.bind(ResetpwdActivity.this);

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
        return R.layout.activity_resetpwd;
    }

    // 点击确认修改密码处理重置密码事件
    @OnClick(R.id.bt_resetpwd)
    public void onClickResetPwd(View view){

        if (BmobUser.isLogin()) {

            // 获取三个输入框的数据
            password1 = et_change_pass1.getText().toString().trim();
            password2 = et_change_pass2.getText().toString().trim();
            password3 = et_change_pass3.getText().toString().trim();

            // 判断两次密码输入是否一致
            if (!password2.equals(password3)) {
                Toast.makeText(ResetpwdActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT);
                return;
            }

            BmobUser.updateCurrentUserPassword(password1, password2, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(ResetpwdActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ResetpwdActivity.this, "修改密码失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(ResetpwdActivity.this, "请先登录！", Toast.LENGTH_SHORT);
        }
    }


}
