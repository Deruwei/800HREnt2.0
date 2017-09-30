// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InviteListActivity$$ViewBinder<T extends com.hr.ent.ui.InviteListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558612, "field 'ivInvitelistinvitelistBack' and method 'onClick'");
    target.ivInvitelistinvitelistBack = finder.castView(view, 2131558612, "field 'ivInvitelistinvitelistBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131558614, "field 'lvInvitelist'");
    target.lvInvitelist = finder.castView(view, 2131558614, "field 'lvInvitelist'");
  }

  @Override public void unbind(T target) {
    target.ivInvitelistinvitelistBack = null;
    target.lvInvitelist = null;
  }
}
