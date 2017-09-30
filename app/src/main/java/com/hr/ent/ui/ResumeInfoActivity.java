package com.hr.ent.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.InviteTempleAdapter;
import com.hr.ent.adapter.InviteTimeAdapter;
import com.hr.ent.adapter.ResumeInfoPageAdapter;
import com.hr.ent.app.App;
import com.hr.ent.db.RecentlyEmailDao;
import com.hr.ent.fragment.ResumeInfoFragment;
import com.hr.ent.handler.ResumeInfoHandler;
import com.hr.ent.model.BaseResumeInfoBean;
import com.hr.ent.model.RecentlyEmailBean;
import com.hr.ent.model.ResumeTempleInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DataPickerDialog;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.StringUtils;
import com.hr.ent.utils.ToastUtils;
import com.hr.ent.utils.UMengEvent;
import com.hr.ent.view.CustomDatePicker;
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
public class ResumeInfoActivity extends BaseActivity implements
        OnClickListener, OnItemClickListener {
    private TextView title;
    private lazyLoadViewPager infoPager;
    private ResumeInfoPageAdapter adapter;
    private int pageSelected;
    private boolean isViewPagerLoadMore;
    private ResumeInfoBroadCast broadCast;

    private String type;
    private String typeID;
    private App app;
    private PopupWindow popupWindow;
    private ResumeInfoHandler handler;
    public static boolean isPhone;
    private ResumeTempleInfoBean templeInfoBean;

    private Dialog inviteDialog;// 通知
    private Dialog noteDialog;// 备注
    private Dialog forwordDialog;// 转发
    private Dialog callphoneDialog;// 转发
    //	private Dialog deleteDialog;// 删除
    private TextView invite_tv;
    private TextView forward_tv;

    // 通知对话框
    private TextView send_email;
    private TextView send_msg;
    private TextView moban_title;
    private TextView reserve_date, tv_resumeinfo_invite;
    private TextView reserve_hour;
    private TextView reserve_minute, tv_resumeinfo_talk;
    private LinearLayout email_title_layout, lins;
    private EditText email_title;
    private TextView email_label_content;
    private EditText email_content;
    private LinearLayout email_address_layout;
    private EditText reply_email;
    private TextView ok_text;

    // 通知对话框上的弹出框
    private PopupWindow popWindow;
    private View popView;
    private ListView mListView;
    private int popWidth = 100;
    private static String apply_state;
    public static String namePerson;
    public static String telNum;
    public static String callNum;
    private int sendType = 1;// 发送类型 1、邮件 2、短信

    private ImageView iv_resumeinfo_talk;
    private String userName;
    private String userid;
    private String resume_id, isHighResume;
    private String resume_fdID, resume_from_id;
    private String resume_language, resume_from = "";
    private String boxtype;//box类型
    private String remark = "";
    private ImageView iv_resumeinfo_callphone, iv_resumeinfo_invite;
    private TextView tv_resumeinfo_callphone;
    private String ids;
    private CustomDatePicker datePickerModle,datePickerHour,datePickerMiniter;

    public static ResumeInfoActivity resumeInfoActivity = null;
    private RecentlyEmailDao emailDao;
    private HashMap<String, String> emailSelectMap = new HashMap<String, String>();
    private TextView tvCurrentNum;
    //    private Dialog tipDialog = null;
    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if (msg.what == 100) {
                // 初始化ViewPager
                adapter = new ResumeInfoPageAdapter(getSupportFragmentManager());
                adapter.clear();
                adapter.setType(type);
                adapter.setTypeID(typeID);
                adapter.setBaseResumeInfoBeans(ResumeScanActivity.infoBeans);
                infoPager.setAdapter(adapter);
                infoPager.setCurrentItem(pageSelected);
                infoPager.setOnPageChangeListener(new lazyLoadViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        // TODO Auto-generated method stub
                        pageSelected = position;
                        // 更新上个页面的Adapter
                        Intent intent = new Intent(Const.UPDATE_ADAPTER);
                        intent.putExtra("position", position);
                        sendBroadcast(intent);
                        tvCurrentNum.setText( position + 1 + "/" + ResumeScanActivity.infoBeans.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        // TODO Auto-generated method stub
                        if (state == 1 && pageSelected == adapter.getCount() - 1) {
                            isViewPagerLoadMore = true;
                            Intent intent = new Intent(Const.LOAD_MORE);
                            sendBroadcast(intent);
                        }
                    }
                });/*new OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // TODO Auto-generated method stub
                        pageSelected = position;
                        // 更新上个页面的Adapter
                        Intent intent = new Intent(Const.UPDATE_ADAPTER);
                        intent.putExtra("position", position);
                        sendBroadcast(intent);
                        ToastUtils.makeTopToast(ResumeInfoActivity.this, position + 1 + "/" + ResumeScanActivity.infoBeans.size());
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                        // TODO Auto-generated method stub
                        if (arg0 == 1 && pageSelected == adapter.getCount() - 1) {
                            isViewPagerLoadMore = true;
                            Intent intent = new Intent(Const.LOAD_MORE);
                            sendBroadcast(intent);
                        }
                    }
                })*/
//                if (tipDialog.isShowing()) {
//                    tipDialog.dismiss();
//                    tipDialog = null;
//                }
            }
            return true;
        }
    });

    public void refreshApply(String ids2, String type2) {
        this.ids = ids2;
        this.type = type2;
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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resumeinfo);
        resumeInfoActivity = ResumeInfoActivity.this;
//        tipDialog = DialogUtil.createLoadingDialog(this, "请稍候...");
//        tipDialog.show();
        app = (App) getApplication();
        handler = new ResumeInfoHandler(this, ResumeInfoActivity.this);
        app.setLoadingResumeInfo(true);
        init();
        goInit();

    }

    private void initDialog(final List<ResumeTempleInfoBean> templeInfoBeans) {
        String[] s=new String[templeInfoBeans.size()];
        for(int i=0;i<templeInfoBeans.size();i++){
            s[i]=templeInfoBeans.get(i).getName();
        }
        datePickerModle = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                if("".equals(time)||time==null){
                    moban_title.setText("请选择");
                }else {
                    moban_title.setText(time);
                    for(int i=0;i<templeInfoBeans.size();i++){
                        if(time.equals(templeInfoBeans.get(i).getName())){
                           templeInfoBean=templeInfoBeans.get(i);
                            refshInviteDialog();
                            break;
                        }
                    }
                }
            }
        }, s);
        String[] hours = new String[13];

        hours[0]="08";
        hours[1]="09";
        hours[2]="10";
        hours[3]="11";
        hours[4]="12";
        hours[5]="13";
        hours[6]="14";
        hours[7]="15";
        hours[8]="16";
        hours[9]="17";
        hours[10]="18";
        hours[11]="19";
        hours[12]="20";
        //List<ResumeTempleInfoBean> templeInfoBeans
        String[] minutes = new String[6];
        minutes[0]="00";
        minutes[1]="10";
        minutes[2]="20";
        minutes[3]="30";
        minutes[4]="40";
        minutes[5]="50";
        datePickerMiniter = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                if("".equals(time)||time==null){
                    reserve_minute.setText("请选择");
                }else {
                    reserve_minute.setText(time);
                }
            }
        }, minutes);
        datePickerHour = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                if("".equals(time)||time==null){
                    reserve_hour.setText("请选择");
                }else {
                    reserve_hour.setText(time);
                }
            }
        }, hours);
    }

    public void goInit() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(100);
            }
        }).start();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        infoPager = (lazyLoadViewPager) findViewById(R.id.info_pager);
        title.setText("简历详情");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.setting).setVisibility(View.VISIBLE);
        iv_resumeinfo_talk = (ImageView) findViewById(R.id.iv_resumeinfo_talk);
        iv_resumeinfo_callphone = (ImageView) findViewById(R.id.iv_resumeinfo_callphone);
        iv_resumeinfo_invite = (ImageView) findViewById(R.id.iv_resumeinfo_invite);
        tv_resumeinfo_callphone = (TextView) findViewById(R.id.tv_resumeinfo_callphone);
        iv_resumeinfo_talk = (ImageView) findViewById(R.id.iv_resumeinfo_talk);
        tv_resumeinfo_talk = (TextView) findViewById(R.id.tv_resumeinfo_talk);
        tv_resumeinfo_invite = (TextView) findViewById(R.id.tv_resumeinfo_invite);
        tvCurrentNum= (TextView) findViewById(R.id.tv_currentNum);
        findViewById(R.id.invite_layout).setOnClickListener(this);
//		findViewById(R.id.note_layout).setOnClickListener(this);
        findViewById(R.id.forward_layout).setOnClickListener(this);
        findViewById(R.id.linear_resumeinfo_talk).setOnClickListener(this);
        findViewById(R.id.linear_resumeinfo_callphone).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
        lins = (LinearLayout) findViewById(R.id.lins);
        type = getIntent().getStringExtra("type");
        typeID = getIntent().getStringExtra("typeID");
        boxtype = getIntent().getStringExtra("boxtype");
        isHighResume = getIntent().getStringExtra("isHighResume");
        pageSelected = getIntent().getIntExtra("position", 0);
        tvCurrentNum.setText((pageSelected+1)+"/"+ResumeScanActivity.infoBeans.size());
        if (boxtype != null && boxtype.equals("3")) {
            resume_from = "3";
        }
        if (isHighResume.equals("0")) {
            iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong);
            tv_resumeinfo_talk.setTextColor(Color.parseColor("#333333"));
        } else {
            iv_resumeinfo_talk.setImageResource(R.mipmap.yixianggoutong2);
            tv_resumeinfo_talk.setTextColor(Color.parseColor("#CCCCCC"));
        }
//        resume_from_id = getIntent().getStringExtra("resume_from_id");
        // 初始化通知的弹出PopWindow的View

//        invite_tv = (TextView) findViewById(R.id.invite_tv);
//        forward_tv = (TextView) findViewById(R.id.forward_tv);
    }
    /**
     * 初始化ViewPager
     *
     * @param resumeInfoBeans
     */
    public void initViewPager(List<BaseResumeInfoBean> resumeInfoBeans) {
        adapter.clear();
        adapter.setBaseResumeInfoBeans(resumeInfoBeans);
        infoPager.setCurrentItem(pageSelected);
        adapter.notifyDataSetChanged();
    }

    public void clearViewPager() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化ViewPager
     *
     * @param resumeInfoBeans
     */
    public void addMoreViewPager(List<BaseResumeInfoBean> resumeInfoBeans) {
        adapter.clear();
        adapter.setBaseResumeInfoBeans(resumeInfoBeans);
        adapter.setPosition(ResumeInfoPageAdapter.POSITION_UNCHANGED);
        adapter.notifyDataSetChanged();
        if (isViewPagerLoadMore) {
            infoPager.setCurrentItem(infoPager.getCurrentItem() + 1);
        }
        adapter.setPosition(ResumeInfoPageAdapter.POSITION_NONE);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.back:// 返回
                this.finish();
                break;
            case R.id.setting:// 设置
                Intent intentOperate = new Intent(ResumeInfoActivity.this, ResumeOperationActivity.class);
                intentOperate.putExtra("user_id", userid);
                intentOperate.putExtra("resume_id", resume_id);
                intentOperate.putExtra("resume_language", resume_language);
                intentOperate.putExtra("ids", resume_fdID);
                intentOperate.putExtra("boxtype", boxtype);
                startActivity(intentOperate);
                break;
            case R.id.invite_layout:// 通知
                if (isPhone) {
                    getInviteTag();
                } else {
                }
                break;
            case R.id.forward_layout:// 转发
//			initTextColor(3);
                showForwardDialog();
                break;
            case R.id.linear_resumeinfo_callphone:// 拨打
                if (isPhone) {
//                    Toast.makeText(context,callNum+"  "+telNum,Toast.LENGTH_SHORT).show();
                    if (callNum != null && !callNum.equals("")) {
                        callPhone(callNum);
                    } else {
                        callPhone(telNum);
                    }
                } else {
                    downloadResume();
                }
                break;
            case R.id.linear_resumeinfo_talk:// 意向沟通
                if (isHighResume.equals("0")) {
                    if (app.intentionNum <= 0) {
                        Toast.makeText(context, "您尚未开启意向沟通服务或限量已用完，请联系您的招聘顾问!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(ResumeInfoActivity.this, DoIntentionCommunicationActivity.class);
                        intent.putExtra("user_id", userid);
                        intent.putExtra("resume_id", resume_id);
                        intent.putExtra("resume_language", resume_language);
                        intent.putExtra("resume_from_id", ids);
                        intent.putExtra("resume_from", resume_from);
                        startActivity(intent);
                    }
                } else {
                }
                break;
        }
    }


    public void refushUI() {
        if (isPhone) {
            tv_resumeinfo_callphone.setText("拨打");
            iv_resumeinfo_callphone.setImageResource(R.mipmap.dianhua2);
            iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing1);
            tv_resumeinfo_invite.setTextColor(Color.parseColor("#333333"));
        } else {
            tv_resumeinfo_callphone.setText("下载");
            iv_resumeinfo_callphone.setImageResource(R.mipmap.xiazai);
            iv_resumeinfo_invite.setImageResource(R.mipmap.yaoqing);
            tv_resumeinfo_invite.setTextColor(Color.parseColor("#CCCCCC"));
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
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler.wDownloadResumeStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

//	/**
//	 * 设置底部菜单字体的颜色
//	 *
//	 * @param i
//	 */
//	private void initTextColor(int i) {
//		if (i == 1) {
//			invite_tv.setTextColor(getResources().getColor(R.color.orange));
//			forward_tv.setTextColor(getResources().getColor(R.color.whrite));
//		} else if (i == 2) {
//			invite_tv.setTextColor(getResources().getColor(R.color.whrite));
//			forward_tv.setTextColor(getResources().getColor(R.color.whrite));
//		} else if (i == 3) {
//			invite_tv.setTextColor(getResources().getColor(R.color.whrite));
//			forward_tv.setTextColor(getResources().getColor(R.color.orange));
//		} else if (i == 4) {
//			invite_tv.setTextColor(getResources().getColor(R.color.whrite));
//			forward_tv.setTextColor(getResources().getColor(R.color.whrite));
//		}
//	}

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
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    public void initPopuwindow(final List<ResumeTempleInfoBean> templeInfoBeans) {
        initDialog(templeInfoBeans);
        //设置contentView
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_invite, null);
        popupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        send_email = (TextView) view.findViewById(R.id.send_email);
        send_msg = (TextView) view.findViewById(R.id.send_msg);
        moban_title = (TextView) view.findViewById(R.id.moban_title);
        reserve_date = (TextView) view.findViewById(R.id.reserve_date);
        reserve_hour = (TextView) view.findViewById(R.id.reserve_hour);
        reserve_minute = (TextView) view.findViewById(R.id.reserve_minute);
        email_title_layout = (LinearLayout) view.findViewById(R.id.email_title_layout);
        email_title = (EditText) view.findViewById(R.id.email_title);
        email_label_content = (TextView) view
                .findViewById(R.id.email_label_content);
        email_content = (EditText) view.findViewById(R.id.email_content);
        email_address_layout = (LinearLayout) view
                .findViewById(R.id.email_address_layout);
        reply_email = (EditText) view.findViewById(R.id.reply_email);
        ok_text = (TextView) view.findViewById(R.id.note_ok);
        /**//*showInviteTemple(templeInfoBeans);*/
        // init data
        sendType = 1;
        templeInfoBean = templeInfoBeans.get(0);
        moban_title.setText(templeInfoBean.getName());
//        moban_title.setText(namePerson);
        email_title.setText(templeInfoBean.getEmail_title());
        email_content.setText(namePerson
                + templeInfoBean.getContent().replaceAll("<br>", "\n"));
        reply_email.setText(templeInfoBean.getEmail());
        ok_text.setText("发邮箱");
        moban_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerModle.show(moban_title.getText().toString());
            }
        });
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

        reserve_date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DataPickerDialog.showDialog(ResumeInfoActivity.this,
                        reserve_date, 3);
            }
        });
        reserve_hour.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               datePickerHour.show(reserve_hour.getText().toString());
            }
        });
        reserve_minute.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                datePickerMiniter.show(reserve_minute.getText().toString());
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
                        popupWindow.dismiss();
                    }
                });
        /*inviteDialog.setContentView(view);
        inviteDialog.setCancelable(true);
        inviteDialog.setCanceledOnTouchOutside(true);

        pop.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
//				invite_tv.setTextColor(getResources().getColor(R.color.whrite));
            }
        });*/
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.dialog_invite, null);
        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
  /*  *//**
     * 显示面试邀请对话框
     *//*
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
        email_label_content = (TextView) view
                .findViewById(R.id.email_label_content);
        email_content = (EditText) view.findViewById(R.id.email_content);
        email_address_layout = (LinearLayout) view
                .findViewById(R.id.email_address_layout);
        reply_email = (EditText) view.findViewById(R.id.reply_email);
        ok_text = (TextView) view.findViewById(R.id.note_ok);

        // init data
        sendType = 1;
        templeInfoBean = templeInfoBeans.get(0);
        moban_title.setText(templeInfoBean.getName());
//        moban_title.setText(namePerson);
        email_title.setText(templeInfoBean.getEmail_title());
        email_content.setText(namePerson
                + templeInfoBean.getContent().replaceAll("<br>", "\n"));
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
                DataPickerDialog.showDialog(ResumeInfoActivity.this,
                        reserve_date, 3);
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
*/
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

//        if (app.getNetworkMng().isCanConnect()) {
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put(HttpHelper.METHOD, HttpHelper.INVITE_SEND);
//            params.put("ids", resume_fdID);
//            params.put("send_type", sendType);
//            if (type.equals(Const.DOWNLOAD)) {
//                params.put("type", "4");
//            } else {
//                params.put("type", "1");
//            }
//            if (sendType == 1) {// 邮箱
//                params.put("sms_content", "");
//                params.put("email_title", email_title.getText().toString()
//                        .trim());
//                params.put("email_content", email_content.getText().toString()
//                        .trim());
//                params.put("reply_email", reply_email.getText().toString()
//                        .trim());
//                MobclickAgent.onEvent(this, UMengEvent.INVITE_EMAIL);
//            } else {// 短信
//                MobclickAgent.onEvent(this, UMengEvent.INVITE_SMS);
//                params.put("sms_content", email_content.getText().toString()
//                        .trim());
//                params.put("email_title", "");
//                params.put("email_content", "");
//                params.put("reply_email", "");
//            }
//
//            params.put("destine_year", reserve_date.getText().toString().trim());
//            params.put("destine_hour", reserve_hour.getText().toString().trim());
//            params.put("destine_minute", reserve_minute.getText().toString()
//                    .trim());
//
//            MsgHandler.sendMessage(params, handler,
//                    ResumeInfoHandler.wSendInviteStart);
//        } else {
//            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
//        }
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.INVITE_SEND);
            params.put("ids", ids);
            params.put("send_type", sendType);
            if (type.equals(Const.DOWNLOAD)) {
                params.put("type", type);
            } else {
                params.put("type", type);
            }
            if (sendType == 1) {// 邮箱
                params.put("sms_content", "");
                params.put("email_title", email_title.getText().toString()
                        .trim());
                params.put("email_content", email_content.getText().toString()
                        .trim());
                params.put("reply_email", reply_email.getText().toString()
                        .trim());
                MobclickAgent.onEvent(this, UMengEvent.INVITE_EMAIL);
            } else {// 短信
                MobclickAgent.onEvent(this, UMengEvent.INVITE_SMS);
                params.put("sms_content", email_content.getText().toString()
                        .trim());
                params.put("email_title", "");
                params.put("email_content", "");
                params.put("reply_email", "");
            }

            params.put("destine_year", reserve_date.getText().toString().trim());
            params.put("destine_hour", reserve_hour.getText().toString().trim());
            params.put("destine_minute", reserve_minute.getText().toString()
                    .trim());

            MsgHandler.sendMessage(params, handler,
                    ResumeInfoHandler.wSendInviteStart);
            popupWindow.dismiss();
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

 /*   *//**
     * 显示模版弹出框
     *
     * @param templeInfoBeans
     *
     */
    private void showInviteTemple(List<ResumeTempleInfoBean> templeInfoBeans) {

        popView = LayoutInflater.from(this).inflate(R.layout.dialog_invite_view, null);
        mListView = (ListView) popView.findViewById(R.id.temple_hour_list);
        mListView.setOnItemClickListener(this);
        popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                true);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        popWindow.setAnimationStyle(R.style.ModePopupAnimation);
        View rootview = LayoutInflater.from(this).inflate(R.layout.dialog_invite, null);
        moban_title= (TextView) rootview.findViewById(R.id.moban_title);
        // 设置好参数之后再show
        int local[] = new int[2];
        //弹出控件的位置，坐标存在local数组
        moban_title.getLocationOnScreen(local);

        int width = moban_title.getWidth();
        int height = moban_title.getHeight();
        if (width == 0 || height == 0) {
            // 获取测量后的宽度
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            moban_title.measure(w, h);
            width =moban_title.getMeasuredWidth();
            height = moban_title.getMeasuredHeight();
        }

        // x坐标计算方式：complete_count_txt的x坐标加上他的长度一半（相当于complete_count_txt的横向居中位置）
        // ，再减少弹出气泡宽度的一半（相当于向左移动气泡一半的宽度，就居中显示在complete_count_txt了）
        int x = local[0] + (moban_title.getWidth() / 2) - width / 2;
        // y坐标计算方式：complete_count_txt的y坐标减去气泡的高度
        int y = local[1] - height;

        InviteTempleAdapter adapter = new InviteTempleAdapter(this);
        adapter.setList(templeInfoBeans);
        mListView.setAdapter(adapter);
        popWindow.showAtLocation(moban_title, Gravity.CENTER, x, y);
    }
    /**
     * 更新通知界面UI
     */
    private void updateInviteDialogUI() {
        if (sendType == 1) {
            email_title_layout.setVisibility(View.VISIBLE);
            email_label_content.setText("邮件内容：");
            email_address_layout.setVisibility(View.VISIBLE);
            ok_text.setText("发邮箱");
        } else {
            ok_text.setText("发短信");
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
            email_content.setText(namePerson
                    + templeInfoBean.getContent().replaceAll("<br>", "\n"));
        } else {
            email_content.setText(namePerson
                    + templeInfoBean.getSms_content().replaceAll("<br>", "\n"));
        }
    }

    /**
     * 显示时间弹出框
     */
   /* private void showHour() {
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
    }*/

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
            MsgHandler.sendMessage(params, handler, ResumeInfoHandler.wSetResumeNoteStart);
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
        LinearLayout recently_emails_layout = (LinearLayout) view
                .findViewById(R.id.recently_emails_layout);
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
                            Toast.makeText(ResumeInfoActivity.this, "最多只能添加5个邮箱", Toast.LENGTH_SHORT).show();
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
            if (type.equals(Const.DOWNLOAD)) {
                params.put("type", type);
            } else {
                params.put("type", type);
            }
//            if (type.equals(Const.DOWNLOAD)) {
//                params.put("type", "4");
//            } else {
//                params.put("type", "1");
//            }
            params.put("ids", ids);
            params.put("mail_address", content);
            params.put("mail_content", " ");
            MobclickAgent.onEvent(this, UMengEvent.RESUME_FORWARD);
            MsgHandler.sendMessage(params, handler,
                    ResumeInfoHandler.wResumeForwardStart);
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
//
//	/**
//	 * 显示删除对话框
//	 */
//	private void showDeleteDialog() {
//		deleteDialog = new AlertDialog.Builder(this).create();
//		View view = LayoutInflater.from(this).inflate(R.layout.dialog_delete,
//				new LinearLayout(this), false);
//		view.findViewById(R.id.ok_layout).setOnClickListener(
//				new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						deleteResume();
//					}
//				});
//
//		deleteDialog.setView(view, 0, 0, 0, 0);
//		deleteDialog.setCancelable(true);
//		deleteDialog.setCanceledOnTouchOutside(true);
//
//		deleteDialog.setOnDismissListener(new OnDismissListener() {
//
//			@Override
//			public void onDismiss(DialogInterface dialog) {
//				// TODO Auto-generated method stub
//			}
//		});
//
//		deleteDialog.show();
//		setDialogSize(deleteDialog, (float) 0.8);
//	}

//	/**
//	 * 发送删除简历请求
//	 */
//	private void deleteResume() {
//		if (app.getNetworkMng().isCanConnect()) {
//			MobclickAgent.onEvent(this, UMengEvent.RESUME_DELETE);
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put(HttpHelper.METHOD, HttpHelper.RESUME_DELETE);
//			params.put("ids", resume_fdID);
//
//			MsgHandler.sendMessage(params, handler,
//					ResumeInfoHandler.wDeleteResumeStart);
//		}
//	}

//	/**
//	 * 关闭删除对话框
//	 */
//	public void closeDeleteDialog() {
//		if (deleteDialog != null && deleteDialog.isShowing()) {
//			deleteDialog.dismiss();
//		}
//		// 发送广播，更新数据
//		Intent intent = new Intent(Const.INIT_DATA);
//		sendBroadcast(intent);
//	}

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
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (broadCast == null) {
            broadCast = new ResumeInfoBroadCast();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Const.INIT_PAGE);
            filter.addAction(Const.REFSH_PAGE);
            registerReceiver(broadCast, filter);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this);
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
        unregisterReceiver(broadCast);
    }

    /**
     * 这个广播主要用于更新ViewPager
     *
     * @author 800hr：yelong
     *         <p/>
     *         2015-7-3
     */
    public class ResumeInfoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals(Const.INIT_PAGE)) {// 重新初始化ViewPager
                // List<BaseResumeInfoBean> infoBeans=(List<BaseResumeInfoBean>)
                // intent.getExtras().getSerializable("infobeans");
                initViewPager(ResumeScanActivity.infoBeans);
            } else if (action.equals(Const.REFSH_PAGE)) {// 加载更多的ViewPager
                addMoreViewPager(ResumeScanActivity.infoBeans);
            }
        }
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
        this.namePerson = userName;
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

    public String getResume_fdID() {
        return resume_fdID;
    }

    public void setResume_fdID(String resume_fdID) {
        this.resume_fdID = resume_fdID;
    }

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

    public void HidGson() {
        lins.setVisibility(View.GONE);
    }
    public void showGson() {
        lins.setVisibility(View.VISIBLE);
    }

}
