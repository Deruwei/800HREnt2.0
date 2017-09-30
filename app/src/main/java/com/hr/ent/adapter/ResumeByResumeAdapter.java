package com.hr.ent.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.ui.ResumeScanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Colin
 * 日期：2016/8/29 14:54
 * 邮箱：bestxt@qq.com
 */
public class ResumeByResumeAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> listBoxName;
    public static List<ResumeNum> listResume2;
    public static List<ResumeNum> listResume3;
    public static List<ResumeNum> listResume4;
    private ExpandableListView listView;

    public ResumeByResumeAdapter(Context context, ExpandableListView listView) {
        this.context = context;
        this.listView = listView;
        initData();
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//                super.handleMessage(msg);
//            }
//        };
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for (int i = 0; i < this.getGroupCount(); i++) {
            listView.collapseGroup(i);
        }
        for (int i = 0; i < this.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }

    private void initData() {
        listBoxName = new ArrayList<>();
        listResume2 = new ArrayList<ResumeNum>();
        listResume3 = new ArrayList<ResumeNum>();
        listResume4 = new ArrayList<ResumeNum>();
        listBoxName.add("简历库");
        listBoxName.add("应聘简历库");
        listBoxName.add("公司人才库");
    }

    public static void setData(List<ResumeNum> listResume22, List<ResumeNum> listResume33, List<ResumeNum> listResume44) {
        listResume2.clear();
        listResume2.addAll(listResume22);
        listResume3.clear();
        listResume3.addAll(listResume33);
        listResume4.clear();
//        listResume4.addAll(listResume44);
        for (int i = 0; i < listResume44.size(); i++) {
            if (!listResume44.get(i).getTypeName().equals("已屏蔽简历")) {
                listResume4.add(listResume44.get(i));
            }
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return listBoxName.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return listResume2.size();
        } else if (groupPosition == 1) {
            return listResume3.size();
        } else {
            return listResume4.size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listBoxName.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupPosition == 0) {
            return listResume2.get(childPosition);
        } else if (groupPosition == 1) {
            return listResume3.get(childPosition);
        } else {
            return listResume4.get(childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ParentViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_byresume, null);
            viewHolder.tv_byresume_name = (TextView) convertView.findViewById(R.id.tv_byresume_name);
            viewHolder.iv_byresume_jiantou = (ImageView) convertView.findViewById(R.id.iv_byresume_jiantou);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ParentViewHolder) convertView.getTag();
        }
        viewHolder.tv_byresume_name.setText(listBoxName.get(groupPosition));
        if (isExpanded) {
            viewHolder.iv_byresume_jiantou.setBackgroundResource(R.mipmap.zhankai2);
        } else {
            viewHolder.iv_byresume_jiantou.setBackgroundResource(R.mipmap.zhankai);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_byresume_child, null);
            viewHolder.tv_byresume_namechild = (TextView) convertView.findViewById(R.id.tv_byresume_namechild);
            viewHolder.tv_byresume_numchild = (TextView) convertView.findViewById(R.id.tv_byresume_numchild);
            viewHolder.view_byresume_item = (View) convertView.findViewById(R.id.view_byresume_item);
            viewHolder.rel_byresume_item = (RelativeLayout) convertView.findViewById(R.id.rel_byresume_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        if (groupPosition == 0) {
            viewHolder.tv_byresume_namechild.setText(listResume2.get(childPosition).getTypeName());
            viewHolder.tv_byresume_numchild.setText(listResume2.get(childPosition).getTypeNum());
            if (childPosition == 1) {
                viewHolder.view_byresume_item.setVisibility(View.VISIBLE);
            } else {
                viewHolder.view_byresume_item.setVisibility(View.GONE);
            }
            viewHolder.rel_byresume_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goResume(listResume2.get(childPosition));
                }
            });
            if (listResume2.get(childPosition).getUnread() != null) {
                if (listResume2.get(childPosition).getUnread().equals("0")) {
                    viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#666666"));
                } else {
                    viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#F7931E"));
                }
            }

        } else if (groupPosition == 1) {
            viewHolder.tv_byresume_namechild.setText(listResume3.get(childPosition).getTypeName());
            viewHolder.tv_byresume_numchild.setText(listResume3.get(childPosition).getTypeNum());
            if (childPosition == 4) {
                viewHolder.view_byresume_item.setVisibility(View.VISIBLE);
            } else {
                viewHolder.view_byresume_item.setVisibility(View.GONE);
            }
            viewHolder.rel_byresume_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goResume(listResume3.get(childPosition));
                }
            });
            if (listResume3.get(childPosition).getUnread() != null) {
                if (listResume3.get(childPosition).getUnread().equals("0")) {
                    viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#666666"));
                } else {
                    viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#F7931E"));
                }
            }
        } else {
            if (!listResume4.get(childPosition).getTypeName().equals("已屏蔽简历")) {
                viewHolder.tv_byresume_namechild.setText(listResume4.get(childPosition).getTypeName());
                viewHolder.tv_byresume_numchild.setText(listResume4.get(childPosition).getTypeNum());
                viewHolder.rel_byresume_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goResume(listResume4.get(childPosition));
                    }
                });
                viewHolder.view_byresume_item.setVisibility(View.GONE);
                if (listResume4.get(childPosition).getUnread() != null) {
                    if (listResume4.get(childPosition).getUnread().equals("0")) {
                        viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#666666"));
                    } else {
                        viewHolder.tv_byresume_numchild.setTextColor(Color.parseColor("#F7931E"));
                    }
                }
            }

        }

        return convertView;
    }

    private void goResume(ResumeNum num) {
        if (num.getTypeNum() != null && !num.getTypeNum().equals("0") && !num.getTypeNum().equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString("type", num.getType());
            bundle.putString("typeID", num.getTypeID());
            bundle.putString("boxtype", num.getSortID() + "");
            bundle.putString("typeName", num.getTypeName());
            Intent intent = new Intent(context, ResumeScanActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "暂无简历", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    class ParentViewHolder {
        TextView tv_byresume_name;
        ImageView iv_byresume_jiantou;
    }

    class ChildViewHolder {
        TextView tv_byresume_namechild, tv_byresume_numchild;
        RelativeLayout rel_byresume_item;
        View view_byresume_item;
    }

}
