// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.record;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SeeDocDetail$$ViewBinder<T extends com.zxiaofan.yunyi.record.SeeDocDetail> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231225, "field 'tvContent'");
    target.tvContent = finder.castView(view, 2131231225, "field 'tvContent'");
    view = finder.findRequiredView(source, 2131231237, "field 'tvFyd'");
    target.tvFyd = finder.castView(view, 2131231237, "field 'tvFyd'");
    view = finder.findRequiredView(source, 2131231221, "field 'tvCfd'");
    target.tvCfd = finder.castView(view, 2131231221, "field 'tvCfd'");
    view = finder.findRequiredView(source, 2131231247, "field 'tvJyd'");
    target.tvJyd = finder.castView(view, 2131231247, "field 'tvJyd'");
    view = finder.findRequiredView(source, 2131231244, "field 'tvJcd'");
    target.tvJcd = finder.castView(view, 2131231244, "field 'tvJcd'");
    view = finder.findRequiredView(source, 2131231226, "field 'tvDate'");
    target.tvDate = finder.castView(view, 2131231226, "field 'tvDate'");
    view = finder.findRequiredView(source, 2131231236, "field 'tvDzbl'");
    target.tvDzbl = finder.castView(view, 2131231236, "field 'tvDzbl'");
    view = finder.findRequiredView(source, 2131231216, "field 'tvFjyd'");
    target.tvFjyd = finder.castView(view, 2131231216, "field 'tvFjyd'");
    view = finder.findRequiredView(source, 2131231215, "field 'tvFjcd'");
    target.tvFjcd = finder.castView(view, 2131231215, "field 'tvFjcd'");
    view = finder.findRequiredView(source, 2131231214, "field 'tvFcfd'");
    target.tvFcfd = finder.castView(view, 2131231214, "field 'tvFcfd'");
    view = finder.findRequiredView(source, 2131230967, "field 'llFragment'");
    target.llFragment = finder.castView(view, 2131230967, "field 'llFragment'");
  }

  @Override public void unbind(T target) {
    target.tvContent = null;
    target.tvFyd = null;
    target.tvCfd = null;
    target.tvJyd = null;
    target.tvJcd = null;
    target.tvDate = null;
    target.tvDzbl = null;
    target.tvFjyd = null;
    target.tvFjcd = null;
    target.tvFcfd = null;
    target.llFragment = null;
  }
}
