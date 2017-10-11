package com.hz.okhttp.base.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{
    public static final int CODE_CHECK_NOT_LAWFUL = 1003;//安全校验不合法
    public static final int CODE_NO_POWERS_VISIT_RES = 5004;//没权限访问该资源
    public static final int CODE_RES_NEVER_EXISTED = 5005;//资源内容不存在
    public static final int CODE_RES_HAD_SALE = 5006;//资源已下架
    public static final int CODE_NEED_LOGIN_VISIT = 1002;//需登录后访问
    public static final int CODE_SALES_RETURN = 1064;//发货
    public static final int CODE_MANEY_RETURN = 1065;//退货信息
    public static final int CODE_TAKE_DELIVERY = 1066;//退款信息
    public static final int CODE_NEED_LOGIN_LOGIN_AGAIN = 2016;//需登录后访问
    public static final String RELOGIN_ACTION = "action_login";//需要登录的action
    public static final String RELOGIN_ACTION_CODE_PARAMTER = "action_login_code";//需要登录的action
    public static final String RELOGIN_ACTION_MESSAGE_PARAMTER = "action_login_message";//需要登录的提示语key
    public static final String READED_RELOGIN_ACTION = "action_readed";//需要查询红点的action
    public static final String READED_ACTION_CODE_PARAMTER = "action_readed_code";//需要查询红点的action
    protected static final int SUCCESS_CODE = 0000;
    protected static final int HTTP_ERROR_CODE = 8888;
    protected static final int DEFAULT_ERROR_CODE = -1;
    protected static final String HTTP_ERROR_MSG = "网络异常,稍后请重试";
    protected static final String DEFAULT_ERROR_MSG = "服务器数据返回异常";
    protected static final String DATA_PRASE_ERROR_MSG = "数据解析异常";
    protected static final String KEY_CODE = "code";
    protected static final String KEY_OBJECT = "data";
    protected static final String KEY_MESSAGE = "msg";

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }
}
