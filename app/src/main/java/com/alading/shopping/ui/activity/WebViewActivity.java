package com.alading.shopping.ui.activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alading.shopping.R;
import com.alading.shopping.ui.base.BaseActivity;

/**
 * Created by Administrator on 2015/8/31.
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private String mUrl;
    private TextView actionbar_Left;
    private ImageView actionbar_Right;
    private String mTitle;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        mUrl = getIntent().getStringExtra("weburl");
        mTitle = getIntent().getStringExtra("webtitle");
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState) {
        super.onAfterOnCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initActionBar();
        mWebView = (WebView)findViewById(R.id.webView);
        if(mUrl!=null){
            loadWebView(mUrl);
        }else{
            showToast("地址为空");
        }
    }

    private void initActionBar() {
        actionbar_Left = (TextView) findViewById(R.id.back_title);
        actionbar_Right = (ImageView) findViewById(R.id.actionbar_right);
        actionbar_Left.setVisibility(View.VISIBLE);
        actionbar_Left.setText(R.string.index_main);
        actionbar_Right.setImageResource(R.drawable.share);
        actionbar_Left.setOnClickListener(this);
        actionbar_Right.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.actionbar_title);
        title.setText(mTitle);
    }

    private void loadWebView(String mUrl) {
        WebSettings setting = mWebView.getSettings();
        // 设置支持JavaScript脚本
        setting.setJavaScriptEnabled(true);
        // 设置可以访问文件
        setting.setAllowFileAccess(true);
        // 设置可以支持缩放
        setting.setSupportZoom(true);
        // 设置默认缩放方式尺寸是far
        setting.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 设置出现缩放工具
        setting.setBuiltInZoomControls(false);
        setting.setDefaultFontSize(20);
//        WebInterface jsInterface = new WebInterface(this, mWebView);
//        webView.addJavascriptInterface(jsInterface, "browser");
        setting.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyViewClient());
        mWebView.setWebChromeClient(wvcc);
        mWebView.loadUrl(mUrl); // 加载指定网址
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_title:
                finish();
                break;
        }
    }

    public class MyViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                view.loadUrl(url);
            }
            if (url.contains("loginstatuserror")) {
//                toLogin = true;
//                Intent intent = new Intent(context, LoginActivity.class);
//                startActivity(intent);
//                showToast("请登录");
            }else if(url.contains("loginstatuserror")){
//                Intent intent = new Intent(context,WYMainActivity.class);
//                startActivity(intent);
//                finish();
            }
            if (url.contains("myredbag")) {
//                Intent intent = new Intent(context, MyRedBagActivity.class);
//                startActivity(intent);
//                view.loadUrl(mUrl);
            }
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {

            super.onLoadResource(view, url);
        }
    }
    WebChromeClient wvcc = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (title.equals("-1")) {
//                Intent intent = new Intent();
//                intent.setClass(context, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                showToast("用户信息失效，请重新登陆");
            }
        }

    };

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}
