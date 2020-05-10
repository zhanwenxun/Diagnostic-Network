// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.registered;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DepartmentRegistered$$ViewBinder<T extends com.zxiaofan.yunyi.registered.DepartmentRegistered> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230848, "field 'departmentOne'");
    target.departmentOne = finder.castView(view, 2131230848, "field 'departmentOne'");
    view = finder.findRequiredView(source, 2131231241, "field 'tvHospitalName'");
    target.tvHospitalName = finder.castView(view, 2131231241, "field 'tvHospitalName'");
    view = finder.findRequiredView(source, 2131230849, "field 'departmentTwo'");
    target.departmentTwo = finder.castView(view, 2131230849, "field 'departmentTwo'");
    view = finder.findRequiredView(source, 2131230966, "field 'llContain'");
    target.llContain = finder.castView(view, 2131230966, "field 'llContain'");
  }

  @Override public void unbind(T target) {
    target.departmentOne = null;
    target.tvHospitalName = null;
    target.departmentTwo = null;
    target.llContain = null;
  }
}
