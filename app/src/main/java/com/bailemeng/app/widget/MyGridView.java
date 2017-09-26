package com.bailemeng.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.widget
 * 创 建 人: shenjinghao
 * 创建时间: 2017/9/25
 */

public class MyGridView extends GridView {

	public MyGridView(Context context) {
		super(context);
	}
	
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
