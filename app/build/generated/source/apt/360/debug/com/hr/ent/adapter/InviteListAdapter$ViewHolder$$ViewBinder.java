// Generated code from Butter Knife. Do not modify!
package com.hr.ent.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InviteListAdapter$ViewHolder$$ViewBinder<T extends com.hr.ent.adapter.InviteListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558953, "field 'tvInvitelistName'");
    target.tvInvitelistName = finder.castView(view, 2131558953, "field 'tvInvitelistName'");
    view = finder.findRequiredView(source, 2131558954, "field 'tvInvitelistSendtime'");
    target.tvInvitelistSendtime = finder.castView(view, 2131558954, "field 'tvInvitelistSendtime'");
    view = finder.findRequiredView(source, 2131558955, "field 'ivInvitelistEmail'");
    target.ivInvitelistEmail = finder.castView(view, 2131558955, "field 'ivInvitelistEmail'");
    view = finder.findRequiredView(source, 2131558956, "field 'ivInvitelistPhone'");
    target.ivInvitelistPhone = finder.castView(view, 2131558956, "field 'ivInvitelistPhone'");
    view = finder.findRequiredView(source, 2131558957, "field 'tvInvitelistJob'");
    target.tvInvitelistJob = finder.castView(view, 2131558957, "field 'tvInvitelistJob'");
    view = finder.findRequiredView(source, 2131558958, "field 'tvInvitelistInvitetime'");
    target.tvInvitelistInvitetime = finder.castView(view, 2131558958, "field 'tvInvitelistInvitetime'");
    view = finder.findRequiredView(source, 2131558939, "field 'linearInvite'");
    target.linearInvite = finder.castView(view, 2131558939, "field 'linearInvite'");
  }

  @Override public void unbind(T target) {
    target.tvInvitelistName = null;
    target.tvInvitelistSendtime = null;
    target.ivInvitelistEmail = null;
    target.ivInvitelistPhone = null;
    target.tvInvitelistJob = null;
    target.tvInvitelistInvitetime = null;
    target.linearInvite = null;
  }
}
