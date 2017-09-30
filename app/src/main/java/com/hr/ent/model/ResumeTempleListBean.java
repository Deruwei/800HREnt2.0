package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通知信列表实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-5
 */
@SuppressLint("ParcelCreator")
public class ResumeTempleListBean implements Parcelable {
	private List<ResumeTempleInfoBean> return_data = new ArrayList<ResumeTempleInfoBean>();

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeTypedList(return_data);
	}
	
	public static final Parcelable.Creator<ResumeTempleListBean> CREATOR = new Parcelable.Creator<ResumeTempleListBean>() {

		@Override
		public ResumeTempleListBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeTempleListBean templeListBean = new ResumeTempleListBean();
			templeListBean.return_data = new ArrayList<ResumeTempleInfoBean>();
			source.readTypedList(templeListBean.return_data, ResumeTempleInfoBean.CREATOR);
			return templeListBean;
		}

		@Override
		public ResumeTempleListBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeTempleListBean[size];
		}
		
	};

	public List<ResumeTempleInfoBean> getReturn_data() {
		return return_data;
	}

	public void setReturn_data(List<ResumeTempleInfoBean> return_data) {
		this.return_data = return_data;
	}
}
