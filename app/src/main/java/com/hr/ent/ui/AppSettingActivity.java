package com.hr.ent.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.handler.LoginHandler;
import com.hr.ent.utils.AndroidMarketEvaluate;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.StatusBarCompat;
import com.hr.ent.view.WindowUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppSettingActivity extends BaseActivity {

    @Bind(R.id.iv_settingapp_back)
    ImageView ivSettingappBack;
    @Bind(R.id.rl_settingapp_contact)
    RelativeLayout rlSettingappContact;
    @Bind(R.id.rl_settingapp_feedback)
    RelativeLayout rlSettingappFeedback;
    @Bind(R.id.rl_settingapp_apk)
    RelativeLayout rlSettingappApk;
    @Bind(R.id.rl_settingapp_hotadv)
    RelativeLayout rlSettingappHotadv;
    @Bind(R.id.rl_settingapp_brand)
    RelativeLayout rlSettingappBrand;
    @Bind(R.id.rl_settingapp_loginout)
    RelativeLayout rlSettingappLoginout;
    private Context context;
    private LoginHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_app_setting);

        ButterKnife.bind(this);
        context = AppSettingActivity.this;
    }

    @OnClick({R.id.iv_settingapp_back, R.id.rl_settingapp_contact, R.id.rl_settingapp_feedback, R.id.rl_settingapp_apk, R.id.rl_settingapp_hotadv, R.id.rl_settingapp_brand, R.id.rl_settingapp_loginout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_settingapp_back:
                finish();
                break;
            case R.id.rl_settingapp_contact:
                Intent intentContact = new Intent(this, ContactOurActivity.class);
                startActivity(intentContact);
                break;
            case R.id.rl_settingapp_feedback:
                AndroidMarketEvaluate.goMarket(context);
                break;
            case R.id.rl_settingapp_apk:
                openLink(Const.DOWNAPK);
                break;
            case R.id.rl_settingapp_hotadv:
                openLink(getURL(true));
                break;
            case R.id.rl_settingapp_brand:
                openLink(getURL(false));
                break;
            case R.id.rl_settingapp_loginout:
                app.setExit(false);
                handler = new LoginHandler(context);
                if (app.getNetworkMng().isCanConnect()) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(HttpHelper.METHOD, HttpHelper.LOGOUT);
                    MsgHandler.sendMessage(params, handler, LoginHandler.wGetExitStart);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.nonet), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 获取URL，true是热点广告，false是品牌广告
     *
     * @param type
     * @return
     */
    private String getURL(boolean type) {
        StringBuilder builder = new StringBuilder("http://m.");

        int siteCode = Integer.parseInt(app.getUser().getSite_code());
        switch (siteCode) {
            case 11:// 建筑
                builder.append("buildhr");
                break;
            case 12:// 金融
                builder.append("bankhr");
                break;
            case 14:// 医药
                builder.append("healthr");
                break;
            case 29:// 化工
                builder.append("chenhr");
                break;
            default:
                builder.append("buildhr");
                break;
        }

        if (type) {
            builder.append(".com/so");
        } else {
            builder.append(".com/faxian");
        }
        return builder.toString();
    }

    private void openLink(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.e("PopUtil", "openLink fail");
        }
    }
}
