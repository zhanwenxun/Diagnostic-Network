// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.User;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyUserActivity$$ViewBinder<T extends com.zxiaofan.yunyi.User.MyUserActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230959, "field 'listView7'");
    target.listView7 = finder.castView(view, 2131230959, "field 'listView7'");
    view = finder.findRequiredView(source, 2131231067, "field 'slistview'");
    target.slistview = finder.castView(view, 2131231067, "field 'slistview'");
  }

  @Override public void unbind(T target) {
    target.titleBar = null;
    target.listView7 = null;
    target.slistview = null;
  }
}
