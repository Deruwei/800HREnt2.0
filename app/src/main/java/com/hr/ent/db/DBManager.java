package com.hr.ent.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设置单例模式的数据库操作 1.私有的构造器 2.私有的静态对象 3.共有的访问该对象的方法
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class DBManager {
	private static DBManager instance;
	private static DatabaseHelper mHelper;
	private SQLiteDatabase mDatabase;
	// 打开数据库操作计数器
	private AtomicInteger mOpenCounter = new AtomicInteger();

	public static synchronized void initialize(DatabaseHelper helper) {
		if (instance == null) {
			instance = new DBManager();
			mHelper = helper;
		}
	}

	private DBManager() {

	}

	public static DBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(DBManager.class.getName()
					+ " is not initialize");
		}
		return instance;
	}

	public synchronized SQLiteDatabase openDB() {
		if (mOpenCounter.incrementAndGet() == 1) {
			mDatabase = mHelper.getWritableDatabase();
		}
		return mDatabase;
	}

	public synchronized void closeDB() {
		if (mOpenCounter.decrementAndGet() == 0) {
			mDatabase.close();
		}
	}

}
