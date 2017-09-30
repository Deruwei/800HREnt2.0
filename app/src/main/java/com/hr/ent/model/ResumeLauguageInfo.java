package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ResumeLauguageInfo implements Parcelable{
	
	private String id;//本地ID
	private String langname;//语言名称
	private String read_level;//读写能力
	private String speak_level;//听说能力
	private String user_id;//
	private String user_name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLangname() {
		return langname;
	}

	public void setLangname(String langname) {
		this.langname = langname;
	}

	public String getRead_level() {
		return read_level;
	}

	public void setRead_level(String read_level) {
		this.read_level = read_level;
	}

	public String getSpeak_level() {
		return speak_level;
	}

	public void setSpeak_level(String speak_level) {
		this.speak_level = speak_level;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(langname);
		dest.writeString(read_level);
		dest.writeString(speak_level);
		dest.writeString(user_id);
		dest.writeString(user_name);
	}
	
	public static final Parcelable.Creator<ResumeLauguageInfo> CREATOR = new Parcelable.Creator<ResumeLauguageInfo>() {

		@Override
		public ResumeLauguageInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeLauguageInfo baseInfo = new ResumeLauguageInfo();
			baseInfo.langname = source.readString();
			baseInfo.read_level = source.readString();
			baseInfo.speak_level = source.readString();
			baseInfo.user_id = source.readString();
			baseInfo.user_name = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeLauguageInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeLauguageInfo[size];
		}
		
	};

}
