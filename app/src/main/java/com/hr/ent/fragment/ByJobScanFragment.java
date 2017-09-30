package com.hr.ent.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.ResumeHandler;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.ui.ResumeScanActivity;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 按职位查看
 */
public class ByJobScanFragment extends Fragment implements OnItemClickListener {
    private ListView byjob;
    private ResumeHandler handler;
    private Activity activity;
    private App app;
    private ResumeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        activity = getActivity();
        app = (App) activity.getApplication();
        adapter = new ResumeAdapter(activity);
        handler = new ResumeHandler(activity, adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_byjob, container, false);
        byjob = (ListView) view.findViewById(R.id.byjob_listview);
        byjob.setEmptyView(view.findViewById(R.id.byjob_tishi));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        if (byjob != null) {
            byjob.setAdapter(adapter);
            byjob.setOnItemClickListener(this);
        }
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initData();
    }
    public void initData() {
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(activity, UMengEvent.RESUME_SCAN);
            Map<String, Object> params = initParams();
            MsgHandler.sendMessage(params, handler, ResumeHandler.wGetResumeByJobStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    //method=enterprise_job.jobgetlist&get_job_resume=1&gettype=&topjob_type=1&get_job_resume=1&no_parent_job=0&get_sub_job=0&total=&currentpage=1&pagesize=20
    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.GETJOB);
        params.put("get_job_resume", "1");
        params.put("gettype", "");
        params.put("topjob_type", "1");
        params.put("get_job_resume", "1");
        params.put("no_parent_job", "0");
        params.put("get_sub_job", "0");
        params.put("total", "");
        params.put("currentpage", "");
        params.put("pagesize", "20");
        return params;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        ResumeNum num = (ResumeNum) parent.getAdapter().getItem(position);
        if (num.getTypeNum() != null && !num.getTypeNum().equals("0") && !num.getTypeNum().equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString("type", num.getType());
            bundle.putString("typeID", num.getTypeID());
            bundle.putString("typeName", num.getTypeName());
            bundle.putString("boxtype", "3");
            Intent intent = new Intent(getActivity(), ResumeScanActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(activity, "该职位没有投递的简历", Toast.LENGTH_SHORT).show();
        }
    }
}