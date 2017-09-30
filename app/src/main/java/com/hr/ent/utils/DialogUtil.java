package com.hr.ent.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.hr.ent.R;

public class DialogUtil {

	public static Dialog createLoadingDialog(Context context, String msg) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_loading, new LinearLayout(context), false);
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.dialog_view);

		TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);
		tipTextView.setText(msg);

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
		loadingDialog.setCancelable(false);
		loadingDialog.setCanceledOnTouchOutside(false);
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		return loadingDialog;
	}

	public static Dialog createLoadingDialog(Context context, String msg,boolean isCancel) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_loading_new, new LinearLayout(context), false);
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.dialog_view);
		
		ImageView imageView=(ImageView) view.findViewById(R.id.loading_img);
		Animation animation=AnimationUtils.loadAnimation(context, R.anim.loading_anim);
		imageView.setAnimation(animation);

		TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);
		tipTextView.setText(msg);

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
		loadingDialog.setCancelable(isCancel);
		loadingDialog.setCanceledOnTouchOutside(isCancel);
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		return loadingDialog;
	}
	
}
