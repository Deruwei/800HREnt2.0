package com.hr.ent.model;

import android.os.Parcelable;

/**
 * 简历详情基类
 *
 * @author 800hr：zhuhui
 *         <p>
 *         2014-12-1
 */
public abstract class BaseResumeInfoBean implements Parcelable {
    public abstract String getUserName();//获取用户名

    public abstract String getResumeDate();//简历更新日期

    public abstract String getSex();//用户性别

    public abstract String getYear();//用户出生年份

    public abstract String getLocation();//用户现居地

    public abstract String getWorkBeginYear();//开始工作年份

    public abstract String getJobName();//职位名称

    public abstract String getMoreMojor();//专业

    public abstract String getHighEdu();//学历

    public abstract String getPicPhoto();//头像

    public abstract String getUserID();//用户ID

    public abstract String getResumeID();//简历ID

    public abstract String getResumeLanguage();//简历语言

    public abstract String getResumeDeleteID();//删除简历ID

    public abstract String getIsNew();//已读未读

    public abstract void setIsnew(String tag);
}
