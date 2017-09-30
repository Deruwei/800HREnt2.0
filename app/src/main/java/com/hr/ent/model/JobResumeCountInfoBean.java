package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.hr.ent.utils.Const;

/**
 * 按职位查看实体类
 *
 * @author 800hr：zhuhui
 *         <p>
 *         2014-11-28
 */
@SuppressLint("ParcelCreator")
public class JobResumeCountInfoBean extends ResumeNum implements Parcelable {
    private String job_resume="";// 简历数
    private String job_name="";// 职位名称
    private String job_id="";// 职位ID
    private String quyu="";//工作地点(以逗号分隔的地区id最多3个)
    private String issue_date="";//刷新时间时间戳

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(job_resume);
        dest.writeString(job_id);
        dest.writeString(job_name);
        dest.writeString(issue_date);
        dest.writeString(quyu);
    }

    public static final Parcelable.Creator<JobResumeCountInfoBean> CREATOR = new Parcelable.Creator<JobResumeCountInfoBean>() {

        @Override
        public JobResumeCountInfoBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            JobResumeCountInfoBean jobResumeCountInfoBean = new JobResumeCountInfoBean();
            jobResumeCountInfoBean.job_resume = source.readString();
            jobResumeCountInfoBean.job_id = source.readString();
            jobResumeCountInfoBean.job_name = source.readString();
            jobResumeCountInfoBean.issue_date = source.readString();
            jobResumeCountInfoBean.quyu = source.readString();
            return jobResumeCountInfoBean;
        }
        @Override
        public JobResumeCountInfoBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new JobResumeCountInfoBean[size];
        }
    };

    public String getNum() {
        return job_resume;
    }
    public void setNum(String num) {
        this.job_resume = num;
    }

    public String getJob_name() {
        return job_name;
    }
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_id() {
        return job_id;
    }
    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getQuyu() {return quyu;}
    public void setQuyu(String quyu) {this.quyu = quyu;}

    public String getIssue_date() {return issue_date;}
    public void setIssue_date(String issue_date) {this.issue_date = issue_date;}

    @Override
    public String getTypeNum() {
        // TODO Auto-generated method stub
        return job_resume;
    }

    @Override
    public String getTypeName() {
        // TODO Auto-generated method stub
        return job_name;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return Const.JOB;
    }

    @Override
    public String getTypeID() {
        // TODO Auto-generated method stub
        return job_id;
    }

    @Override
    public String getTypeQuYu() {
        return quyu;
    }

    @Override
    public String getTypeDate() {
        return issue_date;
    }

    @Override
    public String getUnread() {
        return null;
    }
}
