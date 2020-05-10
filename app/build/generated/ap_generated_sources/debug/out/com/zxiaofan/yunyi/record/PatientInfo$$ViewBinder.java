// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.record;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PatientInfo$$ViewBinder<T extends com.zxiaofan.yunyi.record.PatientInfo> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231252, "field 'tvName'");
    target.tvName = finder.castView(view, 2131231252, "field 'tvName'");
    view = finder.findRequiredView(source, 2131231268, "field 'tvSex'");
    target.tvSex = finder.castView(view, 2131231268, "field 'tvSex'");
    view = finder.findRequiredView(source, 2131231242, "field 'tvIdcard'");
    target.tvIdcard = finder.castView(view, 2131231242, "field 'tvIdcard'");
    view = finder.findRequiredView(source, 2131231259, "field 'tvPhone'");
    target.tvPhone = finder.castView(view, 2131231259, "field 'tvPhone'");
    view = finder.findRequiredView(source, 2131230782, "field 'btnSubmit'");
    target.btnSubmit = finder.castView(view, 2131230782, "field 'btnSubmit'");
  }

  @Override public void unbind(T target) {
    target.tvName = null;
    target.tvSex = null;
    target.tvIdcard = null;
    target.tvPhone = null;
    target.btnSubmit = null;
  }
}
