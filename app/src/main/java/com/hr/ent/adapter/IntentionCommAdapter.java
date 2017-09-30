package com.hr.ent.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.IntentionInfoBean2;
import com.hr.ent.ui.ResumeParticularInviteActivity;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.ResumeInfoIDToString;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Colin
 * 日期：2016/9/2 17:12
 * 邮箱：bestxt@qq.com
 */
public class IntentionCommAdapter extends BaseAdapter {
    private ArrayList<IntentionInfoBean2> list;

    private Context context;
    private int currentPage;
    private int totalPage;
    private int totalNums;
    private int selectItem;

    public IntentionCommAdapter(Context context) {
        this.context = context;
        list = new ArrayList<IntentionInfoBean2>();
    }

    public void addList(ArrayList<IntentionInfoBean2> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_intention_communication, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final IntentionInfoBean2 intentionInfoBean = list.get(position);
        viewHolder.tvIntentionName.setText(intentionInfoBean.getUser_name());
//        viewHolder.tvIntentionNowjob.setText(ResumeInfoIDToString.getFunc(context, list.get(position).getIndustry(), list.get(position).getLast_position()));
        viewHolder.tvIntentionNowjob.setText(intentionInfoBean.getLast_position());
        viewHolder.tvIntentionCommjob.setText(intentionInfoBean.getJob_name());
        switch (intentionInfoBean.getIntention_state()) {
            case "0":
//              0沟通中  1无效,2无,3低,4中,5高
                viewHolder.tvIntentionIntentiondegree.setText("沟通中");
                break;
            case "1":
                viewHolder.tvIntentionIntentiondegree.setText("无效");
                break;
            case "2":
                viewHolder.tvIntentionIntentiondegree.setText("无");
                break;
            case "3":
                viewHolder.tvIntentionIntentiondegree.setText("低");
                break;
            case "4":
                viewHolder.tvIntentionIntentiondegree.setText("中");
                break;
            case "5":
                viewHolder.tvIntentionIntentiondegree.setText("高");
                break;
        }
        viewHolder.tvIntentionSendtime.setText(DateUtils.transforMillToDate(Long.parseLong(list.get(position).getApply_time()) * 1000));
        int age = DateUtils.getYearDate() - Integer.parseInt(list.get(position).getUser_year());
        viewHolder.tvIntentionPersonmessage.setText((list.get(position).getUser_sex().equals("1") ? "男" : "女") + " " + age + "岁 " + ResumeInfoIDToString.getEducationDegree(context, list.get(position).getHigh_education(), true));
        viewHolder.linearInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResumeParticularInviteActivity.class);
                intent.putExtra("resume_id", intentionInfoBean.getResume_id());
                intent.putExtra("userid", intentionInfoBean.getUser_id());
                intent.putExtra("resume_language", intentionInfoBean.getResume_language());
                intent.putExtra("userName", intentionInfoBean.getUser_name());
                intent.putExtra("resume_from", intentionInfoBean.getResume_from());
                intent.putExtra("apply_state", intentionInfoBean.getApply_state());
                intent.putExtra("intention_state", intentionInfoBean.getIntention_state());
                intent.putExtra("job_id", intentionInfoBean.getJob_id());
                intent.putExtra("job_name", intentionInfoBean.getJob_name());
                intent.putExtra("id", intentionInfoBean.getR_id());
                intent.putExtra("apply_state", intentionInfoBean.getApply_state());
                intent.putExtra("type", "10");
//                intent.putExtra("resume_from", intentionInfoBean.getResume_from());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setList(ArrayList<IntentionInfoBean2> list) {
        this.list = list;
    }


    static class ViewHolder {
        @Bind(R.id.tv_intention_name)
        TextView tvIntentionName;
        @Bind(R.id.tv_intention_sendtime)
        TextView tvIntentionSendtime;
        @Bind(R.id.tv_intention_personmessage)
        TextView tvIntentionPersonmessage;
        @Bind(R.id.tv_intention_intentiondegree)
        TextView tvIntentionIntentiondegree;
        @Bind(R.id.tv_intention_nowjob)
        TextView tvIntentionNowjob;
        @Bind(R.id.tv_intention_commjob)
        TextView tvIntentionCommjob;
        @Bind(R.id.linear_invite)
        LinearLayout linearInvite;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
