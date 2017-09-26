package com.bailemeng.app.main.activity;

import android.app.Activity;
import android.content.Intent;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.main.activity
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/25
 */
public class LoginActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, LoginActivity.class);
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
        return R.layout.activity_login;
    }
}
