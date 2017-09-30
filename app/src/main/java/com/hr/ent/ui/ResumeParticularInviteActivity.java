package com.hr.ent.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.InviteTempleAdapter;
import com.hr.ent.adapter.InviteTimeAdapter;
import com.hr.ent.app.App;
import com.hr.ent.db.RecentlyEmailDao;
import com.hr.ent.fragment.ResumeInfoFragment;
import com.hr.ent.fragment.ResumeInfoParticularFragment;
import com.hr.ent.handler.ResumeHandler;
import com.hr.ent.handler.ResumeInfoHandler2;
import com.hr.ent.handler.ResumeOperationHandler;
import com.hr.ent.model.RecentlyEmailBean;
import com.hr.ent.model.ResumeTempleInfoBean;
import com.hr.ent.utils.DataPickerDialog;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.StringUtils;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人简历信息界面
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-24
 */
public class ResumeParticularInviteActivity extends BaseActivity implements
        OnClickListener, OnItemClickListener {
    private TextView title;
    private String typeID;
    private App app;
    private ResumeInfoHandler2 handler;
    private ResumeTempleInfoBean templeInfoBean;

    private Dialog inviteDialog;// 通知
    private Dialog noteDialog;// 备注
    private Dialog forwordDialog;// 转发
    private Dialog callphoneDialog;// 转发

    // 通知对话框
    private TextView send_email;
    private TextView send_msg;
    private TextView moban_title;
    private TextView reserve_date, tv_resumeinfo_invite, tv_resumeinfo_talk;
    private TextView reserve_hour;
    private TextView reserve_minute, tv_setting, tv_resumeinfo_forward;
    private LinearLayout email_title_layout, lins;
    private EditText email_title;
    private TextView email_label_content;
    private EditText email_content;
    private LinearLayout email_address_layout;
    private EditText reply_email;
    private TextView ok_text, tv_resumeinfo_callphone;
    private ImageView iv_resumeinfo_callphone, iv_resumeinfo_invite, iv_resumeinfo_forward;


    // 通知对话框上的弹出框
    private PopupWindow popWindow;
    private View popView;
    private ListView mListView;
    private int popWidth = 100;

    private int sendType = 1;// 发送类型 1、邮件 2、短信
    private String userName;
    private String userid;
    private String resume_id;
    //    private String resume_fdID;
    private String resume_language;
    private String id;
    private String ids;
    private String type;
    private String type2;
    private String resume_from = "";
    private String remark = "";
    private String intention_state = "";
    private String apply_state = "";
    private String job_name = "";
    private String job_id = "";
    private String r_id = "";
    private ResumeInfoParticularFragment resumeInfoFragment;
    private RecentlyEmailDao emailDao;
    private HashMap<String, String> emailSelectMap = new HashMap<String, String>();
    private ImageView iv_resumeinfo_talk;
    public static ResumeParticularInviteActivity resumeParticularInviteActivity = null;

    //    private Dialog tipDialog = null;
    public static boolean isPhone;

    public void refushUI() {
        if (!resume_from.equals("2")) {
            if (isPhone) {
                tv_resumeinfo_callphone.setText("拨打");
                iv_resumeinfo_callphone.setImageResource(R.mipmap.dianhua2);
                iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing1);
                tv_resumeinfo_invite.setTextColor(Color.parseColor("#333333"));
                tv_resumeinfo_callphone.setTextColor(Color.parseColor("#333333"));

            } else {

                tv_resumeinfo_callphone.setText("下载");
                iv_resumeinfo_callphone.setImageResource(R.mipmap.xiazai);
                iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing);
                tv_resumeinfo_callphone.setTextColor(Color.parseColor("#333333"));
                tv_resumeinfo_invite.setTextColor(Color.parseColor("#CCCCCC"));
            }
        } else {
            if (isPhone) {
                tv_resumeinfo_callphone.setText("下载");
                iv_resumeinfo_callphone.setImageResource(R.mipmap.xiazai2);
                iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing);
                tv_resumeinfo_invite.setTextColor(Color.parseColor("#CCCCCC"));
                tv_resumeinfo_callphone.setTextColor(Color.parseColor("#CCCCCC"));
            }
        }

    }

    public void refreshApply(String ids2, String type2, String apply_state2, String resume_id2) {
        this.ids = ids2;
        this.type2 = type2;
        this.apply_state = apply_state2;
        this.resume_id = resume_id2;
//        Toast.makeText(context, ids + " " + type2, Toast.LENGTH_SHORT).show();
        if (resume_id.equals("6")) {
            iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong2);
            tv_resumeinfo_talk.setTextColor(Color.parseColor("#CCCCCC"));
        } else {
            if (apply_state.equals("4") || apply_state.equals("5")) {
                iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong2);
                tv_resumeinfo_talk.setTextColor(Color.parseColor("#CCCCCC"));
            } else {
                iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong);
                tv_resumeinfo_talk.setTextColor(Color.parseColor("#333333"));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resumeparticular_invite);
        resumeParticularInviteActivity = ResumeParticularInviteActivity.this;
//        tipDialog = DialogUtil.createLoadingDialog(this, "请稍候...");
//        tipDialog.show();
        handler = new ResumeInfoHandler2(this, ResumeParticularInviteActivity.this);
        app = (App) getApplication();
        app.setLoadingResumeInfo(true);
        init();
        initFragment();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        resumeInfoFragment = new ResumeInfoParticularFragment(userid, resume_id, resume_language, userName, id, type);
        transaction.add(R.id.info_framelayout, resumeInfoFragment, ResumeInfoParticularFragment.class.getName());
        transaction.commit();
    }

    public void init() {
        title = (TextView) findViewById(R.id.title);
        title.setText("简历详情");
        findViewById(R.id.back).setOnClickListener(this);
        tv_setting = (TextView) findViewById(R.id.setting);
        findViewById(R.id.invite_layout).setOnClickListener(this);
//		findViewById(R.id.note_layout).setOnClickListener(this);
        findViewById(R.id.forward_layout).setOnClickListener(this);
        findViewById(R.id.linear_resumeinfo_talk).setOnClickListener(this);
        findViewById(R.id.linear_resumeinfo_callphone).setOnClickListener(this);
        tv_resumeinfo_callphone = (TextView) findViewById(R.id.tv_resumeinfo_callphone);
        iv_resumeinfo_callphone = (ImageView) findViewById(R.id.iv_resumeinfo_callphone);
        iv_resumeinfo_invite = (ImageView) findViewById(R.id.iv_resumeinfo_invite);
        lins = (LinearLayout) findViewById(R.id.lins);
        // 初始化通知的弹出PopWindow的View
        popView = LayoutInflater.from(app).inflate(R.layout.dialog_invite_view, new LinearLayout(this), false);
        mListView = (ListView) popView.findViewById(R.id.temple_hour_list);
        mListView.setOnItemClickListener(this);
        iv_resumeinfo_forward = (ImageView) findViewById(R.id.iv_resumeinfo_forward);
        iv_resumeinfo_talk = (ImageView) findViewById(R.id.iv_resumeinfo_talk);
        tv_resumeinfo_invite = (TextView) findViewById(R.id.tv_resumeinfo_invite);
        tv_resumeinfo_forward = (TextView) findViewById(R.id.tv_resumeinfo_forward);
        tv_resumeinfo_forward = (TextView) findViewById(R.id.tv_resumeinfo_forward);
        tv_resumeinfo_talk = (TextView) findViewById(R.id.tv_resumeinfo_talk);

        userid = getIntent().getStringExtra("userid");
        resume_id = getIntent().getStringExtra("resume_id");
        resume_language = getIntent().getStringExtra("resume_language");
        userName = getIntent().getStringExtra("username");
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        if (type != null && type.equals("10")) {
            resume_from = getIntent().getStringExtra("resume_from");
            apply_state = getIntent().getStringExtra("apply_state");
            job_id = getIntent().getStringExtra("job_id");
            job_name = getIntent().getStringExtra("job_name");
//            apply_state = getIntent().getStringExtra("id");
            if (apply_state.equals("4") || apply_state.equals("5")) {
                iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong2);
                tv_resumeinfo_talk.setTextColor(Color.parseColor("#CCCCCC"));
            } else {
                iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong);
                tv_resumeinfo_talk.setTextColor(Color.parseColor("#333333"));
            }
        }
//        resume_fdID = getIntent().getStringExtra("resume_fdID");
        if (type.equals("2")) {
            tv_setting.setVisibility(View.VISIBLE);
            tv_setting.setText("收藏");
            tv_setting.setOnClickListener(this);
        } else {
            tv_setting.setVisibility(View.GONE);
        }
        if (resume_from.equals("2")) {
            iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing);
            iv_resumeinfo_callphone.setImageResource(R.mipmap.xiazai2);
            iv_resumeinfo_forward.setImageResource(R.mipmap.zhuanfa2);
            tv_resumeinfo_invite.setTextColor(Color.parseColor("#CCCCCC"));
            tv_resumeinfo_callphone.setTextColor(Color.parseColor("#CCCCCC"));
            tv_resumeinfo_forward.setTextColor(Color.parseColor("#CCCCCC"));
        } else {
            iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing1);
            iv_resumeinfo_callphone.setImageResource(R.mipmap.xiazai);
            iv_resumeinfo_forward.setImageResource(R.mipmap.zhuanfa);
            tv_resumeinfo_invite.setTextColor(Color.parseColor("#333333"));
            tv_resumeinfo_callphone.setTextColor(Color.parseColor("#333333"));
            tv_resumeinfo_forward.setTextColor(Color.parseColor("#333333"));
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.back:// 返回
                this.finish();
                break;
            case R.id.setting:// 设置
                if (app.getNetworkMng().isCanConnect()) {
                    Map<String, Object> paramsMap = new HashMap<String, Object>();
                    paramsMap.put(HttpHelper.METHOD, HttpHelper.RESUMESEARCHSETBOX);
                    paramsMap.put("user_id", userid);
                    paramsMap.put("resume_id", resume_id);
                    paramsMap.put("resume_language", resume_language);
                    paramsMap.put("boxid", "4");
                    paramsMap.put("type", "0");
                    MsgHandler.sendMessage(paramsMap, handler, ResumeInfoHandler2.wSetResumeParticularStart);
                } else {
                    Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.invite_layout:// 通知
                if (!resume_from.equals("2")) {
                    if (isPhone) {
                        getInviteTag();
                    } else {
                    }
                }
                break;
            case R.id.linear_resumeinfo_talk:// 意向沟通

                if (app.intentionNum <= 0) {
                    Toast.makeText(context, "您尚未开启意向沟通服务或限量已用完，请联系您的招聘顾问!", Toast.LENGTH_SHORT).show();
                } else {
                    if (apply_state.equals("4") || apply_state.equals("5") || resume_id.equals("6")) {
                    } else {
                        Intent intent = new Intent(ResumeParticularInviteActivity.this, DoIntentionCommunicationActivity.class);
                        intent.putExtra("user_id", userid);
                        intent.putExtra("resume_id", resume_id);
                        intent.putExtra("resume_language", resume_language);
                        intent.putExtra("resume_from", resume_from);
                        intent.putExtra("type", type);
                        if (type.equals("10")) {
                            intent.putExtra("isIntention", "1");
                            intent.putExtra("job_name", job_name);
                            intent.putExtra("job_id", job_id);
                            intent.putExtra("r_id", id);
                        }
                        startActivity(intent);
                    }
                }
//                    }
//                } else {
//                    Intent intent = new Intent(ResumeParticularInviteActivity.this, DoIntentionCommunicationActivity.class);
//                    startActivity(intent);
//                }
                break;
            case R.id.forward_layout:// 转发
                if (!resume_from.equals("2")) {
                    showForwardDialog();
                }

                break;
            case R.id.linear_resumeinfo_callphone:// 拨打
                if (!resume_from.equals("2")) {
                    if (isPhone) {
                        resumeInfoFragment.callPhone();
                    } else {
                        downloadResume();
                    }
                }
                break;
        }
    }

    /**
     * 下载简历
     */
    private void downloadResume() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.DOWNLOAD_CONTACT);
            params.put("user_id", userid);
            params.put("name", userName);
            params.put("resume_id", resume_id);
            params.put("resume_language", resume_language);
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler2.wDownloadResumeStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通知之前需要先获取简历模版，然后打开通知对话框
     */
    private void getInviteTag() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUME_INVITE);
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler2.wGetInviteTagStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示面试邀请对话框
     */
    public void showInviteDialog(
            final List<ResumeTempleInfoBean> templeInfoBeans) {
        inviteDialog = new Dialog(this, R.style.SimpleDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_invite, new LinearLayout(this), false);
        send_email = (TextView) view.findViewById(R.id.send_email);
        send_msg = (TextView) view.findViewById(R.id.send_msg);
        moban_title = (TextView) view.findViewById(R.id.moban_title);
        reserve_date = (TextView) view.findViewById(R.id.reserve_date);
        reserve_hour = (TextView) view.findViewById(R.id.reserve_hour);
        reserve_minute = (TextView) view.findViewById(R.id.reserve_minute);
        email_title_layout = (LinearLayout) view.findViewById(R.id.email_title_layout);
        email_title = (EditText) view.findViewById(R.id.email_title);
        email_label_content = (TextView) view.findViewById(R.id.email_label_content);
        email_content = (EditText) view.findViewById(R.id.email_content);
        email_address_layout = (LinearLayout) view.findViewById(R.id.email_address_layout);
        reply_email = (EditText) view.findViewById(R.id.reply_email);
        ok_text = (TextView) view.findViewById(R.id.note_ok);
        // init data
        sendType = 1;
        templeInfoBean = templeInfoBeans.get(0);
        moban_title.setText(templeInfoBean.getName());
        email_title.setText(templeInfoBean.getEmail_title());
        email_content.setText(userName + templeInfoBean.getContent().replaceAll("<br>", "\n"));
        reply_email.setText(templeInfoBean.getEmail());
        ok_text.setText("确认发送邮件");

        send_email.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendType = 1;
                updateInviteDialogUI();
                refshInviteDialog();
            }
        });
        send_msg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendType = 2;
                updateInviteDialogUI();
                refshInviteDialog();
            }
        });

        moban_title.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showInviteTemple(templeInfoBeans);
            }
        });

        reserve_date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DataPickerDialog.showDialog(ResumeParticularInviteActivity.this, reserve_date, 3);
            }
        });
        reserve_hour.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showHour();
            }
        });
        reserve_minute.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showMinute();
            }
        });

        view.findViewById(R.id.note_ok).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        sendInvite();
                    }
                });
        view.findViewById(R.id.note_no).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        inviteDialog.dismiss();
                    }
                });
        inviteDialog.setContentView(view);
        inviteDialog.setCancelable(true);
        inviteDialog.setCanceledOnTouchOutside(true);
        inviteDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
//				invite_tv.setTextColor(getResources().getColor(R.color.whrite));
            }
        });
        inviteDialog.show();
    }

    /**
     * 发送通知请求
     */
    private void sendInvite() {
        if (moban_title.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请选择模板", Toast.LENGTH_SHORT).show();
            return;
        }

        if (reserve_date.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请选择预约日期", Toast.LENGTH_SHORT).show();
            return;
        }

        if (reserve_hour.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请选择预约时间到时", Toast.LENGTH_SHORT).show();
            return;
        }

        if (reserve_minute.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请选择预约时间到分", Toast.LENGTH_SHORT).show();
            return;
        }

        if (sendType == 1) {
            if (email_title.getText().toString().trim().equals("")) {
                Toast.makeText(this, "请输入邮件标题", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email_title.getText().toString().trim().length() > 100) {
                Toast.makeText(this, "邮件标题不能超过100个字符", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            if (email_content.getText().toString().trim().equals("")) {
                Toast.makeText(this, "请输入邮件内容", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email_content.getText().toString().trim().length() > 1000) {
                Toast.makeText(this, "邮件内容不能超过1000个字符", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            if (reply_email.getText().toString().trim().equals("")) {
                Toast.makeText(this, "请输入回复邮箱", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!StringUtils.isEmailRight(reply_email.getText().toString()
                    .trim())) {
                Toast.makeText(this, "请输入正确的回复邮箱", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (email_content.getText().toString().trim().equals("")) {
                Toast.makeText(this, "请输入短信内容", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email_content.getText().toString().trim().length() > 200) {
                Toast.makeText(this, "邮件内容不能超过200个字符", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }


        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.INVITE_SEND);
            params.put("ids", ids);
            params.put("send_type", sendType);
            if (type.equals("10")) {
                params.put("user_id", userid);
                params.put("resume_id", resume_id);
                params.put("resume_language", resume_language);
            }
//            params.put("user_id", userid);
//            params.put("resume_id", resume_id);
//            params.put("resume_language", resume_language);
//            if (type.equals(Const.DOWNLOAD)) {
//                params.put("type", "4");
//            } else {
            params.put("type", type2);
//            }
            if (sendType == 1) {// 邮箱
                params.put("sms_content", "");
                params.put("email_title", email_title.getText().toString().trim());
                params.put("email_content", email_content.getText().toString().trim());
                params.put("reply_email", reply_email.getText().toString().trim());
                MobclickAgent.onEvent(this, UMengEvent.INVITE_EMAIL);
            } else {// 短信
                MobclickAgent.onEvent(this, UMengEvent.INVITE_SMS);
                params.put("sms_content", email_content.getText().toString().trim());
                params.put("email_title", "");
                params.put("email_content", "");
                params.put("reply_email", "");
            }

            params.put("destine_year", reserve_date.getText().toString().trim());
            params.put("destine_hour", reserve_hour.getText().toString().trim());
            params.put("destine_minute", reserve_minute.getText().toString().trim());

            MsgHandler.sendMessage(params, handler, ResumeInfoHandler2.wSendInviteStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示模版弹出框
     *
     * @param templeInfoBeans
     */
    private void showInviteTemple(List<ResumeTempleInfoBean> templeInfoBeans) {
        popWindow = new PopupWindow(popView, 400, LayoutParams.MATCH_PARENT,
                true);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        popWindow.setAnimationStyle(R.style.ModePopupAnimation);

        InviteTempleAdapter adapter = new InviteTempleAdapter(this);
        adapter.setList(templeInfoBeans);
        mListView.setAdapter(adapter);

        if (popWindow.isShowing()) {
            popWindow.dismiss();
        } else {
            popWindow.showAsDropDown(moban_title, 0, 0);
        }
    }

    /**
     * 更新通知界面UI
     */
    private void updateInviteDialogUI() {
        if (sendType == 1) {
            email_title_layout.setVisibility(View.VISIBLE);
            email_label_content.setText("邮件内容：");
            email_address_layout.setVisibility(View.VISIBLE);
            ok_text.setText("确认发送邮件");
        } else {
            ok_text.setText("确认发送短信");
            email_title_layout.setVisibility(View.GONE);
            email_label_content.setText("短信内容：");
            email_address_layout.setVisibility(View.GONE);
        }
    }

    private void refshInviteDialog() {
        moban_title.setText(templeInfoBean.getName());
        email_title.setText(templeInfoBean.getEmail_title());
        reply_email.setText(templeInfoBean.getEmail());
        if (sendType == 1) {
            email_content.setText(userName
                    + templeInfoBean.getContent().replaceAll("<br>", "\n"));
        } else {
            email_content.setText(userName
                    + templeInfoBean.getSms_content().replaceAll("<br>", "\n"));
        }
    }

    /**
     * 显示时间弹出框
     */
    private void showHour() {
        popWindow = new PopupWindow(popView, 100, LayoutParams.MATCH_PARENT,
                true);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        popWindow.setAnimationStyle(R.style.ModePopupAnimation);

        List<String> hours = new ArrayList<String>();
        hours.add("08");
        hours.add("09");
        hours.add("10");
        hours.add("11");
        hours.add("12");
        hours.add("13");
        hours.add("14");
        hours.add("15");
        hours.add("16");
        hours.add("17");
        hours.add("18");
        hours.add("19");
        hours.add("20");

        InviteTimeAdapter adapter = new InviteTimeAdapter(this, 1);
        adapter.setList(hours);
        mListView.setAdapter(adapter);

        if (popWindow.isShowing()) {
            popWindow.dismiss();
        } else {
            popWindow.showAsDropDown(reserve_hour, 0, 0);
        }
    }

    /**
     * 显示分钟弹出框
     */
    private void showMinute() {
        popWindow = new PopupWindow(popView, 100, LayoutParams.MATCH_PARENT,
                true);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        popWindow.setAnimationStyle(R.style.ModePopupAnimation);

        List<String> minutes = new ArrayList<String>();
        minutes.add("00");
        minutes.add("10");
        minutes.add("20");
        minutes.add("30");
        minutes.add("40");
        minutes.add("50");

        InviteTimeAdapter adapter = new InviteTimeAdapter(this, 2);
        adapter.setList(minutes);
        mListView.setAdapter(adapter);

        if (popWindow.isShowing()) {
            popWindow.dismiss();
        } else {
            popWindow.showAsDropDown(reserve_minute, 0, 0);
        }

    }

    public int getViewWidth(final TextView view) {
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnPreDrawListener(new OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                // TODO Auto-generated method stub
                popWidth = view.getMeasuredWidth();
                return false;
            }
        });
        if (popWidth < 100) {
            popWidth = 100;
        }
        return popWidth;
    }

    /**
     * 关闭通知对话框
     */
    public void closeInviteDialog() {
        if (inviteDialog != null && inviteDialog.isShowing()) {
            inviteDialog.dismiss();
        }
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
            MobclickAgent.onEvent(this, UMengEvent.RESUME_NOTE);
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler2.wSetResumeNoteStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭备注对话框
     */
    public void closeNoteDialog(String note) {
        if (noteDialog != null && noteDialog.isShowing()) {
            noteDialog.dismiss();
        }
        remark = note;
        // 发送广播，修改备注信息
        Intent intent = new Intent(ResumeInfoFragment.NOTE_ACTION);
        intent.putExtra("remark", remark);
        sendBroadcast(intent);
    }

    /**
     * 显示转发对话框
     */
    private void showForwardDialog() {
        emailSelectMap.clear();
        emailDao = new RecentlyEmailDao(this);

        forwordDialog = new Dialog(this, R.style.SimpleDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_forward, new LinearLayout(this), false);
        forwordDialog.setContentView(view);
        final EditText edit_mail = (EditText) view.findViewById(R.id.edit_email);
        view.findViewById(R.id.note_ok).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        String emails = edit_mail.getText().toString().trim();
                        setEmail(emails);
                    }
                });
        view.findViewById(R.id.note_no).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        forwordDialog.dismiss();
                    }
                });
        LinearLayout recently_emails_layout = (LinearLayout) view.findViewById(R.id.recently_emails_layout);
        recently_emails_layout.removeAllViews();

        List<RecentlyEmailBean> recentlyEmailBeans = emailDao.getRecentlyEmailBean(app.getUser().getUser_id(), app.getUser().getSite_code());
        if (recentlyEmailBeans.size() > 0) {
            if (recentlyEmailBeans.size() > 5) {
                recentlyEmailBeans = recentlyEmailBeans.subList(0, 5);
            }
            for (final RecentlyEmailBean emailBean : recentlyEmailBeans) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.email_item, new LinearLayout(this), false);
                final ImageView email_check = (ImageView) itemView.findViewById(R.id.email_check);
                TextView email_text = (TextView) itemView.findViewById(R.id.email_text);
                email_text.setText(emailBean.getEmail());
                itemView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (emailSelectMap.size() == 5) {
                            Toast.makeText(ResumeParticularInviteActivity.this, "最多只能添加5个邮箱", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean isShowImg = addEmail(emailBean.getEmail(), edit_mail);
                        if (isShowImg) {
                            email_check.setVisibility(View.VISIBLE);
                        } else {
                            email_check.setVisibility(View.GONE);
                        }

                    }
                });
                recently_emails_layout.addView(itemView);
            }
        }


        forwordDialog.setCancelable(true);
        forwordDialog.setCanceledOnTouchOutside(true);

        forwordDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
//				forward_tv.setTextColor(getResources().getColor(R.color.whrite));
            }
        });

        forwordDialog.show();
    }

    /**
     * 显示转发对话框
     */
    private void callPhone() {
        callphoneDialog = new Dialog(this, R.style.SimpleDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_forward, new LinearLayout(this), false);
        callphoneDialog.setContentView(view);
        final EditText edit_mail = (EditText) view.findViewById(R.id.edit_email);
        view.findViewById(R.id.note_ok).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        view.findViewById(R.id.note_no).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        callphoneDialog.dismiss();
                    }
                });
        callphoneDialog.setCancelable(true);
        callphoneDialog.setCanceledOnTouchOutside(true);
        callphoneDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });

        callphoneDialog.show();
    }

    /**
     * 发送简历转发请求
     *
     * @param content
     */
    private void setEmail(String content) {
        if (content.equals("")) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] emails = content.split(",");
        List<RecentlyEmailBean> emailBeans = new ArrayList<RecentlyEmailBean>();
        if (emails.length > 5) {
            Toast.makeText(this, "最多只能添加5个邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        for (String email : emails) {
            if (!StringUtils.isEmailRight(email)) {
                Toast.makeText(this, "请正确输入电子邮件地址", Toast.LENGTH_SHORT).show();
                return;
            }
            RecentlyEmailBean bean = new RecentlyEmailBean();
            bean.setUser_id(app.getUser().getUser_id());
            bean.setUser_name(app.getUser().getUser_name());
            bean.setSite_code(app.getUser().getSite_code());
            bean.setEmail(email);
            bean.setDateline(System.currentTimeMillis());
            emailBeans.add(bean);
        }

        for (RecentlyEmailBean bean : emailBeans) {
            emailDao.save(bean);
        }
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.RESUME_FORWARD);
//            if (type.equals(Const.DOWNLOAD)) {
//                params.put("type", "4");
//            } else {
            params.put("type", type2);
//            }
            params.put("ids", ids);
            params.put("mail_address", content);
            params.put("mail_content", " ");
            MobclickAgent.onEvent(this, UMengEvent.RESUME_FORWARD);
            MsgHandler.sendMessage(params, handler,
                    ResumeInfoHandler2.wResumeForwardStart);
        } else {
            Toast.makeText(this, getString(R.string.nonet), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 关闭转发对话框
     */
    public void closeForwardDialog() {
        if (forwordDialog != null && forwordDialog.isShowing()) {
            forwordDialog.dismiss();
        }
    }

    /**
     * 添加email
     *
     * @param key
     * @param edit_mail
     * @return
     */
    private boolean addEmail(String key, EditText edit_mail) {
        if (emailSelectMap.get(key) == null) {
            emailSelectMap.put(key, key);
            refshEmails(edit_mail);
            return true;
        } else {
            emailSelectMap.remove(key);
            refshEmails(edit_mail);
        }
        return false;
    }

    /**
     * 更新EditText的数据
     *
     * @param edit_mail
     */
    private void refshEmails(EditText edit_mail) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : emailSelectMap.entrySet()) {
            sb.append(entry.getValue()).append(",");
        }
        if (sb.toString().trim().length() > 1) {
            edit_mail.setText(sb.toString().substring(0,
                    sb.toString().length() - 1));
        } else {
            edit_mail.setText("");
        }
    }

    /**
     * 设置对话框的大小
     *
     * @param dialog
     */
    public void setDialogSize(AlertDialog dialog, float size) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (metrics.widthPixels * size);
        params.height = LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        Object obj = parent.getAdapter().getItem(position);
        if (obj instanceof ResumeTempleInfoBean) {// 点击模版列表
            templeInfoBean = (ResumeTempleInfoBean) obj;
            refshInviteDialog();
            if (popWindow.isShowing()) {
                popWindow.dismiss();
            }
        } else if (parent.getAdapter() instanceof InviteTimeAdapter) {
            InviteTimeAdapter adapter = (InviteTimeAdapter) parent.getAdapter();
            if (adapter.getType() == 1) {// 小时
                reserve_hour.setText(obj.toString());
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
            } else {// 分钟
                reserve_minute.setText(obj.toString());
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
            }
        }
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        inviteDialog = null;
        noteDialog = null;
        forwordDialog = null;
//		deleteDialog = null;
        popWindow = null;
        app.setLoadingResumeInfo(false);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

//    public String getResume_fdID() {
//        return resume_fdID;
//    }
//
//    public void setResume_fdID(String resume_fdID) {
//        this.resume_fdID = resume_fdID;
//    }

    public String getResume_language() {
        return resume_language;
    }

    public void setResume_language(String resume_language) {
        this.resume_language = resume_language;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void showGson() {
        lins.setVisibility(View.GONE);
    }

}
