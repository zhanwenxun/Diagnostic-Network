package com.zhanwenxun.dnapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.activity.NavigationActivity;
import com.zhanwenxun.dnapp.activity.ReservationActivity;
import com.zhanwenxun.dnapp.adapter.HospitalAdapter;
import com.zhanwenxun.dnapp.bean.Hospital;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 医院模块的Fragment
 */

public class HospitalFragment extends Fragment {

    @BindView(R.id.hos_name)
    TextView hos_name;
    @BindView(R.id.hos_level)
    TextView hos_level;
    @BindView(R.id.hos_city)
    TextView hos_city;
    @BindView(R.id.hos_tel)
    TextView hos_tel;
    @BindView(R.id.hos_address)
    TextView hos_address;

    @BindView(R.id.image_navigation)
    ImageView image_navigation;
    @BindView(R.id.text_navigation)
    TextView text_navigation;
    @BindView(R.id.image_reservation)
    ImageView image_reservation;
    @BindView(R.id.text_reservation)
    TextView text_reservation;

    private Unbinder unbinder;

    // 处理查询医院
    private ListView hospital_view;
    private ArrayList<Hospital> hosList = new ArrayList();
    private HospitalAdapter adapter;
    private EditText search_hospital;

    private int hospital_id = 2;
    private String hospital_name = "九江市第三人民医院";
    private String hospital_city = "九江市";
    private double longtitude = 0.0;
    private double latitude = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);

        Bmob.initialize(getActivity(), "6eb8a41a8df46d76705a4bcbd7bbc346");

        // 数据初始化
        hospital_view = view.findViewById(R.id.hospital_view);
        search_hospital = view.findViewById(R.id.search_hospital);

        initData();

        // 给搜索框添加监听事件
        search_hospital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String name = s.toString();
                changeClient(name);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hospital_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Hospital hospital = hosList.get(position);

                hospital_id = hospital.getId();
                hospital_name = hospital.getName();
                hospital_city = hospital.getCity();
                longtitude = hospital.getLongtitude();
                latitude = hospital.getLatitude();

                hos_name.setText(hospital.getName());
                hos_level.setText(hospital.getLevel());
                hos_city.setText(hospital.getCity());
                hos_tel.setText(hospital.getTel());
                hos_address.setText(hospital.getAddress());

                ViewGroup.LayoutParams params = hospital_view.getLayoutParams();
                params.height = 0;
            }
        });

        // 返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        // 查询数据
        BmobQuery<Hospital> query = new BmobQuery<>();
        query.findObjects(new FindListener<Hospital>() {
            @Override
            public void done(List<Hospital> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Hospital hos = list.get(i);
                        hosList.add(hos);

                        adapter = new HospitalAdapter(getActivity(), hosList);
                        hospital_view.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void changeClient(String name) {
        // 根据输入条件筛选医院数据
        hosList.clear();
        BmobQuery<Hospital> query1 = new BmobQuery<>();
        if (!name.equals("")) {
            query1.addWhereEqualTo("keywords", name);
        }
        query1.findObjects(new FindListener<Hospital>() {
            @Override
            public void done(List<Hospital> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Hospital dis = list.get(i);
                        hosList.add(dis);

                        adapter = new HospitalAdapter(getActivity(), hosList);
                        hospital_view.setAdapter(adapter);
                        setListViewHeight(hospital_view);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    // ListView自适应变化高度
    private void setListViewHeight(ListView listView) {
        if (adapter == null) {
            return;
        }

        View itemView = adapter.getView(0, null, listView); // 获取其中一项
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight(); // 获取其中一项的高度
        int itemCount = adapter.getCount(); // 获取总得项数
        int height = itemHeight * itemCount;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height;
    }

    // 点击预约挂号跳转至 reservation_table 界面
    @OnClick({R.id.image_reservation, R.id.text_reservation})
    public void onClickReservation(View view) {
        Intent intent = new Intent();
        intent.putExtra("hospitalId", hospital_id);
        intent.putExtra("hospitalName", hospital_name);
        intent.putExtra("hospitalCity", hospital_city);
        intent.setClass(getActivity(), ReservationActivity.class);
        startActivity(intent);
    }

    // 点击就医导航跳转至 activity_hospital_navigation 界面
    @OnClick({R.id.image_navigation, R.id.text_navigation})
    public void onClickNavigation(View view) {
        if (BmobUser.isLogin()) {
            Intent intent = new Intent();
            intent.putExtra("longtitude", longtitude);
            intent.putExtra("latitude", latitude);
            intent.setClass(getActivity(), NavigationActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "您还未登录！", Toast.LENGTH_SHORT).show();
        }

    }

    // 退出时解除绑定
    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
