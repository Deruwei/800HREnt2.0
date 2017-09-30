package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.hr.ent.utils.Const;

/**
 * 按简历查看Filter详情实体类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-28
 */
@SuppressLint("ParcelCreator")
public class ResumeFilterInfoBean extends ResumeNum implements Parcelable {
	private String filter;// filterID
	private String all;// 该分类所有的简历数
	private String name;// 分类名称

	public void setUnread(String unread) {
		this.unread = unread;
	}

	private String unread;// 未读简历数

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(filter);
		dest.writeString(all);
		dest.writeString(name);
		dest.writeString(unread);
	}

	public static final Parcelable.Creator<ResumeFilterInfoBean> CREATOR = new Parcelable.Creator<ResumeFilterInfoBean>() {

		@Override
		public ResumeFilterInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeFilterInfoBean resumeFilterInfoBean = new ResumeFilterInfoBean();
			resumeFilterInfoBean.filter = source.readString();
			resumeFilterInfoBean.all = source.readString();
			resumeFilterInfoBean.name = source.readString();
			resumeFilterInfoBean.unread = source.readString();
			return resumeFilterInfoBean;
		}

		@Override
		public ResumeFilterInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeFilterInfoBean[size];
		}

	};

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getTypeNum() {
		// TODO Auto-generated method stub
		return all;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Const.FILTER;
	}

	@Override
	public String getTypeID() {
		// TODO Auto-generated method stub
		return filter;
	}

	@Override
	public String getTypeQuYu() {
		return null;
	}

	@Override
	public String getTypeDate() {
		return null;
	}

	@Override
	public String getUnread() {
		return unread;
	}
}
