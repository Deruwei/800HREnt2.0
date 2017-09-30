package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hr.ent.utils.Const;

/**
 * 职位详情实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-14
 */
public class JobInfoBean extends ResumeNum implements Parcelable {

    private String job_id = "";//职位ID
    private String user_id = "";//用户ID
    private String job_name = "";//职位名称
    private String issue_date;//更新时间
    private String enterprise_name = "";//企业名称
    private String quyu="";//区域ID
    private String job_resume = "0";//该职位收到的简历数
    private String crypt_job_id;//加密的职位ID
    private String expiry_date;//发布时间
    private String counts;
    private String effect_time;

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getEffect_time() {
        return effect_time;
    }

    public void setEffect_time(String effect_time) {
        this.effect_time = effect_time;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }


//    private String
    private IntentionInfoBean intention_info;


    public IntentionInfoBean getIntention_info() {
        return intention_info;
    }

    public void setIntention_info(IntentionInfoBean intention_info) {
        this.intention_info = intention_info;
    }

    public static class IntentionInfoBean {
        private String intention_state;
        private String apply_state;
        private String r_id;
        private String resume_from;
        private String resume_from_id;
        private String expiry_date;

        public String getIntention_state() {
            return intention_state;
        }

        public void setIntention_state(String intention_state) {
            this.intention_state = intention_state;
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

        public void setR_id(String apply_state) {
            this.r_id = r_id;
        }

        public void setResume_from_id(String resume_from_id) {
            this.resume_from_id = resume_from_id;
        }

        public String getResume_from_id() {
            return resume_from_id;
        }

        public String getResume_from() {
            return resume_from;
        }

        public void setResume_from(String resume_from) {
            this.resume_from = resume_from;
        }

        @Override
        public String toString() {
            return "IntentionInfoBean{" +
                    "intention_state='" + intention_state + '\'' +
                    ", apply_state='" + apply_state + '\'' +
                    ", r_id='" + r_id + '\'' +
                    ", resume_from='" + resume_from + '\'' +
                    ", resume_from_id='" + resume_from_id + '\'' +
                    ", expiry_date='" + expiry_date + '\'' +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(job_id);
        dest.writeString(user_id);
        dest.writeString(job_name);
        dest.writeString(issue_date);
        dest.writeString(enterprise_name);
        dest.writeString(quyu);
        dest.writeString(job_resume);
        dest.writeString(crypt_job_id);
        dest.writeString(expiry_date);
        dest.writeString(counts);
        dest.writeString(effect_time);
        dest.writeParcelable((Parcelable) this.intention_info, flags);
    }

    public static final Parcelable.Creator<JobInfoBean> CREATOR = new Parcelable.Creator<JobInfoBean>() {

        @Override
        public JobInfoBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            JobInfoBean jobInfo = new JobInfoBean();
            jobInfo.job_id = source.readString();
            jobInfo.job_name = source.readString();
            jobInfo.enterprise_name = source.readString();
            jobInfo.issue_date = source.readString();
            jobInfo.user_id = source.readString();
            jobInfo.quyu = source.readString();
            jobInfo.job_resume = source.readString();
            jobInfo.crypt_job_id = source.readString();
            jobInfo.expiry_date = source.readString();
            jobInfo.effect_time=source.readString();
            jobInfo.counts=source.readString();
            jobInfo.intention_info = source.readParcelable(IntentionInfoBean.class.getClassLoader());
            return jobInfo;
        }

        @Override
        public JobInfoBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new JobInfoBean[size];
        }

    };


    public String getCrypt_job_id() {
        return crypt_job_id;
    }

    public void setCrypt_job_id(String crypt_job_id) {
        this.crypt_job_id = crypt_job_id;
    }

    public String getJob_resume() {
        return job_resume;
    }

    public void setJob_resume(String job_resume) {
        this.job_resume = job_resume;
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

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

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

    @Override
    public String toString() {
        return "JobInfoBean{" +
                "job_id='" + job_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", job_name='" + job_name + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", enterprise_name='" + enterprise_name + '\'' +
                ", quyu='" + quyu + '\'' +
                ", job_resume='" + job_resume + '\'' +
                ", crypt_job_id='" + crypt_job_id + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", counts='" + counts + '\'' +
                ", effect_time='" + effect_time + '\'' +
                ", intention_info=" + intention_info +
                '}';
    }
}
