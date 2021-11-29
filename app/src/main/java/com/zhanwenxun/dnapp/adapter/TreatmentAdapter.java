package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Treatment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示就诊信息ListView的适配器
 */

public class TreatmentAdapter extends BaseAdapter {

    private List<Treatment> treData;
    private Context mContext;

    public TreatmentAdapter(Context context, ArrayList<Treatment> treData) {
        this.mContext = context;
        this.treData = treData;
    }

    @Override
    public int getCount() {
        return treData.size();
    }

    @Override
    public Object getItem(int i) {
        return treData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_treatment, parent, false);
            TreatmentAdapter.ViewHolder viewHolder = new TreatmentAdapter.ViewHolder();

            viewHolder.diseaseName = view.findViewById(R.id.tv_diseaseName);
            viewHolder.hospitalName = view.findViewById(R.id.tv_hospitalname);
            viewHolder.doctorName = view.findViewById(R.id.tv_doctorName);
            viewHolder.date= view.findViewById(R.id.tv_Date);
            viewHolder.userName = view.findViewById(R.id.tv_userName);
            viewHolder.department = view.findViewById(R.id.department);

            view.setTag(viewHolder);
        }

        Treatment tre = treData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.diseaseName.setText(tre.getDiseaseName());
        viewHolder.hospitalName.setText(tre.getHospitalName());
        viewHolder.doctorName.setText(tre.getDoctorName());
        viewHolder.date.setText(tre.getDate().getDate());
        viewHolder.userName.setText(tre.getUserName());
        viewHolder.department.setText(tre.getDepartment());

        return view;
    }

    class ViewHolder {
        TextView diseaseName;
        TextView userName;
        TextView doctorName;
        TextView hospitalName;
        TextView date;
        TextView department;
    }
}
