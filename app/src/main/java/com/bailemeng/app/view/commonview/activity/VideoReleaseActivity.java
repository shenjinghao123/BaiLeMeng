package com.bailemeng.app.view.commonview.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.utils.ToastUtil;
import com.bailemeng.app.widget.dialog.ChooseVideoPhotoDialog;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.commonview.activity
 * 描    述: 视频发布
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/13
 */
public class VideoReleaseActivity extends BaseAppActivity {

    public static void start(Activity mActivity, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(mActivity, VideoReleaseActivity.class);
        if (extras != null){
            intent.putExtras(extras);
        }
        mActivity.startActivity(intent);
    }

    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private RelativeLayout chooseVideoPhotoRl;
    private TextView headTitle;
    private ImageView headLeft;

    @Override
    public void initialView() {
        headLeft = (ImageView) findViewById(R.id.iv_app_head_left);
        headTitle = (TextView) findViewById(R.id.tv_app_head_center);
        chooseVideoPhotoRl = (RelativeLayout) findViewById(R.id.choose_video_photo_rl);

    }

    @Override
    public void initialListenter() {
        headLeft.setOnClickListener(this);
        chooseVideoPhotoRl.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        headLeft.setVisibility(View.VISIBLE);
        headTitle.setVisibility(View.VISIBLE);
        headTitle.setText("上传视频");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_video_release;
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        switch (v.getId()){
            case R.id.iv_app_head_left:
                finish();
                break;
            case R.id.choose_video_photo_rl:
                ChooseVideoPhotoDialog chooseVideoPhotoDialog=new ChooseVideoPhotoDialog(mActivity);
                chooseVideoPhotoDialog.show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                takePhoto();
            } else {
                // Permission Denied
                ToastUtil.showLongToast(mActivity, "Permission Denied");
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /**
                 * 打开选择图片的界面
                 */
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            } else {
                // Permission Denied
                ToastUtil.showLongToast(mActivity, "Permission Denied");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        ToastUtil.showLongToast(this,uri.getPath());
                        //imageView.setImageBitmap(bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("tag",e.getMessage());
                        ToastUtil.showLongToast(this,"程序崩溃");
                    }
                }
                else{
                    Log.i("liang", "失败");
                }

                break;

            default:
                break;
        }
    }
}
