package com.hr.ent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.ResumeInfoDetalBean;

public class ResumeInfoAdapter implements ExpandableListAdapter {
	private Context context;
	// private ResumeInfoDetalBean detalBean;
	private ArrayList<String> itemTypes;
	private ArrayList<ArrayList<String>> attrs;
	private ArrayList<ArrayList<String>> values;

	public ResumeInfoAdapter(Context context, ResumeInfoDetalBean detalBean) {
		this.context = context;
		// this.detalBean = detalBean;
	}

	// 取消先前通过registerDataSetObserver(DataSetObserver)方式注册进该适配器中的观察者对象。
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	// 注册一个观察者(observer)，当此适配器数据修改时即调用此观察者。
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	// 当组展开状态的时候此方法被调用。
	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub

	}

	// 当组收缩状态的时候此方法被调用。
	@Override
	public void onGroupCollapsed(int groupPosition) {
		// TODO Auto-generated method stub

	}

	// 如果当前适配器不包含任何数据则返回True。经常用来决定一个空视图是否应该被显示。
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	// 是否选中指定位置上的子元素。
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	// 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	// 获取显示指定组的视图对象。这个方法仅返回关于组的视图对象，
	// 要想获取子元素的视图对象，就需要调用getChildView(int, int, boolean, View,
	// ViewGroup)。
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GroupViewHolder groupVh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.resumeinfo_group_item, parent, false);
			groupVh = new GroupViewHolder();
			groupVh.item_type = (TextView) convertView
					.findViewById(R.id.resumeinfo_item_type);
			groupVh.expand_state_img = (ImageView) convertView
					.findViewById(R.id.expand_state_img);
			convertView.setTag(groupVh);
		} else {
			groupVh = (GroupViewHolder) convertView.getTag();
		}

		groupVh.item_type.setText(itemTypes.get(groupPosition));
		if (values.get(groupPosition).size() == 0) {
			// 没有子项不展开
			groupVh.expand_state_img.setImageResource(R.drawable.close);
		} else {
			if (isExpanded) {
				groupVh.expand_state_img.setImageResource(R.drawable.open);
			} else {
				groupVh.expand_state_img.setImageResource(R.drawable.close);
			}
		}

		return convertView;
	}

	// 获取指定组的ID，这个组ID必须是唯一的
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	// 获取组的个数
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return itemTypes.size();
	}

	// 　获取指定组中的数据
	@Override
	public String getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return itemTypes.get(groupPosition);
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 从列表所有项(组或子项)中获得一个唯一的子ID号。可折叠列表要求每个元素(组或子项)在所有的子元素和组中有一个唯一的ID。
	// 本方法负责根据所给的子ID号和组ID号返回唯一的ID。此外，若hasStableIds()是true，那么必须要返回稳定的ID。
	@Override
	public long getCombinedChildId(long groupId, long childId) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 获取指定组中的子元素个数
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return values.get(groupPosition).size();
	}

	// 获取一个视图对象，显示指定组中的指定子元素数据。 isLastChild 子元素是否处于组中的最后一个
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder childVh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.resumeinfo_child_item, parent, false);
			childVh = new ChildViewHolder();
			childVh.item_attr = (TextView) convertView
					.findViewById(R.id.resumeinfo_item_attr);
			childVh.item_value = (TextView) convertView
					.findViewById(R.id.resumeinfo_item_value);
			convertView.setTag(childVh);
		} else {
			childVh = (ChildViewHolder) convertView.getTag();
		}

		childVh.item_attr.setText(attrs.get(groupPosition).get(childPosition));
		childVh.item_value
				.setText(values.get(groupPosition).get(childPosition));
		return convertView;
	}

	// 获取指定组中的指定子元素ID
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	// 获取指定组中的指定子元素数据。
	// groupPosition 组位置（该组内部含有子元素）
	// childPosition 子元素位置（相对于其它子元素）
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return values.get(groupPosition).get(childPosition);
	}

	// ExpandableListAdapter里面的所有条目都可用吗？如果是yes，就意味着所有条目可以选择和点击了。
	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	static class GroupViewHolder {
		TextView item_type;
		ImageView expand_state_img;
	}

	static class ChildViewHolder {
		TextView item_attr;
		TextView item_value;
	}
}
