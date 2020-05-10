// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.registered;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisteredMain$$ViewBinder<T extends com.zxiaofan.yunyi.registered.RegisteredMain> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231249, "field 'tvLoc'");
    target.tvLoc = finder.castView(view, 2131231249, "field 'tvLoc'");
    view = finder.findRequiredView(source, 2131231240, "field 'tvHospital'");
    target.tvHospital = finder.castView(view, 2131231240, "field 'tvHospital'");
    view = finder.findRequiredView(source, 2131230988, "field 'minHospital'");
    target.minHospital = finder.castView(view, 2131230988, "field 'minHospital'");
    view = finder.findRequiredView(source, 2131231270, "field 'tvSjwk'");
    target.tvSjwk = finder.castView(view, 2131231270, "field 'tvSjwk'");
    view = finder.findRequiredView(source, 2131231226, "field 'tvDate'");
    target.tvDate = finder.castView(view, 2131231226, "field 'tvDate'");
    view = finder.findRequiredView(source, 2131230987, "field 'minDate'");
    target.minDate = finder.castView(view, 2131230987, "field 'minDate'");
    view = finder.findRequiredView(source, 2131231263, "field 'tvPtmz'");
    target.tvPtmz = finder.castView(view, 2131231263, "field 'tvPtmz'");
    view = finder.findRequiredView(source, 2131230989, "field 'minPtmz'");
    target.minPtmz = finder.castView(view, 2131230989, "field 'minPtmz'");
  }

  @Override public void unbind(T target) {
    target.tvLoc = null;
    target.tvHospital = null;
    target.minHospital = null;
    target.tvSjwk = null;
    target.tvDate = null;
    target.minDate = null;
    target.tvPtmz = null;
    target.minPtmz = null;
  }
}
