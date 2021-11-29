package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.adapter.ReservationAdapter;
import com.zhanwenxun.dnapp.bean.Reservation;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @explain: 用户“我的预约“的Activity
 */

public class MyReservationActivity extends BaseActivity
        implements AdapterView.OnItemClickListener, ReservationAdapter.Callback {

    // 处理查询预约数据
    private ListView reservation_view;
    private ArrayList<Reservation> resList = new ArrayList();
    private ReservationAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bmob.initialize(this, "6eb8a41a8df46d76705a4bcbd7bbc346");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservation);
        ButterKnife.bind(MyReservationActivity.this);

        // 数据初始化
        reservation_view = findViewById(R.id.reseravtion_view);

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
        return R.layout.my_reservation;
    }

    protected void getData() {
        // 查询预约数据
        BmobQuery<Reservation> query = new BmobQuery<>();
        int id = (int) BmobUser.getObjectByKey("id");
        query.addWhereEqualTo("userId", id);
        query.findObjects(new FindListener<Reservation>() {
            @Override
            public void done(List<Reservation> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Reservation res = list.get(i);
                        resList.add(res);

                        reservation_view.setAdapter(new ReservationAdapter(MyReservationActivity.this, resList, MyReservationActivity.this));
                        reservation_view.setOnItemClickListener(MyReservationActivity.this);
                    }
                } else {
                    Toast.makeText(MyReservationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // 响应ListView中item的点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
        Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position, Toast.LENGTH_SHORT).show();
    }

    // 接口方法，响应ListView按钮点击事件
    @Override
    public void click(View view) {

        String ObjectId = resList.get((Integer) view.getTag()).getObjectId();
        Reservation reservation = new Reservation();
        reservation.setObjectId(ObjectId);
        reservation.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(MyReservationActivity.this, "退号成功", Toast.LENGTH_SHORT).show();
                    resList.clear();
                    getData();
                } else {
                    Toast.makeText(MyReservationActivity.this, "退号失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
