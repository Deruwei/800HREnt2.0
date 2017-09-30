package com.hr.ent.task;

import android.content.Context;
import android.util.Log;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 刷新全部职位请求
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-13
 */
public class JobNumTask extends ValidSessionTask {

    public JobNumTask(Context context, Map<String, Object> params) {
        super(context, params);
    }

    @Override
    protected Map<String, Object> sendData(Map<String, Object> params)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = HttpUtils.sendData(Const.API_URL, params);
        Log.i("==========resultMap",result.toString());
        JSONObject jo = new JSONObject(result);
        resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
        resultMap.put("return_data", jo.getString("return_data"));
        return resultMap;
    }
}
