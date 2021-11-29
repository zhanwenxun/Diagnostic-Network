package com.zhanwenxun.dnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.News;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @explain: 用于显示资讯信息ListView的适配器
 */

public class NewsAdapter extends BaseAdapter {

    private List<News> newsData;
    private Context mContext;

    public NewsAdapter(Context context, ArrayList<News> newsData) {
        this.mContext = context;
        this.newsData = newsData;
    }

    @Override
    public int getCount() {
        return newsData.size();
    }

    @Override
    public Object getItem(int i) {
        return newsData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        Bmob.initialize(mContext, "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.title = view.findViewById(R.id.news_title);
            viewHolder.content = view.findViewById(R.id.news_content);

            view.setTag(viewHolder);
        }

        News news = newsData.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.title.setText(news.getTitle());
        viewHolder.content.setText(news.getContent());

        return view;
    }

    class ViewHolder {
        TextView title;
        TextView content;
    }
}
