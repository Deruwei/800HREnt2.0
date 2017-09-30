package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewResumeNum implements Parcelable {
	private int allNum;// 总简历数
	private int newNum;// 未读简历数
	private int oldNum;// 旧简历数
	private int stowNum;// 收藏简历数
	private int todayNum;// 今日新增简历数

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public int getNewNum() {
		return newNum;
	}

	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}

	public int getOldNum() {
		return oldNum;
	}

	public void setOldNum(int oldNum) {
		this.oldNum = oldNum;
	}

	public int getStowNum() {
		return stowNum;
	}

	public void setStowNum(int stowNum) {
		this.stowNum = stowNum;
	}

	public int getTodayNum() {
		return todayNum;
	}

	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(allNum);
		dest.writeInt(newNum);
		dest.writeInt(oldNum);
		dest.writeInt(stowNum);
		dest.writeInt(todayNum);
	}

	public static final Parcelable.Creator<NewResumeNum> CREATOR = new Creator<NewResumeNum>() {

		@Override
		public NewResumeNum[] newArray(int size) {
			// TODO Auto-generated method stub
			return new NewResumeNum[size];
		}

		@Override
		public NewResumeNum createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			NewResumeNum num = new NewResumeNum();
			num.allNum = source.readInt();
			num.newNum = source.readInt();
			num.oldNum = source.readInt();
			num.stowNum = source.readInt();
			num.todayNum = source.readInt();
			return num;
		}
	};

}
