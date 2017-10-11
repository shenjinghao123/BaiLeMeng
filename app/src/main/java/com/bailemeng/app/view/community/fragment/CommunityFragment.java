package com.bailemeng.app.view.community.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.base.RecyclerBaseAdapter;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.community.fragment
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */
public class CommunityFragment extends BaseAppFragment {

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    private TextView titleTv;
    private RecyclerView communityRv;

    @Override
    public void initialView(View view) {
        titleTv = view.findViewById(R.id.tv_app_head_center);
        communityRv = view.findViewById(R.id.community_message_rv);
    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText("社区");
        communityRv.setLayoutManager(new LinearLayoutManager(mActivity));
        CommunityAdapter adapter=new CommunityAdapter();
        communityRv.setAdapter(adapter);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_community;
    }

    class CommunityAdapter extends RecyclerBaseAdapter<CommunityAdapter.CommunityHolder>{

        @Override
        protected void onBindHolder(CommunityHolder holder, int position) {

        }

        @Override
        public CommunityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommunityHolder(LayoutInflater.from(mActivity).inflate(R.layout.adapter_community_item, parent, false));
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
}
