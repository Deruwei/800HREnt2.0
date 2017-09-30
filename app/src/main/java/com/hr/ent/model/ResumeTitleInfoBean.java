package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历标题实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-3
 */
@SuppressLint("ParcelCreator")
public class ResumeTitleInfoBean implements Parcelable {

	private String user_id;//用户ID
	private String resume_id;//简历ID
	private String title;//简历标题
	private String key_word;//标签
	private String resume_language;//简历语言
	private String uptime;//简历更新时间
	private String fill_scale;//简历完整度
	private String add_time;//简历创建时间
	private String modify_time;//简历修改时间
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(user_id);
		dest.writeString(resume_id);
		dest.writeString(title);
		dest.writeString(key_word);
		dest.writeString(resume_language);
		dest.writeString(uptime);
		dest.writeString(fill_scale);
		dest.writeString(add_time);
		dest.writeString(modify_time);

	}
	
	public static final Parcelable.Creator<ResumeTitleInfoBean> CREATOR = new Parcelable.Creator<ResumeTitleInfoBean>() {

		@Override
		public ResumeTitleInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeTitleInfoBean titleInfoBean = new ResumeTitleInfoBean();
			titleInfoBean.user_id = source.readString();
			titleInfoBean.resume_id = source.readString();
			titleInfoBean.title = source.readString();
			titleInfoBean.key_word = source.readString();
			
			titleInfoBean.resume_language = source.readString();
			titleInfoBean.uptime = source.readString();
			titleInfoBean.fill_scale = source.readString();
			titleInfoBean.add_time = source.readString();
			titleInfoBean.modify_time = source.readString();
			return titleInfoBean;
		}

		@Override
		public ResumeTitleInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeTitleInfoBean[size];
		}
		
	};

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getResume_id() {
		return resume_id;
	}

	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	public String getResume_language() {
		return resume_language;
	}

	public void setResume_language(String resume_language) {
		this.resume_language = resume_language;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getFill_scale() {
		return fill_scale;
	}

	public void setFill_scale(String fill_scale) {
		this.fill_scale = fill_scale;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	
	

}
