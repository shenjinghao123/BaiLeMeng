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

import com.bailemeng.app.R;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget.dialog
 * 创 建 人: 123
 * 创建时间: 2017/9/26
 */
public class ShadeBottomUploadDialog extends Dialog {

    private Activity activity;

    public ShadeBottomUploadDialog(Activity activity) {
        super(activity, R.style.CommonDialogStyle);
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
        setContentView(view);
    }

    private void setListeners() {

    }
}
