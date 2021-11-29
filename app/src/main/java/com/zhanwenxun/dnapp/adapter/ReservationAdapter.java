package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Reservation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示预约信息ListView的适配器
 */

public class ReservationAdapter extends BaseAdapter
        implements View.OnClickListener {

    private static final String TAG = "ReservationAdapter";
    private List<Reservation> resData;
    private Context mContext;

    // 所有listview的item共用同一个
    private Callback callback;

    // 自定义接口，用于回调按钮点击事件到Activity
    public interface Callback {
        public void click(View view);
    }

    public ReservationAdapter(Context context, ArrayList<Reservation> resData,
                              Callback callback) {
        this.mContext = context;
        this.resData = resData;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return resData.size();
    }

    @Override
    public Object getItem(int i) {
        Log.i(TAG, "getItem");
        return resData.get(i);
    }

    @Override
    public long getItemId(int i) {
        Log.i(TAG, "getItemId");
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Log.i(TAG, "getView");
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");
        ViewHolder viewHolder = null;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_reservation, parent, false);
            viewHolder = new ReservationAdapter.ViewHolder();

            viewHolder.hospitalName = view.findViewById(R.id.tv_hospitalName);
            viewHolder.date= view.findViewById(R.id.tv_date);
            viewHolder.userName = view.findViewById(R.id.tv_userName);
            viewHolder.department = view.findViewById(R.id.tv_Department);
            viewHolder.backno = view.findViewById(R.id.btn_back_no);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Reservation res = resData.get(i);

        viewHolder.hospitalName.setText(res.getHospitalName());
        viewHolder.date.setText(res.getDate().getDate());
        viewHolder.userName.setText(res.getUserName());
        viewHolder.department.setText(res.getDepartment());

        viewHolder.backno.setOnClickListener(this);
        viewHolder.backno.setTag(i);
        return view;
    }

    public class ViewHolder {
        TextView hospitalName;
        TextView date;
        TextView userName;
        TextView department;
        Button backno;
    }

    // 响应点击事件，调用子定义接口，并传入View
    @Override
    public void onClick(View view) {
        callback.click(view);
    }
}
