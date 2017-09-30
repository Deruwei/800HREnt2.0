package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 职位列表分页数据实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-4-8
 */
public class NavpageInfoBean implements Parcelable {
    private int current_page;//当前页
    private int total_pages;//总页数
    private int record_nums;//符合条件的记录数
    private int page_nums;//每页返回的个数

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getRecord_nums() {
        return record_nums;
    }

    public void setRecord_nums(int record_nums) {
        this.record_nums = record_nums;
    }

    public int getPage_nums() {
        return page_nums;
    }

    public void setPage_nums(int page_nums) {
        this.page_nums = page_nums;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(current_page);
        dest.writeInt(total_pages);
        dest.writeInt(record_nums);
        dest.writeInt(page_nums);
    }

    public static final Parcelable.Creator<NavpageInfoBean> CREATOR = new Parcelable.Creator<NavpageInfoBean>() {
        @Override
        public NavpageInfoBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            NavpageInfoBean bean = new NavpageInfoBean();
            bean.current_page = source.readInt();
            bean.total_pages = source.readInt();
            bean.record_nums = source.readInt();
            bean.page_nums = source.readInt();
            return bean;
        }

        @Override
        public NavpageInfoBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new NavpageInfoBean[size];
        }
    };

    @Override
    public String toString() {
        return "NavpageInfoBean{" +
                "current_page=" + current_page +
                ", total_pages=" + total_pages +
                ", record_nums=" + record_nums +
                ", page_nums=" + page_nums +
                '}';
    }
}
