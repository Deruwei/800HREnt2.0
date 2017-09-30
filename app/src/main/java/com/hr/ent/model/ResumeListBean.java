package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历列表实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-1
 */
@SuppressLint("ParcelCreator")
public class ResumeListBean implements Parcelable {
	
	private List<ResumeInfoBean> return_data = new ArrayList<ResumeInfoBean>();
	private NavpageInfoBean navpage_info;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeTypedList(return_data);
		dest.writeParcelable(navpage_info, flags);
	}
	
	public static final Parcelable.Creator<ResumeListBean> CREATOR = new Parcelable.Creator<ResumeListBean>() {

		@Override
		public ResumeListBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeListBean resumeListBean = new ResumeListBean();
			resumeListBean.return_data = new ArrayList<ResumeInfoBean>();
			source.readTypedList(resumeListBean.return_data, ResumeInfoBean.CREATOR);
			source.readParcelable(NavpageInfoBean.class.getClassLoader());
			return resumeListBean;
		}

		@Override
		public ResumeListBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeListBean[size];
		}
		
	};

	public List<ResumeInfoBean> getReturn_data() {
		return return_data;
	}

	public void setReturn_data(List<ResumeInfoBean> return_data) {
		this.return_data = return_data;
	}

	public NavpageInfoBean getNavpage_info() {
		return navpage_info;
	}

	public void setNavpage_info(NavpageInfoBean navpage_info) {
		this.navpage_info = navpage_info;
	}

}
