package com.bailemeng.app.view.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */
public class HomeItemFragment extends BaseAppFragment {

    public static HomeItemFragment newInstance() {
        return new HomeItemFragment();
    }

    @Override
    public void initialView(View view) {
        TextView mTextView = (TextView) view.findViewById(R.id.textview);

        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        String title = mBundle.getString("arg");

        mTextView.setText(title);

    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_item;
    }
}
