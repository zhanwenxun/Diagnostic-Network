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

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.activity.TreatmentActivity;
import com.zhanwenxun.dnapp.bean.Doctor;
import com.zhanwenxun.dnapp.bean.Hospital;
import com.zhanwenxun.dnapp.util.GlobalParms;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @explain: 医生模块的Fragment
 */

public class DoctorFragment extends Fragment {

    @BindView(R.id.tab_zhenduanjilu)
    View zhendaunjilu;

    @BindView(R.id.image_search_medicine)
    ImageView image_search_medicine;

    @BindView(R.id.text_search_medicine)
    TextView text_serach_medicine;

    @BindView(R.id.doc_name)
    TextView doc_name;

    @BindView(R.id.doc_job)
    TextView doc_job;

    @BindView(R.id.doc_type)
    TextView doc_type;

    @BindView(R.id.doc_hospital)
    TextView doc_hospital;

    private Unbinder unbinder;
    private int role = 0;
    int doctorId = 0;
    String doctorName = "";
    String hospital_name = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        Bmob.initialize(getActivity(), "6eb8a41a8df46d76705a4bcbd7bbc346");

        if (BmobUser.isLogin()) {
            getData();
        }

        // 返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    // 若为医生，显示医生信息
    public void getData() {

        // 判断用户是否为医生
        role = (int) BmobUser.getObjectByKey("role");

        if (role == 2) {
            // 查询医生数据
            doctorName = (String) BmobUser.getObjectByKey("username");
            BmobQuery<Doctor> query = new BmobQuery<>();
            query.addWhereEqualTo("account", doctorName);
            query.findObjects(new FindListener<Doctor>() {
                @Override
                public void done(List<Doctor> list, BmobException e) {
                    if (e == null) {
                        Doctor doc = list.get(0);
                        doc_name.setText(doc.getName());
                        doc_job.setText(doc.getJob());
                        doc_type.setText(doc.getType());

                        doctorId = doc.getId();
                        doctorName = doc.getName();

                        // 根据医院Id获取医生所在医院
                        BmobQuery<Hospital> bmobQuery = new BmobQuery<Hospital>();
                        bmobQuery.addWhereEqualTo("id", doc.getHospitalid());
                        bmobQuery.findObjects(new FindListener<Hospital>() {
                            @Override
                            public void done(List<Hospital> list, BmobException e) {
                                if (e == null) {
                                    Hospital hospital = list.get(0);
                                    hospital_name = hospital.getName();
                                    doc_hospital.setText(hospital_name+"");
                                } else {
                                    Toast.makeText(getActivity(), "错误信息：" + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "错误信息：" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            // 若不为医生则不操作
        }
    }

    @OnClick(R.id.tab_zhenduanjilu)
    public void onClickZhenDuanJiLu(View view) {
        Intent intent = new Intent();
        if (role == 2) {
            intent.putExtra("doctorId", doctorId);
            intent.putExtra("doctorName", doctorName);
            intent.setClass(getActivity(), TreatmentActivity.class);
            startActivity(intent);
        } else {
         Toast.makeText(getActivity(), "此功能仅对医生开放，谢谢！", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击药品查询跳转至 fragment_medicine 界面
    @OnClick({R.id.image_search_medicine, R.id.text_search_medicine})
    public void onClickSearchMedicine(View view) {
        if (role == 2) {
            GlobalParms.sChangeFragment.changeFragment(R.id.navigation_medicine);
        } else {
            Toast.makeText(getActivity(), "此功能仅对医生开放，谢谢！", Toast.LENGTH_SHORT).show();
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
