// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TalentMessageActivity$$ViewBinder<T extends com.hr.ent.ui.TalentMessageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558764, "field 'tvTalentsmessageBack' and method 'onClick'");
    target.tvTalentsmessageBack = finder.castView(view, 2131558764, "field 'tvTalentsmessageBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558765, "field 'tvTalentsmessageTitle'");
    target.tvTalentsmessageTitle = finder.castView(view, 2131558765, "field 'tvTalentsmessageTitle'");
    view = finder.findRequiredView(source, 2131558766, "field 'lvTalentsmessage'");
    target.lvTalentsmessage = finder.castView(view, 2131558766, "field 'lvTalentsmessage'");
  }

  @Override public void unbind(T target) {
    target.tvTalentsmessageBack = null;
    target.tvTalentsmessageTitle = null;
    target.lvTalentsmessage = null;
  }
}
