package com.hr.ent.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.hr.ent.R;
import com.hr.ent.utils.SystemBarTintManager;

/**
 * Created by wdr on 2017/9/30.
 */

public class WindowUtils {
    private static SystemBarTintManager tintManager;
    @TargetApi(19)
    public static void initWindow(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.new_main));// 通知栏所需颜色
        }
    }
}
