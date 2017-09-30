package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 *个人信息 
 * @author 800hr：zhuhui
 *
 * 2014-12-1
 */
@SuppressLint("ParcelCreator")
public class ResumeBaseInfoBean implements Parcelable {
	private String user_id;//用户ID
	private String name;//用户名
	private String sex;//性别
	private String year;//出生年份
	private String month;//出生月份
	private String day;//出生日
	private String height;//身高
	private String nationality;//民族
	private String hukou;//户口
	private String idnumber;//证件号
	private String cardtype;//证件类型
	private String marriage;//婚姻状态
	private String salary;//薪资
	private String current_salary;//目前薪资
	private String work_beginyear;//开始工作年份
	private String high_education;//学历
	private String location;//现居地
	private String ydphone;//电话
	private String emailaddress;//邮箱
	private String address;//地址
	private String zipcode;//邮编
	private String homepage;//主页
	private String echo_yes;//
	private String resume_language;//语言
	private String post_rank;//职称
	private String polity;//党
	private String blood;//血型
	private String last_position;//最后职位
	private String current_workstate;//目前工作状态
	private String pic_filekey;//头像
	private String modify_time;//更新时间
	private String current_yearsalary;//目前年薪
	private String capability;//
	private String other_benefits;//
	private String other_benefits_txt;//
	private String telephone;//电话
	private String im_account;//即时通讯号码
	private String im_type;//即时通讯类型
	
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(user_id);
		dest.writeString(name);
		dest.writeString(sex);
		dest.writeString(year);
		dest.writeString(month);
		dest.writeString(day);
		dest.writeString(height);
		dest.writeString(nationality);
		dest.writeString(hukou);
		dest.writeString(idnumber);
		dest.writeString(cardtype);
		dest.writeString(marriage);
		dest.writeString(salary);
		dest.writeString(current_salary);
		dest.writeString(work_beginyear);
		dest.writeString(high_education);
		dest.writeString(location);
		dest.writeString(ydphone);
		dest.writeString(emailaddress);
		dest.writeString(address);
		dest.writeString(zipcode);
		dest.writeString(homepage);
		dest.writeString(echo_yes);
		dest.writeString(resume_language);
		dest.writeString(post_rank);
		dest.writeString(polity);
		dest.writeString(blood);
		dest.writeString(last_position);
		dest.writeString(current_workstate);
		dest.writeString(pic_filekey);
		dest.writeString(modify_time);
		dest.writeString(current_yearsalary);
		dest.writeString(capability);
		dest.writeString(telephone);
		dest.writeString(telephone);
		dest.writeString(im_account);
		dest.writeString(im_type);

	}
	
	public static final Parcelable.Creator<ResumeBaseInfoBean> CREATOR = new Parcelable.Creator<ResumeBaseInfoBean>() {

		@Override
		public ResumeBaseInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeBaseInfoBean resumeBaseInfoBean = new ResumeBaseInfoBean();
			resumeBaseInfoBean.user_id = source.readString();
			resumeBaseInfoBean.name = source.readString();
			resumeBaseInfoBean.sex = source.readString();
			resumeBaseInfoBean.year = source.readString();
			resumeBaseInfoBean.month = source.readString();
			resumeBaseInfoBean.day = source.readString();
			resumeBaseInfoBean.height = source.readString();
			resumeBaseInfoBean.nationality = source.readString();
			resumeBaseInfoBean.hukou = source.readString();
			resumeBaseInfoBean.idnumber = source.readString();
			resumeBaseInfoBean.cardtype = source.readString();
			resumeBaseInfoBean.marriage = source.readString();
			resumeBaseInfoBean.salary = source.readString();
			resumeBaseInfoBean.current_salary = source.readString();
			resumeBaseInfoBean.work_beginyear = source.readString();
			resumeBaseInfoBean.high_education = source.readString();
			resumeBaseInfoBean.location = source.readString();
			resumeBaseInfoBean.ydphone = source.readString();
			resumeBaseInfoBean.day = source.readString();
			resumeBaseInfoBean.emailaddress = source.readString();
			resumeBaseInfoBean.address = source.readString();
			resumeBaseInfoBean.zipcode = source.readString();
			resumeBaseInfoBean.homepage = source.readString();
			resumeBaseInfoBean.echo_yes = source.readString();
			resumeBaseInfoBean.resume_language = source.readString();
			resumeBaseInfoBean.post_rank = source.readString();
			resumeBaseInfoBean.polity = source.readString();
			resumeBaseInfoBean.blood = source.readString();
			resumeBaseInfoBean.last_position = source.readString();
			resumeBaseInfoBean.current_workstate = source.readString();
			resumeBaseInfoBean.pic_filekey = source.readString();
			resumeBaseInfoBean.modify_time = source.readString();
			resumeBaseInfoBean.current_yearsalary = source.readString();
			resumeBaseInfoBean.capability = source.readString();
			resumeBaseInfoBean.telephone = source.readString();
			resumeBaseInfoBean.im_account = source.readString();
			resumeBaseInfoBean.im_type = source.readString();
			return resumeBaseInfoBean;
		}

		@Override
		public ResumeBaseInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeBaseInfoBean[size];
		}
		
	};

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHukou() {
		return hukou;
	}

	public void setHukou(String hukou) {
		this.hukou = hukou;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCurrent_salary() {
		return current_salary;
	}

	public void setCurrent_salary(String current_salary) {
		this.current_salary = current_salary;
	}

	public String getWork_beginyear() {
		return work_beginyear;
	}

	public void setWork_beginyear(String work_beginyear) {
		this.work_beginyear = work_beginyear;
	}

	public String getHigh_education() {
		return high_education;
	}

	public void setHigh_education(String high_education) {
		this.high_education = high_education;
	}

	public String getYdphone() {
		return ydphone;
	}

	public void setYdphone(String ydphone) {
		this.ydphone = ydphone;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEcho_yes() {
		return echo_yes;
	}

	public void setEcho_yes(String echo_yes) {
		this.echo_yes = echo_yes;
	}

	public String getResume_language() {
		return resume_language;
	}

	public void setResume_language(String resume_language) {
		this.resume_language = resume_language;
	}

	public String getPost_rank() {
		return post_rank;
	}

	public void setPost_rank(String post_rank) {
		this.post_rank = post_rank;
	}

	public String getPolity() {
		return polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getLast_position() {
		return last_position;
	}

	public void setLast_position(String last_position) {
		this.last_position = last_position;
	}

	public String getCurrent_workstate() {
		return current_workstate;
	}

	public void setCurrent_workstate(String current_workstate) {
		this.current_workstate = current_workstate;
	}

	public String getPic_filekey() {
		return pic_filekey;
	}

	public void setPic_filekey(String pic_filekey) {
		this.pic_filekey = pic_filekey;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getCurrent_yearsalary() {
		return current_yearsalary;
	}

	public void setCurrent_yearsalary(String current_yearsalary) {
		this.current_yearsalary = current_yearsalary;
	}

	public String getCapability() {
		return capability;
	}

	public void setCapability(String capability) {
		this.capability = capability;
	}

	public String getOther_benefits() {
		return other_benefits;
	}

	public void setOther_benefits(String other_benefits) {
		this.other_benefits = other_benefits;
	}

	public String getOther_benefits_txt() {
		return other_benefits_txt;
	}

	public void setOther_benefits_txt(String other_benefits_txt) {
		this.other_benefits_txt = other_benefits_txt;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIm_account() {
		return im_account;
	}

	public void setIm_account(String im_account) {
		this.im_account = im_account;
	}

	public String getIm_type() {
		return im_type;
	}

	public void setIm_type(String im_type) {
		this.im_type = im_type;
	}
}
