package com.bailemeng.app.widget.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.bailemeng.app.base.BaseAppFragment;

import java.util.List;



public class TabAdapter extends FragmentPagerAdapter {
	
	private List<Fragment> fragments;
	private List<String>   tabTitle;
	private String searchKey;
	
	public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabTitle) {
		super(fm);
		this.fragments = fragments;
		this.tabTitle = tabTitle;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		BaseAppFragment fragment = (BaseAppFragment) super.instantiateItem(container, position);
		Bundle bundle=new Bundle();
		bundle.putString("searchKey",searchKey);
		fragment.updateArguments(bundle);
		return fragment;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}
	
	@Override
	public int getCount() {
		return fragments.size();
	}
	
	//设置TabLayout标题，方法继承自PagerAdapter，默认是返回null，所以不重写这个方法tab就没字了
	@Override
	public CharSequence getPageTitle(int position) {
		return tabTitle.get(position);
	}

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}

	public void updateFragment(String searchKey){
		this.searchKey=searchKey;
		notifyDataSetChanged();
	}
}
