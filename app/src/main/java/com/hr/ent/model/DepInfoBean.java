package com.hr.ent.model;

import com.google.gson.Gson;

/**
 * Created by tx on 2017/9/21.
 */

public class DepInfoBean {

    /**
     * user_id : 2755687
     * enterprise_name : 北京红根基建筑有限公司
     * timefrom_m : 11
     * start_day : 2004-01-19
     * login_money : 1100
     * stuff_munber : 14
     * address : 北京市上地嘉华大厦
     * zipcode : 100000
     * linkman : 安先生
     * phone : 010-85296341-1234,0433-8456123-1234
     * fax : 010-88121212
     * email : anxuezhe@800hr.com
     * homepage : www.buildhr.com
     * home_area : 1100
     * synopsis :
     　　英才网联(www.800hr.com)是国内首家分行业专业人才招聘网站，致力于为国内企业与求职者提供分行业、精准化、专业化网络招聘服务。
     　　目前旗下拥有建筑英才网、医药英才网、化工英才网、金融英才网、机械英才网、服装英才网、传媒英才网、教培英才网、IT英才网、电子英才网、酒店餐饮英才网、运输物流英才网、通信英才网、电力英才网、旅游英才网十五个分行业专业人才招聘网站，均已成为该行业最具影响力和专业性的行业人才招聘网站。
     　　英才网联专注于为各行业提供精准的招聘渠道，为行业精英打造专属的求职平台，以推动企业聘用的高效和个人求职的准确。经过多年发展，旗下拥有的十五个分行业专业人才招聘网站，已成为深受行业客户信任与广大求职者青睐的专业招聘与求职平台。

     * intro_url_1 :
     * intro_url_2 :
     * user_open : 2
     * vacancy :
     * company : 20
     * count : 4657
     * jobcount : 0
     * allowtime : 0
     * modifytime : 1505380954
     * is_show_link_address : 1
     * is_show_link_zip : 1
     * is_show_link_man : 1
     * is_show_link_phone : 1
     * is_show_link_fax : 1
     * is_show_link_mail : 1
     * is_show_link_web : 1
     * corporation :
     * patent :
     * ent_logo :
     * map_mark : MAPBCNQMCBFAZOXIYWHIX
     * b1 : 1
     * b2 :
     * mailformat : 3
     * job_list_number : 50
     * resume_list_number : 50
     * system_version : 3
     * style_select : nostyle
     * resume_stow_auto_reply :
     * invite_resume_templet :
     * job_over_remind : 0
     * terrify_id : 50
     * map_lon : 116.32091
     * map_lat : 39.96171
     * google_map_lon : 116.331553
     * google_map_lat : 39.962452
     * lingyu : 111200,111100,111300,111400,111500,112000,111900,111700,111800,111600,112100
     * show_job_report : 1
     * show_open_platform : 0
     * how_to_know : 0
     * crypt_user_id : Bi92A
     * enterprise_name_min : 北京红根基建筑有限公司
     * enterprise_name_pinyin : BeiJingHongGenJiJianZhuYouXianGongSi
     * email_validate : 0
     * patent_validate : 0
     * mobilephone : 18766667297
     * is_show_link_mobilephone : 1
     * is_from_net : 0
     * welfare_label : 交通补贴,法定假日,高温补贴,餐饮补贴,年底双薪,
     * baidu_map_lon : 120.757764
     * baidu_map_lat : 30.782168
     */

    private String user_id;
    private String enterprise_name;
    private String timefrom_m;
    private String start_day;
    private String login_money;
    private String stuff_munber;
    private String address;
    private String zipcode;
    private String linkman;
    private String phone;
    private String fax;
    private String email;
    private String homepage;
    private String home_area;
    private String synopsis;
    private String intro_url_1;
    private String intro_url_2;
    private String user_open;
    private String vacancy;
    private String company;
    private String count;
    private String jobcount;
    private String allowtime;
    private String modifytime;
    private String is_show_link_address;
    private String is_show_link_zip;
    private String is_show_link_man;
    private String is_show_link_phone;
    private String is_show_link_fax;
    private String is_show_link_mail;
    private String is_show_link_web;
    private String corporation;
    private String patent;
    private String ent_logo;
    private String map_mark;
    private String b1;
    private String b2;
    private String mailformat;
    private String job_list_number;
    private String resume_list_number;
    private String system_version;
    private String style_select;
    private String resume_stow_auto_reply;
    private String invite_resume_templet;
    private String job_over_remind;
    private String terrify_id;
    private String map_lon;
    private String map_lat;
    private String google_map_lon;
    private String google_map_lat;
    private String lingyu;
    private String show_job_report;
    private String show_open_platform;
    private String how_to_know;
    private String crypt_user_id;
    private String enterprise_name_min;
    private String enterprise_name_pinyin;
    private String email_validate;
    private String patent_validate;
    private String mobilephone;
    private String is_show_link_mobilephone;
    private String is_from_net;
    private String welfare_label;
    private String baidu_map_lon;
    private String baidu_map_lat;

    public static DepInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, DepInfoBean.class);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getTimefrom_m() {
        return timefrom_m;
    }

    public void setTimefrom_m(String timefrom_m) {
        this.timefrom_m = timefrom_m;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getLogin_money() {
        return login_money;
    }

    public void setLogin_money(String login_money) {
        this.login_money = login_money;
    }

    public String getStuff_munber() {
        return stuff_munber;
    }

    public void setStuff_munber(String stuff_munber) {
        this.stuff_munber = stuff_munber;
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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHome_area() {
        return home_area;
    }

    public void setHome_area(String home_area) {
        this.home_area = home_area;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getIntro_url_1() {
        return intro_url_1;
    }

    public void setIntro_url_1(String intro_url_1) {
        this.intro_url_1 = intro_url_1;
    }

    public String getIntro_url_2() {
        return intro_url_2;
    }

    public void setIntro_url_2(String intro_url_2) {
        this.intro_url_2 = intro_url_2;
    }

    public String getUser_open() {
        return user_open;
    }

    public void setUser_open(String user_open) {
        this.user_open = user_open;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getJobcount() {
        return jobcount;
    }

    public void setJobcount(String jobcount) {
        this.jobcount = jobcount;
    }

    public String getAllowtime() {
        return allowtime;
    }

    public void setAllowtime(String allowtime) {
        this.allowtime = allowtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getIs_show_link_address() {
        return is_show_link_address;
    }

    public void setIs_show_link_address(String is_show_link_address) {
        this.is_show_link_address = is_show_link_address;
    }

    public String getIs_show_link_zip() {
        return is_show_link_zip;
    }

    public void setIs_show_link_zip(String is_show_link_zip) {
        this.is_show_link_zip = is_show_link_zip;
    }

    public String getIs_show_link_man() {
        return is_show_link_man;
    }

    public void setIs_show_link_man(String is_show_link_man) {
        this.is_show_link_man = is_show_link_man;
    }

    public String getIs_show_link_phone() {
        return is_show_link_phone;
    }

    public void setIs_show_link_phone(String is_show_link_phone) {
        this.is_show_link_phone = is_show_link_phone;
    }

    public String getIs_show_link_fax() {
        return is_show_link_fax;
    }

    public void setIs_show_link_fax(String is_show_link_fax) {
        this.is_show_link_fax = is_show_link_fax;
    }

    public String getIs_show_link_mail() {
        return is_show_link_mail;
    }

    public void setIs_show_link_mail(String is_show_link_mail) {
        this.is_show_link_mail = is_show_link_mail;
    }

    public String getIs_show_link_web() {
        return is_show_link_web;
    }

    public void setIs_show_link_web(String is_show_link_web) {
        this.is_show_link_web = is_show_link_web;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getEnt_logo() {
        return ent_logo;
    }

    public void setEnt_logo(String ent_logo) {
        this.ent_logo = ent_logo;
    }

    public String getMap_mark() {
        return map_mark;
    }

    public void setMap_mark(String map_mark) {
        this.map_mark = map_mark;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getMailformat() {
        return mailformat;
    }

    public void setMailformat(String mailformat) {
        this.mailformat = mailformat;
    }

    public String getJob_list_number() {
        return job_list_number;
    }

    public void setJob_list_number(String job_list_number) {
        this.job_list_number = job_list_number;
    }

    public String getResume_list_number() {
        return resume_list_number;
    }

    public void setResume_list_number(String resume_list_number) {
        this.resume_list_number = resume_list_number;
    }

    public String getSystem_version() {
        return system_version;
    }

    public void setSystem_version(String system_version) {
        this.system_version = system_version;
    }

    public String getStyle_select() {
        return style_select;
    }

    public void setStyle_select(String style_select) {
        this.style_select = style_select;
    }

    public String getResume_stow_auto_reply() {
        return resume_stow_auto_reply;
    }

    public void setResume_stow_auto_reply(String resume_stow_auto_reply) {
        this.resume_stow_auto_reply = resume_stow_auto_reply;
    }

    public String getInvite_resume_templet() {
        return invite_resume_templet;
    }

    public void setInvite_resume_templet(String invite_resume_templet) {
        this.invite_resume_templet = invite_resume_templet;
    }

    public String getJob_over_remind() {
        return job_over_remind;
    }

    public void setJob_over_remind(String job_over_remind) {
        this.job_over_remind = job_over_remind;
    }

    public String getTerrify_id() {
        return terrify_id;
    }

    public void setTerrify_id(String terrify_id) {
        this.terrify_id = terrify_id;
    }

    public String getMap_lon() {
        return map_lon;
    }

    public void setMap_lon(String map_lon) {
        this.map_lon = map_lon;
    }

    public String getMap_lat() {
        return map_lat;
    }

    public void setMap_lat(String map_lat) {
        this.map_lat = map_lat;
    }

    public String getGoogle_map_lon() {
        return google_map_lon;
    }

    public void setGoogle_map_lon(String google_map_lon) {
        this.google_map_lon = google_map_lon;
    }

    public String getGoogle_map_lat() {
        return google_map_lat;
    }

    public void setGoogle_map_lat(String google_map_lat) {
        this.google_map_lat = google_map_lat;
    }

    public String getLingyu() {
        return lingyu;
    }

    public void setLingyu(String lingyu) {
        this.lingyu = lingyu;
    }

    public String getShow_job_report() {
        return show_job_report;
    }

    public void setShow_job_report(String show_job_report) {
        this.show_job_report = show_job_report;
    }

    public String getShow_open_platform() {
        return show_open_platform;
    }

    public void setShow_open_platform(String show_open_platform) {
        this.show_open_platform = show_open_platform;
    }

    public String getHow_to_know() {
        return how_to_know;
    }

    public void setHow_to_know(String how_to_know) {
        this.how_to_know = how_to_know;
    }

    public String getCrypt_user_id() {
        return crypt_user_id;
    }

    public void setCrypt_user_id(String crypt_user_id) {
        this.crypt_user_id = crypt_user_id;
    }

    public String getEnterprise_name_min() {
        return enterprise_name_min;
    }

    public void setEnterprise_name_min(String enterprise_name_min) {
        this.enterprise_name_min = enterprise_name_min;
    }

    public String getEnterprise_name_pinyin() {
        return enterprise_name_pinyin;
    }

    public void setEnterprise_name_pinyin(String enterprise_name_pinyin) {
        this.enterprise_name_pinyin = enterprise_name_pinyin;
    }

    public String getEmail_validate() {
        return email_validate;
    }

    public void setEmail_validate(String email_validate) {
        this.email_validate = email_validate;
    }

    public String getPatent_validate() {
        return patent_validate;
    }

    public void setPatent_validate(String patent_validate) {
        this.patent_validate = patent_validate;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getIs_show_link_mobilephone() {
        return is_show_link_mobilephone;
    }

    public void setIs_show_link_mobilephone(String is_show_link_mobilephone) {
        this.is_show_link_mobilephone = is_show_link_mobilephone;
    }

    public String getIs_from_net() {
        return is_from_net;
    }

    public void setIs_from_net(String is_from_net) {
        this.is_from_net = is_from_net;
    }

    public String getWelfare_label() {
        return welfare_label;
    }

    public void setWelfare_label(String welfare_label) {
        this.welfare_label = welfare_label;
    }

    public String getBaidu_map_lon() {
        return baidu_map_lon;
    }

    public void setBaidu_map_lon(String baidu_map_lon) {
        this.baidu_map_lon = baidu_map_lon;
    }

    public String getBaidu_map_lat() {
        return baidu_map_lat;
    }

    public void setBaidu_map_lat(String baidu_map_lat) {
        this.baidu_map_lat = baidu_map_lat;
    }
}
