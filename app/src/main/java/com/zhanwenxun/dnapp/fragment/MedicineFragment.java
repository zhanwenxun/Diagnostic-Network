package com.zhanwenxun.dnapp.fragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.adapter.MedicineAdapter;
import com.zhanwenxun.dnapp.bean.Medicine;
import com.zhanwenxun.dnapp.bean.Order;

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
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 药品模块的Fragment
 */

public class MedicineFragment extends Fragment
        implements AdapterView.OnItemClickListener {

    @BindView(R.id.bt_total_num)
    Button bt_num;

    // 初始化总价和总数量
    private double total_price = 0.0;
    private int total_num = 0;
    private int[] medArray = new int[7];

    // 处理查询药品
    private ListView medicine_view;
    private ArrayList<Medicine> medList = new ArrayList();
    private MedicineAdapter adapter;
    private EditText search_medicine;
    private TextView tv_total_price;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        Bmob.initialize(getActivity(), "6eb8a41a8df46d76705a4bcbd7bbc346");

        // 数据初始化
        medicine_view = view.findViewById(R.id.medicine_view);
        medicine_view.setOnItemClickListener(this::onItemClick);

        search_medicine = view.findViewById(R.id.search_medicine);
        tv_total_price = view.findViewById(R.id.tv_total_price);

        initData();

        // 给搜索框添加监听事件
        search_medicine.addTextChangedListener(new TextWatcher() {
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

        // 返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        // 查询数据
        BmobQuery<Medicine> query = new BmobQuery<>();
        query.findObjects(new FindListener<Medicine>() {
            @Override
            public void done(List<Medicine> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Medicine med = list.get(i);
                        medList.add(med);

                        adapter = new MedicineAdapter(getActivity(), medList, listener);
                        medicine_view.setAdapter(adapter);
                    }
                }
            }
        });
    }

    public void changeClient(String name) {
        // 根据输入条件筛选疾病数据
        medList.clear();
        BmobQuery<Medicine> query1 = new BmobQuery<>();
        if (!name.equals("")) {
            query1.addWhereEqualTo("keywords", name);
        }
        query1.findObjects(new FindListener<Medicine>() {
            @Override
            public void done(List<Medicine> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Medicine med = list.get(i);
                        medList.add(med);

                        adapter = new MedicineAdapter(getActivity(), medList, listener);
                        medicine_view.setAdapter(adapter);
                        setListViewHeight(medicine_view);
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

    // 响应item点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int i, long id) {
        Toast.makeText(getActivity(), "listview的item被点击了！，点击的位置是-->" + i, Toast.LENGTH_SHORT).show();
    }

    // 实现类，响应按钮点击事件
    private MedicineAdapter.MyClickListener listener = new MedicineAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            total_num ++;
            medArray[position]++;
            total_price += medList.get(position).getMedicinePrice();

            // 设置数据变化
            bt_num.setText("结算(" + total_num + ")");
            tv_total_price.setText("合计: " + total_price);
        }
    };

    @OnClick(R.id.bt_total_num)
    public void onClickTotalNum (View view) {
        for (int i=0; i<medArray.length; i++) {
            // 没有购买该药品则跳过
            if (medArray[i] != 0) {
                Order order = new Order();
                order.setUserId((int) BmobUser.getObjectByKey("id"));
                order.setMedicineId(medList.get(i).getId());
                order.setNum(medArray[i]);
                order.setPrice(medArray[i] * medList.get(i).getMedicinePrice());
                order.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "购买成功，请到“我的订单”里查看。", Toast.LENGTH_SHORT).show();
                            bt_num.setText("结算(0)");
                            tv_total_price.setText("合计：0.0");
                        } else {
                            Toast.makeText(getActivity(), "购买失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
