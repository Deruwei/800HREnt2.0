package com.hr.ent.ui;

import java.util.ArrayList;

import android.app.Activity;

/**
 * 管理所有的Activity
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class ActivityManager {

	public static ArrayList<Activity> activities = new ArrayList<Activity>();

	/**
	 * 添加指定的activity
	 * 
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	/**
	 * 移除指定的activity
	 * 
	 * @param activity
	 */
	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	/**
	 * 移除所有活动的Activity
	 */
	public static void finishAll() {
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
