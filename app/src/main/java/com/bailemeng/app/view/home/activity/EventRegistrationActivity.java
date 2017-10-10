package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.view.home.fragment.EventRegistrationItemFragment;
import com.bailemeng.app.widget.yviewpage.YFragmentPagerAdapter;
import com.bailemeng.app.widget.yviewpage.YViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.activity
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/9
 */
public class EventRegistrationActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, EventRegistrationActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private TextView titleTv;
    private ImageView leftIv;
    private YViewPager yViewPager;

    @Override
    public void initialView() {
        titleTv = (TextView) findViewById(R.id.tv_app_head_center);
        leftIv = (ImageView) findViewById(R.id.iv_app_head_left);
        yViewPager= (YViewPager) findViewById(R.id.event_registration_yviewpager);
    }

    @Override
    public void initialListenter() {
        leftIv.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        titleTv.setVisibility(View.VISIBLE);
        leftIv.setVisibility(View.VISIBLE);
        titleTv.setText("活动报名");
        List<String> titleList=new ArrayList<>();
        titleList.add("#FF5252");titleList.add("#F8BBD0");titleList.add("#BBBBBB");titleList.add("#518F9C");titleList.add("#56B6FF");titleList.add("#00ABEB");titleList.add("#19A318");
        yViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),titleList));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_event_registration;
    }

    class FragmentAdapter extends YFragmentPagerAdapter {

        private List<String> tabTitle;

        public FragmentAdapter(FragmentManager fm,List<String> tabTitle) {
            super(fm);
            this.tabTitle=tabTitle;
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = EventRegistrationItemFragment.newInstance();
            Bundle args = new Bundle();
            args.putInt("type", position==(tabTitle.size()-1)?1:0);
            args.putString("title", tabTitle.get(position));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return tabTitle.size();
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_app_head_left:
                finish();
                break;
        }
    }
}
