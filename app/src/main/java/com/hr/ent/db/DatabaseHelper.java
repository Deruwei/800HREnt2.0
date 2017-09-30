package com.hr.ent.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库创建辅助类
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, DataParam.DBNAME, null, DataParam.DBVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建用户表
		db.execSQL("create table if not exists LoginUser"
				+ "(enterprise_user_id varchar(15) primary key,user_id varchar(15),"
				+ "login_user_type varchar(15),user_name varchar(64),"
				+ "user_pwd varchar(64),site_code varchar(15),"
				+ "name varchar(64),telphone varchar(64),"
				+ "fax_tel varchar(64),email varchar(64),"
				+ "zipcode varchar(64))");
		// 创建常用联系人表
		db.execSQL("create table if not exists RecentlyEmail"
				+ "(user_id varchar(15),user_name varchar(64),"
				+ "site_code varchar(15),email varchar(64),"
				+ "dateline varchar(64))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
