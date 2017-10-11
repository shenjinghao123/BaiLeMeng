package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.activity
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/10
 */
public class SearchVideoActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, SearchVideoActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private ImageView searchBackIv;

    @Override
    public void initialView() {
        searchBackIv= (ImageView) findViewById(R.id.search_back_iv);
    }

    @Override
    public void initialListenter() {
        searchBackIv.setOnClickListener(this);
    }

    @Override
    public void initialData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search_video;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.search_back_iv:
                finish();
                break;
        }
    }
}
