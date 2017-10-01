package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.view.commonview.VideoVODView;
import com.bailemeng.app.view.home.fragment.CommentFragment;
import com.bailemeng.app.view.home.fragment.SynopsisFragment;
import com.bailemeng.app.widget.adapter.TabAdapter;
import com.tencent.rtmp.TXLivePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.activity
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/29
 */
public class VideoDetailsActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, VideoDetailsActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private ImageView detailsVideoVodIv;
    private FrameLayout detailsVideoVodFl;
    private VideoVODView videoVODView;
    private ViewPager detailsVodViewpager;
    private TabLayout tabLayout;
    private Toolbar mToolbar;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    private CommentFragment commentFragment;
    private SynopsisFragment synopsisFragment;
    private FloatingActionButton playStartFab;
    private CollapsingToolbarLayout coolapsingToolbar;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayoutState state;
    private ButtonBarLayout playButton;
    private ScaleAnimation animShow;
    private ScaleAnimation animHind;
    private boolean isFrist=true;
    private TextView playTopTv;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    public void initialView() {
        detailsVideoVodIv = (ImageView) findViewById(R.id.details_video_vod_iv);
        detailsVideoVodFl = (FrameLayout) findViewById(R.id.details_video_vod_fl);
        tabLayout = (TabLayout) findViewById(R.id.details_video_vod_tabs);
        detailsVodViewpager = (ViewPager) findViewById(R.id.details_video_vod_viewpager);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbar = (Toolbar) findViewById(R.id.details_video_vod_toolbar);
        playStartFab = (FloatingActionButton) findViewById(R.id.play_start_fab);
        playButton = (ButtonBarLayout) findViewById(R.id.playButton);
        playTopTv = (TextView) findViewById(R.id.play_top_tv);
        coolapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.coolapsing_toolbar);
        videoVODView=new VideoVODView(mActivity);
        detailsVideoVodFl.addView(videoVODView);
        animShow = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animHind = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animShow.setDuration(200);
        animHind.setDuration(200);
    }

    @Override
    public void initialListenter() {
        playStartFab.setOnClickListener(this);
        detailsVideoVodIv.setOnClickListener(this);
        playButton.setOnClickListener(this);
        videoVODView.setPlayingListener(new VideoVODView.OnPlayingListener() {
            @Override
            public void isPlaying(boolean isPlaying) {
                AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) coolapsingToolbar.getLayoutParams();
                if (isPlaying) {
                    mParams.setScrollFlags(0);//的时候AppBarLayout下的toolbar就不会随着滚动条折叠
                    coolapsingToolbar.setLayoutParams(mParams);
                } else {
                    mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);//的时候AppBarLayout下的toolbar会随着滚动条折叠
                    coolapsingToolbar.setLayoutParams(mParams);
                }
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        coolapsingToolbar.setTitle("EXPANDED");//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        playStartFab.startAnimation(animHind);
                        playStartFab.setVisibility(View.GONE);

                        coolapsingToolbar.setTitle("");//设置title不显示
                        playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮

                            if (isFrist) {
                                playStartFab.startAnimation(animShow);
                                playStartFab.setVisibility(View.VISIBLE);
                            }
                        }
                        coolapsingToolbar.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    @Override
    public void initialData() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        commentFragment=CommentFragment.newInstance();
        synopsisFragment=SynopsisFragment.newInstance();
        fragments.add(synopsisFragment);
        fragments.add(commentFragment);
        titles.add("简介");titles.add("评论");
        setSupportActionBar(mToolbar);
        detailsVodViewpager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments,titles));
        tabLayout.setupWithViewPager(detailsVodViewpager);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 继续
        videoVODView.playerResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 暂停
        videoVODView.playerPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoVODView.playerDestroy();
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        switch (v.getId()){
            case R.id.playButton:
            case R.id.details_video_vod_iv:
            case R.id.play_start_fab:
                if (isFrist) {
                    String flvUrl = "http://1253916064.vod2.myqcloud.com/eda2836cvodtransgzp1253916064/6e35407c9031868223240078260/v.f30.mp4";
                    int type = TXLivePlayer.PLAY_TYPE_VOD_MP4;
                    videoVODView.playStart(flvUrl, type);
                    isFrist=false;
                    playTopTv.setText("继续播放");
                } else {
                    videoVODView.playerResume();
                }
                AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) coolapsingToolbar.getLayoutParams();
                mParams.setScrollFlags(0);//的时候AppBarLayout下的toolbar就不会随着滚动条折叠
                coolapsingToolbar.setLayoutParams(mParams);

                playStartFab.startAnimation(animHind);
                playStartFab.setVisibility(View.GONE);
                detailsVideoVodFl.setVisibility(View.VISIBLE);
                detailsVideoVodIv.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
                break;
        }
    }
}
