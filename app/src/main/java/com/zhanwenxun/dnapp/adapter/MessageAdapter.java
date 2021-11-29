package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Message;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示用户消息ListView的适配器
 */

public class MessageAdapter extends BaseAdapter {

    private List<Message> mesData;
    private Context mContext;

    public MessageAdapter(Context context, ArrayList<Message> mesData) {
        this.mContext = context;
        this.mesData = mesData;
    }

    @Override
    public int getCount() {
        return mesData.size();
    }

    @Override
    public Object getItem(int i) {
        return mesData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
            MessageAdapter.ViewHolder viewHolder = new MessageAdapter.ViewHolder();

            viewHolder.message_context = view.findViewById(R.id.message_context);
            viewHolder.message_doctorName = view.findViewById(R.id.message_doctorName);

            view.setTag(viewHolder);
        }

        Message message = mesData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.message_context.setText(message.getContactText());
        viewHolder.message_doctorName.setText(message.getDoctorName());

        return view;
    }

    class ViewHolder {
        TextView message_doctorName;
        TextView message_context;
    }
}
