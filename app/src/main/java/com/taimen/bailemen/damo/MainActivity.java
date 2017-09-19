package com.taimen.bailemen.damo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.classic.android.utils.DoubleClickExitHelper;
import com.taimen.bailemen.base.BaseAppActivity;
import com.taimen.bailemen.widget.MyTabWidget;

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
//    private HomeFragment     homeFragment;//首页
//    private DiscoverFragment discoverFragment;//发现页面
//    private Fragment sessionListFragment;//消息页面
//    private MineFragment     mineFragment;//我的页面
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
