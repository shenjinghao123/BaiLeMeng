package com.bailemeng.app.common.http;

import android.util.Log;

import com.bailemeng.app.AppContext;
import com.bailemeng.app.common.AppConfig;
import com.hz.okhttp.base.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;


/**
 * 通用对象解析
 * <pre>使用Gson进行解析</pre>
 *
 * @author 续写经典
 * @date 2015/12/18
 */
public abstract class TextCallback extends StringCallback {

    @Override
    public void onResponse(String data, int id) {
        String json=AES.AES_Decrypt(AppConfig.AES_KEY,data);
        try {
            if (json==null||json.equals("null")||json.length()==0) {
                onErrored(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
                return;
            }
            Log.i("Artist",json);
            final int code = new JSONObject(json).getInt(KEY_CODE);
            final String content = new JSONObject(json).getString(KEY_OBJECT);
            final String msg = new JSONObject(json).getString(KEY_MESSAGE);
            if (code == SUCCESS_CODE) {
                onSuccessed(content);
            }
//            else if(code==CODE_NEED_LOGIN_LOGIN_AGAIN){
//                BraodcastUtil.sendLoginBroadcast(AppContext.getContext(),code);
//            }
            else{
                onErrored(code, msg);
            }
        } catch (Exception e) {
            onErrored(DEFAULT_ERROR_CODE, DATA_PRASE_ERROR_MSG);
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onErrored(HTTP_ERROR_CODE,HTTP_ERROR_MSG);
    }

    //成功
    public abstract void onSuccessed(String message);

    //失败
    public abstract void onErrored(int code, String message);
}
