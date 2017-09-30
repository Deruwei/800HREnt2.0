package com.hr.ent.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.view.WindowUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 联系我们
 */
public class ContactOurActivity extends Activity {

    @Bind(R.id.iv_contact_back)
    ImageView ivContactBack;
    @Bind(R.id.tv_contract_our)
    TextView tvContractOur;
    @Bind(R.id.tv_contract_pay)
    TextView tvContractPay;
    @Bind(R.id.linear_contact_our)
    LinearLayout linearContactOur;
    @Bind(R.id.linear_contact_pay)
    LinearLayout linearContactPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_contact_our);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_contact_back, R.id.tv_contract_our, R.id.tv_contract_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_contact_back:
                finish();
                break;
            case R.id.tv_contract_our:
                tvContractOur.setTextColor(Color.parseColor("#F39D0D"));
                tvContractPay.setTextColor(Color.parseColor("#FFFFFF"));
                tvContractOur.setBackgroundResource(R.drawable.linear_banyuan_left1);
                tvContractPay.setBackgroundResource(R.drawable.linear_banyuan_right2);
                linearContactOur.setVisibility(View.VISIBLE);
                linearContactPay.setVisibility(View.GONE);
                break;
            case R.id.tv_contract_pay:
                tvContractOur.setTextColor(Color.parseColor("#FFFFFF"));
                tvContractPay.setTextColor(Color.parseColor("#F39D0D"));
                tvContractOur.setBackgroundResource(R.drawable.linear_banyuan_left2);
                tvContractPay.setBackgroundResource(R.drawable.linear_banyuan_right1);
                linearContactOur.setVisibility(View.GONE);
                linearContactPay.setVisibility(View.VISIBLE);
                break;
        }
    }
}
