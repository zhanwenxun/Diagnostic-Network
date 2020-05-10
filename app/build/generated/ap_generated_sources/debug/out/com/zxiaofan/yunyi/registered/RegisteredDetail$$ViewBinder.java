// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.registered;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisteredDetail$$ViewBinder<T extends com.zxiaofan.yunyi.registered.RegisteredDetail> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231240, "field 'tvHospital'");
    target.tvHospital = finder.castView(view, 2131231240, "field 'tvHospital'");
    view = finder.findRequiredView(source, 2131231230, "field 'tvDepartment'");
    target.tvDepartment = finder.castView(view, 2131231230, "field 'tvDepartment'");
    view = finder.findRequiredView(source, 2131231233, "field 'tvDocDate'");
    target.tvDocDate = finder.castView(view, 2131231233, "field 'tvDocDate'");
    view = finder.findRequiredView(source, 2131231258, "field 'tvOutpatientType'");
    target.tvOutpatientType = finder.castView(view, 2131231258, "field 'tvOutpatientType'");
    view = finder.findRequiredView(source, 2131231260, "field 'tvPrice'");
    target.tvPrice = finder.castView(view, 2131231260, "field 'tvPrice'");
    view = finder.findRequiredView(source, 2131231252, "field 'tvName'");
    target.tvName = finder.castView(view, 2131231252, "field 'tvName'");
    view = finder.findRequiredView(source, 2131231268, "field 'tvSex'");
    target.tvSex = finder.castView(view, 2131231268, "field 'tvSex'");
    view = finder.findRequiredView(source, 2131231259, "field 'tvPhone'");
    target.tvPhone = finder.castView(view, 2131231259, "field 'tvPhone'");
    view = finder.findRequiredView(source, 2131231242, "field 'tvIdcard'");
    target.tvIdcard = finder.castView(view, 2131231242, "field 'tvIdcard'");
    view = finder.findRequiredView(source, 2131230926, "field 'ivHead'");
    target.ivHead = finder.castView(view, 2131230926, "field 'ivHead'");
    view = finder.findRequiredView(source, 2131230782, "field 'btnSubmit'");
    target.btnSubmit = finder.castView(view, 2131230782, "field 'btnSubmit'");
    view = finder.findRequiredView(source, 2131230965, "field 'llChangePatient'");
    target.llChangePatient = finder.castView(view, 2131230965, "field 'llChangePatient'");
    view = finder.findRequiredView(source, 2131230968, "field 'llMain'");
    target.llMain = finder.castView(view, 2131230968, "field 'llMain'");
  }

  @Override public void unbind(T target) {
    target.tvHospital = null;
    target.tvDepartment = null;
    target.tvDocDate = null;
    target.tvOutpatientType = null;
    target.tvPrice = null;
    target.tvName = null;
    target.tvSex = null;
    target.tvPhone = null;
    target.tvIdcard = null;
    target.ivHead = null;
    target.btnSubmit = null;
    target.llChangePatient = null;
    target.llMain = null;
  }
}
