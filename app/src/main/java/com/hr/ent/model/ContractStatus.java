package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 合同状态实体类
 * @author 800hr：zhuhui
 *
 * 2015-1-8
 */
public class ContractStatus implements Parcelable {
	private String time;
	private String use;
	private String value;
	private String pic;
	private String name;
	private String shiyongqi;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(time);
		dest.writeString(use);
		dest.writeString(value);
		dest.writeString(pic);
		dest.writeString(name);
		dest.writeString(shiyongqi);
	}

	public static final Parcelable.Creator<ContractStatus> CREATOR = new Parcelable.Creator<ContractStatus>() {

		@Override
		public ContractStatus createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ContractStatus contractStatus = new ContractStatus();
			contractStatus.time = source.readString();
			contractStatus.use = source.readString();
			contractStatus.value = source.readString();
			contractStatus.pic = source.readString();
			contractStatus.name = source.readString();
			contractStatus.shiyongqi = source.readString();
			return contractStatus;
		}

		@Override
		public ContractStatus[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ContractStatus[size];
		}
		
	};
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShiyongqi() {
		return shiyongqi;
	}

	public void setShiyongqi(String shiyongqi) {
		this.shiyongqi = shiyongqi;
	}

}
