package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 按职位查看简历实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-28
 */
@SuppressLint("ParcelCreator")
public class JobResumeCountListBean implements Parcelable {
	private List<JobResumeCountInfoBean> list = new ArrayList<JobResumeCountInfoBean>();

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeTypedList(list);
	}
	
	public static final Parcelable.Creator<JobResumeCountListBean> CREATOR = new Parcelable.Creator<JobResumeCountListBean>() {

		@Override
		public JobResumeCountListBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			JobResumeCountListBean jobListBean = new JobResumeCountListBean();
			jobListBean.list = new ArrayList<JobResumeCountInfoBean>();
			source.readTypedList(jobListBean.list, JobResumeCountInfoBean.CREATOR);
			return jobListBean;
		}

		@Override
		public JobResumeCountListBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new JobResumeCountListBean[size];
		}
	};

	public List<JobResumeCountInfoBean> getReturn_data() {
		return list;
	}

	public void setReturn_data(List<JobResumeCountInfoBean> return_data) {
		this.list = return_data;
	}
}
