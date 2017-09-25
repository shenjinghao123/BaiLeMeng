package com.bailemeng.app.base;

import android.os.Bundle;
import android.view.View;

import com.classic.android.base.BaseFragment;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.base
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */

public abstract class BaseAppFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View parentView, Bundle savedInstanceState) {
        super.initView(parentView, savedInstanceState);
        initialView(parentView);
        initialListenter();
        initialData();
    }

    public abstract void initialView(View view);

    public abstract void initialListenter();

    public abstract void initialData();

    public void updateArguments(Bundle data) {
        Bundle args = getArguments();
        if (args != null) {
            args.putBundle("data", data);
        }
    }
}
