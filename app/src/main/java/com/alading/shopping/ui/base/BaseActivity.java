package com.alading.shopping.ui.base;

import com.alading.library.TAActivity;
import com.alading.library.TAApplication;
import com.alading.library.TASyncHttpClient;
import com.alading.library.annotation.TAInject;
import com.alading.library.util.TALogger;
import com.alading.library.util.http.AsyncHttpClient;
import com.alading.library.util.netstate.TANetChangeObserver;
import com.alading.library.util.netstate.TANetWorkUtil;
import com.alading.library.util.netstate.TANetWorkUtil.netType;
import com.alading.library.util.netstate.TANetworkStateReceiver;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.ui.appwidget.LoadingDialog;
import com.google.gson.Gson;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends TAActivity implements HttpResponseHandler.HttpProcessListener {
    protected TASyncHttpClient syncHttpClient;
    protected AsyncHttpClient asyncHttpClient;
    private LoadingDialog progressDialog;
    protected Gson baseGson;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);

//        if(TANetWorkUtil.isNetworkConnected()){
//
//        }
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState) {
        super.onAfterOnCreate(savedInstanceState);
        asyncHttpClient = new AsyncHttpClient();
        syncHttpClient = new TASyncHttpClient();
        baseGson = new Gson();
    }
    @Override
    public TAApplication getTAApplication() {
        return super.getTAApplication();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    /**
     * 要显示的文本
     *
     * @param text
     */
    public void showLoading(String text) {
        if (progressDialog == null) {
            progressDialog = LoadingDialog.createDialog(this, text);
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 显示Loading框
     * <p/>
     * 提示文本默认为“正在加载”
     */
    public void showLoading() {
        showLoading(null);
    }

    /**
     * 将显示的Loading框关闭
     */
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int res) {
        String msg = getResources().getString(res);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 结束acitivity
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 退出app
     */
    @Override
    public void exitApp() {
        super.exitApp();
    }

    @Override
    public void exitApp(Boolean isBackground) {
        super.exitApp(isBackground);
    }

    @Override
    public void exitAppToBackground() {
        super.exitAppToBackground();
    }

    @Override
    public void httpStart(String urlPath) {

    }

    @Override
    public void httpSuccess(String urlPath, int statusCode, Object obj) {

    }

    @Override
    public void httpFailed(String urlPath, String errorInfo, String content) {

    }

    @Override
    public void httpFinish(String urlPath) {

    }


}
