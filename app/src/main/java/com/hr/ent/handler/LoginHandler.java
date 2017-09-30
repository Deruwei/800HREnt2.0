package com.hr.ent.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.db.UserDao;
import com.hr.ent.model.User;
import com.hr.ent.task.ExitTask;
import com.hr.ent.task.LoginTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.ActivityManager;
import com.hr.ent.ui.LoginActivity;
import com.hr.ent.ui.MainActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;
import com.hr.ent.utils.PasswordEncryUtils;
import com.hr.ent.utils.SharedPreferencedUtils;

import java.util.Map;

/**
 * 登录处理类
 *
 * @author 800hr：yelong
 *         <p>
 *         2015-6-26
 */
public class LoginHandler extends Handler {

    public static final int wLoginStart = 1;
    public static final int wLoginSuccess = 2;
    public static final int wLoginFailed = 3;

    public static final int wGetExitStart = 4;// 开始退出
    public static final int wGetExitSuccess = 5;// 退出成功
    public static final int wGetExitFailed = 6;// 退出失败

    private Context context;
    private App app;

    public LoginHandler(Context context) {
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
            case wGetExitStart:
                wGetExitStart((Map<String, Object>) msg.obj);
                break;
            case wGetExitSuccess:
                wGetExitSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetExitFailed:
                wGetExitFailed((Map<String, Object>) msg.obj);
                break;
        }
    }

    /**
     * 登录失败
     *
     * @param obj
     */
    private void wLoginFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && Integer.parseInt(obj.get(Const.ERROR_CODE).toString()) == Const.ApiSuccess) {
            Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
            if (utils.getBooleanValue(Const.AUTOLOGIN, false)) {
                Intent intent = new Intent(Const.LOGIN_FAIL);
                context.sendBroadcast(intent);
            }
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
            try {
                SharedPreferencedUtils utils = new SharedPreferencedUtils(
                        context);
                if (utils.getBooleanValue(Const.AUTOLOGIN, false)) {
                    Intent intent = new Intent(Const.LOGIN_SUCCESS);
                    context.sendBroadcast(intent);
                }
                // 跳转到主界面
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);

                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                App app = (App) context.getApplicationContext();
                app.setUser((User) obj.get("result"));
                app.setIsLogin(true);
                PasswordEncryUtils encryUtils = new PasswordEncryUtils();
                app.getUser().setUser_pwd(encryUtils.encrypt(app.getUser().getUser_pwd()));
                UserDao userDao = new UserDao(context);
                userDao.save(app.getUser());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始登录
     *
     * @param obj
     */
    private void wLoginStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new LoginTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wLoginSuccess, wLoginFailed);
        executant.execute();
    }

    private void wGetExitFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "退出失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetExitSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && Integer.parseInt(obj.get(Const.ERROR_CODE).toString()) == Const.ApiSuccess) {
            Toast.makeText(context, "退出成功", Toast.LENGTH_SHORT).show();
            app.setSessionKey("");
            app.setContractBean(null);
            app.setUser(null);
            app.setIsLogin(false);
            if (app.isExit()) {// 退出
                ActivityManager.finishAll();
                // finish LoginActivity
                Intent intent = new Intent(Const.EXIT);
                context.sendBroadcast(intent);
            } else {// 注销登录
                ActivityManager.finishAll();
                // 删除用户名和密码
                UserDao userDao = new UserDao(context);
                userDao.deleteUser();
                // 删除记住密码和自动登陆
                SharedPreferencedUtils utils = new SharedPreferencedUtils(
                        context);
                if (utils.getBooleanValue(Const.AUTOLOGIN, false)) {
                    // 必须跳转到登录界面
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {// 不是自动登录，就存在登录界面，需要发送广播
//                    Intent intent = new Intent(Const.LOGIN_OUT);
//                    context.sendBroadcast(intent);
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
                utils.setBooleanValue(Const.SAVEPWD, false);
                utils.setBooleanValue(Const.AUTOLOGIN, false);
            }
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetExitStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ExitTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetExitSuccess, wGetExitFailed);
        executant.execute();
    }
}
