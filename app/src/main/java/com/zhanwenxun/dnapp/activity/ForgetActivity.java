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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @explain: 忘记密码的Activity
 */

public class ForgetActivity extends BaseActivity {

    @BindView(R.id.forget_username)
    EditText forget_username;
    @BindView(R.id.forget_email)
    EditText forget_email;
    @BindView(R.id.reset_button)
    Button bt_reset;

    private String username;
    private String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        ButterKnife.bind(ForgetActivity.this);

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
        return R.layout.activity_forget;
    }

    // 处理重置密码事件
    @OnClick(R.id.reset_button)
    public void onClickReset(View view) {

        username = forget_username.getText().toString().trim();
        email = forget_email.getText().toString().trim();

        // 判断输入是否为空，为空则提示
        if (username.equals("")) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 判断输入是否为空，为空则提示
        if (email.equals("")) {
            Toast.makeText(this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 查询邮箱和用户名是否对应，并修改密码
        BmobQuery<User> bmobQuery = new BmobQuery<>();

        bmobQuery.addWhereEqualTo("username", username);
        bmobQuery.addWhereEqualTo("email", email);

        // 只查询ObjectId列
        bmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {

                // 如果查询有结果，则邮箱和用户名对应，否则不对应
                if (e == null) {
                    // 邮箱重置密码
                    User user = list.get(0);
                    String userEmail = user.getEmail();

                    BmobUser.resetPasswordByEmail(userEmail, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ForgetActivity.this, "重置密码请求成功，请到" + email + "邮箱进行密码重置操作", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgetActivity.this, "重置密码请求失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

                } else {
                    Toast.makeText(ForgetActivity.this, "邮箱和用户名不对应！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
