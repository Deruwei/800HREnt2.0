package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.task.Task;
import com.hr.ent.task.UpdateArrayTask;
import com.hr.ent.task.UpdateCityArrayTask;
import com.hr.ent.task.UpdateJobArrayTask;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpUtils;
import com.hr.ent.utils.SharedPreferencedUtils;

import java.util.HashMap;
import java.util.Map;

public class WelcomeHandler extends Handler {

    public static final int wUpdateArrayStart = 4;
    public static final int wUpdateArraySuccess = 5;
    public static final int wUpdateArrayFailed = 6;

    private static final int wUpdateCityArrayStart = 7;// 更新城市数组信息
    private static final int wUpdateCityArraySuccess = 8;// 更新成功
    private static final int wUpdateCityArrayFailed = 9;// 更新失败

    private static final int wUpdateJobArrayStart = 10;// 更新Job数组信息
    private static final int wUpdateJobArraySuccess = 11;// 更新成功
    private static final int wUpdateJobArrayFailed = 12;// 更新失败

    private String cityVer;
    private String cityDate;
    private String jobVer = "";
    private String jobDate = "";

    private Context context;

    public WelcomeHandler(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wUpdateArrayStart:
                wUpdateArrayStart((Map<String, Object>) msg.obj);
                break;
            case wUpdateArraySuccess:
                wUpdateArraySuccess((Map<String, Object>) msg.obj);
                break;
            case wUpdateArrayFailed:
                wUpdateArrayFailed((Map<String, Object>) msg.obj);
                break;
            case wUpdateCityArrayStart:
                wUpdateCityArrayStart((Map<String, Object>) msg.obj);
                break;
            case wUpdateCityArraySuccess:
                wUpdateCityArraySuccess((Map<String, Object>) msg.obj);
                break;
            case wUpdateCityArrayFailed:
                wUpdateCityArrayFailed((Map<String, Object>) msg.obj);
                break;
            case wUpdateJobArrayStart:
                wUpdateJobArrayStart((Map<String, Object>) msg.obj);
                break;
            case wUpdateJobArraySuccess:
                wUpdateJobArraySuccess((Map<String, Object>) msg.obj);
                break;
            case wUpdateJobArrayFailed:
                wUpdateJobArrayFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    private void wUpdateJobArrayFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "更新职位数组失败", Toast.LENGTH_SHORT).show();
    }

    private void wUpdateJobArraySuccess(Map<String, Object> obj) {
        SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
        utils.setStringValue(Const.JOB_VER, jobVer);
        utils.setStringValue(Const.JOB_DATE, jobDate);
    }

    private void wUpdateJobArrayStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        obj = new HashMap<String, Object>();
        obj.put(HttpUtils.METHOD, HttpUtils.METHOD_GET);
        Task task = new UpdateJobArrayTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, this, wUpdateJobArraySuccess, wUpdateJobArrayFailed);
        executant.execute();
    }

    private void wUpdateCityArrayFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "更新城市数组失败", Toast.LENGTH_SHORT).show();
    }

    private void wUpdateCityArraySuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
        utils.setStringValue(Const.CITY_VER, cityVer);
        utils.setStringValue(Const.CITY_DATE, cityDate);
    }

    private void wUpdateCityArrayStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        obj = new HashMap<String, Object>();
        obj.put(HttpUtils.METHOD, HttpUtils.METHOD_GET);
        Task task = new UpdateCityArrayTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, this, wUpdateCityArraySuccess, wUpdateCityArrayFailed);
        executant.execute();
    }

    private void wUpdateArrayFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
//        Toast.makeText(context, "更新职位数据失败", Toast.LENGTH_SHORT).show();
    }

    private void wUpdateArraySuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
//        Toast.makeText(context, "更新职位数据成功", Toast.LENGTH_SHORT).show();
        cityVer = obj.get("cityVer").toString();
        cityDate = obj.get("cityDate").toString();
        jobVer = obj.get("jobVer").toString();
        jobDate = obj.get("jobDate").toString();
        Log.i("========Wealcome","cityVer="+cityVer+" jobVer"+jobVer+" jobDate"+jobDate+" ");
        SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
//
//        if (!(utils.getStringValue(Const.JOB_VER, "0.0").equals(jobVer) && utils
//                .getStringValue(Const.JOB_DATE, "2010").equals(jobDate))) {
//            Log.i("========WealcomeJOB_VER","cityVer="+cityVer+" jobVer"+jobVer+" jobDate"+jobDate+" ");
//        }
//        if (!(utils.getStringValue(Const.CITY_VER, "0.0").equals(cityVer) && utils
//                .getStringValue(Const.CITY_DATE, "2010").equals(cityDate))) {
//            Log.i("========WealcomecityVer","cityVer="+cityVer+" jobVer"+jobVer+" jobDate"+jobDate+" ");
//        }
        sendEmptyMessage(wUpdateCityArrayStart);
        sendEmptyMessage(wUpdateJobArrayStart);
    }

    private void wUpdateArrayStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new UpdateArrayTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, this, wUpdateArraySuccess, wUpdateArrayFailed);
        Log.i("========Wealcome","1111");
        executant.execute();
    }
}
