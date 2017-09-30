package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历详情实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-12-1
 */
@SuppressLint("ParcelCreator")
public class ResumeInfoBean extends BaseResumeInfoBean implements Parcelable {
    private String id;//删除简历ID
    private String job_id;//职位ID
    private String user_id;//用户ID
    private String user_user_id;
    private String name;//用户名
    private String sex;//性别
    private String year;//出生年份
    private String high_education;//学历
    private String time;//投递日期
    private String job_name;//职位名称
    private String resume_id;//简历ID
    private String workyear;//工作年限
    private String loccat;//现居地
    private String moremajor;//专业
    private String pic_filekey;//头像
    private String resume_language;//简历语言
    private String isnew;

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(id);
        dest.writeString(job_id);
        dest.writeString(user_id);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(year);
        dest.writeString(high_education);
        dest.writeString(time);
        dest.writeString(job_name);
        dest.writeString(resume_id);
        dest.writeString(workyear);
        dest.writeString(loccat);
        dest.writeString(moremajor);
        dest.writeString(pic_filekey);
        dest.writeString(user_user_id);
        dest.writeString(resume_language);
        dest.writeString(isnew);

    }

    public static final Parcelable.Creator<ResumeInfoBean> CREATOR = new Parcelable.Creator<ResumeInfoBean>() {

        @Override
        public ResumeInfoBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            ResumeInfoBean resumeInfoBean = new ResumeInfoBean();
            resumeInfoBean.id = source.readString();
            resumeInfoBean.job_id = source.readString();
            resumeInfoBean.user_id = source.readString();
            resumeInfoBean.name = source.readString();
            resumeInfoBean.sex = source.readString();
            resumeInfoBean.year = source.readString();
            resumeInfoBean.high_education = source.readString();
            resumeInfoBean.time = source.readString();
            resumeInfoBean.job_name = source.readString();
            resumeInfoBean.resume_id = source.readString();
            resumeInfoBean.workyear = source.readString();
            resumeInfoBean.loccat = source.readString();
            resumeInfoBean.moremajor = source.readString();
            resumeInfoBean.pic_filekey = source.readString();
            resumeInfoBean.user_user_id = source.readString();
            resumeInfoBean.resume_language = source.readString();
            resumeInfoBean.isnew = source.readString();
            return resumeInfoBean;
        }

        @Override
        public ResumeInfoBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ResumeInfoBean[size];
        }

    };

    public String getIsnew() {
        return isnew;
    }


    public String getResume_language() {
        return resume_language;
    }

    public void setResume_language(String resume_language) {
        this.resume_language = resume_language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_user_id() {
        return user_user_id;
    }

    public void setUser_user_id(String user_user_id) {
        this.user_user_id = user_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHigh_education() {
        return high_education;
    }

    public void setHigh_education(String high_education) {
        this.high_education = high_education;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getLoccat() {
        return loccat;
    }

    public void setLoccat(String loccat) {
        this.loccat = loccat;
    }

    public String getMoremajor() {
        return moremajor;
    }

    public void setMoremajor(String moremajor) {
        this.moremajor = moremajor;
    }

    public String getPic_filekey() {
        return pic_filekey;
    }

    public void setPic_filekey(String pic_filekey) {
        this.pic_filekey = pic_filekey;
    }

    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public String getResumeDate() {
        // TODO Auto-generated method stub
        return time;
    }

    @Override
    public String getLocation() {
        // TODO Auto-generated method stub
        return loccat;
    }

    @Override
    public String getWorkBeginYear() {
        // TODO Auto-generated method stub
        return workyear;
    }

    @Override
    public String getJobName() {
        // TODO Auto-generated method stub
        return job_name;
    }

    @Override
    public String getMoreMojor() {
        // TODO Auto-generated method stub
        return moremajor;
    }

    @Override
    public String getHighEdu() {
        // TODO Auto-generated method stub
        return high_education;
    }

    @Override
    public String getPicPhoto() {
        // TODO Auto-generated method stub
        return pic_filekey;
    }

    @Override
    public String getUserID() {
        // TODO Auto-generated method stub
        return user_user_id;
    }

    @Override
    public String getResumeID() {
        // TODO Auto-generated method stub
        return resume_id;
    }

    @Override
    public String getResumeDeleteID() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public String getResumeLanguage() {
        // TODO Auto-generated method stub
        return resume_language;
    }

    @Override
    public String getIsNew() {
        // TODO Auto-generated method stub
        return isnew;
    }


    @Override
    public void setIsnew(String tag) {
        // TODO Auto-generated method stub
        isnew = tag;
    }

}
