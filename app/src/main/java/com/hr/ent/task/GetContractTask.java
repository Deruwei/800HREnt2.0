package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.hr.ent.model.ContractBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;
import com.hr.ent.utils.LogUtil;

/**
 * 获取当前合同信息
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-25
 */
public class GetContractTask extends ValidSessionTask {

	public GetContractTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		LogUtil.i("GetContractTask", jo.get("return_data").toString());
		if (jo.get("return_data").equals("")) {
			resultMap.put("result", jo.get("return_data").toString());
		} else {
			Gson gson = new Gson();
			ContractBean value = gson.fromJson(result, ContractBean.class);
			resultMap.put("result", value);
		}
		return resultMap;
	}
}
