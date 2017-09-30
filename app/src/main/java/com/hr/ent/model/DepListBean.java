package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tx on 2017/8/23.
 */

public class DepListBean implements Parcelable {

    /**
     * error_code : 0
     * return_data : [{"did":"24790","user_id":"2755687","dname":"我的新部门名字比较长我的新部门名字比较长我的新部门名字比较长我的新部门名字比较长","address":"我的新部门名字比较长我的新部门名字比较长","zipcode":"111111","linkman":"我的新部门名字比较长","phone":"010-88121212","fax":"","email":"anxuezh123@800hr.com","is_del":"0","deltime":"","job_over_remind":"1","depart_sort":"64"}]
     */

    private int error_code;
    private List<ReturnDataBean> return_data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ReturnDataBean> getReturn_data() {
        return return_data;
    }

    public void setReturn_data(List<ReturnDataBean> return_data) {
        this.return_data = return_data;
    }

    public static class ReturnDataBean implements Parcelable {
        /**
         * did : 24790
         * user_id : 2755687
         * dname : 我的新部门名字比较长我的新部门名字比较长我的新部门名字比较长我的新部门名字比较长
         * address : 我的新部门名字比较长我的新部门名字比较长
         * zipcode : 111111
         * linkman : 我的新部门名字比较长
         * phone : 010-88121212
         * fax :
         * email : anxuezh123@800hr.com
         * is_del : 0
         * deltime :
         * job_over_remind : 1
         * depart_sort : 64
         */

        private String did;
        private String user_id;
        private String dname;
        private String address;
        private String zipcode;
        private String linkman;
        private String phone;
        private String fax;
        private String email;
        private String is_del;
        private String deltime;
        private String job_over_remind;
        private String depart_sort;

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDname() {
            return dname;
        }

        public void setDname(String dname) {
            this.dname = dname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getDeltime() {
            return deltime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }

        public String getJob_over_remind() {
            return job_over_remind;
        }

        public void setJob_over_remind(String job_over_remind) {
            this.job_over_remind = job_over_remind;
        }

        public String getDepart_sort() {
            return depart_sort;
        }

        public void setDepart_sort(String depart_sort) {
            this.depart_sort = depart_sort;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.did);
            dest.writeString(this.user_id);
            dest.writeString(this.dname);
            dest.writeString(this.address);
            dest.writeString(this.zipcode);
            dest.writeString(this.linkman);
            dest.writeString(this.phone);
            dest.writeString(this.fax);
            dest.writeString(this.email);
            dest.writeString(this.is_del);
            dest.writeString(this.deltime);
            dest.writeString(this.job_over_remind);
            dest.writeString(this.depart_sort);
        }

        public ReturnDataBean() {
        }

        protected ReturnDataBean(Parcel in) {
            this.did = in.readString();
            this.user_id = in.readString();
            this.dname = in.readString();
            this.address = in.readString();
            this.zipcode = in.readString();
            this.linkman = in.readString();
            this.phone = in.readString();
            this.fax = in.readString();
            this.email = in.readString();
            this.is_del = in.readString();
            this.deltime = in.readString();
            this.job_over_remind = in.readString();
            this.depart_sort = in.readString();
        }

        public static final Parcelable.Creator<ReturnDataBean> CREATOR = new Parcelable.Creator<ReturnDataBean>() {
            @Override
            public ReturnDataBean createFromParcel(Parcel source) {
                return new ReturnDataBean(source);
            }

            @Override
            public ReturnDataBean[] newArray(int size) {
                return new ReturnDataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.error_code);
        dest.writeTypedList(this.return_data);
    }

    public DepListBean() {
    }

    protected DepListBean(Parcel in) {
        this.error_code = in.readInt();
        this.return_data = in.createTypedArrayList(ReturnDataBean.CREATOR);
    }

    public static final Parcelable.Creator<DepListBean> CREATOR = new Parcelable.Creator<DepListBean>() {
        @Override
        public DepListBean createFromParcel(Parcel source) {
            return new DepListBean(source);
        }

        @Override
        public DepListBean[] newArray(int size) {
            return new DepListBean[size];
        }
    };
}
