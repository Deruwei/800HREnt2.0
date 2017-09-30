package com.hr.ent.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.adapter.ConstractServerListAdapter;
import com.hr.ent.model.ContractInfoBean;
import com.hr.ent.model.ContractListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServerParticularActivity extends Activity {

    @Bind(R.id.iv_serverparticular_back)
    ImageView ivServerparticularBack;
    @Bind(R.id.tv_serverparticular_contractnum)
    TextView tvServerparticularContractnum;
    @Bind(R.id.tv_serverparticular_contractpeople)
    TextView tvServerparticularContractpeople;
    @Bind(R.id.tv_serverparticular_contracttime)
    TextView tvServerparticularContracttime;
    @Bind(R.id.tv_serverparticular_validtime)
    TextView tvServerparticularValidtime;
    @Bind(R.id.lv_serverparticular)
    ListView lvServerparticular;
    private ContractListBean constract;
    private ConstractServerListAdapter  conAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_server_particular);
        ButterKnife.bind(this);
        constract= (ContractListBean)getIntent().getParcelableExtra(Const.PAR_KEY);
        initData();
    }

    private void initData() {
        tvServerparticularContractnum.setText(constract.getContract_number());
        tvServerparticularContractpeople.setText(constract.getEdit_username());
        tvServerparticularContracttime.setText(DateUtils.transforMillToDate(Long.parseLong(constract.getSign_time()) * 1000));
        tvServerparticularValidtime.setText(DateUtils.transforMillToDate2(Long.parseLong(constract.getCbegin_time()) * 1000) + " — " +
                DateUtils.transforMillToDate2(Long.parseLong(constract.getCend_time()) * 1000));
        conAdapter = new ConstractServerListAdapter((ArrayList<ContractInfoBean>) constract.getServe_list(),this);
        lvServerparticular.setAdapter(conAdapter);
    }

    @OnClick(R.id.iv_serverparticular_back)
    public void onClick() {
        finish();
    }
}
