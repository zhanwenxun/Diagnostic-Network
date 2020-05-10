// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.record;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SeeDocDetailDzbl$$ViewBinder<T extends com.zxiaofan.yunyi.record.SeeDocDetailDzbl> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231283, "field 'tvZs'");
    target.tvZs = finder.castView(view, 2131231283, "field 'tvZs'");
    view = finder.findRequiredView(source, 2131231280, "field 'tvXbs'");
    target.tvXbs = finder.castView(view, 2131231280, "field 'tvXbs'");
    view = finder.findRequiredView(source, 2131231271, "field 'tvTgjc'");
    target.tvTgjc = finder.castView(view, 2131231271, "field 'tvTgjc'");
    view = finder.findRequiredView(source, 2131231238, "field 'tvFzjc'");
    target.tvFzjc = finder.castView(view, 2131231238, "field 'tvFzjc'");
    view = finder.findRequiredView(source, 2131231281, "field 'tvZd'");
    target.tvZd = finder.castView(view, 2131231281, "field 'tvZd'");
  }

  @Override public void unbind(T target) {
    target.tvZs = null;
    target.tvXbs = null;
    target.tvTgjc = null;
    target.tvFzjc = null;
    target.tvZd = null;
  }
}
