package com.hr.ent.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.ent.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResumeFragment extends Fragment {
    @Bind(R.id.tv_fragmentresume_resume)
    TextView tvFragmentresumeResume;
    @Bind(R.id.tv_fragmentresume_job)
    TextView tvFragmentresumeJob;
    @Bind(R.id.rl_contact_top)
    RelativeLayout rlContactTop;
//    @Bind(R.id.framLayout_fragmentresume)
//    FrameLayout framLayoutFragmentresume;
    @Bind(R.id.vp_fragmentresume)
    ViewPager vpFragmentresume;
    private View view;
    private ByJobScanFragment resumeJobFragment;
    private ByResumeScanFragment resumeResumeFragment;
    /**
     * Fragment管理器
     */
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragmentList;
    public ResumeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resume, container, false);
        ButterKnife.bind(this, view);
        initData();
        initViewPager();

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //9244
    public void initViewPager(){
        fragmentList = new ArrayList<Fragment>();
        resumeResumeFragment = new ByResumeScanFragment();
        resumeJobFragment =new ByJobScanFragment();
        fragmentList.add(resumeResumeFragment);
        fragmentList.add(resumeJobFragment);
        //给ViewPager设置适配器
        vpFragmentresume.setAdapter(new MyFragmentPagerAdapter(fragmentManager, fragmentList));
        vpFragmentresume.setCurrentItem(0);//设置当前显示标签页为第一页
        vpFragmentresume.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
//        private int one = offset *2 +bmpW;//两个相邻页面的偏移量

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
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
    }
        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }
    @OnClick({R.id.tv_fragmentresume_resume, R.id.tv_fragmentresume_job})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fragmentresume_resume:
                setTabSelect(0);
                vpFragmentresume.setCurrentItem(0);
                break;
            case R.id.tv_fragmentresume_job:
                setTabSelect(1);
                vpFragmentresume.setCurrentItem(1);
                break;
        }
    }
    /**
     * 初始化数据
     */
    private void initData() {
        fragmentManager = getActivity().getSupportFragmentManager();
        setTabSelect(0);
    }
    /**
     * 重置按钮状态
     *
     */
    private void resetButton() {
        tvFragmentresumeJob.setBackgroundResource(R.drawable.linear_banyuan_right2);
        tvFragmentresumeJob.setTextColor(Color.parseColor("#FFFFFF"));
        tvFragmentresumeResume.setBackgroundResource(R.drawable.linear_banyuan_left2);
        tvFragmentresumeResume.setTextColor(Color.parseColor("#FFFFFF"));
    }
    /**
     * 根据传入的index选择Tab页
     * @param index
     */
    public void setTabSelect(int index) {
        resetButton();
        //开启新事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        hideFragment(transaction);
        switch (index) {
            case 0:
                tvFragmentresumeResume.setBackgroundResource(R.drawable.linear_banyuan_left1);
                tvFragmentresumeResume.setTextColor(Color.parseColor("#F7931E"));
//                if (resumeResumeFragment == null) {
//                    resumeResumeFragment = new ByResumeScanFragment();
////                    transaction.add(R.id.framLayout_fragmentresume, resumeResumeFragment);
//                } else {
////                    transaction.show(resumeResumeFragment);
//                }\
                break;
            case 1:
                tvFragmentresumeJob.setBackgroundResource(R.drawable.linear_banyuan_right1);
                tvFragmentresumeJob.setTextColor(Color.parseColor("#F7931E"));
//                if (resumeJobFragment == null) {
//                    resumeJobFragment = new ByJobScanFragment();
////                    transaction.add(R.id.framLayout_fragmentresume, resumeJobFragment);
//                } else {
////                    transaction.show(resumeJobFragment);
//                }
                break;
        }

//        transaction.commit();
    }
//    /**
//     * 隐藏所有Fragment+
//     */
//    @SuppressLint("NewApi")
//    private void hideFragment(FragmentTransaction transaction) {
//        if (resumeResumeFragment != null) {
//            transaction.hide(resumeResumeFragment);
//        }
//        if (resumeJobFragment != null) {
//            transaction.hide(resumeJobFragment);
//        }
//    }
}
