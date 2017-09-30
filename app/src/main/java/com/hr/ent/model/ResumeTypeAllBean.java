package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 按简历查看最外层实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-28
 */
@SuppressLint("ParcelCreator")
public class ResumeTypeAllBean implements Parcelable {
	private ResumeTypeBean return_data;
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeParcelable(return_data, flags);
	}
	
	public static final Parcelable.Creator<ResumeTypeAllBean> CREATOR = new Parcelable.Creator<ResumeTypeAllBean>() {
		@Override
		public ResumeTypeAllBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeTypeAllBean resumeTypeAllBean = new ResumeTypeAllBean();
			resumeTypeAllBean.return_data = source.readParcelable(ResumeTypeBean.class.getClassLoader());
			return resumeTypeAllBean;
		}

		@Override
		public ResumeTypeAllBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeTypeAllBean[size];
		}
	};

	public ResumeTypeBean getReturn_data() {
		return return_data;
	}

	public void setReturn_data(ResumeTypeBean return_data) {
		this.return_data = return_data;
	}
}
