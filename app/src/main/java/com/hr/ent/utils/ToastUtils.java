package com.hr.ent.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;

/**
 * 自定义toast
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-26
 */
public class ToastUtils {

	public static void makeToast(Context context) {
		makeToast(context, null);
	}

	public static void makeToast(Context context, String text) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.toast_jobrefsh, null);
		TextView textView = (TextView) view.findViewById(R.id.toast_text);
		if (text != null) {
			textView.setText(text);
		}
		
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setView(view);
		toast.show();
	}

	public static void makeTopToast(Context context, String text) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.toast_resume_num, null);
		TextView textView = (TextView) view.findViewById(R.id.toast_text);
		if (text != null) {
			textView.setText(text);
		}

		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, dip2px(context, 70));
		toast.setView(view);
		toast.show();
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
