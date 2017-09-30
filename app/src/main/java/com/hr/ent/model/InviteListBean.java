package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 邀请记录列表实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-26
 */
public class InviteListBean implements Parcelable {
    private List<InviteInfoBean> return_data = new ArrayList<InviteInfoBean>();
    private NavpageInfoBean navpage_info;

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeParcelable(navpage_info, flags);
        dest.writeTypedList(return_data);
    }

    public static final Parcelable.Creator<InviteListBean> CREATOR = new Parcelable.Creator<InviteListBean>() {
        @Override
        public InviteListBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            InviteListBean invitelistBean = new InviteListBean();
            invitelistBean.return_data = new ArrayList<InviteInfoBean>();
            source.readTypedList(invitelistBean.return_data, InviteInfoBean.CREATOR);
            invitelistBean.navpage_info = source.readParcelable(NavpageInfoBean.class.getClassLoader());
            return invitelistBean;
        }

        @Override
        public InviteListBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new InviteListBean[size];
        }
    };

    public List<InviteInfoBean> getReturn_data() {
        return return_data;
    }

    public void setReturn_data(List<InviteInfoBean> return_data) {
        this.return_data = return_data;
    }

    public NavpageInfoBean getNavpage_info() {
        return navpage_info;
    }

    public void setNavpage_info(NavpageInfoBean navpage_info) {
        this.navpage_info = navpage_info;
    }
}
