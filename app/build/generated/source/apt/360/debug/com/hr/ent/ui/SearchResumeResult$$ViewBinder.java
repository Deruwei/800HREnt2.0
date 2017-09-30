// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SearchResumeResult$$ViewBinder<T extends com.hr.ent.ui.SearchResumeResult> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558735, "field 'ivResumeresultBack' and method 'onClick'");
    target.ivResumeresultBack = finder.castView(view, 2131558735, "field 'ivResumeresultBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558737, "field 'lvResumesearch'");
    target.lvResumesearch = finder.castView(view, 2131558737, "field 'lvResumesearch'");
    view = finder.findRequiredView(source, 2131558736, "field 'tvResumesearchEmpty'");
    target.tvResumesearchEmpty = finder.castView(view, 2131558736, "field 'tvResumesearchEmpty'");
  }

  @Override public void unbind(T target) {
    target.ivResumeresultBack = null;
    target.lvResumesearch = null;
    target.tvResumesearchEmpty = null;
  }
}
