package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.adapter.MessageAdapter;
import com.zhanwenxun.dnapp.bean.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 用户消息的Activity
 */

public class NotificationActivity extends BaseActivity {

    // 处理查询消息
    private ListView message_view;
    private ArrayList<Message> mesList = new ArrayList();
    private MessageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_notification);
        ButterKnife.bind(NotificationActivity.this);

        // 数据初始化
        message_view = findViewById(R.id.message_view);
        getData();

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
        return R.layout.user_notification;
    }

    private void getData() {
        // 查询用户的消息数据
        int userId = (int) BmobUser.getObjectByKey("id");
        BmobQuery<Message> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> list, BmobException e) {
                if(e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Message mes = list.get(i);
                        mesList.add(mes);

                        adapter = new MessageAdapter(NotificationActivity.this, mesList);
                        message_view.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(NotificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
