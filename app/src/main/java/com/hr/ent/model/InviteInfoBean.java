package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通知信详情实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-26
 */
@SuppressLint("ParcelCreator")
public class InviteInfoBean implements Parcelable {
	private String user_id;//用户ID
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String enterprise_id;//企业ID
	private String add_time;//发送时间
	private String enterprise_name;//企业名称
	private String job_name;//职位名称
	private String job_id;//职位ID
	private String is_email;//邮箱发送
	private String is_sms;//短信发送
	private String sms_code;//短信发送码
	private String email_state;//邮箱发送状态
	private String user_name;//用户名称
	private String destine_time;//预约时间
	private String phone;//电话
	private String email;//邮箱
	private String email_title;//邮箱标题
	private String sex;//性别
	private String year;//出生日期
	private String work_beginyear;//工作年限
	private String sms_content;//短信内容
	private String high_education;//学历
	private String location;//现居住地
	private String moremajor;//专业
	private String pic_filekey;//头像地址
	private String id;//id


	public String getEmail_content() {
		return email_content;
	}

	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}

	private String email_content;//邮件内容
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
		dest.writeString(enterprise_id);
		dest.writeString(add_time);
		dest.writeString(enterprise_name);
		dest.writeString(job_name);
		dest.writeString(job_id);
		dest.writeString(is_email);
		dest.writeString(is_sms);
		dest.writeString(sms_code);
		dest.writeString(email_state);
		dest.writeString(destine_time);
		dest.writeString(phone);
		dest.writeString(email);
		dest.writeString(email_title);
		dest.writeString(user_name);
		dest.writeString(sex);
		dest.writeString(year);
		dest.writeString(work_beginyear);
		dest.writeString(high_education);
		dest.writeString(location);
		dest.writeString(moremajor);
		dest.writeString(pic_filekey);
		dest.writeString(sms_content);
		dest.writeString(email_content);
		dest.writeString(id);

	}
	
	public static final Parcelable.Creator<InviteInfoBean> CREATOR = new Parcelable.Creator<InviteInfoBean>() {

		@Override
		public InviteInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			InviteInfoBean inviteInfoBean = new InviteInfoBean();
			inviteInfoBean.user_id = source.readString();
			inviteInfoBean.resume_id = source.readString();
			inviteInfoBean.resume_language = source.readString();
			inviteInfoBean.enterprise_id = source.readString();
			inviteInfoBean.add_time = source.readString();
			inviteInfoBean.enterprise_name = source.readString();
			inviteInfoBean.job_name = source.readString();
			inviteInfoBean.job_id = source.readString();
			inviteInfoBean.is_email = source.readString();
			inviteInfoBean.is_sms = source.readString();
			inviteInfoBean.sms_code = source.readString();
			inviteInfoBean.email_state = source.readString();
			inviteInfoBean.destine_time = source.readString();
			inviteInfoBean.phone = source.readString();
			inviteInfoBean.email = source.readString();
			inviteInfoBean.email_title = source.readString();
			inviteInfoBean.user_name = source.readString();
			inviteInfoBean.sex = source.readString();
			inviteInfoBean.year = source.readString();
			inviteInfoBean.work_beginyear = source.readString();
			inviteInfoBean.high_education = source.readString();
			inviteInfoBean.location = source.readString();
			inviteInfoBean.moremajor = source.readString();
			inviteInfoBean.pic_filekey = source.readString();
			inviteInfoBean.sms_content = source.readString();
			inviteInfoBean.email_content = source.readString();
			inviteInfoBean.id = source.readString();
			return inviteInfoBean;
		}

		@Override
		public InviteInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new InviteInfoBean[size];
		}
		
	};
	
	public String getSms_content() {
		return sms_content;
	}

	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMoremajor() {
		return moremajor;
	}

	public void setMoremajor(String moremajor) {
		this.moremajor = moremajor;
	}

	public String getPic_filekey() {
		return pic_filekey;
	}

	public void setPic_filekey(String pic_filekey) {
		this.pic_filekey = pic_filekey;
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

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getEnterprise_name() {
		return enterprise_name;
	}

	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getIs_email() {
		return is_email;
	}

	public void setIs_email(String is_email) {
		this.is_email = is_email;
	}

	public String getIs_sms() {
		return is_sms;
	}

	public void setIs_sms(String is_sms) {
		this.is_sms = is_sms;
	}

	public String getSms_code() {
		return sms_code;
	}

	public void setSms_code(String sms_code) {
		this.sms_code = sms_code;
	}

	public String getEmail_state() {
		return email_state;
	}

	public void setEmail_state(String email_state) {
		this.email_state = email_state;
	}

	public String getDestine_time() {
		return destine_time;
	}

	public void setDestine_time(String destine_time) {
		this.destine_time = destine_time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_title() {
		return email_title;
	}

	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
