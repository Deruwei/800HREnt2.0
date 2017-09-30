package com.hr.ent.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.HomeHandler;
import com.hr.ent.handler.JobInfoHandler;
import com.hr.ent.handler.PostJobHandler;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 * 职位信息界面
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-24
 */
public class JobInfoActivity extends BaseActivity implements OnClickListener {

    private TextView title, tv_jobinfo_1, tv_jobinfo_2, tv_jobinfo_3;
    private WebView jobinfo;

    private JobInfoHandler jobinfoHandler;
    private PopupWindow popupWindow,popupWindowDelete;
    private App app;
    private String job_id, gettype,job_name;
    private ImageView ivShare;
    public int current_limitNum;
    private String crypt_job_id;
	private boolean isShared = false;
	private long currTime = 0;// 分享的点击时间
    private JobInfoHandler handler;

    public void setCurrent_limitNum(int current_limitNum) {
        this.current_limitNum = current_limitNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_jobinfo);
        handler=new JobInfoHandler(this);
        app= (App) getApplication();
        initData();
        initView();
    }

    private Drawable drawable1;
    private Drawable drawable2;
    private Drawable drawable3;

    private void initView() {
        switch (gettype) {
            case "1":
                drawable1 = getResources().getDrawable(R.mipmap.job_bianji);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tv_jobinfo_1.setCompoundDrawables(drawable1, null, null, null);
                tv_jobinfo_1.setText("编辑");

                drawable2 = getResources().getDrawable(R.mipmap.job_pase);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tv_jobinfo_2.setCompoundDrawables(drawable2, null, null, null);
                tv_jobinfo_2.setText("暂停职位");

                drawable3 = getResources().getDrawable(R.mipmap.job_look);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tv_jobinfo_3.setCompoundDrawables(drawable3, null, null, null);
                tv_jobinfo_3.setText("查看职位");
                break;
            case "2":
                drawable1 = getResources().getDrawable(R.mipmap.job_bianji);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tv_jobinfo_1.setCompoundDrawables(drawable1, null, null, null);
                tv_jobinfo_1.setText("编辑");

                drawable2 = getResources().getDrawable(R.mipmap.job_star);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tv_jobinfo_2.setCompoundDrawables(drawable2, null, null, null);
                tv_jobinfo_2.setText("发布");


                drawable3 = getResources().getDrawable(R.mipmap.job_del);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tv_jobinfo_3.setCompoundDrawables(drawable3, null, null, null);
                tv_jobinfo_3.setText("删除");
                break;
            case "3":
                drawable1 = getResources().getDrawable(R.mipmap.job_bianji);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tv_jobinfo_1.setCompoundDrawables(drawable1, null, null, null);
                tv_jobinfo_1.setText("编辑");

                drawable2 = getResources().getDrawable(R.mipmap.job_star);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tv_jobinfo_2.setCompoundDrawables(drawable2, null, null, null);
                tv_jobinfo_2.setText("开启");

                drawable3 = getResources().getDrawable(R.mipmap.job_del);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tv_jobinfo_3.setCompoundDrawables(drawable3, null, null, null);
                tv_jobinfo_3.setText("删除");
                break;
            case "5":
                drawable1 = getResources().getDrawable(R.mipmap.job_bianji);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tv_jobinfo_1.setCompoundDrawables(drawable1, null, null, null);
                tv_jobinfo_1.setText("编辑");

                drawable2 = getResources().getDrawable(R.mipmap.job_star);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tv_jobinfo_2.setCompoundDrawables(drawable2, null, null, null);
                tv_jobinfo_2.setText("重新发布");

                drawable3 = getResources().getDrawable(R.mipmap.job_del);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tv_jobinfo_3.setCompoundDrawables(drawable3, null, null, null);
                tv_jobinfo_3.setText("删除");
                break;
            case "9":
                drawable1 = getResources().getDrawable(R.mipmap.job_bianji);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tv_jobinfo_1.setCompoundDrawables(drawable1, null, null, null);
                tv_jobinfo_1.setText("编辑");

//                drawable2 = getResources().getDrawable(R.mipmap.job_star);
//                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
//                tv_jobinfo_2.setCompoundDrawables(drawable2, null, null, null);
//                tv_jobinfo_2.setText("开启");

                tv_jobinfo_2.setVisibility(View.GONE);

                drawable3 = getResources().getDrawable(R.mipmap.job_del);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tv_jobinfo_3.setCompoundDrawables(drawable3, null, null, null);
                tv_jobinfo_3.setText("删除");
                break;
        }
    }

    private void initData() {
        getLimitJobNum();
        title = (TextView) findViewById(R.id.title);
        tv_jobinfo_1 = (TextView) findViewById(R.id.tv_jobinfo_1);
        tv_jobinfo_2 = (TextView) findViewById(R.id.tv_jobinfo_2);
        tv_jobinfo_3 = (TextView) findViewById(R.id.tv_jobinfo_3);

        tv_jobinfo_1.setOnClickListener(this);
        tv_jobinfo_2.setOnClickListener(this);
        tv_jobinfo_3.setOnClickListener(this);

        jobinfo = (WebView) findViewById(R.id.jobinfo_wv);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.setting).setVisibility(View.GONE);
        ivShare= (ImageView) findViewById(R.id.iv_share);
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setOnClickListener(this);
//		findViewById(R.id.share_job).setOnClickListener(this);
        title.setText("职位详情");
        app = (App) getApplication();
        jobinfoHandler = new JobInfoHandler(this);
        job_id = getIntent().getStringExtra("job_id");
        gettype = getIntent().getStringExtra("gettype");
        job_name = getIntent().getStringExtra("job_name");
//		crypt_job_id = getIntent().getStringExtra("crypt_job_id");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("enterprise_id", app.getUser().getEnterprise_user_id());
        params.put("job_id", job_id);
        params.put("industry", app.getUser().getSite_code());
        MsgHandler.sendMessage(params, jobinfoHandler, JobInfoHandler.wGetJobInfoStart);
    }

    /**
     * 加载显示页面
     */
    public void refshWebView(String html) {
        jobinfo.loadDataWithBaseURL("", html, "text/html", "utf-8", "");
        if (html != null && !html.equals("")) {
			isShared = true;
        }
    }

    /**
     * 获取分享字符串
     */
	private String getShareUrl() {
		String industry_id = app.getUser().getSite_code();
		StringBuilder builder = new StringBuilder("http://m.");
		if (industry_id.equals("11")) {// 建筑行业
			builder.append("buildhr");
		} else if (industry_id.equals("12")) {// 金融行业
			builder.append("bankhr");
		} else if (industry_id.equals("13")) {// 传媒行业
			builder.append("media.800hr");
		} else if (industry_id.equals("14")) {// 医药行业
			builder.append("healthr");
		} else if (industry_id.equals("15")) {// 教培行业
			builder.append("edu.800hr");
		} else if (industry_id.equals("19")) {// 电子行业
			builder.append("ele.800hr");
		} else if (industry_id.equals("22")) {// 机械行业
			builder.append("mechr");
		} else if (industry_id.equals("26")) {// 服装行业
			builder.append("clothr");
		} else if (industry_id.equals("29")) {// 化工行业
			builder.append("chenhr");
		}
		builder.append(".com/job/");
		builder.append(crypt_job_id);
        builder.append(".html");
		return builder.toString();
	}
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.setting:
                break;
            case R.id.tv_jobinfo_1:
//                switch (gettype) {
//                    case "1":
//                        break;
//                    case "2":
//                        break;
//                    case "3":
//                        break;
//                    case "5":
//                        break;
//                    case "9":
//                        break;
//                }
                Intent editIntent = new Intent(this, EditJobActivity.class);
                editIntent.putExtra("job_id", job_id);
                startActivity(editIntent);
                break;
            case R.id.tv_jobinfo_2:
                switch (gettype) {
                    case "1":
                        pauseJob();
                        break;
                    case "2":
                        if(current_limitNum>0) {
                            initPopuwindow();
                        }else{
                            Toast.makeText(this,"发布职位限量不足",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "3":
                        if(current_limitNum>0) {
                            issueJob();
                        }else{
                            Toast.makeText(this,"发布职位限量不足",Toast.LENGTH_SHORT).show();
                        }
                    case "5":
                        if(current_limitNum>0) {
                            initPopuwindow();
                        }else{
                            Toast.makeText(this,"发布职位限量不足",Toast.LENGTH_SHORT).show();
                        }
                    case "9":
                        break;
                }
                break;
            case R.id.tv_jobinfo_3:
                switch (gettype) {
                    case "1":
                        Bundle bundle = new Bundle();
                        bundle.putString("type",Const.JOB);
                        bundle.putString("typeID",job_id);
                        bundle.putString("typeName",job_name);
                        bundle.putString("boxtype", "");
                        Intent intent = new Intent(this, ResumeScanActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2":
                        initPopuwindowDelete();
                        break;
                    case "3":
                        initPopuwindowDelete();
                        break;
                    case "5":
                        initPopuwindowDelete();
                        break;
                    case "9":
                        initPopuwindowDelete();
                        break;
                }
                break;
            //分享功能
		case R.id.iv_share:
			if (!isShared) {
				Toast.makeText(this, "没有职位详情，不能分享", Toast.LENGTH_SHORT).show();
			    return;
		    }
			showShare(getShareUrl());
			break;
        }
    }

    /**
     * 删除职位
     */
    private void delJob() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBDEL);
        params.put("job_id", job_id);
        MsgHandler.sendMessage(params, jobinfoHandler, JobInfoHandler.wJobInfoDelStart);
    }

    /**
     * 重新发布职位跳出的提示页面
     */
    private void initPopuwindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindow_dialog, null);
        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        Button btn_OK= (Button) contentView.findViewById(R.id.btn_popSubmit);
        Button btn_cancle= (Button) contentView.findViewById(R.id.btn_cancle);
      btn_cancle.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
              popupWindow.dismiss();
          }
      });
        btn_OK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                issueJob();
                popupWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.fragment_home, null);
        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void initPopuwindowDelete() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindow_dialog, null);
        popupWindowDelete = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        TextView tv_msg= (TextView) contentView.findViewById(R.id.iv_popMsg);
        tv_msg.setText(R.string.deleteJob);
        Button btn_OK= (Button) contentView.findViewById(R.id.btn_popSubmit);
        Button btn_cancle= (Button) contentView.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindowDelete.dismiss();
            }
        });
        btn_OK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                delJob();
                popupWindowDelete.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.fragment_home, null);
        popupWindowDelete.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void getLimitJobNum(){
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.CONTRACTSTATE);
            MsgHandler.sendMessage(params, handler,
                    JobInfoHandler.wGetServiceStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 发布职位
     */
    private void issueJob() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBISSUE);
        params.put("job_id", job_id);
        MsgHandler.sendMessage(params, jobinfoHandler, JobInfoHandler.wJobIssueStart);
    }

    /**
     * 暂停职位
     */
    private void pauseJob() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBPAUSE);
        params.put("job_id", job_id);
        MsgHandler.sendMessage(params, jobinfoHandler, JobInfoHandler.wJobPasueStart);
    }

    	private void showShare(String shareStr) {
            ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		oks.addHiddenPlatform(QQ.NAME);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(shareStr);// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(shareStr);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(shareStr);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(shareStr);
		// 启动分享GUI,防止用户多次点击
		if (System.currentTimeMillis() - currTime > 500) {
			oks.show(this);
		}
		currTime = System.currentTimeMillis();
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
        super.onDestroy();
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
        if(popupWindowDelete!=null){
            popupWindowDelete.dismiss();
        }
    }
}
