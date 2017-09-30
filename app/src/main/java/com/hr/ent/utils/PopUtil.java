package com.hr.ent.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.LoginHandler;

public class PopUtil implements OnClickListener {

	private Context context;
	private PopupWindow popupWindow;
	private View mView;

	private boolean isShare;
	private String shareStr;

	private App app;
	private LoginHandler handler;

	private long currTime = 0;// 分享的点击时间

	public PopUtil(Context context, boolean isShare, String shareStr) {
		this.context = context;
		this.isShare = isShare;
		this.shareStr = shareStr;
		app = (App) context.getApplicationContext();
		init();
	}

	public void init() {
		mView = LayoutInflater.from(context).inflate(R.layout.popup_setting,
				new LinearLayout(context), false);
		if (isShare) {
			mView.findViewById(R.id.setting_share).setVisibility(View.VISIBLE);
			mView.findViewById(R.id.setting_share_line).setVisibility(
					View.VISIBLE);
			mView.findViewById(R.id.setting_share).setOnClickListener(this);
		}
		mView.findViewById(R.id.setting_exit).setOnClickListener(this);
		mView.findViewById(R.id.setting_login_out).setOnClickListener(this);
		mView.findViewById(R.id.setting_contact_us).setOnClickListener(this);
		mView.findViewById(R.id.setting_app_down).setOnClickListener(this);
		mView.findViewById(R.id.setting_hot_ad).setOnClickListener(this);
		mView.findViewById(R.id.setting_brand_add).setOnClickListener(this);
	}

	public void showWindow(View anchor) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);

		popupWindow = new PopupWindow(mView, DensityUtil.dip2px(context, 150),
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setBackgroundDrawable(new ColorDrawable());
		popupWindow.setOutsideTouchable(true);
		popupWindow.setAnimationStyle(R.style.ModePopupAnimation);

		if (popupWindow.isShowing()) {
			popupWindow.dismiss();
		} else {
			popupWindow.showAsDropDown(anchor, metrics.widthPixels
					- DensityUtil.dip2px(context, 150), 0);
		}
	}

	@Override
	public void onClick(View v) {
		// 点击按键之前记得要关闭弹出框
		popupWindow.dismiss();
		switch (v.getId()) {
		case R.id.setting_share:
			showShare();

			break;
		case R.id.setting_exit:
			app.setExit(true);
			handler = new LoginHandler(context);
			if (app.getNetworkMng().isCanConnect()) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(HttpHelper.METHOD, HttpHelper.LOGOUT);
				MsgHandler.sendMessage(params, handler,
						LoginHandler.wGetExitStart);
			} else {
				Toast.makeText(context,
						context.getResources().getString(R.string.nonet),
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.setting_login_out:
			app.setExit(false);
			handler = new LoginHandler(context);
			if (app.getNetworkMng().isCanConnect()) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(HttpHelper.METHOD, HttpHelper.LOGOUT);
				MsgHandler.sendMessage(params, handler,
						LoginHandler.wGetExitStart);
			} else {
				Toast.makeText(context,
						context.getResources().getString(R.string.nonet),
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.setting_contact_us:
			callPhone("4006500588");

			break;
		case R.id.setting_app_down:
			openLink("http://www.800hr.com/app/android/800hr.apk");

			break;
		case R.id.setting_hot_ad:
			openLink(getURL(true));

			break;
		case R.id.setting_brand_add:
			openLink(getURL(false));

			break;
		}
	}

	private void showShare() {
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		oks.addHiddenPlatform(QQ.NAME);

		// text是分享文本，所有平台都需要这个字段
		oks.setText(shareStr);
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(shareStr);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(shareStr);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(shareStr);
		// 启动分享GUI,防止用户多次点击
		if (System.currentTimeMillis() - currTime > 500) {
			oks.show(context);
		}
		currTime = System.currentTimeMillis();
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
		}

		if (type) {
			builder.append(".com/so");
		} else {
			builder.append(".com/faxian");
		}
		return builder.toString();
	}

	private void callPhone(String nums) {
		try {
			Uri uri = Uri.parse("tel:" + nums);
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			context.startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			LogUtil.e("PopUtil", "callPhone fail");
		}
	}
}
