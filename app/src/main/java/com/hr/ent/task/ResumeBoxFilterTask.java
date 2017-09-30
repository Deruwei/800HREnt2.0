package com.hr.ent.task;

import android.content.Context;

import com.google.gson.Gson;
import com.hr.ent.model.ResumeTypeAllBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取简历数目请求
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-26
 */
public class ResumeBoxFilterTask extends ValidSessionTask {

	public ResumeBoxFilterTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		Gson gson = new Gson();
		ResumeTypeAllBean value = gson.fromJson(result, ResumeTypeAllBean.class);
		resultMap.put("result", value);
		return resultMap;
	}
}
