package com.bailemeng.app.main.activity;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.classic.android.utils.WeakHandler;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.main.activity
 * 描    述:
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/12
 */
public class StartActivity extends BaseAppActivity {
    HanderRunnable runnable=new HanderRunnable();
    private static int SPLASH_TIME_OUT = 2000;
    WeakHandler handler=new WeakHandler();
    @Override
    public void initialView() {

    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_start;
    }

    class HanderRunnable implements Runnable{

        @Override
        public void run() {
            startNextPage();
        }
    }

    private void startNextPage() {
//        isUpgrade = ArtistLogic.getVersionNum() != AppConfig.VERSION_CODE;
        MainActivity.start(mActivity,null);
        finish();
    }

}
