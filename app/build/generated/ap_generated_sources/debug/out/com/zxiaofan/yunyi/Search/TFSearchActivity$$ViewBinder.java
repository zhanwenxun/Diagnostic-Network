// Generated code from Butter Knife. Do not modify!
package com.zxiaofan.yunyi.Search;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TFSearchActivity$$ViewBinder<T extends com.zxiaofan.yunyi.Search.TFSearchActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231204, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131231204, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131230751, "field 'autoEdit'");
    target.autoEdit = finder.castView(view, 2131230751, "field 'autoEdit'");
    view = finder.findRequiredView(source, 2131231051, "field 'searchButton'");
    target.searchButton = finder.castView(view, 2131231051, "field 'searchButton'");
    view = finder.findRequiredView(source, 2131231030, "field 'relativeLayout1'");
    target.relativeLayout1 = finder.castView(view, 2131231030, "field 'relativeLayout1'");
    view = finder.findRequiredView(source, 2131231200, "field 'thos'");
    target.thos = finder.castView(view, 2131231200, "field 'thos'");
    view = finder.findRequiredView(source, 2131230752, "field 'autoListview'");
    target.autoListview = finder.castView(view, 2131230752, "field 'autoListview'");
    view = finder.findRequiredView(source, 2131230891, "field 'gengduo'");
    target.gengduo = finder.castView(view, 2131230891, "field 'gengduo'");
    view = finder.findRequiredView(source, 2131231114, "field 'tdoc'");
    target.tdoc = finder.castView(view, 2131231114, "field 'tdoc'");
    view = finder.findRequiredView(source, 2131230963, "field 'listview2'");
    target.listview2 = finder.castView(view, 2131230963, "field 'listview2'");
    view = finder.findRequiredView(source, 2131231071, "field 'sousuo2'");
    target.sousuo2 = finder.castView(view, 2131231071, "field 'sousuo2'");
  }

  @Override public void unbind(T target) {
    target.titleBar = null;
    target.autoEdit = null;
    target.searchButton = null;
    target.relativeLayout1 = null;
    target.thos = null;
    target.autoListview = null;
    target.gengduo = null;
    target.tdoc = null;
    target.listview2 = null;
    target.sousuo2 = null;
  }
}
