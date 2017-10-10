package com.bailemeng.app.main.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.utils.ToastUtil;
import com.bailemeng.app.widget.umeng.UmThirdPartyUtil;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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

    private TableRow weixinGoTr,qqGoTr,phoneGoTr;
    private TextView loginCancelTv;

    @Override
    public void initialView() {
        weixinGoTr = (TableRow) findViewById(R.id.login_weixin_go);
        qqGoTr = (TableRow) findViewById(R.id.login_qq_go);
        phoneGoTr = (TableRow) findViewById(R.id.login_phone_go);
        loginCancelTv = (TextView) findViewById(R.id.login_cancel_tv);
    }

    @Override
    public void initialListenter() {
        weixinGoTr.setOnClickListener(this);
        qqGoTr.setOnClickListener(this);
        phoneGoTr.setOnClickListener(this);
        loginCancelTv.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        //Android6.0权限适配
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.login_weixin_go:
                new UmThirdPartyUtil(mActivity).getPlatformInfo(SHARE_MEDIA.WEIXIN,new OnLaginAuthListener());
                break;
            case R.id.login_qq_go:
                new UmThirdPartyUtil(mActivity).getPlatformInfo(SHARE_MEDIA.QQ,new OnLaginAuthListener());
                break;
            case R.id.login_phone_go:
                LoginTwoStageActivity.start(mActivity,null);
                break;
            case R.id.login_cancel_tv:
                finish();
                break;
        }
    }

    class OnLaginAuthListener implements UmThirdPartyUtil.OnAuthListener{

        @Override
        public void onSuccess(SHARE_MEDIA share_media, Map<String, String> map) {
            ToastUtil.showToast(mActivity,map.toString());
        }

        @Override
        public void onError() {

        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
