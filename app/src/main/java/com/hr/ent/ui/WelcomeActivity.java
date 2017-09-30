package com.hr.ent.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.db.UserDao;
import com.hr.ent.handler.LoginHandler;
import com.hr.ent.handler.WelcomeHandler;
import com.hr.ent.model.User;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.HttpUtils;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.PasswordEncryUtils;
import com.hr.ent.utils.SharedPreferencedUtils;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.utils.VersionUpdate;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {
    private Bitmap bitmap = null;
    private WelcomeHandler welcomeHandler;
    private boolean isAutoLogin = false;
    private LoginHandler loginHandler;
    private UserDao userDao;
    private User user;
    private WelcomeBReceiver receiver;
    private SharedPreferencedUtils sUtils;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return false;
        }
    });
    private Handler handler2 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1000) {
                getCity();
                autoLogin();
            } else if (msg.what == 1001) {// 无可用网络，启动程序

            } else if (msg.what == 1002) {// 有网络，但服务器连接失败或数组更新完毕（不论是否更新成功），启动程序

            } else if (msg.what == 1003) {// exit sys
                WelcomeActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        sUtils = new SharedPreferencedUtils(this);
        loginHandler = new LoginHandler(this);
        welcomeHandler = new WelcomeHandler(this);
        userDao = new UserDao(this);
        user = userDao.getLoginUser();
        new VersionUpdate(WelcomeActivity.this, handler2);
    }

    private void getCity() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpUtils.METHOD, HttpUtils.METHOD_GET);
            Message message = new Message();
            message.what = WelcomeHandler.wUpdateArrayStart;
            message.obj = params;
            welcomeHandler.sendMessage(message);
        }
    }

    private void autoLogin() {
        if (app.getNetworkMng().isCanConnect()) {
            if (sUtils.getBooleanValue(Const.IS_FIRST, true)) {
                Intent intent = new Intent(WelcomeActivity.this, NewGuideActivity.class);// 新手指导
                startActivity(intent);
                finish();
            } else {
                isAutoLogin = sUtils.getBooleanValue(Const.AUTOLOGIN, false);
                if (user != null && isAutoLogin) {
                    try {
                        PasswordEncryUtils encryUtils = new PasswordEncryUtils();
                        MobclickAgent.onEvent(this, UMengEvent.LOGIN);
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_LOGIN);
                        params.put("user_name", user.getUser_name());
                        params.put("user_pwd", encryUtils.decrypt(user.getUser_pwd()));
                        params.put("site_code", "10");
                        params.put("http_referer", "");
                        MsgHandler.sendMessage(params, loginHandler, LoginHandler.wLoginStart);
                    } catch (Exception e) {
                        // 跳转到登陆界面
                        handler.sendEmptyMessage(0);
                    }
                } else {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try {
                                Thread.sleep(1 * 1000);
                                handler.sendEmptyMessage(0);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        } else {
            Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        receiver = new WelcomeBReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.LOGIN_SUCCESS);
        filter.addAction(Const.LOGIN_FAIL);
        this.getApplicationContext().registerReceiver(receiver, filter);
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
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        this.getApplicationContext().unregisterReceiver(receiver);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }

    public class WelcomeBReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(Const.LOGIN_SUCCESS)) {
                finish();
            } else if (intent.getAction().equals(Const.LOGIN_FAIL)) {
                handler.sendEmptyMessage(0);
            }
        }
    }
}
