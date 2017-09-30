package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 自我评价实体类
 * @author 800hr：zhuhui
 *
 * 2014-5-19
 */
@SuppressLint("ParcelCreator")
public class ResumeAccessInfo implements Parcelable{
	
	private String user_id;//用户ID
	private String introduction;//自我评价
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
		dest.writeString(user_id);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
		dest.writeString(introduction);
	}
	
	public static final Parcelable.Creator<ResumeAccessInfo> CREATOR = new Parcelable.Creator<ResumeAccessInfo>() {

		@Override
		public ResumeAccessInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeAccessInfo baseInfo = new ResumeAccessInfo();
			baseInfo.user_id = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			baseInfo.introduction = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeAccessInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeAccessInfo[size];
		}
		
	};

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
