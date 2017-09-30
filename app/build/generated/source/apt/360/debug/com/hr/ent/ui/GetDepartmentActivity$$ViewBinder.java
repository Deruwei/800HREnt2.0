// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GetDepartmentActivity$$ViewBinder<T extends com.hr.ent.ui.GetDepartmentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558586, "field 'tvGetdepBack' and method 'onViewClicked'");
    target.tvGetdepBack = finder.castView(view, 2131558586, "field 'tvGetdepBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked();
        }
      });
    view = finder.findRequiredView(source, 2131558587, "field 'lvGetdep'");
    target.lvGetdep = finder.castView(view, 2131558587, "field 'lvGetdep'");
  }

  @Override public void unbind(T target) {
    target.tvGetdepBack = null;
    target.lvGetdep = null;
  }
}
