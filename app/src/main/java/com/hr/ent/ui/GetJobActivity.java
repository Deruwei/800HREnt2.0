package com.hr.ent.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.JobrefshAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.JobRefshHandler;
import com.hr.ent.model.JobInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.WindowUtils;
import com.hr.ent.view.animaton.AlphaInAnimationAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class GetJobActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, LoadMoreListView.OnLoadMoreListener {
    private LoadMoreListView job_listview;
    private JobrefshAdapter adapter;
    private JobRefshHandler handler;
    private App app;
    public static GetJobActivity getJobActivity;
    private String gettype;
    private TextView tv_getJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_get_job);
        getJobActivity = GetJobActivity.this;
        init();
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setAbsListView(job_listview);
        job_listview.setAdapter(alphaInAnimationAdapter);
        job_listview.setOnItemClickListener(this);
        job_listview.setOnLoadMoreListener(this);
        job_listview.setEmptyView(findViewById(R.id.tv_job_empty));
    }


    private void init() {
        tv_getJob= (TextView) findViewById(R.id.tv_getJobTitle);
        findViewById(R.id.refsh_all_job).setOnClickListener(this);
        findViewById(R.id.iv_getJobBack).setOnClickListener(this);
        gettype = getIntent().getStringExtra("gettype");
        job_listview = (LoadMoreListView) findViewById(R.id.job_listview);
        adapter = new JobrefshAdapter(this,gettype);
        handler = new JobRefshHandler(this, adapter, job_listview);
        if (("5").equals(gettype)) {
            tv_getJob.setText(R.string.offlineJob);
        }else if("9".equals(gettype)){
            tv_getJob.setText(R.string.auditJob);
        }else if("1".equals(gettype)){
            tv_getJob.setText(R.string.issueJob);
        }else if("2".equals(gettype)){
            tv_getJob.setText(R.string.unPublic);
        }else if("3".equals(gettype)){
            tv_getJob.setText(R.string.pauseJob);
        }
    }

    public void initDataByNet() {
        // TODO Auto-generated method stub
        app = (App) this.getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(this, UMengEvent.JOBREFSH);
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(1);
            params.put("currentpage", "1");
            MsgHandler.sendMessage(params, handler,
                    JobRefshHandler.wGetJobStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOB);
        params.put("gettype", gettype);
        params.put("topjob_type", "1");
//        params.put("no_parent_job", "1");
        params.put("get_sub_job", "0");
        params.put("get_job_resume", "1");
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.refsh_all_job:
                if (app.getNetworkMng().isCanConnect()) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(HttpHelper.METHOD, HttpHelper.JOBREFSH_ALL);
                    MsgHandler.sendMessage(params, handler, JobRefshHandler.wRefshAllStart);
                    initDataByNet();
                } else {
                    Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_getJobBack:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        if (adapter != null) {
            adapter.setSelectItem(position);
            adapter.notifyDataSetChanged();
        }
        JobInfoBean infoBean = (JobInfoBean) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, JobInfoActivity.class);
        intent.putExtra("job_id", infoBean.getJob_id());
        intent.putExtra("job_name", infoBean.getJob_name());
        intent.putExtra("crypt_job_id", infoBean.getCrypt_job_id());
        intent.putExtra("gettype", gettype);
        this.startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub
        if (adapter.getCurrentPage() < adapter.getTotalPage()) {
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(adapter.getCurrentPage() + 1);
            params.put("currentpage", adapter.getCurrentPage());
            MsgHandler.sendMessage(params, handler, JobRefshHandler.wGetJobMoreStart);
        } else {

            job_listview.onLoadMoreComplete();
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initDataByNet();
    }
}
