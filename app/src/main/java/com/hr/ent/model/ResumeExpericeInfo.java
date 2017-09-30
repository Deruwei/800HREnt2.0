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
public class ResumeExpericeInfo implements Parcelable{
	
	private String user_id;//用户ID
	private String fromyear;//起止时间年份
	private String frommonth;//起止时间月份
	private String toyear;//起止时间至年份
	private String tomonth;//起止时间至月份
	private String company;//公司名称
	private String companyhide;//是否隐藏
	private String industry;//行业ID
	private String companytype;//公司性质
	private String stuffmunber;//公司规模
	private String division;//
	private String companyaddress;//公司地点ID
	private String position;//职位
	private String responsiblity;//职责
	
	private String offreason;//离职原因
	private String zhixi;//职系
	private String zhicheng;//职称
	private String is_overseas;//海外
	private String country;//国家
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String lingyu;//领域ID
	private String func;//职能ID
	private String salary;//薪资
	private String salary_hide;//薪资是否隐藏
	private String experience_id;//工作经历ID
	
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
		dest.writeString(company);
		dest.writeString(companyhide);
		dest.writeString(industry);
		dest.writeString(companytype);
		dest.writeString(stuffmunber);
		dest.writeString(division);
		dest.writeString(companyaddress);
		dest.writeString(position);
		dest.writeString(responsiblity);
		dest.writeString(offreason);
		dest.writeString(zhixi);
		dest.writeString(zhicheng);
		dest.writeString(is_overseas);
		dest.writeString(country);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
		dest.writeString(lingyu);
		dest.writeString(func);
		dest.writeString(salary);
		dest.writeString(salary_hide);
		dest.writeString(experience_id);
		
	}
	
	public static final Parcelable.Creator<ResumeExpericeInfo> CREATOR = new Parcelable.Creator<ResumeExpericeInfo>() {

		@Override
		public ResumeExpericeInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeExpericeInfo baseInfo = new ResumeExpericeInfo();
			baseInfo.user_id = source.readString();
			baseInfo.fromyear = source.readString();
			baseInfo.frommonth = source.readString();
			baseInfo.toyear = source.readString();
			baseInfo.tomonth = source.readString();
			baseInfo.company = source.readString();
			baseInfo.companyhide = source.readString();
			baseInfo.industry = source.readString();
			baseInfo.companytype = source.readString();
			baseInfo.stuffmunber = source.readString();
			baseInfo.division = source.readString();
			baseInfo.companyaddress = source.readString();
			baseInfo.position = source.readString();
			baseInfo.responsiblity = source.readString();
			baseInfo.offreason = source.readString();
			baseInfo.zhixi = source.readString();
			baseInfo.zhicheng = source.readString();
			baseInfo.is_overseas = source.readString();
			baseInfo.country = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			baseInfo.lingyu = source.readString();
			baseInfo.func = source.readString();
			baseInfo.salary = source.readString();
			baseInfo.salary_hide = source.readString();
			baseInfo.experience_id = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeExpericeInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeExpericeInfo[size];
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyhide() {
		return companyhide;
	}

	public void setCompanyhide(String companyhide) {
		this.companyhide = companyhide;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getStuffmunber() {
		return stuffmunber;
	}

	public void setStuffmunber(String stuffmunber) {
		this.stuffmunber = stuffmunber;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getResponsiblity() {
		return responsiblity;
	}

	public void setResponsiblity(String responsiblity) {
		this.responsiblity = responsiblity;
	}

	public String getOffreason() {
		return offreason;
	}

	public void setOffreason(String offreason) {
		this.offreason = offreason;
	}

	public String getZhixi() {
		return zhixi;
	}

	public void setZhixi(String zhixi) {
		this.zhixi = zhixi;
	}

	public String getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
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

	public String getLingyu() {
		return lingyu;
	}

	public void setLingyu(String lingyu) {
		this.lingyu = lingyu;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSalary_hide() {
		return salary_hide;
	}

	public void setSalary_hide(String salary_hide) {
		this.salary_hide = salary_hide;
	}

	public String getExperience_id() {
		return experience_id;
	}

	public void setExperience_id(String experience_id) {
		this.experience_id = experience_id;
	}


}
