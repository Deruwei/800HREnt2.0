package com.hr.ent.task;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hr.ent.model.DepListBean;
import com.hr.ent.model.JobListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取发布中职位网络请求
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-13
 */
public class GetDepTask extends ValidSessionTask {

	public GetDepTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);

		Gson gson = new Gson();
		DepListBean value = gson.fromJson(result, DepListBean.class);
		resultMap.put(Const.ERROR_CODE,value.getError_code());
		resultMap.put("result", value);
		return resultMap;
	}
}
