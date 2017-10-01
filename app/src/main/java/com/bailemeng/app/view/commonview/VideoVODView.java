package com.bailemeng.app.view.commonview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.utils.ToastUtil;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.Locale;

import static com.tencent.rtmp.TXLiveConstants.PLAY_EVT_PLAY_BEGIN;
import static com.tencent.rtmp.TXLiveConstants.PLAY_EVT_PLAY_LOADING;
import static com.tencent.rtmp.TXLiveConstants.PLAY_EVT_PLAY_PROGRESS;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.commonview
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/29
 */
public class VideoVODView extends FrameLayout implements View.OnClickListener {

    private Context context;
    private View view;
    private TXCloudVideoView mPlayerView;
    private SeekBar videoSeekBar;
    private TextView videoProgressTime;
    private ImageView playIv;
    private TXLivePlayer mLivePlayer;
    private long mTrackingTouchTS = 0;
    private boolean mStartSeek = false;
    private RelativeLayout vodVideoPlayRl;
    private OnPlayingListener onPlayingListener;

    public VideoVODView(@Nullable Context context) {
        this(context,null);
    }

    public VideoVODView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoVODView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init(){
        //创建player对象
        mLivePlayer = new TXLivePlayer(context);
        initView();
        initEvevt();
        initData();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.view_video_vod,null);
        mPlayerView = (TXCloudVideoView) view.findViewById(R.id.vod_video_play_view);
        videoSeekBar = (SeekBar) view.findViewById(R.id.vod_video_seek_bar);
        videoProgressTime = (TextView) view.findViewById(R.id.vod_video_progress_time);
        playIv = (ImageView) view.findViewById(R.id.vod_video_play_iv);
        vodVideoPlayRl = (RelativeLayout) view.findViewById(R.id.vod_video_play_rl);
        addView(view);
    }

    private void initEvevt() {
        mPlayerView.setOnClickListener(this);
        playIv.setOnClickListener(this);
        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean bFromUser) {
                if (videoProgressTime != null) {
                    videoProgressTime.setText(String.format(Locale.CHINA, "%02d:%02d/%02d:%02d", (progress) / 60, (progress) % 60, (seekBar.getMax()) / 60, (seekBar.getMax()) % 60));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mStartSeek = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mLivePlayer != null) {
                    mLivePlayer.seek(seekBar.getProgress());
                }
                mTrackingTouchTS = System.currentTimeMillis();
                mStartSeek = false;
            }
        });
        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                //事件通知
                if (i==PLAY_EVT_PLAY_BEGIN) {//视频播放开始，如果有转菊花什么的这个时候该停了

                } else if (i==PLAY_EVT_PLAY_PROGRESS) {//视频播放进度，会通知当前进度和总体进度，仅在点播时有效
                    if (mStartSeek) {
                        return;
                    }
//                    if (mImageViewBg.isShown()) {
//                        mImageViewBg.setVisibility(View.GONE);
//                    }
                    int progress = bundle.getInt(TXLiveConstants.EVT_PLAY_PROGRESS);
                    int duration = bundle.getInt(TXLiveConstants.EVT_PLAY_DURATION);//单位为s
                    long curTS = System.currentTimeMillis();
                    // 避免滑动进度条松开的瞬间可能出现滑动条瞬间跳到上一个位置
                    if (Math.abs(curTS - mTrackingTouchTS) < 500) {
                        return;
                    }
                    mTrackingTouchTS = curTS;

                    if (videoSeekBar != null) {
                        videoSeekBar.setProgress(progress);
                    }
                    if (videoProgressTime != null) {
                        videoProgressTime.setText(String.format(Locale.CHINA, "%02d:%02d/%02d:%02d", (progress) / 60, progress % 60, (duration) / 60, duration % 60));
                    }

                    if (videoSeekBar != null) {
                        videoSeekBar.setMax(duration);
                    }
                } else if (i==PLAY_EVT_PLAY_LOADING) {//视频播放loading，如果能够恢复，之后会有BEGIN事件

                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
                //质量反馈
            }
        });
    }

    private void initData() {
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//铺满or适应
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);//画面旋转
        mLivePlayer.setAutoPlay(false);//设置点播是否startPlay后自动开始播放。默认自动播放
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
    }

    public void playerResume() {
        // 继续
        mLivePlayer.resume();
    }

    public void playerPause() {
        // 暂停
        mLivePlayer.pause();
    }

    public void playerDestroy() {
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mPlayerView.onDestroy();
    }

    public boolean isPlaying(){
        return mLivePlayer.isPlaying();
    }

    public void playStart(String url,int type){
        mLivePlayer.startPlay(url, type);
    }

    public void setFullScreen(int type){
        mLivePlayer.setRenderRotation(type);//画面旋转
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vod_video_play_iv:
                if (mLivePlayer.isPlaying()) {
                    playIv.setImageResource(R.drawable.record_start_press);
                    mLivePlayer.pause();
                    onPlayingListener.isPlaying(false);
                } else {
                    playIv.setImageResource(R.drawable.record_start);
                    mLivePlayer.resume();
                    onPlayingListener.isPlaying(true);
                }
                break;
            case R.id.vod_video_play_view:
                if (vodVideoPlayRl.getVisibility()==VISIBLE) {
                    vodVideoPlayRl.setVisibility(GONE);
                } else {
                    vodVideoPlayRl.setVisibility(VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vodVideoPlayRl.setVisibility(GONE);
                        }
                    }, 3000);
                }
                break;
        }
    }

    public interface OnPlayingListener{
        void isPlaying(boolean isPlaying);
    }

    public void setPlayingListener(OnPlayingListener onPlayingListener){
        this.onPlayingListener=onPlayingListener;
    }
}
