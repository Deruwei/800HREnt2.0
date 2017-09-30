package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历其他项
 * @author 800hr：zhuhui
 *
 * 2015-1-22
 */
public class SubjoinInfoBean implements Parcelable {
	private String subjoin_id;
	private String topic;//主题
	private String miscinfo;//描述
	private String user_id;//用户ID
	private String resume_id;//简历ID
	private String resume_language;//简历语言

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(subjoin_id);
		dest.writeString(topic);
		dest.writeString(miscinfo);
		dest.writeString(user_id);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
	}

	public static final Parcelable.Creator<SubjoinInfoBean> CREATOR = new Parcelable.Creator<SubjoinInfoBean>() {

		@Override
		public SubjoinInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			SubjoinInfoBean infoBean = new SubjoinInfoBean();
			infoBean.subjoin_id = source.readString();
			infoBean.topic = source.readString();
			infoBean.miscinfo = source.readString();
			infoBean.user_id = source.readString();
			infoBean.resume_id = source.readString();
			infoBean.resume_language = source.readString();
			return infoBean;
		}

		@Override
		public SubjoinInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new SubjoinInfoBean[size];
		}
		
	};
	
	public String getSubjoin_id() {
		return subjoin_id;
	}

	public void setSubjoin_id(String subjoin_id) {
		this.subjoin_id = subjoin_id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMiscinfo() {
		return miscinfo;
	}

	public void setMiscinfo(String miscinfo) {
		this.miscinfo = miscinfo;
	}

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

	public String getResume_language() {
		return resume_language;
	}

	public void setResume_language(String resume_language) {
		this.resume_language = resume_language;
	}
}
