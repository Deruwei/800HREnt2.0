package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by tx on 2017/9/20.
 */

public class GetJobInfoBean implements Parcelable {

    /**
     * job_id : 4969681
     * user_id : 2755687
     * job_name : 防水工程师
     * issue_date : 1505873424
     * enterprise_name : 北京红根基建筑有限公司
     * synopsis : asd
     * monthly_play : 11
     * monthly_pay : 1500
     * monthly_pay_to : 1500
     * department : 北京红根基建筑有限公司
     * effect_time : 175
     * number : 2
     * study1 : 10
     * study2 : 21
     * email :
     * phone :
     * user_open : 0
     * language1 : 0
     * level1 : 0
     * language2 : 0
     * level2 : 0
     * workyear : 10
     * standby6 : 396101
     * counts : 0
     * quyu : 1101
     * last_refresh_time : 1505873415
     * linkman :
     * email2 :
     * post_rank : 0
     * work_type : 13
     * invite_major :
     * deltime :
     * hy : 11
     * recruiter_count : 0
     * issue_state : 0
     * is_show_phone : 0
     * is_show_email : 0
     * is_show_fax : 0
     * is_show_address : 0
     * is_send_mail : 1
     * is_send_mail2 : 0
     * is_show_pay : 1
     * job_number :
     * did : 1
     * is_include_more_degree : 1
     * is_send_stow : 1
     * is_show_linkman : 0
     * job_sort : 99
     * job_type : 396101
     * is_show_number_some : 0
     * is_show_pay_interview : 0
     * parent_job_id : 0
     * is_use_auto_reply : 2
     * expiry_date : 1505836800
     * average_pay : 0
     * monthly_play_to : 11
     * poster_state : 0
     * shield_resume : a:7:{s:8:"graduate";s:1:"0";s:8:"workyear";i:0;s:6:"degree";i:0;s:12:"allow_locate";i:0;s:12:"workyear_val";s:0:"";s:10:"degree_val";s:0:"";s:16:"allow_locate_val";s:0:"";}
     * crypt_job_id : YPcc6
     * job_name_pinyin : FangShuiGongChengShi
     * audit_state : 0
     * audit_reason :
     * is_from_net : 0
     * offline_time : 0
     * topjob_type : 1
     * year_salary_from : 0
     * year_salary_to : 0
     * subordinate_num : 0
     * superior :
     * other_benefits :
     * recruit_students : 0
     * job_slogan :
     * show_language :
     * country : 0
     * osjob_salary_from : 0
     * osjob_salary_to : 0
     * salary_currency : 0
     * salary_duration : 0
     * salary_convert : 0
     * osjob_type : 0
     * osjob_status : 0
     * address : 北京市上地嘉华大厦
     * zipcode : 100000
     * contact_info : {"address":"北京市上地嘉华大厦","email":"anxuezhe@800hr.com","linkman":"安先生","phone":"010-85296341-1234,0433-8456123-1234","fax":"010-88121212","zipcode":"100000","mobilephone":"18766667297"}
     */

    private String job_id;
    private String user_id;
    private String job_name;
    private String issue_date;
    private String enterprise_name;
    private String synopsis;
    private String monthly_play;
    private String monthly_pay;
    private String monthly_pay_to;
    private String department;
    private String effect_time;
    private String number;
    private String study1;
    private String study2;
    private String email;
    private String phone;
    private String user_open;
    private String language1;
    private String level1;
    private String language2;
    private String level2;
    private String workyear;
    private String standby6;
    private String counts;
    private String quyu;
    private String last_refresh_time;
    private String linkman;
    private String email2;
    private String post_rank;
    private String work_type;
    private String invite_major;
    private String deltime;
    private String hy;
    private String recruiter_count;
    private String issue_state;
    private String is_show_phone;
    private String is_show_email;
    private String is_show_fax;
    private String is_show_address;
    private String is_send_mail;
    private String is_send_mail2;
    private String is_show_pay;
    private String job_number;
    private int did;
    private String is_include_more_degree;
    private String is_send_stow;
    private String is_show_linkman;
    private String job_sort;
    private String job_type;
    private String is_show_number_some;
    private String is_show_pay_interview;
    private String parent_job_id;
    private String is_use_auto_reply;
    private String expiry_date;
    private String average_pay;
    private String monthly_play_to;
    private String poster_state;
    private String shield_resume;
    private String crypt_job_id;
    private String job_name_pinyin;
    private String audit_state;
    private String audit_reason;
    private String is_from_net;
    private String offline_time;
    private String topjob_type;
    private String year_salary_from;
    private String year_salary_to;
    private String subordinate_num;
    private String superior;
    private String other_benefits;
    private String recruit_students;
    private String job_slogan;
    private String show_language;
    private String country;
    private String osjob_salary_from;
    private String osjob_salary_to;
    private String salary_currency;
    private String salary_duration;
    private String salary_convert;
    private String osjob_type;
    private String osjob_status;
    private String address;
    private String zipcode;
    private ContactInfoBean contact_info;

    public static GetJobInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, GetJobInfoBean.class);
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getMonthly_play() {
        return monthly_play;
    }

    public void setMonthly_play(String monthly_play) {
        this.monthly_play = monthly_play;
    }

    public String getMonthly_pay() {
        return monthly_pay;
    }

    public void setMonthly_pay(String monthly_pay) {
        this.monthly_pay = monthly_pay;
    }

    public String getMonthly_pay_to() {
        return monthly_pay_to;
    }

    public void setMonthly_pay_to(String monthly_pay_to) {
        this.monthly_pay_to = monthly_pay_to;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEffect_time() {
        return effect_time;
    }

    public void setEffect_time(String effect_time) {
        this.effect_time = effect_time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStudy1() {
        return study1;
    }

    public void setStudy1(String study1) {
        this.study1 = study1;
    }

    public String getStudy2() {
        return study2;
    }

    public void setStudy2(String study2) {
        this.study2 = study2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_open() {
        return user_open;
    }

    public void setUser_open(String user_open) {
        this.user_open = user_open;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getStandby6() {
        return standby6;
    }

    public void setStandby6(String standby6) {
        this.standby6 = standby6;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getLast_refresh_time() {
        return last_refresh_time;
    }

    public void setLast_refresh_time(String last_refresh_time) {
        this.last_refresh_time = last_refresh_time;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPost_rank() {
        return post_rank;
    }

    public void setPost_rank(String post_rank) {
        this.post_rank = post_rank;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getInvite_major() {
        return invite_major;
    }

    public void setInvite_major(String invite_major) {
        this.invite_major = invite_major;
    }

    public String getDeltime() {
        return deltime;
    }

    public void setDeltime(String deltime) {
        this.deltime = deltime;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getRecruiter_count() {
        return recruiter_count;
    }

    public void setRecruiter_count(String recruiter_count) {
        this.recruiter_count = recruiter_count;
    }

    public String getIssue_state() {
        return issue_state;
    }

    public void setIssue_state(String issue_state) {
        this.issue_state = issue_state;
    }

    public String getIs_show_phone() {
        return is_show_phone;
    }

    public void setIs_show_phone(String is_show_phone) {
        this.is_show_phone = is_show_phone;
    }

    public String getIs_show_email() {
        return is_show_email;
    }

    public void setIs_show_email(String is_show_email) {
        this.is_show_email = is_show_email;
    }

    public String getIs_show_fax() {
        return is_show_fax;
    }

    public void setIs_show_fax(String is_show_fax) {
        this.is_show_fax = is_show_fax;
    }

    public String getIs_show_address() {
        return is_show_address;
    }

    public void setIs_show_address(String is_show_address) {
        this.is_show_address = is_show_address;
    }

    public String getIs_send_mail() {
        return is_send_mail;
    }

    public void setIs_send_mail(String is_send_mail) {
        this.is_send_mail = is_send_mail;
    }

    public String getIs_send_mail2() {
        return is_send_mail2;
    }

    public void setIs_send_mail2(String is_send_mail2) {
        this.is_send_mail2 = is_send_mail2;
    }

    public String getIs_show_pay() {
        return is_show_pay;
    }

    public void setIs_show_pay(String is_show_pay) {
        this.is_show_pay = is_show_pay;
    }

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getIs_include_more_degree() {
        return is_include_more_degree;
    }

    public void setIs_include_more_degree(String is_include_more_degree) {
        this.is_include_more_degree = is_include_more_degree;
    }

    public String getIs_send_stow() {
        return is_send_stow;
    }

    public void setIs_send_stow(String is_send_stow) {
        this.is_send_stow = is_send_stow;
    }

    public String getIs_show_linkman() {
        return is_show_linkman;
    }

    public void setIs_show_linkman(String is_show_linkman) {
        this.is_show_linkman = is_show_linkman;
    }

    public String getJob_sort() {
        return job_sort;
    }

    public void setJob_sort(String job_sort) {
        this.job_sort = job_sort;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getIs_show_number_some() {
        return is_show_number_some;
    }

    public void setIs_show_number_some(String is_show_number_some) {
        this.is_show_number_some = is_show_number_some;
    }

    public String getIs_show_pay_interview() {
        return is_show_pay_interview;
    }

    public void setIs_show_pay_interview(String is_show_pay_interview) {
        this.is_show_pay_interview = is_show_pay_interview;
    }

    public String getParent_job_id() {
        return parent_job_id;
    }

    public void setParent_job_id(String parent_job_id) {
        this.parent_job_id = parent_job_id;
    }

    public String getIs_use_auto_reply() {
        return is_use_auto_reply;
    }

    public void setIs_use_auto_reply(String is_use_auto_reply) {
        this.is_use_auto_reply = is_use_auto_reply;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getAverage_pay() {
        return average_pay;
    }

    public void setAverage_pay(String average_pay) {
        this.average_pay = average_pay;
    }

    public String getMonthly_play_to() {
        return monthly_play_to;
    }

    public void setMonthly_play_to(String monthly_play_to) {
        this.monthly_play_to = monthly_play_to;
    }

    public String getPoster_state() {
        return poster_state;
    }

    public void setPoster_state(String poster_state) {
        this.poster_state = poster_state;
    }

    public String getShield_resume() {
        return shield_resume;
    }

    public void setShield_resume(String shield_resume) {
        this.shield_resume = shield_resume;
    }

    public String getCrypt_job_id() {
        return crypt_job_id;
    }

    public void setCrypt_job_id(String crypt_job_id) {
        this.crypt_job_id = crypt_job_id;
    }

    public String getJob_name_pinyin() {
        return job_name_pinyin;
    }

    public void setJob_name_pinyin(String job_name_pinyin) {
        this.job_name_pinyin = job_name_pinyin;
    }

    public String getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(String audit_state) {
        this.audit_state = audit_state;
    }

    public String getAudit_reason() {
        return audit_reason;
    }

    public void setAudit_reason(String audit_reason) {
        this.audit_reason = audit_reason;
    }

    public String getIs_from_net() {
        return is_from_net;
    }

    public void setIs_from_net(String is_from_net) {
        this.is_from_net = is_from_net;
    }

    public String getOffline_time() {
        return offline_time;
    }

    public void setOffline_time(String offline_time) {
        this.offline_time = offline_time;
    }

    public String getTopjob_type() {
        return topjob_type;
    }

    public void setTopjob_type(String topjob_type) {
        this.topjob_type = topjob_type;
    }

    public String getYear_salary_from() {
        return year_salary_from;
    }

    public void setYear_salary_from(String year_salary_from) {
        this.year_salary_from = year_salary_from;
    }

    public String getYear_salary_to() {
        return year_salary_to;
    }

    public void setYear_salary_to(String year_salary_to) {
        this.year_salary_to = year_salary_to;
    }

    public String getSubordinate_num() {
        return subordinate_num;
    }

    public void setSubordinate_num(String subordinate_num) {
        this.subordinate_num = subordinate_num;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getOther_benefits() {
        return other_benefits;
    }

    public void setOther_benefits(String other_benefits) {
        this.other_benefits = other_benefits;
    }

    public String getRecruit_students() {
        return recruit_students;
    }

    public void setRecruit_students(String recruit_students) {
        this.recruit_students = recruit_students;
    }

    public String getJob_slogan() {
        return job_slogan;
    }

    public void setJob_slogan(String job_slogan) {
        this.job_slogan = job_slogan;
    }

    public String getShow_language() {
        return show_language;
    }

    public void setShow_language(String show_language) {
        this.show_language = show_language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOsjob_salary_from() {
        return osjob_salary_from;
    }

    public void setOsjob_salary_from(String osjob_salary_from) {
        this.osjob_salary_from = osjob_salary_from;
    }

    public String getOsjob_salary_to() {
        return osjob_salary_to;
    }

    public void setOsjob_salary_to(String osjob_salary_to) {
        this.osjob_salary_to = osjob_salary_to;
    }

    public String getSalary_currency() {
        return salary_currency;
    }

    public void setSalary_currency(String salary_currency) {
        this.salary_currency = salary_currency;
    }

    public String getSalary_duration() {
        return salary_duration;
    }

    public void setSalary_duration(String salary_duration) {
        this.salary_duration = salary_duration;
    }

    public String getSalary_convert() {
        return salary_convert;
    }

    public void setSalary_convert(String salary_convert) {
        this.salary_convert = salary_convert;
    }

    public String getOsjob_type() {
        return osjob_type;
    }

    public void setOsjob_type(String osjob_type) {
        this.osjob_type = osjob_type;
    }

    public String getOsjob_status() {
        return osjob_status;
    }

    public void setOsjob_status(String osjob_status) {
        this.osjob_status = osjob_status;
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

    public ContactInfoBean getContact_info() {
        return contact_info;
    }

    public void setContact_info(ContactInfoBean contact_info) {
        this.contact_info = contact_info;
    }

    public static class ContactInfoBean {
        /**
         * address : 北京市上地嘉华大厦
         * email : anxuezhe@800hr.com
         * linkman : 安先生
         * phone : 010-85296341-1234,0433-8456123-1234
         * fax : 010-88121212
         * zipcode : 100000
         * mobilephone : 18766667297
         */

        private String address;
        private String email;
        private String linkman;
        private String phone;
        private String fax;
        private String zipcode;
        private String mobilephone;

        public static ContactInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, ContactInfoBean.class);
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.job_id);
        dest.writeString(this.user_id);
        dest.writeString(this.job_name);
        dest.writeString(this.issue_date);
        dest.writeString(this.enterprise_name);
        dest.writeString(this.synopsis);
        dest.writeString(this.monthly_play);
        dest.writeString(this.monthly_pay);
        dest.writeString(this.monthly_pay_to);
        dest.writeString(this.department);
        dest.writeString(this.effect_time);
        dest.writeString(this.number);
        dest.writeString(this.study1);
        dest.writeString(this.study2);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.user_open);
        dest.writeString(this.language1);
        dest.writeString(this.level1);
        dest.writeString(this.language2);
        dest.writeString(this.level2);
        dest.writeString(this.workyear);
        dest.writeString(this.standby6);
        dest.writeString(this.counts);
        dest.writeString(this.quyu);
        dest.writeString(this.last_refresh_time);
        dest.writeString(this.linkman);
        dest.writeString(this.email2);
        dest.writeString(this.post_rank);
        dest.writeString(this.work_type);
        dest.writeString(this.invite_major);
        dest.writeString(this.deltime);
        dest.writeString(this.hy);
        dest.writeString(this.recruiter_count);
        dest.writeString(this.issue_state);
        dest.writeString(this.is_show_phone);
        dest.writeString(this.is_show_email);
        dest.writeString(this.is_show_fax);
        dest.writeString(this.is_show_address);
        dest.writeString(this.is_send_mail);
        dest.writeString(this.is_send_mail2);
        dest.writeString(this.is_show_pay);
        dest.writeString(this.job_number);
        dest.writeInt(this.did);
        dest.writeString(this.is_include_more_degree);
        dest.writeString(this.is_send_stow);
        dest.writeString(this.is_show_linkman);
        dest.writeString(this.job_sort);
        dest.writeString(this.job_type);
        dest.writeString(this.is_show_number_some);
        dest.writeString(this.is_show_pay_interview);
        dest.writeString(this.parent_job_id);
        dest.writeString(this.is_use_auto_reply);
        dest.writeString(this.expiry_date);
        dest.writeString(this.average_pay);
        dest.writeString(this.monthly_play_to);
        dest.writeString(this.poster_state);
        dest.writeString(this.shield_resume);
        dest.writeString(this.crypt_job_id);
        dest.writeString(this.job_name_pinyin);
        dest.writeString(this.audit_state);
        dest.writeString(this.audit_reason);
        dest.writeString(this.is_from_net);
        dest.writeString(this.offline_time);
        dest.writeString(this.topjob_type);
        dest.writeString(this.year_salary_from);
        dest.writeString(this.year_salary_to);
        dest.writeString(this.subordinate_num);
        dest.writeString(this.superior);
        dest.writeString(this.other_benefits);
        dest.writeString(this.recruit_students);
        dest.writeString(this.job_slogan);
        dest.writeString(this.show_language);
        dest.writeString(this.country);
        dest.writeString(this.osjob_salary_from);
        dest.writeString(this.osjob_salary_to);
        dest.writeString(this.salary_currency);
        dest.writeString(this.salary_duration);
        dest.writeString(this.salary_convert);
        dest.writeString(this.osjob_type);
        dest.writeString(this.osjob_status);
        dest.writeString(this.address);
        dest.writeString(this.zipcode);
        dest.writeParcelable((Parcelable) this.contact_info, flags);
    }

    public GetJobInfoBean() {
    }

    protected GetJobInfoBean(Parcel in) {
        this.job_id = in.readString();
        this.user_id = in.readString();
        this.job_name = in.readString();
        this.issue_date = in.readString();
        this.enterprise_name = in.readString();
        this.synopsis = in.readString();
        this.monthly_play = in.readString();
        this.monthly_pay = in.readString();
        this.monthly_pay_to = in.readString();
        this.department = in.readString();
        this.effect_time = in.readString();
        this.number = in.readString();
        this.study1 = in.readString();
        this.study2 = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.user_open = in.readString();
        this.language1 = in.readString();
        this.level1 = in.readString();
        this.language2 = in.readString();
        this.level2 = in.readString();
        this.workyear = in.readString();
        this.standby6 = in.readString();
        this.counts = in.readString();
        this.quyu = in.readString();
        this.last_refresh_time = in.readString();
        this.linkman = in.readString();
        this.email2 = in.readString();
        this.post_rank = in.readString();
        this.work_type = in.readString();
        this.invite_major = in.readString();
        this.deltime = in.readString();
        this.hy = in.readString();
        this.recruiter_count = in.readString();
        this.issue_state = in.readString();
        this.is_show_phone = in.readString();
        this.is_show_email = in.readString();
        this.is_show_fax = in.readString();
        this.is_show_address = in.readString();
        this.is_send_mail = in.readString();
        this.is_send_mail2 = in.readString();
        this.is_show_pay = in.readString();
        this.job_number = in.readString();
        this.did = in.readInt();
        this.is_include_more_degree = in.readString();
        this.is_send_stow = in.readString();
        this.is_show_linkman = in.readString();
        this.job_sort = in.readString();
        this.job_type = in.readString();
        this.is_show_number_some = in.readString();
        this.is_show_pay_interview = in.readString();
        this.parent_job_id = in.readString();
        this.is_use_auto_reply = in.readString();
        this.expiry_date = in.readString();
        this.average_pay = in.readString();
        this.monthly_play_to = in.readString();
        this.poster_state = in.readString();
        this.shield_resume = in.readString();
        this.crypt_job_id = in.readString();
        this.job_name_pinyin = in.readString();
        this.audit_state = in.readString();
        this.audit_reason = in.readString();
        this.is_from_net = in.readString();
        this.offline_time = in.readString();
        this.topjob_type = in.readString();
        this.year_salary_from = in.readString();
        this.year_salary_to = in.readString();
        this.subordinate_num = in.readString();
        this.superior = in.readString();
        this.other_benefits = in.readString();
        this.recruit_students = in.readString();
        this.job_slogan = in.readString();
        this.show_language = in.readString();
        this.country = in.readString();
        this.osjob_salary_from = in.readString();
        this.osjob_salary_to = in.readString();
        this.salary_currency = in.readString();
        this.salary_duration = in.readString();
        this.salary_convert = in.readString();
        this.osjob_type = in.readString();
        this.osjob_status = in.readString();
        this.address = in.readString();
        this.zipcode = in.readString();
        this.contact_info = in.readParcelable(ContactInfoBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetJobInfoBean> CREATOR = new Parcelable.Creator<GetJobInfoBean>() {
        @Override
        public GetJobInfoBean createFromParcel(Parcel source) {
            return new GetJobInfoBean(source);
        }

        @Override
        public GetJobInfoBean[] newArray(int size) {
            return new GetJobInfoBean[size];
        }
    };
}
