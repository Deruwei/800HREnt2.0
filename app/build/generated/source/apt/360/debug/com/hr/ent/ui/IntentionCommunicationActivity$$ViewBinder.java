// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IntentionCommunicationActivity$$ViewBinder<T extends com.hr.ent.ui.IntentionCommunicationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558607, "field 'tvIntentionBack' and method 'onClick'");
    target.tvIntentionBack = finder.castView(view, 2131558607, "field 'tvIntentionBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558608, "field 'tvIntentionNum'");
    target.tvIntentionNum = finder.castView(view, 2131558608, "field 'tvIntentionNum'");
    view = finder.findRequiredView(source, 2131558609, "field 'tvIntentionFiltrate' and method 'onClick'");
    target.tvIntentionFiltrate = finder.castView(view, 2131558609, "field 'tvIntentionFiltrate'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558611, "field 'lvIntention'");
    target.lvIntention = finder.castView(view, 2131558611, "field 'lvIntention'");
    view = finder.findRequiredView(source, 2131558606, "field 'rlIntentionComm'");
    target.rlIntentionComm = finder.castView(view, 2131558606, "field 'rlIntentionComm'");
  }

  @Override public void unbind(T target) {
    target.tvIntentionBack = null;
    target.tvIntentionNum = null;
    target.tvIntentionFiltrate = null;
    target.lvIntention = null;
    target.rlIntentionComm = null;
  }
}
