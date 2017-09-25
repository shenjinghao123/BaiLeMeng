package com.bailemeng.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * 日期操作工具类
 * 
 * @author 续写经典
 * @date 2015/11/3
 */
public final class DateUtil
{
	private DateUtil() {
	}
	
	public static final SimpleDateFormat FORMAT		 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	public static final SimpleDateFormat FORMAT2		 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	public static final SimpleDateFormat FORMAT_MOUTH = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
	public static final SimpleDateFormat FORMAT_YEAR = new SimpleDateFormat("yyyy", Locale.CHINA);
	public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm", Locale.CHINA);
	
	private static final Calendar c			 = Calendar.getInstance();
	
	public static int getYear() {
		return c.get(Calendar.YEAR);
	}
	
	public static int getMonth() {
		return c.get(Calendar.MONTH);
	}
	
	public static int getDay() {
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHours() {
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getMinutes() {
		return c.get(Calendar.MINUTE);
	}
	
	public static int getSeconds() {
		return c.get(Calendar.SECOND);
	}
	
	/**
	 * 获取N天前、N天后的 日期
	 *
	 * @param nowDate
	 *            当前日期;
	 * @param dayAddNum
	 *            正数：N天前，负数：N天后;
	 * @return
	 */
	public static Date getAddDay(Date nowDate, int dayAddNum) {
		
		Calendar tday = new GregorianCalendar();
		tday.setTime(nowDate);
		tday.add(Calendar.DAY_OF_MONTH, dayAddNum);
		Date preDate = tday.getTime();
		return preDate;
	}
	
	/**
	 * 获取N天前、N天后的 日期
	 * 
	 * @param nowDate
	 *            当前时间戳;
	 * @param dayAddNum
	 *            正数：N天前，负数：N天后;
	 * @return
	 */
	public static Date getAddDay(long nowDate, int dayAddNum) {
		return getAddDay(new Date(nowDate), dayAddNum);
	}
	
	/**
	 * 时间格式化
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static String formatDate(String format, Long time) {
		return formatDate(new SimpleDateFormat(format, Locale.CHINA), time);
	}
	
	/**
	 * 时间格式化
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static String formatDate(SimpleDateFormat format, Long time) {
		if (null == time || time <= 0)
		                               return "";
		return format.format(new Date(String.valueOf(time).length() == 13 ? time : time * 1000));
	}

	// strTime要转换的String类型的时间
	// formatType时间格式
	// strTime的时间格式和formatType的时间格式必须相同
	public static long stringToLong(SimpleDateFormat formatType, String strTime)
			throws ParseException {
		Date date = stringToDate(formatType,strTime); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
	// HH时mm分ss秒，
	// strTime的时间格式必须要与formatType的时间格式相同
	public static Date stringToDate(SimpleDateFormat formatType, String strTime)
			throws ParseException {
		Date date = null;
		date = formatType.parse(strTime);
		return date;
	}

	// date要转换的date类型的时间
	public static long dateToLong(Date date) {
		return date.getTime();
	}

	/**
	 * 将秒转换为（ 分：秒 格式）
	 * 
	 * @param time
	 *            单位：秒
	 * @return
	 */
	public static String getTimeFromInt(int time) {
		if (time <= 0)
		               return "00:00";
		int secondnd = time / 60;
		int million = time % 60;
		String f = secondnd >= 10 ? String.valueOf(secondnd) : "0" + String.valueOf(secondnd);
		String m = million >= 10 ? String.valueOf(million) : "0" + String.valueOf(million);
		return f + ":" + m;
	}
	
	/**
	 * 根据月份获得最大天数
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最大天数
	 */
	public static int getMaxDayByMonth(int year, int month) {
		Calendar time = Calendar.getInstance();//使用默认时区和语言环境获得一个日历
		//注意：在使用set方法之前，必须先调用clear（），否则很多信息会继承自系统当前的时间
		time.clear();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month);//注意Calendar对象默认一月是为零的
		int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//获得本月份的天数
		return day;
	}
	
	/**
	 * 根据日期获得星期
	 */
	public static String getWeek(Date d) {
		final String dayNames[] = {
		                            "星期日",
		                            "星期一",
		                            "星期二",
		                            "星期三",
		                            "星期四",
		                            "星期五",
		                            "星期六"
		};
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
		                   dayOfWeek = 0;
		return (dayNames[dayOfWeek]);
	}
	
	/**
	 * 时间格式化（刚刚、几分钟前、几小时前、昨天、前天、年-月-日）
	 * 
	 * @param time
	 * @return
	 */
	public static String getShortTime(long time) {
		String shortstring = "";
		if (time == 0)
		               return shortstring;
		long now = Calendar.getInstance().getTimeInMillis();
		long datetime = (now - time) / 1000;
		if (datetime > 2 * 24 * 60 * 60)
		{
			shortstring = FORMAT_DATE.format(new Date(time));
		}
		else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60))) == 2)
		{
			shortstring = "前天";
		}
		else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60))) == 1)
		{
			shortstring = "昨天";
		}
		else if (datetime > 60 * 60)
		{
			shortstring = (int) (datetime / (60 * 60)) + "小时前";
		}
		else if (datetime > 60)
		{
			shortstring = (int) (datetime / (60)) + "分钟前";
		}
		else
		{
			shortstring = "刚刚";
		}
		return shortstring;
	}
	
	/**
	 * 时间格式化(秒，分，小时，天)
	 * 
	 * @param time
	 * @return
	 */
	public static String getShortTime2(long time) {
		String shortstring = "";
		if (time == 0)
		               return shortstring;
		long now = Calendar.getInstance().getTimeInMillis();
		long datetime = (now - time) / 1000; // 秒
		if (datetime > 24 * 60 * 60)
		{
			shortstring = (int) (datetime / (24 * 60 * 60)) + "天";
		}
		else if (datetime > 60 * 60)
		{
			shortstring = (int) (datetime / (60 * 60)) + "小时";
		}
		else if (datetime > 60)
		{
			shortstring = (int) (datetime / (60)) + "分钟";
		}
		else
		{
			shortstring = (int) datetime + "秒";
		}
		return shortstring;
	}
	
	/**
	 * 时间格式化(秒，分，小时，昨天 ，前天 xxxx-xx-xx xx:xx)
	 * 
	 * @param time
	 * @return
	 */
	public static String getShortTime3(long time) {
		String shortstring = "";
		if (time == 0) { return shortstring; }
		long now = Calendar.getInstance().getTimeInMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTimeInMillis(now);
		
		long datetime = (now - time) / 1000; // 秒
		if(datetime < 6){
			shortstring = "刚刚";
		}
		else if (datetime < 60)
		{
			shortstring = datetime + "秒前";
		}
		else if (datetime < 60 * 60)
		{
			shortstring = (datetime / 60) + "分钟前";
		}
		else if (datetime < 60 * 60 * 24)
		{
			if (day == c.get(Calendar.DAY_OF_MONTH))
			{
				shortstring = (datetime / (60 * 60)) + "小时前";
			}
			else
			{
				shortstring = "昨天";
			}
		}
        else if (datetime< 60 * 60 * 48){
            if(day == c.get(Calendar.DAY_OF_MONTH)-1){
                shortstring = "昨天";
            }else {
                shortstring = "前天";
            }
        }else {
            shortstring = FORMAT2.format(time);
        }
		return shortstring;
	}

	/**
	 * 时间格式化(秒，分，小时，今天 ，昨天 xxxx-xx-xx xx:xx)
	 *
	 * @param time
	 * @return
	 */
	public static String getShortTime4(long time) {
		String shortstring = "";
		if (time == 0) { return shortstring; }
		long now = Calendar.getInstance().getTimeInMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTimeInMillis(now);
		long datetime = (now - time) / 1000; // 秒
		if (datetime < 60 * 60 * 24)
		{
			if (day == c.get(Calendar.DAY_OF_MONTH))
			{
				shortstring = "今天 "+FORMAT_TIME.format(time);
			}
			else
			{
				shortstring = "昨天 "+FORMAT_TIME.format(time);
			}
		}
		else if (datetime< 60 * 60 * 48){
			if(day == c.get(Calendar.DAY_OF_MONTH)-1){
				shortstring = "昨天 "+FORMAT_TIME.format(time);
			}else {
				shortstring = FORMAT2.format(time);
			}
		}else {
			shortstring = FORMAT2.format(time);
		}
		return shortstring;
	}

	/**
	 * HH:mm/ yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFromNow(Date date) {
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		int yearNow;
		c.setTime(new Date());
		yearNow = c.get(Calendar.YEAR);
		String sTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sTime = sdf.format(date);
		if (sTime.startsWith("0"))
		{
			sTime = sTime.substring(1);
		}
		if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month && day == c.get(Calendar.DAY_OF_MONTH)) { return sTime; }
		
		c.roll(Calendar.DATE, -1);
		if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month && day == c.get(Calendar.DAY_OF_MONTH)) { return "昨天"; }
		
		c.roll(Calendar.DATE, -1);
		if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month && day == c.get(Calendar.DAY_OF_MONTH)) { return "前天"; }
		month++;
		
		return year + "年" + month + "月" + day + "日" + " " + sTime;
	}
	
	/**
	 * 获取当前日期上一周的开始日期 （周日）
	 */
	public static String previousWeekByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - s);//根据日历的规则，给当前日期减往星期几与一个星期第一天的差值
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		String imptimeBegin = sdf.format(cal.getTime());
		//      System.out.println("所在周星期日的日期："+imptimeBegin);
		return imptimeBegin;
	}
	
	/**
	 * 获取当前日期上一周的结束日期 （周六）
	 */
	public static String previousWeekEndDayByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() + (6 - s));
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		String imptimeBegin = sdf.format(cal.getTime());
		//      System.out.println("星期六的日期："+imptimeBegin);
		return imptimeBegin;
	}
	
	/**
	 * 获取当前日期当前一周的开始日期 （周日）
	 */
	public static String getCurrentWeekFirstDayByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - s);//根据日历的规则，给当前日期减往星期几与一个星期第一天的差值
		
		String imptimeBegin = sdf.format(cal.getTime());
		//  System.out.println("所在周星期日的日期："+imptimeBegin);
		return imptimeBegin;
	}
	
	/**
	 * 获取当前日期当前一周的结束日期 （周六）
	 */
	public static String getCurrentWeekEndDayByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() + (6 - s));
		
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
	}
	
	/**
	 * 返回上一个月的第一天
	 *
	 * @return 20120201
	 */
	public static String previousMonthByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 2, 1);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		String imptimeBegin = sdf.format(cal.getTime());
		//      System.out.println(imptimeBegin);
		return imptimeBegin;
	}
	
	/**
	 * 返回下一个月的第一天
	 *
	 * @return 20120401
	 */
	public static String nextMonthByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		String imptimeBegin = sdf.format(cal.getTime());
		//      System.out.println(imptimeBegin);
		return imptimeBegin;
	}
	
	/**
	 * 返回当前月的第一天
	 *
	 * @return 20120101
	 */
	public static String getCurrentMonthFirstDayByDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date newdate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(newdate);
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
	}

	/**
	 * 判断两个时间是否在同一天
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isInOneDay(long time1, long time2) {
		String str1 = FORMAT_DATE.format(new Date(time1));
		String str2 = FORMAT_DATE.format(new Date(time2));
		return str1.equals(str2);
	}

	/**
	 * 判断两个时间是否小于15分钟
	 *
	 * @return
	 */
	public static boolean isIn15Min(long time) {
		long between= System.currentTimeMillis()-time;
		return between<=15*60*1000;
	}

	public static String getYear(long time) {
		Date data = new Date(time);
		return FORMAT_YEAR.format(data);
	}

	public static String getMouth(String time) {
		Date data = null;
		try {
			data = FORMAT.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			if (data==null) {
				try {
					data = FORMAT_DATE.parse(time);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		return FORMAT_MOUTH.format(data);
	}

	public static String getDate(String time) {
		Date data = null;
		try {
			data = FORMAT.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			if (data==null) {
				try {
					data = FORMAT_DATE.parse(time);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		return FORMAT_DATE.format(data);
	}
	public static boolean getTime(String time, String time1){
		boolean isTime = false;
		try {
			isTime = FORMAT_DATE.parse(time).after(FORMAT_DATE.parse(time1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isTime;
	}
}
