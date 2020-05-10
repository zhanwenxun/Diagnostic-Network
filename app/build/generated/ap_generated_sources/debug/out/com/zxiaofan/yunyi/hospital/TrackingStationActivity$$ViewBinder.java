// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.hospital;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TrackingStationActivity$$ViewBinder<T extends com.zxiaofan.yunyi.hospital.TrackingStationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230790, "field 'callNumList'");
    target.callNumList = finder.castView(view, 2131230790, "field 'callNumList'");
    view = finder.findRequiredView(source, 2131230780, "field 'btnRefresh'");
    target.btnRefresh = finder.castView(view, 2131230780, "field 'btnRefresh'");
    view = finder.findRequiredView(source, 2131231241, "field 'tvHospitalName'");
    target.tvHospitalName = finder.castView(view, 2131231241, "field 'tvHospitalName'");
  }

  @Override public void unbind(T target) {
    target.callNumList = null;
    target.btnRefresh = null;
    target.tvHospitalName = null;
  }
}
