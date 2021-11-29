package com.zhanwenxun.dnapp.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * @explain: 基础的Activity
 */

public abstract class BaseActivity extends AppCompatActivity  {

    protected BaseActivity mActivity;
    protected ImmersionBar mImmersionBar;
    private InputMethodManager imm;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        //绑定控件
        mActivity = this;
        unbinder = ButterKnife.bind(mActivity);

        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();

        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();
    }

    protected abstract int setLayoutId();

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    protected Disposable disposable;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
        unbinder.unbind();
        this.imm = null;
    }

    /**
     * 解除rxjava订阅
     */
    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
