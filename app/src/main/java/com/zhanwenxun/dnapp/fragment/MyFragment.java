package com.zhanwenxun.dnapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

import com.zhanwenxun.dnapp.MainActivity;
import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.activity.AboutActivity;
import com.zhanwenxun.dnapp.activity.FeedbackActivity;
import com.zhanwenxun.dnapp.activity.LoginActivity;
import com.zhanwenxun.dnapp.activity.NotificationActivity;
import com.zhanwenxun.dnapp.activity.MyReservationActivity;
import com.zhanwenxun.dnapp.activity.MyOrderActivity;
import com.zhanwenxun.dnapp.activity.ResetpwdActivity;
import com.zhanwenxun.dnapp.activity.MyTreatmentActivity;
import com.zhanwenxun.dnapp.activity.UserInfoActivity;

/**
 * @explain: 个人模块的Fragment
 */

public class MyFragment extends Fragment {

    ImageView iv_usericon;
    TextView tv_username;

    @BindView(R.id.notification)
    TextView tv_notification;

    @BindView(R.id.reservation)
    TextView tv_reservation;

    @BindView(R.id.treatment)
    TextView tv_diagnosis;

    @BindView(R.id.order)
    TextView tv_order;

    @BindView(R.id.resetpwd)
    TextView tv_resetpwd;

    @BindView(R.id.loginout)
    TextView tv_loginout;

    @BindView(R.id.aboutus)
    TextView tv_aboutus;

    @BindView(R.id.feedback)
    TextView tv_feedback;

    private Unbinder unbinder;
    private int role; // 1为用户，2为医生

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        // 判断用户已登录修改用户名和头像
        iv_usericon = (ImageView) view.findViewById(R.id.usericon);
        tv_username = (TextView) view.findViewById(R.id.username);

        if (BmobUser.isLogin()) {
            role = (int) BmobUser.getObjectByKey("role");
        }

        // 判断用户登录且不为医生
        if (BmobUser.isLogin() && role == 1) {
            iv_usericon.setBackgroundResource(R.drawable.head_icon);
            String username = (String) BmobUser.getObjectByKey("username");
            tv_username.setText(username);
        } else {
            iv_usericon.setBackgroundResource(R.mipmap.user_head);
            tv_username.setText("用户名");
        }

        // 返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    // 点击用户头像
    // 未登录状态则跳转至 activity_login 界面
    // 已登录状态则跳转至 activity_userinfo 界面
    @OnClick(R.id.usericon)
    public void onClickIcon(View view) {
        if (!BmobUser.isLogin()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (role == 1) {
            startActivity(new Intent(getActivity(), UserInfoActivity.class));
        } else {
            Toast.makeText(getActivity(), "您为医生，不能进入！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击未读消息跳转至 notification 界面
    @OnClick(R.id.notification)
    public void onClickNotification(View view) {
        if (BmobUser.isLogin() && role == 1) {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录或为医生！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击我的预约跳转至 my_reservation 界面
    @OnClick(R.id.reservation)
    public void onClickReservation(View view) {
        if (BmobUser.isLogin() && role == 1) {
            startActivity(new Intent(getActivity(), MyReservationActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录或为医生！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击我的就诊跳转至 my_treatment 界面
    @OnClick(R.id.treatment)
    public void onClickTreatment(View view) {
        if (BmobUser.isLogin() && role == 1) {
            startActivity(new Intent(getActivity(), MyTreatmentActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录或为医生！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击我的订单跳转至 my_order 界面
    @OnClick(R.id.order)
    public void onClickInfo(View view) {
        if (BmobUser.isLogin() && role == 1) {
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录或为医生！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击修改密码跳转至 activity_resetpwd 界面
    @OnClick(R.id.resetpwd)
    public void onClickReset(View view) {
        if (BmobUser.isLogin()) {
            startActivity(new Intent(getActivity(), ResetpwdActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录！", Toast.LENGTH_SHORT).show();
        }
    }

    // 处理退出登录事件
    @OnClick(R.id.loginout)
    public void onClickLoginOut(View view) {
        if (BmobUser.isLogin()) {
            BmobUser.logOut();
            startActivity(new Intent(getActivity(), MainActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击关于我们跳转至 activity_about 界面
    @OnClick(R.id.aboutus)
    public void onClickAbout(View view) {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    // 点击用户反馈跳转至 activity_feedback 界面
    @OnClick(R.id.feedback)
    public void onClickFeedback(View view) {
        if (BmobUser.isLogin() && role == 1) {
            startActivity(new Intent(getActivity(), FeedbackActivity.class));
        } else {
            Toast.makeText(getActivity(), "您还未登录或为医生！", Toast.LENGTH_SHORT).show();
        }
    }

    // 退出时解除绑定
    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
