// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FindDocActivity$$ViewBinder<T extends com.zxiaofan.yunyi.activity.FindDocActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230885, "field 'finddoc'");
    target.finddoc = finder.castView(view, 2131230885, "field 'finddoc'");
    view = finder.findRequiredView(source, 2131230951, "field 'listView'");
    target.listView = finder.castView(view, 2131230951, "field 'listView'");
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131231210, "field 'tv1'");
    target.tv1 = finder.castView(view, 2131231210, "field 'tv1'");
    view = finder.findRequiredView(source, 2131231211, "field 'tv2'");
    target.tv2 = finder.castView(view, 2131231211, "field 'tv2'");
    view = finder.findRequiredView(source, 2131231212, "field 'tv3'");
    target.tv3 = finder.castView(view, 2131231212, "field 'tv3'");
    view = finder.findRequiredView(source, 2131230944, "field 'l1'");
    target.l1 = finder.castView(view, 2131230944, "field 'l1'");
    view = finder.findRequiredView(source, 2131231028, "field 'refresh'");
    target.refresh = finder.castView(view, 2131231028, "field 'refresh'");
  }

  @Override public void unbind(T target) {
    target.finddoc = null;
    target.listView = null;
    target.titleBar = null;
    target.tv1 = null;
    target.tv2 = null;
    target.tv3 = null;
    target.l1 = null;
    target.refresh = null;
  }
}
