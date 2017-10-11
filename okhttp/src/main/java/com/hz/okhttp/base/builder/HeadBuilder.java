package com.hz.okhttp.base.builder;


import com.hz.okhttp.base.OkHttpUtils;
import com.hz.okhttp.base.request.OtherRequest;
import com.hz.okhttp.base.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
