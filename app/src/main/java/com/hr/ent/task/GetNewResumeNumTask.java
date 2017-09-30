package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.hr.ent.model.NewResumeNum;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

/**
 * 获取主页面简历数目
 * 
 * @author 800hr：yelong
 * 
 *         2015-7-28
 */
public class GetNewResumeNumTask extends ValidSessionTask {

	public GetNewResumeNumTask(Context context, Map<String, Object> params) {
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
		NewResumeNum resumeNum = new NewResumeNum();
		resumeNum.setAllNum(returnData.optInt("all"));
		resumeNum.setNewNum(returnData.optInt("new"));
		resumeNum.setOldNum(returnData.optInt("old"));
		resumeNum.setStowNum(returnData.optInt("stow"));
		resumeNum.setTodayNum(returnData.optInt("today"));

		// Gson gson = new Gson();
		// NewResumeNum value = gson.fromJson(result, NewResumeNum.class);
		resultMap.put("result", resumeNum);
		return resultMap;
	}

}
