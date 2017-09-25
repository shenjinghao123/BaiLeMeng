package com.bailemeng.app.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.widget.MyTabWidget;
import com.classic.android.utils.DoubleClickExitHelper;

public class MainActivity extends BaseAppActivity {
    public static final String TAG = "MainActivity";
    private static MyTabWidget mTabWidget;
    //fragment索引值
    public static final int MAIN_ONE_INDEX = 0;
    public static final int MAIN_TWO_INDEX = 1;
    public static final int MAIN_THREE_INDEX = 2;
    public static final int MAIN_FIVE_INDEX = 3;

    //默认下标
    private int mIndex = MAIN_ONE_INDEX;

    private FragmentManager mFragmentManager;
    private DoubleClickExitHelper exitHelper;

    //主页fragment
//    private HomeFragment       homeFragment;//百乐萌首页
//    private CollegeFragment    collegeFragment;//大学页面
//    private MineFragment  communityFragment;//社区页面
//    private MineFragment       mineFragment;//我的页面
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initialView() {

    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {

    }
}
