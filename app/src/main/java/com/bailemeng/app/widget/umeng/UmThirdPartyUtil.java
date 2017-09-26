package com.bailemeng.app.widget.umeng;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget.umeng
 * 创 建 人: 123
 * 创建时间: 2017/9/26
 */
public class UmThirdPartyUtil {
    private Activity activity;
    private UMShareAPI mShareAPI;

    public UmThirdPartyUtil(Activity activity) {
        this.activity = activity;
        mShareAPI = UMShareAPI.get(activity);
    }

    /*
    * 获取用户资料授权
    * */
    public void getPlatformInfo(SHARE_MEDIA shareMedia,final OnAuthListener onAuthListener){
        mShareAPI.getPlatformInfo(activity, shareMedia, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //授权开始的回调
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                onAuthListener.onSuccess(share_media,map);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                onAuthListener.onError();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                onAuthListener.onCancel();
            }
        });
    }

    public void doOauthVerify(SHARE_MEDIA shareMedia,final OnAuthListener onAuthListener){
        mShareAPI.doOauthVerify(activity, shareMedia, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //授权开始的回调
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                onAuthListener.onSuccess(share_media,map);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                onAuthListener.onError();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                onAuthListener.onCancel();
            }
        });

    }

    public interface OnAuthListener{
        void onSuccess(SHARE_MEDIA share_media, Map<String, String> map);
        void onError();
        void onCancel();
    }
}
