package com.hr.ent.app;

import android.app.Application;
import android.content.Context;

import com.hr.ent.model.ContractBean;
import com.hr.ent.model.User;
import com.hr.ent.network.NetworkMng;
import com.hr.ent.utils.CrashUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 全局控制类，主要负责保存一些全局字段、开启网络监控
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-24
 */
public class App extends Application {
    private NetworkMng networkMng;// 网络监控
    private String sessionKey;// 用户获取会话连接后的key
    private String api_ver;// api版本
    private String industry = "11";// 用户选择的行业
    private String industryName = "建筑";// 用户选择的行业名称
    public static int intentionNum = 0;
    public static boolean isContract = false;
    public static String job_json = "";
    private long sessionTime;// 用来记录上次请求session_key的时间，防止session_key失效
    public static final String strKey = "5ps85qNEygN2PnleGPbivpNV";// 百度key
    private User user;
    private ContractBean contractBean;
    private int homeCurrentIndex;

    // 是否加载了简历详情界面
    private boolean isLoadingResumeInfo = false;
    // 是否退出
    private boolean isExit = false;
    //是否是试用
    public static boolean isTrial = false;
    /**
     * 所选行业
     */
    private boolean isLogin = false;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        // 开启网络监控
        networkMng = new NetworkMng(this);
        networkMng.startReceive();

        // 注册crashUtils
        CrashUtils crashUtils = CrashUtils.getInstance();
        crashUtils.init(getApplicationContext());
        initImageLoader(getApplicationContext());
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }


    public NetworkMng getNetworkMng() {
        return networkMng;
    }

    public void setNetworkMng(NetworkMng networkMng) {
        this.networkMng = networkMng;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;

    }

    /**
     * session是否有效
     *
     * @return
     */
    public boolean isValidSession() {
        if (sessionKey == null || sessionKey.equals("")) {
            return false;
        }
        if (System.currentTimeMillis() - sessionTime > 10 * 60 * 1000) {
            return false;
        }
        return true;
    }

    public String getApi_ver() {
        return api_ver;
    }

    public void setApi_ver(String api_ver) {
        this.api_ver = api_ver;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public long getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(long sessionTime) {
        this.sessionTime = sessionTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContractBean getContractBean() {
        return contractBean;
    }

    public void setContractBean(ContractBean contractBean) {
        this.contractBean = contractBean;
    }

    public int getHomeCurrentIndex() {
        return homeCurrentIndex;
    }

    public void setHomeCurrentIndex(int homeCurrentIndex) {
        this.homeCurrentIndex = homeCurrentIndex;
    }

    public boolean isLoadingResumeInfo() {
        return isLoadingResumeInfo;
    }

    public void setLoadingResumeInfo(boolean isLoadingResumeInfo) {
        this.isLoadingResumeInfo = isLoadingResumeInfo;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean getIsLogin() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

}
