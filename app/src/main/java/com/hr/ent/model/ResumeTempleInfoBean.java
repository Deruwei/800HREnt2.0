package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通知信详情实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-5
 */
@SuppressLint("ParcelCreator")
public class ResumeTempleInfoBean implements Parcelable {
	private String id;//ID
	private String user_id;//用户ID
	private String type;//类型
	private String name;//名称
	private String email_title;//邮件标题
	private String content;//邮件内容
	private String sms_content;//短信内容
	private String last_time;//
	private String email;//邮箱

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(user_id);
		dest.writeString(type);
		dest.writeString(name);
		dest.writeString(email_title);
		dest.writeString(content);
		dest.writeString(sms_content);
		dest.writeString(last_time);
		dest.writeString(email);
	}
	
	public static final Parcelable.Creator<ResumeTempleInfoBean> CREATOR = new Parcelable.Creator<ResumeTempleInfoBean>() {

		@Override
		public ResumeTempleInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeTempleInfoBean crmInfo = new ResumeTempleInfoBean();
			crmInfo.id = source.readString();
			crmInfo.user_id = source.readString();
			crmInfo.type = source.readString();
			crmInfo.name = source.readString();
			crmInfo.email_title = source.readString();
			crmInfo.content = source.readString();
			crmInfo.sms_content = source.readString();
			crmInfo.last_time = source.readString();
			crmInfo.email = source.readString();
			return crmInfo;
		}

		@Override
		public ResumeTempleInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeTempleInfoBean[size];
		}
	};

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail_title() {
		return email_title;
	}

	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSms_content() {
		return sms_content;
	}

	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

}
