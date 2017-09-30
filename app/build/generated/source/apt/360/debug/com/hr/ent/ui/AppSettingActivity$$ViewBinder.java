// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AppSettingActivity$$ViewBinder<T extends com.hr.ent.ui.AppSettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558472, "field 'ivSettingappBack' and method 'onClick'");
    target.ivSettingappBack = finder.castView(view, 2131558472, "field 'ivSettingappBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558474, "field 'rlSettingappContact' and method 'onClick'");
    target.rlSettingappContact = finder.castView(view, 2131558474, "field 'rlSettingappContact'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558477, "field 'rlSettingappFeedback' and method 'onClick'");
    target.rlSettingappFeedback = finder.castView(view, 2131558477, "field 'rlSettingappFeedback'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558480, "field 'rlSettingappApk' and method 'onClick'");
    target.rlSettingappApk = finder.castView(view, 2131558480, "field 'rlSettingappApk'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558483, "field 'rlSettingappHotadv' and method 'onClick'");
    target.rlSettingappHotadv = finder.castView(view, 2131558483, "field 'rlSettingappHotadv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558486, "field 'rlSettingappBrand' and method 'onClick'");
    target.rlSettingappBrand = finder.castView(view, 2131558486, "field 'rlSettingappBrand'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558489, "field 'rlSettingappLoginout' and method 'onClick'");
    target.rlSettingappLoginout = finder.castView(view, 2131558489, "field 'rlSettingappLoginout'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.ivSettingappBack = null;
    target.rlSettingappContact = null;
    target.rlSettingappFeedback = null;
    target.rlSettingappApk = null;
    target.rlSettingappHotadv = null;
    target.rlSettingappBrand = null;
    target.rlSettingappLoginout = null;
  }
}
