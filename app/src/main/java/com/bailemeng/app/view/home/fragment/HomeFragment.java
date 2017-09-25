package com.bailemeng.app.view.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.widget.adapter.TabAdapter;

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

    private List<Fragment> fragments = new ArrayList<>();
    private List<String>	 titles	   = new ArrayList<>();

    private HomeOneFragment        allOrderFragment;//订单全部页面
    private HomeOneFragment obligationFragment;//待付款页面
    private HomeOneFragment    waitingFragment;//待发货页面
    private HomeOneFragment  receivingFragment;//待收货页面
    private HomeOneFragment     refundFragment;//退款页面
    private TabAdapter adapter;

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
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
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
        allOrderFragment = HomeOneFragment.newInstance();
        fragments.add(allOrderFragment);
        obligationFragment = HomeOneFragment.newInstance();
        fragments.add(obligationFragment);
        waitingFragment = HomeOneFragment.newInstance();
        fragments.add(waitingFragment);
        receivingFragment = HomeOneFragment.newInstance();
        fragments.add(receivingFragment);
        refundFragment = HomeOneFragment.newInstance();
        fragments.add(refundFragment);
        //tag所在比例问题
        tagView();
    }

    private void tagView() {
        if (titles.size() == 3)
        {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        else
        {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        viewPager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (allOrderFragment != null)
        {
            allOrderFragment.onHiddenChanged(hidden);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment */
        if (null != allOrderFragment){
            allOrderFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != obligationFragment){
            obligationFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != waitingFragment){
            waitingFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != receivingFragment){
            receivingFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != refundFragment){
            refundFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
