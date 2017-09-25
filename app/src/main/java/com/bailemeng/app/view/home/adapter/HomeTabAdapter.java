package com.bailemeng.app.view.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.view.home.fragment.HomeItemFragment;

import java.util.List;


public class HomeTabAdapter extends FragmentPagerAdapter {

	private List<String>   tabTitle;
	
	public HomeTabAdapter(FragmentManager fm, List<String> tabTitle) {
		super(fm);
		this.tabTitle = tabTitle;
	}

	@Override
	public Fragment getItem(int position) {
		//新建一个Fragment来展示ViewPager item的内容，并传递参数
		Fragment fragment = new HomeItemFragment();
		Bundle args = new Bundle();
		args.putString("arg", tabTitle.get(position));
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public int getCount() {
		return tabTitle.size();
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

}
