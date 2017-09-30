package com.hr.ent.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 获取手机信息的工具类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-3-26
 */
public class DeviceUtils {
    /**
     * 获得版本名称
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            String packName = context.getPackageName();
            verName = context.getPackageManager().getPackageInfo(packName, 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e("版本名称获取异常", e.getMessage());
        }
        return verName;
    }


    /**
     * 获取程序的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return 0;
    }

    /**
     * 获取设备的宽度
     *
     * @return
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static String getOS_Version() {
        return android.os.Build.VERSION.RELEASE;
    }
    /**
     * 获取设备的高度
     *
     * @return
     */
    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取设备屏幕的密度
     *
     * @param context
     * @return
     */
    public static int getDeviceDensity(Context context) {
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        return displayMetrics.densityDpi;
    }

    /**
     * 获取手机硬件信息 ---BRAND:手机系统定制商; DEVICE:设备参数; MODEL:版本
     *
     * @return 手机硬件信息
     */
    public static String get_model() {
        String model = Build.BRAND + " " + Build.DEVICE + "(" + Build.MODEL
                + ")";
        return model;
    }
}
