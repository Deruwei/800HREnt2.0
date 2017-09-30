package com.hr.ent.task;

import android.content.Context;

import com.google.gson.Gson;
import com.hr.ent.model.DepInfoBean;
import com.hr.ent.model.DepListBean;
import com.hr.ent.model.GetJobInfoBean;
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
public class GetDepInfoTask extends ValidSessionTask {

	public GetDepInfoTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		String result2 = jo.getJSONObject("return_data").toString();
		Gson gson = new Gson();
		DepInfoBean value = gson.fromJson(result2, DepInfoBean.class);
		resultMap.put("result", value);
		return resultMap;
	}
}
