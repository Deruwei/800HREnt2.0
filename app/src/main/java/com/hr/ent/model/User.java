package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 登录用户实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-18
 */
public class User implements Parcelable {
	private String enterprise_user_id;//企业ID
	private String user_id;//用户ID
	private String login_user_type;//登录用户类型
	private String user_name;//用户名
	private String user_pwd;//用户密码
	private String site_code;//网站编号
	private CrmInfo crminfo;//crm信息
	private ContractStatus contractStatus;
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(enterprise_user_id);
		dest.writeString(user_id);
		dest.writeString(login_user_type);
		dest.writeString(user_name);
		dest.writeString(site_code);
		dest.writeParcelable(crminfo, flags);
		dest.writeParcelable(contractStatus, flags);
	}
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

		@Override
		public User createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			User user = new User();
			user.enterprise_user_id = source.readString();
			user.user_id = source.readString();
			user.login_user_type = source.readString();
			user.user_name = source.readString();
			user.site_code = source.readString();
			user.crminfo = source.readParcelable(CrmInfo.class.getClassLoader());
			user.contractStatus = source.readParcelable(ContractStatus.class.getClassLoader());
			return user;
		}

		@Override
		public User[] newArray(int size) {
			// TODO Auto-generated method stub
			return new User[size];
		}
		
	};

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getEnterprise_user_id() {
		return enterprise_user_id;
	}

	public void setEnterprise_user_id(String enterprise_user_id) {
		this.enterprise_user_id = enterprise_user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getLogin_user_type() {
		return login_user_type;
	}

	public void setLogin_user_type(String login_user_type) {
		this.login_user_type = login_user_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSite_code() {
		return site_code;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public CrmInfo getCrminfo() {
		return crminfo;
	}

	public void setCrminfo(CrmInfo crminfo) {
		this.crminfo = crminfo;
	}

}
