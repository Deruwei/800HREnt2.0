package com.hr.ent.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.WindowUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalentMessageActivity extends ActionBarActivity {

    @Bind(R.id.tv_talentsmessage_back)
    ImageView tvTalentsmessageBack;
    @Bind(R.id.tv_talentsmessage_title)
    TextView tvTalentsmessageTitle;
    @Bind(R.id.lv_talentsmessage)
    LoadMoreListView lvTalentsmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_talent_message);
        ButterKnife.bind(this);
        initData();
    }
    private void initData() {
    }
    @OnClick(R.id.tv_talentsmessage_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_talentsmessage_back:
                finish();
                break;
        }
    }
}
