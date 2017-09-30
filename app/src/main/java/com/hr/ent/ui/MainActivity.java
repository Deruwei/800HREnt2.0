package com.hr.ent.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.fragment.HomeFragment;
import com.hr.ent.fragment.JobFragment;
import com.hr.ent.fragment.JobManageFragment;
import com.hr.ent.fragment.ResumeFragment;
import com.hr.ent.handler.LoginHandler;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.StatusBarCompat;
import com.hr.ent.view.BeautifulDialog;
import com.hr.ent.view.WindowUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 主界面管理简历、Home、职位三个界面
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-25
 */
@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity implements OnClickListener {

    public static MainActivity instanceMain = null;
    /**
     * TAG
     */
    private static final String TAG = "MainActivity";
    /**
     * 上下文环境
     */
    private Context mContext = MainActivity.this;
    /**
     * 第一次点击时间
     */
    private long firstClickTime;
    /**
     * 控件名
     */
    private FrameLayout framLayout_main_content;
    private LinearLayout linear_main_home;
    private ImageView iv_main_home;
    private TextView tv_main_home;
    private LinearLayout linear_main_job, linear_main_resume;
    private ImageView iv_main_job;
    private TextView tv_main_job;

    private ImageView iv_main_resume;
    private TextView tv_main_resume;
    /**
     * Fragment管理器
     */
    private FragmentManager fragmentManager;
    /**
     * 声明Fragment对象
     */
    private ResumeFragment resumeFragment;
    private HomeFragment homeFragment;
    private JobManageFragment jobFragment;
//    private JobFragment jobFragment;

    private App app;
    private LoginHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_main);
        ActivityManager.addActivity(this);
        app = (App) getApplication();
        handler = new LoginHandler(this);
        instanceMain = MainActivity.this;
        initView();
        initData();
    }
   /* private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }*/
    /**
     * 初始化控件
     */
    private void initView() {
        framLayout_main_content = (FrameLayout) findViewById(R.id.framLayout_main_content);
        linear_main_home = (LinearLayout) findViewById(R.id.linear_main_home);
        linear_main_resume = (LinearLayout) findViewById(R.id.linear_main_resume);
        iv_main_home = (ImageView) findViewById(R.id.iv_main_home);
        tv_main_home = (TextView) findViewById(R.id.tv_main_home);
        linear_main_job = (LinearLayout) findViewById(R.id.linear_main_job);
        iv_main_resume = (ImageView) findViewById(R.id.iv_main_resume);
        tv_main_job = (TextView) findViewById(R.id.tv_main_job);
        tv_main_resume = (TextView) findViewById(R.id.tv_main_resume);
        iv_main_job = (ImageView) findViewById(R.id.iv_main_job);
        /*
        设置监听
         */
        linear_main_job.setOnClickListener(this);
        linear_main_home.setOnClickListener(this);
        linear_main_resume.setOnClickListener(this);

    }

    /**
     * 点击监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_main_resume:
                if (app.isContract) {
                    setTabSelect(0);
                } else {
                    Toast.makeText(this, "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.linear_main_home:
                if (app.isContract) {
                    setTabSelect(1);
                } else {
                    Toast.makeText(this, "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.linear_main_job:
                if (app.isContract) {
                    setTabSelect(2);
                } else {
                    Toast.makeText(this, "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /**
     * 根据传入的index选择Tab页
     *
     * @param index
     */
    public void setTabSelect(int index) {
        resetButton();
        //开启新事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                iv_main_resume.setImageResource(R.mipmap.jianliguanli1);
                tv_main_resume.setTextColor(Color.parseColor("#F7931E"));
                if (resumeFragment == null) {
                    resumeFragment = new ResumeFragment();
                    transaction.add(R.id.framLayout_main_content, resumeFragment);
                } else {
                    transaction.show(resumeFragment);
                }
                break;
            case 1:
                iv_main_home.setImageResource(R.mipmap.home1);
                tv_main_home.setTextColor(Color.parseColor("#F7931E"));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.framLayout_main_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 2:
                iv_main_job.setImageResource(R.mipmap.zhiweiguanli1);
                tv_main_job.setTextColor(Color.parseColor("#F7931E"));
                if (jobFragment == null) {
                    jobFragment = new JobManageFragment();
//                    jobFragment = new JobFragment();
                    transaction.add(R.id.framLayout_main_content, jobFragment);
                } else {
                    transaction.show(jobFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有Fragment
     */
    @SuppressLint("NewApi")
    private void hideFragment(FragmentTransaction transaction) {
        if (resumeFragment != null) {
            transaction.hide(resumeFragment);
        }
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (jobFragment != null) {
            transaction.hide(jobFragment);
        }
    }

    /**
     * 重置按钮状态
     */
    private void resetButton() {
        iv_main_resume.setImageResource(R.mipmap.jianliguanli2);
        iv_main_home.setImageResource(R.mipmap.home2);
        iv_main_job.setImageResource(R.mipmap.zhiweiguanli2);
        tv_main_resume.setTextColor(Color.parseColor("#333333"));
        tv_main_job.setTextColor(Color.parseColor("#333333"));
        tv_main_home.setTextColor(Color.parseColor("#333333"));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        fragmentManager = getSupportFragmentManager();
//        setTabSelect(0);
        setTabSelect(1);
    }

    private BeautifulDialog.Builder saveDialog;

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        saveDialog = new BeautifulDialog.Builder(this);
        saveDialog.setMessage("您确定退出吗？");
//        beautifulDialog.setTitle("提示");
        saveDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (app.getNetworkMng().isCanConnect()) {
                    app.setExit(true);
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(HttpHelper.METHOD, HttpHelper.LOGOUT);
                    MsgHandler.sendMessage(params, handler,
                            LoginHandler.wGetExitStart);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            MainActivity.this.getResources().getString(
                                    R.string.nonet), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        saveDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        saveDialog.create().show();
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
