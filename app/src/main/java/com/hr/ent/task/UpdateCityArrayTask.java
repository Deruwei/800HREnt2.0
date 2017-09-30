package com.hr.ent.task;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新程式数组数据
 * 
 *         2014-4-29
 */
public class UpdateCityArrayTask extends ValidSourceTask {

	public UpdateCityArrayTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
//		Log.i("========WealcomeCity",params.toString()+"");
		HttpUtils.updateArrayFile(context, Const.CITY_ARRAY_URL, Const.CITY_FILENAME);
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
