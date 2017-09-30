package com.hr.ent.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.hr.ent.app.App;

import java.util.Map;

public abstract class MsgHandler {

	/**
	 * 发送请求
	 * 
	 * @param what
	 */
	public static void sendMessage(Object object, Handler handler, int what) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = object;
		handler.sendMessage(msg);
	}

	/**
	 * 需要判断网络情况
	 * 
	 * @param params
	 * @param netWhat
	 * @param localWath
	 */
	public static void sendMessage(Context context, Map<String, Object> params,
			Handler handler, int netWhat, int localWath) {
		App app = (App) context.getApplicationContext();
		int what = netWhat;
		if (app.getNetworkMng().isCanConnect()) {
			what = netWhat;
		} else {
			what = localWath;
		}
		sendMessage(params, handler, what);
	}
}
