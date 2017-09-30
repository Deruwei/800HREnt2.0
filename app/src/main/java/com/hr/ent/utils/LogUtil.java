package com.hr.ent.utils;

import android.util.Log;

public class LogUtil {
	public static final boolean isLog = true;

	public static void v(String tag, String msg) {
		if (isLog) {
			Log.v(tag, msg);
		}
	}

	public static void v(String tag, String msg, Throwable thr) {
		if (isLog) {
			Log.v(tag, buildMessage(msg), thr);
		}
	}

	public static void d(String tag, String msg) {
		if (isLog) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable thr) {
		if (isLog) {
			Log.d(tag, buildMessage(msg), thr);
		}
	}

	public static void i(String tag, String msg) {
		if (isLog) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable thr) {
		if (isLog) {
			Log.i(tag, buildMessage(msg), thr);
		}
	}

	public static void w(String tag, String msg) {
		if (isLog) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable thr) {
		if (isLog) {
			Log.w(tag, buildMessage(msg), thr);
		}
	}

	public static void e(String tag, String msg) {
		if (isLog) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable thr) {
		if (isLog) {
			Log.e(tag, buildMessage(msg), thr);
		}
	}

	/**
	 * Building Message
	 */
	protected static String buildMessage(String msg) {
		StackTraceElement caller = new Throwable().fillInStackTrace()
				.getStackTrace()[2];

		return new StringBuilder().append(caller.getClassName()).append(".")
				.append(caller.getMethodName()).append("(): ").append(msg)
				.toString();
	}
}
