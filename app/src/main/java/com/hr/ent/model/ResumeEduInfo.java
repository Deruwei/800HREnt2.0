package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历信息实体类
 * @author 800hr：zhuhui
 *
 * 2014-5-19
 */
@SuppressLint("ParcelCreator")
public class ResumeEduInfo implements Parcelable{
	
	private String user_id;//用户ID
	private String fromyear;//起止时间年份
	private String frommonth;//起止时间月份
	private String toyear;//起止时间至年份
	private String tomonth;//起止时间至月份
	private String schoolname;//学校名称
	private String moremajor;//
	private String degree;//学历
	private String edudetail;//培训详情
	private String is_overseas;//是否海外培训
	private String country;//国家
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String education_id;//培训ID

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(user_id);
		dest.writeString(fromyear);
		dest.writeString(frommonth);
		dest.writeString(toyear);
		
		dest.writeString(tomonth);
		dest.writeString(schoolname);
		dest.writeString(moremajor);
		dest.writeString(degree);
		
		dest.writeString(edudetail);
		dest.writeString(is_overseas);
		dest.writeString(country);
		dest.writeString(resume_id);
		
		dest.writeString(resume_language);
		dest.writeString(education_id);
	}
	
	public static final Parcelable.Creator<ResumeEduInfo> CREATOR = new Parcelable.Creator<ResumeEduInfo>() {

		@Override
		public ResumeEduInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeEduInfo baseInfo = new ResumeEduInfo();
			baseInfo.user_id = source.readString();
			baseInfo.frommonth = source.readString();
			baseInfo.resume_language = source.readString();
			baseInfo.toyear = source.readString();
			
			baseInfo.tomonth = source.readString();
			baseInfo.schoolname = source.readString();
			baseInfo.moremajor = source.readString();
			baseInfo.degree = source.readString();
			baseInfo.edudetail = source.readString();
			baseInfo.is_overseas = source.readString();
			baseInfo.country = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			baseInfo.education_id = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeEduInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeEduInfo[size];
		}
		
	};


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFromyear() {
		return fromyear;
	}

	public void setFromyear(String fromyear) {
		this.fromyear = fromyear;
	}

	public String getFrommonth() {
		return frommonth;
	}

	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}

	public String getToyear() {
		return toyear;
	}

	public void setToyear(String toyear) {
		this.toyear = toyear;
	}

	public String getTomonth() {
		return tomonth;
	}

	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getMoremajor() {
		return moremajor;
	}

	public void setMoremajor(String moremajor) {
		this.moremajor = moremajor;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEdudetail() {
		return edudetail;
	}

	public void setEdudetail(String edudetail) {
		this.edudetail = edudetail;
	}

	public String getIs_overseas() {
		return is_overseas;
	}

	public void setIs_overseas(String is_overseas) {
		this.is_overseas = is_overseas;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getEducation_id() {
		return education_id;
	}

	public void setEducation_id(String education_id) {
		this.education_id = education_id;
	}
}
