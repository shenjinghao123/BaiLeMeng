package com.bailemeng.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.common.http.ActionHelper;
import com.bailemeng.app.common.http.TextCallback;

import java.lang.reflect.Type;

/**
 * Created by test1234 on 2017/6/22.
 */

public class TestActivity extends Activity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, TestActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initView();
    }

    private TextView testTv,contentTv;

    private void initView() {
        testTv = (TextView) findViewById(R.id.test_tv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        testTv.setText("测试");
        testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHttp();
            }
        });
    }

    private void onHttp() {
        ActionHelper.test(this,new  CallBack());
    }

    class CallBack extends TextCallback {

        @Override
        public void onSuccessed(String message) {
            contentTv.setText(message);
        }

        @Override
        public void onErrored(int code, String message) {
            contentTv.setText(code+"-----"+message);
        }
    }

}
