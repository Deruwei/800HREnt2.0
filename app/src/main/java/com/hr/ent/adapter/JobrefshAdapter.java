package com.hr.ent.adapter;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.JobRefshHandler;
import com.hr.ent.model.JobInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.FileUtils;
import com.hr.ent.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JobrefshAdapter extends BaseAdapter {
    private Context context;
    private List<JobInfoBean> jobInfoBeans = new ArrayList<JobInfoBean>();
    private App app;
    private JobRefshHandler jobRefshHandler;
    private int currentPage;
    private int totalPage;
    private int totalNums;
    private String jobType;

    private int selectItem;

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

    public JobrefshAdapter(Context context,String jobType) {
        this.context = context;
        this.jobType=jobType;
        app = (App) context.getApplicationContext();
        jobRefshHandler = new JobRefshHandler(context, this);
    }

    public List<JobInfoBean> getJobInfoBeans() {
        return jobInfoBeans;
    }

    public void setJobInfoBeans(List<JobInfoBean> jobInfoBeans) {
        this.jobInfoBeans = jobInfoBeans;
    }

    public void addJobInfoBeans(List<JobInfoBean> jobInfoBeans) {
        this.jobInfoBeans.addAll(jobInfoBeans);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return jobInfoBeans.size();
    }

    @Override
    public JobInfoBean getItem(int position) {
        // TODO Auto-generated method stub
        return jobInfoBeans.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_getjob, parent, false);
            vh = new ViewHolder();
            vh.tv_job_name = (TextView) convertView.findViewById(R.id.tv_job_name);
            vh.tv_job_address = (TextView) convertView.findViewById(R.id.tv_job_address);
            vh.tv_job_issue_date = (TextView) convertView.findViewById(R.id.tv_job_issue_date);
            vh.tv_job_resumenum = (TextView) convertView.findViewById(R.id.tv_job_resumenum);
			vh.tv_job_expiry = (TextView) convertView.findViewById(R.id.tv_job_expiry);
            vh.rl_jobTime= (LinearLayout) convertView.findViewById(R.id.ll_jobTime);
            vh.tvScanNum= (TextView) convertView.findViewById(R.id.tv_job_scanNum);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final JobInfoBean jobInfoBean = getItem(position);
        vh.tv_job_name.setText(jobInfoBean.getJob_name());
        if("2".equals(jobType)){
            vh.rl_jobTime.setVisibility(View.GONE);
        }else{
            vh.rl_jobTime.setVisibility(View.VISIBLE);
        }
        try {
            InputStream inputStream = context.getAssets().open("city_zh.json");
            JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
//            JSONArray cityArray = FileUtils.getCityAsJSONArray(context, "city.json");
            String[] quyuString = jobInfoBean.getQuyu().split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cityJSONArray.length(); i++) {
                JSONObject city = cityJSONArray.getJSONObject(i);
                for (String quyu : quyuString) {
                    if (city.has(quyu)) {
                        sb.append(city.getString(quyu)).append(" ");
                    }
                }
            }
            vh.tv_job_address.setText(sb.toString());
        } catch (Exception e) {
            vh.tv_job_address.setText("北京市");
        }

        vh.tv_job_issue_date.setText(DateUtils.transforMillToDate(Long.parseLong(jobInfoBean.getIssue_date()) * 1000));
        vh.tv_job_resumenum.setText(jobInfoBean.getJob_resume());
       vh.tvScanNum.setText(jobInfoBean.getCounts());
        vh.tv_job_expiry.setText(DateUtils.transforMillToDate(Long.parseLong(jobInfoBean.getExpiry_date()) * 1000)+" - "+addDate(DateUtils.transforMillToDate(Long.parseLong(jobInfoBean.getExpiry_date()) * 1000),Long.parseLong(jobInfoBean.getEffect_time())));
        return convertView;
    }
    public static String addDate(String s,long day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd"); // 日期格式
        Date date = null; // 指定日期
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        Date date1=new Date(time);
        String str=dateFormat.format(date1);
        return str; // 将毫秒数转换成日期
    }
    public void updateJobDate(String jobid) {
        for (JobInfoBean infoBean : jobInfoBeans) {
            if (infoBean.getJob_id().equals(jobid)) {
                infoBean.setIssue_date(String.valueOf(System.currentTimeMillis() / 1000));
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView tvScanNum;
        TextView tv_job_name;
        TextView tv_job_address;
        TextView tv_job_issue_date;
        TextView tv_job_resumenum;
        TextView tv_job_expiry;
        LinearLayout rl_jobTime;
//        LinearLayout job_refsh;
    }
}
