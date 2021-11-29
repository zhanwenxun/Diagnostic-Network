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
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.activity.NewsActivity;
import com.zhanwenxun.dnapp.activity.SelfDiagnosisActivity;
import com.zhanwenxun.dnapp.adapter.DiseaseAdapter;
import com.zhanwenxun.dnapp.adapter.NewsAdapter;
import com.zhanwenxun.dnapp.bean.Disease;
import com.zhanwenxun.dnapp.bean.News;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 诊断模块的Fragment
 */

public class DiagnosisFragment extends Fragment {

    @BindView(R.id.self_diagnosis)
    View self_diagnosis;

    // 处理查询疾病
    private ListView disease_view;
    private ArrayList<Disease> disList = new ArrayList();
    private DiseaseAdapter adapter;
    private EditText search_disease;

    // 显示资讯
    private ListView news_view;
    private ArrayList<News> newsList = new ArrayList();
    private NewsAdapter adapter2;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosis, container, false);

        Bmob.initialize(getActivity(), "6eb8a41a8df46d76705a4bcbd7bbc346");

        // 数据初始化
        disease_view = view.findViewById(R.id.disease_view);
        news_view = view.findViewById(R.id.news_view);
        search_disease = view.findViewById(R.id.search_disease);

        // 给搜索框添加监听事件
        search_disease.addTextChangedListener(new TextWatcher() {
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

        initData2();

        // 给资讯ListView添加点击监听事件
        news_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News news = newsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("title", news.getTitle());
                bundle.putString("type", news.getType());
                bundle.putString("keywords", news.getKeywords());
                bundle.putString("content", news.getContent());

                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        // 返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        disList.clear();
        // 查询疾病数据
        BmobQuery<Disease> query = new BmobQuery<>();
        query.findObjects(new FindListener<Disease>() {
            @Override
            public void done(List<Disease> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Disease dis = list.get(i);
                        disList.add(dis);

                        adapter = new DiseaseAdapter(getActivity(), disList);
                        disease_view.setAdapter(adapter);
                    }
                }
            }
        });
    }

    // 点击疾病自诊按钮跳转至 self_diagnosis 界面
    @OnClick(R.id.self_diagnosis)
    public void onClickSelfDiagnosis(View view) {
        // 处理疾病自诊事件
        startActivity(new Intent(getActivity(), SelfDiagnosisActivity.class));
    }

    public void changeClient(String name) {
        // 根据输入条件筛选疾病数据
        disList.clear();
        BmobQuery<Disease> query1 = new BmobQuery<>();
        if (!name.equals("")) {
            query1.addWhereEqualTo("keywords", name);
        }
        query1.findObjects(new FindListener<Disease>() {
            @Override
            public void done(List<Disease> list, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list.size(); i++) {
                        Disease dis = list.get(i);
                        disList.add(dis);

                        adapter = new DiseaseAdapter(getActivity(), disList);
                        disease_view.setAdapter(adapter);
                        setListViewHeight(disease_view);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initData2() {
        // 查询新闻数据
        newsList.clear();
        BmobQuery<News> query2 = new BmobQuery<>();
        query2.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list2, BmobException e) {
                if (e == null) {
                    for (int i=0; i<list2.size(); i++) {
                        News news = list2.get(i);
                        newsList.add(news);

                        adapter2 = new NewsAdapter(getActivity(), newsList);
                        news_view.setAdapter(adapter2);
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
}
