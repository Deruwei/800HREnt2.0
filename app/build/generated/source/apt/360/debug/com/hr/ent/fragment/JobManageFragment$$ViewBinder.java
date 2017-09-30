// Generated code from Butter Knife. Do not modify!
package com.hr.ent.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JobManageFragment$$ViewBinder<T extends com.hr.ent.fragment.JobManageFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558838, "field 'rlJobmagIssuenewjob' and method 'onViewClicked'");
    target.rlJobmagIssuenewjob = finder.castView(view, 2131558838, "field 'rlJobmagIssuenewjob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558840, "field 'tvJobmagIssuejob' and method 'onViewClicked'");
    target.tvJobmagIssuejob = finder.castView(view, 2131558840, "field 'tvJobmagIssuejob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558839, "field 'rlJobmagIssuejob' and method 'onViewClicked'");
    target.rlJobmagIssuejob = finder.castView(view, 2131558839, "field 'rlJobmagIssuejob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558842, "field 'tvJobmagAuditjob' and method 'onViewClicked'");
    target.tvJobmagAuditjob = finder.castView(view, 2131558842, "field 'tvJobmagAuditjob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558841, "field 'rlJobmagAuditjob' and method 'onViewClicked'");
    target.rlJobmagAuditjob = finder.castView(view, 2131558841, "field 'rlJobmagAuditjob'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558844, "field 'tvJobmagUnpulish' and method 'onViewClicked'");
    target.tvJobmagUnpulish = finder.castView(view, 2131558844, "field 'tvJobmagUnpulish'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558843, "field 'rlJobmagUnpulish' and method 'onViewClicked'");
    target.rlJobmagUnpulish = finder.castView(view, 2131558843, "field 'rlJobmagUnpulish'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558846, "field 'tvJobmagPause' and method 'onViewClicked'");
    target.tvJobmagPause = finder.castView(view, 2131558846, "field 'tvJobmagPause'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558845, "field 'rlJobmagPause' and method 'onViewClicked'");
    target.rlJobmagPause = finder.castView(view, 2131558845, "field 'rlJobmagPause'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558848, "field 'tvJobmagOffline' and method 'onViewClicked'");
    target.tvJobmagOffline = finder.castView(view, 2131558848, "field 'tvJobmagOffline'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558847, "field 'rlJobmagOffline' and method 'onViewClicked'");
    target.rlJobmagOffline = finder.castView(view, 2131558847, "field 'rlJobmagOffline'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.rlJobmagIssuenewjob = null;
    target.tvJobmagIssuejob = null;
    target.rlJobmagIssuejob = null;
    target.tvJobmagAuditjob = null;
    target.rlJobmagAuditjob = null;
    target.tvJobmagUnpulish = null;
    target.rlJobmagUnpulish = null;
    target.tvJobmagPause = null;
    target.rlJobmagPause = null;
    target.tvJobmagOffline = null;
    target.rlJobmagOffline = null;
  }
}
