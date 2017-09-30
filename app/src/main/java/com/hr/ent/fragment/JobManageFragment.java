package com.hr.ent.fragment;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.JobRefshHandler;
import com.hr.ent.ui.GetJobActivity;
import com.hr.ent.ui.PostJobActivity;
import com.hr.ent.utils.BitmapUtil;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobManageFragment extends Fragment {

    @Bind(R.id.rl_jobmag_issuenewjob)
    RelativeLayout rlJobmagIssuenewjob;
    @Bind(R.id.tv_jobmag_issuejob)
    TextView tvJobmagIssuejob;
    @Bind(R.id.rl_jobmag_issuejob)
    RelativeLayout rlJobmagIssuejob;
    @Bind(R.id.tv_jobmag_auditjob)
    TextView tvJobmagAuditjob;
    @Bind(R.id.rl_jobmag_auditjob)
    RelativeLayout rlJobmagAuditjob;
    @Bind(R.id.tv_jobmag_unpulish)
    TextView tvJobmagUnpulish;
    @Bind(R.id.rl_jobmag_unpulish)
    RelativeLayout rlJobmagUnpulish;
    @Bind(R.id.tv_jobmag_pause)
    TextView tvJobmagPause;
    @Bind(R.id.rl_jobmag_pause)
    RelativeLayout rlJobmagPause;
    @Bind(R.id.tv_jobmag_offline)
    TextView tvJobmagOffline;
    @Bind(R.id.rl_jobmag_offline)
    RelativeLayout rlJobmagOffline;
    private App app;
    private JobRefshHandler handler;

    public JobManageFragment() {
    }

    public static JobManageFragment jobManageFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_manage, container, false);
        ButterKnife.bind(this, view);
        jobManageFragment = JobManageFragment.this;
        handler = new JobRefshHandler(getActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initDataByNet();
    }

    //53426.63 8241 62335.96
    public void initDataByNet() {
        // TODO Auto-generated method stub
        app = (App) getActivity().getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = initParams();
            MsgHandler.sendMessage(params, handler, JobRefshHandler.wGetJobNumStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOBNUM);
        return params;
    }

    public void setNum(String issuejob, String auditjob, String unpulish, String pause, String offline) {
        tvJobmagAuditjob.setText(auditjob);
        tvJobmagIssuejob.setText(issuejob);
        tvJobmagOffline.setText(offline);
        tvJobmagPause.setText(pause);
        tvJobmagUnpulish.setText(unpulish);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_jobmag_issuenewjob, R.id.tv_jobmag_issuejob, R.id.rl_jobmag_issuejob, R.id.tv_jobmag_auditjob, R.id.rl_jobmag_auditjob, R.id.tv_jobmag_unpulish, R.id.rl_jobmag_unpulish, R.id.tv_jobmag_pause, R.id.rl_jobmag_pause, R.id.tv_jobmag_offline, R.id.rl_jobmag_offline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_jobmag_issuenewjob:
                Intent intent = new Intent(getActivity(), PostJobActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_jobmag_issuejob:
                break;
            case R.id.rl_jobmag_issuejob:
                goActivity("1");
                break;
            case R.id.tv_jobmag_auditjob:
                break;
            case R.id.rl_jobmag_auditjob:
                goActivity("9");
                break;
            case R.id.tv_jobmag_unpulish:
                break;
            case R.id.rl_jobmag_unpulish:
                goActivity("2");
                break;
            case R.id.tv_jobmag_pause:
                break;
            case R.id.rl_jobmag_pause:
                goActivity("3");
                break;
            case R.id.tv_jobmag_offline:
                break;
            case R.id.rl_jobmag_offline:
                goActivity("5");
                break;
        }
    }

    private void goActivity(String gettype) {
        Intent intent = new Intent(getActivity(), GetJobActivity.class);
        intent.putExtra("gettype", gettype);
        startActivity(intent);
    }
}
