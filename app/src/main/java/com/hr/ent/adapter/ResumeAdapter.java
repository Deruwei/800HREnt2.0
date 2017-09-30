package com.hr.ent.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.utils.CityNameConvertCityID;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.FileUtils;
import com.hr.ent.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ResumeAdapter extends BaseAdapter {
    private Context context;
    private List<ResumeNum> nums = new ArrayList<ResumeNum>();
    private int currentPage;
    private int totalPage;

    public ResumeAdapter(Context context) {
        this.context = context;
    }

    public void add(List<ResumeNum> nums) {
        this.nums.addAll(nums);
    }

    public List<ResumeNum> getNums() {
        return nums;
    }

    public void setNums(List<ResumeNum> nums) {
        this.nums = nums;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return nums.size();
    }

    @Override
    public ResumeNum getItem(int position) {
        // TODO Auto-generated method stub
        return nums.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.resume_item, parent, false);
            holder.tv_resumejob_jobname = (TextView) convertView.findViewById(R.id.tv_resumejob_jobname);
            holder.tv_resumejob_jobplace = (TextView) convertView.findViewById(R.id.tv_resumejob_jobplace);
            holder.tv_resumejob_num = (TextView) convertView.findViewById(R.id.tv_resumejob_num);
            holder.tv_resumejob_time = (TextView) convertView.findViewById(R.id.tv_resumejob_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResumeNum num = getItem(position);
        if (num.getTypeName() != null && !num.getTypeName().equals("")) {
            holder.tv_resumejob_jobname.setText(num.getTypeName());
            if (num.getTypeNum() != null) {
                if (num.getTypeNum().equals("")) {
                    holder.tv_resumejob_num.setText("历史应聘数 0");
                } else {
                    holder.tv_resumejob_num.setText("历史应聘数 "+num.getTypeNum());
                }
            } else {
            }
            if (num.getTypeDate() != null && !num.getTypeDate().equals("")) {
                holder.tv_resumejob_time.setText(DateUtils.transforMillToDate2(Long.parseLong(num.getTypeDate()) * 1000) + "");
//                        TimeStampUtils.getDateTimeByMillisecond(num.getTypeDate().trim(),"yyyy-MM-dd")+"");
            }
            try {
                InputStream inputStream = context.getAssets().open("city_zh.json");
                JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
//            JSONArray cityArray = FileUtils.getCityAsJSONArray(context, "city.json");
                String[] quyuString = num.getTypeQuYu().split(",");
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < cityJSONArray.length(); i++) {
                    JSONObject city = cityJSONArray.getJSONObject(i);
                    for (String quyu : quyuString) {
                        if (city.has(quyu)) {
                            sb.append(city.getString(quyu)).append(" ");
                        }
                    }
                }
                holder.tv_resumejob_jobplace.setText(sb.toString());
            } catch (Exception e) {
                // TODO: handle exception
                holder.tv_resumejob_jobplace.setText("北京市");
            }
//                holder.tv_resumejob_jobplace.setText(CityNameConvertCityID.convertCityName(context, num.getTypeQuYu()) + "");
            if (num.getUnread() != null) {
                if (num.getUnread().equals("0")) {
                    holder.tv_resumejob_num.setTextColor(Color.parseColor("#999999"));
                } else {
                    holder.tv_resumejob_num.setTextColor(Color.parseColor("#F39D0D"));
                }
            }
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_resumejob_jobname, tv_resumejob_jobplace, tv_resumejob_num, tv_resumejob_time;
    }
}
