package com.taimen.bailemen.base;

import android.os.Bundle;

import com.classic.android.base.BaseActivity;

/**
 * Created by 123 on 2017/9/18.
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
