package com.bailemeng.app.view.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bailemeng.app.R;
import com.bailemeng.app.base.RecyclerBaseAdapter;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.adapter
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/30
 */
public class SynopsisAdapter extends RecyclerBaseAdapter {
    private Activity activity;

    public SynopsisAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onBindHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SynopsisHolder(LayoutInflater.from(activity).inflate(R.layout.adapter_synopsis_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class SynopsisHolder extends RecyclerView.ViewHolder {
        public SynopsisHolder(View view) {
            super(view);
        }
    }
}
