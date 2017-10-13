package com.bailemeng.app.view.community.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bailemeng.app.R;
import com.bailemeng.app.base.RecyclerBaseAdapter;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.community.adapter
 * 描    述:
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/11
 */
public
class CommunityAdapter extends RecyclerBaseAdapter<CommunityAdapter.CommunityHolder> {

    private Activity activity;

    public CommunityAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onBindHolder(CommunityHolder holder, int position) {

    }

    @Override
    public CommunityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommunityHolder(LayoutInflater.from(activity).inflate(R.layout.adapter_community_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class CommunityHolder
            extends
            RecyclerView.ViewHolder
    {
        CommunityHolder(View itemView) {
            super(itemView);
        }
    }
}