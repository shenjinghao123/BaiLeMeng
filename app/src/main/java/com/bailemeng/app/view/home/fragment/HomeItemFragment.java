package com.bailemeng.app.view.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.view.home.adapter.HomeItemAdapter;
import com.bailemeng.app.widget.MyGridView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: 123
 * 创建时间: 2017/9/25
 */
public class HomeItemFragment extends BaseAppFragment {

    public static HomeItemFragment newInstance() {
        return new HomeItemFragment();
    }

    private Banner banner;
    private MyGridView itemGridView;
    private BaseAdapter adapter;

    @Override
    public void initialView(View view) {
        banner = (Banner) view.findViewById(R.id.home_banner_view);
        itemGridView = view.findViewById(R.id.home_item_grid_view);

        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        String title = mBundle.getString("arg");

    }

    @Override
    public void initialListenter() {

    }

    @Override
    public void initialData() {
        bannerStyle();
        setBanner();
        List<String> list = new ArrayList<String>();
        list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");
        adapter=new HomeItemAdapter(mActivity,list);
        itemGridView.setAdapter(adapter);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_item;
    }

    private void bannerStyle(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        banner.setLayoutParams(params);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    private void setBanner(){
        List<String> titles = new ArrayList<>();
        List<String> images = new ArrayList<>();
        images.add(null);images.add(null);images.add(null);
        titles.add("标题1");titles.add("标题2");titles.add("标题3");
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //用fresco加载图片简单用法，记得要写下面的createImageView方法
                if(path==null){
                    //"res://包名(实际可以是任何字符串甚至留空)/"
                    imageView.setImageURI(Uri.parse("res:/"+ R.mipmap.icon_banner));
                }else {
                    Uri uri = Uri.parse((String) path);
                    imageView.setImageURI(uri);
                }
            }

            //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
            @Override
            public ImageView createImageView(Context context) {
                //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
                SimpleDraweeView imageView=new SimpleDraweeView(context);
                return imageView;
            }
        });
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {//注意下标从1开始
//                Intent intent = new Intent();
//                intent.putExtra("productId",response.get(position-1).getProductId());
//                ProductDetailsActivity.start(activity,intent);
            }
        });
        banner.start();
    }

}
