package com.hr.ent.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.ent.R;

/**
 * 通知对话框的时间和分钟适配器
 * 
 * @author 800hr：yelong
 * 
 *         2015-7-1
 */
public class InviteTimeAdapter extends BaseAdapter {
	private Context context;
	private List<String> list = new ArrayList<String>();
	private int type;// 1是小时，2是分钟

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getType() {
		return type;
	}

	public InviteTimeAdapter(Context context, int type) {
		this.context = context;
		this.type = type;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.dialog_invite_item, parent, false);
			vh = new ViewHolder();
			vh.invite_item_name = (TextView) convertView
					.findViewById(R.id.invite_item_name);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		String item = getItem(position);
		vh.invite_item_name.setText(item);
		return convertView;
	}

	static class ViewHolder {
		TextView invite_item_name;
	}
}
