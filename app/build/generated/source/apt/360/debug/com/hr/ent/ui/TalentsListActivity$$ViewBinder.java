// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TalentsListActivity$$ViewBinder<T extends com.hr.ent.ui.TalentsListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558768, "field 'tvTalentsBack' and method 'onClick'");
    target.tvTalentsBack = finder.castView(view, 2131558768, "field 'tvTalentsBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558770, "field 'tvTalentsEmpty'");
    target.tvTalentsEmpty = finder.castView(view, 2131558770, "field 'tvTalentsEmpty'");
    view = finder.findRequiredView(source, 2131558771, "field 'lvTalents'");
    target.lvTalents = finder.castView(view, 2131558771, "field 'lvTalents'");
  }

  @Override public void unbind(T target) {
    target.tvTalentsBack = null;
    target.tvTalentsEmpty = null;
    target.lvTalents = null;
  }
}
