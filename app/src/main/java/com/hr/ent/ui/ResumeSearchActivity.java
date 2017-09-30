package com.hr.ent.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.hr.ent.R;
import com.hr.ent.fragment.SearchResumeFragment;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResumeSearchActivity extends FragmentActivity {

    @Bind(R.id.iv_resumesearch_back)
    ImageView ivResumesearchBack;
    //    @Bind(R.id.tv_resumesearch_resumelow)
//    TextView tvResumesearchResumelow;
//    @Bind(R.id.tv_resumesearch_resumehigh)
//    TextView tvResumesearchResumehigh;
    @Bind(R.id.vp_fragmentresume)
    ViewPager vpFragmentresume;
    /**
     * Fragment管理器
     */
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragmentList;
    public static SearchResumeFragment searchResumeFragment;

    //    public static  SearchResumeHighFragment searchResumeHighFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resume_search);
        ButterKnife.bind(this);
        initData();
        initViewPager();
    }

    //    @OnClick({R.id.iv_resumesearch_back, R.id.tv_resumesearch_resumelow, R.id.tv_resumesearch_resumehigh})
    @OnClick({R.id.iv_resumesearch_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_resumesearch_back:
                finish();
                break;
        }
    }

    public void initViewPager() {
        fragmentList = new ArrayList<Fragment>();
        searchResumeFragment = new SearchResumeFragment();
        fragmentList.add(searchResumeFragment);

        //给ViewPager设置适配器
        vpFragmentresume.setAdapter(new MyFragmentPagerAdapter(fragmentManager, fragmentList));
        vpFragmentresume.setCurrentItem(0);//设置当前显示标签页为第一页
        vpFragmentresume.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }

    /**
     * 初始化数据
     */
    private void initData() {
        fragmentManager = getSupportFragmentManager();
//        setTabSelect(0);
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
//            setTabSelect(arg0);
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
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

//    /**
//     * 重置按钮状态
//     */
//    private void resetButton() {
//        tvResumesearchResumelow.setBackgroundResource(R.drawable.linear_banyuan_left2);
//        tvResumesearchResumelow.setTextColor(Color.parseColor("#FFFFFF"));
//        tvResumesearchResumehigh.setBackgroundResource(R.drawable.linear_banyuan_right2);
//        tvResumesearchResumehigh.setTextColor(Color.parseColor("#FFFFFF"));
//    }

//    /**
//     * 根据传入的index选择Tab页
//     *
//     * @param index
//     */
//    public void setTabSelect(int index) {
//        resetButton();
//        switch (index) {
//            case 0:
//                tvResumesearchResumelow.setBackgroundResource(R.drawable.linear_banyuan_left1);
//                tvResumesearchResumelow.setTextColor(Color.parseColor("#F7931E"));
//                break;
//            case 1:
//                tvResumesearchResumehigh.setBackgroundResource(R.drawable.linear_banyuan_right1);
//                tvResumesearchResumehigh.setTextColor(Color.parseColor("#F7931E"));
//                break;
//        }
//    }
}
