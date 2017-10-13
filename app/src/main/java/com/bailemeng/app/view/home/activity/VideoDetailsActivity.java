package com.bailemeng.app.view.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.utils.ScreenUtil;
import com.bailemeng.app.utils.StatusBarUtil;
import com.bailemeng.app.view.commonview.VideoVODView;
import com.bailemeng.app.view.home.fragment.CommentFragment;
import com.bailemeng.app.view.home.fragment.SynopsisFragment;
import com.bailemeng.app.widget.adapter.TabAdapter;
import com.tencent.rtmp.TXLivePlayer;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.rtmp.TXLiveConstants.RENDER_ROTATION_LANDSCAPE;

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

    private FrameLayout detailsVideoVodFl;
    private VideoVODView videoVODView;
    private ViewPager detailsVodViewpager;
    private TabLayout tabLayout;
    private Toolbar mToolbar;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    private CommentFragment commentFragment;
    private SynopsisFragment synopsisFragment;
    private CollapsingToolbarLayout coolapsingToolbar;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayoutState state;
    private boolean isFrist=true;
    private TextView playTopTv;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    public void initialView() {
        detailsVideoVodFl = (FrameLayout) findViewById(R.id.details_video_vod_fl);
        tabLayout = (TabLayout) findViewById(R.id.details_video_vod_tabs);
        detailsVodViewpager = (ViewPager) findViewById(R.id.details_video_vod_viewpager);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbar = (Toolbar) findViewById(R.id.details_video_vod_toolbar);
        playTopTv = (TextView) findViewById(R.id.play_top_tv);
        coolapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.coolapsing_toolbar);
        videoVODView=new VideoVODView(mActivity);
        detailsVideoVodFl.addView(videoVODView);
    }

    @Override
    public void initialListenter() {
        playTopTv.setOnClickListener(this);
        videoVODView.setPlayingListener(new VideoVODView.OnPlayingListener() {
            @Override
            public void isPlaying(boolean isPlaying) {
                AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) coolapsingToolbar.getLayoutParams();
                if (isPlaying) {
                    mParams.setScrollFlags(0);//的时候AppBarLayout下的toolbar就不会随着滚动条折叠
                    coolapsingToolbar.setLayoutParams(mParams);
                    mToolbar.setVisibility(View.INVISIBLE);
                    mAppBarLayout.setSystemUiVisibility(View.INVISIBLE);
                } else {
                    mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);//的时候AppBarLayout下的toolbar会随着滚动条折叠
                    coolapsingToolbar.setLayoutParams(mParams);
                    mToolbar.setVisibility(View.VISIBLE);
                    mAppBarLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
            }
        });
        videoVODView.setPlayVideoStartListener(new VideoVODView.OnPlayVideoStartListener() {
            @Override
            public void succeed() {
                startPlay();
            }
        });
        videoVODView.setPlayVideoFullScreenListener(new VideoVODView.OnPlayVideoFullScreenListener() {
            @Override
            public void succeed() {
                if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    fullScreen();
                } else {
                    fromFullScreen();
                }
            }
        });
        videoVODView.setVideoTopShowListener(new VideoVODView.OnVideoTopShowListener() {
            @Override
            public void isShow(boolean b) {
                if (b){
                    mToolbar.setVisibility(View.VISIBLE);
                    mAppBarLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                } else {
                    mToolbar.setVisibility(View.INVISIBLE);
                    mAppBarLayout.setSystemUiVisibility(View.INVISIBLE);
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
                        coolapsingToolbar.setTitle("");//设置title不显示
                        playTopTv.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                            playTopTv.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        coolapsingToolbar.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    private void fullScreen(){
        //设置全屏,即隐藏状态栏（该栏为显示时间、电量）
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN; getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);   //获取当前屏幕高、宽

        WindowManager wm = mActivity.getWindowManager();
        int hight = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();   //改变程序中某控件大小，如SurfaceView（即surface）
        FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) detailsVideoVodFl.getLayoutParams();  //放大屏幕（满屏）
        linearParams.height = width;
        linearParams.width = hight;
        detailsVideoVodFl.setLayoutParams(linearParams);   //改变屏幕方向，如果是竖屏,则改为横屏
        if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        //隐藏其他控件（或者布局）,如Button
        detailsVodViewpager.setVisibility(View.GONE);//GONE、INVISIBLE、VISIBLE：隐藏（不占用布局）、不可见（但是占用布局）、可见
        tabLayout.setVisibility(View.GONE);
    }

    private void fromFullScreen(){
        //退出全屏,即显示状态栏（该栏为显示时间、电量）
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags&= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(params);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //改变程序中某控件大小，如SurfaceView（即surface）
        FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) detailsVideoVodFl.getLayoutParams();
        //放大屏幕（满屏）,XX为退出全屏后大小
        linearParams.height = ScreenUtil.dip2px(200);
        linearParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        detailsVideoVodFl.setLayoutParams(linearParams);   //改变屏幕方向，如果是横屏,则改为竖屏
        if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }  //显示其他控件（或者布局）,如Button
        detailsVodViewpager.setVisibility(View.VISIBLE);//GONE、INVISIBLE、VISIBLE：隐藏（不占用布局）、不可见（但是占用布局）、可见
        tabLayout.setVisibility(View.VISIBLE);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        detailsVodViewpager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments,titles));
        tabLayout.setupWithViewPager(detailsVodViewpager);
        mAppBarLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        StatusBarUtil.setTranslucent(mActivity,0);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 继续
//        videoVODView.playerResume();
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
            case R.id.play_top_tv:
                startPlay();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    fromFullScreen();
                } else {
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                fromFullScreen();
                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void startPlay(){
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

        videoVODView.playerStartGone();
        detailsVideoVodFl.setVisibility(View.VISIBLE);
        playTopTv.setVisibility(View.GONE);
        mToolbar.setVisibility(View.INVISIBLE);
        mAppBarLayout.setSystemUiVisibility(View.INVISIBLE);
    }
}
