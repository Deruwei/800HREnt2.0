package com.hr.ent.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hr.ent.R;
import com.hr.ent.utils.SharedPreferencedUtils;
import com.hr.ent.view.WindowUtils;

/**
 * 作者：Colin
 * 日期：2015/12/2 10:44
 * 功能：选择行业界面
 */
public class ChooseIndustriesActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ChooseIndustriesActivity";
    private Context mContext = this;
    /**
     * 找控件
     */
    private LinearLayout industry11;
    private LinearLayout industry29;
    private LinearLayout industry14;
    private LinearLayout industry12;
    private LinearLayout industry22;
    private LinearLayout industry13;
    private LinearLayout industry21;
    private LinearLayout industry26;
    private LinearLayout industry40;
    private LinearLayout industry20;
    private LinearLayout industry15;
    private LinearLayout industry16;
    private LinearLayout industry30;
    private LinearLayout industry19;
    private LinearLayout industry23;
    /**
     * SharedPreferences工具类
     */
    private SharedPreferencedUtils sUtils;

    private void initView() {

        industry11 = (LinearLayout) findViewById(R.id.industry_11);
        industry29 = (LinearLayout) findViewById(R.id.industry_29);
        industry14 = (LinearLayout) findViewById(R.id.industry_14);
        industry12 = (LinearLayout) findViewById(R.id.industry_12);
        industry22 = (LinearLayout) findViewById(R.id.industry_22);
        industry13 = (LinearLayout) findViewById(R.id.industry_13);
        industry21 = (LinearLayout) findViewById(R.id.industry_21);
        industry26 = (LinearLayout) findViewById(R.id.industry_26);
        industry40 = (LinearLayout) findViewById(R.id.industry_40);
        industry20 = (LinearLayout) findViewById(R.id.industry_20);
        industry15 = (LinearLayout) findViewById(R.id.industry_15);
        industry16 = (LinearLayout) findViewById(R.id.industry_16);
        industry30 = (LinearLayout) findViewById(R.id.industry_30);
        industry19 = (LinearLayout) findViewById(R.id.industry_19);
        industry23 = (LinearLayout) findViewById(R.id.industry_23);

        industry11.setOnClickListener(this);
        industry29.setOnClickListener(this);
        industry14.setOnClickListener(this);
        industry12.setOnClickListener(this);
        industry22.setOnClickListener(this);
        industry13.setOnClickListener(this);
        industry21.setOnClickListener(this);
        industry26.setOnClickListener(this);
        industry40.setOnClickListener(this);
        industry20.setOnClickListener(this);
        industry15.setOnClickListener(this);
        industry16.setOnClickListener(this);
        industry30.setOnClickListener(this);
        industry19.setOnClickListener(this);
        industry23.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_choose_industries);


        initView();
        initData();
    }

    private void initData() {
        sUtils = new SharedPreferencedUtils(mContext);
    }

    /**
     * 记住所选行业
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.industry_11:
                app.setIndustry("11");
                app.setIndustryName("建筑");
                break;
            case R.id.industry_12:
                app.setIndustry("12");
                app.setIndustryName("金融");
                break;
            case R.id.industry_13:
                app.setIndustry("13");
                app.setIndustryName("传媒");

                break;
            case R.id.industry_14:
                app.setIndustry("14");
                app.setIndustryName("医药");
                break;
            case R.id.industry_15:
                app.setIndustry("15");
                app.setIndustryName("教培");
                break;
            case R.id.industry_16:
                app.setIndustry("16");
                app.setIndustryName("运输");
                break;
            case R.id.industry_19:
                app.setIndustry("19");
                app.setIndustryName("电子");

                break;
            case R.id.industry_20:
                app.setIndustry("20");
                app.setIndustryName("电力");
                break;
            case R.id.industry_21:
                app.setIndustry("21");
                app.setIndustryName("通信");
                break;
            case R.id.industry_22:
                app.setIndustry("22");
                app.setIndustryName("制造");
                break;
            case R.id.industry_23:
                app.setIndustry("23");
                app.setIndustryName("IT");
                break;
            case R.id.industry_26:
                app.setIndustry("26");
                app.setIndustryName("服装");
                break;
            case R.id.industry_29:
                app.setIndustryName("化工");
                app.setIndustry("29");
                break;
            case R.id.industry_30:
                app.setIndustry("30");
                app.setIndustryName("旅游");
                break;
            case R.id.industry_40:
                app.setIndustry("40");
                app.setIndustryName("餐饮");
                break;
        }
        CompanyRegisterActivity.companyRegisterActivity.initData();
        finish();
    }

}
