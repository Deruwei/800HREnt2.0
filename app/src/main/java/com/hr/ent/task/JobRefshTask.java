package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

/**
 * 刷新单个职位请求
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-13
 */
public class JobRefshTask extends ValidSessionTask {

	public JobRefshTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		//Log.i("============JobRefshTask",result.toString());
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		resultMap.putAll(params);
		// Gson gson = new Gson();
		// JobListBean value = gson.fromJson(result, JobListBean.class);
		// resultMap.put("result", value);
		return resultMap;
	}
}
