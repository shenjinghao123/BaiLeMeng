package com.bailemeng.app.view.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.view.home.adapter.HomeTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */
public class HomeFragment extends BaseAppFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<String>	 titles	   = new ArrayList<>();

    private HomeTabAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initialView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.home_manage_tab);
        viewPager = (ViewPager) view.findViewById(R.id.vp_home_manage_tab_container);
    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        adapter = new HomeTabAdapter(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //初始化顶部tab
        initTab();
    }

    private void initTab() {
        titles.add("推荐");
        titles.add("团舞");
        titles.add("瑜伽");
        titles.add("旗袍");
        titles.add("声乐");
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
    }

}
