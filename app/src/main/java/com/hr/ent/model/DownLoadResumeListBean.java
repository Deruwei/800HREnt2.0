package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 已下载简历列表最外层实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-1
 */
@SuppressLint("ParcelCreator")
public class DownLoadResumeListBean implements Parcelable{
	private DownLoadResumeList return_data;
	private NavpageInfoBean navpage_info;
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeParcelable(return_data, flags);
		dest.writeParcelable(navpage_info, flags);
	}
	
	public static final Parcelable.Creator<DownLoadResumeListBean> CREATOR = new Parcelable.Creator<DownLoadResumeListBean>() {

		@Override
		public DownLoadResumeListBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DownLoadResumeListBean downLoadResumeListBean = new DownLoadResumeListBean();
			downLoadResumeListBean.return_data = source.readParcelable(DownLoadResumeList.class.getClassLoader());
			downLoadResumeListBean.navpage_info = source.readParcelable(NavpageInfoBean.class.getClassLoader());
			return downLoadResumeListBean;
		}

		@Override
		public DownLoadResumeListBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DownLoadResumeListBean[size];
		}
		
	};

	public DownLoadResumeList getReturn_data() {
		return return_data;
	}

	public void setReturn_data(DownLoadResumeList return_data) {
		this.return_data = return_data;
	}

	public NavpageInfoBean getNavpage_info() {
		return navpage_info;
	}

	public void setNavpage_info(NavpageInfoBean navpage_info) {
		this.navpage_info = navpage_info;
	}

}
