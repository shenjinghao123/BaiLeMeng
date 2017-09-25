package com.bailemeng.app.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast工具类
 * @author 续写经典
 * @date 2015/11/3
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, int resId){
        makeText(context.getApplicationContext(), context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String text){
        makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, int resId) {
        makeText(context.getApplicationContext(), context.getString(resId), Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context, String text) {
        makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
    }

    private static void makeText(Context context, String message, int duration){
        if(toast == null){
            toast = Toast.makeText(context, message, duration);
        }
        else{
            toast.setText(message);
            toast.setDuration(duration);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
