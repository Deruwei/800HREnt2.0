package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * 已下载简历详情实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-12-1
 */
@SuppressLint("ParcelCreator")
public class DownLoadResumeInfoBean extends BaseResumeInfoBean implements Parcelable {
    private String id;//转发、通知信用的ID
    private String operate_time;//简历更新日期
    private String operate_name;//职位名称
    private String resume_number;//简历编号
    private String name;//用户名
    private String echo_yes;
    private String sex;//性别
    private String year;//出生的年份
    private String work_beginyear;//开始工作的年份
    private String high_education;//学历
    private String location;//现居地
    private String pic_filekey;//头像
    private String user_id;//用户ID
    private String resume_id;//简历ID
    private String moremajor;//专业
    private String isnew;//已读未读标识

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(operate_time);
        dest.writeString(operate_name);
        dest.writeString(resume_number);
        dest.writeString(name);
        dest.writeString(echo_yes);
        dest.writeString(sex);
        dest.writeString(year);
        dest.writeString(work_beginyear);
        dest.writeString(high_education);
        dest.writeString(location);
        dest.writeString(pic_filekey);
        dest.writeString(user_id);
        dest.writeString(resume_id);
        dest.writeString(moremajor);
        dest.writeString(isnew);
    }

    public static final Parcelable.Creator<DownLoadResumeInfoBean> CREATOR = new Parcelable.Creator<DownLoadResumeInfoBean>() {

        @Override
        public DownLoadResumeInfoBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            DownLoadResumeInfoBean downLoadResumeInfoBean = new DownLoadResumeInfoBean();
            downLoadResumeInfoBean.operate_time = source.readString();
            downLoadResumeInfoBean.resume_number = source.readString();
            downLoadResumeInfoBean.name = source.readString();
            downLoadResumeInfoBean.echo_yes = source.readString();
            downLoadResumeInfoBean.sex = source.readString();
            downLoadResumeInfoBean.year = source.readString();
            downLoadResumeInfoBean.work_beginyear = source.readString();
            downLoadResumeInfoBean.high_education = source.readString();
            downLoadResumeInfoBean.location = source.readString();
            downLoadResumeInfoBean.pic_filekey = source.readString();
            downLoadResumeInfoBean.user_id = source.readString();
            downLoadResumeInfoBean.resume_id = source.readString();
            downLoadResumeInfoBean.moremajor = source.readString();
            downLoadResumeInfoBean.operate_name = source.readString();
            downLoadResumeInfoBean.isnew = source.readString();
            return downLoadResumeInfoBean;
        }

        @Override
        public DownLoadResumeInfoBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new DownLoadResumeInfoBean[size];
        }

    };

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getOperate_name() {
        return operate_name;
    }

    public void setOperate_name(String operate_name) {
        this.operate_name = operate_name;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getResume_number() {
        return resume_number;
    }

    public void setResume_number(String resume_number) {
        this.resume_number = resume_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEcho_yes() {
        return echo_yes;
    }

    public void setEcho_yes(String echo_yes) {
        this.echo_yes = echo_yes;
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

    public String getWork_beginyear() {
        return work_beginyear;
    }

    public void setWork_beginyear(String work_beginyear) {
        this.work_beginyear = work_beginyear;
    }

    public String getHigh_education() {
        return high_education;
    }

    public void setHigh_education(String high_education) {
        this.high_education = high_education;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPic_filekey() {
        return pic_filekey;
    }

    public void setPic_filekey(String pic_filekey) {
        this.pic_filekey = pic_filekey;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getMoremajor() {
        return moremajor;
    }

    public void setMoremajor(String moremajor) {
        this.moremajor = moremajor;
    }

    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public String getResumeDate() {
        // TODO Auto-generated method stub
        return operate_time;
    }

    @Override
    public String getWorkBeginYear() {
        // TODO Auto-generated method stub
        Calendar calendar = Calendar.getInstance();
        if (!work_beginyear.equals("") && work_beginyear != null) {
            return String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_beginyear));
        }
        return "";
    }

    @Override
    public String getJobName() {
        // TODO Auto-generated method stub
        return operate_name;
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
        return user_id;
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
        return "zh";
    }

    @Override
    public String getIsNew() {
        // TODO Auto-generated method stub
        return "0";
    }

}
