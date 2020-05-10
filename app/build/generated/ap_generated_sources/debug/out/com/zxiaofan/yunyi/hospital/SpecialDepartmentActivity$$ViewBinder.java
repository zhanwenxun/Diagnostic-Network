// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.hospital;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SpecialDepartmentActivity$$ViewBinder<T extends com.zxiaofan.yunyi.hospital.SpecialDepartmentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230902, "field 'hosname'");
    target.hosname = finder.castView(view, 2131230902, "field 'hosname'");
    view = finder.findRequiredView(source, 2131230957, "field 'listView5'");
    target.listView5 = finder.castView(view, 2131230957, "field 'listView5'");
  }

  @Override public void unbind(T target) {
    target.titleBar = null;
    target.hosname = null;
    target.listView5 = null;
  }
}
