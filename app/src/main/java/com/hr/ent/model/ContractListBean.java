package com.hr.ent.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 800hr：zhuhui
 *         <p>
 *         2014-11-27
 */
@SuppressLint("ParcelCreator")
public class ContractListBean implements Parcelable {

    private List<ContractInfoBean> serve_list = new ArrayList<ContractInfoBean>();
    private String cbegin_time;//合同开始时间
    private String cend_time;//合同结束时间
    private String pause_state;//合同状态
    private String sign_time;//合同签订时间
    private String edit_username;//编辑人
    private String audit_status;//审核状态
    private String contract_number;//合同号

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeTypedList(serve_list);
        dest.writeString(cbegin_time);
        dest.writeString(cend_time);
        dest.writeString(pause_state);
        dest.writeString(sign_time);
        dest.writeString(edit_username);
        dest.writeString(audit_status);
        dest.writeString(contract_number);
    }

    public static final Parcelable.Creator<ContractListBean> CREATOR = new Parcelable.Creator<ContractListBean>() {

        @Override
        public ContractListBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            ContractListBean contractListBean = new ContractListBean();
            contractListBean.serve_list = new ArrayList<ContractInfoBean>();
            source.readTypedList(contractListBean.serve_list, ContractInfoBean.CREATOR);
            contractListBean.cbegin_time = source.readString();
            contractListBean.cend_time = source.readString();
            contractListBean.pause_state = source.readString();
            contractListBean.sign_time = source.readString();
            contractListBean.edit_username = source.readString();
            contractListBean.audit_status = source.readString();
            contractListBean.contract_number = source.readString();
            return contractListBean;
        }

        @Override
        public ContractListBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ContractListBean[size];
        }

    };

    public List<ContractInfoBean> getServe_list() {
        return serve_list;
    }

    public void setServe_list(List<ContractInfoBean> serve_list) {
        this.serve_list = serve_list;
    }

    public String getCbegin_time() {
        return cbegin_time;
    }

    public void setCbegin_time(String cbegin_time) {
        this.cbegin_time = cbegin_time;
    }

    public String getCend_time() {
        return cend_time;
    }

    public void setCend_time(String cend_time) {
        this.cend_time = cend_time;
    }

    public String getPause_state() {
        return pause_state;
    }

    public void setPause_state(String pause_state) {
        this.pause_state = pause_state;
    }

    public String getSign_time() {
        return sign_time;
    }

    public void setSign_time(String sign_time) {
        this.sign_time = sign_time;
    }

    public String getEdit_username() {
        return edit_username;
    }

    public void setEdit_username(String edit_username) {
        this.edit_username = edit_username;
    }

    public String getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }

    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

}
