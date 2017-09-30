package com.hr.ent.task;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateArrayTask extends ValidSourceTask {

	public UpdateArrayTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		String result = HttpUtils.sendData(Const.ARRAY_URL, params);
		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject jo = new JSONObject(result);

		map.put("cityVer", jo.getJSONObject("city").getString("ver"));
		map.put("cityDate", jo.getJSONObject("city").getString("lasttime"));

		map.put("jobVer", jo.getJSONObject("job").getString("ver"));
		map.put("jobDate", jo.getJSONObject("job").getString("lasttime"));

		map.put("lingyuVer", jo.getJSONObject("lingyu").getString("ver"));
		map.put("lingyuDate", jo.getJSONObject("lingyu").getString("lasttime"));

		return map;
	}

}
