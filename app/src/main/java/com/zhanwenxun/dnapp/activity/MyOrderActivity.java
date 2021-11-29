package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.adapter.OrderAdapter;
import com.zhanwenxun.dnapp.bean.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 用户“我的订单”的Activity
 */

public class MyOrderActivity extends BaseActivity {

    // 处理查询订单数据
    private ListView order_view;
    private ArrayList<Order> ordList = new ArrayList();
    private OrderAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bmob.initialize(this, "6eb8a41a8df46d76705a4bcbd7bbc346");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order);
        ButterKnife.bind(MyOrderActivity.this);

        // 数据初始化
        order_view = findViewById(R.id.order_view);

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
        return R.layout.my_order;
    }

    protected void getData() {
        // 查询订单数据
        BmobQuery<Order> query = new BmobQuery<>();
        int id = (int) BmobUser.getObjectByKey("id");
        query.addWhereEqualTo("userId", id);
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Order ord = list.get(i);
                        ordList.add(ord);

                        adapter = new OrderAdapter(MyOrderActivity.this, ordList);
                        order_view.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(MyOrderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
