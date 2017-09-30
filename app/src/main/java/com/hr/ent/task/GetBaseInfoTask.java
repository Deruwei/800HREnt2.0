package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

public class GetBaseInfoTask extends ValidSessionTask {

	public GetBaseInfoTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		JSONObject returnData = new JSONObject(jo.getString("return_data"));
		resultMap.put("result", returnData.optString("linkman"));
		return resultMap;
	}

}
