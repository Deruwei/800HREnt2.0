package com.hr.ent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.hr.ent.view.LoadMoreListView.OnLoadMoreListener;
import com.hr.ent.view.WindowUtils;
import com.hr.ent.view.animaton.AlphaInAnimationAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 主菜单职位界面
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-24
 */
public class JobActivity extends BaseActivity implements OnClickListener,
        OnItemClickListener, OnLoadMoreListener {
    private TextView title;
    private LoadMoreListView job_listview;
    private JobrefshAdapter adapter;
    private JobRefshHandler handler;
    private App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_job);
        init();
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setAbsListView(job_listview);
        job_listview.setAdapter(alphaInAnimationAdapter);
        job_listview.setOnItemClickListener(this);
        job_listview.setOnLoadMoreListener(this);
    }
    private void init() {
        findViewById(R.id.back).setVisibility(View.GONE);
        title = (TextView) findViewById(R.id.title);
        title.setText("职位刷新");
        findViewById(R.id.setting).setVisibility(View.GONE);
        findViewById(R.id.refsh_all_job).setOnClickListener(this);

        job_listview = (LoadMoreListView) findViewById(R.id.job_listview);
        adapter = new JobrefshAdapter(this,"3");
        handler = new JobRefshHandler(this, adapter, job_listview);
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
    private Map<String, Object> initParams( ) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOB);
        params.put("topjob_type", "1");
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.setting:
                break;
            case R.id.refsh_all_job:
                if (app.getNetworkMng().isCanConnect()) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(HttpHelper.METHOD, HttpHelper.JOBREFSH_ALL);
                    params.put("gettype", "1");
                    params.put("no_parent_job", "1");
                    params.put("get_sub_job", "1");
                    MsgHandler.sendMessage(params, handler, JobRefshHandler.wRefshAllStart);
                    initDataByNet();
                } else {
                    Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
                }
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
        intent.putExtra("crypt_job_id", infoBean.getCrypt_job_id());
        startActivity(intent);
    }
    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub
        if (adapter.getCurrentPage() < adapter.getTotalPage()) {
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(adapter.getCurrentPage() + 1);
            params.put("currentpage", adapter.getCurrentPage());
            MsgHandler.sendMessage(params, handler,
                    JobRefshHandler.wGetJobMoreStart);
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
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.dispatchKeyEvent(event);
    }
}