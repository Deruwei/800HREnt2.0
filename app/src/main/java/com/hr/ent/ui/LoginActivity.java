package com.hr.ent.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.app.DownAPK;
import com.hr.ent.db.UserDao;
import com.hr.ent.handler.LoginHandler;
import com.hr.ent.model.User;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.PasswordEncryUtils;
import com.hr.ent.utils.SharedPreferencedUtils;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录界面
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-24
 */
public class LoginActivity extends Activity implements OnCheckedChangeListener,
        OnClickListener, OnItemClickListener {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_save_psd;
    private CheckBox cb_auto_login;
    //    private TextView tv_register;
    private LinearLayout linear_login_register;
    private String userName;
    private String passWord;
    private LoginHandler loginHandler;
    private TextView tv_login_person;
    private UserDao userDao;
    private User user;
    private App app;
    private PasswordEncryUtils utils;
    private SharedPreferencedUtils sUtils;

    private boolean isSavePsd = false;
    private boolean isAutoLogin = false;

    private LoginBroadcast broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        ActivityManager.addActivity(this);
        app = (App) getApplication();
        loginHandler = new LoginHandler(this);
        userDao = new UserDao(this);
        user = userDao.getLoginUser();
        try {
            utils = new PasswordEncryUtils();
            sUtils = new SharedPreferencedUtils(this);
            if (user != null) {
                et_username.setText(user.getUser_name());
                if (sUtils.getBooleanValue(Const.SAVEPWD, false)) {
                    et_password.setText(utils.decrypt(user.getUser_pwd()));
                    cb_save_psd.setChecked(true);
                    isSavePsd = true;
                } else {
                    et_password.setText("");
                    cb_save_psd.setChecked(false);
                    isSavePsd = false;
                }
            }
            if (sUtils.getBooleanValue(Const.AUTOLOGIN, false)) {
                cb_auto_login.setChecked(true);
            } else {
                cb_auto_login.setChecked(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void init() {
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        cb_save_psd = (CheckBox) findViewById(R.id.save_psd_check);
        tv_login_person = (TextView) findViewById(R.id.tv_login_person);
        cb_auto_login = (CheckBox) findViewById(R.id.auto_login_check);
        linear_login_register = (LinearLayout) findViewById(R.id.linear_login_register);
        cb_save_psd.setOnCheckedChangeListener(this);
        cb_auto_login.setOnCheckedChangeListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        linear_login_register.setOnClickListener(this);
        tv_login_person.setOnClickListener(this);
//        tv_register = (TextView) findViewById(R.id.register_tell);
        NoUnderLineSpan noUnderLineSpan = new NoUnderLineSpan();
//        if (tv_register.getText() instanceof Spannable) {
//            Spannable spannable = (Spannable) tv_register.getText();
//            spannable.setSpan(noUnderLineSpan, 0, spannable.length(),
//                    Spanned.SPAN_MARK_MARK);
//        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login:
                if (!app.getNetworkMng().isCanConnect()) {
                    Toast.makeText(this, getString(R.string.nonet),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                userName = et_username.getText().toString().trim();
                passWord = et_password.getText().toString().trim();

                if (userName.equals("")) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passWord.equals("")) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isSavePsd) {
                    sUtils.setBooleanValue(Const.SAVEPWD, true);
                } else {
                    sUtils.setBooleanValue(Const.SAVEPWD, false);
                }

                if (isAutoLogin) {
                    sUtils.setBooleanValue(Const.AUTOLOGIN, true);
                } else {
                    sUtils.setBooleanValue(Const.AUTOLOGIN, false);
                }

                MobclickAgent.onEvent(this, UMengEvent.LOGIN);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_LOGIN);
                params.put("user_name", userName);
                params.put("user_pwd", passWord);
                params.put("site_code", "10");
                params.put("http_referer", "");
                MsgHandler.sendMessage(params, loginHandler, LoginHandler.wLoginStart);
                break;
            case R.id.linear_login_register:
                Intent intentRegister = new Intent(this, CompanyRegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.tv_login_person:
                openLink(Const.DOWNAPK);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // TODO Auto-generated method stub
        closeSoftInput();
        switch (buttonView.getId()) {
            case R.id.save_psd_check:
                isSavePsd = isChecked;
                break;
            case R.id.auto_login_check:
                isAutoLogin = isChecked;
                break;

        }
    }
    private void openLink(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.e("PopUtil", "openLink fail");
        }
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (broadcast == null) {
            broadcast = new LoginBroadcast();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Const.EXIT);
            filter.addAction(Const.LOGIN_OUT);
            registerReceiver(broadcast, filter);
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("您确定退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                LoginActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.show();
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
        unregisterReceiver(broadcast);
    }

    public class LoginBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(Const.EXIT)) {
                LoginActivity.this.finish();
            } else if (intent.getAction().equals(Const.LOGIN_OUT)) {
                et_username.setText("");
                et_password.setText("");
                cb_save_psd.setChecked(false);
                cb_auto_login.setChecked(false);
            }
        }
    }

    /**
     * 设置TextView没有下划线
     *
     * @author 800hr：yelong
     *         <p/>
     *         2015-7-29
     */
    public class NoUnderLineSpan extends UnderlineSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            // TODO Auto-generated method stub
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
        }

    }

    /**
     * 关闭软键盘
     */
    public void closeSoftInput() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        MobclickAgent.onEvent(this, Const.DOWNAPK);
        ISFileExist isExist = new ISFileExist(
                Environment.getExternalStorageDirectory() + "/" + "800hr.apk");
        if (isExist.isexists()) {
            new AlertDialog.Builder(this)
                    .setTitle("下载提示")
                    .setCancelable(false)
                    .setMessage("该应用已存在，确定下载新版本？")
                    .setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    new DownAPK(LoginActivity.this, "800hr.apk")
                                            .downFile("http://www.800hr.com/app/android/800hr.apk");

                                }
                            })
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // 不更新
                                    dialog.dismiss();

                                }
                            }).show();
        } else {
            new DownAPK(this, "800hr.apk")
                    .downFile("http://www.800hr.com/app/android/800hr.apk");
        }
    }

    public class ISFileExist {
        File f;

        public ISFileExist(String fileutil) {
            f = new File(fileutil);
        }

        public boolean isexists() {
            return f.exists();
        }
    }
}
