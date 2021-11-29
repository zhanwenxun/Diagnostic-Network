package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Medicine;
import com.zhanwenxun.dnapp.bean.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 用于显示订单信息ListView的适配器
 */

public class OrderAdapter extends BaseAdapter {

    private List<Order> ordData;
    private Context mContext;

    public OrderAdapter(Context context, ArrayList<Order> ordData) {
        this.mContext = context;
        this.ordData = ordData;
    }

    @Override
    public int getCount() {
        return ordData.size();
    }

    @Override
    public Object getItem(int i) {
        return ordData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
            OrderAdapter.ViewHolder viewHolder=new OrderAdapter.ViewHolder();

            viewHolder.name = view.findViewById(R.id.medicine_name);
            viewHolder.num = view.findViewById(R.id.order_num);
            viewHolder.price = view.findViewById(R.id.order_price);
            viewHolder.date = view.findViewById(R.id.order_date);

            view.setTag(viewHolder);
        }

        Order ord = ordData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // 查询药品名称
        BmobQuery<Medicine> bmobQuery = new BmobQuery<Medicine>();
        bmobQuery.addWhereEqualTo("id", ord.getMedicineId());
        bmobQuery.findObjects(new FindListener<Medicine>() {
            @Override
            public void done(List<Medicine> list, BmobException e) {
                if (e == null) {
                    Medicine medicine = list.get(0);
                    viewHolder.name.setText(medicine.getMedicineName());
                } else {

                }
            }
        });

        viewHolder.num.setText("x" + ord.getNum());
        viewHolder.price.setText(ord.getPrice() + "");
        viewHolder.date.setText(ord.getCreatedAt() + "");

        return view;
    }

    class ViewHolder {
        TextView name;
        TextView num;
        TextView price;
        TextView date;
    }
}
