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
import com.hr.ent.model.ResumeTempleInfoBean;

/**
 * 通知信标题适配器
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-12-4
 */
public class InviteTempleAdapter extends BaseAdapter {
	private Context context;
	private List<ResumeTempleInfoBean> list = new ArrayList<ResumeTempleInfoBean>();

	public InviteTempleAdapter(Context context) {
		this.context = context;
	}

	public List<ResumeTempleInfoBean> getList() {
		return list;
	}

	public void setList(List<ResumeTempleInfoBean> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public ResumeTempleInfoBean getItem(int position) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.dialog_invite_item, parent, false);
			vh = new ViewHolder();
			vh.invite_item_name = (TextView) convertView
					.findViewById(R.id.invite_item_name);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		ResumeTempleInfoBean item = getItem(position);
		vh.invite_item_name.setText(item.getName());
		return convertView;
	}
	static class ViewHolder {
		TextView invite_item_name;
	}
}
