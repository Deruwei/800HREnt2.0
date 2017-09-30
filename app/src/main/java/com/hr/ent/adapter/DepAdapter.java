package com.hr.ent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.DepListBean;

import java.util.ArrayList;

public class DepAdapter extends BaseAdapter {

    private ArrayList<DepListBean.ReturnDataBean> list = new ArrayList<>();
    private Context context;

    public DepAdapter(Context context, ArrayList<DepListBean.ReturnDataBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_simple_lv, parent, false);
            vh = new ViewHolder();
            vh.tvItemSimple = (TextView) convertView.findViewById(R.id.tv_item_simple);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvItemSimple.setText(list.get(position).getDname());
        return convertView;
    }

    static class ViewHolder {
        TextView tvItemSimple;
    }
}