package com.bailemeng.app.view.mine.fragment;

import android.view.View;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.mine.fragment
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */
public class MineFragment extends BaseAppFragment {

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    private TextView titleTv;

    @Override
    public void initialView(View view) {
        titleTv = view.findViewById(R.id.tv_app_head_center);
    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText("我的");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_mine;
    }
}
