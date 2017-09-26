package com.bailemeng.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget
 * 类描述:  自定义ListView
 *         解决ScrollView和ListView一起使用的问题，显示的时候ListView不能完全正确的显示
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/25
 */

public class MyListView extends ListView {

	public MyListView(Context context) {
	    super(context);
    }

	public MyListView(Context context, AttributeSet attrs) {
	    super(context, attrs);
    }

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
    }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
	    super.onMeasure(widthMeasureSpec, expandSpec);
	}
}