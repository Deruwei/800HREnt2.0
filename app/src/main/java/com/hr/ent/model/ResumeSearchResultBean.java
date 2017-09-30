package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Colin
 * 日期：2016/9/20 16:22
 * 邮箱：bestxt@qq.com
 */
public class ResumeSearchResultBean implements Parcelable{
    private List<ResumeSearchListBean> list = new ArrayList<ResumeSearchListBean>();

    public List<ResumeSearchListBean> getList() {
        return list;
    }

    public void setList(List<ResumeSearchListBean> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeTypedList(list);
    }

    public static final Parcelable.Creator<ResumeSearchResultBean> CREATOR = new Parcelable.Creator<ResumeSearchResultBean>() {

        @Override
        public ResumeSearchResultBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            ResumeSearchResultBean jobListBean = new ResumeSearchResultBean();
            jobListBean.list = new ArrayList<ResumeSearchListBean>();
            source.readTypedList(jobListBean.list, ResumeSearchListBean.CREATOR);
            return jobListBean;
        }

        @Override
        public ResumeSearchResultBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ResumeSearchResultBean[size];
        }

    };
}
