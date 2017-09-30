package com.hr.ent.task;

import android.content.Context;

import com.google.gson.Gson;
import com.hr.ent.model.User;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录网络请求
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-10
 */
public class LoginTask extends ValidSessionTask {

    public LoginTask(Context context, Map<String, Object> params) {
        super(context, params);
    }

    @Override
    protected Map<String, Object> sendData(Map<String, Object> params)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = HttpUtils.sendData(Const.API_URL, params);
        JSONObject jo = new JSONObject(result);
        resultMap.put(Const.ERROR_CODE, jo.getString(Const.ERROR_CODE));
        Gson gson = new Gson();
        User value = gson.fromJson(result, User.class);
        value.setUser_pwd(params.get("user_pwd").toString());
        resultMap.put("result", value);
        return resultMap;
    }
}
