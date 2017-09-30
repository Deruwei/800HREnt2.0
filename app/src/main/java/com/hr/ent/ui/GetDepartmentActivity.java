package com.hr.ent.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.DepAdapter;
import com.hr.ent.app.App;
import com.hr.ent.handler.PostJobHandler;
import com.hr.ent.model.DepInfoBean;
import com.hr.ent.model.DepListBean;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GetDepartmentActivity extends Activity {

    @Bind(R.id.tv_getdep_back)
    ImageView tvGetdepBack;
    @Bind(R.id.lv_getdep)
    ListView lvGetdep;
    private PostJobHandler postJobHandler;
    private App app;
    private DepAdapter depAdapter;
    public static GetDepartmentActivity getDepartmentActivity;
//    private ArrayList<DepListBean.ReturnDataBean> returnDataBeanList;
    private DepListBean.ReturnDataBean returnDataBean;
    //    private ArrayList<DepListBean.ReturnDataBean> returnDataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_get_department);
        ButterKnife.bind(this);
        getDepartmentActivity = GetDepartmentActivity.this;
        initData();

    }

    private void initData() {
        postJobHandler = new PostJobHandler(this, lvGetdep);
        getDepartment1();

    }

    public void initAdapter(final ArrayList<DepListBean.ReturnDataBean> returnDataBeanList) {
        returnDataBeanList.add(0,returnDataBean);
//        this.returnDataBean = returnDataBean;
        depAdapter = new DepAdapter(this, returnDataBeanList);
        lvGetdep.setAdapter(depAdapter);
        lvGetdep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("ReturnDataBean", returnDataBeanList.get(position));
//                mIntent.putExtra("dname", returnDataBean.get(position).getDname());
//                mIntent.putExtra("did", returnDataBean.get(position).getDid());
//                returnDataBean.get(position).getDname();
                mIntent.putExtras(bundle);
                setResult(0, mIntent);
                finish();
            }
        });
    }

    public void initDepInfo(DepInfoBean depInfoBean) {
        returnDataBean = new DepListBean.ReturnDataBean();
        returnDataBean.setDname(depInfoBean.getEnterprise_name());
        returnDataBean.setDid("1");
        returnDataBean.setAddress(depInfoBean.getAddress());
        returnDataBean.setEmail(depInfoBean.getEmail());
        returnDataBean.setPhone(depInfoBean.getPhone());
        returnDataBean.setFax(depInfoBean.getFax());
        returnDataBean.setUser_id(depInfoBean.getUser_id());
        returnDataBean.setZipcode(depInfoBean.getZipcode());
        getDepartment();
    }

    private void getDepartment() {
        app = (App) getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(this, UMengEvent.JOBREFSH);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.GET_DEPARTMENT);
            MsgHandler.sendMessage(params, postJobHandler, PostJobHandler.wGetDepStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private void getDepartment1() {
        app = (App) getApplication();
        if (app.getNetworkMng().isCanConnect()) {
            MobclickAgent.onEvent(this, UMengEvent.JOBREFSH);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISEGETBASEINFO);
            MsgHandler.sendMessage(params, postJobHandler, PostJobHandler.wGetDep2Start);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_getdep_back)
    public void onViewClicked() {
        finish();
    }
}
