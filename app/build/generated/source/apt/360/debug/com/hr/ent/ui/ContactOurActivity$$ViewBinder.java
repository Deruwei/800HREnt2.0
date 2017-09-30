// Generated code from Butter Knife. Do not modify!
package com.hr.ent.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ContactOurActivity$$ViewBinder<T extends com.hr.ent.ui.ContactOurActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558565, "field 'ivContactBack' and method 'onClick'");
    target.ivContactBack = finder.castView(view, 2131558565, "field 'ivContactBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558566, "field 'tvContractOur' and method 'onClick'");
    target.tvContractOur = finder.castView(view, 2131558566, "field 'tvContractOur'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558567, "field 'tvContractPay' and method 'onClick'");
    target.tvContractPay = finder.castView(view, 2131558567, "field 'tvContractPay'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558568, "field 'linearContactOur'");
    target.linearContactOur = finder.castView(view, 2131558568, "field 'linearContactOur'");
    view = finder.findRequiredView(source, 2131558569, "field 'linearContactPay'");
    target.linearContactPay = finder.castView(view, 2131558569, "field 'linearContactPay'");
  }

  @Override public void unbind(T target) {
    target.ivContactBack = null;
    target.tvContractOur = null;
    target.tvContractPay = null;
    target.linearContactOur = null;
    target.linearContactPay = null;
  }
}
