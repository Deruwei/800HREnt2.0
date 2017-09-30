package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.hr.ent.utils.Const;

/**
 * 已下载简历详情实体类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-28
 */
@SuppressLint("ParcelCreator")
public class ResumeDownloadBean extends ResumeNum implements Parcelable {

	private String num;// 已下载简历数
	private String name;// 类型名称

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(num);
		dest.writeString(name);

	}

	public static final Parcelable.Creator<ResumeDownloadBean> CREATOR = new Parcelable.Creator<ResumeDownloadBean>() {

		@Override
		public ResumeDownloadBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeDownloadBean resumeDownloadBean = new ResumeDownloadBean();
			resumeDownloadBean.num = source.readString();
			resumeDownloadBean.name = source.readString();
			return resumeDownloadBean;
		}

		@Override
		public ResumeDownloadBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeDownloadBean[size];
		}

	};

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
		return num;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Const.DOWNLOAD;
	}

	@Override
	public String getTypeID() {
		// TODO Auto-generated method stub
		return null;
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
		return "0";
	}
}
