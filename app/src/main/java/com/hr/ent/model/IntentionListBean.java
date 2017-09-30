package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Colin
 * 日期：2016/9/6 09:40
 * 邮箱：bestxt@qq.com
 */
public class IntentionListBean implements Parcelable {
    private List<IntentionInfoBean2> list = new ArrayList<IntentionInfoBean2>();
    //    private String _run_time="";
    private int error_code;
    //    private int navpage_info;
    private NavpageInfoBean navpage_info;

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
//        dest.writeInt(navpage_info);
        dest.writeInt(error_code);
        dest.writeParcelable(navpage_info, flags);
        dest.writeTypedList(list);
    }

    public static final Parcelable.Creator<IntentionListBean> CREATOR = new Parcelable.Creator<IntentionListBean>() {
        @Override
        public IntentionListBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            IntentionListBean intentionListBean = new IntentionListBean();
            intentionListBean.list = new ArrayList<IntentionInfoBean2>();
            source.readTypedList(intentionListBean.list, IntentionInfoBean2.CREATOR);
            intentionListBean.navpage_info = source.readParcelable(NavpageInfoBean.class.getClassLoader());
//            intentionListBean.navpage_info = source.readParcelable(NavpageInfoBean.class.getClassLoader());
            return intentionListBean;
        }

        @Override
        public IntentionListBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new IntentionListBean[size];
        }
    };

    public List<IntentionInfoBean2> getlist() {
        return list;
    }

    public void setlist(List<IntentionInfoBean2> list) {
        this.list = list;
    }


//    public String get_run_time() {
//        return _run_time;
//    }
//
//    public void set_run_time(String _run_time) {
//        this._run_time = _run_time;
//    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    //    public int getNavpage_info() {
//        return navpage_info;
//    }
//
//    public void setNavpage_info(int navpage_info) {
//        this.navpage_info = navpage_info;
//    }
    public NavpageInfoBean getNavpage_info() {
        return navpage_info;
    }

    public void setNavpage_info(NavpageInfoBean navpage_info) {
        this.navpage_info = navpage_info;
    }
}
