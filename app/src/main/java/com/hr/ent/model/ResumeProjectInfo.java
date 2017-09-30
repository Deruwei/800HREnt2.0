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
public class ResumeProjectInfo implements Parcelable{
	
	private String id;
	private String project_id;//项目ID
	private String fromyear;//起止时间年份
	private String frommonth;//起止时间月份
	private String toyear;//起止时间至年份
	private String tomonth;//起止时间至月份
	private String projectname;//项目名称
	private String position;//职位
	private String projectdesc;//项目描述
	private String responsibility;//项目职责
	private String user_id;//用户ID
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String user_name;//用户名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

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
		dest.writeString(project_id);
		dest.writeString(projectname);
		dest.writeString(position);
		dest.writeString(projectdesc);
		dest.writeString(responsibility);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
	}
	
	public static final Parcelable.Creator<ResumeProjectInfo> CREATOR = new Parcelable.Creator<ResumeProjectInfo>() {

		@Override
		public ResumeProjectInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeProjectInfo baseInfo = new ResumeProjectInfo();
			baseInfo.user_id = source.readString();
			baseInfo.fromyear = source.readString();
			baseInfo.frommonth = source.readString();
			baseInfo.toyear = source.readString();
			
			baseInfo.tomonth = source.readString();
			baseInfo.project_id = source.readString();
			baseInfo.projectname = source.readString();
			baseInfo.position = source.readString();
			baseInfo.projectdesc = source.readString();
			baseInfo.responsibility = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeProjectInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeProjectInfo[size];
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

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProjectdesc() {
		return projectdesc;
	}

	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
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
