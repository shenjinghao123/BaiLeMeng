package com.bailemeng.app.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.view.commonview.activity.WebViewActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.main.activity
 * 创 建 人: 123
 * 创建时间: 2017/9/26
 */
public class LoginTwoStageActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, LoginTwoStageActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private TextView headTitle,headLeft,loginTogoMainTv,loginTogoRegisterTv,loginTogoForgotTv,webTermTv;

    @Override
    public void initialView() {
        headLeft = (TextView) findViewById(R.id.tv_app_head_left);
        headTitle = (TextView) findViewById(R.id.tv_app_head_center);
        loginTogoMainTv = (TextView) findViewById(R.id.login_togo_main_tv);//登录
        loginTogoRegisterTv = (TextView) findViewById(R.id.login_togo_register_tv);//注册
        loginTogoForgotTv = (TextView) findViewById(R.id.login_forget_passward_tv);//找回密码
        webTermTv = (TextView) findViewById(R.id.web_term_service_content_tv);//服务条款
    }

    @Override
    public void initialListenter() {
        headLeft.setOnClickListener(this);
        loginTogoMainTv.setOnClickListener(this);
        loginTogoRegisterTv.setOnClickListener(this);
        loginTogoForgotTv.setOnClickListener(this);
        webTermTv.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        headLeft.setVisibility(View.VISIBLE);
        headTitle.setVisibility(View.VISIBLE);
        headLeft.setText("返回");
        headTitle.setText("登录");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_two_stage_login;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_app_head_left:
                finish();
                break;
            case R.id.login_togo_main_tv:
                MainActivity.start(mActivity,null);
                break;
            case R.id.login_togo_register_tv:
                RegisterActivity.start(mActivity,null);
                break;
            case R.id.login_forget_passward_tv:
                RegisterActivity.start(mActivity,null);
                break;
            case R.id.web_term_service_content_tv:
                WebViewActivity.start(mActivity,"网页","https://www.baidu.com");
                break;
        }
    }
}
