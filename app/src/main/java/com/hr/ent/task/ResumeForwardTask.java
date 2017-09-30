package com.hr.ent.task;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 简历转发请求
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-26
 */
public class ResumeForwardTask extends ValidSessionTask {

    public ResumeForwardTask(Context context, Map<String, Object> params) {
        super(context, params);
    }

    @Override
    protected Map<String, Object> sendData(Map<String, Object> params)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = HttpUtils.sendData(Const.API_URL, params);
        JSONObject jo = new JSONObject(result);
        resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
        return resultMap;
    }
}
