package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Colin
 * 日期：2016/9/6 09:38
 * 邮箱：bestxt@qq.com
 */
public class IntentionInfoBean2 implements Parcelable {

    private String enterprise_name;
    private String work_beginyear;
    private String apply_time;
    private String apply_state;
    private String r_id;
    private String location;
    private String last_company;
    private String user_year;
    private String high_education;
    private String resume_language;
    private String user_name;
    private String intention_state;
    private String job_name;
    private String last_position;
    private String user_id;
    private String user_sex;
    private String enterprise_id;
    private String resume_from_id;
    private String industry;
    private String resume_from;
    private String resume_id;
    private String job_id;

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getWork_beginyear() {
        return work_beginyear;
    }

    public void setWork_beginyear(String work_beginyear) {
        this.work_beginyear = work_beginyear;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLast_company() {
        return last_company;
    }

    public void setLast_company(String last_company) {
        this.last_company = last_company;
    }

    public String getUser_year() {
        return user_year;
    }

    public void setUser_year(String user_year) {
        this.user_year = user_year;
    }

    public String getHigh_education() {
        return high_education;
    }

    public void setHigh_education(String high_education) {
        this.high_education = high_education;
    }

    public String getResume_language() {
        return resume_language;
    }

    public void setResume_language(String resume_language) {
        this.resume_language = resume_language;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIntention_state() {
        return intention_state;
    }

    public void setIntention_state(String intention_state) {
        this.intention_state = intention_state;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getLast_position() {
        return last_position;
    }

    public void setLast_position(String last_position) {
        this.last_position = last_position;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getResume_from_id() {
        return resume_from_id;
    }

    public void setResume_from_id(String resume_from_id) {
        this.resume_from_id = resume_from_id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getResume_from() {
        return resume_from;
    }

    public void setResume_from(String resume_from) {
        this.resume_from = resume_from;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.enterprise_name);
        dest.writeString(this.work_beginyear);
        dest.writeString(this.apply_time);
        dest.writeString(this.apply_state);
        dest.writeString(this.r_id);
        dest.writeString(this.location);
        dest.writeString(this.last_company);
        dest.writeString(this.user_year);
        dest.writeString(this.high_education);
        dest.writeString(this.resume_language);
        dest.writeString(this.user_name);
        dest.writeString(this.intention_state);
        dest.writeString(this.job_name);
        dest.writeString(this.last_position);
        dest.writeString(this.user_id);
        dest.writeString(this.user_sex);
        dest.writeString(this.enterprise_id);
        dest.writeString(this.resume_from_id);
        dest.writeString(this.industry);
        dest.writeString(this.resume_from);
        dest.writeString(this.resume_id);
        dest.writeString(this.job_id);
    }

    public static final Parcelable.Creator<IntentionInfoBean2> CREATOR = new Parcelable.Creator<IntentionInfoBean2>() {
        @Override
        public IntentionInfoBean2 createFromParcel(Parcel source) {
            IntentionInfoBean2 intentionInfoBean = new IntentionInfoBean2();
            intentionInfoBean.enterprise_name = source.readString();
            intentionInfoBean.work_beginyear = source.readString();
            intentionInfoBean.apply_time = source.readString();
            intentionInfoBean.apply_state = source.readString();
            intentionInfoBean.r_id = source.readString();
            intentionInfoBean.location = source.readString();
            intentionInfoBean.last_company = source.readString();
            intentionInfoBean.user_year = source.readString();
            intentionInfoBean.high_education = source.readString();
            intentionInfoBean.resume_language = source.readString();
            intentionInfoBean.user_name = source.readString();
            intentionInfoBean.intention_state = source.readString();
            intentionInfoBean.job_name = source.readString();
            intentionInfoBean.last_position = source.readString();
            intentionInfoBean.user_id = source.readString();
            intentionInfoBean.user_sex = source.readString();
            intentionInfoBean.resume_from_id = source.readString();
            intentionInfoBean.enterprise_id = source.readString();
            intentionInfoBean.industry = source.readString();
            intentionInfoBean.resume_from = source.readString();
            intentionInfoBean.resume_id = source.readString();
            intentionInfoBean.job_id = source.readString();
            return intentionInfoBean;
        }

        @Override
        public IntentionInfoBean2[] newArray(int size) {
            return new IntentionInfoBean2[size];
        }
    };
}
