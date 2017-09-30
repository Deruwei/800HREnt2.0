package com.hr.ent.ui;

import android.content.Context;
import android.view.ContextThemeWrapper;

import com.hr.ent.utils.Const;
import com.hr.ent.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by wdr on 2017/9/28.
 */

public class CityUtils {
    public static String getCityName(String string, Context context){
        String[] strings;
        try {
            if(string.contains(",")){
                strings=string.split(",");
            }else{
                strings=new String[]{string};
            }
            StringBuffer sb=new StringBuffer();
            InputStream inputStream = context.getAssets().open("city_zh.json");
            JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
            for(int i=0;i<strings.length;i++){
                for (int j = 0; j < cityJSONArray.length(); j++) {
                    JSONObject object = cityJSONArray.getJSONObject(j);
                    String s=object.toString().substring(2,6);
                    if(s.equals(string)){
                        sb.append(object.getString(s));
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getServiceState(String id){
        return "";
    }
}
