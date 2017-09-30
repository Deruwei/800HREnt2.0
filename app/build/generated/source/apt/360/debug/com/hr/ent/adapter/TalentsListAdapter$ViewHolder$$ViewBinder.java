// Generated code from Butter Knife. Do not modify!
package com.hr.ent.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TalentsListAdapter$ViewHolder$$ViewBinder<T extends com.hr.ent.adapter.TalentsListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558999, "field 'tvTalentsName'");
    target.tvTalentsName = finder.castView(view, 2131558999, "field 'tvTalentsName'");
    view = finder.findRequiredView(source, 2131559000, "field 'tvTalentsSendtime'");
    target.tvTalentsSendtime = finder.castView(view, 2131559000, "field 'tvTalentsSendtime'");
    view = finder.findRequiredView(source, 2131559001, "field 'tvTalentsPersonnum'");
    target.tvTalentsPersonnum = finder.castView(view, 2131559001, "field 'tvTalentsPersonnum'");
    view = finder.findRequiredView(source, 2131559002, "field 'tvTalentsType'");
    target.tvTalentsType = finder.castView(view, 2131559002, "field 'tvTalentsType'");
    view = finder.findRequiredView(source, 2131559003, "field 'tvTalentsDepartment'");
    target.tvTalentsDepartment = finder.castView(view, 2131559003, "field 'tvTalentsDepartment'");
    view = finder.findRequiredView(source, 2131559004, "field 'tvTalentsPlace'");
    target.tvTalentsPlace = finder.castView(view, 2131559004, "field 'tvTalentsPlace'");
    view = finder.findRequiredView(source, 2131558939, "field 'linearInvite'");
    target.linearInvite = finder.castView(view, 2131558939, "field 'linearInvite'");
  }

  @Override public void unbind(T target) {
    target.tvTalentsName = null;
    target.tvTalentsSendtime = null;
    target.tvTalentsPersonnum = null;
    target.tvTalentsType = null;
    target.tvTalentsDepartment = null;
    target.tvTalentsPlace = null;
    target.linearInvite = null;
  }
}
