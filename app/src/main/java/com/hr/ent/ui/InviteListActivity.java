package com.hr.ent.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.InviteListAdapter;
import com.hr.ent.handler.InviteListHandler;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.LoadMoreListView.OnLoadMoreListener;
import com.hr.ent.view.WindowUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteListActivity extends BaseActivity implements OnLoadMoreListener {

    @Bind(R.id.iv_invitelistinvitelist_back)
    ImageView ivInvitelistinvitelistBack;
    @Bind(R.id.lv_invitelist)
    LoadMoreListView lvInvitelist;
    private InviteListHandler handler;
    private InviteListAdapter inviteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_invite_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        inviteListAdapter = new InviteListAdapter(this);
        handler = new InviteListHandler(this, inviteListAdapter, lvInvitelist);
        lvInvitelist.setAdapter(inviteListAdapter);
        lvInvitelist.setOnLoadMoreListener(this);
        lvInvitelist.setEmptyView(findViewById(R.id.tv_invitelist_empty));
        initContractDataByNet();
    }

    public void initContractDataByNet() {
        if (app.getNetworkMng().isCanConnect()) {
            inviteListAdapter.setCurrentPage(1);
            Map<String, Object> params = initParams();
            params.put("currentpage", "1");
            MsgHandler.sendMessage(params, handler, InviteListHandler.wGetInviteListStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.iv_invitelistinvitelist_back)
    public void onClick() {
        finish();
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.INVITELIST);
        params.put("job_id", "");
        params.put("order", "");
        params.put("orderby", "");
        params.put("total", "");
        params.put("pagesize", Const.PAGESIZE);
        return params;
    }


    @Override
    public void onLoadMore() {
        if (inviteListAdapter.getCurrentPage() < inviteListAdapter.getTotalPage()) {
            Map<String, Object> params = initParams();
            inviteListAdapter.setCurrentPage(inviteListAdapter.getCurrentPage() + 1);
            params.put("currentpage", inviteListAdapter.getCurrentPage() + "");
            MsgHandler.sendMessage(params, handler, InviteListHandler.wGetMoreInviteListStart);
        } else {
            lvInvitelist.onLoadMoreComplete();
        }
    }
}
