package com.hr.ent.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.InviteInfoBean;
import com.hr.ent.ui.InviteParticularActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Colin
 * 日期：2016/9/1 14:55
 * 邮箱：bestxt@qq.com
 */
public class InviteListAdapter extends BaseAdapter {
    private ArrayList<InviteInfoBean> list;
    private Context context;
    private int currentPage;
    private int totalPage;
    private int totalNums;
    private int selectItem;

    public InviteListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<InviteInfoBean>();
    }

    public void setList(ArrayList<InviteInfoBean> list) {
        this.list = list;
    }

    public void addList(ArrayList<InviteInfoBean> list) {
        this.list.addAll(list);
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_invitelist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Toast.makeText(context,list.get(position).getAdd_time(),Toast.LENGTH_SHORT).show();
        if (list.get(position) != null) {

            final InviteInfoBean inviteInfoBean = list.get(position);
            viewHolder.tvInvitelistSendtime.setText(DateUtils.transforMillToDate(Long.parseLong(inviteInfoBean.getAdd_time()) * 1000));
            viewHolder.tvInvitelistName.setText(inviteInfoBean.getUser_name());
            viewHolder.tvInvitelistInvitetime.setText(DateUtils.transforMillToDate(Long.parseLong(inviteInfoBean.getDestine_time()) * 1000));
            viewHolder.tvInvitelistJob.setText(inviteInfoBean.getJob_name());
            if (inviteInfoBean.getIs_email().equals("1")) {
                viewHolder.ivInvitelistEmail.setVisibility(View.VISIBLE);
            } else {
                viewHolder.ivInvitelistEmail.setVisibility(View.GONE);
            }
//            else if (inviteInfoBean.getIs_email().equals("2")) {
//                viewHolder.ivInvitelistEmail.setVisibility(View.GONE);
//            }
            if (inviteInfoBean.getIs_sms().equals("1") || inviteInfoBean.getIs_sms().equals("2")) {
                viewHolder.ivInvitelistPhone.setVisibility(View.VISIBLE);
            } else {

//            } else if (inviteInfoBean.getIs_sms().equals("3")) {
//                viewHolder.ivInvitelistPhone.setVisibility(View.GONE);
//            } else {
                viewHolder.ivInvitelistPhone.setVisibility(View.GONE);
            }
            viewHolder.linearInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InviteParticularActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("position", position + "");
                    mBundle.putParcelable(Const.PAR_KEY, inviteInfoBean);
                    intent.putExtras(mBundle);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_invitelist_name)
        TextView tvInvitelistName;
        @Bind(R.id.tv_invitelist_sendtime)
        TextView tvInvitelistSendtime;
        @Bind(R.id.iv_invitelist_email)
        ImageView ivInvitelistEmail;
        @Bind(R.id.iv_invitelist_phone)
        ImageView ivInvitelistPhone;
        @Bind(R.id.tv_invitelist_job)
        TextView tvInvitelistJob;
        @Bind(R.id.tv_invitelist_invitetime)
        TextView tvInvitelistInvitetime;
        @Bind(R.id.linear_invite)
        LinearLayout linearInvite;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
