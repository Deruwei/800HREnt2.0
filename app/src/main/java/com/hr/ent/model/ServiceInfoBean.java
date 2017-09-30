package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdr on 2017/9/27.
 */
@SuppressLint("ParcelCreator")
public class ServiceInfoBean implements Parcelable{
    private int cbegin_time;
    private int cend_time;
    private int pause_state;
    private int sign_time;
    private String edit_username;
    private int audit_status;
    private String contract_number;
    private List<ServeListBean> serve_list=new ArrayList<>();

    protected ServiceInfoBean(Parcel in) {
        cbegin_time = in.readInt();
        cend_time = in.readInt();
        pause_state = in.readInt();
        sign_time = in.readInt();
        edit_username = in.readString();
        audit_status = in.readInt();
        contract_number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cbegin_time);
        dest.writeInt(cend_time);
        dest.writeInt(pause_state);
        dest.writeInt(sign_time);
        dest.writeString(edit_username);
        dest.writeInt(audit_status);
        dest.writeString(contract_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ServiceInfoBean> CREATOR = new Creator<ServiceInfoBean>() {
        @Override
        public ServiceInfoBean createFromParcel(Parcel in) {
            return new ServiceInfoBean(in);
        }

        @Override
        public ServiceInfoBean[] newArray(int size) {
            return new ServiceInfoBean[size];
        }
    };
}
