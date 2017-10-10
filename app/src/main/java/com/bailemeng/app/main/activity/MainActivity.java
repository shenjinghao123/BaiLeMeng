package com.bailemeng.app.main.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.utils.ToastUtil;
import com.bailemeng.app.view.college.fragment.CollegeFragment;
import com.bailemeng.app.view.community.fragment.CommunityFragment;
import com.bailemeng.app.view.home.fragment.HomeFragment;
import com.bailemeng.app.view.mine.fragment.MineFragment;
import com.bailemeng.app.widget.MyTabWidget;
import com.bailemeng.app.widget.dialog.ShadeBottomUploadDialog;
import com.classic.android.utils.DoubleClickExitHelper;
import com.tencent.rtmp.TXLiveBase;

public class MainActivity extends BaseAppActivity implements MyTabWidget.OnTabSelectedListener {
    public static final String TAG = "MainActivity";
    private static MyTabWidget mTabWidget;
    //fragment索引值
    public static final int MAIN_ONE_INDEX = 0;
    public static final int MAIN_TWO_INDEX = 1;
    public static final int MAIN_SHOT_INDEX = 2;
    public static final int MAIN_THREE_INDEX = 3;
    public static final int MAIN_FIVE_INDEX = 4;

    //默认下标
    private int mIndex = MAIN_ONE_INDEX;

    private FragmentManager mFragmentManager;
    private DoubleClickExitHelper exitHelper;

    //主页fragment
    private HomeFragment         homeFragment;//百乐萌首页
    private CollegeFragment      collegeFragment;//大学页面
    private CommunityFragment    communityFragment;//社区页面
    private MineFragment         mineFragment;//我的页面
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initialView() {
        mTabWidget = (MyTabWidget) findViewById(R.id.main_tab_widget);
        mTabWidget.setIndicateImageDisplay(MAIN_SHOT_INDEX,true);
        String sdkver = TXLiveBase.getSDKVersionStr();
        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);
    }

    @Override
    public void initialListenter() {
        mTabWidget.setOnTabSelectedListener(this);
    }

    @Override
    public void initialData() {
        mFragmentManager = getSupportFragmentManager();
        exitHelper = new DoubleClickExitHelper(this);

        if (getIntent().hasExtra("index")) mIndex = getIntent().getIntExtra("index", MAIN_ONE_INDEX);
        onTabSelected(mIndex);
    }

    @Override
    public void onTabSelected(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case MAIN_ONE_INDEX:
                if (null == homeFragment){
                    homeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.center_layout, homeFragment);
                }
                else{
                    transaction.show(homeFragment);
                }
                break;
            case MAIN_TWO_INDEX:
                if (null == collegeFragment){
                    collegeFragment = CollegeFragment.newInstance();
                    transaction.add(R.id.center_layout, collegeFragment);
                }
                else{
                    transaction.show(collegeFragment);
                }
                break;
            case MAIN_SHOT_INDEX:
//                LoginActivity.start(mActivity,null);
                ShadeBottomUploadDialog shadeBottomUploadDialog=new ShadeBottomUploadDialog(mActivity);
                shadeBottomUploadDialog.show();
                return;
            case MAIN_THREE_INDEX:
                if (null == communityFragment){
                    communityFragment = CommunityFragment.newInstance();
                    transaction.add(R.id.center_layout, communityFragment);
                }
                else{
                    transaction.show(communityFragment);
                }
                break;
            case MAIN_FIVE_INDEX:
                if (true) {
                    mTabWidget.setTabsDisplay(mActivity, mIndex);
                    ToastUtil.showLongToast(mActivity, "该操作需要先登录才能使用");
                    LoginActivity.start(mActivity, null);
                    return;
                }
                if (null == mineFragment){
                    mineFragment = MineFragment.newInstance();
                    transaction.add(R.id.center_layout, mineFragment);
                }
                else{
                    transaction.show(mineFragment);
                }
                break;
        }
        mIndex = index;
        transaction.commitAllowingStateLoss();
        mTabWidget.setTabsDisplay(mActivity, index);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != homeFragment){
            transaction.hide(homeFragment);
        }
        if (null != collegeFragment){
            transaction.hide(collegeFragment);
        }
        if (null != communityFragment){
            transaction.hide(communityFragment);
        }
        if (null != mineFragment){
            transaction.hide(mineFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
		/* 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment */
        if (null != homeFragment){
            homeFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != collegeFragment){
            collegeFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != communityFragment){
            communityFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (null != mineFragment){
            mineFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitHelper.onKeyDown(keyCode, event);
    }

}
