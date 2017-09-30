package com.hr.ent.task;

import android.content.Context;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Colin
 * 日期：2016/9/1 15:01
 * 邮箱：bestxt@qq.com
 * <p/>
 * 意向沟通
 */
public class IntentionApplyTask extends ValidSessionTask {
    public IntentionApplyTask(Context context, Map<String, Object> params) {
        super(context, params);
    }

    @Override
    protected Map<String, Object> sendData(Map<String, Object> params) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = HttpUtils.sendData(Const.API_URL, params);
        JSONObject jo = new JSONObject(result);
        resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
//        Log.i("==========ERROR_CODE",jo.toString());
//        Log.i("==========resultMap",resultMap.toString());
        return resultMap;
    }
}
