package com.bailemeng.app.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.bailemeng.app.R;
import com.bailemeng.app.utils.ToastUtil;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget.dialog
 * 创 建 人: 123
 * 创建时间: 2017/9/26
 */
public class ShadeBottomUploadDialog extends Dialog implements View.OnClickListener {

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
                ToastUtil.showLongToast(activity,"拍摄");
                break;
            case R.id.upload_game_ll:
                ToastUtil.showLongToast(activity,"参赛");
                break;
            case R.id.upload_video_ll:
                ToastUtil.showLongToast(activity,"上传");
                break;
        }
    }
}
