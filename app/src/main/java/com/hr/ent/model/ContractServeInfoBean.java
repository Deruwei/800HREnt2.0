package com.hr.ent.model;

import java.util.List;

/**
 * Created by wdr on 2017/9/27.
 */

public class ContractServeInfoBean {

    /**
     * cbegin_time : 1489420800
     * cend_time : 1520956799
     * pause_state : 1
     * sign_time : 1460563200
     * edit_username : 安学哲
     * audit_status : 4
     * contract_number : 1604141122590003
     * salespromotion_id : 0
     * serve_list : [{"serve_name":"年度基础","serve_type":"基础服务","server_time":"1年","server_date":"2017年03月14日 至 2018年03月13日"},{"serve_name":"人才寻访","serve_type":"高端人才服务","server_time":"365天","server_date":"2017年03月14日 至 2018年03月13日"},{"serve_name":"意向沟通","serve_type":"高端人才服务","server_time":"365天","server_date":"2017年03月14日 至 2018年03月13日"},{"serve_name":"高端简历下载-测试-请勿删","serve_type":"高端人才服务","server_time":"365天","server_date":"2017年03月14日 至 2018年03月13日"},{"serve_name":"高端职位发布","serve_type":"高端人才服务","server_time":"365天","server_date":"2017年03月14日 至 2018年03月13日"}]
     */

    private String cbegin_time;
    private String cend_time;
    private String pause_state;
    private String sign_time;
    private String edit_username;
    private String audit_status;
    private String contract_number;
    private String salespromotion_id;
    private List<ServeListBean> serve_list;

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

    public String getSalespromotion_id() {
        return salespromotion_id;
    }

    public void setSalespromotion_id(String salespromotion_id) {
        this.salespromotion_id = salespromotion_id;
    }

    public List<ServeListBean> getServe_list() {
        return serve_list;
    }

    public void setServe_list(List<ServeListBean> serve_list) {
        this.serve_list = serve_list;
    }

    public static class ServeListBean {
        /**
         * serve_name : 年度基础
         * serve_type : 基础服务
         * server_time : 1年
         * server_date : 2017年03月14日 至 2018年03月13日
         */

        private String serve_name;
        private String serve_type;
        private String server_time;
        private String server_date;

        public String getServe_name() {
            return serve_name;
        }

        public void setServe_name(String serve_name) {
            this.serve_name = serve_name;
        }

        public String getServe_type() {
            return serve_type;
        }

        public void setServe_type(String serve_type) {
            this.serve_type = serve_type;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public String getServer_date() {
            return server_date;
        }

        public void setServer_date(String server_date) {
            this.server_date = server_date;
        }
    }

    @Override
    public String toString() {
        return "ContractServeInfoBean{" +
                "cbegin_time='" + cbegin_time + '\'' +
                ", cend_time='" + cend_time + '\'' +
                ", pause_state='" + pause_state + '\'' +
                ", sign_time='" + sign_time + '\'' +
                ", edit_username='" + edit_username + '\'' +
                ", audit_status='" + audit_status + '\'' +
                ", contract_number='" + contract_number + '\'' +
                ", salespromotion_id='" + salespromotion_id + '\'' +
                ", serve_list=" + serve_list +
                '}';
    }
}
