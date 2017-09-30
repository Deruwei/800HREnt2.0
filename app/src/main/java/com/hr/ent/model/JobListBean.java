package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 职位列表实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-14
 */
public class JobListBean implements Parcelable {
	
	private List<JobInfoBean> list = new ArrayList<JobInfoBean>();
	private NavpageInfoBean navpage_info ;

	
	public NavpageInfoBean getNavpage_info() {
		return navpage_info;
	}

	public void setNavpage_info(NavpageInfoBean navpage_info) {
		this.navpage_info = navpage_info;
	}

	public List<JobInfoBean> getList() {
		return list;
	}

	public void setList(List<JobInfoBean> list) {
		this.list = list;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeTypedList(list);
		dest.writeParcelable(navpage_info, flags);
	}
	
	public static final Parcelable.Creator<JobListBean> CREATOR = new Parcelable.Creator<JobListBean>() {

		@Override
		public JobListBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			JobListBean jobListBean = new JobListBean();
			jobListBean.list = new ArrayList<JobInfoBean>();
			source.readTypedList(jobListBean.list, JobInfoBean.CREATOR);
			source.readParcelable(NavpageInfoBean.class.getClassLoader());
			return jobListBean;
		}

		@Override
		public JobListBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new JobListBean[size];
		}
		
	};

	@Override
	public String toString() {
		return "JobListBean{" +
				"list=" + list +
				", navpage_info=" + navpage_info +
				'}';
	}
}
