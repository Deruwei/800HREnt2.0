package com.hr.ent.task;

import android.content.Context;

import com.google.gson.Gson;
import com.hr.ent.model.ContractBean;
import com.hr.ent.model.ContractInfoBean;
import com.hr.ent.model.ContractServeInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;
import com.hr.ent.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取当前合同信息
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-25
 */
public class GetContractInfoTask extends ValidSessionTask {

	public GetContractInfoTask(Context context, Map<String, Object> params) {
		super(context, params);
	}

	@Override
	protected Map<String, Object> sendData(Map<String, Object> params)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = HttpUtils.sendData(Const.API_URL, params);
		JSONObject jo = new JSONObject(result);
		resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
		//LogUtil.i("GetContractTask", jo.get("return_data").toString());
		if (jo.get("return_data").equals("")) {
			resultMap.put("result", jo.get("return_data").toString());
		} else {
			List<ContractServeInfoBean> value=new ArrayList<>();
			Gson gson = new Gson();
			JSONArray arr=jo.getJSONArray("return_data");
			for(int i=0;i<arr.length();i++) {
				ContractServeInfoBean info = gson.fromJson(arr.get(i).toString(), ContractServeInfoBean.class);
				value.add(info);
			}
			resultMap.put("result", value);
			//LogUtil.i("GetContractTask", value.toString());
		}
		return resultMap;
	}
}
