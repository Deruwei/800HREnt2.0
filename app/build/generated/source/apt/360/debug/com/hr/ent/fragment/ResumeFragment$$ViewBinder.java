// Generated code from Butter Knife. Do not modify!
package com.hr.ent.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ResumeFragment$$ViewBinder<T extends com.hr.ent.fragment.ResumeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558849, "field 'tvFragmentresumeResume' and method 'onClick'");
    target.tvFragmentresumeResume = finder.castView(view, 2131558849, "field 'tvFragmentresumeResume'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558850, "field 'tvFragmentresumeJob' and method 'onClick'");
    target.tvFragmentresumeJob = finder.castView(view, 2131558850, "field 'tvFragmentresumeJob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558564, "field 'rlContactTop'");
    target.rlContactTop = finder.castView(view, 2131558564, "field 'rlContactTop'");
    view = finder.findRequiredView(source, 2131558714, "field 'vpFragmentresume'");
    target.vpFragmentresume = finder.castView(view, 2131558714, "field 'vpFragmentresume'");
  }

  @Override public void unbind(T target) {
    target.tvFragmentresumeResume = null;
    target.tvFragmentresumeJob = null;
    target.rlContactTop = null;
    target.vpFragmentresume = null;
  }
}
