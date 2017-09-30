package com.hr.ent.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeSearchListAdapter;
import com.hr.ent.handler.ResumeRefshHandler;
import com.hr.ent.model.ResumeSearchListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.LoadMoreListView.OnLoadMoreListener;
import com.hr.ent.view.WindowUtils;
import com.hr.ent.view.animaton.AlphaInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchResumeResult extends BaseActivity implements AdapterView.OnItemClickListener, OnLoadMoreListener {

    @Bind(R.id.iv_resumeresult_back)
    ImageView ivResumeresultBack;
    @Bind(R.id.lv_resumesearch)
    LoadMoreListView lvResumesearch;
    @Bind(R.id.tv_resumesearch_empty)
    TextView tvResumesearchEmpty;
    private String keyword_type;
    private String entkeysearch;
    private String currentpage;
    private String pagesize;
    private Map<String, Object> params;
    private ResumeRefshHandler handler;
    private ResumeSearchListAdapter adapter;
    private String lingyuIdString = "";// 领域id


    // 当前简历列表
    public static List<ResumeSearchListBean> infoBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_search_resume_result);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        adapter = new ResumeSearchListAdapter(this);
        handler = new ResumeRefshHandler(this, adapter, lvResumesearch);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setAbsListView(lvResumesearch);
        lvResumesearch.setAdapter(alphaInAnimationAdapter);
        lvResumesearch.setOnItemClickListener(this);
        lvResumesearch.setOnLoadMoreListener(this);
        params = new HashMap<String, Object>();
        infoBeans = new ArrayList<ResumeSearchListBean>();
        lvResumesearch.setAdapter(adapter);
        lvResumesearch.setEmptyView(tvResumesearchEmpty);
        initParams();
        initBaseInfo();
    }

    private Map<String, Object> initParams() {
        Bundle bundle = getIntent().getExtras();
        params.put(HttpHelper.METHOD, HttpHelper.RESUME_SEARCH);
        params.put("sex", bundle.getString("sex"));
        params.put("search_id", bundle.getString("search_id"));
        params.put("norecord", bundle.getString("norecord"));
        params.put("resume_number", bundle.getString("resume_number"));
        params.put("high_education", bundle.getString("high_education"));
        params.put("is_include_more_degree", bundle.getString("is_include_more_degree"));
        params.put("language", bundle.getString("language"));
        params.put("funtype", bundle.getString("funtype"));
        params.put("workarea", bundle.getString("workarea"));
        params.put("level", bundle.getString("level"));
        params.put("searchtime", bundle.getString("searchtime"));
        params.put("locat", bundle.getString("locat"));
        params.put("post_rank", bundle.getString("post_rank"));
        params.put("work_type", bundle.getString("work_type"));
        params.put("keyword_type", bundle.getString("keyword_type"));
        params.put("entkeysearch", bundle.getString("entkeysearch"));
        params.put("workyear", bundle.getString("workyear"));
        params.put("is_include_more_workyear", bundle.getString("is_include_more_workyear"));
        params.put("lingyu", bundle.getString("lingyu"));
        params.put("age1", bundle.getString("age1"));
        params.put("age2", bundle.getString("age2"));
        params.put("age3", bundle.getString("age3"));
        params.put("salary_from", bundle.getString("salary_from"));
        params.put("salary_to", bundle.getString("salary_to"));
        params.put("zhixi", bundle.getString("zhixi"));
        params.put("resume_type", bundle.getString("resume_type"));
        params.put("current_yearsalary", bundle.getString("current_yearsalary"));
        params.put("site_code", bundle.getString("site_code"));
        params.put("yy_zhicheng", bundle.getString("yy_zhicheng"));
        params.put("total", bundle.getString("total"));
        params.put("current_yearsalary", bundle.getString("current_yearsalary"));
        params.put("pagesize", Const.PAGESIZE + "");
        return params;
    }

    private void initBaseInfo() {
        if (app.getNetworkMng().isCanConnect()) {
            adapter.setCurrentPage(1);
            params.put("currentpage", adapter.getCurrentPage());
            MsgHandler.sendMessage(params, handler, ResumeRefshHandler.wGetResumeStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.iv_resumeresult_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_resumeresult_back:
                finish();
                break;
        }
    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub
        if (adapter.getCurrentPage() < adapter.getTotalPage()) {
            Map<String, Object> params = initParams();
            adapter.setCurrentPage(adapter.getCurrentPage() + 1);
            params.put("currentpage", adapter.getCurrentPage());
            MsgHandler.sendMessage(params, handler, ResumeRefshHandler.wGetResumeMoreStart);
        } else {
            lvResumesearch.onLoadMoreComplete();
        }
    }

    private void getMoreResumeList(Map<String, Object> params) {
        adapter.setCurrentPage(adapter.getCurrentPage() + 1);
        params.put("currentpage", adapter.getCurrentPage());
        MsgHandler.sendMessage(params, handler, ResumeRefshHandler.wGetResumeMoreStart);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public class ResumeScanBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals(Const.RESUME_READ)) {// 设置已读
                String id = intent.getStringExtra("id");
//                setItemRead(id);
            } else if (action.equals(Const.INIT_DATA)) {// 重新初始化数据
                initData();
                if (adapter.getCount() <= 0) {
                    finish();
                }
            } else if (action.equals(Const.LOAD_MORE)) {// 加载更多数据
                onLoadMore();
            } else if (action.equals(Const.UPDATE_ADAPTER)) {// 更新适配器
//                int position = intent.getIntExtra("position", 0);
//                updateAdapter(position);
            } else if (action.equals(Const.RESUME_LIST)) {
                infoBeans = adapter.getList();
            }
        }
    }

//    /**
//     * 更新简历浏览界面的适配器
//     *
//     * @param position
//     */
//    public void updateAdapter(int position) {
//        if (adapter != null) {
//            adapter.setCurrentSelectedItem(position);
//        }
//    }

    private ResumeScanBroadCast broadCast;

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(broadCast);
        infoBeans.clear();
        infoBeans = null;
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // 注册广播
        if (broadCast == null) {
            broadCast = new ResumeScanBroadCast();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Const.RESUME_READ);
            filter.addAction(Const.INIT_DATA);
            filter.addAction(Const.LOAD_MORE);
            filter.addAction(Const.UPDATE_ADAPTER);
            filter.addAction(Const.RESUME_LIST);
            registerReceiver(broadCast, filter);
        }
    }
}
