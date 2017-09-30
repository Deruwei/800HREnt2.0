package com.hr.ent.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeListAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.ResumeScanHandler;
import com.hr.ent.model.BaseResumeInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简历浏览界面
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-24
 */
public class ResumeScanActivity extends BaseActivity implements
        OnClickListener, OnScrollListener, OnItemClickListener {
    private TextView title;
    private ListView resume_listview;

    // 表示简历的类型，如已下载简历不能删除
    private String type;
    private String typeID, boxtype, typeName;
    private App app;
    private ResumeListAdapter adapter;
    private ResumeScanHandler handler;

    private ResumeScanBroadCast broadCast;

    // 当前简历列表
    public static List<BaseResumeInfoBean> infoBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resumescan);//listview
        infoBeans = new ArrayList<BaseResumeInfoBean>();
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        typeID = bundle.getString("typeID");
        boxtype = bundle.getString("boxtype");
        typeName = bundle.getString("typeName");
        System.out.println("type==" + type);
        System.out.println("typeID==" + typeID);
        init();
        adapter = new ResumeListAdapter(this);
        handler = new ResumeScanHandler(this, adapter);
        resume_listview.setAdapter(adapter);
        resume_listview.setOnItemClickListener(this);
        resume_listview.setOnScrollListener(this);
        initData();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        title.setText(typeName);
        resume_listview = (ListView) findViewById(R.id.resumescan_listview);
        resume_listview.setEmptyView(findViewById(R.id.tv_resumescan_empty));
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.setting).setVisibility(View.GONE);
    }

    public void initData() {
        app = (App) getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            initDataByNet();
        } else {
            Toast.makeText(this, getResources().getString(R.string.nonet), Toast.LENGTH_SHORT).show();
        }
    }

    private void initDataByNet() {
        Map<String, Object> params = initParams();
        if (type.equals(Const.BOX)) {
            params.put("boxid", typeID);
            getResumeList(params);
        } else if (type.equals(Const.FILTER)) {
            params.put("this_filter", typeID);
            getResumeList(params);
        } else if (type.equals(Const.DOWNLOAD)) {
            getDownLoadResumeList();
        } else if (type.equals(Const.JOB)) {
            params.put("job_id", typeID);
            getResumeList(params);
        } else if (type.equals(Const.IS_NEW)) {
            params.put("isnew", typeID);
            params.put("boxid", "0");
            getResumeList(params);
        } else if (type.equals(Const.TODAY)) {
            params.put("boxid", "0");
            params.put("last_day", typeID);
            getResumeList(params);
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.RESUMELIST);
        params.put("orderby", "desc");
        params.put("order", "time");
        // params.put(key, value)
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }

    private void getResumeList(Map<String, Object> params) {
        adapter.setCurrentPage(1);
        params.put("currentpage", adapter.getCurrentPage());
        MsgHandler.sendMessage(params, handler,
                ResumeScanHandler.wGetResumeListStart);
    }

    private void getDownLoadResumeList() {
        Map<String, Object> params = initDownLoadParams();
        adapter.setCurrentPage(1);
        params.put("currentpage", adapter.getCurrentPage());
        MsgHandler.sendMessage(params, handler,
                ResumeScanHandler.wGetDownResumeListStart);
    }

    private Map<String, Object> initDownLoadParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.DOWNRESUMELIST);
        params.put("resume_type", "2");
        params.put("total", "");
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }

    /**
     * 设置简历已读
     *
     * @param id
     */
    public void setItemRead(String id) {
        if (adapter != null) {
            adapter.setItemRead(id);
        }
    }

    private int visibleLastIndex = 0; // 最后的可视项索引

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
        int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
        if (scrollState == ListView.OnScrollListener.SCROLL_STATE_IDLE
                && visibleLastIndex == itemsLastIndex) {
            onLoadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    public void onLoadMore() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = initParams();
            if (adapter.getCurrentPage() < adapter.getTotalPage()) {
                if (type.equals(Const.BOX)) {
                    params.put("boxid", typeID);
                    getMoreResumeList(params);
                } else if (type.equals(Const.FILTER)) {
                    params.put("this_filter", typeID);
                    getMoreResumeList(params);
                } else if (type.equals(Const.DOWNLOAD)) {
                    getDownLoadMoreResumeList();
                } else if (type.equals(Const.JOB)) {
                    params.put("job_id", typeID);
                    getMoreResumeList(params);
                } else if (type.equals(Const.IS_NEW)) {
                    params.put("isnew", typeID);
                    params.put("boxid", "0");
                    getMoreResumeList(params);
                } else if (type.equals(Const.TODAY)) {
                    params.put("last_day", typeID);
                    params.put("boxid", "0");
                    getMoreResumeList(params);
                }
            }
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }

    }

    private void getMoreResumeList(Map<String, Object> params) {
        adapter.setCurrentPage(adapter.getCurrentPage() + 1);
        params.put("currentpage", adapter.getCurrentPage());
        MsgHandler.sendMessage(params, handler,
                ResumeScanHandler.wGetResumeListMoreStart);
    }

    private void getDownLoadMoreResumeList() {
        adapter.setCurrentPage(adapter.getCurrentPage() + 1);
        Map<String, Object> params = initDownLoadParams();
        params.put("currentpage", adapter.getCurrentPage());
        MsgHandler.sendMessage(params, handler,
                ResumeScanHandler.wGetDownResumeListMoreStart);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.setting:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        updateAdapter(position);
        Intent intent = new Intent(this, ResumeInfoActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("typeID", typeID);
        intent.putExtra("position", position);
        intent.putExtra("boxtype", boxtype);
//        intent.putExtra("boxtype", resume_from);
        if (infoBeans.get(position).getResumeID().equals("6")) {
            intent.putExtra("isHighResume","1");
        }else {
            intent.putExtra("isHighResume","0");
        }
        startActivity(intent);
    }

    /**
     * 更新简历浏览界面的适配器
     *
     * @param position
     */
    public void updateAdapter(int position) {
        if (adapter != null) {
            adapter.setCurrentSelectedItem(position);
        }
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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(broadCast);
        infoBeans.clear();
        infoBeans = null;
    }

    public class ResumeScanBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals(Const.RESUME_READ)) {// 设置已读
                String id = intent.getStringExtra("id");
                setItemRead(id);
            } else if (action.equals(Const.INIT_DATA)) {// 重新初始化数据
                initData();
                if (adapter.getCount() <= 0) {
                    finish();
                }
            } else if (action.equals(Const.LOAD_MORE)) {// 加载更多数据
                onLoadMore();
            } else if (action.equals(Const.UPDATE_ADAPTER)) {// 更新适配器
                int position = intent.getIntExtra("position", 0);
                updateAdapter(position);
            } else if (action.equals(Const.RESUME_LIST)) {
                infoBeans = adapter.getList();
            }
        }
    }
}
