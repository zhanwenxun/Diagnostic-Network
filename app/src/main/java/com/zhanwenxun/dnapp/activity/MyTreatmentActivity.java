package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.adapter.TreatmentAdapter;
import com.zhanwenxun.dnapp.bean.Treatment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 用户“我的就诊”的Activity
 */

public class MyTreatmentActivity extends BaseActivity {

    // 处理查询就诊数据
    private ListView treatment_view;
    private ArrayList<Treatment> treList = new ArrayList();
    private TreatmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bmob.initialize(this, "6eb8a41a8df46d76705a4bcbd7bbc346");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_treatment);
        ButterKnife.bind(MyTreatmentActivity.this);

        // 数据初始化
        treatment_view = findViewById(R.id.treatment_view);

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
        return R.layout.my_treatment;
    }

    protected void getData() {
        // 查询预约数据
        BmobQuery<Treatment> query = new BmobQuery<>();
        int id = (int) BmobUser.getObjectByKey("id");
        query.addWhereEqualTo("userId", id);
        query.findObjects(new FindListener<Treatment>() {
            @Override
            public void done(List<Treatment> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Treatment res = list.get(i);
                        treList.add(res);

                        adapter = new TreatmentAdapter(MyTreatmentActivity.this, treList);
                        treatment_view.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(MyTreatmentActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
