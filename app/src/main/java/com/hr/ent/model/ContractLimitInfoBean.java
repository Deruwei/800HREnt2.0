package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 合同限量使用情况实体类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-27
 */
@SuppressLint("ParcelCreator")
public class ContractLimitInfoBean implements Parcelable {
	private String job_open_limit;// 发布职位限量
	private String job_open_limit_used;// 发布职位已使用量
	private String job_refresh_limit;// 职位刷新限量
	private String job_refresh_limit_used;// 职位刷新已使用量
	private String browse_personal_limit;// 下载简历限量
	private String browse_personal_limit_used;// 下载简历已使用量
	private String sms_limit;// 发送短信限量
	private String sms_limit_used;// 发送短信已使用量

	private String limit_opentopjob;// 高端职位限量
	private String limit_opentopjob_used;// 高端职位已使用量
	private String view_topresume_num;// 高端简历下载限量
	private String view_topresume_num_used;// 高端简历下载已使用量

	private String linkup_num;// 意向沟通
	private String linkup_num_used;// 意向沟通
	private String xunfang_num;// 人才寻访
	private String xunfang_num_used;// 人才寻访


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(job_open_limit);
		dest.writeString(job_open_limit_used);
		dest.writeString(job_refresh_limit);
		dest.writeString(job_refresh_limit_used);
		dest.writeString(browse_personal_limit);
		dest.writeString(browse_personal_limit_used);
		dest.writeString(sms_limit);
		dest.writeString(sms_limit_used);
		dest.writeString(limit_opentopjob);
		dest.writeString(limit_opentopjob_used);
		dest.writeString(view_topresume_num);
		dest.writeString(view_topresume_num_used);
		dest.writeString(linkup_num);
		dest.writeString(linkup_num_used);
		dest.writeString(xunfang_num);
		dest.writeString(xunfang_num_used);

	}

	public static final Parcelable.Creator<ContractLimitInfoBean> CREATOR = new Parcelable.Creator<ContractLimitInfoBean>() {

		@Override
		public ContractLimitInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ContractLimitInfoBean contractLimitInfoBean = new ContractLimitInfoBean();
			contractLimitInfoBean.job_open_limit = source.readString();
			contractLimitInfoBean.job_open_limit_used = source.readString();
			contractLimitInfoBean.job_refresh_limit = source.readString();
			contractLimitInfoBean.job_refresh_limit_used = source.readString();
			contractLimitInfoBean.browse_personal_limit = source.readString();
			contractLimitInfoBean.browse_personal_limit_used = source.readString();
			contractLimitInfoBean.sms_limit = source.readString();
			contractLimitInfoBean.sms_limit_used = source.readString();
			contractLimitInfoBean.limit_opentopjob = source.readString();
			contractLimitInfoBean.limit_opentopjob_used = source.readString();
			contractLimitInfoBean.view_topresume_num = source.readString();
			contractLimitInfoBean.view_topresume_num_used = source.readString();
			contractLimitInfoBean.linkup_num = source.readString();
			contractLimitInfoBean.linkup_num_used = source.readString();
			contractLimitInfoBean.xunfang_num = source.readString();
			contractLimitInfoBean.xunfang_num_used = source.readString();
			return contractLimitInfoBean;
		}

		@Override
		public ContractLimitInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ContractLimitInfoBean[size];
		}

	};

	public String getLinkup_num() {
		return linkup_num;
	}

	public void setLinkup_num(String linkup_num) {
		this.linkup_num = linkup_num;
	}

	public String getLinkup_num_used() {
		return linkup_num_used;
	}

	public void setLinkup_num_used(String linkup_num_used) {
		this.linkup_num_used = linkup_num_used;
	}

	public String getXunfang_num() {
		return xunfang_num;
	}

	public void setXunfang_num(String xunfang_num) {
		this.xunfang_num = xunfang_num;
	}

	public String getXunfang_num_used() {
		return xunfang_num_used;
	}

	public void setXunfang_num_used(String xunfang_num_used) {
		this.xunfang_num_used = xunfang_num_used;
	}
	public String getJob_open_limit() {
		return job_open_limit;
	}

	public void setJob_open_limit(String job_open_limit) {
		this.job_open_limit = job_open_limit;
	}

	public String getJob_open_limit_used() {
		return job_open_limit_used;
	}

	public void setJob_open_limit_used(String job_open_limit_used) {
		this.job_open_limit_used = job_open_limit_used;
	}

	public String getJob_refresh_limit() {
		return job_refresh_limit;
	}

	public void setJob_refresh_limit(String job_refresh_limit) {
		this.job_refresh_limit = job_refresh_limit;
	}

	public String getJob_refresh_limit_used() {
		return job_refresh_limit_used;
	}

	public void setJob_refresh_limit_used(String job_refresh_limit_used) {
		this.job_refresh_limit_used = job_refresh_limit_used;
	}

	public String getBrowse_personal_limit() {
		return browse_personal_limit;
	}

	public void setBrowse_personal_limit(String browse_personal_limit) {
		this.browse_personal_limit = browse_personal_limit;
	}

	public String getBrowse_personal_limit_used() {
		return browse_personal_limit_used;
	}

	public void setBrowse_personal_limit_used(String browse_personal_limit_used) {
		this.browse_personal_limit_used = browse_personal_limit_used;
	}

	public String getSms_limit() {
		return sms_limit;
	}

	public void setSms_limit(String sms_limit) {
		this.sms_limit = sms_limit;
	}

	public String getSms_limit_used() {
		return sms_limit_used;
	}

	public void setSms_limit_used(String sms_limit_used) {
		this.sms_limit_used = sms_limit_used;
	}

	public String getLimit_opentopjob() {
		return limit_opentopjob;
	}

	public void setLimit_opentopjob(String limit_opentopjob) {
		this.limit_opentopjob = limit_opentopjob;
	}

	public String getLimit_opentopjob_used() {
		return limit_opentopjob_used;
	}

	public void setLimit_opentopjob_used(String limit_opentopjob_used) {
		this.limit_opentopjob_used = limit_opentopjob_used;
	}

	public String getView_topresume_num() {
		return view_topresume_num;
	}

	public void setView_topresume_num(String view_topresume_num) {
		this.view_topresume_num = view_topresume_num;
	}

	public String getView_topresume_num_used() {
		return view_topresume_num_used;
	}

	public void setView_topresume_num_used(String view_topresume_num_used) {
		this.view_topresume_num_used = view_topresume_num_used;
	}
}