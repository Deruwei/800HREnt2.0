package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author 800hr：zhangyayan
 *
 * 2015-8-17
 */
public class CerificateAccessory implements Parcelable {

	private String Other_Accessory;// 其他附件
	private String attachmentdesc;// 附件描述

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(Other_Accessory);
		dest.writeString(attachmentdesc);
	}

	public static final Parcelable.Creator<CerificateAccessory> CREATOR = new Parcelable.Creator<CerificateAccessory>() {

		@Override
		public CerificateAccessory createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			CerificateAccessory cerificateAccessory = new CerificateAccessory();
			cerificateAccessory.Other_Accessory = source.readString();
			cerificateAccessory.attachmentdesc = source.readString();
			return cerificateAccessory;
		}

		@Override
		public CerificateAccessory[] newArray(int size) {
			return new CerificateAccessory[size];
		}
	};

	public String getOther_Accessory() {
		return Other_Accessory;
	}

	public void setOther_Accessory(String other_Accessory) {
		Other_Accessory = other_Accessory;
	}

	public String getAttachmentdesc() {
		return attachmentdesc;
	}

	public void setAttachmentdesc(String attachmentdesc) {
		this.attachmentdesc = attachmentdesc;
	}

}
