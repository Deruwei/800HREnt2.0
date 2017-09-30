package com.hr.ent.task;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hr.ent.model.ResumeNavpageInfoBean;
import com.hr.ent.model.ResumeSearchResultBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取发布中职位网络请求
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-13
 */
public class GetSearchResumeTask extends ValidSessionTask {

    public GetSearchResumeTask(Context context, Map<String, Object> params) {
        super(context, params);
    }

    @Override
    protected Map<String, Object> sendData(Map<String, Object> params)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = HttpUtils.sendData(Const.API_URL, params);
        JSONObject jo = new JSONObject(result);
        Log.i("=======SearchResume", result);
        resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
        Gson gson = new Gson();
        ResumeNavpageInfoBean resumeNavpageInfo =gson.fromJson(jo.getJSONObject("return_data").getJSONObject("navpage_info").toString(),ResumeNavpageInfoBean.class);
        ResumeSearchResultBean value =gson.fromJson(jo.getJSONObject("return_data").toString(), ResumeSearchResultBean.class);
        resultMap.put("result", value);
        resultMap.put("resumeNavpageInfo", resumeNavpageInfo);
        return resultMap;
    }
}
