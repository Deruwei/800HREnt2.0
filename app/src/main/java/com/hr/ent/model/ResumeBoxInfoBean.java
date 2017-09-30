package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.hr.ent.utils.Const;

/**
 * 按简历查看box详情实体类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-11-28
 */
@SuppressLint("ParcelCreator")
public class ResumeBoxInfoBean extends ResumeNum implements Parcelable {

	private String box_name;// 分类名称
	private String user_id;// 用户ID
	private String box_id;// 分类ID
	private String all;// 总数
	private String unread;// 未读简历
	private String old;// 已读简历

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub
		dest.writeString(box_name);
		dest.writeString(box_id);
		dest.writeString(user_id);
		dest.writeString(all);
		dest.writeString(unread);
		dest.writeString(old);
	}

	public static final Parcelable.Creator<ResumeBoxInfoBean> CREATOR = new Parcelable.Creator<ResumeBoxInfoBean>() {

		@Override
		public ResumeBoxInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeBoxInfoBean resumeBoxInfoBean = new ResumeBoxInfoBean();
			resumeBoxInfoBean.box_name = source.readString();
			resumeBoxInfoBean.user_id = source.readString();
			resumeBoxInfoBean.box_id = source.readString();
			resumeBoxInfoBean.all = source.readString();
			resumeBoxInfoBean.unread = source.readString();
			resumeBoxInfoBean.old = source.readString();
			return resumeBoxInfoBean;
		}

		@Override
		public ResumeBoxInfoBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeBoxInfoBean[size];
		}

	};

	public String getBox_name() {
		return box_name;
	}

	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	@Override
	public String getTypeNum() {
		// TODO Auto-generated method stub
		return all;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return box_name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Const.BOX;
	}

	@Override
	public String getTypeID() {
		// TODO Auto-generated method stub
		return box_id;
	}

	@Override
	public String getTypeQuYu() {
		return null;
	}

	@Override
	public String getTypeDate() {
		return null;
	}

	public String getUnread() {
		return unread;
	}

	public void setUnread(String unread) {
		this.unread = unread;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}

}
