// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ResumeSearchActivity$$ViewBinder<T extends com.hr.ent.ui.ResumeSearchActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558713, "field 'ivResumesearchBack' and method 'onClick'");
    target.ivResumesearchBack = finder.castView(view, 2131558713, "field 'ivResumesearchBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558714, "field 'vpFragmentresume'");
    target.vpFragmentresume = finder.castView(view, 2131558714, "field 'vpFragmentresume'");
  }

  @Override public void unbind(T target) {
    target.ivResumesearchBack = null;
    target.vpFragmentresume = null;
  }
}
