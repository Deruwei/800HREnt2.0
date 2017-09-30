package com.hr.ent.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hr.ent.app.App;
import com.hr.ent.model.RecentlyEmailBean;

/**
 * 最近联系邮箱数据库控制类
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class RecentlyEmailDao {
	private SQLiteDatabase db;
	private App app;

	public RecentlyEmailDao(Context context) {
		DBManager.initialize(new DatabaseHelper(context));
		app = (App) context.getApplicationContext();
	}

	private void openDB() {
		db = DBManager.getInstance().openDB();
	}

	private void closeDB() {
		DBManager.getInstance().closeDB();
	}

	/**
	 * 保存邮箱信息
	 * 
	 * @param bean
	 */
	public void save(RecentlyEmailBean bean) {
		openDB();
		List<RecentlyEmailBean> emailBeans = getRecentlyEmailBean(app.getUser()
				.getUser_id(), app.getUser().getSite_code());

		for (RecentlyEmailBean bean2 : emailBeans) {
			if (bean.getEmail().equals(bean2.getEmail())) {
				updateEmail(bean);
				return;
			}
		}
		String sql = "insert into recentlyEmail values (?,?,?,?,?)";
		try {
			db.execSQL(
					sql,
					new Object[] { bean.getUser_id(), bean.getUser_name(),
							bean.getSite_code(), bean.getEmail(),
							bean.getDateline() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

	/**
	 * 更新Email信息
	 * 
	 * @param bean
	 */
	private void updateEmail(RecentlyEmailBean bean) {
		openDB();
		String sql = "update RecentlyEmail set dateline = ? where user_id = ? and site_code = ? and email = ?";
		try {
			db.execSQL(sql, new Object[] { bean.getDateline(),
					app.getUser().getUser_id(), app.getUser().getSite_code(),
					bean.getEmail() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

	/**
	 * 获取最近联系的邮箱信息
	 * 
	 * @param user_id
	 * @param site_code
	 * @return
	 */
	public List<RecentlyEmailBean> getRecentlyEmailBean(String user_id,
			String site_code) {
		openDB();
		List<RecentlyEmailBean> recentlyEmailBeans = new ArrayList<RecentlyEmailBean>();
		Cursor cur = null;
		RecentlyEmailBean bean = null;
		String sql = "select * from RecentlyEmail where user_id = ? and site_code = ? order by dateline desc";
		try {
			cur = db.rawQuery(sql, new String[] { user_id, site_code });
			while (cur.moveToNext()) {
				bean = new RecentlyEmailBean();
				bean.setUser_id(cur.getString(cur.getColumnIndex("user_id")));
				bean.setUser_name(cur.getString(cur.getColumnIndex("user_name")));
				bean.setSite_code(cur.getString(cur.getColumnIndex("site_code")));
				bean.setEmail(cur.getString(cur.getColumnIndex("email")));
				recentlyEmailBeans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!cur.isClosed())
				cur.close();
		}
		closeDB();
		return recentlyEmailBeans;
	}

}
