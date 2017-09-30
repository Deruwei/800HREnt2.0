package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.hr.ent.app.App;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.EncryptUtils;
import com.hr.ent.utils.HttpUtils;
import com.hr.ent.utils.Parser;

/**
 * 会话请求类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-3-27
 */
public class GetSessionTask extends NoNeedSessionTask {

	public Map<String, Object> params;// 请求参数
	private Context context;// 上下文对象

	public GetSessionTask(Context context, Map<String, Object> params) {
		super(context, params);
		this.params = params;
		this.context = context;
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		App app = (App) context.getApplicationContext();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String reult = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(reult);
		resultMap.put(Const.ERROR_CODE, jo.getInt(Const.ERROR_CODE));
		if (jo.getInt(Const.ERROR_CODE) == Const.ApiSuccess) {
			if (Parser.isNotNull(jo, "session_key"))
				app.setSessionKey(jo.getString("session_key"));
			if (Parser.isNotNull(jo, "secret_key"))
				EncryptUtils.secret_key = Const.pre_secret_key
						+ jo.getString("secret_key");
			if (Parser.isNotNull(jo, "svr_api_ver"))
				app.setApi_ver(jo.getString("svr_api_ver"));
			app.setSessionTime(System.currentTimeMillis());
		}
		return resultMap;
	}

}
