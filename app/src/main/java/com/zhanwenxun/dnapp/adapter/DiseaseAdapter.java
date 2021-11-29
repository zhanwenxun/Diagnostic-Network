package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Disease;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示疾病信息ListView的适配器
 */

public class DiseaseAdapter extends BaseAdapter {

    private List<Disease> disData;
    private Context mContext;

    public DiseaseAdapter(Context context, ArrayList<Disease> disData) {
        this.mContext = context;
        this.disData = disData;
    }

    @Override
    public int getCount() {
        return disData.size();
    }

    @Override
    public Object getItem(int i) {
        return disData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_disease, parent, false);
            DiseaseAdapter.ViewHolder viewHolder=new DiseaseAdapter.ViewHolder();

            viewHolder.name = view.findViewById(R.id.dis_name);
            viewHolder.department = view.findViewById(R.id.dis_department);
            viewHolder.cause = view.findViewById(R.id.dis_cause);
            viewHolder.symptom = view.findViewById(R.id.dis_symptom);
            viewHolder.description = view.findViewById(R.id.dis_description);

            view.setTag(viewHolder);
        }

        Disease dis = disData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.name.setText(dis.getName());
        viewHolder.department.setText(dis.getDepartment());
        viewHolder.cause.setText(dis.getCause());
        viewHolder.symptom.setText(dis.getSymptom());
        viewHolder.description.setText(dis.getDescription());

        return view;
    }

    class ViewHolder {
        TextView name;
        TextView department;
        TextView cause;
        TextView symptom;
        TextView description;
    }
}
