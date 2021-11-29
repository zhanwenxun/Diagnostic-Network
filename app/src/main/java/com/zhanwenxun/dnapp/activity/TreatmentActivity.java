package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 显示医生就诊记录的Activity
 */

public class TreatmentActivity extends BaseActivity {

    // 处理查询医生诊断数据
    private ListView doc_treatment_view;
    private ArrayList<Treatment> doc_treList = new ArrayList();
    private TreatmentAdapter adapter;

    private int doctorId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bmob.initialize(this, "6eb8a41a8df46d76705a4bcbd7bbc346");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_treatment);
        ButterKnife.bind(TreatmentActivity.this);

        // 数据初始化
        doc_treatment_view = findViewById(R.id.doctor_treatment_view);
        Intent intent = getIntent();
        doctorId = intent.getIntExtra("doctorId", 0);

        getData();

        // 点击标题栏的返回按钮结束该Activity
        Toolbar toolbar = findViewById(R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        // 给病人ListView添加点击监听事件
        doc_treatment_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Treatment treatment = doc_treList.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("userId", treatment.getUserId());
                bundle.putString("userName", treatment.getUserName());
                bundle.putInt("doctorId", treatment.getDoctorId());
                bundle.putString("doctorName", treatment.getDoctorName());

                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(TreatmentActivity.this, PatientContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.doctor_treatment;
    }

    protected void getData() {
        // 查询预约数据
        BmobQuery<Treatment> query = new BmobQuery<>();
        query.addWhereEqualTo("doctorId", doctorId);
        query.findObjects(new FindListener<Treatment>() {
            @Override
            public void done(List<Treatment> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Treatment res = list.get(i);
                        doc_treList.add(res);

                        adapter = new TreatmentAdapter(TreatmentActivity.this, doc_treList);
                        doc_treatment_view.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(TreatmentActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
