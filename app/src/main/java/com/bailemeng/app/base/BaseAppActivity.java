package com.bailemeng.app.base;

import android.os.Bundle;

import com.classic.android.base.BaseActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.base
 * 创 建 人: 123
 * 创建时间: 2017/9/18
 */

public abstract class BaseAppActivity extends BaseActivity {
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initialView();
        initialListenter();
        initialData();
    }

    public abstract void initialView();

    public abstract void initialListenter();

    public abstract void initialData();
}
