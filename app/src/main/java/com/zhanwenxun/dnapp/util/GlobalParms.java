package com.zhanwenxun.dnapp.util;

import com.zhanwenxun.dnapp.fragment.DiagnosisFragment;
import com.zhanwenxun.dnapp.fragment.DoctorFragment;
import com.zhanwenxun.dnapp.fragment.HospitalFragment;
import com.zhanwenxun.dnapp.fragment.MedicineFragment;
import com.zhanwenxun.dnapp.fragment.MyFragment;

/**
 * @explain: 全局类：用于切换Fragment
 */

public class GlobalParms {

    // 定义五个模块对应的Fragment
    private static DiagnosisFragment diagnosisFragment;
    private static DoctorFragment doctorFragment;
    private static HospitalFragment hospitalFragment;
    private static MedicineFragment medicineFragment;
    private static MyFragment myFragment;

    public static ChangeFragment sChangeFragment;

    // 获取五个模块的Fragment
    public static DiagnosisFragment getDiagnosisFragment() {
        if (diagnosisFragment == null) {
            diagnosisFragment = new DiagnosisFragment();
        }
        return diagnosisFragment;
    }

    public static HospitalFragment getHospitalFragment() {
        if (hospitalFragment == null) {
            hospitalFragment = new HospitalFragment();
        }
        return hospitalFragment;
    }

    public static DoctorFragment getDoctorFragment() {
        if (doctorFragment == null) {
            doctorFragment = new DoctorFragment();
        }
        return doctorFragment;
    }

    public static MedicineFragment getMedicineFragment() {
        if (medicineFragment == null) {
            medicineFragment = new MedicineFragment();
        }
        return medicineFragment;
    }

    public static MyFragment getMyFragment() {
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        return myFragment;
    }

    // 设置选中的Fragment
    public static void setFragmentSelected(ChangeFragment changeFragment) {
        sChangeFragment = changeFragment;
    }
}
