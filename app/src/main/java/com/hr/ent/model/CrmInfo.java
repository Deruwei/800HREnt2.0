package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * CRM信息实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-27
 */
@SuppressLint("ParcelCreator")
public class CrmInfo implements Parcelable {
	private String name;//招聘顾问名称
	private String telphone;//办公电话
	private String fax_tel;//传真号码
	private String email;//邮箱
	private String zipcode;//邮政编码

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeString(telphone);
		dest.writeString(fax_tel);
		dest.writeString(email);
		dest.writeString(zipcode);
	}

	public static final Parcelable.Creator<CrmInfo> CREATOR = new Parcelable.Creator<CrmInfo>() {

		@Override
		public CrmInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			CrmInfo crmInfo = new CrmInfo();
			crmInfo.name = source.readString();
			crmInfo.telphone = source.readString();
			crmInfo.fax_tel = source.readString();
			crmInfo.email = source.readString();
			crmInfo.zipcode = source.readString();
			return crmInfo;
		}

		@Override
		public CrmInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CrmInfo[size];
		}
	};
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFax_tel() {
		return fax_tel;
	}

	public void setFax_tel(String fax_tel) {
		this.fax_tel = fax_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
