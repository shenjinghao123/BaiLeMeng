package com.bailemeng.app.common.http;

import android.app.Activity;

import com.bailemeng.app.utils.DataUtil;
import com.google.gson.Gson;
import com.hz.okhttp.base.OkHttpUtils;
import com.hz.okhttp.base.callback.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test1234 on 2017/6/9.
 */

public class ActionHelper extends BaseActionHelper {
    /**
     * 登录请求
     *
     * @param activity
     * @param mobile
     * @param password
     * @param method
     * @param json
     */
    public static void login(final Activity activity, String method, final String mobile, final String password, Map<String, String> json, Callback callback){
        callback.setContext(activity.getApplicationContext());
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final Map<String, String> params = getRequestParams(timeStamp);
        final Map<String, String> data = new HashMap<>();
        // 记录参数
        data.put("service", getLoginUrl()+"signIn");
        data.put("token", "");
        data.put("method", method);
        data.put("isSign", "n");
        if (!DataUtil.isEmpty(mobile)) data.put("mobile", mobile);
        if (!DataUtil.isEmpty(password)) data.put("password", password);
        if (!DataUtil.isEmpty(json)) data.put("info",new Gson().toJson(json));

        params.put("content", getContent(data,AES_KEY));
        params.put("sign", getSign(params,APPSECRET));

        OkHttpUtils.post()
                .url(getPersonUrl())
                .tag(activity)
                .params(params)
                .build()
                .execute(callback);
    }
}
