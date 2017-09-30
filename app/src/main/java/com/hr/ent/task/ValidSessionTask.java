package com.hr.ent.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.content.Context;
import android.util.DisplayMetrics;

import com.hr.ent.app.App;
import com.hr.ent.db.UserDao;
import com.hr.ent.model.User;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DeviceUtils;
import com.hr.ent.utils.EncryptUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.PasswordEncryUtils;
import com.hr.ent.utils.SharedPreferencedUtils;

/**
 * 所有需要session才能访问服务器的网络操作父类,具体操作实现sendData方法
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-3-26
 */
public abstract class ValidSessionTask implements Task {
	// private static final String TAG = "ValidSessionTask";
	protected Context context;
	protected Map<String, Object> params;
	private UserDao userDao;

	private User user;

	public ValidSessionTask(Context context, Map<String, Object> params) {
		super();
		this.context = context;
		this.params = params;
	}

	@Override
	public Map<String, Object> execute() throws Exception {
		App app = (App) (context.getApplicationContext());
		// 如果网络不能连接，立即返回
		if (!app.getNetworkMng().isCanConnect()) {
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("ret", Const.ApiNoNet);
			return r;
		}
		validateSession();

		Map<String, Object> result = sendData(getCommonParams());

		String code = result.get(Const.ERROR_CODE).toString();
		if (code.equals(Const.LoginInvalid + "")) {// 判断登录失效，需要重新登录
			userDao = new UserDao(context);
			user = userDao.getLoginUser();
			try {
				PasswordEncryUtils encryUtils = new PasswordEncryUtils();
				String pwd = encryUtils.decrypt(user.getUser_pwd());
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_LOGIN);
				params.put("user_name", user.getUser_name());
				params.put("user_pwd", pwd);
				params.put("site_code", "10");
				params.put("http_referer", "");

				LoginTask task = new LoginTask(context, params);
				Map<String, Object> resultMap = task.execute();

				if (resultMap.containsKey(Const.ERROR_CODE)
						&& resultMap.get(Const.ERROR_CODE).toString()
								.equals(Const.ApiSuccess + "")) {
					return sendData(getCommonParams());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return result;
		}
		return null;
	}

	// 同步方法,确认当前登陆是一个有效的登陆
	private void validateSession() throws Exception {
		App app = (App) (context.getApplicationContext());

		synchronized (app) {
			// 如果是有效的登陆，则直接返回
			if (app.isValidSession()) {
				return;
			}
			// 现在是登陆已经超时了，并且用户也曾经登陆，所以直接登陆，然后发送数据
			Map<String, Object> p = new HashMap<String, Object>();
			p.put(HttpHelper.METHOD, HttpHelper.USER_CONNECT);
			p.put("api_ver", Const.API_VER);
			p.put("client_ver", DeviceUtils.getAppVersionCode(context));
			p.put("os_name", Const.OS_NAME);
			p.put("os_ver", DeviceUtils.getOS_Version());
			p.put("width", String.valueOf(DeviceUtils.getDeviceWidth(context)));
			p.put("height",
					String.valueOf(DeviceUtils.getDeviceHeight(context)));
			p.put("model", DeviceUtils.get_model());
			p.put("dnfrom", Const.DNFROM);
			p.put("appcode", "personal");
			p.put("network_type", app.getNetworkMng().getNetworkType());
			SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
			if (utils.getStringValue(Const.IS_WELCOME, "1") == "1") {
				utils.setStringValue(Const.IS_WELCOME, "0");
				p.put("new_setup", "1");
				utils.setStringValue(Const.DEVICE_USER_ID, UUID.randomUUID()
						.toString().replaceAll("-", ""));
			} else {
				p.put("new_stup", "0");
			}
			p.put("phonecode", utils.getStringValue(Const.DEVICE_USER_ID, ""));
			GetSessionTask task = new GetSessionTask(context, p);
			p = task.execute();
			String error_code = p.get(Const.ERROR_CODE).toString();
			if (error_code.equals("203") || error_code.equals("204")
					|| error_code.equals("205") || error_code.equals("205.2")) {
				reConnect(initReconnectParams(context));
			}
			// if(Integer.parseInt(p.get(Const.ERROR_CODE).toString()) !=
			// Const.ApiSuccess){
			// //如果会话请求不成功则抛出异常
			// throw new BackGroundGetSessionException();
			// }
		}
	}

	private void reConnect(Map<String, Object> hashMap) {
		// TODO Auto-generated method stub
		GetSessionTask task2 = new GetSessionTask(context, hashMap);
		try {
			hashMap = task2.execute();
			String error_code = hashMap.get(Const.ERROR_CODE).toString();
			if (error_code.equals("203") || error_code.equals("204")
					|| error_code.equals("205") || error_code.equals("205.2")) {
				reConnect(initReconnectParams(context));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化重连接字段
	 */
	private static HashMap<String, Object> initReconnectParams(Context context) {
		App app = (App) (context.getApplicationContext());
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		requestParams.put("method", "user.connect");
		requestParams.put("api_ver", Const.API_VER);
		requestParams.put("client_ver", DeviceUtils.getAppVersionCode(context));
		requestParams.put("os_name", "android");
		requestParams.put("os_ver", android.os.Build.VERSION.RELEASE);
		DisplayMetrics displayMetrics = context.getResources()
				.getDisplayMetrics();
		requestParams.put("width", String.valueOf(displayMetrics.widthPixels));
		requestParams
				.put("height", String.valueOf(displayMetrics.heightPixels));

		SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
		requestParams.put("phonecode",
				utils.getStringValue(Const.DEVICE_USER_ID, ""));
		requestParams.put("model", DeviceUtils.get_model());
		requestParams.put("dnfrom", Const.DNFROM);
		requestParams.put("new_setup", "0");
		requestParams.put("appcode", "personal");
		requestParams.put("network_type", app.getNetworkMng().getNetworkType());
		EncryptUtils.secret_key = Const.secret_key;
		// System.out.println("重连");
		return requestParams;
	}

	private Map<String, Object> getCommonParams() {
		Map<String, Object> p = new HashMap<String, Object>();
		App app = (App) (context.getApplicationContext());
		p.put("session_key", app.getSessionKey());

		if (params != null) {
			p.putAll(params);
		}
		return p;
	}

	@Override
	public Object getParam() {
		return this.params;
	}

	/**
	 * 提交服务时,要将此params参数提交给HttpUtils的方法,可以删除或者添加参数.
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected abstract Map<String, Object> sendData(Map<String, Object> params)
			throws Exception;

}
