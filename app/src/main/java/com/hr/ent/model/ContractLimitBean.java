package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 最外层合同限量实体类
 * @author 800hr：zhuhui
 *
 * 2014-11-27
 */
@SuppressLint("ParcelCreator")
public class ContractLimitBean extends ContractInfoBean implements Parcelable {
	
	private ContractLimitInfoBean base_contract;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeParcelable(base_contract, flags);
	}
	
	public static final Parcelable.Creator<ContractLimitBean> CREATOR = new Parcelable.Creator<ContractLimitBean>() {

		@Override
		public ContractLimitBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ContractLimitBean contractLimitBean = new ContractLimitBean();
			contractLimitBean.base_contract = source.readParcelable(ContractLimitInfoBean.class.getClassLoader());
			return contractLimitBean;
		}

		@Override
		public ContractLimitBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ContractLimitBean[size];
		}
		
	};

	public ContractLimitInfoBean getBase_contract() {
		return base_contract;
	}

	public void setBase_contract(ContractLimitInfoBean base_contract) {
		this.base_contract = base_contract;
	}
	
	

}
