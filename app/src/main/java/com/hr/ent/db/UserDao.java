package com.hr.ent.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hr.ent.model.CrmInfo;
import com.hr.ent.model.User;

/**
 * 用户登录数据库管理类
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class UserDao {
	private SQLiteDatabase db;

	public UserDao(Context context) {
		DBManager.initialize(new DatabaseHelper(context));
	}

	private void openDB() {
		db = DBManager.getInstance().openDB();
	}

	private void closeDB() {
		DBManager.getInstance().closeDB();
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void save(User user) {
		openDB();
		CrmInfo crmInfo = user.getCrminfo();
		String sql = "replace into LoginUser VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			db.execSQL(
					sql,
					new Object[] { user.getEnterprise_user_id(),
							user.getUser_id(), user.getLogin_user_type(),
							user.getUser_name(), user.getUser_pwd(),
							user.getSite_code(), crmInfo.getName(),
							crmInfo.getTelphone(), crmInfo.getFax_tel(),
							crmInfo.getEmail(), crmInfo.getZipcode() });
		} catch (Exception e) {
			// TODO: handle exception
		}
		closeDB();
	}

	public void deleteUser() {
		openDB();
		db.execSQL("delete from LoginUser");
		closeDB();
	}

	/**
	 * 获取登录用户
	 * 
	 * @return
	 */
	public User getLoginUser() {
		openDB();
		User user = null;
		Cursor cur = null;
		String sql = "select * from LoginUser";
		try {
			cur = db.rawQuery(sql, null);
			while (cur.moveToNext()) {
				user = fetch(cur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!cur.isClosed())
				cur.close();
		}
		closeDB();
		return user;
	}

	private User fetch(Cursor cur) {
		User user = new User();
		user.setEnterprise_user_id(cur.getString(cur
				.getColumnIndex("enterprise_user_id")));
		user.setUser_id(cur.getString(cur.getColumnIndex("user_id")));
		user.setLogin_user_type(cur.getString(cur
				.getColumnIndex("login_user_type")));
		user.setUser_name(cur.getString(cur.getColumnIndex("user_name")));
		user.setSite_code(cur.getString(cur.getColumnIndex("site_code")));
		user.setUser_pwd(cur.getString(cur.getColumnIndex("user_pwd")));
		CrmInfo crmInfo = new CrmInfo();

		crmInfo.setName(cur.getString(cur.getColumnIndex("name")));
		crmInfo.setTelphone(cur.getString(cur.getColumnIndex("telphone")));
		crmInfo.setFax_tel(cur.getString(cur.getColumnIndex("fax_tel")));
		crmInfo.setEmail(cur.getString(cur.getColumnIndex("email")));
		crmInfo.setZipcode(cur.getString(cur.getColumnIndex("zipcode")));

		user.setCrminfo(crmInfo);

		return user;
	}
}
