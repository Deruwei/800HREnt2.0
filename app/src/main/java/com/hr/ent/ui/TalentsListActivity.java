package com.hr.ent.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.TalentsListAdapter;
import com.hr.ent.handler.TalentsListHandler;
import com.hr.ent.model.SelectBean;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalentsListActivity extends BaseActivity implements LoadMoreListView.OnLoadMoreListener {

    @Bind(R.id.tv_talents_back)
    ImageView tvTalentsBack;
    @Bind(R.id.tv_talents_empty)
    TextView tvTalentsEmpty;
    @Bind(R.id.lv_talents)
    LoadMoreListView lvTalents;
    private TalentsListAdapter talentsListAdapter;
    private TalentsListHandler handler;
    private List<SelectBean> selectBeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_talents_list);
        ButterKnife.bind(this);
        getServiceState();
        initData();
    }
    @OnClick({R.id.tv_talents_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_talents_back:
                finish();
                break;
        }
    }
    private void getServiceState(){
        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.array_serviceState_zh));
        List<String> stringList2 = Arrays.asList(getResources().getStringArray(R.array.array_serviceState_id));
        for(int i=0;i<stringList.size();i++){
            SelectBean selectBean=new SelectBean();
            selectBean.setId(stringList2.get(i));
            selectBean.setName(stringList.get(i));
            selectBeanList.add(selectBean);
        }
    }
    private void initData() {
        talentsListAdapter = new TalentsListAdapter(this);
        talentsListAdapter.setSelectBeanList(selectBeanList);
        handler = new TalentsListHandler(this, talentsListAdapter,lvTalents);
        lvTalents.setAdapter(talentsListAdapter);
        lvTalents.setOnLoadMoreListener(this);
        lvTalents.setEmptyView(tvTalentsEmpty);
        initContractDataByNet();

    }
    public void initContractDataByNet() {
        if (app.getNetworkMng().isCanConnect()) {
            talentsListAdapter.setCurrentPage(1);
            Map<String, Object> params = initParams();
            talentsListAdapter.setCurrentPage(1);
            params.put("page", "1");
            MsgHandler.sendMessage(params, handler, TalentsListHandler.wGetTalentsListStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETXUNFANGLIST);
        params.put("page_nums", "20");
        return params;
    }
    @Override
    public void onLoadMore() {
//        if (talentsListAdapter.getCurrentPage() < talentsListAdapter.getTotalPage()) {
        if (talentsListAdapter.getCurrentPage() < 1000) {
//            Toast.makeText(context, "到底了", Toast.LENGTH_SHORT).show();
            Map<String, Object> params = initParams();
            talentsListAdapter.setCurrentPage(talentsListAdapter.getCurrentPage() + 1);
            params.put("page", talentsListAdapter.getCurrentPage() + "");
            MsgHandler.sendMessage(params, handler, TalentsListHandler.wGetMoreTalentsListStart);
        } else {
            lvTalents.onLoadMoreComplete();
        }
    }
}
