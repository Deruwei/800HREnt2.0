package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 最外层简历详情实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-12-1
 */
@SuppressLint("ParcelCreator")
public class ResumeInfoOuterBean implements Parcelable {
    private ResumeInfoDetalBean resume_info;
    /**
     * id : 19150075
     * type : 4
     */

    private OptInfoBean opt_info;


    public ResumeInfoDetalBean getResume_info() {
        return resume_info;
    }

    public void setResume_info(ResumeInfoDetalBean resume_info) {
        this.resume_info = resume_info;
    }

    private IntentionInfoBean intention_info;


    public IntentionInfoBean getIntention_info() {
        return intention_info;
    }

    public void setIntention_info(IntentionInfoBean intention_info) {
        this.intention_info = intention_info;
    }


    public OptInfoBean getOpt_info() {
        return opt_info;
    }

    public void setOpt_info(OptInfoBean opt_info) {
        this.opt_info = opt_info;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeParcelable(resume_info, flags);
        dest.writeParcelable((Parcelable) this.intention_info, flags);
        dest.writeParcelable((Parcelable) this.opt_info, flags);

    }

    public static final Creator<ResumeInfoOuterBean> CREATOR = new Creator<ResumeInfoOuterBean>() {

        @Override
        public ResumeInfoOuterBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            ResumeInfoOuterBean resumeInfoOuterBean = new ResumeInfoOuterBean();
            resumeInfoOuterBean.resume_info = source.readParcelable(ResumeInfoDetalBean.class.getClassLoader());
            resumeInfoOuterBean.intention_info = source.readParcelable(IntentionInfoBean.class.getClassLoader());
            resumeInfoOuterBean.opt_info = source.readParcelable(IntentionInfoBean.class.getClassLoader());
            return resumeInfoOuterBean;
        }

        @Override
        public ResumeInfoOuterBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ResumeInfoOuterBean[size];
        }
    };

    public static class OptInfoBean {
        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class IntentionInfoBean {
        private String intention_state;
        private String apply_state;

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
    }
}
