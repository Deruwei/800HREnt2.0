package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 求职意向实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-1
 */
public class ResumeOrderInfoBean implements Parcelable {
	private String user_id;//用户ID
	private String jobtype;//工作类型
	private String industry;//行业
	private String func;//职位
	private String workarea;//工作地点
	private String zhixi ;//职系
	private String order_salary;//期望薪资
	private String order_salary_noshow;//是否显示
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String jobname;//职位名称
	private String lingyu;//领域
	private String order_yearsalary;//期望年薪
	private String current_workstate;//目前求职状态
	private String castbehalf;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(user_id);
		dest.writeString(jobtype);
		dest.writeString(industry);
		dest.writeString(func);
		dest.writeString(workarea);
		dest.writeString(zhixi);
		dest.writeString(order_salary);
		dest.writeString(order_salary_noshow);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
		dest.writeString(jobname);
		dest.writeString(lingyu);
		dest.writeString(order_yearsalary);
		dest.writeString(current_workstate);
		dest.writeString(castbehalf);

	}
	
	public static final Parcelable.Creator<ResumeOrderInfoBean> CREATOR = new Parcelable.Creator<ResumeOrderInfoBean>() {

		@Override
		public ResumeOrderInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeOrderInfoBean resumeOrderInfoBean = new ResumeOrderInfoBean();
			resumeOrderInfoBean.user_id = source.readString();
			resumeOrderInfoBean.jobtype = source.readString();
			resumeOrderInfoBean.industry = source.readString();
			resumeOrderInfoBean.func = source.readString();
			resumeOrderInfoBean.workarea = source.readString();
			resumeOrderInfoBean.zhixi = source.readString();
			resumeOrderInfoBean.order_salary = source.readString();
			resumeOrderInfoBean.order_salary_noshow = source.readString();
			resumeOrderInfoBean.resume_id = source.readString();
			resumeOrderInfoBean.resume_language = source.readString();
			resumeOrderInfoBean.jobname = source.readString();
			resumeOrderInfoBean.lingyu = source.readString();
			resumeOrderInfoBean.order_yearsalary = source.readString();
			resumeOrderInfoBean.current_workstate = source.readString();
			resumeOrderInfoBean.castbehalf = source.readString();
			return resumeOrderInfoBean;
		}

		@Override
		public ResumeOrderInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeOrderInfoBean[size];
		}
		
	};

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getWorkarea() {
		return workarea;
	}

	public void setWorkarea(String workarea) {
		this.workarea = workarea;
	}

	public String getZhixi() {
		return zhixi;
	}

	public void setZhixi(String zhixi) {
		this.zhixi = zhixi;
	}

	public String getOrder_salary() {
		return order_salary;
	}

	public void setOrder_salary(String order_salary) {
		this.order_salary = order_salary;
	}

	public String getOrder_salary_noshow() {
		return order_salary_noshow;
	}

	public void setOrder_salary_noshow(String order_salary_noshow) {
		this.order_salary_noshow = order_salary_noshow;
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

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getLingyu() {
		return lingyu;
	}

	public void setLingyu(String lingyu) {
		this.lingyu = lingyu;
	}

	public String getOrder_yearsalary() {
		return order_yearsalary;
	}

	public void setOrder_yearsalary(String order_yearsalary) {
		this.order_yearsalary = order_yearsalary;
	}

	public String getCurrent_workstate() {
		return current_workstate;
	}

	public void setCurrent_workstate(String current_workstate) {
		this.current_workstate = current_workstate;
	}

	public String getCastbehalf() {
		return castbehalf;
	}

	public void setCastbehalf(String castbehalf) {
		this.castbehalf = castbehalf;
	}

}
