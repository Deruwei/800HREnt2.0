package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.hr.ent.utils.HttpUtils;

/**
 * 获取单个职位详细信息
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-26
 */
public class JobInfoTask extends ValidSourceTask {

	public JobInfoTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.postString(
				"http://api.800hr.com/ent/job.php", params);
		// JSONObject jo = new JSONObject(result);
		// resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));

		// Gson gson = new Gson();
		// JobListBean value = gson.fromJson(result, JobListBean.class);
		resultMap.put("result", result);
		return resultMap;
	}
}
