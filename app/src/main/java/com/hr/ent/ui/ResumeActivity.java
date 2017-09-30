package com.hr.ent.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumePageAdapter;
import com.hr.ent.fragment.ByJobScanFragment;
import com.hr.ent.fragment.ByResumeScanFragment;
import com.hr.ent.view.WindowUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 主菜单简历界面
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-24
 */
public class ResumeActivity extends BaseActivity implements OnClickListener {
	private TextView title;
	private FrameLayout type_mark;
	private ViewPager viewPager;

	private List<Fragment> fragments;
	private ByResumeScanFragment byResumeScanFragment;
	private ByJobScanFragment byJobScanFragment;
	private ResumePageAdapter adapter;

	private int currIndex = 0;
	private int position_one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowUtils.initWindow(this);
		setContentView(R.layout.activity_resume);
		init();

		fragments = new ArrayList<Fragment>();

		if (byResumeScanFragment == null) {
			byResumeScanFragment = new ByResumeScanFragment();
		}
		if (byJobScanFragment == null) {
			byJobScanFragment = new ByJobScanFragment();
		}
		fragments.add(byResumeScanFragment);
		fragments.add(byJobScanFragment);

		adapter = new ResumePageAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);

		initPagerListener();
	}

	private void init() {
		title = (TextView) findViewById(R.id.title);
		title.setText("简历管理");
		findViewById(R.id.back).setVisibility(View.GONE);
		findViewById(R.id.setting).setVisibility(View.GONE);

		type_mark = (FrameLayout) findViewById(R.id.bottom_mark);
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		findViewById(R.id.byresume_text).setOnClickListener(this);//按简历
		findViewById(R.id.byjob_text).setOnClickListener(this);//按职位
	}

	private void initPagerListener() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		position_one = (int) (screenW / 2.0);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Animation animation = null;
				if(arg0==0)
				{
					byResumeScanFragment.initData();
				}else
				{
					byJobScanFragment.initData();
				}
				switch (arg0) {
				case 0:
					if (currIndex == 1) {
						animation = new TranslateAnimation(-position_one, 0, 0,
								0);
					}
					break;
				case 1:
					if (currIndex == 0) {animation = new TranslateAnimation(0, -position_one, 0, 0);
					}
					break;
				}
				currIndex = arg0;
				animation.setFillAfter(true);
				animation.setDuration(300);
				type_mark.startAnimation(animation);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting:
			break;
		case R.id.byresume_text:
			viewPager.setCurrentItem(0);
			break;
		case R.id.byjob_text:
			viewPager.setCurrentItem(1);
			break;
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
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

}
