package com.hr.ent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreference工具类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-3-27
 */
public class SharedPreferencedUtils {
	private SharedPreferences sharedPreferences;
	private Editor editor;

	/**
	 * 
	 * @param context
	 * @param name
	 */
	public SharedPreferencedUtils(Context context, String name) {
		sharedPreferences = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	/**
	 * the name of sharedpreferenced is sp800hr.
	 * 
	 * @param context
	 */
	public SharedPreferencedUtils(Context context) {
		sharedPreferences = context.getSharedPreferences(Const.PREFS_NAME,
				Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getStringValue(String key, String defValue) {
		return sharedPreferences.getString(key, defValue);
	}

	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getIntValue(String key, int defValue) {
		return sharedPreferences.getInt(key, defValue);
	}

	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloatValue(String key, float defValue) {
		return sharedPreferences.getFloat(key, defValue);
	}

	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBooleanValue(String key, boolean defValue) {
		return sharedPreferences.getBoolean(key, defValue);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setStringValue(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setIntValue(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setFloatValue(String key, Float value) {
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setBooleanValue(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * delete all
	 */
	public void deleteAll() {
		editor.clear();
		editor.commit();
	}
}
