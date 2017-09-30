// Generated code from Butter Knife. Do not modify!
package com.hr.ent.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ConstractServerListAdapter$ViewHolder$$ViewBinder<T extends com.hr.ent.adapter.ConstractServerListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558928, "field 'tvServerlistName'");
    target.tvServerlistName = finder.castView(view, 2131558928, "field 'tvServerlistName'");
    view = finder.findRequiredView(source, 2131558929, "field 'tvServerlistType'");
    target.tvServerlistType = finder.castView(view, 2131558929, "field 'tvServerlistType'");
    view = finder.findRequiredView(source, 2131558930, "field 'tvServerlistServerduration'");
    target.tvServerlistServerduration = finder.castView(view, 2131558930, "field 'tvServerlistServerduration'");
    view = finder.findRequiredView(source, 2131558931, "field 'tvServerlistServertime'");
    target.tvServerlistServertime = finder.castView(view, 2131558931, "field 'tvServerlistServertime'");
  }

  @Override public void unbind(T target) {
    target.tvServerlistName = null;
    target.tvServerlistType = null;
    target.tvServerlistServerduration = null;
    target.tvServerlistServertime = null;
  }
}
