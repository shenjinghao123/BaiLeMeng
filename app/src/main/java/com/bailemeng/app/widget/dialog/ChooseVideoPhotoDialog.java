package com.bailemeng.app.widget.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.tencent.shortvideo.choose.TCVideoChooseActivity;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget.dialog
 * 描    述:
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/13
 */
public class ChooseVideoPhotoDialog extends Dialog implements View.OnClickListener  {
    private Activity activity;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    public ChooseVideoPhotoDialog(Activity activity) {
        super(activity, R.style.common_dialog_style);
        this.activity = activity;
        findViews();
        setListeners();
        Window window = getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.window_anim_style);
    }

    private TextView photoTv,videoTv;

    private void findViews() {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_video_photo, null);
        photoTv = view.findViewById(R.id.choose_photo_tv);
        videoTv = view.findViewById(R.id.choose_video_tv);
        setContentView(view);
    }

    private void setListeners() {
        photoTv.setOnClickListener(this);
        videoTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choose_photo_tv:
                //第二个参数是需要申请的权限
                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                    //权限还没有授予，需要在这里写申请权限的代码
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    choosePhoto();
                }
                break;
            case R.id.choose_video_tv:
                Intent intent=new Intent(activity,TCVideoChooseActivity.class);
                activity.startActivity(intent);
                dismiss();
                break;
        }
    }

    void choosePhoto(){
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        activity.startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

    }

}
