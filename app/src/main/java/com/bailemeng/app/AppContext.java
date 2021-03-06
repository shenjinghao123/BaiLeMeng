package com.bailemeng.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bailemeng.app.common.AppConfig;
import com.bailemeng.app.utils.SDcardUtil;
import com.bailemeng.app.utils.ScreenUtil;
import com.bailemeng.app.utils.SharedPreferencesUtil;
import com.bailemeng.app.utils.log.Logger;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/26
 */

public class AppContext extends Application {
    {

        PlatformConfig.setWeixin("wx4af595bda8c8a77d"/*"wx967daebe835fbeac"*/, "7fc6356e6e4d7c02ed19e848c4168232"/*"5bb696d9ccd75a38c8a0bfe0675559b3"*/);
        PlatformConfig.setQQZone("1106270066"/*"100424468"*/, "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    public static final String	  LOG_TAG = "BaiLeMeng";
    private static Context context;
    private static final String TAG = "AppContext";

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(newBase);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context =this;
        //SP
        SharedPreferencesUtil.init(this, AppConfig.SP_NAME);
        //初始化SD卡
        SDcardUtil.initDir();
        //屏幕尺寸
        ScreenUtil.init(this);
        //初始化打印日志
        Logger.init(LOG_TAG);
        //初始化Fresco
        Fresco.initialize(this/*, FrescoUtil.getImagePipelineConfig(this)*/);
        //友盟
        UMShareAPI.get(this);

    }

    public static Context getContext() {
        return context;
    }

}
