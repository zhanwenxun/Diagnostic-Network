package com.zhanwenxun.dnapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhanwenxun.dnapp.bean.User;
import com.zhanwenxun.dnapp.fragment.DiagnosisFragment;
import com.zhanwenxun.dnapp.fragment.DoctorFragment;
import com.zhanwenxun.dnapp.fragment.HospitalFragment;
import com.zhanwenxun.dnapp.fragment.MedicineFragment;
import com.zhanwenxun.dnapp.fragment.MyFragment;
import com.zhanwenxun.dnapp.util.ChangeFragment;
import com.zhanwenxun.dnapp.util.GlobalParms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * @explain: 项目主Activity
 */

public class MainActivity extends AppCompatActivity {

    // 定义Bmob云数据库应用ID
    private static final String Application_ID = "6eb8a41a8df46d76705a4bcbd7bbc346";

    // 定义五个模块对应的Fragment
    private DiagnosisFragment diagnosisFragment;
    private DoctorFragment doctorFragment;
    private HospitalFragment hospitalFragment;
    private MedicineFragment medicineFragment;
    private MyFragment myFragment;

    private int role; // 1为用户，2为医生

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //该方法注意要在setContentView之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        // Bmob初始化
        Bmob.initialize(this, "6eb8a41a8df46d76705a4bcbd7bbc346");
        init();

        ButterKnife.bind(MainActivity.this);

        // 设置底部导航栏监听
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // 初始化
    private void init() {

        diagnosisFragment = new DiagnosisFragment();
        hospitalFragment = new HospitalFragment();
        doctorFragment = new DoctorFragment();
        medicineFragment = new MedicineFragment();
        myFragment = new MyFragment();

        GlobalParms.setFragmentSelected(new ChangeFragment() {
            @Override
            public void changeFragment(int position) {
                BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
                navigationView.setSelectedItemId(R.id.navigation_medicine);
                showNav(position);
            }
        });

        // 查看用户登录状态
        if (BmobUser.isLogin()) {
            role = (int) BmobUser.getObjectByKey("role");
            User user = BmobUser.getCurrentUser(User.class);
            if (role == 1) {
                Toast.makeText(this, "用户好，您已经登录：" + user.getUsername(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "医生好，您已经登录：" + user.getUsername(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "您还未登录" , Toast.LENGTH_LONG).show();
        }

        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        // 开启一个事件将fragment动态加载到组件
        beginTransaction.add(R.id.content, diagnosisFragment)
                .add(R.id.content, hospitalFragment)
                .add(R.id.content, doctorFragment)
                .add(R.id.content, medicineFragment)
                .add(R.id.content, myFragment);
        // 隐藏fragment
        beginTransaction.hide(diagnosisFragment).hide(doctorFragment).
                hide(hospitalFragment).hide(medicineFragment).hide(myFragment);
        // 返回到上一个显示的fragment
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();

        showNav(R.id.navigation_diagnosis);
    }

    // 底部导航栏监听方法
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_diagnosis:
                    showNav(R.id.navigation_diagnosis);
                    return true;

                case R.id.navigation_hospital:
                    showNav(R.id.navigation_hospital);
                    return true;

                case R.id.navigation_doctor:
                    showNav(R.id.navigation_doctor);
                    return true;

                case R.id.navigation_medicine:
                    showNav(R.id.navigation_medicine);
                    return true;

                case R.id.navigation_my:
                    showNav(R.id.navigation_my);
                    return true;
            }
            return false;
        }
    };

    // 实现点击底部导航栏切换页面
    private void showNav(int navid) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        switch (navid) {
            // 诊断模块
            case R.id.navigation_diagnosis:
                beginTransaction.hide(doctorFragment).hide(hospitalFragment)
                        .hide(medicineFragment).hide(myFragment);
                beginTransaction.show(diagnosisFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            // 医院模块
            case R.id.navigation_hospital:
                beginTransaction.hide(diagnosisFragment).hide(doctorFragment)
                        .hide(medicineFragment).hide(myFragment);
                beginTransaction.show(hospitalFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            // 医生模块
            case R.id.navigation_doctor:
                beginTransaction.hide(diagnosisFragment).hide(hospitalFragment)
                        .hide(medicineFragment).hide(myFragment);
                beginTransaction.show(doctorFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            // 药品模块
            case R.id.navigation_medicine:
                beginTransaction.hide(diagnosisFragment).hide(doctorFragment)
                        .hide(hospitalFragment).hide(myFragment);
                beginTransaction.show(medicineFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            // 个人模块
            case R.id.navigation_my:
                beginTransaction.hide(diagnosisFragment).hide(doctorFragment)
                        .hide(hospitalFragment).hide(medicineFragment);
                beginTransaction.show(myFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }
}
