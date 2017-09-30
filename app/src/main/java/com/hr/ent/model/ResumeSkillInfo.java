package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历信息实体类
 * @author 800hr：zhuhui
 *
 * 2014-5-19
 */
@SuppressLint("ParcelCreator")
public class ResumeSkillInfo implements Parcelable{
	
	private String id;//技能本地ID
	private String skill_id;//专业技能ID
	private String skilltitle;//专业技能标题
	private String usetime;//使用时间
	private String ability;//技能水平ID
	private String user_id;//用户ID
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String user_name;//
	
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(skill_id);
		dest.writeString(skilltitle);
		dest.writeString(usetime);
		dest.writeString(ability);
		dest.writeString(user_id);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
	}
	
	public static final Parcelable.Creator<ResumeSkillInfo> CREATOR = new Parcelable.Creator<ResumeSkillInfo>() {

		@Override
		public ResumeSkillInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ResumeSkillInfo baseInfo = new ResumeSkillInfo();
			baseInfo.skill_id = source.readString();
			baseInfo.skilltitle = source.readString();
			baseInfo.usetime = source.readString();
			baseInfo.ability = source.readString();
			
			baseInfo.user_id = source.readString();
			baseInfo.resume_id = source.readString();
			baseInfo.resume_language = source.readString();
			return baseInfo;
		}

		@Override
		public ResumeSkillInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResumeSkillInfo[size];
		}
		
	};


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(String skill_id) {
		this.skill_id = skill_id;
	}

	public String getSkilltitle() {
		return skilltitle;
	}

	public void setSkilltitle(String skilltitle) {
		this.skilltitle = skilltitle;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getResume_id() {
		return resume_id;
	}

	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}

	public String getResume_language() {
		return resume_language;
	}

	public void setResume_language(String resume_language) {
		this.resume_language = resume_language;
	}
}
