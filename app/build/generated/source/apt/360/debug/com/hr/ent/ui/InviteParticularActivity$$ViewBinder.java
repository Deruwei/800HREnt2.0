// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InviteParticularActivity$$ViewBinder<T extends com.hr.ent.ui.InviteParticularActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558615, "field 'ivInviteparticularBack' and method 'onClick'");
    target.ivInviteparticularBack = finder.castView(view, 2131558615, "field 'ivInviteparticularBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558616, "field 'tvInviteparticularEmail' and method 'onClick'");
    target.tvInviteparticularEmail = finder.castView(view, 2131558616, "field 'tvInviteparticularEmail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558617, "field 'tvInviteparticularMessage' and method 'onClick'");
    target.tvInviteparticularMessage = finder.castView(view, 2131558617, "field 'tvInviteparticularMessage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558618, "field 'tvInviteparticularResume' and method 'onClick'");
    target.tvInviteparticularResume = finder.castView(view, 2131558618, "field 'tvInviteparticularResume'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558619, "field 'vpInviteparticular'");
    target.vpInviteparticular = finder.castView(view, 2131558619, "field 'vpInviteparticular'");
  }

  @Override public void unbind(T target) {
    target.ivInviteparticularBack = null;
    target.tvInviteparticularEmail = null;
    target.tvInviteparticularMessage = null;
    target.tvInviteparticularResume = null;
    target.vpInviteparticular = null;
  }
}
