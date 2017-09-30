package com.hr.ent.model;

import java.util.List;

/**
 * Created by Colin on 2016/11/17.
 */

public class TalentsListBean {

    /**
     * error_code : 0
     * list : [{"xfjob_id":"284","enterprise_id":"2755687","enterprise_name":"北京红根基建筑有限公司","job_id":"0","job_name":"1","industry":"11","department":"北京红根基建筑有限公司","area":"4400","job_type":"248101","workyear":"10","study":"10","monthly_pay":"13","monthly_pay_to":"13","post_rank":"14","school":"","major":"","recruit_students":"0","work_type":"13","number":"1","age1":"0","age2":"0","language1":"10","level1":"1","language2":"10","level2":"1","subordinate_num":"0","superior":"1","keyword":"1","synopsis":"1","qualification":"","requirement":"1","highlight":"13","other_benefits":"1","conditions":"","link_man":"1","link_phone":"18511862889","add_time":"1478761320","xunfang_state":"1","estimate":"","estimate_time":"0","allotee_id":"0","allotee":"","allot_time":"0","start_time":"0","expected_time":"0","finish_time":"0","editor":"","edit_time":"0","serve_key":"167686_6229_8791","enterprise_pid":"0","has_newresume":"1","xf_type":"1","salary_structure":"","salary_type":"1","salary_txt":"","add_from":"1","resume_totals":"0"}]
     * _run_time : 0.00406813621521
     * navpage_info : {"current_page":"1","total_pages":7,"record_nums":7,"page_nums":"1"}
     */

    private String error_code;
    private String _run_time;
    private NavpageInfoBean navpage_info;
    private List<ListBean> list;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String get_run_time() {
        return _run_time;
    }

    public void set_run_time(String _run_time) {
        this._run_time = _run_time;
    }

    public NavpageInfoBean getNavpage_info() {
        return navpage_info;
    }

    public void setNavpage_info(NavpageInfoBean navpage_info) {
        this.navpage_info = navpage_info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class NavpageInfoBean {
        /**
         * current_page : 1
         * total_pages : 7
         * record_nums : 7
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
    }

    public static class ListBean {
        /**
         * xfjob_id : 284
         * enterprise_id : 2755687
         * enterprise_name : 北京红根基建筑有限公司
         * job_id : 0
         * job_name : 1
         * industry : 11
         * department : 北京红根基建筑有限公司
         * area : 4400
         * job_type : 248101
         * workyear : 10
         * study : 10
         * monthly_pay : 13
         * monthly_pay_to : 13
         * post_rank : 14
         * school :
         * major :
         * recruit_students : 0
         * work_type : 13
         * number : 1
         * age1 : 0
         * age2 : 0
         * language1 : 10
         * level1 : 1
         * language2 : 10
         * level2 : 1
         * subordinate_num : 0
         * superior : 1
         * keyword : 1
         * synopsis : 1
         * qualification :
         * requirement : 1
         * highlight : 13
         * other_benefits : 1
         * conditions :
         * link_man : 1
         * link_phone : 18511862889
         * add_time : 1478761320
         * xunfang_state : 1
         * estimate :
         * estimate_time : 0
         * allotee_id : 0
         * allotee :
         * allot_time : 0
         * start_time : 0
         * expected_time : 0
         * finish_time : 0
         * editor :
         * edit_time : 0
         * serve_key : 167686_6229_8791
         * enterprise_pid : 0
         * has_newresume : 1
         * xf_type : 1
         * salary_structure :
         * salary_type : 1
         * salary_txt :
         * add_from : 1
         * resume_totals : 0
         */

        private String xfjob_id;
        private String enterprise_id;
        private String enterprise_name;
        private String job_id;
        private String job_name;
        private String industry;
        private String department;
        private String area;
        private String job_type;
        private String workyear;
        private String study;
        private String monthly_pay;
        private String monthly_pay_to;
        private String post_rank;
        private String school;
        private String major;
        private String recruit_students;
        private String work_type;
        private String number;
        private String age1;
        private String age2;
        private String language1;
        private String level1;
        private String language2;
        private String level2;
        private String subordinate_num;
        private String superior;
        private String keyword;
        private String synopsis;
        private String qualification;
        private String requirement;
        private String highlight;
        private String other_benefits;
        private String conditions;
        private String link_man;
        private String link_phone;
        private String add_time;
        private String xunfang_state;
        private String estimate;
        private String estimate_time;
        private String allotee_id;
        private String allotee;
        private String allot_time;
        private String start_time;
        private String expected_time;
        private String finish_time;
        private String editor;
        private String edit_time;
        private String serve_key;
        private String enterprise_pid;
        private String has_newresume;
        private String xf_type;
        private String salary_structure;
        private String salary_type;
        private String salary_txt;
        private String add_from;
        private String resume_totals;

        public String getXfjob_id() {
            return xfjob_id;
        }

        public void setXfjob_id(String xfjob_id) {
            this.xfjob_id = xfjob_id;
        }

        public String getEnterprise_id() {
            return enterprise_id;
        }

        public void setEnterprise_id(String enterprise_id) {
            this.enterprise_id = enterprise_id;
        }

        public String getEnterprise_name() {
            return enterprise_name;
        }

        public void setEnterprise_name(String enterprise_name) {
            this.enterprise_name = enterprise_name;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getJob_type() {
            return job_type;
        }

        public void setJob_type(String job_type) {
            this.job_type = job_type;
        }

        public String getWorkyear() {
            return workyear;
        }

        public void setWorkyear(String workyear) {
            this.workyear = workyear;
        }

        public String getStudy() {
            return study;
        }

        public void setStudy(String study) {
            this.study = study;
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

        public String getPost_rank() {
            return post_rank;
        }

        public void setPost_rank(String post_rank) {
            this.post_rank = post_rank;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getRecruit_students() {
            return recruit_students;
        }

        public void setRecruit_students(String recruit_students) {
            this.recruit_students = recruit_students;
        }

        public String getWork_type() {
            return work_type;
        }

        public void setWork_type(String work_type) {
            this.work_type = work_type;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getAge1() {
            return age1;
        }

        public void setAge1(String age1) {
            this.age1 = age1;
        }

        public String getAge2() {
            return age2;
        }

        public void setAge2(String age2) {
            this.age2 = age2;
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

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getQualification() {
            return qualification;
        }

        public void setQualification(String qualification) {
            this.qualification = qualification;
        }

        public String getRequirement() {
            return requirement;
        }

        public void setRequirement(String requirement) {
            this.requirement = requirement;
        }

        public String getHighlight() {
            return highlight;
        }

        public void setHighlight(String highlight) {
            this.highlight = highlight;
        }

        public String getOther_benefits() {
            return other_benefits;
        }

        public void setOther_benefits(String other_benefits) {
            this.other_benefits = other_benefits;
        }

        public String getConditions() {
            return conditions;
        }

        public void setConditions(String conditions) {
            this.conditions = conditions;
        }

        public String getLink_man() {
            return link_man;
        }

        public void setLink_man(String link_man) {
            this.link_man = link_man;
        }

        public String getLink_phone() {
            return link_phone;
        }

        public void setLink_phone(String link_phone) {
            this.link_phone = link_phone;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getXunfang_state() {
            return xunfang_state;
        }

        public void setXunfang_state(String xunfang_state) {
            this.xunfang_state = xunfang_state;
        }

        public String getEstimate() {
            return estimate;
        }

        public void setEstimate(String estimate) {
            this.estimate = estimate;
        }

        public String getEstimate_time() {
            return estimate_time;
        }

        public void setEstimate_time(String estimate_time) {
            this.estimate_time = estimate_time;
        }

        public String getAllotee_id() {
            return allotee_id;
        }

        public void setAllotee_id(String allotee_id) {
            this.allotee_id = allotee_id;
        }

        public String getAllotee() {
            return allotee;
        }

        public void setAllotee(String allotee) {
            this.allotee = allotee;
        }

        public String getAllot_time() {
            return allot_time;
        }

        public void setAllot_time(String allot_time) {
            this.allot_time = allot_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getExpected_time() {
            return expected_time;
        }

        public void setExpected_time(String expected_time) {
            this.expected_time = expected_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getEdit_time() {
            return edit_time;
        }

        public void setEdit_time(String edit_time) {
            this.edit_time = edit_time;
        }

        public String getServe_key() {
            return serve_key;
        }

        public void setServe_key(String serve_key) {
            this.serve_key = serve_key;
        }

        public String getEnterprise_pid() {
            return enterprise_pid;
        }

        public void setEnterprise_pid(String enterprise_pid) {
            this.enterprise_pid = enterprise_pid;
        }

        public String getHas_newresume() {
            return has_newresume;
        }

        public void setHas_newresume(String has_newresume) {
            this.has_newresume = has_newresume;
        }

        public String getXf_type() {
            return xf_type;
        }

        public void setXf_type(String xf_type) {
            this.xf_type = xf_type;
        }

        public String getSalary_structure() {
            return salary_structure;
        }

        public void setSalary_structure(String salary_structure) {
            this.salary_structure = salary_structure;
        }

        public String getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(String salary_type) {
            this.salary_type = salary_type;
        }

        public String getSalary_txt() {
            return salary_txt;
        }

        public void setSalary_txt(String salary_txt) {
            this.salary_txt = salary_txt;
        }

        public String getAdd_from() {
            return add_from;
        }

        public void setAdd_from(String add_from) {
            this.add_from = add_from;
        }

        public String getResume_totals() {
            return resume_totals;
        }

        public void setResume_totals(String resume_totals) {
            this.resume_totals = resume_totals;
        }
    }
}
