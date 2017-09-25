package com.bailemeng.app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;


public class ScreenUtil
{
	private static final String TAG	  = "ScreenUtil";
	
	private static double		RATIO = 0.85;
	
	public static int			screenWidth;
	public static int			screenHeight;
	public static int			screenMin;			 // 宽高中，小的一边
	public static int			screenMax;			 // 宽高中，较大的值
	public static float			scale;				 //屏幕宽高比
	
	public static float			density;
	public static float			scaleDensity;
	public static float			xdpi;
	public static float			ydpi;
	public static int			densityDpi;
	
	public static int			dialogWidth;
	public static int			statusbarheight;
	public static int			navbarheight;
	
	public static int dip2px(float dipValue) {
		return (int) (dipValue * density + 0.5f);
	}
	
	public static int px2dip(float pxValue) {
		return (int) (pxValue / density + 0.5f);
	}

	public static int sp2px(float spValue) {
		return (int) (spValue * scaleDensity + 0.5f);
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}


	public static int getDialogWidth() {
		dialogWidth = (int) (screenMin * RATIO);
		return dialogWidth;
	}
	
	public static void init(Context context) {
		if (null == context) { return; }
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
		scale = (float ) screenWidth / (float) screenHeight;
		density = dm.density;
		scaleDensity = dm.scaledDensity;
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;
		densityDpi = dm.densityDpi;
		
		Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density + "scaleDensity="+scaleDensity+"densityDpi="+densityDpi+"scale=" + scale);
	}
	
	public static void GetInfo(Context context) {
		if (null == context) { return; }
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
		screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
		density = dm.density;
		scaleDensity = dm.scaledDensity;
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;
		densityDpi = dm.densityDpi;
		statusbarheight = getStatusBarHeight(context);
		navbarheight = getNavBarHeight(context);
		Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
	}
	
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try
		{
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return sbar;
	}
	
	public static int getNavBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) { return resources.getDimensionPixelSize(resourceId); }
		return 0;
	}
}
