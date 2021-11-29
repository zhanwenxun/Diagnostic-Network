package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Hospital;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示医院信息ListView的适配器
 */

public class HospitalAdapter  extends BaseAdapter {

    private List<Hospital> hosData;
    private Context mContext;

    public HospitalAdapter(Context context, ArrayList<Hospital> hosData) {
        this.mContext = context;
        this.hosData = hosData;
    }

    @Override
    public int getCount() {
        return hosData.size();
    }

    @Override
    public Object getItem(int i) {
        return hosData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hospital, parent, false);
            ViewHolder viewHolder=new ViewHolder();

            viewHolder.name = view.findViewById(R.id.hos_name);
            viewHolder.city = view.findViewById(R.id.hos_city);
            viewHolder.level = view.findViewById(R.id.hos_level);
            viewHolder.address = view.findViewById(R.id.hos_address);

            view.setTag(viewHolder);
        }

        Hospital hos = hosData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.name.setText(hos.getName());
        viewHolder.city.setText(hos.getCity());
        viewHolder.level.setText(hos.getLevel());
        viewHolder.address.setText(hos.getAddress());

        return view;
    }

    class ViewHolder {
        TextView name;
        TextView city;
        TextView level;
        TextView address;
    }
}
