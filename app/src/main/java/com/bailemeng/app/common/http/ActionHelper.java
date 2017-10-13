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
     */
    public static void test(final Activity activity, Callback callback){
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final Map<String, String> params = new HashMap<>();//getRequestParams(timeStamp);
        final Map<String, String> data = new HashMap<>();
        // 记录参数
        data.put("url","/video"+"/popularity/page");
        data.put("pageNum", "1");
        data.put("classifyId", "1");

        params.put("data", new Gson().toJson(data));

        OkHttpUtils.post()
                .url(getUrl())
                .tag(activity)
                .params(params)
                .build()
                .execute(callback);
    }
}
