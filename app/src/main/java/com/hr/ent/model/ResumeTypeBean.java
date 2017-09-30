package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 按简历查看实体类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-28
 */
@SuppressLint("ParcelCreator")
public class ResumeTypeBean implements Parcelable {
	private List<ResumeBoxInfoBean> box = new ArrayList<ResumeBoxInfoBean>();
	private List<ResumeFilterInfoBean> filter = new ArrayList<ResumeFilterInfoBean>();
	private ResumeDownloadBean download;
	private String day_count;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeTypedList(box);
		dest.writeTypedList(filter);
		dest.writeParcelable(download, flags);
		dest.writeString(day_count);
	}

	public static final Parcelable.Creator<ResumeTypeBean> CREATOR = new Parcelable.Creator<ResumeTypeBean>() {

		@Override
		public ResumeTypeBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeTypeBean resumeTypeBean = new ResumeTypeBean();
			resumeTypeBean.box = new ArrayList<ResumeBoxInfoBean>();
			source.readTypedList(resumeTypeBean.box, ResumeBoxInfoBean.CREATOR);
			resumeTypeBean.filter = new ArrayList<ResumeFilterInfoBean>();
			source.readTypedList(resumeTypeBean.filter,
					ResumeFilterInfoBean.CREATOR);
			resumeTypeBean.download = source
					.readParcelable(ResumeDownloadBean.class.getClassLoader());
			resumeTypeBean.day_count = source.readString();
			return resumeTypeBean;
		}

		@Override
		public ResumeTypeBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeTypeBean[size];
		}

	};

	public List<ResumeBoxInfoBean> getBox() {
		return box;
	}

	public void setBox(List<ResumeBoxInfoBean> box) {
		this.box = box;
	}

	public List<ResumeFilterInfoBean> getFilter() {
		return filter;
	}

	public void setFilter(List<ResumeFilterInfoBean> filter) {
		this.filter = filter;
	}

	public ResumeDownloadBean getDownload() {
		return download;
	}

	public void setDownload(ResumeDownloadBean download) {
		this.download = download;
	}

	public String getDay_count() {
		return day_count;
	}

	public void setDay_count(String day_count) {
		this.day_count = day_count;
	}

}
