package com.hr.ent.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.InviteInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteParticularActivity extends Activity {
    private InviteInfoBean inviteInfoBean;
    @Bind(R.id.iv_inviteparticular_back)
    ImageView ivInviteparticularBack;
    @Bind(R.id.tv_inviteparticular_email)
    TextView tvInviteparticularEmail;
    @Bind(R.id.tv_inviteparticular_message)
    TextView tvInviteparticularMessage;
    @Bind(R.id.tv_inviteparticular_resume)
    TextView tvInviteparticularResume;
    @Bind(R.id.vp_inviteparticular)
    ViewPager vpInviteparticular;
    private ArrayList<View> pageview;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_invite_particular);
        ButterKnife.bind(this);
        initData();
        initViewPager();
    }

    private void initData() {
        Bundle bundle = this.getIntent().getExtras();
        position = bundle.getString("position");
        inviteInfoBean = (InviteInfoBean) getIntent().getParcelableExtra(Const.PAR_KEY);
    }

    private void initViewPager() {
        pageview = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
        RelativeLayout view1 = (RelativeLayout) inflater.inflate(R.layout.item_inviteparticular_viewpager, null);
        RelativeLayout view2 = (RelativeLayout) inflater.inflate(R.layout.item_inviteparticular_viewpager, null);
        TextView textview1 = (TextView) view1.findViewById(R.id.tv_item_inviteparticular);
        TextView textview2 = (TextView) view2.findViewById(R.id.tv_item_inviteparticular);
        if (inviteInfoBean != null) {
            if (inviteInfoBean.getEmail_content() != null && !inviteInfoBean.getEmail_content().equals("")) {
                textview1.setText(inviteInfoBean.getEmail_content());
            } else {
                view1.setGravity(Gravity.CENTER);
                textview1.setGravity(Gravity.CENTER);
                textview1.setText("未发送邮件或发送失败");
            }
            if (inviteInfoBean.getSms_content() != null && !inviteInfoBean.getSms_content().equals("")) {
                textview2.setText(inviteInfoBean.getSms_content());
            } else {
                view2.setGravity(Gravity.CENTER);
                textview2.setGravity(Gravity.CENTER);
                textview2.setText("未发送短信或发送失败");
            }
        }
        pageview.add(view1);
        pageview.add(view2);
        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageview.size();
            }

            @Override
            //断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            //是从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }
        };
        //绑定适配器
        vpInviteparticular.setAdapter(mPagerAdapter);
        vpInviteparticular.setCurrentItem(0);//设置当前显示标签页为第一页
        vpInviteparticular.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            setTabSelect(arg0);
        }
    }

    /**
     * 根据传入的index选择Tab页
     *
     * @param index
     */
    public void setTabSelect(int index) {
        resetButton();
        switch (index) {
            case 0:
                tvInviteparticularEmail.setBackgroundResource(R.drawable.linear_banyuan_left1);
                tvInviteparticularEmail.setTextColor(Color.parseColor("#F7931E"));
                break;
            case 1:
                tvInviteparticularMessage.setBackgroundResource(R.drawable.linear_banyuan_right1);
                tvInviteparticularMessage.setTextColor(Color.parseColor("#F7931E"));
                break;
        }
    }

    /**
     * 重置按钮状态
     */
    private void resetButton() {
        tvInviteparticularEmail.setBackgroundResource(R.drawable.linear_banyuan_left2);
        tvInviteparticularEmail.setTextColor(Color.parseColor("#FFFFFF"));
        tvInviteparticularMessage.setBackgroundResource(R.drawable.linear_banyuan_right2);
        tvInviteparticularMessage.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @OnClick({R.id.iv_inviteparticular_back, R.id.tv_inviteparticular_email, R.id.tv_inviteparticular_message, R.id.tv_inviteparticular_resume})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_inviteparticular_back:
                finish();
                break;
            case R.id.tv_inviteparticular_email:
                setTabSelect(0);
                vpInviteparticular.setCurrentItem(0);
                break;
            case R.id.tv_inviteparticular_message:
                setTabSelect(1);
                vpInviteparticular.setCurrentItem(1);
                break;
            case R.id.tv_inviteparticular_resume:
                Intent intent = new Intent(this, ResumeParticularInviteActivity.class);
                intent.putExtra("resume_id", inviteInfoBean.getResume_id());
                intent.putExtra("userid", inviteInfoBean.getUser_id());
//                intent.putExtra("resume_fdID", inviteInfoBean.getResume_id());
                intent.putExtra("resume_language", inviteInfoBean.getResume_language());
                intent.putExtra("userName", inviteInfoBean.getUser_name());
                intent.putExtra("id", inviteInfoBean.getId());
                intent.putExtra("type","3");
                startActivity(intent);
                break;
        }
    }
}
