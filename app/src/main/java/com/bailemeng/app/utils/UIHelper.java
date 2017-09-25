package com.bailemeng.app.utils;

import android.app.Activity;
import android.content.res.Configuration;

/**
 * 项目名称：appmaster
 * 包名称：com.dracom.android.basekit.util
 * 类描述：UI界面
 * 创建人：yangxd
 * 创建时间：2016/5/31 9:49
 */
public class UIHelper {

    private static long lastClickTime;

    /**
     * 判断是否快速点击多次
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    // 获取屏幕是否竖屏
    public static boolean getScreenOrientation(Activity activity) {
        return activity.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT;
    }
}
