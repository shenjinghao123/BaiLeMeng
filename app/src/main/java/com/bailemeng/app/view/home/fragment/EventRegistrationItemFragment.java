package com.bailemeng.app.view.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/9
 */
public class EventRegistrationItemFragment extends BaseAppFragment {

    public static EventRegistrationItemFragment newInstance() {
        return new EventRegistrationItemFragment();
    }

    private ImageView itemEvevtIv;
    private TextView itemEvevtTv;

    @Override
    public void initialView(View view) {
        itemEvevtIv=view.findViewById(R.id.item_evevt_iv);
        itemEvevtTv=view.findViewById(R.id.item_evevt_tv);
        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        int type = mBundle.getInt("type");
        String color = mBundle.getString("title");
        if (type==0){
            itemEvevtIv.setVisibility(View.VISIBLE);
            itemEvevtTv.setVisibility(View.GONE);
            itemEvevtIv.setBackgroundColor(Color.parseColor(color));
        } else if (type==1){
            itemEvevtIv.setVisibility(View.GONE);
            itemEvevtTv.setVisibility(View.VISIBLE);
            itemEvevtTv.setText("finish");
        }
    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_item_event_registration;
    }
}
