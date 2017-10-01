package com.bailemeng.app.view.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.view.home.adapter.SynopsisAdapter;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/30
 */
public class SynopsisFragment extends BaseAppFragment {

    public static SynopsisFragment newInstance() {
        return new SynopsisFragment();
    }

    private RecyclerView synopsisListRv;

    @Override
    public void initialView(View view) {
        synopsisListRv=view.findViewById(R.id.synopsis_list_rv);
    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        synopsisListRv.setLayoutManager(new LinearLayoutManager(mActivity));
        SynopsisAdapter adapter=new SynopsisAdapter(mActivity);
        synopsisListRv.setAdapter(adapter);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_synopsis;
    }
}
