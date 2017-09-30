package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 最外层合同实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-27
 */
@SuppressLint("ParcelCreator")
public class ContractBean implements Parcelable {
	
//	private ContractListBean return_data ;
	private List<ContractListBean> return_data = new ArrayList<ContractListBean>();

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
//		dest.writeParcelable(return_data, flags);
		dest.writeTypedList(return_data);
	}
	
	public static final Parcelable.Creator<ContractBean> CREATOR = new Parcelable.Creator<ContractBean>() {

		@Override
		public ContractBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ContractBean invitelistBean = new ContractBean();
			invitelistBean.return_data = new ArrayList<ContractListBean>();
			source.readTypedList(invitelistBean.return_data, ContractListBean.CREATOR);
//			invitelistBean.return_data = source.readParcelable(ContractListBean.class.getClassLoader());
			return invitelistBean;
		}
		@Override
		public ContractBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ContractBean[size];
		}
	};

	public List<ContractListBean> getReturn_data() {
		return return_data;
	}

	public void setReturn_data(List<ContractListBean> return_data) {
		this.return_data = return_data;
	}

}
