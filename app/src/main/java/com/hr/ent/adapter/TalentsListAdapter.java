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
import com.hr.ent.model.SelectBean;
import com.hr.ent.model.TalentsListBean;
import com.hr.ent.ui.CityUtils;
import com.hr.ent.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Colin
 * 日期：2016/9/2 17:12
 * 邮箱：bestxt@qq.com
 */
public class TalentsListAdapter extends BaseAdapter {
    private ArrayList<TalentsListBean.ListBean> list;

    private Context context;
    private int currentPage;
    private int totalPage;
    private int totalNums;
    private int selectItem;
    private List<SelectBean> selectBeanList;

    public void setSelectBeanList(List<SelectBean> selectBeanList) {
        this.selectBeanList = selectBeanList;
    }

    public TalentsListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<TalentsListBean.ListBean>();
    }

    public void addList(ArrayList<TalentsListBean.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_talents_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TalentsListBean.ListBean talentsListBean2 = list.get(position);
        viewHolder.tvTalentsName.setText(talentsListBean2.getJob_name());
        viewHolder.tvTalentsDepartment.setText(talentsListBean2.getDepartment());
        viewHolder.tvTalentsSendtime.setText(DateUtils.transforMillToDate(Long.parseLong(talentsListBean2.getAdd_time()) * 1000));
        viewHolder.tvTalentsPersonnum.setText("候选人数 "+talentsListBean2.getResume_totals());
        viewHolder.tvTalentsPlace.setText(CityUtils.getCityName(talentsListBean2.getArea(),context));
        for(int i=0;i<selectBeanList.size();i++){
            if(talentsListBean2.getXunfang_state().equals(selectBeanList.get(i).getId())){
                viewHolder.tvTalentsType.setText(selectBeanList.get(i).getName());
            }
        }
        return convertView;
    }

    public void setList(ArrayList<TalentsListBean.ListBean> list) {
        this.list = list;
    }

    static class ViewHolder {
        @Bind(R.id.tv_talents_name)
        TextView tvTalentsName;
        @Bind(R.id.tv_talents_sendtime)
        TextView tvTalentsSendtime;
        @Bind(R.id.tv_talents_personnum)
        TextView tvTalentsPersonnum;
        @Bind(R.id.tv_talents_type)
        TextView tvTalentsType;
        @Bind(R.id.tv_talents_department)
        TextView tvTalentsDepartment;
        @Bind(R.id.tv_talents_place)
        TextView tvTalentsPlace;
        @Bind(R.id.linear_invite)
        LinearLayout linearInvite;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
