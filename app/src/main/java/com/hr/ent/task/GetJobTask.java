package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hr.ent.model.JobListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

/**
 * 获取发布中职位网络请求
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-13
 */
public class GetJobTask extends ValidSessionTask {

	public GetJobTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		//Log.i("数据",result.toString());
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));

		Gson gson = new Gson();
		JobListBean value = gson.fromJson(result, JobListBean.class);
		resultMap.put("result", value);
		return resultMap;
	}
}
