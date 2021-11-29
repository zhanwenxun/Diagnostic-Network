package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Feedback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 用户反馈的Activity
 */

public class FeedbackActivity extends BaseActivity{

    @BindView(R.id.et_feedback)
    EditText et_feedback;

    @BindView(R.id.feedback_submit)
    Button bt_feedback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(FeedbackActivity.this);

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
        return R.layout.activity_feedback;
    }

    // 处理用户反馈事件
    @OnClick(R.id.feedback_submit)
    public void onClickSubmit(View veiw) {

        String feedbacktext = et_feedback.getText().toString().trim();

        // 反馈如果为空，提示请重新填写
        if (feedbacktext.equals("")) {
            Toast.makeText(FeedbackActivity.this,"反馈为空，请重新填写！", Toast.LENGTH_SHORT).show();
        } else {

            String username = (String) BmobUser.getObjectByKey("username");

            Feedback feedback = new Feedback();
            feedback.setUsername(username);
            feedback.setFeedback(feedbacktext);
            feedback.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        Toast.makeText(FeedbackActivity.this, "反馈成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FeedbackActivity.this, "反馈失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
