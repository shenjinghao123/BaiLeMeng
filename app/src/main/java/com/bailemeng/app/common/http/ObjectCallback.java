package com.bailemeng.app.common.http;

import android.util.Log;

import com.bailemeng.app.AppContext;
import com.bailemeng.app.common.AppConfig;
import com.bailemeng.app.utils.DataUtil;
import com.google.gson.Gson;
import com.hz.okhttp.base.callback.StringCallback;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Call;


/**
 * 通用对象解析
 * <p>
 * <pre>
 * 使用Gson进行解析
 * </pre>
 *
 * @author 续写经典
 * @date 2015/12/18
 */
public abstract class ObjectCallback<T>
        extends
        StringCallback {

    @Override
    public void onResponse(String data, int id) {
        String json = AES.AES_Decrypt(AppConfig.AES_KEY, data);
        try {
            if (json == null || json.equals("null") || json.length() == 0) {
                onErrored(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
                return;
            }
            Log.i("LibRead", json);
            final int code = new JSONObject(json).getInt(KEY_CODE);
            final String content = new JSONObject(json).getString(KEY_OBJECT);
            final String msg = new JSONObject(json).getString(KEY_MESSAGE);
            if (code == SUCCESS_CODE) {
                if (DataUtil.isEmpty(content) || content.equals("[]") || content.equals("操作成功")) {
                    onSuccessed(null);
                } else {
                    T obj = new Gson().fromJson(content, getType());
                    onSuccessed(obj);
                }
//            } else if (CODE_NEED_LOGIN_LOGIN_AGAIN == code) { //请先登录
//                BraodcastUtil.sendLoginBroadcast(AppContext.getContext(), code);
////                onErrored(0, "");
            } else {
                onErrored(code, msg);
            }
        } catch (Exception e) {
            onErrored(DEFAULT_ERROR_CODE, e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onErrored(HTTP_ERROR_CODE, HTTP_ERROR_MSG);
    }

    //成功
    public abstract void onSuccessed(T response);

    //失败
    public abstract void onErrored(int code, String message);

    //设置要转换的对象类型  <br />
    public abstract Type getType();
}
