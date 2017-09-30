package com.hr.ent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.JobrefshAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.JobRefshHandler;
import com.hr.ent.model.JobInfoBean;
import com.hr.ent.ui.JobInfoActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.LoadMoreListView.OnLoadMoreListener;
import com.hr.ent.view.animaton.AlphaInAnimationAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 主菜单职位界面
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-24
 */
public class JobFragment extends Fragment implements OnClickListener,OnItemClickListener,OnLoadMoreListener {
    private LoadMoreListView job_listview;
    private JobrefshAdapter adapter;
    private JobRefshHandler handler;
    private App app;
    public static JobFragment jobFragment;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_job, container, false);
        jobFragment = JobFragment.this;
        init();
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setAbsListView(job_listview);
        job_listview.setAdapter(alphaInAnimationAdapter);
        job_listview.setOnItemClickListener(this);
        job_listview.setOnLoadMoreListener(this);
        job_listview.setEmptyView(view.findViewById(R.id.tv_job_empty));
        return view;
    }

    private void init() {
        view.findViewById(R.id.refsh_all_job).setOnClickListener(this);
        job_listview = (LoadMoreListView) view.findViewById(R.id.job_listview);
        adapter = new JobrefshAdapter(getActivity(),"3");
        handler = new JobRefshHandler(getActivity(), adapter, job_listview);
    }

    public void initDataByNet() {
        // TODO Auto-generated method stub
        app = (App) getActivity().getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(getActivity(), UMengEvent.JOBREFSH);
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(1);
            params.put("currentpage", "1");
            MsgHandler.sendMessage(params, handler,
                    JobRefshHandler.wGetJobStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOB);
        params.put("topjob_type", "1");
        params.put("no_parent_job", "1");
        params.put("get_sub_job", "1");
        params.put("get_job_resume", "1");
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
                    MsgHandler.sendMessage(params, handler, JobRefshHandler.wRefshAllStart);
                    initDataByNet();
                } else {
                    Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
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

        JobInfoBean infoBean = (JobInfoBean) parent.getAdapter().getItem(
                position);

        Intent intent = new Intent(getActivity(), JobInfoActivity.class);
        intent.putExtra("job_id", infoBean.getJob_id());
        intent.putExtra("crypt_job_id", infoBean.getCrypt_job_id());
        getActivity().startActivity(intent);
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