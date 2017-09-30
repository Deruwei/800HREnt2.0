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
public class UpdateJobArrayTask extends ValidSourceTask {

	public UpdateJobArrayTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
//		Log.i("========Wealcome1",params.toString()+"");
		HttpUtils.updateArrayFile(context, Const.JOB_ARRAY_URL, Const.JOB_FILENAME);
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
