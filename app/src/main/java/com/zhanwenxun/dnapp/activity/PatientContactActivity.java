package com.zhanwenxun.dnapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.zhanwenxun.dnapp.R;
import com.zhanwenxun.dnapp.bean.Message;
import com.zhanwenxun.dnapp.bean.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @explain: 医生与病人交流的Activity
 */

public class PatientContactActivity extends BaseActivity {

    @BindView(R.id.patient_name)
    TextView patient_name;
    @BindView(R.id.patient_sex)
    TextView patient_sex;
    @BindView(R.id.patient_phone)
    TextView patient_phone;
    @BindView(R.id.patinet_email)
    TextView patient_email;
    @BindView(R.id.patient_age)
    TextView patient_age;

    @BindView(R.id.et_contact)
    EditText et_contact;
    @BindView(R.id.contact_submit)
    Button contact_submit;

    private int userId = 0;
    private int doctorId = 0;
    private String doctorName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_contact);
        ButterKnife.bind(PatientContactActivity.this);

        Bundle bundle = getIntent().getExtras();
        doctorId = bundle.getInt("doctorId");
        userId = bundle.getInt("userId");
        doctorName = bundle.getString("doctorName");

        getUserData(userId);

        // 点击标题栏的返回按钮结束该Activity
        Toolbar toolbar = findViewById(R.id.titlebar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.patient_contact;
    }

    // 查询用户数据并替换用户信息
    public void getUserData(int userId) {

        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("id", userId);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    User user = list.get(0);
                    patient_name.setText(user.getName());
                    patient_age.setText(user.getAge()+"");
                    patient_email.setText(user.getEmail());
                    patient_phone.setText(user.getMobilePhoneNumber());
                    if (user.getSex() == 1) {
                        patient_sex.setText("男");
                    } else {
                        patient_sex.setText("女");
                    }
                } else {
                    Toast.makeText(PatientContactActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // 处理医生联系患者事件
    @OnClick(R.id.contact_submit)
    public void onClickContact(View view) {

        String contacttext = et_contact.getText().toString().trim();

        // 联系消息如果为空，提示请重新填写
        if (contacttext.equals("")) {
            Toast.makeText(PatientContactActivity.this,"消息为空，请重新填写！", Toast.LENGTH_SHORT).show();
        } else {

            Message message = new Message();
            message.setUserId(userId);
            message.setDoctorId(doctorId);
            message.setDoctorName(doctorName);
            message.setContactText(contacttext);
            message.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        Toast.makeText(PatientContactActivity.this, "发送消息成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PatientContactActivity.this, "发送消息失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
