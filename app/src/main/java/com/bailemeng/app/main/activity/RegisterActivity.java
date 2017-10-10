package com.bailemeng.app.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.main.activity
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/10
 */
public class RegisterActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, RegisterActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private TextView headTitle,headLeft;

    @Override
    public void initialView() {
        headLeft = (TextView) findViewById(R.id.tv_app_head_left);
        headTitle = (TextView) findViewById(R.id.tv_app_head_center);
    }

    @Override
    public void initialListenter() {
        headLeft.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        headLeft.setVisibility(View.VISIBLE);
        headTitle.setVisibility(View.VISIBLE);
        headLeft.setText("返回");
        headTitle.setText("用户注册");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_app_head_left:
                finish();
                break;
        }
    }
}
