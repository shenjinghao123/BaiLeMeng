package com.bailemeng.app.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：阅读器
 * 类描述：动态设置margin工具类
 * 创建人：张栋
 * 创建时间 2016/12/02 11:08
 */

public class MarginUtil {

    /**
     * 动态设置margin
     * @param v view
     * @param l 左部
     * @param t 顶部
     * @param r 右部
     * @param b 底部
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
        {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
