package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 合同信息实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-27
 */
@SuppressLint("ParcelCreator")
public class ContractInfoBean implements Parcelable {
	private String serve_name;//服务名称
	private String serve_type;//服务类型
	private String server_time;//服务时长
	private String server_date;//服务日期


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(serve_name);
		dest.writeString(serve_type);
		dest.writeString(server_time);
		dest.writeString(server_date);
	}

	public static final Parcelable.Creator<ContractInfoBean> CREATOR = new Parcelable.Creator<ContractInfoBean>() {

		@Override
		public ContractInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ContractInfoBean contractInfoBean = new ContractInfoBean();
			contractInfoBean.serve_name = source.readString();
			contractInfoBean.serve_type = source.readString();
			contractInfoBean.server_time = source.readString();
			contractInfoBean.server_date = source.readString();
			return contractInfoBean;
		}

		@Override
		public ContractInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ContractInfoBean[size];
		}

	};

	public String getServe_name() {
		return serve_name;
	}

	public void setServe_name(String serve_name) {
		this.serve_name = serve_name;
	}

	public String getServe_type() {
		return serve_type;
	}

	public void setServe_type(String serve_type) {
		this.serve_type = serve_type;
	}

	public String getServer_time() {
		return server_time;
	}

	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}

	public String getServer_date() {
		return server_date;
	}

	public void setServer_date(String server_date) {
		this.server_date = server_date;
	}

}
