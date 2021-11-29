package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @explain: 资讯显示的Activity
 */

public class NewsActivity extends BaseActivity {

    @BindView(R.id.news_title)
    TextView news_title;
    @BindView(R.id.news_type)
    TextView news_type;
    @BindView(R.id.news_keywords)
    TextView news_keywords;
    @BindView(R.id.news_content)
    TextView news_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        ButterKnife.bind(NewsActivity.this);

        // 通过Intent传递获取资讯信息
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String type = bundle.getString("type");
        String keywors = bundle.getString("keywords");
        String content = bundle.getString("content");

        // 设置资讯内容
        news_title.setText(title);
        news_type.setText(type);
        news_keywords.setText(keywors);
        news_content.setText(content);

        // 点击标题栏的返回按钮结束该Activity
        Toolbar toolbar = findViewById(R.id.titlebar_news);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.news_details;
    }


}
