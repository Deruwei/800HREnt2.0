package com.hr.ent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 简历详情完整实体类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-12-1
 */
public class ResumeInfoDetalBean implements Parcelable {

    private ResumeTitleInfoBean title_info;
    private ResumeBaseInfoBean base_info;
    private ResumeOrderInfoBean order_info;
    private ResumeAccessInfo assess_info;
    private List<ResumeLauguageInfo> language_list = new ArrayList<ResumeLauguageInfo>();
    private List<ResumeEduInfo> education_list = new ArrayList<ResumeEduInfo>();
    private List<ResumeExpericeInfo> experience_list = new ArrayList<ResumeExpericeInfo>();
    private List<ResumeProjectInfo> project_list = new ArrayList<ResumeProjectInfo>();
    private List<ResumePlantInfo> plant_list = new ArrayList<ResumePlantInfo>();
    private List<ResumeSkillInfo> skill_list = new ArrayList<ResumeSkillInfo>();
    private List<CertificateInfoBean> certificate_list = new ArrayList<CertificateInfoBean>();
    private List<SubjoinInfoBean> subjoin_list = new ArrayList<SubjoinInfoBean>();
    private String remark;//简历备注
    private String remarknumber;//简历编号

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeParcelable(title_info, flags);
        dest.writeParcelable(base_info, flags);
        dest.writeParcelable(order_info, flags);
        dest.writeParcelable(assess_info, flags);
        dest.writeTypedList(language_list);
        dest.writeTypedList(education_list);
        dest.writeTypedList(experience_list);
        dest.writeTypedList(project_list);
        dest.writeTypedList(plant_list);
        dest.writeTypedList(skill_list);
        dest.writeTypedList(certificate_list);
        dest.writeString(remark);
        dest.writeString(remarknumber);
        dest.writeTypedList(subjoin_list);

    }


    public static final Parcelable.Creator<ResumeInfoDetalBean> CREATOR = new Parcelable.Creator<ResumeInfoDetalBean>() {

        @Override
        public ResumeInfoDetalBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            ResumeInfoDetalBean resumeInfoDetalBean = new ResumeInfoDetalBean();
            resumeInfoDetalBean.language_list = new ArrayList<ResumeLauguageInfo>();
            source.readTypedList(resumeInfoDetalBean.language_list, ResumeLauguageInfo.CREATOR);
            resumeInfoDetalBean.education_list = new ArrayList<ResumeEduInfo>();
            source.readTypedList(resumeInfoDetalBean.education_list, ResumeEduInfo.CREATOR);
            resumeInfoDetalBean.experience_list = new ArrayList<ResumeExpericeInfo>();
            source.readTypedList(resumeInfoDetalBean.experience_list, ResumeExpericeInfo.CREATOR);
            resumeInfoDetalBean.project_list = new ArrayList<ResumeProjectInfo>();
            source.readTypedList(resumeInfoDetalBean.project_list, ResumeProjectInfo.CREATOR);

            resumeInfoDetalBean.plant_list = new ArrayList<ResumePlantInfo>();
            source.readTypedList(resumeInfoDetalBean.plant_list, ResumePlantInfo.CREATOR);
            resumeInfoDetalBean.skill_list = new ArrayList<ResumeSkillInfo>();
            source.readTypedList(resumeInfoDetalBean.skill_list, ResumeSkillInfo.CREATOR);

            resumeInfoDetalBean.certificate_list = new ArrayList<CertificateInfoBean>();
            source.readTypedList(resumeInfoDetalBean.certificate_list, CertificateInfoBean.CREATOR);

            resumeInfoDetalBean.subjoin_list = new ArrayList<SubjoinInfoBean>();
            source.readTypedList(resumeInfoDetalBean.subjoin_list, SubjoinInfoBean.CREATOR);

            resumeInfoDetalBean.title_info = source.readParcelable(ResumeTitleInfoBean.class.getClassLoader());
            resumeInfoDetalBean.base_info = source.readParcelable(ResumeBaseInfoBean.class.getClassLoader());
            resumeInfoDetalBean.order_info = source.readParcelable(ResumeOrderInfoBean.class.getClassLoader());
            resumeInfoDetalBean.assess_info = source.readParcelable(ResumeAccessInfo.class.getClassLoader());
            resumeInfoDetalBean.remark = source.readString();
            resumeInfoDetalBean.remarknumber = source.readString();
            return resumeInfoDetalBean;
        }

        @Override
        public ResumeInfoDetalBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ResumeInfoDetalBean[size];
        }

    };


    public List<SubjoinInfoBean> getSubjoin_list() {
        return subjoin_list;
    }

    public void setSubjoin_list(List<SubjoinInfoBean> subjoin_list) {
        this.subjoin_list = subjoin_list;
    }

    public String getRemarknumber() {
        return remarknumber;
    }

    public void setRemarknumber(String remarknumber) {
        this.remarknumber = remarknumber;
    }

    public ResumeTitleInfoBean getTitle_info() {
        return title_info;
    }

    public void setTitle_info(ResumeTitleInfoBean title_info) {
        this.title_info = title_info;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CertificateInfoBean> getCertificate_list() {
        return certificate_list;
    }

    public void setCertificate_list(List<CertificateInfoBean> certificate_list) {
        this.certificate_list = certificate_list;
    }

    public ResumeBaseInfoBean getBase_info() {
        return base_info;
    }

    public void setBase_info(ResumeBaseInfoBean base_info) {
        this.base_info = base_info;
    }

    public ResumeOrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(ResumeOrderInfoBean order_info) {
        this.order_info = order_info;
    }


    public ResumeAccessInfo getAssess_info() {
        return assess_info;
    }

    public void setAssess_info(ResumeAccessInfo assess_info) {
        this.assess_info = assess_info;
    }

    public List<ResumeLauguageInfo> getLanguage_list() {
        return language_list;
    }

    public void setLanguage_list(List<ResumeLauguageInfo> language_list) {
        this.language_list = language_list;
    }

    public List<ResumeEduInfo> getEducation_list() {
        return education_list;
    }

    public void setEducation_list(List<ResumeEduInfo> education_list) {
        this.education_list = education_list;
    }

    public List<ResumeExpericeInfo> getExperience_list() {
        return experience_list;
    }

    public void setExperience_list(List<ResumeExpericeInfo> experience_list) {
        this.experience_list = experience_list;
    }

    public List<ResumeProjectInfo> getProject_list() {
        return project_list;
    }

    public void setProject_list(List<ResumeProjectInfo> project_list) {
        this.project_list = project_list;
    }

    public List<ResumePlantInfo> getPlant_list() {
        return plant_list;
    }

    public void setPlant_list(List<ResumePlantInfo> plant_list) {
        this.plant_list = plant_list;
    }

    public List<ResumeSkillInfo> getSkill_list() {
        return skill_list;
    }

    public void setSkill_list(List<ResumeSkillInfo> skill_list) {
        this.skill_list = skill_list;
    }

}
