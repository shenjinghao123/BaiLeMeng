package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Intent;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.activity
 * 描    述: 活动详情页
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/10
 */
public class EventDetailsItemActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, EventDetailsItemActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    @Override
    public void initialView() {

    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_event_details_item;
    }
}
