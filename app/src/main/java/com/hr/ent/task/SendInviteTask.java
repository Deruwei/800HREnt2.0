package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

/**
 * 发送通知信请求
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-26
 */
public class SendInviteTask extends ValidSessionTask {

	public SendInviteTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));

		// Gson gson = new Gson();
		// ResumeTempleListBean value = gson.fromJson(result,
		// ResumeTempleListBean.class);
		// resultMap.put("result", value);
		return resultMap;
	}
}