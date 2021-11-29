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
import com.zhanwenxun.dnapp.bean.Medicine;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示药品信息ListView的适配器
 */

public class MedicineAdapter extends BaseAdapter {

    private static final String TAG = "MedicineAdapter";
    private List<Medicine> medData;
    private Context mContext;
    // 所有listview的item都共用同一个listener对象
    private MyClickListener listener;

    public MedicineAdapter(Context context, ArrayList<Medicine> medData, MyClickListener listener) {
        this.mContext = context;
        this.medData = medData;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return medData.size();
    }

    @Override
    public Object getItem(int i) {
        Log.i(TAG, "getItem");
        return medData.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_medicine, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.name = view.findViewById(R.id.med_name);
            viewHolder.format = view.findViewById(R.id.med_format);
            viewHolder.price = view.findViewById(R.id.med_price);
            viewHolder.type = view.findViewById(R.id.med_type);
            viewHolder.producter = view.findViewById(R.id.med_producter);
            viewHolder.add = view.findViewById(R.id.med_add);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Medicine med = medData.get(i);

        viewHolder.name.setText(med.getMedicineName());
        viewHolder.format.setText(med.getFormat());
        viewHolder.price.setText(med.getMedicinePrice()+"");
        viewHolder.type.setText(med.getMedicineType());
        viewHolder.producter.setText(med.getProducter());

        viewHolder.add.setOnClickListener(listener);
        // 这样能使所有listview的item都共用同一个listener，而不用为每个item都设置各自的listener
        viewHolder.add.setTag(i);

        return view;
    }

    public class ViewHolder {
        TextView name;
        TextView type;
        TextView format;
        TextView price;
        TextView producter;
        // 增加药品数量按钮
        Button add;
    }

    // 用于回调的抽象类
    public static abstract  class MyClickListener
            implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            myOnClick((Integer) view.getTag(), view);
        }

        public abstract void myOnClick(int position, View view);
    }
}
