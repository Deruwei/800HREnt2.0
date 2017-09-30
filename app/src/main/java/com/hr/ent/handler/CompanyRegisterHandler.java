package com.hr.ent.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.task.CompanyRegisterTask;
import com.hr.ent.ui.LoginActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.Map;

/**
 * 注册Handler
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-26
 */
public class CompanyRegisterHandler extends Handler {

    public static final int wLoginStart = 1;
    public static final int wLoginSuccess = 2;
    public static final int wLoginFailed = 3;

    private Context context;
    private App app;

    public CompanyRegisterHandler(Context context) {
        this.context = context;
        app = (App) context.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wLoginStart:
                wLoginStart((Map<String, Object>) msg.obj);
                break;
            case wLoginSuccess:
                wLoginSuccess((Map<String, Object>) msg.obj);
                break;
            case wLoginFailed:
                wLoginFailed((Map<String, Object>) msg.obj);
                break;
        }
    }

    /**
     * 登录失败
     * @param obj
     */
    private void wLoginFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && Integer.parseInt(obj.get(Const.ERROR_CODE).toString()) == Const.ApiSuccess) {
            Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录成功
     *
     * @param obj
     */
    private void wLoginSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            // 跳转到主界面
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始登录
     *
     * @param obj
     */
    private void wLoginStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        CompanyRegisterTask task = new CompanyRegisterTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wLoginSuccess, wLoginFailed);
        executant.execute();
    }
}
