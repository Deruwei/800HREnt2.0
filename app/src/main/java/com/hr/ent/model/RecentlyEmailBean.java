package com.hr.ent.model;

/**
 * 最近联系邮箱实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-11
 */
public class RecentlyEmailBean {
	
	private String user_id;
	private String user_name;
	private String site_code;
	private String email;
	private long dateline;
	
	public long getDateline() {
		return dateline;
	}
	public void setDateline(long dateline) {
		this.dateline = dateline;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
