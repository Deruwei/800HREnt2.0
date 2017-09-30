// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ServerInfoActivity$$ViewBinder<T extends com.hr.ent.ui.ServerInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558754, "field 'ivServerinfoBack' and method 'onClick'");
    target.ivServerinfoBack = finder.castView(view, 2131558754, "field 'ivServerinfoBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558755, "field 'lvServerinfo'");
    target.lvServerinfo = finder.castView(view, 2131558755, "field 'lvServerinfo'");
  }

  @Override public void unbind(T target) {
    target.ivServerinfoBack = null;
    target.lvServerinfo = null;
  }
}
