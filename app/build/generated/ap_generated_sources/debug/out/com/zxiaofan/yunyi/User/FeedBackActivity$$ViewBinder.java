// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.User;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedBackActivity$$ViewBinder<T extends com.zxiaofan.yunyi.User.FeedBackActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230857, "field 'ed1'");
    target.ed1 = finder.castView(view, 2131230857, "field 'ed1'");
    view = finder.findRequiredView(source, 2131231081, "field 'submitA'");
    target.submitA = finder.castView(view, 2131231081, "field 'submitA'");
  }

  @Override public void unbind(T target) {
    target.titleBar = null;
    target.ed1 = null;
    target.submitA = null;
  }
}
