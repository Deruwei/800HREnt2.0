package com.hr.ent.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hr.ent.app.App;
import com.umeng.analytics.MobclickAgent;

/**
 * Activit的基类，有添加和删除Activity的操作
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-24
 */
public class BaseActivity extends FragmentActivity {
    //上下文实例
    public Context context;
    //应用全局实例
    public App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        context = getApplicationContext();
        app = (App) this.getApplication();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
