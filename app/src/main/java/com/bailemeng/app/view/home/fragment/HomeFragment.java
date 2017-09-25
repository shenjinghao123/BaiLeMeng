package com.bailemeng.app.view.home.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.view.home.adapter.HomeTabAdapter;
import com.bailemeng.app.widget.MyViewPager;

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
    private MyViewPager viewPager;

    private List<String>	 titles	   = new ArrayList<>();

    private HomeTabAdapter adapter;
    private ViewHolder holder;

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
        viewPager = (MyViewPager) view.findViewById(R.id.vp_home_manage_tab_container);
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
        setTabView();
    }

    /**
     * 设置Tab的样式
     */
    private void setTabView() {
        holder = null;
        for (int i = 0; i < titles.size(); i++) {
            //依次获取标签
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //为每个标签设置布局
            tab.setCustomView(R.layout.home_tab_item);
            holder = new ViewHolder(tab.getCustomView());
            //为标签填充数据
            holder.tvTabName.setText(titles.get(i));
            //默认选择第一项
            if (i == 0){
                holder.tvTabName.setSelected(true);
                holder.tvTabName.setTextSize(18);
                holder.tvTabName.setTextColor(Color.parseColor("#ffffff"));
            }
        }

        //tab选中的监听事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(true);
                //选中后字体变大
                holder.tvTabName.setTextSize(18);
                holder.tvTabName.setTextColor(Color.parseColor("#ffffff"));
                //让Viewpager跟随TabLayout的标签切换
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(false);
                //恢复为默认字体大小
                holder.tvTabName.setTextSize(16);
                holder.tvTabName.setTextColor(Color.parseColor("#E5E5E5"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class ViewHolder{
        TextView tvTabName;

        public ViewHolder(View tabView) {
            tvTabName = (TextView) tabView.findViewById(R.id.tv_tab_name);
        }
    }
}
