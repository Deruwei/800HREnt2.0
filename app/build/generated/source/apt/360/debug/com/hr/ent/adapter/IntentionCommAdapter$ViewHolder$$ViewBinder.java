// Generated code from Butter Knife. Do not modify!
package com.hr.ent.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IntentionCommAdapter$ViewHolder$$ViewBinder<T extends com.hr.ent.adapter.IntentionCommAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558947, "field 'tvIntentionName'");
    target.tvIntentionName = finder.castView(view, 2131558947, "field 'tvIntentionName'");
    view = finder.findRequiredView(source, 2131558948, "field 'tvIntentionSendtime'");
    target.tvIntentionSendtime = finder.castView(view, 2131558948, "field 'tvIntentionSendtime'");
    view = finder.findRequiredView(source, 2131558949, "field 'tvIntentionPersonmessage'");
    target.tvIntentionPersonmessage = finder.castView(view, 2131558949, "field 'tvIntentionPersonmessage'");
    view = finder.findRequiredView(source, 2131558950, "field 'tvIntentionIntentiondegree'");
    target.tvIntentionIntentiondegree = finder.castView(view, 2131558950, "field 'tvIntentionIntentiondegree'");
    view = finder.findRequiredView(source, 2131558951, "field 'tvIntentionNowjob'");
    target.tvIntentionNowjob = finder.castView(view, 2131558951, "field 'tvIntentionNowjob'");
    view = finder.findRequiredView(source, 2131558952, "field 'tvIntentionCommjob'");
    target.tvIntentionCommjob = finder.castView(view, 2131558952, "field 'tvIntentionCommjob'");
    view = finder.findRequiredView(source, 2131558939, "field 'linearInvite'");
    target.linearInvite = finder.castView(view, 2131558939, "field 'linearInvite'");
  }

  @Override public void unbind(T target) {
    target.tvIntentionName = null;
    target.tvIntentionSendtime = null;
    target.tvIntentionPersonmessage = null;
    target.tvIntentionIntentiondegree = null;
    target.tvIntentionNowjob = null;
    target.tvIntentionCommjob = null;
    target.linearInvite = null;
  }
}
