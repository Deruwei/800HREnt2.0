package com.hr.ent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.ChooseJobRefshHandler;
import com.hr.ent.model.JobInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.FileUtils;
import com.hr.ent.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChooseJobAdapter extends BaseAdapter {
    private Context context;
    private List<JobInfoBean> jobInfoBeans = new ArrayList<JobInfoBean>();
    private App app;
    private ChooseJobRefshHandler jobRefshHandler;
    private int currentPage;
    private int totalPage;
    private int totalNums;
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

    public ChooseJobAdapter(Context context) {
        this.context = context;
        app = (App) context.getApplicationContext();
        jobRefshHandler = new ChooseJobRefshHandler(context, this);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false);
            vh = new ViewHolder();
            vh.job_name = (TextView) convertView.findViewById(R.id.job_name);
            vh.job_address = (TextView) convertView.findViewById(R.id.job_address);
            vh.job_publish_time = (TextView) convertView.findViewById(R.id.job_publish_time);
            vh.tv_resumejob_resumenum = (TextView) convertView.findViewById(R.id.tv_resumejob_resumenum);
//			vh.job_refsh = (LinearLayout) convertView.findViewById(R.id.job_refsh);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final JobInfoBean jobInfoBean = getItem(position);
        vh.job_name.setText(jobInfoBean.getJob_name());

        try {
            InputStream inputStream = context.getAssets().open("city_zh.json");
            JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
//            JSONArray cityArray = FileUtils.getCityAsJSONArray(context, "city.json");
            String[] quyuString = jobInfoBean.getTypeQuYu().split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cityJSONArray.length(); i++) {
                JSONObject city = cityJSONArray.getJSONObject(i);
                for (String quyu : quyuString) {
                    if (city.has(quyu)) {
                        sb.append(city.getString(quyu)).append(" ");
                    }
                }
            }
            vh.job_address.setText(sb.toString());
        } catch (Exception e) {
            // TODO: handle exception
            vh.job_address.setText("北京市");
        }

        vh.job_publish_time.setText(DateUtils.transforMillToDate(Long.parseLong(jobInfoBean.getIssue_date()) * 1000));
        if (jobInfoBean.getIntention_info().getApply_state().equals("4")) {
            vh.tv_resumejob_resumenum.setText("申请中");
        } else if (jobInfoBean.getIntention_info().getApply_state().equals("5")) {
            vh.tv_resumejob_resumenum.setText("沟通中");
        } else {
            vh.tv_resumejob_resumenum.setText("可选择");
        }

//        vh.job_refsh.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (app.getNetworkMng().isCanConnect()) {
//                    Map<String, Object> params = new HashMap<String, Object>();
//                    params.put(HttpHelper.METHOD, HttpHelper.JOBREFSH);
//                    params.put("job_id", jobInfoBean.getJob_id());
//                    MsgHandler.sendMessage(params, jobRefshHandler,
//                            JobRefshHandler.wRefshStart);
//                } else {
//                    Toast.makeText(context, R.string.nonet, Toast.LENGTH_SHORT)
//                            .show();
//                }
//            }
//        });

//        if (selectItem == position) {
//            convertView.setBackgroundResource(R.color.yellow_resumelistitem);
//        } else {
//            convertView.setBackgroundResource(R.color.alpha);
//        }

        return convertView;
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
        TextView job_name;
        TextView job_address;
        TextView job_publish_time;
        TextView tv_resumejob_resumenum;
//        LinearLayout job_refsh;
    }
}
