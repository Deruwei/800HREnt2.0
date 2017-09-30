package com.hr.ent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.handler.HomeHandler;
import com.hr.ent.model.ContractBean;
import com.hr.ent.model.ContractLimitInfoBean;
import com.hr.ent.model.ContractListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServerInfoActivity extends BaseActivity {

    @Bind(R.id.iv_serverinfo_back)
    ImageView ivServerinfoBack;
    @Bind(R.id.lv_serverinfo)
    ListView lvServerinfo;
    TextView tvServerinfoIndustry;
    TextView tvJobPublish;
    TextView tvResumeDownload;
    TextView tvSmsSend;
    TextView tvHighJobPublish;
    TextView tvHighJobPublishdown;
    TextView tvJobOrder;
    TextView tvPeopleSearch;
    private HomeHandler handler;
    private List<ContractListBean> list = new ArrayList<>();
    private ServerAdapter serverInfoAdapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_server_info);
        ButterKnife.bind(this);
        view = LayoutInflater.from(context).inflate(R.layout.item_server_head, null);
        initView();
        lvServerinfo.addHeaderView(view);
        initData();
    }
    private void initView() {
        tvPeopleSearch = (TextView) view.findViewById(R.id.tv_people_search);
        tvJobOrder = (TextView) view.findViewById(R.id.tv_job_order);
        tvHighJobPublishdown = (TextView) view.findViewById(R.id.tv_high_job_publishdown);
        tvHighJobPublish = (TextView) view.findViewById(R.id.tv_high_job_publish);
        tvSmsSend = (TextView) view.findViewById(R.id.tv_sms_send);
        tvResumeDownload = (TextView) view.findViewById(R.id.tv_resume_download);
        tvJobPublish = (TextView) view.findViewById(R.id.tv_job_publish);
        tvServerinfoIndustry = (TextView) view.findViewById(R.id.tv_serverinfo_industry);
    }
    private void initData() {
        tvServerinfoIndustry.setText(app.getIndustryName() + "英才网");
        handler = new HomeHandler(this);
        initServiceDataByNet();
        if (!app.isTrial) {
            initContractDataByNet();
        }
    }
    private void initServiceDataByNet() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.CONTRACTSTATE);
            MsgHandler.sendMessage(params, handler, HomeHandler.wGetServiceStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新合同信息
     * @param bean
     */
    public void updateServiceLimit(ContractLimitInfoBean bean) {
        int jobLimit = Integer.parseInt(bean.getJob_open_limit());
        int jobUsed = Integer.parseInt(bean.getJob_open_limit_used());

        int highJobLimit = Integer.parseInt(bean.getLimit_opentopjob());
        int highJobUsed = Integer.parseInt(bean.getLimit_opentopjob_used());

        int downResumeLimit = Integer.parseInt(bean.getBrowse_personal_limit());
        int downResemeUsed = Integer.parseInt(bean.getBrowse_personal_limit_used());

        int highDownResumeLimit = Integer.parseInt(bean.getView_topresume_num());
        int highDownResemeUsed = Integer.parseInt(bean.getView_topresume_num_used());

        int smsLimit = Integer.parseInt(bean.getSms_limit());
        int smsUsed = Integer.parseInt(bean.getSms_limit_used());

        int linkup_num = Integer.parseInt(bean.getLinkup_num());
        int linkup_num_used = Integer.parseInt(bean.getLinkup_num_used());

        int xunfang_num = Integer.parseInt(bean.getXunfang_num());
        int xunfang_num_used = Integer.parseInt(bean.getXunfang_num_used());

        if (jobLimit==-1){
            tvJobPublish.setText("职位发布限量：不限量");
        }else {
            tvJobPublish.setText("职位发布限量：剩余" + (jobLimit - jobUsed) + "/总数" + jobLimit + "个");
        }
        if (highJobLimit==-1){
            tvHighJobPublish.setText("高端职位发布：不限量");
        }else {
            tvHighJobPublish.setText("高端职位发布：剩余" + (highJobLimit - highJobUsed) + "/总数" + highJobLimit + "个");
        }
        if (downResumeLimit==-1){
            tvResumeDownload.setText("简历下载限量：不限量");
        }else {
            tvResumeDownload.setText("简历下载限量：剩余" + (downResumeLimit - downResemeUsed) + "/总数" + downResumeLimit + "个");
        }
        if (highDownResumeLimit==-1){
            tvHighJobPublishdown.setText("高端简历下载：不限量");
        }else {
            tvHighJobPublishdown.setText("高端简历下载：剩余" + (highDownResumeLimit - highDownResemeUsed) + "/总数" + highDownResumeLimit + "个");
        }
        if (smsLimit==-1){
            tvSmsSend.setText("短信发送限量：不限量");
        }else {
            tvSmsSend.setText("短信发送限量：剩余" + (smsLimit - smsUsed) + "/总数" + smsLimit + "个");
        }

        if (linkup_num==-1){
            tvJobOrder.setText("意向沟通服务：不限量");
        }else {
            tvJobOrder.setText("意向沟通服务：剩余" + (linkup_num - linkup_num_used) + "/总数" + linkup_num + "个");
        }
        if (xunfang_num==-1){
            tvPeopleSearch.setText("人才寻访服务：不限量");
        }else {
            tvPeopleSearch.setText("人才寻访服务：剩余" + (xunfang_num - xunfang_num_used) + "/总数" + xunfang_num + "个");
        }

        if (app.isTrial) {
            serverInfoAdapter = new ServerAdapter();
            lvServerinfo.setAdapter(serverInfoAdapter);
        }

    }

    public void initContractDataByNet() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.GETCONTRACT);
            MsgHandler.sendMessage(params, handler, HomeHandler.wGetContractStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新合同信息
     */
    public void updateContractInfo(ContractBean contractBean) {
        List<ContractListBean> list2 = new ArrayList<>();
        list.clear();
        list2 = contractBean.getReturn_data();
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).getPause_state().equals("1")) {
                list.add(list2.get(i));
            }
        }
        serverInfoAdapter = new ServerAdapter();
        lvServerinfo.setAdapter(serverInfoAdapter);
        lvServerinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    goServer(position - 1);
                }
            }
        });
    }

    private void goServer(int position) {
        if (list.get(position) != null) {
            Intent mIntent = new Intent(this, ServerParticularActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(Const.PAR_KEY, list.get(position));
            mIntent.putExtras(mBundle);
            startActivity(mIntent);
        }
    }

    class ServerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHOlder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHOlder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_serverinfo_list, null);
                viewHolder.tv_serverinfo_contractnum = (TextView) convertView.findViewById(R.id.tv_serverinfo_contractnum);
                viewHolder.tv_serverinfo_contracttime = (TextView) convertView.findViewById(R.id.tv_serverinfo_contracttime);
                viewHolder.tv_serverinfo_contractname = (TextView) convertView.findViewById(R.id.tv_serverinfo_contractname);
                viewHolder.tv_serverinfo_contractvalidtime = (TextView) convertView.findViewById(R.id.tv_serverinfo_contractvalidtime);
                viewHolder.tv_serverinfo_contractvalid = (TextView) convertView.findViewById(R.id.tv_serverinfo_contractvalid);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHOlder) convertView.getTag();
            }
            if (list.get(position).getPause_state().equals("1")) {
                viewHolder.tv_serverinfo_contractname.setText("签订人：" + list.get(position).getEdit_username());
                viewHolder.tv_serverinfo_contracttime.setText(DateUtils.transforMillToDate(Long.parseLong(list.get(position).getSign_time()) * 1000));
                viewHolder.tv_serverinfo_contractnum.setText(list.get(position).getContract_number());
                if (list.get(position).getPause_state().equals("1")) {
                    viewHolder.tv_serverinfo_contractvalid.setText("有效");
                } else {
                    viewHolder.tv_serverinfo_contractvalid.setText("暂停");
                }
                viewHolder.tv_serverinfo_contractvalidtime.setText(DateUtils.transforMillToDate2(Long.parseLong(list.get(position).getCbegin_time()) * 1000) + " — " +
                        DateUtils.transforMillToDate2(Long.parseLong(list.get(position).getCend_time()) * 1000));
            }
            return convertView;
        }

        class ViewHOlder {
            TextView tv_serverinfo_contractnum, tv_serverinfo_contracttime, tv_serverinfo_contractname, tv_serverinfo_contractvalidtime, tv_serverinfo_contractvalid;
        }
    }

    @OnClick({R.id.iv_serverinfo_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_serverinfo_back:
                finish();
                break;
        }
    }
}
