package com.bailemeng.app.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.bailemeng.app.R;
import com.bailemeng.app.tencent.videorecord.TCVideoRecordActivity;
import com.bailemeng.app.utils.ToastUtil;
import com.bailemeng.app.view.commonview.activity.VideoReleaseActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget.dialog
 * 创 建 人: 123
 * 创建时间: 2017/9/26
 */
public class ShadeBottomUploadDialog extends Dialog implements View.OnClickListener {

    public static final String RECORD_CONFIG_MAX_DURATION       = "record_config_max_duration";
    public static final String RECORD_CONFIG_MIN_DURATION       = "record_config_min_duration";
    public static final String RECORD_CONFIG_ASPECT_RATIO       = "record_config_aspect_ratio";
    public static final String RECORD_CONFIG_RECOMMEND_QUALITY  = "record_config_recommend_quality";
    public static final String RECORD_CONFIG_RESOLUTION         = "record_config_resolution";
    public static final String RECORD_CONFIG_BITE_RATE          = "record_config_bite_rate";
    public static final String RECORD_CONFIG_FPS                = "record_config_fps";
    public static final String RECORD_CONFIG_GOP                = "record_config_gop";

    private int mRecommendQuality = -1;
    private int mAspectRatio; // 视频比例
    private int mRecordResolution; // 录制分辨率
    private int mBiteRate = 1800; // 码率
    private int mFps = 20; // 帧率
    private int mGop = 3; // 关键帧间隔

    private Activity activity;
    private LinearLayout uploadShotLl,uploadGameLl,uploadVideoLl;

    public ShadeBottomUploadDialog(Activity activity) {
        super(activity, R.style.common_dialog_style);
        this.activity = activity;
        findViews();
        setListeners();
        Window window = getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.window_anim_style);
    }

    private void findViews() {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_shade_bottom_upload, null);
        uploadShotLl = view.findViewById(R.id.upload_shot_ll);
        uploadGameLl = view.findViewById(R.id.upload_game_ll);
        uploadVideoLl = view.findViewById(R.id.upload_video_ll);
        setContentView(view);
    }

    private void setListeners() {
        uploadShotLl.setOnClickListener(this);
        uploadGameLl.setOnClickListener(this);
        uploadVideoLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upload_shot_ll:
//                ToastUtil.showLongToast(activity,"拍摄");
                startVideoRecordActivity();
                break;
            case R.id.upload_game_ll:
                ToastUtil.showLongToast(activity,"参赛");
                break;
            case R.id.upload_video_ll:
                ToastUtil.showLongToast(activity,"上传");
                VideoReleaseActivity.start(activity,null);
                break;
        }
    }
    private void startVideoRecordActivity(){
        Intent intent = new Intent(activity, TCVideoRecordActivity.class);
        intent.putExtra(RECORD_CONFIG_MIN_DURATION, 5 * 1000);
        intent.putExtra(RECORD_CONFIG_MAX_DURATION, 60 * 1000);
        intent.putExtra(RECORD_CONFIG_ASPECT_RATIO, mAspectRatio);
        if(mRecommendQuality != -1){
            // 提供的三挡设置
            intent.putExtra(RECORD_CONFIG_RECOMMEND_QUALITY, mRecommendQuality);
        }else{
            // 自定义设置
            intent.putExtra(RECORD_CONFIG_RESOLUTION, mRecordResolution);
            intent.putExtra(RECORD_CONFIG_BITE_RATE, mBiteRate);
            intent.putExtra(RECORD_CONFIG_FPS, mFps);
            intent.putExtra(RECORD_CONFIG_GOP, mGop);
        }
        activity.startActivity(intent);
    }
}
