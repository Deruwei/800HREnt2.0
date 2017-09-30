package com.hr.ent.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeByResumeAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.ResumeByResumeHandler;
import com.hr.ent.handler.ResumeHandler;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 按简历查看
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-26
 */
public class ByResumeScanFragment extends Fragment {
    //    private ListView byresume;
    private ExpandableListView elv_byresume_listview;
    private ResumeByResumeHandler handler;
    private Activity activity;
    private App app;
    private ResumeByResumeAdapter adapter;
    public static ByResumeScanFragment byResumeScanFragment = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        byResumeScanFragment = ByResumeScanFragment.this;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_byresume, container, false);
        if (container == null) {
            return null;
        }
        elv_byresume_listview = (ExpandableListView) view.findViewById(R.id.elv_byresume_listview);
        activity = getActivity();
        app = (App) activity.getApplication();
        adapter = new ResumeByResumeAdapter(activity, elv_byresume_listview);
        handler = new ResumeByResumeHandler(activity, adapter, elv_byresume_listview);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        if (elv_byresume_listview != null) {
            elv_byresume_listview.setAdapter(adapter);
//            elv_byresume_listview.setOnItemClickListener(this);
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initData();
    }

    public void initData() {
        // TODO Auto-generated method stub
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(activity, UMengEvent.RESUME_SCAN);
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put(HttpHelper.METHOD, HttpHelper.BOXFILTER);
            MsgHandler.sendMessage(paramsMap, handler, ResumeHandler.wGetResumeFilterStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position,
//                            long id) {
//        // TODO Auto-generated method stub
//        ResumeNum num = (ResumeNum) parent.getAdapter().getItem(position);
//
//        if (num.getTypeNum() != null && !num.getTypeNum().equals("") && !num.getTypeNum().equals("0")) {
//            Bundle bundle = new Bundle();
//            bundle.putString("type", num.getType());
//            bundle.putString("typeID", num.getTypeID());
//            Intent intent = new Intent(getActivity(), ResumeScanActivity.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        } else {
//            Toast.makeText(activity, "该文件夹没有投递简历", Toast.LENGTH_SHORT).show();
//        }
//    }
}
