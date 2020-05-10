// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.User;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddHosCard$$ViewBinder<T extends com.zxiaofan.yunyi.User.AddHosCard> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230874, "field 'etHosname'");
    target.etHosname = finder.castView(view, 2131230874, "field 'etHosname'");
    view = finder.findRequiredView(source, 2131230873, "field 'etCardid'");
    target.etCardid = finder.castView(view, 2131230873, "field 'etCardid'");
    view = finder.findRequiredView(source, 2131231187, "field 'textView63'");
    target.textView63 = finder.castView(view, 2131231187, "field 'textView63'");
    view = finder.findRequiredView(source, 2131231188, "field 'textView64'");
    target.textView64 = finder.castView(view, 2131231188, "field 'textView64'");
    view = finder.findRequiredView(source, 2131230788, "field 'button5'");
    target.button5 = finder.castView(view, 2131230788, "field 'button5'");
  }

  @Override public void unbind(T target) {
    target.titleBar = null;
    target.etHosname = null;
    target.etCardid = null;
    target.textView63 = null;
    target.textView64 = null;
    target.button5 = null;
  }
}
