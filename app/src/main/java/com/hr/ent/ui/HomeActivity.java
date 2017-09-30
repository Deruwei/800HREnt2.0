package com.hr.ent.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.HomeHandler;
import com.hr.ent.model.ContractLimitInfoBean;
import com.hr.ent.model.CrmInfo;
import com.hr.ent.model.User;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.PopUtil;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 主菜单Home界面
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class HomeActivity extends BaseActivity implements OnClickListener {
	private TextView title;
	private TextView welcome_info;
	private TextView new_resume;
	private TextView unread_resume;
	private TextView job_publish;
	private TextView high_job_publish;
	private TextView resume_download;
	private TextView high_resume_download;
	private TextView sms_send;
	private TextView recruiter_name;
	private TextView recruiter_tel;

	private TextView contract_state_tv;
	private ImageView contract_state;

	private App app;
	private HomeHandler handler;

	private PopUtil popUtil;

	private String day_count = "0";
	private String unread = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowUtils.initWindow(this);
		setContentView(R.layout.activity_home);
		popUtil = new PopUtil(this, false, null);
		app = (App) getApplication();
		handler = new HomeHandler(this);
		init();
		User user = app.getUser();
		CrmInfo crmInfo = user.getCrminfo();
		welcome_info.setText(getTime());//时间
		recruiter_name.setText(crmInfo.getName());//名字
		recruiter_tel.setText(crmInfo.getTelphone());//电话
		initContractState();
		initBaseInfo();
		initServiceDataByNet();
	}

	private void init() {
		findViewById(R.id.back).setVisibility(View.GONE);
		title = (TextView) findViewById(R.id.title);
		title.setText("Home");
		findViewById(R.id.setting).setOnClickListener(this);
		welcome_info = (TextView) findViewById(R.id.welcome_info);
		new_resume = (TextView) findViewById(R.id.new_resume);
		unread_resume = (TextView) findViewById(R.id.unread_resume);
		job_publish = (TextView) findViewById(R.id.job_publish);
		high_job_publish = (TextView) findViewById(R.id.high_job_publish);
		resume_download = (TextView) findViewById(R.id.resume_download);
		high_resume_download = (TextView) findViewById(R.id.high_resume_download);
		sms_send = (TextView) findViewById(R.id.sms_send);
		recruiter_name = (TextView) findViewById(R.id.recruiter_name);
		recruiter_tel = (TextView) findViewById(R.id.recruiter_tel);//电话

		contract_state = (ImageView) findViewById(R.id.contract_state);
		contract_state_tv = (TextView) findViewById(R.id.contract_state_tv);
		findViewById(R.id.call_img).setOnClickListener(this);
		new_resume.setOnClickListener(this);
		unread_resume.setOnClickListener(this);
	}
	/**
	 * 返回当前时间字符串
	 * @return
	 */
	private String getTime() {
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour < 12) {
			return "上午好";
		} else {
			return "下午好";
		}
	}


	/**
	 * 初始化合同的状态
	 */
	private void initContractState() {
		int time = Integer.parseInt(app.getUser().getContractStatus().getTime());
		int use = Integer.parseInt(app.getUser().getContractStatus().getUse());
		if(use != 2 && time != 2)//过期切换页面
		{
			Intent mIntent = new Intent(HomeActivity.this,LogininvalidActivity.class);
			startActivity(mIntent);
			finish();
		}else if (use == 0 && time == 0) {// 暂停
			contract_state_tv.setTextColor(Color.GREEN);
			contract_state.setImageResource(R.drawable.contact_ing);
		} else if (use == 5 && time != 4) {// 暂停
			contract_state_tv.setText("暂停中");
			contract_state_tv.setTextColor(Color.GRAY);
			contract_state.setImageResource(R.drawable.contact_pause);
		} else if (time == 1 && use == 1) {// 受限
			contract_state_tv.setText("受限中");
			contract_state_tv.setTextColor(Color.GRAY);
			contract_state.setImageResource(R.drawable.contact_pause);
		} else if (time == 2 && use == 2) {// 合同中
			contract_state_tv.setText("合同中");
			contract_state_tv.setTextColor(Color.GREEN);
			contract_state.setImageResource(R.drawable.contact_ing);
		} else if (time == 4) {// 过期
			contract_state_tv.setText("过期中");
			contract_state_tv.setTextColor(Color.RED);
			contract_state.setImageResource(R.drawable.contact_over);
		} else {
			contract_state_tv.setText("暂停中");
			contract_state_tv.setTextColor(Color.GRAY);
			contract_state.setImageResource(R.drawable.contact_pause);
		}
	}

	public void initContractDataByNet() {
		if (app.getNetworkMng().isCanConnect()) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(HttpHelper.METHOD, HttpHelper.GETCONTRACT);
			MsgHandler.sendMessage(params, handler,
					HomeHandler.wGetContractStart);
		} else {
			Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
		}
	}

	private void initBaseInfo() {
		if (app.getNetworkMng().isCanConnect()) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(HttpHelper.METHOD, HttpHelper.BASE_INFO);
			MsgHandler.sendMessage(params, handler,
					HomeHandler.wGetBaseInfoStart);
		} else {
			Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
		}                                                                               
	}

	public void updateName(String name) {
		welcome_info.setText(getTime() + "，" + name);
	}

	/**
	 * 获取服务器简历数
	 */
	private void initResumeNum() {
		if (app.getNetworkMng().isCanConnect()) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(HttpHelper.METHOD, HttpHelper.BOXFILTER);
			MsgHandler.sendMessage(params, handler,
					HomeHandler.wGetResumeFilterStart);
		} else {
			Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
		}
	}

	public void updateNewResumeNum(String day_count, String unread) {
		if (day_count != null && !day_count.equals("")) {
			this.day_count = day_count;
		}
		if (unread != null && !unread.equals("")) {
			this.unread = unread;
		}
		new_resume.setText("今日新增简历 " + this.day_count + " 份");
		unread_resume.setText("未读简历 " + this.unread + " 份");
	}

	private void initServiceDataByNet() {
		if (app.getNetworkMng().isCanConnect()) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(HttpHelper.METHOD, HttpHelper.CONTRACTSTATE);
			MsgHandler.sendMessage(params, handler,
					HomeHandler.wGetServiceStart);
		} else {
			Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 更新合同信息
	 * 
	 * @param bean
	 */
	public void updateServiceLimit(ContractLimitInfoBean bean) {
		int jobLimit = Integer.parseInt(bean.getJob_open_limit());
		int jobUsed = Integer.parseInt(bean.getJob_open_limit_used());

		int highJobLimit = Integer.parseInt(bean.getLimit_opentopjob());
		int highJobUsed = Integer.parseInt(bean.getLimit_opentopjob_used());

		int downResumeLimit = Integer.parseInt(bean.getBrowse_personal_limit());
		int downResemeUsed = Integer.parseInt(bean
				.getBrowse_personal_limit_used());

		int highDownResumeLimit = Integer
				.parseInt(bean.getView_topresume_num());
		int highDownResemeUsed = Integer.parseInt(bean
				.getView_topresume_num_used());

		int smsLimit = Integer.parseInt(bean.getSms_limit());
		int smsUsed = Integer.parseInt(bean.getSms_limit_used());

		job_publish.setText("职位发布限量：剩余" + (jobLimit - jobUsed) + "/总数" + jobLimit + "个");
		high_job_publish.setText("高端职位发布：剩余" + (highJobLimit - highJobUsed) + "/总数" + highJobLimit + "个");
		resume_download.setText("简历下载限量：剩余" + (downResumeLimit - downResemeUsed) + "/总数" + downResumeLimit + "个");
		high_resume_download.setText("高端简历下载：剩余" + (highDownResumeLimit - highDownResemeUsed) + "/总数" + highDownResumeLimit + "个");
		sms_send.setText("短信发送限量：剩余" + (smsLimit - smsUsed) + "/总数" + smsLimit + "个");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting:
			RelativeLayout view = (RelativeLayout) findViewById(R.id.head_title_layout);
			popUtil.showWindow(view);

			break;
		case R.id.call_img:// 打电话
			callPhone(recruiter_tel.getText().toString().trim());

			break;
		case R.id.new_resume:
			if (day_count.equals("") || day_count.equals("0")) {
				Toast.makeText(this, "今日没有新增简历", Toast.LENGTH_SHORT).show();
				return;
			}

			Bundle bundle = new Bundle();
			bundle.putString("type", "today");
			bundle.putString("typeID", "1");

			Intent intent = new Intent(this, ResumeScanActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);

			break;
		case R.id.unread_resume:
			if (unread.equals("") || unread.equals("0")) {
				Toast.makeText(this, "没有未读简历", Toast.LENGTH_SHORT).show();
				return;
			}

			Bundle bundle2 = new Bundle();
			bundle2.putString("type", "isnew");
			bundle2.putString("typeID", "2");

			Intent intent2 = new Intent(this, ResumeScanActivity.class);
			intent2.putExtras(bundle2);
			startActivity(intent2);

			break;
		}
		
	}

	/**
	 * 打电话
	 * 
	 * @param nums
	 */
	private void callPhone(String nums) {
		try {
			Uri uri = Uri.parse("tel:" + nums);
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			LogUtil.e("HomeActivity", "callPhone fail");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
		initResumeNum();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

}
