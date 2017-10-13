package com.bailemeng.app.common.http;

import com.bailemeng.app.common.AppConfig;
import com.google.gson.Gson;
import com.hz.okhttp.utils.DESUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by test1234 on 2017/6/12.
 */

public class BaseActionHelper {
    //请求头
    public static final String CONTENTTYPE = AppConfig.CONTENTTYPE;
    public static final String UPLOAD_CONTENTTYPE = AppConfig.UPLOAD_CONTENTTYPE;
    public static final String CHARTSET = AppConfig.CHARTSET;

    //key
    public static String APPSECRET = AppConfig.APPSECRET;//MD5 key
    public static String AES_KEY = AppConfig.AES_KEY; //公用秘钥Key

    protected static final String FROM_SOURCE = AppConfig.FROM_SOURCE;//1=手机客户端 2=web版 3=wap
    protected static final String OS_TYPE = AppConfig.OS_TYPE;//1=android;2=ios;3=pad;4=ipad;5=wap
//    private static String URL_R = "http://192.168.0.86:8080/xshop_sgw_api/member/submit.do"; //
////    private static String URL_R = "http://192.168.0.111/xshop_sgw_api/member/submit.do"; //
//    private static String URL_O = "http://192.168.0.111/xshop_sgw_api/member/submit.do"; //
    //测试
//    private static String URL_R = "http://liqone.s1.natapp.cc/xshop_sgw_api/member/submit.do"; //
//    private static String URL_O = "http://liqone.s1.natapp.cc/xshop_sgw_api/member/submit.do"; //

    private static String URL = "https://api.ibailemeng.com/api/json";

    private static String URL_R = "http://www.artistmarket.cn:8080/xshop_sgw_api/member/submit.do";
    private static String URL_O = "http://www.artistmarket.cn:8080/xshop_sgw_api/member/submit.do";

    private static String LOGIN_URL = "AppLoginController."; //登陆
    private static String PRODUCT_URL = "AppProductController."; //
    private static String ORDER_URL = "AppOrderController."; //
    private static String PAYMENT_URL = "AppPaymentController."; //
    private static String ACCOUNT_URL = "AppAccountController."; //
    private static String WITHDRAW_URL = "AppWithdrawController."; //
    private static String FOOTPRINT_URL = "AppFootprintController."; //

    private static boolean isCommKey = false;//共有key使用参数，用来处理userId值

    /**
     * 获取接口名
     *
     * @return
     */
    public static String getLoginUrl() {
        return LOGIN_URL;
    }
    public static String getProductUrl() {
        return PRODUCT_URL;
    }
    public static String getOrderUrl() {
        return ORDER_URL;
    }
    public static String getPaymentUrl() {
        return PAYMENT_URL;
    }
    public static String getAccountUrl() {
        return ACCOUNT_URL;
    }
    public static String getWithdrawUrl() {
        return WITHDRAW_URL;
    }
    public static String getFootprintUrl() {
        return FOOTPRINT_URL;
    }

    /**
     * 获取接口url
     *
     * @return
     */
    public static String getPersonUrl() {
        return URL_R;
    }
    public static String getAppOrderUrl() {
        return URL_O;
    }

    public static String getUrl(){
        return URL;
    }

    protected static Map<String, String> getHeaders() {
        final HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Chartset", CHARTSET);
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN,en-US");
        return headerMap;
    }

//    //公共key， value参数
//    protected static String getCommKeyValue(Map<String, String> data, String key, String timeStamp){
//        isCommKey = true;
//        return getJsonValue(data, key, timeStamp);
//    }
//
//    //私有key， value参数
//    protected static String getValue(Map<String, String> data, String key, String timeStamp) {
//        isCommKey = false;
//        return getJsonValue(data, key, timeStamp);
//    }

    //content参数
    protected static String getContent(Map<String, String> data, String key) {
        return AES.AES_Encrypt(key, new Gson().toJson(data));
    }

    //sign参数
    protected static String getSign(Map<String, String> params, String key){
        return BasicCoder.signHmacRequest(params, key, BasicCoder.Hmac.HmacMD5);
    }

//    //value参数
//    protected static String getJsonValue(Map<String, String> data, String key, String timeStamp){
//        Map<String, Object> commonMap = getRequestParams(timeStamp);
//        data.put("timeStamp", String.valueOf(System.currentTimeMillis()));
//        commonMap.put("data", getDataString(data, key));
//        return new Gson().toJson(commonMap);
//    }

    //data请求参数
    protected static String getDataString(Map<String, String> data, String key) {
        String des = "";
        try {
            des = DESUtil.encrypt(new Gson().toJson(data), key);
        } catch (Exception e) {
//            Log.e("ActionHelp", e.getMessage());
        }
        return des;
    }

    protected static Map<String, String> getRequestParams(String timeStamp) {
        final HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", AppConfig.APPID);
        paramsMap.put("timeStamp", timeStamp);
        return paramsMap;
    }

//    protected static Map<String, String> getProductDataParams() {
//        Map<String, String> data = new HashMap<>();
//        if (AccountLogic.isLogin()) {
//            data.put("isSign", "n");
//            data=getProductDataParams(data);
//        } else {
//            data.put("isSign", "n");
//            data.put("method", "visitor");
//        }
//        return data;
//    }

//    protected static Map<String, String> getDataParams() {
//        Map<String, String> data = new HashMap<>();
//        if (AccountLogic.isLogin()) {
//            data.put("isSign", "y");
//            data=getDataParams(data);
//        } else {
//            data.put("isSign", "n");
//            data.put("method", "visitor");
//        }
//        return data;
//    }

//    protected static Map<String, String> getDataParams(Map<String, String> data) {
//        data.put("method", AccountLogic.getMethod());
//        if (AccountLogic.getMethod().equals("mobile")){
////            data.put("customerId", String.valueOf(AccountLogic.getCustomerId()));
//            data.put("loginPassword", AccountLogic.getLoginPwd());
//        } else if (AccountLogic.getMethod().equals("weixin")){
//            data.put("openId", AccountLogic.getOpenId());
//        } else if (AccountLogic.getMethod().equals("qq")){
//            data.put("uid", AccountLogic.getUid());
//        } else if (AccountLogic.getMethod().equals("weibo")){
//            data.put("weiboUid", AccountLogic.getWenboUid());
//        }
//        data.put("customerId", String.valueOf(AccountLogic.getCustomerId()));
//        data.put("token", AccountLogic.getToken());
//        return data;
//    }

//    protected static Map<String, String> getProductDataParams(Map<String, String> data) {
//        if (AccountLogic.getMethod().equals("mobile")){
//            data.put("customerId", String.valueOf(AccountLogic.getCustomerId()));
//            data.put("loginPassword", AccountLogic.getLoginPwd());
//            data.put("method", "visitor");
//        } else if (AccountLogic.getMethod().equals("weixin")){
//            data.put("openId", AccountLogic.getOpenId());
//            data.put("method", AccountLogic.getMethod());
//        } else if (AccountLogic.getMethod().equals("qq")){
//            data.put("uid", AccountLogic.getUid());
//            data.put("method", AccountLogic.getMethod());
//        } else if (AccountLogic.getMethod().equals("weibo")){
//            data.put("weiboUid", AccountLogic.getWenboUid());
//            data.put("method", AccountLogic.getMethod());
//        }
//        data.put("customerId", String.valueOf(AccountLogic.getCustomerId()));
//        data.put("token", AccountLogic.getToken());
//        return data;
//    }
//
//    protected static String getHashCode(String timeStamp) {
//        return MD5Util.digest(timeStamp + MD5_KEY);
//    }
}
