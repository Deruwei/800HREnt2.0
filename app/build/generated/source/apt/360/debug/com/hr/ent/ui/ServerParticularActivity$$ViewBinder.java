// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ServerParticularActivity$$ViewBinder<T extends com.hr.ent.ui.ServerParticularActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558756, "field 'ivServerparticularBack' and method 'onClick'");
    target.ivServerparticularBack = finder.castView(view, 2131558756, "field 'ivServerparticularBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131558757, "field 'tvServerparticularContractnum'");
    target.tvServerparticularContractnum = finder.castView(view, 2131558757, "field 'tvServerparticularContractnum'");
    view = finder.findRequiredView(source, 2131558758, "field 'tvServerparticularContractpeople'");
    target.tvServerparticularContractpeople = finder.castView(view, 2131558758, "field 'tvServerparticularContractpeople'");
    view = finder.findRequiredView(source, 2131558759, "field 'tvServerparticularContracttime'");
    target.tvServerparticularContracttime = finder.castView(view, 2131558759, "field 'tvServerparticularContracttime'");
    view = finder.findRequiredView(source, 2131558760, "field 'tvServerparticularValidtime'");
    target.tvServerparticularValidtime = finder.castView(view, 2131558760, "field 'tvServerparticularValidtime'");
    view = finder.findRequiredView(source, 2131558761, "field 'lvServerparticular'");
    target.lvServerparticular = finder.castView(view, 2131558761, "field 'lvServerparticular'");
  }

  @Override public void unbind(T target) {
    target.ivServerparticularBack = null;
    target.tvServerparticularContractnum = null;
    target.tvServerparticularContractpeople = null;
    target.tvServerparticularContracttime = null;
    target.tvServerparticularValidtime = null;
    target.lvServerparticular = null;
  }
}
