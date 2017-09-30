package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 已下载简历列表实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-1
 */
@SuppressLint("ParcelCreator")
public class DownLoadResumeList implements Parcelable {
	private List<DownLoadResumeInfoBean> list = new ArrayList<DownLoadResumeInfoBean>();

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

	public List<DownLoadResumeInfoBean> getList() {
		return list;
	}

	public void setList(List<DownLoadResumeInfoBean> list) {
		this.list = list;
	}
	
	public static final Parcelable.Creator<DownLoadResumeList> CREATOR = new Parcelable.Creator<DownLoadResumeList>() {

		@Override
		public DownLoadResumeList createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DownLoadResumeList downLoadResumeListBean = new DownLoadResumeList();
			downLoadResumeListBean.list = new ArrayList<DownLoadResumeInfoBean>();
			source.readTypedList(downLoadResumeListBean.list, DownLoadResumeInfoBean.CREATOR);
			return downLoadResumeListBean;
		}

		@Override
		public DownLoadResumeList[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DownLoadResumeList[size];
		}
		
	};
	
	

}
