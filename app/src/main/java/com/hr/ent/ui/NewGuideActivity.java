package com.hr.ent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hr.ent.R;
import com.hr.ent.adapter.ViewPagerAdapter;
import com.hr.ent.db.UserDao;
import com.hr.ent.handler.LoginHandler;
import com.hr.ent.model.User;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.PasswordEncryUtils;
import com.hr.ent.utils.SharedPreferencedUtils;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新手指导
 */
public class NewGuideActivity extends BaseActivity implements OnClickListener,
        OnPageChangeListener {
    private LoginHandler loginHandler;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;
    private boolean isAutoLogin = false;
    private Button myClickBtn;
    private static final int[] pics = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};
    private SharedPreferencedUtils sUtils;
    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_newbie_guide);
        sUtils = new SharedPreferencedUtils(this);
        loginHandler = new LoginHandler(this);
        views = new ArrayList<View>();
        myClickBtn = (Button) findViewById(R.id.myClickBtn);
        myClickBtn.setOnClickListener(this);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        userDao = new UserDao(this);
        user = userDao.getLoginUser();
    }

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            sUtils.setBooleanValue(Const.IS_FIRST, false);
            Intent intent = new Intent(NewGuideActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return false;
        }
    });

    @Override
    public void onClick(View v) {
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

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int index, float arg1, int dis) {
    }

    @Override
    public void onPageSelected(int index) {
        if (index == pics.length - 1) {
            myClickBtn.setVisibility(View.VISIBLE);
        } else {
            myClickBtn.setVisibility(View.GONE);
        }
    }
}
