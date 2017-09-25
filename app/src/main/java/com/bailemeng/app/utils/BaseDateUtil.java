package com.bailemeng.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/5/30.
 */
public class BaseDateUtil {
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    /**
     * 时间格式化（刚刚、几分钟前、几小时前、昨天、前天、年-月-日）
     * @param time
     * @return
     */
    public static String getShortTime(long time) {
        String shortstring = "";
        if (time == 0)
            return shortstring;

        long now = Calendar.getInstance().getTimeInMillis();
        long datetime = (now - time) / 1000;
        if (datetime > 365 * 24 * 60 * 60) {
            shortstring = FORMAT_DATE.format(new Date(time));
        } else if(((int) (datetime / (24 * 60 * 60)))>=3){
            shortstring = FORMAT.format(new Date(time));
        }
        else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60)))==2){
            shortstring = "前天";
        } else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60)))==1){
            shortstring = "昨天";
        } else if (datetime > 60 * 60) {
            shortstring = (int) (datetime / (60 * 60)) + "小时前";
        } else if (datetime > 60) {
            shortstring = (int) (datetime / (60)) + "分钟前";
        } else {
            shortstring = "刚刚";
        }
        return shortstring;
    }
}
