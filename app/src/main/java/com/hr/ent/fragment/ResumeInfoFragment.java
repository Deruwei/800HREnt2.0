package com.hr.ent.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.ResumeInfoHandler;
import com.hr.ent.model.BaseResumeInfoBean;
import com.hr.ent.model.CerificateAccessory;
import com.hr.ent.model.CertificateInfoBean;
import com.hr.ent.model.ResumeAccessInfo;
import com.hr.ent.model.ResumeBaseInfoBean;
import com.hr.ent.model.ResumeEduInfo;
import com.hr.ent.model.ResumeExpericeInfo;
import com.hr.ent.model.ResumeInfoDetalBean;
import com.hr.ent.model.ResumeInfoOuterBean;
import com.hr.ent.model.ResumeLauguageInfo;
import com.hr.ent.model.ResumeOrderInfoBean;
import com.hr.ent.model.ResumePlantInfo;
import com.hr.ent.model.ResumeProjectInfo;
import com.hr.ent.model.ResumeSkillInfo;
import com.hr.ent.model.ResumeTempleInfoBean;
import com.hr.ent.model.ResumeTitleInfoBean;
import com.hr.ent.model.SubjoinInfoBean;
import com.hr.ent.ui.DoIntentionCommunicationActivity;
import com.hr.ent.ui.ResumeInfoActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DataPickerDialog;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.UMengEvent;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.umeng.analytics.MobclickAgent;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 简历详情Fragment
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-7-2
 */
public class ResumeInfoFragment extends LazyFragment implements OnClickListener {
    private static final String TAG = "ResumeInfoFragment";
    private ScrollView resumeinfo_content;// 总布局
    private LinearLayout workExperienceLayout;// 工作经验布局
    private LinearLayout eduBackgroundLayout;// 教育背景布局
    private LinearLayout projectExperienceLayout;// 项目经验布局
    private LinearLayout selfEvaluationLayout;// 自我评价布局
    private LinearLayout languageLayout;// 语言能力布局
    private LinearLayout professionalSkillLayout;// 专业技能布局
    private LinearLayout trainingExperienceLayout;// 培训经历布局
    private LinearLayout resumeinfoaccessorylayout;// 附加、作品
    private LinearLayout cerificateLayout;// 证书布局
    private LinearLayout otherLayout;// 其他布局
    private Dialog noteDialog;// 备注

    // 头信息
    private LinearLayout headinfo_job;
    private TextView headInfoNote, applyJobName, lableApplyJobTime, applyJobTime;

    // 个人信息
    private Button btn_resumeinfo_note;
    private TextView personInfoName, personInfoSex, personInfoAge, personinfobloodtype, personinfoheight,
            personInfoDegree, personInfoCountry, personInfoNowplace, personInfoHukou, personInfoWorkyear,
            personInfoPostRank, personInfoPoliticsStatus, personInfoMarriageStatus, personInfoCellphone, personInfoEmailAddress, personInfoMessageAddress, personInfoHomePage,
            personInfoMessagetype/*, personInfoMessageNum*/;
    private ImageView personInfoPhoto, callPhone1/*, callPhone2*/;
    private TableRow cellphoneTr, emailTr, homepageTr, messagetypeTr, messagenumTr;
    private TextView personinfoTitle;

    // 求职意向
    private TextView jobIntentionWorktype, jobIntentionExpectPosition, jobIntentionIndustry, jobIntentionField,
            jobIntentionWorkPalce, jobIntentionExceptSalary, jobIntentionState;

    // 工作经验
    private TextView workExperienceTime, workExperienceCompany, workExperienceCompanyType, workExperienceCompanySize,
            workExperienceAddress, workExperiencePresalary, workExperiencePosition, workExperienceDuty,
            workExperienceResonLeave, workExperienceAbroad;

    // 教育背景
    private TextView eduBackgroundTime, eduBackgroundSchoolName, eduBackgroundDegree, eduBackgroundMajor,
            eduBackgroundMajorDes, eduBackgroundAbroad;

    // 项目经验
    private TextView projectExperienceTime, projectExperienceName, projectExperienceDesc, projectExperiencePosition,
            projectExperienceDuty;

    // 自我评价
    private TextView selfEvaluationDesc;

    // 语言能力
    private TextView languageType, languageReadWrite, languageListenSpeak;

    // 专业能力
    private TextView professionalSkillName, professionalSkillUserTime, professionalSkillLevel;

    // 培训经历
    private TextView trainingExperienceTime, trainingExperienceOrganization, trainingExperienceCourses,
            trainingExperienceCertificate, trainingExperienceAddress, trainingExperienceDesc;
    // 附件/作品
    private TextView cerificateaccessories, cerificatename;

    // 证书
    private TextView cerificateTime, cerificateName, cerificatedescribe;

    // 其他
    private TextView otherLabelName, otherName;

    private Activity activity;
    private boolean isPrepared;

    private App app;
    private String type;
    private String typeID;
    private String userid;
    private String userName;
    private String resume_id;
    private String resume_fdID;
    private String resume_language;

    private String[] cellNums = null;
    private String[] telNums = null;

    private ResumeInfoHandler handler;
    private BaseResumeInfoBean resumeInfoBean;
    private String remark = "";// 备注信息
    public static final String NOTE_ACTION = "NOTE";
    private ResumeInfoFragmentBrocast brocast;
    private LinearLayout lins;

    // 显示个人图像的
    private DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false).cacheOnDisk(true)
            .displayer(new FadeInBitmapDisplayer(200)).build();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    public static ResumeInfoFragment resumeInfoFragment = null;
    private TextView tvNoResume;

    @Override
    protected void lazyLoad() {
        // TODO Auto-generated method stub
        if (!isPrepared || !isVisible) {
            return;
        }
        initResumeInfo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        isPrepared = true;
        activity = getActivity();
        app = (App) activity.getApplication();
        handler = new ResumeInfoHandler(activity, this);
        resumeInfoFragment = ResumeInfoFragment.this;
    }

    /**
     * 关闭备注对话框
     */
    public void closeNoteDialog() {
        if (noteDialog != null && noteDialog.isShowing()) {
            noteDialog.dismiss();
        }
    }

    /**
     * 显示提交备注对话框
     */
    private void showNoteDialog() {
//        noteDialog = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.SimpleDialog)).create();
        noteDialog = new Dialog(getActivity(), R.style.SimpleDialog);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_note, new LinearLayout(getActivity()), false);
        noteDialog.setContentView(view);
        final EditText edit_note = (EditText) view.findViewById(R.id.note_content);
        if (remark != null && !"".equals(remark)) {
            edit_note.setText(remark);
        }
        view.findViewById(R.id.note_ok).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        String note = edit_note.getText().toString().trim();
                        noteDialog.dismiss();
                        setNote(note);
                    }
                });
        view.findViewById(R.id.note_no).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        noteDialog.dismiss();
                    }
                });
        noteDialog.setCancelable(true);
        noteDialog.setCanceledOnTouchOutside(true);
        noteDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
            }
        });
        noteDialog.show();
    }
    /**
     * 发送简历备注请求
     *
     * @param note
     */
    private void setNote(String note) {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUME_NOTE);
            params.put("personal_user_id", userid);
            params.put("resume_id", resume_id);
            params.put("resume_language", resume_language);
            params.put("remark", note);
            MobclickAgent.onEvent(getActivity(), UMengEvent.RESUME_NOTE);
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler.wSetResumeNoteStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_resumeinfo, container, false);
        resumeinfo_content = (ScrollView) view.findViewById(R.id.resumeinfo_content);
        workExperienceLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_workexperience_layout);
        eduBackgroundLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_edubackground_layout);
        projectExperienceLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_projectexperience_layout);
        selfEvaluationLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_selfevaluation_layout);
        languageLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_language_layout);
        professionalSkillLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_professionalskill_layout);
        trainingExperienceLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_trainingexperience_layout);
//		// 添加附件、作品
//		resumeinfoaccessorylayout = (LinearLayout) view.findViewById(R.id.resumeinfo_accessory_layout);
      /*  lins = (LinearLayout) view.findViewById(R.id.lins);*/
        cerificateLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_cerificate_layout);
        tvNoResume= (TextView) view.findViewById(R.id.tv_noResume);
        otherLayout = (LinearLayout) view.findViewById(R.id.resumeinfo_other_layout);
        // 初始化头布局==3
        headinfo_job = (LinearLayout) view.findViewById(R.id.headinfo_job);
        headInfoNote = (TextView) view.findViewById(R.id.headinfo_note);
        applyJobName = (TextView) view.findViewById(R.id.apply_job_name);
        lableApplyJobTime = (TextView) view.findViewById(R.id.lable_apply_job_time);
        applyJobTime = (TextView) view.findViewById(R.id.apply_job_time);
        // 初始化个人信息==18
        personInfoName = (TextView) view.findViewById(R.id.personinfo_name);
        personInfoSex = (TextView) view.findViewById(R.id.personinfo_sex);
        personInfoAge = (TextView) view.findViewById(R.id.personinfo_age);
        btn_resumeinfo_note = (Button) view.findViewById(R.id.btn_resumeinfo_note);
        btn_resumeinfo_note.setOnClickListener(this);
        // 添加初始化血型
        personinfobloodtype = (TextView) view.findViewById(R.id.personinfo_bloodtype);
        personInfoDegree = (TextView) view.findViewById(R.id.personinfo_degree);
        // 添加初始化身高
        personinfoheight = (TextView) view.findViewById(R.id.personinfo_height);
        personInfoCountry = (TextView) view.findViewById(R.id.personinfo_country);
        personInfoNowplace = (TextView) view.findViewById(R.id.personinfo_nowplace);
        personInfoHukou = (TextView) view.findViewById(R.id.personinfo_hukou);
        personInfoWorkyear = (TextView) view.findViewById(R.id.personinfo_workyear);
        personInfoPostRank = (TextView) view.findViewById(R.id.personinfo_post_rank);
        personInfoPoliticsStatus = (TextView) view.findViewById(R.id.personinfo_politics_status);
        personInfoMarriageStatus = (TextView) view.findViewById(R.id.personinfo_marriage_status);
        personInfoCellphone = (TextView) view.findViewById(R.id.personinfo_cellphone_number);
       /* personInfoTelphone = (TextView) view.findViewById(R.id.personinfo_telphone);*/
        personInfoEmailAddress = (TextView) view.findViewById(R.id.personinfo_emailaddress);
        personInfoMessageAddress = (TextView) view.findViewById(R.id.personinfo_message_address);
        personInfoHomePage = (TextView) view.findViewById(R.id.personinfo_homepage);
        personInfoMessagetype = (TextView) view.findViewById(R.id.personinfo_messagetype);
       /* personInfoMessageNum = (TextView) view.findViewById(R.id.personinfo_message_num);*/
        personInfoPhoto = (ImageView) view.findViewById(R.id.personinfo_photo);
        callPhone1 = (ImageView) view.findViewById(R.id.call_phone_1);
        /*callPhone2 = (ImageView) view.findViewById(R.id.call_phone_2);*/

        cellphoneTr = (TableRow) view.findViewById(R.id.cellphone_tr);
        /*telphoneTr = (TableRow) view.findViewById(R.id.telphone_tr);*/
        emailTr = (TableRow) view.findViewById(R.id.email_tr);
        homepageTr = (TableRow) view.findViewById(R.id.homepage_tr);
        messagetypeTr = (TableRow) view.findViewById(R.id.messagetype_tr);
      /*  messagenumTr = (TableRow) view.findViewById(R.id.messagenum_tr);*/
        personinfoTitle = (TextView) view.findViewById(R.id.personinfo_title);

        // 初始化求职意向==7
        jobIntentionWorktype = (TextView) view.findViewById(R.id.jobintention_worktype);
        jobIntentionExpectPosition = (TextView) view.findViewById(R.id.jobintention_expectposition);
        jobIntentionIndustry = (TextView) view.findViewById(R.id.jobintention_industry);
        jobIntentionField = (TextView) view.findViewById(R.id.jobintention_field);
        jobIntentionWorkPalce = (TextView) view.findViewById(R.id.jobintention_workplace);
        jobIntentionExceptSalary = (TextView) view.findViewById(R.id.jobintention_expectsalary);
        jobIntentionState = (TextView) view.findViewById(R.id.jobintention_state);

        // 初始化工作经验==10
        workExperienceTime = (TextView) view.findViewById(R.id.workexperience_time);
        workExperienceCompany = (TextView) view.findViewById(R.id.workexperience_company);
        workExperienceCompanyType = (TextView) view.findViewById(R.id.workexperience_companytype);
        workExperienceCompanySize = (TextView) view.findViewById(R.id.workexperience_companysize);
        workExperienceAddress = (TextView) view.findViewById(R.id.workexperience_address);
        workExperiencePresalary = (TextView) view.findViewById(R.id.workexperience_presalary);
        workExperiencePosition = (TextView) view.findViewById(R.id.workexperience_position);
        workExperienceDuty = (TextView) view.findViewById(R.id.workexperience_duty);
        workExperienceResonLeave = (TextView) view.findViewById(R.id.workexperience_resonleave);
        workExperienceAbroad = (TextView) view.findViewById(R.id.workexperience_abroad);

        // 初始化教育背景==6
        eduBackgroundTime = (TextView) view.findViewById(R.id.edubackground_time);
        eduBackgroundSchoolName = (TextView) view.findViewById(R.id.edubackground_schoolname);
        eduBackgroundDegree = (TextView) view.findViewById(R.id.edubackground_degree);
        eduBackgroundMajor = (TextView) view.findViewById(R.id.edubackground_major);
        // 专业描述
        eduBackgroundMajorDes = (TextView) view.findViewById(R.id.edubackground_major_des);
        eduBackgroundAbroad = (TextView) view.findViewById(R.id.edubackground_abroad);

        // 初始化项目经验==5
        projectExperienceTime = (TextView) view.findViewById(R.id.projectexperience_time);
        projectExperienceName = (TextView) view.findViewById(R.id.projectexperience_name);
        projectExperienceDesc = (TextView) view.findViewById(R.id.projectexperience_desc);
        projectExperiencePosition = (TextView) view.findViewById(R.id.projectexperience_position);
        projectExperienceDuty = (TextView) view.findViewById(R.id.projectexperience_duty);

        // 初始化自我评价==1
        selfEvaluationDesc = (TextView) view.findViewById(R.id.selfevaluation_desc);

        // 初始化语言==3
        languageType = (TextView) view.findViewById(R.id.language_type);
        languageReadWrite = (TextView) view.findViewById(R.id.language_readwrite);
        languageListenSpeak = (TextView) view.findViewById(R.id.language_listenspeak);

        // 初始化专业技能==3
        professionalSkillName = (TextView) view.findViewById(R.id.professionalskill_name);
        professionalSkillUserTime = (TextView) view.findViewById(R.id.professionalskill_usertime);
        professionalSkillLevel = (TextView) view.findViewById(R.id.professionalskill_level);

        // 初始化培训经历==6
        trainingExperienceTime = (TextView) view.findViewById(R.id.trainingexperience_time);
        trainingExperienceOrganization = (TextView) view.findViewById(R.id.trainingexperience_organization);
        trainingExperienceCourses = (TextView) view.findViewById(R.id.trainingexperience_courses);
        trainingExperienceCertificate = (TextView) view.findViewById(R.id.trainingexperience_certificate);
        trainingExperienceAddress = (TextView) view.findViewById(R.id.trainingexperience_address);
        trainingExperienceDesc = (TextView) view.findViewById(R.id.trainingexperience_desc);

        // 初始化附件、作品
        cerificateaccessories = (TextView) view.findViewById(R.id.cerificate_accessories);
        cerificatename = (TextView) view.findViewById(R.id.cerificate_name2);

        // 初始化证书==2
        cerificateTime = (TextView) view.findViewById(R.id.cerificate_time);
        cerificateName = (TextView) view.findViewById(R.id.cerificate_name);
        // 添加证书描述
        cerificatedescribe = (TextView) view.findViewById(R.id.cerificate_describe);

        // 初始化其他==1
        otherLabelName = (TextView) view.findViewById(R.id.other_lable_name);
        otherName = (TextView) view.findViewById(R.id.other_name);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // 注册广播，接收修改备注信息
        brocast = new ResumeInfoFragmentBrocast();
        IntentFilter filter = new IntentFilter(NOTE_ACTION);
        activity.registerReceiver(brocast, filter);

        lazyLoad();
    }
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        resume_id = null;
        cellNums = null;
        telNums = null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        activity.unregisterReceiver(brocast);
    }

    private void initResumeInfo() {
        if (resume_id == null) {
            Bundle bundle = getArguments();
            if (bundle.containsKey("infoBean")) {
                BaseResumeInfoBean bean = bundle.getParcelable("infoBean");
                type = bundle.getString("type");
                typeID = bundle.getString("typeID");
                initResumeInfo(bean);
            }
        }
    }

    public void initResumeInfo(BaseResumeInfoBean infoBean) {
        this.resumeInfoBean = infoBean;

        userName = resumeInfoBean.getUserName();
        userid = resumeInfoBean.getUserID();
        resume_id = resumeInfoBean.getResumeID();
        resume_fdID = resumeInfoBean.getResumeDeleteID();
        resume_language = resumeInfoBean.getResumeLanguage();
        LogUtil.i(TAG, "userid=" + userid);
        LogUtil.i(TAG, "resume_id=" + resume_id);
        LogUtil.i(TAG, "resume_fdID=" + resume_fdID);

        ((ResumeInfoActivity) activity).setType(type);
        ((ResumeInfoActivity) activity).setTypeID(typeID);
        ((ResumeInfoActivity) activity).setUserName(userName);
        ((ResumeInfoActivity) activity).setUserid(userid);
        ((ResumeInfoActivity) activity).setResume_id(resume_id);
        ((ResumeInfoActivity) activity).setResume_fdID(resume_fdID);
        ((ResumeInfoActivity) activity).setResume_language(resume_language);

        // 获取简历详情
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUMEPREVIEW);
            if (type.equals(Const.DOWNLOAD)) {
                params.put("type", 4);
                params.put("id", resume_fdID);
                params.put("user_id", userid);
                params.put("resume_language", resume_language);
                params.put("industry", app.getUser().getSite_code());
                params.put("resume_id", "");
            } else {
                params.put("type", "1");
                params.put("user_id", userid);
                params.put("resume_id", resume_id);
                params.put("resume_language", resume_language);
                params.put("industry", app.getUser().getSite_code());
                params.put("id", resume_fdID);
            }
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler.wGetResumeInfoStart);
        } else {
            initEmptyView();
            Toast.makeText(getActivity(), getResources().getString(R.string.nonet), Toast.LENGTH_SHORT).show();
        }

    }

    public void initEmptyView() {
        resumeinfo_content.setVisibility(View.GONE);
        tvNoResume.setVisibility(View.VISIBLE);
        ((ResumeInfoActivity) activity).HidGson();
    }

    public void setContentVisiable() {
        resumeinfo_content.setVisibility(View.VISIBLE);
        tvNoResume.setVisibility(View.GONE);
        ((ResumeInfoActivity) activity).showGson();
    }

    /**
     * 更新UI信息
     *
     * @param detalBean
     */
    public void refshUI(ResumeInfoDetalBean detalBean, ResumeInfoOuterBean resumeInfoOuterBean) {
        // 设置简历为已读状态
        if (resumeInfoBean.getIsNew() != null && !resumeInfoBean.getIsNew().equals("0")) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUME_READ);
            params.put("id", resume_fdID);
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler.wSetResumeReadStart);
        }
        remark = detalBean.getRemark();
        ((ResumeInfoActivity) activity).setRemark(remark);
        initHeadInfo(detalBean.getTitle_info());
        initPersonInfo(detalBean.getBase_info());
        initJobIntention(detalBean.getOrder_info());
        initWorkExperience(detalBean.getExperience_list());
        initEduBackground(detalBean.getEducation_list());
        initProjectExperience(detalBean.getProject_list());
        initSelfEvaluation(detalBean.getAssess_info());
        initLanguage(detalBean.getLanguage_list());
        initProfessionalSkill(detalBean.getSkill_list());
        initTrainingExperience(detalBean.getPlant_list());
        initCerificate(detalBean.getCertificate_list());
        initOther(detalBean.getSubjoin_list());
        ResumeInfoActivity.resumeInfoActivity.refreshApply(resumeInfoOuterBean.getOpt_info().getId(), resumeInfoOuterBean.getOpt_info().getType());
    }

    /**
     * 初始化头部信息
     */
    private void initHeadInfo(ResumeTitleInfoBean infoBean) {
        updataNote();
        if (type.equals(Const.DOWNLOAD)) {// 下载简历
            headinfo_job.setVisibility(View.GONE);
            lableApplyJobTime.setText("下载时间：");
        } else if (type.equals(Const.BOX) && typeID.equals("4")) {// 收藏简历
            headinfo_job.setVisibility(View.GONE);
            lableApplyJobTime.setText("收藏时间：");
        } else {
            applyJobName.setText(resumeInfoBean.getJobName());
        }
        applyJobTime.setText(DateUtils.transforMillToDate(Long.parseLong(resumeInfoBean.getResumeDate()) * 1000));
    }

    /**
     * 更新备注信息
     */
    private void updataNote() {
        if (remark != null && !remark.equals("")) {
            headInfoNote.setVisibility(View.VISIBLE);
//            headInfoNote.setText("备注：" + remark);
            headInfoNote.setText(remark);
        } else {
            headInfoNote.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化个人信息
     */
    private void initPersonInfo(ResumeBaseInfoBean infoBean) {
        if (infoBean != null) {
            if (resume_id != null && resume_id.equals("6")) {
                personinfoTitle.setText("个人信息（高端）");
            } else {
                personinfoTitle.setText("个人信息");
            }
            Calendar calendar = Calendar.getInstance();
            personInfoName.setText(infoBean.getName());
            ResumeInfoActivity.resumeInfoActivity.setUserName(infoBean.getName());
            personInfoSex.setText(infoBean.getSex());
            personInfoAge.setText((calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getYear())) + "岁("
                    + infoBean.getYear() + "年" + infoBean.getMonth() + "月" + infoBean.getDay() + "日)");
            // 初始化血型
            personinfobloodtype.setText(infoBean.getBlood());
            personInfoDegree.setText(infoBean.getHigh_education());
            // 初始化身高
            personinfoheight.setText(infoBean.getHeight());
            personInfoCountry.setText(infoBean.getNationality());
            personInfoNowplace.setText(infoBean.getLocation());
            personInfoHukou.setText(infoBean.getHukou());
            if (infoBean.getWork_beginyear().equals("-1")) {
                personInfoWorkyear.setText("无工作经验");
            } else if (calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getWork_beginyear()) == 0) {
                personInfoWorkyear.setText("1年以下工作经验");
            } else {
                personInfoWorkyear.setText((calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getWork_beginyear())) + "年工作经验");
            }

            personInfoPostRank.setText(infoBean.getPost_rank());
            personInfoPoliticsStatus.setText(infoBean.getPolity());
            personInfoMarriageStatus.setText(infoBean.getMarriage());

            cellphoneTr.setVisibility(View.VISIBLE);
            /*telphoneTr.setVisibility(View.VISIBLE);*/
            emailTr.setVisibility(View.VISIBLE);
            homepageTr.setVisibility(View.VISIBLE);
            messagetypeTr.setVisibility(View.VISIBLE);
           /* messagenumTr.setVisibility(View.VISIBLE);*/

            // 移动电话
            if (infoBean.getYdphone().contains(",")) {
                cellNums = infoBean.getYdphone().split(",");
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < cellNums.length; i++) {
                    buffer.append(cellNums[i]);
                    if (i != cellNums.length - 1) {
                        buffer.append("\n");
                    }
                }
                personInfoCellphone.setText(buffer.toString());
                ResumeInfoActivity.callNum = personInfoCellphone.getText().toString().trim();
            } else {
                personInfoCellphone.setText(infoBean.getYdphone());
                ResumeInfoActivity.callNum = personInfoCellphone.getText().toString().trim();
            }
            // 固话
            if (infoBean.getTelephone().contains(",")) {
                telNums = infoBean.getTelephone().split(",");
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < telNums.length; i++) {
                    buffer.append(telNums[i]);
                    if (i != telNums.length - 1) {
                        buffer.append("\n");
                    }
                }
               /* personInfoTelphone.setText(buffer.toString());
                ResumeInfoActivity.telNum = personInfoTelphone.getText().toString().trim();*/
            } else {
                /*personInfoTelphone.setText(infoBean.getTelephone());
                ResumeInfoActivity.telNum = personInfoTelphone.getText().toString().trim();*/
            }
            if (!infoBean.getYdphone().equals("") || !infoBean.getTelephone().equals("")) {
                if (!infoBean.getYdphone().equals("")) {
                    callPhone1.setVisibility(View.VISIBLE);
                    callPhone1.setOnClickListener(this);
                } else {
                    callPhone1.setVisibility(View.GONE);
                }
                if (!infoBean.getTelephone().equals("")) {
                   /* callPhone2.setVisibility(View.VISIBLE);
                    callPhone2.setOnClickListener(this);*/
                } else {
                    /*callPhone2.setVisibility(View.GONE);*/
                }
                ResumeInfoActivity.isPhone = true;
                ResumeInfoActivity.resumeInfoActivity.refushUI();
            } else {
                ResumeInfoActivity.isPhone = false;
                ResumeInfoActivity.resumeInfoActivity.refushUI();
            }

            personInfoEmailAddress.setText(infoBean.getEmailaddress());
            personInfoHomePage.setText(infoBean.getHomepage());
            personInfoMessagetype.setText(infoBean.getIm_type()+"-"+infoBean.getIm_account());
            /*personInfoMessageNum.setText(infoBean.getIm_account());*/
//            }
            personInfoMessageAddress.setText(infoBean.getAddress());

            // 设置图片
            if (!infoBean.getPic_filekey().equals("")) {
                imageLoader.displayImage(Const.IMAGE_ROOTPATH + infoBean.getPic_filekey(), personInfoPhoto, options,
                        animateFirstListener);
            } else {
                if (infoBean.getSex().equals("男")) {
                    personInfoPhoto.setImageResource(R.drawable.photo_man);
                } else {
                    personInfoPhoto.setImageResource(R.drawable.photo_women);
                }
            }
        }
    }

    /**
     * 初始化求职意向
     */
    private void initJobIntention(ResumeOrderInfoBean infoBean) {
        if (infoBean != null) {
            jobIntentionWorktype.setText(infoBean.getJobtype());
            jobIntentionExpectPosition.setText(infoBean.getFunc());
            jobIntentionIndustry.setText(infoBean.getIndustry());
            jobIntentionField.setText(infoBean.getLingyu());
            jobIntentionWorkPalce.setText(infoBean.getWorkarea());
            jobIntentionExceptSalary.setText(infoBean.getOrder_salary());
            jobIntentionState.setText(infoBean.getCurrent_workstate());
        }
    }

    /**
     * 初始化工作经验
     */
    private void initWorkExperience(List<ResumeExpericeInfo> list) {
        if (list.size() == 0) {
            workExperienceLayout.setVisibility(View.GONE);
        } else {
            workExperienceLayout.setVisibility(View.VISIBLE);
            if (workExperienceLayout.getChildCount() > 0) {
                workExperienceLayout.removeAllViews();
            }

            for (ResumeExpericeInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_workexperience,
                        new LinearLayout(activity), false);

                workExperienceTime = (TextView) view.findViewById(R.id.workexperience_time);
                workExperienceCompany = (TextView) view.findViewById(R.id.workexperience_company);
                workExperienceCompanyType = (TextView) view.findViewById(R.id.workexperience_companytype);
                workExperienceCompanySize = (TextView) view.findViewById(R.id.workexperience_companysize);
                workExperienceAddress = (TextView) view.findViewById(R.id.workexperience_address);
                workExperiencePresalary = (TextView) view.findViewById(R.id.workexperience_presalary);
                workExperiencePosition = (TextView) view.findViewById(R.id.workexperience_position);
                workExperienceDuty = (TextView) view.findViewById(R.id.workexperience_duty);
                workExperienceResonLeave = (TextView) view.findViewById(R.id.workexperience_resonleave);
                workExperienceAbroad = (TextView) view.findViewById(R.id.workexperience_abroad);

                if (info.getToyear().equals("0")) {
                    workExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--至今");
                } else {
                    workExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--" + info.getToyear() + "年" + info.getTomonth() + "月");
                }
                workExperienceCompany.setText(info.getCompany());
                workExperienceCompanyType.setText(info.getCompanytype());
                workExperienceCompanySize.setText(info.getStuffmunber());
                workExperienceAddress.setText(info.getCompanyaddress());
                workExperiencePresalary.setText(info.getSalary());
                workExperiencePosition.setText(info.getPosition());
                workExperienceDuty.setText(info.getResponsiblity());
                workExperienceResonLeave.setText(info.getOffreason());
                workExperienceAbroad.setText(info.getCountry());

                workExperienceLayout.addView(view);
            }
        }
    }

    /**
     * 初始化教育背景
     */
    private void initEduBackground(List<ResumeEduInfo> list) {
        if (list.size() == 0) {
            eduBackgroundLayout.setVisibility(View.GONE);
        } else {
            eduBackgroundLayout.setVisibility(View.VISIBLE);
            if (eduBackgroundLayout.getChildCount() > 0) {
                eduBackgroundLayout.removeAllViews();
            }

            for (ResumeEduInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_edubackground,
                        new LinearLayout(activity), false);

                eduBackgroundTime = (TextView) view.findViewById(R.id.edubackground_time);
                eduBackgroundSchoolName = (TextView) view.findViewById(R.id.edubackground_schoolname);
                eduBackgroundDegree = (TextView) view.findViewById(R.id.edubackground_degree);
                eduBackgroundMajor = (TextView) view.findViewById(R.id.edubackground_major);
                // 专业描述
                eduBackgroundMajorDes = (TextView) view.findViewById(R.id.edubackground_major_des);
                eduBackgroundAbroad = (TextView) view.findViewById(R.id.edubackground_abroad);

                if (info.getToyear().equals("0")) {
                    eduBackgroundTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--至今");
                } else {
                    eduBackgroundTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--" + info.getToyear()
                            + "年" + info.getTomonth() + "月");
                }
                eduBackgroundSchoolName.setText(info.getSchoolname());
                eduBackgroundDegree.setText(info.getDegree());
                eduBackgroundMajor.setText(info.getMoremajor());
                eduBackgroundMajorDes.setText(info.getEdudetail());
                eduBackgroundAbroad.setText(info.getCountry());
                eduBackgroundLayout.addView(view);
            }
        }
    }

    /**
     * 初始化项目经验
     */
    private void initProjectExperience(List<ResumeProjectInfo> list) {
        if (list.size() == 0) {
            projectExperienceLayout.setVisibility(View.GONE);
        } else {
            projectExperienceLayout.setVisibility(View.VISIBLE);
            if (projectExperienceLayout.getChildCount() > 0) {
                projectExperienceLayout.removeAllViews();
            }

            for (ResumeProjectInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_projectexperience,
                        new LinearLayout(activity), false);

                projectExperienceTime = (TextView) view.findViewById(R.id.projectexperience_time);
                projectExperienceName = (TextView) view.findViewById(R.id.projectexperience_name);
                projectExperienceDesc = (TextView) view.findViewById(R.id.projectexperience_desc);
                projectExperiencePosition = (TextView) view.findViewById(R.id.projectexperience_position);
                projectExperienceDuty = (TextView) view.findViewById(R.id.projectexperience_duty);

                if (info.getToyear().equals("0")) {
                    projectExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--至今");
                } else {
                    projectExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--"
                            + info.getToyear() + "年" + info.getTomonth() + "月");
                }
                projectExperienceName.setText(info.getProjectname());
                projectExperienceDesc.setText(info.getProjectdesc());
                projectExperiencePosition.setText(info.getPosition());
                projectExperienceDuty.setText(info.getResponsibility());

                projectExperienceLayout.addView(view);
            }
        }

    }

    /**
     * 初始化自我评价
     */
    private void initSelfEvaluation(ResumeAccessInfo accessInfo) {
        if (accessInfo != null && !accessInfo.getIntroduction().equals("")) {
            selfEvaluationLayout.setVisibility(View.VISIBLE);
            selfEvaluationDesc.setText(accessInfo.getIntroduction());
        } else {
            selfEvaluationLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化语言能力
     */
    private void initLanguage(List<ResumeLauguageInfo> list) {
        if (list.size() == 0) {
            languageLayout.setVisibility(View.GONE);
        } else {
            languageLayout.setVisibility(View.VISIBLE);
            if (languageLayout.getChildCount() > 0) {
                languageLayout.removeAllViews();
            }

            for (ResumeLauguageInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_language,
                        new LinearLayout(activity), false);

                languageType = (TextView) view.findViewById(R.id.language_type);
                languageReadWrite = (TextView) view.findViewById(R.id.language_readwrite);
                languageListenSpeak = (TextView) view.findViewById(R.id.language_listenspeak);

                languageType.setText(info.getLangname());
                languageReadWrite.setText(info.getRead_level());
                languageListenSpeak.setText(info.getSpeak_level());

                languageLayout.addView(view);
            }
        }
    }

    /**
     * 初始化专业技能
     */
    private void initProfessionalSkill(List<ResumeSkillInfo> list) {
        if (list.size() == 0) {
            professionalSkillLayout.setVisibility(View.GONE);
        } else {
            professionalSkillLayout.setVisibility(View.VISIBLE);
            if (professionalSkillLayout.getChildCount() > 0) {
                professionalSkillLayout.removeAllViews();
            }

            for (ResumeSkillInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_professionalskill,
                        new LinearLayout(activity), false);

                professionalSkillName = (TextView) view.findViewById(R.id.professionalskill_name);
                professionalSkillUserTime = (TextView) view.findViewById(R.id.professionalskill_usertime);
                professionalSkillLevel = (TextView) view.findViewById(R.id.professionalskill_level);

                professionalSkillName.setText(info.getSkilltitle());
                professionalSkillUserTime.setText(info.getUsetime());
                professionalSkillLevel.setText(info.getAbility());

                professionalSkillLayout.addView(view);
            }
        }
    }

    /**
     * 初始化培训经历
     */
    private void initTrainingExperience(List<ResumePlantInfo> list) {
        if (list.size() == 0) {
            trainingExperienceLayout.setVisibility(View.GONE);
        } else {
            trainingExperienceLayout.setVisibility(View.VISIBLE);
            if (trainingExperienceLayout.getChildCount() > 0) {
                trainingExperienceLayout.removeAllViews();
            }

            for (ResumePlantInfo info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_trainingexperience,
                        new LinearLayout(activity), false);

                trainingExperienceTime = (TextView) view.findViewById(R.id.trainingexperience_time);
                trainingExperienceOrganization = (TextView) view.findViewById(R.id.trainingexperience_organization);
                trainingExperienceCourses = (TextView) view.findViewById(R.id.trainingexperience_courses);
                trainingExperienceCertificate = (TextView) view.findViewById(R.id.trainingexperience_certificate);
                trainingExperienceAddress = (TextView) view.findViewById(R.id.trainingexperience_address);
                trainingExperienceDesc = (TextView) view.findViewById(R.id.trainingexperience_desc);

                if (info.getToyear().equals("0")) {
                    trainingExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--至今");
                } else {
                    trainingExperienceTime.setText(info.getFromyear() + "年" + info.getFrommonth() + "月--"
                            + info.getToyear() + "年" + info.getTomonth() + "月");
                }
                trainingExperienceOrganization.setText(info.getInstitution());
                trainingExperienceCourses.setText(info.getCourse());
                trainingExperienceCertificate.setText(info.getCertification());
                trainingExperienceAddress.setText(info.getPlace());
                trainingExperienceDesc.setText(info.getTraindetail());

                trainingExperienceLayout.addView(view);
            }
        }
    }

    /**
     * 初始化附件、作品
     */

    @SuppressWarnings("unused")
    private void initCerificate_accessory(List<CerificateAccessory> list) {
        if (list.size() == 0) {
            resumeinfoaccessorylayout.setVisibility(View.GONE);
        } else {
            resumeinfoaccessorylayout.setVisibility(View.VISIBLE);
            if (resumeinfoaccessorylayout.getChildCount() > 0) {
                resumeinfoaccessorylayout.removeAllViews();
            }
            for (CerificateAccessory info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_accessory, new LinearLayout(activity), false);
                cerificateaccessories = (TextView) view.findViewById(R.id.cerificate_accessories);
                cerificatename = (TextView) view.findViewById(R.id.cerificate_name2);
                cerificateaccessories.setText(info.getAttachmentdesc());
                cerificatename.setText(info.getOther_Accessory());
                resumeinfoaccessorylayout.addView(view);
            }
        }
    }

    /**
     * 初始化证书
     *
     * @param list
     */
    private void initCerificate(List<CertificateInfoBean> list) {
        if (list.size() == 0) {
            cerificateLayout.setVisibility(View.GONE);
        } else {
            cerificateLayout.setVisibility(View.VISIBLE);
            if (cerificateLayout.getChildCount() > 0) {
                cerificateLayout.removeAllViews();
            }
            for (CertificateInfoBean info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_cerificate, new LinearLayout(activity), false);
                cerificateTime = (TextView) view.findViewById(R.id.cerificate_time);
                cerificateName = (TextView) view.findViewById(R.id.cerificate_name);
                cerificatedescribe = (TextView) view.findViewById(R.id.cerificate_describe);

                // 证书获取年份
                cerificateTime.setText(info.getGetyear() + "年" + info.getGetmonth() + "月");
                // 证书名字
                cerificateName.setText(info.getCertname());
                // 证书描述
                cerificatedescribe.setText(info.getCert_filekey());

                cerificateLayout.addView(view);
            }
        }
    }

    /**
     * 初始化其他
     *
     * @param list
     */
    private void initOther(List<SubjoinInfoBean> list) {
        if (list.size() == 0) {
            otherLayout.setVisibility(View.GONE);
        } else {
            otherLayout.setVisibility(View.VISIBLE);
            if (otherLayout.getChildCount() > 0) {
                otherLayout.removeAllViews();
            }

            for (SubjoinInfoBean info : list) {
                View view = LayoutInflater.from(activity).inflate(R.layout.resumeinfo_other, new LinearLayout(activity), false);

                otherLabelName = (TextView) view.findViewById(R.id.other_lable_name);
                otherName = (TextView) view.findViewById(R.id.other_name);

                otherLabelName.setText(info.getTopic());
                otherName.setText(info.getMiscinfo());

                otherLayout.addView(view);
            }
        }
    }

    public class ResumeInfoFragmentBrocast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(NOTE_ACTION)) {
                remark = intent.getStringExtra("remark");
                updataNote();
            }
        }
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.call_phone_1:
                if (cellNums != null) {
                    showNumDialog(cellNums);
                } else {
                    callPhone(personInfoCellphone.getText().toString().trim());
                }
                break;
            case R.id.call_phone_2:
                if (telNums != null) {
                    showNumDialog(telNums);
                } else {
                    /*callPhone(personInfoTelphone.getText().toString().trim());*/
                }
                break;
            case R.id.btn_resumeinfo_note:
                showNoteDialog();
                break;
        }

    }

    /**
     * 打电话
     *
     * @param nums
     */
    private void callPhone(String nums) {
        try {
            Uri uri = Uri.parse("tel:" + nums);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.e(TAG, "callPhone fail");
        }
    }
    /**
     * 通知之前需要先获取简历模版，然后打开通知对话框
     */
    private void getInviteTag() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUME_INVITE);
            MsgHandler.sendMessage(params, handler,
                    ResumeInfoHandler.wGetInviteTagStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 显示 电话的对话框
     *
     * @param nums
     */
    private void showNumDialog(final String[] nums) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tell, new LinearLayout(getActivity()), false);
        ListView listView = (ListView) view.findViewById(R.id.tell_list);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, nums));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                callPhone(nums[position]);
            }
        });
        dialog.setView(view, 0, 0, 0, 0);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        setDialogSize(dialog, (float) 0.8);
    }

    /**
     * 设置对话框的大小
     *
     * @param dialog
     */
    public void setDialogSize(AlertDialog dialog, float size) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (metrics.widthPixels * size);
        params.height = LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }
}
