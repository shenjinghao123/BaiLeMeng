package com.bailemeng.app.common;

/**
 * 项目名称：BaiLeMeng
 * 包名称：com.bailemeng.app.common
 * 类描述：公共配置文件
 * 创建人：shenjinghao
 * 创建时间：2016/5/7 20:01
 */
public class AppConfig {

    //sp文件
    public static final String SP_NAME = "bailemeng_sp";

    public static final int PAGE_SIZE = 10;

    //请求头
    public static final String CONTENTTYPE = "text/plain;charset=UTF-8";
    public static final String UPLOAD_CONTENTTYPE = "application/octet-stream";
    public static final String CHARTSET = "UTF-8";

    //key
    public static final String APPID = "bdx415e987a";
    public static final String APPSECRET = "8deb7199-7545-kjf1-94c4-4cfb57a2ebac";
    public static final String AES_KEY = "S25m1hm4jN13u0D9";

    //type
    public static final String FROM_SOURCE = "1";//1=手机客户端 2=web版 3=wap
    public static final String OS_TYPE = "1";//1=android;2=ios;3=pad;4=ipad;5=wap

    //版本信息
    public static final String VERSION = "1.0.0";//版本
    public static final int VERSION_CODE = 1000;//新版本号

    //文件类型
    public static final String FILE_APK = ".apk";
    public static final String FILE_PDF = ".pdf";
    public static final String FILE_JPG = ".jpg";
}
