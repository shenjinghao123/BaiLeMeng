package com.bailemeng.app.view.home.adapter;

import android.content.Context;
import android.view.View;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseHolderAdapter;

import java.util.List;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.adapter
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/26
 */
public class HomeItemAdapter extends BaseHolderAdapter<String> {
    public HomeItemAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getContentView(int position) {
        return R.layout.adapter_home_grid_item;
    }

    @Override
    public void onInitView(View view, int position) {

    }
}
