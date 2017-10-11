package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.base.BaseHolderAdapter;
import com.bailemeng.app.utils.ToastUtil;
import com.bailemeng.app.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.activity
 * 描    述: 活动列表页
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/10
 */
public class EventDetailsActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, EventDetailsActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private MyListView evevtListMlv;
    private TextView headTitle;
    private ImageView headLeft;
    private SwipeRefreshLayout swipeRefresh;
    private ScrollView scrollView;
    private EventListAdapter adapter;
    private List<String> list=new ArrayList<>();

    @Override
    public void initialView() {
        headLeft = (ImageView) findViewById(R.id.iv_app_head_left);
        headTitle = (TextView) findViewById(R.id.tv_app_head_center);
        evevtListMlv= (MyListView) findViewById(R.id.evevt_list_mlv);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.event_swipe_refresh);
        scrollView = (ScrollView) findViewById(R.id.event_scroll_view);
    }

    @Override
    public void initialListenter() {
        headLeft.setOnClickListener(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                List<String> addList = new ArrayList<String>();
                addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");
                list.addAll(addList);
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 判断 scrollView 当前滚动位置在顶部
                if(scrollView.getScrollY() == 0){
                }

                // 判断scrollview 滑动到底部
                // scrollY 的值和子view的高度一样，这人物滑动到了底部
                if (scrollView.getChildAt(0).getHeight() - scrollView.getHeight()
                        == scrollView.getScrollY()){
                    ToastUtil.showToast(mActivity,"滑动到底部");
                    List<String> addList = new ArrayList<String>();
                    addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");addList.add("");
                    list.addAll(addList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    @Override
    public void initialData() {
        headLeft.setVisibility(View.VISIBLE);
        headTitle.setVisibility(View.VISIBLE);
        headTitle.setText("活动列表");
        list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");
        adapter=new EventListAdapter(mActivity,list);
        evevtListMlv.setAdapter(adapter);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_event_details;
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        switch (v.getId()){
            case R.id.iv_app_head_left:
                finish();
                break;
        }
    }

    class EventListAdapter extends BaseHolderAdapter{

        public EventListAdapter(Context context, List list) {
            super(context, list);
        }

        @Override
        public int getContentView(int position) {
            return R.layout.adapter_event_details_list;
        }

        @Override
        public void onInitView(View view, int position) {

        }
    }
}
