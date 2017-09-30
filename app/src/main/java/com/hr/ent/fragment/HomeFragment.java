package com.hr.ent.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.handler.HomeHandler;
import com.hr.ent.model.ContractLimitInfoBean;
import com.hr.ent.model.CrmInfo;
import com.hr.ent.model.User;
import com.hr.ent.ui.AppSettingActivity;
import com.hr.ent.ui.IntentionCommunicationActivity;
import com.hr.ent.ui.InviteListActivity;
import com.hr.ent.ui.PayQueryActivity;
import com.hr.ent.ui.ResumeScanActivity;
import com.hr.ent.ui.ResumeSearchActivity;
import com.hr.ent.ui.ServerInfoActivity;
import com.hr.ent.ui.TalentsListActivity;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.LogUtil;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.PopUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Home页
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.iv_fragmenthome_setting)
    ImageView ivFragmenthomeSetting;
    @Bind(R.id.tv_homefragment_hellotime)
    TextView tvHomefragmentHellotime;
    @Bind(R.id.tv_homefragment_iscontract)
    TextView tvHomefragmentIscontract;
    @Bind(R.id.rl_homefragment_search)
    RelativeLayout rlHomefragmentSearch;
    @Bind(R.id.tv_homefragment_newresume)
    TextView tvHomefragmentNewresume;
    @Bind(R.id.rl_homefragment_newresume)
    RelativeLayout rlHomefragmentNewresume;
    @Bind(R.id.tv_homefragment_noread)
    TextView tvHomefragmentNoread;
    @Bind(R.id.rl_homefragment_noread)
    RelativeLayout rlHomefragmentNoread;
    @Bind(R.id.rl_homefragment_searchmoney)
    RelativeLayout rlHomefragmentSearchmoney;
    @Bind(R.id.rl_homefragment_contractinfo)
    RelativeLayout rlHomefragmentContractinfo;
    @Bind(R.id.rl_homefragment_inviterecord)
    RelativeLayout rlHomefragmentInviterecord;
    @Bind(R.id.rl_homefragment_talkinfo)
    RelativeLayout rlHomefragmentTalkinfo;
    @Bind(R.id.rl_homefragment_talents)
    RelativeLayout rlHomefragmentTalents;
    @Bind(R.id.tv_homefragment_recruitname)
    TextView tvHomefragmentRecruitname;
    @Bind(R.id.tv_homefragment_recruit)
    TextView tvHomeFragmenRecruit;
    @Bind(R.id.lr_homefragment_recruitphone)
    LinearLayout lrHomefragmentRecruitphone;
    @Bind(R.id.iv_keyword_net)
    ImageView iv_keyword_net;
    private View view;
    private App app;
    private HomeHandler handler;
    private PopUtil popUtil;
    private String day_count = "0";
    private String unread = "0";
    private String name = "0";
    private CrmInfo crmInfo;
    public static HomeFragment homeFragment;
    private User user;
    private PopupWindow popupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homeFragment = HomeFragment.this;

        return view;
    }

    private void initData() {
        app = (App) getActivity().getApplication();
        handler = new HomeHandler(getActivity());
        user = app.getUser();
        app.setIndustry(app.getUser().getSite_code());
        crmInfo = user.getCrminfo();
        name = crmInfo.getName();
        initContractState();
        initBaseInfo();
        initLogo();
    }

    private void initBaseInfo() {

        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.BASE_INFO);
            MsgHandler.sendMessage(params, handler, HomeHandler.wGetBaseInfoStart);//获取服务信息
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put(HttpHelper.METHOD, HttpHelper.CONTRACTSTATE);
            MsgHandler.sendMessage(params2, handler, HomeHandler.wGetServiceStart2);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//        MobclickAgent.onResume(getActivity());
        initData();
        initResumeNum();
    }


    /**
     * 获取服务器简历数
     */
    private void initResumeNum() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.BOXFILTER);
            MsgHandler.sendMessage(params, handler, HomeHandler.wGetResumeFilterStart);
        } else {
            Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新合同信息
     *
     * @param bean
     */
    public void updateServiceLimit(ContractLimitInfoBean bean) {
        int linkup_num = Integer.parseInt(bean.getLinkup_num());
        int linkup_num_used = Integer.parseInt(bean.getLinkup_num_used());
        app.intentionNum = linkup_num - linkup_num_used;
    }

    private void initLogo() {
        switch (Integer.parseInt(app.getUser().getSite_code())) {
            case 11:// 建筑
                iv_keyword_net.setImageResource(R.mipmap.net11);
                break;
            case 12:// 金融
                iv_keyword_net.setImageResource(R.mipmap.net12);
                break;
            case 14:// 医药
                iv_keyword_net.setImageResource(R.mipmap.net14);
                break;
            case 26:// 服装
                iv_keyword_net.setImageResource(R.mipmap.net26);
                break;
            case 29:// 化工
                iv_keyword_net.setImageResource(R.mipmap.net29);
                break;
            case 15:// 教培
                iv_keyword_net.setImageResource(R.mipmap.net15);
                break;
            case 22:// 机械
                iv_keyword_net.setImageResource(R.mipmap.net22);
                break;
            case 19:// 电子
                iv_keyword_net.setImageResource(R.mipmap.net19);
                break;
            case 13:// 传媒
                iv_keyword_net.setImageResource(R.mipmap.net13);
                break;
            case 30:// 旅游
                iv_keyword_net.setImageResource(R.mipmap.net30);
                break;
            case 40:// 酒店餐饮
                iv_keyword_net.setImageResource(R.mipmap.net40);
                break;
            case 20:// 电力
                iv_keyword_net.setImageResource(R.mipmap.net20);
                break;
            case 23:// IT
                iv_keyword_net.setImageResource(R.mipmap.net23);
                break;
            case 16:// 物流
                iv_keyword_net.setImageResource(R.mipmap.net16);
                break;
            case 21:// 通信
                iv_keyword_net.setImageResource(R.mipmap.net21);
                break;
        }
    }

    public void updateNewResumeNum(String day_count, String unread) {
        if (day_count != null && !day_count.equals("")) {
            this.day_count = day_count;
        }
        if (unread != null && !unread.equals("")) {
            this.unread = unread;
        }
        tvHomefragmentNewresume.setText(this.day_count + "");
        tvHomefragmentNoread.setText(this.unread + "");
    }

    /**
     * 初始化合同的状态
     */
    private void initContractState() {
        int time = Integer.parseInt(app.getUser().getContractStatus().getTime());
        int use = Integer.parseInt(app.getUser().getContractStatus().getUse());
        if (time == 2 && use == 2) {// 暂停
            tvHomefragmentIscontract.setText("合同中");
            tvHomefragmentIscontract.setTextColor(Color.parseColor("#32B16C"));
            app.isContract = true;
            app.isTrial = false;
        } else if (time == 0 && use == 0) {
            tvHomefragmentIscontract.setText("试用中");
            tvHomefragmentIscontract.setTextColor(Color.parseColor("#32B16C"));
            app.isContract = true;
            app.isTrial = true;
        } else {
            tvHomefragmentIscontract.setTextColor(Color.parseColor("#cccccc"));
            tvHomeFragmenRecruit.setText("客服：400-650-0588");
            app.isContract = false;
            if (time == 1 && use == 1) {// 受限
                tvHomefragmentIscontract.setText("服务受限");
            } else if (use == 5 && time != 4) {// 合同中
                tvHomefragmentIscontract.setText("暂停中");
            } else if (time == 4) {// 过期
                tvHomefragmentIscontract.setText("过期中");
            } else {
                tvHomefragmentIscontract.setText("服务受限");
            }
        }
        if (crmInfo.getName() == null || crmInfo.getName().trim().equals("")) {
            tvHomefragmentIscontract.setTextColor(Color.parseColor("#cccccc"));
            tvHomeFragmenRecruit.setText("客服：400-650-0588");
        } else {
            tvHomefragmentRecruitname.setText(crmInfo.getName());//名字
        }
    }

    /**
     * 返回当前时间字符串
     *
     * @return
     */
    private String getTime() {
        long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            return "上午好";
        } else {
            return "下午好";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_fragmenthome_setting, R.id.rl_homefragment_talents, R.id.rl_homefragment_talkinfo, R.id.rl_homefragment_search, R.id.rl_homefragment_newresume, R.id.rl_homefragment_noread, R.id.rl_homefragment_searchmoney, R.id.rl_homefragment_contractinfo, R.id.rl_homefragment_inviterecord, R.id.lr_homefragment_recruitphone})
    public void onClick(View view) {
        if (app.isContract) {
            switch (view.getId()) {
                case R.id.iv_fragmenthome_setting:
                    Intent intentSet = new Intent(getActivity(), AppSettingActivity.class);
                    startActivity(intentSet);
                    break;
                case R.id.rl_homefragment_search:
                    Intent intentSearch = new Intent(getActivity(), ResumeSearchActivity.class);
                    startActivity(intentSearch);
                    break;
                case R.id.rl_homefragment_newresume:
                    if (day_count.equals("") || day_count.equals("0")) {
                        Toast.makeText(getActivity(), "今日没有新增简历", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "today");
                    bundle.putString("typeID", "1");
                    bundle.putString("typeName", "今日新增简历");
                    bundle.putString("boxtype", "3");
                    Intent intent = new Intent(getActivity(), ResumeScanActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.rl_homefragment_noread:
                    if (unread.equals("") || unread.equals("0")) {
                        Toast.makeText(getActivity(), "没有未读简历", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("type", "isnew");
                    bundle2.putString("typeID", "2");
                    bundle2.putString("typeName", "未读简历");
                    bundle2.putString("boxtype", "3");
                    Intent intent2 = new Intent(getActivity(), ResumeScanActivity.class);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                    break;
                case R.id.rl_homefragment_searchmoney:
                    Intent intentSearchMoney = new Intent(getActivity(), PayQueryActivity.class);
                    startActivity(intentSearchMoney);
                    break;
                case R.id.rl_homefragment_contractinfo:
                    Intent intentConstract = new Intent(getActivity(), ServerInfoActivity.class);
                    startActivity(intentConstract);
                    break;
                case R.id.rl_homefragment_inviterecord:
                    Intent intentInvite = new Intent(getActivity(), InviteListActivity.class);
                    startActivity(intentInvite);
                    break;
                case R.id.rl_homefragment_talkinfo:
                    Intent intentIntention = new Intent(getActivity(), IntentionCommunicationActivity.class);
                    startActivity(intentIntention);
                    break;
                /*
                人才寻访
                 */
                case R.id.rl_homefragment_talents:
                    Intent intentionTalents = new Intent(getActivity(), TalentsListActivity.class);
                    startActivity(intentionTalents);
                    break;
                case R.id.lr_homefragment_recruitphone:
                    if (app.isContract) {
                        if (crmInfo.getTelphone() != null && !crmInfo.getTelphone().equals("")) {
                            if(crmInfo.getTelphone().contains("/")){
                                String[] s=crmInfo.getTelphone().split("/");
                                initPopuwindow(s);
                            }else{
                                callPhone(crmInfo.getTelphone());
                            }
                        } else {
                            callPhone("4006500588");
                        }
                    } else {
                        callPhone("4006500588");
                    }
                    break;
            }
        } else {
            switch (view.getId()) {
                case R.id.iv_fragmenthome_setting:
                    Intent intentSet = new Intent(getActivity(), AppSettingActivity.class);
                    startActivity(intentSet);
                    break;
                case R.id.rl_homefragment_search:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_newresume:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_noread:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_searchmoney:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_contractinfo:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_inviterecord:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rl_homefragment_talkinfo:
                    Toast.makeText(getActivity(), "您尚未签订合同或合同已过期，请拨打客服电话进行咨询", Toast.LENGTH_LONG).show();
                    break;
                case R.id.lr_homefragment_recruitphone:
                    callPhone("4006500588");
                    break;
            }
        }
    }
    private void initPopuwindow(final String[] s) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.call_popupwindow, null);
        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        TextView tvPhone= (TextView) contentView .findViewById(R.id.phone);
        LinearLayout llDismiss= (LinearLayout) contentView.findViewById(R.id.ll_dismiss);
        TextView tvTelephone = (TextView) contentView .findViewById(R.id.telephone);
        tvPhone.setText(s[0]);
        tvTelephone.setText(s[1]);
        //设置各个控件的点击响应
        tvPhone .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(s[0]);
                popupWindow.dismiss();
            }
        });
        llDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tvTelephone .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(s[1]);
                popupWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    public void updateName(String name) {
        tvHomefragmentHellotime.setText(getTime() + "，" + name + "！");
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
            LogUtil.e("HomeActivity", "callPhone fail");
        }
    }
}
