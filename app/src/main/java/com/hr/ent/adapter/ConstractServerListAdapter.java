package com.hr.ent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.ContractInfoBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Colin
 * 日期：2016/9/1 11:31
 * 邮箱：bestxt@qq.com
 */
public class ConstractServerListAdapter extends BaseAdapter {

    private ArrayList<ContractInfoBean> conList;
    private Context context;

    public ConstractServerListAdapter(ArrayList<ContractInfoBean> conList, Context context) {
        this.conList = conList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return conList.size();
    }
    @Override
    public Object getItem(int position) {
        return conList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_constract_serverlist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvServerlistName.setText(conList.get(position).getServe_name());
        viewHolder.tvServerlistType.setText(conList.get(position).getServe_type());
        viewHolder.tvServerlistServerduration.setText(conList.get(position).getServer_time());
        viewHolder.tvServerlistServertime.setText(conList.get(position).getServer_date());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.tv_serverlist_name)
        TextView tvServerlistName;
        @Bind(R.id.tv_serverlist_type)
        TextView tvServerlistType;
        @Bind(R.id.tv_serverlist_serverduration)
        TextView tvServerlistServerduration;
        @Bind(R.id.tv_serverlist_servertime)
        TextView tvServerlistServertime;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
