package com.hr.ent.utils;

import android.os.Environment;

import java.io.File;

/**
 * @author 800hr：zhuhui 全局常量类，主要用于保存项目中使用的所有常量字段 2014-3-26
 */
public class Const {
	// 无网络标识
	public static final int ApiNoNet = -1;
	// api请求成功标识
	public static final int ApiSuccess = 0;
	// 登录失效标识
	public static final int LoginInvalid = 303;
	// 第一次请求会话连接时用的加密密钥
	public static final String secret_key = "2cb7G62b2drGdJobcQJ1O8813aV7fds7.800hr.com.800hr.";
	// 动态加密密钥的固定部分
	public static final String pre_secret_key = "2cb7G62b2drGdJobcQJ1O8813aV7fds7";
	// 接口返回的请求错误码字段名
	public static String ERROR_CODE = "error_code";
	// 客户端请求的api版本号
	public static final String API_VER = "2.1.0";
	// 客户端的系统
	public static final String OS_NAME = "android";
	// 客户端安装来源
	public static final String DNFROM = "wandoujia";
	// 结果请求的根域名
	//线上
	public static final String API_URL = "https://api.800hr.com/svrdo.php";
	//测试
//	public static final String API_URL = "https://api.800hr.com/svrdo_v0.php";
	public static final int PHOTO_MAX_SIZE = 3 * 1024 * 1024;// 用户上传头像的最大限制
	public static final String LOGPATH = "/800hr/log/";
	public static final String SAVEPWD = "SAVEPWD";
	public static final String AUTOLOGIN = "AUTOLOGIN";
	/**
	 *薪资查询
	 */
	public static final String PayQuareURL = "http://api.800hr.com/user/emolument/index_solo.html";
	/**
	 * 行业找工作链接
	 */
	public static final String DOWNAPK = "http://www.800hr.com/app/android/800hr.apk";
	/**
	 * 客户端程序的发布版本号
	 */
	public static final String CLIENT_VER = "1.0.0";
	/**
	 * 服务器接口访问地址
	 */
	public static final String SERVER_URL = "https://api.800hr.com/svrdo.php";
	/**
	 * 图片下载主地址
	 */
	public static final String IMAGE_ROOTPATH = "http://file.800hr.com/";

	// 更新数组版本信息
	public static final String ARRAY_URL = "https://api.800hr.com/client/getarrayver.php";

	// 更新城市数组信息
	public static final String CITY_ARRAY_URL = "https://api.800hr.com/client/file/array/city.php";
	// 更新职位数组信息
	public static final String JOB_ARRAY_URL = "https://api.800hr.com/client/file/array/job.php";
	// 更新领域数组信息
	public static final String LINGYU_ARRAY_URL = "https://api.800hr.com/client/file/array/lingyu.php";
	// 城市数组文件名
	public static final String CITY_FILENAME = "city.json";
	// 职位数组文件名
	public static final String JOB_FILENAME = "job.json";
	// 领域数组文件名
	public static final String LINGYU_FILENAME = "lingyu.json";
	// 工作地点城市信息中添加全国项标识
	public static final String CITY_COUNTRY = "*0";
	// 职位分类中添加全部职位标识
	public static final String JOB_ALL = "#0";
	// 领域分类中添加全部领域标识
	public static final String LINGYU_ALL = "0";

	// 字符集编码
	public static final String ENCODE = "utf-8";
	// SharedPreference文件名
	public static final String PREFS_NAME = "800HR_HD";
	// SharedPreference中标识是否需显示欢迎界面的字符
	public static final String IS_WELCOME = "is_welcome";
	// SharedPreference中标识设备ID的字符
	public static final String DEVICE_USER_ID = "device_user_id";
	// SharedPreference中标识是否自动登录的字符
	public static final String IS_AUTOLOGIN = "isautologin";
	// SharedPreference中标识城市数组版本的字符
	public static final String CITY_VER = "cityver";
	public static final String CITY_DATE = "citydate";

	// SharedPreference中标识职位数组版本的字符
	public static final String JOB_VER = "jobver";
	public static final String JOB_DATE = "jobdate";

	// SharedPreference中标识领域数组版本的字符
	public static final String LINGYU_VER = "lingyuver";
	public static final String LINGYU_DATE = "lingyudate";
	//合同结束时间
	public static  String ContractEndTime="";
	// 单页请求的数量
	public static final int PAGESIZE = 20;
	// 图片缓存位置
	public static String IMAGE_TEMP_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".cache800hrHD";
	public static String IMAGE_TEMP_NAME = "temp.jpg";
	public static final String BOX = "box";// 按简历类型查看标识
	public static final String FILTER = "filter";// 按简历类型查看标识
	public static final String DOWNLOAD = "download";// 按简历类型查看标识
	public static final String JOB = "job";// 按简历类型查看标识
	public static final String IS_NEW = "isnew";// 按简历类型查看标识
	public static final String TODAY = "today";// 按简历类型查看标识
	public static final String URL = "file:///android_asset/emolument/index_solo.html";
	// 触屏版各行业网址
	public static final String BUILD_URL = "http://m.buildhr.com/job/";// 建筑
	public static final String ALL_URL = "http://m.800hr.com/job/";// 全行业
	public static final String CHEN_URL = "http://m.chenhr.com/job/";// 化工
	public static final String BANK_URL = "http://m.bankhr.com/job/";// 金融
	public static final String HEALTH_URL = "http://m.healthr.com/job/";// 医药
	// 广播常量
	public static final String INIT_PAGE = "init_page";
	public static final String REFSH_PAGE = "refsh_page";
	public static final String EXIT = "exit";
	public static final String LOGIN_OUT = "login_out";
	// 简历浏览的广播常量
	public static final String RESUME_READ = "resume_read";
	public static final String INIT_DATA = "init_data";
	public static final String LOAD_MORE = "load_more";
	public static final String UPDATE_ADAPTER = "update_adapter";
	public static final String RESUME_LIST = "resume_list";
	// 欢迎界面的广告
	public static final String LOGIN_SUCCESS = "login_success";
	public static final String LOGIN_FAIL = "login_fail";
	//PAR_KEY
	public static final String PAR_KEY = "PAR_KEY";
	//是否是新版本
	public static final String IS_FIRST = "isFirst10";
}
