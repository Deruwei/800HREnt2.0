package com.hr.ent.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.hr.ent.fragment.ResumeInfoFragment;
import com.hr.ent.model.BaseResumeInfoBean;

public class ResumeInfoPageAdapter extends FragmentStatePagerAdapter {

	private List<ResumeInfoFragment> infoFragments = new ArrayList<ResumeInfoFragment>();
	private String type;
	private String typeID;
	private int position = PagerAdapter.POSITION_NONE;

	public List<ResumeInfoFragment> getInfoFragments() {
		return infoFragments;
	}

	public void setInfoFragments(List<ResumeInfoFragment> infoFragments) {
		this.infoFragments = infoFragments;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ResumeInfoPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return infoFragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infoFragments.size();
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Object instantiateItem(ViewGroup parent, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(parent, position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	public void add(List<BaseResumeInfoBean> resumeInfoBeans) {
		for (BaseResumeInfoBean infoBean : resumeInfoBeans) {
			ResumeInfoFragment infoFragment = new ResumeInfoFragment();
			Bundle bundle = new Bundle();
			bundle.putParcelable("infoBean", infoBean);
			bundle.putString("type", type);
			bundle.putString("typeID", typeID);
			infoFragment.setArguments(bundle);
			infoFragments.add(infoFragment);
		}
	}

	public void setBaseResumeInfoBeans(List<BaseResumeInfoBean> resumeInfoBeans) {
		for (BaseResumeInfoBean infoBean : resumeInfoBeans) {
			ResumeInfoFragment infoFragment = new ResumeInfoFragment();
			Bundle bundle = new Bundle();
			bundle.putParcelable("infoBean", infoBean);
			bundle.putString("type", type);
			bundle.putString("typeID", typeID);
			infoFragment.setArguments(bundle);
			infoFragments.add(infoFragment);
		}
	}

	public void clear() {
		infoFragments.clear();
	}
}
