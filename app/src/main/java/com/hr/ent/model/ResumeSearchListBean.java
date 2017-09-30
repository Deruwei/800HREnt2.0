package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Colin
 * 日期：2016/9/20 09:25
 * 邮箱：bestxt@qq.com
 */
public class ResumeSearchListBean implements Parcelable {

    /**
     * user_id : 8694067
     * name : 安大宝
     * emailaddress : anxuezhe@800hr.com
     * sex : 1
     * high_education : 本科
     * year : 1989
     * language : A11020202
     * industry : 29,11
     * func : 281000,282000,283000,284000,285000,240000,241000,255000,396000,249000
     * workarea : 1100,1200,1800,2100,2500
     * workyear : 3
     * work_beginyear : 2013
     * resume_id_language : 1_zh
     * issue_date : 1473401380
     * schoolname : 北京邮电大学
     * moremajor : 计算机
     * user_open : 0
     * loccat :
     * location : 0
     * jobtype : 13
     * post_rank : 11
     * all_search : <~O生产管理 生产操作 生产技术 机械/设备/仪器仪表 注册/申报 建筑设计 土木/工程 工程/项目管理 规划设计 现场专业人员O~><~I I~><~T T~><~E2014年3月 2014年3月 北京红根基建筑有限公司 建筑工程师 建筑工程师 E~><~M M~>
     * is_slave : 0
     * is_photo : 0
     * enterprise_secrecy :
     * last_position : 建筑工程师 建筑工程师
     * salaryfrom : 15
     * resume_other_language : en
     * lingyu : 291200,291400,291100,291500,291700,292100,292300,292400,111100,111200,111300,111400,111500,111600,111700,111800,111900,112000,112100,112200,112300,112400,112500
     * miscinfo :
     * user_train :
     * introduction :
     * user_experience : 2014年3月 2014年3月 北京红根基建筑有限公司 建筑工程师 建筑工程师
     * user_order : 生产管理 生产操作 生产技术 机械/设备/仪器仪表 注册/申报 建筑设计 土木/工程 工程/项目管理 规划设计 现场专业人员
     * user_item :
     * resume_type : 1
     * current_yearsalary : 0
     * is_download : 0
     * is_stow : 0
     * is_invite : 0
     * remark :
     * resume_language : zh
     * resume_id : 1
     * intention_info : {"intention_state":"","apply_state":""}
     * pic_filekey :
     */

    private String user_id;
    private String name;
    private String emailaddress;
    private String sex;
    private String high_education;
    private String year;
    private String language;
    private String industry;
    private String func;
    private String workarea;
    private String workyear;
    private String work_beginyear;
    private String resume_id_language;
    private String issue_date;
    private String schoolname;
    private String moremajor;
    private String user_open;
    private String loccat;
    private String location;
    private String jobtype;
    private String post_rank;
    private String all_search;
    private String is_slave;
    private String is_photo;
    private String enterprise_secrecy;
    private String last_position;
    private String salaryfrom;
    private String resume_other_language;
    private String lingyu;
    private String miscinfo;
    private String user_train;
    private String introduction;
    private String user_experience;
    private String user_order;
    private String user_item;
    private String resume_type;
    private String current_yearsalary;
    private String is_download;
    private String is_stow;
    private String is_invite;
    private String remark;
    private String resume_language;
    private String resume_id;

    public String getResume_number() {
        return resume_number;
    }

    public void setResume_number(String resume_number) {
        this.resume_number = resume_number;
    }

    private String resume_number;
    /**
     * intention_state :
     * apply_state :
     */

    private IntentionInfoBean intention_info;
    private String pic_filekey;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHigh_education() {
        return high_education;
    }

    public void setHigh_education(String high_education) {
        this.high_education = high_education;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getWork_beginyear() {
        return work_beginyear;
    }

    public void setWork_beginyear(String work_beginyear) {
        this.work_beginyear = work_beginyear;
    }

    public String getResume_id_language() {
        return resume_id_language;
    }

    public void setResume_id_language(String resume_id_language) {
        this.resume_id_language = resume_id_language;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getMoremajor() {
        return moremajor;
    }

    public void setMoremajor(String moremajor) {
        this.moremajor = moremajor;
    }

    public String getUser_open() {
        return user_open;
    }

    public void setUser_open(String user_open) {
        this.user_open = user_open;
    }

    public String getLoccat() {
        return loccat;
    }

    public void setLoccat(String loccat) {
        this.loccat = loccat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getPost_rank() {
        return post_rank;
    }

    public void setPost_rank(String post_rank) {
        this.post_rank = post_rank;
    }

    public String getAll_search() {
        return all_search;
    }

    public void setAll_search(String all_search) {
        this.all_search = all_search;
    }

    public String getIs_slave() {
        return is_slave;
    }

    public void setIs_slave(String is_slave) {
        this.is_slave = is_slave;
    }

    public String getIs_photo() {
        return is_photo;
    }

    public void setIs_photo(String is_photo) {
        this.is_photo = is_photo;
    }

    public String getEnterprise_secrecy() {
        return enterprise_secrecy;
    }

    public void setEnterprise_secrecy(String enterprise_secrecy) {
        this.enterprise_secrecy = enterprise_secrecy;
    }

    public String getLast_position() {
        return last_position;
    }

    public void setLast_position(String last_position) {
        this.last_position = last_position;
    }

    public String getSalaryfrom() {
        return salaryfrom;
    }

    public void setSalaryfrom(String salaryfrom) {
        this.salaryfrom = salaryfrom;
    }

    public String getResume_other_language() {
        return resume_other_language;
    }

    public void setResume_other_language(String resume_other_language) {
        this.resume_other_language = resume_other_language;
    }

    public String getLingyu() {
        return lingyu;
    }

    public void setLingyu(String lingyu) {
        this.lingyu = lingyu;
    }

    public String getMiscinfo() {
        return miscinfo;
    }

    public void setMiscinfo(String miscinfo) {
        this.miscinfo = miscinfo;
    }

    public String getUser_train() {
        return user_train;
    }

    public void setUser_train(String user_train) {
        this.user_train = user_train;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUser_experience() {
        return user_experience;
    }

    public void setUser_experience(String user_experience) {
        this.user_experience = user_experience;
    }

    public String getUser_order() {
        return user_order;
    }

    public void setUser_order(String user_order) {
        this.user_order = user_order;
    }

    public String getUser_item() {
        return user_item;
    }

    public void setUser_item(String user_item) {
        this.user_item = user_item;
    }

    public String getResume_type() {
        return resume_type;
    }

    public void setResume_type(String resume_type) {
        this.resume_type = resume_type;
    }

    public String getCurrent_yearsalary() {
        return current_yearsalary;
    }

    public void setCurrent_yearsalary(String current_yearsalary) {
        this.current_yearsalary = current_yearsalary;
    }

    public String getIs_download() {
        return is_download;
    }

    public void setIs_download(String is_download) {
        this.is_download = is_download;
    }

    public String getIs_stow() {
        return is_stow;
    }

    public void setIs_stow(String is_stow) {
        this.is_stow = is_stow;
    }

    public String getIs_invite() {
        return is_invite;
    }

    public void setIs_invite(String is_invite) {
        this.is_invite = is_invite;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResume_language() {
        return resume_language;
    }

    public void setResume_language(String resume_language) {
        this.resume_language = resume_language;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public IntentionInfoBean getIntention_info() {
        return intention_info;
    }

    public void setIntention_info(IntentionInfoBean intention_info) {
        this.intention_info = intention_info;
    }

    public String getPic_filekey() {
        return pic_filekey;
    }

    public void setPic_filekey(String pic_filekey) {
        this.pic_filekey = pic_filekey;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user_id);
        dest.writeString(this.name);
        dest.writeString(this.emailaddress);
        dest.writeString(this.sex);
        dest.writeString(this.high_education);
        dest.writeString(this.year);
        dest.writeString(this.language);
        dest.writeString(this.industry);
        dest.writeString(this.func);
        dest.writeString(this.workarea);
        dest.writeString(this.workyear);
        dest.writeString(this.work_beginyear);
        dest.writeString(this.resume_id_language);
        dest.writeString(this.issue_date);
        dest.writeString(this.schoolname);
        dest.writeString(this.moremajor);
        dest.writeString(this.user_open);
        dest.writeString(this.loccat);
        dest.writeString(this.location);
        dest.writeString(this.jobtype);
        dest.writeString(this.post_rank);
        dest.writeString(this.all_search);
        dest.writeString(this.is_slave);
        dest.writeString(this.is_photo);
        dest.writeString(this.enterprise_secrecy);
        dest.writeString(this.last_position);
        dest.writeString(this.salaryfrom);
        dest.writeString(this.resume_other_language);
        dest.writeString(this.lingyu);
        dest.writeString(this.miscinfo);
        dest.writeString(this.user_train);
        dest.writeString(this.introduction);
        dest.writeString(this.user_experience);
        dest.writeString(this.user_order);
        dest.writeString(this.user_item);
        dest.writeString(this.resume_type);
        dest.writeString(this.current_yearsalary);
        dest.writeString(this.is_download);
        dest.writeString(this.is_stow);
        dest.writeString(this.is_invite);
        dest.writeString(this.remark);
        dest.writeString(this.resume_language);
        dest.writeString(this.resume_id);
        dest.writeParcelable((Parcelable) this.intention_info, flags);
        dest.writeString(this.pic_filekey);
        dest.writeString(this.resume_number);
    }

    public ResumeSearchListBean() {
    }

    protected ResumeSearchListBean(Parcel in) {
        this.user_id = in.readString();
        this.name = in.readString();
        this.emailaddress = in.readString();
        this.sex = in.readString();
        this.high_education = in.readString();
        this.year = in.readString();
        this.language = in.readString();
        this.industry = in.readString();
        this.func = in.readString();
        this.workarea = in.readString();
        this.workyear = in.readString();
        this.work_beginyear = in.readString();
        this.resume_id_language = in.readString();
        this.issue_date = in.readString();
        this.schoolname = in.readString();
        this.moremajor = in.readString();
        this.user_open = in.readString();
        this.loccat = in.readString();
        this.location = in.readString();
        this.jobtype = in.readString();
        this.post_rank = in.readString();
        this.all_search = in.readString();
        this.is_slave = in.readString();
        this.is_photo = in.readString();
        this.enterprise_secrecy = in.readString();
        this.last_position = in.readString();
        this.salaryfrom = in.readString();
        this.resume_other_language = in.readString();
        this.lingyu = in.readString();
        this.miscinfo = in.readString();
        this.user_train = in.readString();
        this.introduction = in.readString();
        this.user_experience = in.readString();
        this.user_order = in.readString();
        this.user_item = in.readString();
        this.resume_type = in.readString();
        this.current_yearsalary = in.readString();
        this.is_download = in.readString();
        this.is_stow = in.readString();
        this.is_invite = in.readString();
        this.remark = in.readString();
        this.resume_language = in.readString();
        this.resume_id = in.readString();
        this.intention_info = in.readParcelable(IntentionInfoBean.class.getClassLoader());
        this.pic_filekey = in.readString();
        this.resume_number = in.readString();
    }

    public static final Creator<ResumeSearchListBean> CREATOR = new Creator<ResumeSearchListBean>() {
        @Override
        public ResumeSearchListBean createFromParcel(Parcel source) {
            return new ResumeSearchListBean(source);
        }
        @Override
        public ResumeSearchListBean[] newArray(int size) {
            return new ResumeSearchListBean[size];
        }
    };
}
