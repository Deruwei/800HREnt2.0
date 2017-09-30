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
public class ResumePlantInfo implements Parcelable{
	
	private String id;//培训经历本地ID
	private String plant_id;//培训经历ID
	private String fromyear;//起止时间年份
	private String frommonth;//起止时间月份
	private String toyear;//起止时间至年份
	private String tomonth;//起止时间至月份
	private String institution;//机构名称
	private String course;//课程名称
	private String place;//培训地点ID
	private String certification;//获得证书
	private String traindetail;//描述
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
		dest.writeString(plant_id);
		dest.writeString(fromyear);
		dest.writeString(frommonth);
		dest.writeString(toyear);
		dest.writeString(tomonth);
		dest.writeString(institution);
		dest.writeString(course);
		dest.writeString(place);
		dest.writeString(certification);
		dest.writeString(traindetail);
		dest.writeString(user_id);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
	}
	
	public static final Parcelable.Creator<ResumePlantInfo> CREATOR = new Parcelable.Creator<ResumePlantInfo>() {

		@Override
		public ResumePlantInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumePlantInfo baseInfo = new ResumePlantInfo();
			baseInfo.plant_id = source.readString();
			baseInfo.fromyear = source.readString();
			baseInfo.frommonth = source.readString();
			baseInfo.toyear = source.readString();
			baseInfo.tomonth = source.readString();
			baseInfo.institution = source.readString();
			baseInfo.course = source.readString();
			baseInfo.place = source.readString();
			baseInfo.certification = source.readString();
			baseInfo.traindetail = source.readString();
			baseInfo.user_id = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			return baseInfo;
		}

		@Override
		public ResumePlantInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumePlantInfo[size];
		}
		
	};


	public String getPlant_id() {
		return plant_id;
	}

	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getTraindetail() {
		return traindetail;
	}

	public void setTraindetail(String traindetail) {
		this.traindetail = traindetail;
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
