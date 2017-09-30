package com.hr.ent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ChooseJobAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.ChooseJobRefshHandler;
import com.hr.ent.handler.JobRefshHandler;
import com.hr.ent.model.JobInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.LoadMoreListView.OnLoadMoreListener;
import com.hr.ent.view.WindowUtils;
import com.hr.ent.view.animaton.AlphaInAnimationAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 意向沟通选择职位
 */
public class ChooseJobActivity extends BaseActivity implements OnClickListener,
        OnItemClickListener, OnLoadMoreListener {
    private TextView title;
    private LoadMoreListView job_listview;
    private ImageView iv_choosejob_back;
    private ChooseJobAdapter adapter;
    private ChooseJobRefshHandler handler;
    private App app;
    private String user_id, resume_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_choose_job);
        init();
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setAbsListView(job_listview);
        job_listview.setAdapter(alphaInAnimationAdapter);
        job_listview.setOnItemClickListener(this);
        job_listview.setOnLoadMoreListener(this);
    }

    private void init() {
        job_listview = (LoadMoreListView) findViewById(R.id.lv_choosejob);
        iv_choosejob_back = (ImageView) findViewById(R.id.iv_choosejob_back);
        iv_choosejob_back.setOnClickListener(this);
        adapter = new ChooseJobAdapter(this);
        handler = new ChooseJobRefshHandler(this, adapter, job_listview);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        resume_id = intent.getStringExtra("resume_id");
    }

    public void initDataByNet() {
        // TODO Auto-generated method stub
        app = (App) getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(this, UMengEvent.JOBREFSH);
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(1);
            params.put("currentpage", "1");
            MsgHandler.sendMessage(params, handler, JobRefshHandler.wGetJobStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOB);
        params.put("topjob_type", "1");
        params.put("no_parent_job", "1");
        params.put("get_sub_job", "1");
        params.put("get_intention", "1");
        params.put("user_id", user_id);
        params.put("resume_id", resume_id);
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.iv_choosejob_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        if (adapter != null) {
            adapter.setSelectItem(position);
            adapter.notifyDataSetChanged();
        }
        JobInfoBean infoBean = (JobInfoBean) parent.getAdapter().getItem(position);
        if (!infoBean.getIntention_info().getApply_state().equals("4") && !infoBean.getIntention_info().getApply_state().equals("5")) {
            DoIntentionCommunicationActivity.doIntentionComm.setChoose(infoBean.getJob_name(), infoBean.getJob_id(), infoBean.getIntention_info().getR_id(),infoBean.getQuyu());
            finish();
        } else {
            Toast.makeText(context, "此职位无法发起意向沟通，请重新选择", Toast.LENGTH_SHORT).show();
        }
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
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initDataByNet();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

}