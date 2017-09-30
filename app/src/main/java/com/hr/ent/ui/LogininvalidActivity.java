package com.hr.ent.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.hr.ent.R;
import com.hr.ent.app.DownAPK;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

public class LogininvalidActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private static final String TAG = "ResumeInfoFragment";
	private TextView register_tip, register_tip1;
	private ImageView call_phone_2;// 打电话
	private Activity activity;
	ListView listview;
	ArrayList<HashMap<String, Object>> listdata;
	private String[] cellNums = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowUtils.initWindow(this);
		setContentView(R.layout.activity_logininvalid);
		ActivityManager.addActivity(this);
		register_tip = (TextView) findViewById(R.id.register_tip);
		register_tip1 = (TextView) findViewById(R.id.register_tip1);
		call_phone_2 = (ImageView) findViewById(R.id.call_phone_2);// 打电话
		listview = (ListView) findViewById(R.id.textView3);// 点击下载行业找工作
		register_tip.setOnClickListener(this);
		register_tip1.setOnClickListener(this);
		call_phone_2.setOnClickListener(this);// 打电话

		listdata = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("q", "个人求职请下载:");
		listdata.add(hs);

		MyBaseAdpter myBaseAdpter = new MyBaseAdpter(this, listdata);
		listview.setAdapter(myBaseAdpter);
		listview.setOnItemClickListener(this);
	}

	class MyBaseAdpter extends BaseAdapter {

		ArrayList<HashMap<String, Object>> adpterData;
		Context context;

		public MyBaseAdpter(Context context,
				ArrayList<HashMap<String, Object>> adpterData) {
			this.context = context;
			this.adpterData = adpterData;
		}

		@Override
		public int getCount() {
			return adpterData.size();
		}

		@Override
		public Object getItem(int position) {
			return adpterData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			MyHolder myholder = null;
			if (convertView == null) {
				myholder = new MyHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.application_listview, null);
				convertView.setTag(myholder);
				myholder.app_name = (TextView) convertView
						.findViewById(R.id.app_name);
			} else {
				myholder = (MyHolder) convertView.getTag();
			}
			myholder.app_name.setText(adpterData.get(position).get("q")
					.toString());
			return convertView;
		}

		private final class MyHolder {
			TextView app_name;
		}
	}
	/**
	 * 打电话
	 * 
	 * @param nums
	 */
	private void callPhone(String nums) {
		try {
			Uri uri = Uri.parse("tel:" + nums);
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			LogUtil.e(TAG, "callPhone fail");
		}
	}
	/**
	 *
	 * 显示 电话的对话框
	 * 
	 * @param nums
	 */
	private void showNumDialog(final String[] nums) {

		final AlertDialog dialog = new AlertDialog.Builder(activity).create();
		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_tell,
				new LinearLayout(activity), false);
		ListView listView = (ListView) view.findViewById(R.id.tell_list);
		listView.setAdapter(new ArrayAdapter<>(activity,
				android.R.layout.simple_list_item_1, nums));

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				callPhone(nums[position]);
			}
		});
		dialog.setView(view, 0, 0, 0, 0);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		setDialogSize(dialog, (float) 0.8);
	}

	/**
	 * 设置对话框的大小
	 * 
	 * @param dialog
	 */
	public void setDialogSize(AlertDialog dialog, float size) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		Window window = dialog.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = (int) (metrics.widthPixels * size);
		params.height = LayoutParams.WRAP_CONTENT;
		window.setAttributes(params);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_tip:
			Toast.makeText(getApplicationContext(), "点击了register_tip", 0)
					.show();
			break;
		case R.id.register_tip1:
			Toast.makeText(getApplicationContext(), "点击了register_tip1", 0)
					.show();
			break;
		case R.id.call_phone_2:// 电话那妞
			if (cellNums != null) {
				showNumDialog(cellNums);
			} else {
				callPhone(register_tip1.getText().toString().trim());
			}
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		MobclickAgent.onEvent(this, Const.DOWNAPK);
		ISFileExist isExist = new ISFileExist(
				Environment.getExternalStorageDirectory() + "/" + "800hr.apk");
		if (isExist.isexists()) {
			new AlertDialog.Builder(this)
					.setTitle("下载提示")
					.setCancelable(false)
					.setMessage("该应用已存在，确定下载新版本？")
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialoginterface, int i) {
									new DownAPK(LogininvalidActivity.this, "800hr.apk")
											.downFile("http://www.800hr.com/app/android/800hr.apk");

								}
							})
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// 不更新
									dialog.dismiss();

								}
							}).show();
		} else {
			new DownAPK(this, "800hr.apk")
					.downFile("http://www.800hr.com/app/android/800hr.apk");
		}
	}

	/**
	 * 一键退出
	 * 
	 * @Override
	 * 
	 *           public void onBackPressed() { // TODO Auto-generated method
	 *           stub AlertDialog.Builder builder = new
	 *           AlertDialog.Builder(this); builder.setTitle("温馨提示");
	 *           builder.setMessage("您确定退出吗？"); builder.setPositiveButton("确定",
	 *           new DialogInterface.OnClickListener() {
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 *           TODO Auto-generated method stub ActivityManager.finishAll(); }
	 *           }); builder.setNegativeButton("取消", new
	 *           DialogInterface.OnClickListener() {
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 *           TODO Auto-generated method stub
	 * 
	 *           } }); builder.show(); }
	 */
	/***
	 * 判断文件是否已存在
	 * */

	public class ISFileExist {
		File f;

		public ISFileExist(String fileutil) {
			f = new File(fileutil);
		}

		public boolean isexists() {
			return f.exists();
		}
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
