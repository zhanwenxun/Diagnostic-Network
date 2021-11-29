package com.zhanwenxun.dnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.zhanwenxun.dnapp.R;

import butterknife.ButterKnife;

/**
 * @explain: 医院导航的Activity
 */

public class NavigationActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_navigation);

        MapView mapView = (MapView) findViewById(R.id.map);
        BaiduMap mBaidumap = mapView.getMap();

        // 从Intent中获取经纬度
        Intent intent = getIntent();
        double longtitude = intent.getDoubleExtra("longtitude", 29.70511);
        double latitude = intent.getDoubleExtra("latitude", 115.992375);

        LatLng cenpt = new LatLng(latitude,longtitude);
        // 定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);

//        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
//        mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        ButterKnife.bind(NavigationActivity.this);

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
        return R.layout.activity_hospital_navigation;
    }
}
