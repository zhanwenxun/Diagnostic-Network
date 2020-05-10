// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.hospital;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DepartmentInformationActivity$$ViewBinder<T extends com.zxiaofan.yunyi.hospital.DepartmentInformationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231028, "field 'refresh'");
    target.refresh = finder.castView(view, 2131231028, "field 'refresh'");
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230961, "field 'listView9'");
    target.listView9 = finder.castView(view, 2131230961, "field 'listView9'");
  }

  @Override public void unbind(T target) {
    target.refresh = null;
    target.titleBar = null;
    target.listView9 = null;
  }
}
