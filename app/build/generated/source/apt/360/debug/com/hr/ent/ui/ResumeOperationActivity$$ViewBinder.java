// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ResumeOperationActivity$$ViewBinder<T extends com.hr.ent.ui.ResumeOperationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558702, "field 'tvOperationBack' and method 'onClick'");
    target.tvOperationBack = finder.castView(view, 2131558702, "field 'tvOperationBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558703, "field 'tvOperationDo' and method 'onClick'");
    target.tvOperationDo = finder.castView(view, 2131558703, "field 'tvOperationDo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558704, "field 'tvOperation2'");
    target.tvOperation2 = finder.castView(view, 2131558704, "field 'tvOperation2'");
    view = finder.findRequiredView(source, 2131558706, "field 'lvOperation'");
    target.lvOperation = finder.castView(view, 2131558706, "field 'lvOperation'");
    view = finder.findRequiredView(source, 2131558705, "field 'lvOperation2'");
    target.lvOperation2 = finder.castView(view, 2131558705, "field 'lvOperation2'");
  }

  @Override public void unbind(T target) {
    target.tvOperationBack = null;
    target.tvOperationDo = null;
    target.tvOperation2 = null;
    target.lvOperation = null;
    target.lvOperation2 = null;
  }
}
