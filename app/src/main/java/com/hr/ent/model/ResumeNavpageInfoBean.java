package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Colin
 * 日期：2016/9/21 09:51
 * 邮箱：bestxt@qq.com
 */
public class ResumeNavpageInfoBean implements Parcelable {
    /**
     * current_page : 1
     * total_pages : 1000
     * record_nums : 1000
     * page_nums : 1
     */

    private String current_page;
    private String total_pages;
    private String record_nums;
    private String page_nums;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getRecord_nums() {
        return record_nums;
    }

    public void setRecord_nums(String record_nums) {
        this.record_nums = record_nums;
    }

    public String getPage_nums() {
        return page_nums;
    }

    public void setPage_nums(String page_nums) {
        this.page_nums = page_nums;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.current_page);
        dest.writeString(this.total_pages);
        dest.writeString(this.record_nums);
        dest.writeString(this.page_nums);
    }

    public ResumeNavpageInfoBean() {
    }

    protected ResumeNavpageInfoBean(Parcel in) {
        this.current_page = in.readString();
        this.total_pages = in.readString();
        this.record_nums = in.readString();
        this.page_nums = in.readString();
    }

    public static final Parcelable.Creator<ResumeNavpageInfoBean> CREATOR = new Parcelable.Creator<ResumeNavpageInfoBean>() {
        @Override
        public ResumeNavpageInfoBean createFromParcel(Parcel source) {
            return new ResumeNavpageInfoBean(source);
        }

        @Override
        public ResumeNavpageInfoBean[] newArray(int size) {
            return new ResumeNavpageInfoBean[size];
        }
    };
}
