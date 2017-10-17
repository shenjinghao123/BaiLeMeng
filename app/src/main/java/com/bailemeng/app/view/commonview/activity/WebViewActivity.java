package com.bailemeng.app.view.commonview.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppActivity;
import com.bailemeng.app.utils.StatusBarUtil;

/**
 */

public class WebViewActivity extends BaseAppActivity {

    private View headLayout;
    private ImageView headLeft;
    private TextView headTitle;
    private WebView webView;
    private ProgressBar progressBar;

    private String title;
    private String url;

    public static void start(Activity activity, String title, String url){
        if (activity != null && !TextUtils.isEmpty(url)){
            Intent intent = new Intent(activity, WebViewActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("url", url);
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        DetailsActivity.DETAIL_PATH.append("-->"+title+"(WebView)");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initialView() {
        headLayout = findViewById(R.id.rl_app_head);
        headLeft = (ImageView) findViewById(R.id.iv_app_head_left);
        headTitle = (TextView) findViewById(R.id.tv_app_head_center);
        webView = (WebView) findViewById(R.id.wv_common);
        progressBar = (ProgressBar) findViewById(R.id.pb_common);
    }

    @Override
    public void initialListenter() {
        headLeft.setOnClickListener(this);
    }

    @Override
    public void initialData() {
        StatusBarUtil.setImmerseLayout(mActivity, headLayout);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        headLeft.setVisibility(View.VISIBLE);
        headTitle.setText(title);
        //初始化webview
        initWebView();
    }

    private void initWebView(){
        webView.requestFocusFromTouch();
        webView.requestFocus();
        //		webView.setScrollbarFadingEnabled(true);
        //		webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);// 关键点
        //		// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        //		// 设置出现缩放工具
        //		webView.getSettings().setBuiltInZoomControls(true);
        // 让网页自适应屏幕宽度
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //		// 设置缓存
        //		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isValidUrl(url)) {
                    view.loadUrl(url);
                    view.requestFocus();
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (null != webView) {
                    webView.getSettings().setBlockNetworkImage(true);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (null != webView) {
                    webView.getSettings().setBlockNetworkImage(false);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100 && progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        if(v == headLeft){
            finish();
        }
    }
}
