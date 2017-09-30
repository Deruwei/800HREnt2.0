package com.hr.ent.utils;

/**
 * 网络请求常量工具类，主要存放网络请求的方法名
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-3-27
 */
public class HttpHelper {
    // 所有网络请求提交的method参数名
    public static final String METHOD = "method";

    // 客户端第一次访问或者会话连接超时后再次其你去会话连接的方法
    public static final String USER_CONNECT = "user.connect";
    // 用户登录请求方法
    public static final String ENTERPRISE_LOGIN = "enterprise.login";
    // 用户退出
    public static final String LOGOUT = "enterprise.logout";
    // 获取发布中职位的方法
    public static final String GETJOB = "enterprise_job.jobgetlist";
    // 获取职位统计
    public static final String GETJOBNUM = "enterprise_job.getcount";
    // 获取职位统计
    public static final String ENTERPRISE_JOBGET = "enterprise_job.get";
    // 获取当前合同信息
    public static final String GETCONTRACT = "enterprise_contract.contractget";
    // 邀请记录
    public static final String INVITELIST = "enterprise_resume.invitegetlist";
    // 获取部门列表
    public static final String GET_DEPARTMENT = "enterprise.getdepartment";
    //获取公司信息
    public static final String ENTERPRISEGETBASEINFO = "enterprise.getbaseinfo";
    // 获取部门列表
    public static final String ENTERPRISE_JOBADD = "enterprise_job.add";
    // 删除职位至回收站
    public static final String ENTERPRISE_JOBDEL = "enterprise_job.del";
    // 开启职位
    public static final String ENTERPRISE_JOBOPEN = "enterprise_job.open";
    // 发布职位
    public static final String ENTERPRISE_JOBISSUE = "enterprise_job.issue";
    //暂停职位
    public static final String ENTERPRISE_JOBPAUSE = "enterprise_job.pause";
    /**
     * 意向沟通
     */
    public static final String INTENTIONLIST = "enterprise_intention.listresume";
    /**
     * 获取寻访列表
     */
    public static final String GETXUNFANGLIST = "enterprise_xunfang.listjob";
    // 获取服务信息
    public static final String CONTRACTSTATE = "enterprise_contract.stateget";
    public static final String CONTRACTINFO="enterprise_contract.contractget";
    // 获取合同列表
    public static final String CONTRACTLIST = "enterprise_contract.list";
    // 职位刷新
    public static final String JOBREFSH = "enterprise_job.jobrefresh";
    // 简历数
    public static final String BOXFILTER = "enterprise_resume.boxfilterlist";
    // 简历列表
    public static final String RESUMELIST = "enterprise_resume.getlist";
    // 已下载简历列表
    public static final String DOWNRESUMELIST = "enterprise_resume.downloadgetlist";

    //enterprise_resume.setbox 设置投递简历到人才库
    public static final String RESUMESETBOX = "enterprise_resume.setbox";
    //
    public static final String RESUMESEARCHSETBOX = "enterprise_resume.searchsetbox";
    //设置筛选状态
    public static final String RESUMESETFILTER = "enterprise_resume.setfilter";
    // "enterprise_job.jobresumegetcount";
    // public static final String JOBRESUME_COUNT =
    // 职位投递简历数


    // 简历预览
    public static final String RESUMEPREVIEW = "enterprise_resume.previewget";
    // 简历标注
    public static final String RESUME_NOTE = "enterprise_resume.setremark";
    // 删除简历至回收站
    public static final String RESUME_DELETE = "enterprise_resume.resumedel";
    // 简历转发
    public static final String RESUME_FORWARD = "enterprise_resume.sendmail";
    // 获取通知信模板
    public static final String RESUME_INVITE = "enterprise_resume.etgetlist";
    // 发送通知信
    public static final String INVITE_SEND = "enterprise_resume.invitesend";
    // 问题反馈
    public static final String FEEDBACK = "user.feedback";
    // 刷新全部职位
    public static final String JOBREFSH_ALL = "enterprise_job.jobrefreshall";
    // 设置简历已读
    public static final String RESUME_READ = "enterprise_resume.setread";
    // 获取简历总数
    public static final String NEW_RESUME_NUM = "enterprise_resume.getcount";
    // 获取简历总数
    public static final String BASE_INFO = "enterprise.getbaseinfo";
    // 企业注册
    public static final String COMPANY_REGISTER = "enterprise.register";
    //简历搜索
    public static final String RESUME_SEARCH = "enterprise_resume.search";
    //下载简历联系方式
    public static final String DOWNLOAD_CONTACT = "enterprise_resume.downloadcontact";
    //申请意向沟通
    public static final String INTENTION_APPLY = "enterprise_intention.apply";

}
