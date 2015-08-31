package com.alading.shopping.ui.base;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alading.library.TAApplication;
import com.alading.library.TASyncHttpClient;
import com.alading.library.util.http.AsyncHttpClient;
import com.alading.library.util.netstate.TANetWorkUtil;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.ui.appwidget.LoadingDialog;
import com.google.gson.Gson;


public class BaseFragment extends Fragment  implements  HttpResponseHandler.HttpProcessListener  {

    protected TASyncHttpClient syncHttpClient;
    protected AsyncHttpClient asyncHttpClient;
    protected Gson baseGson;
    // //自定义的Loading界面
    LoadingDialog progressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asyncHttpClient = new AsyncHttpClient();
        syncHttpClient = new TASyncHttpClient();
        baseGson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
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
    /**
     * 要显示的文本
     *
     * @param text
     */
    public void showLoading(String text) {
        if (progressDialog == null) {
            progressDialog = LoadingDialog.createDialog(getActivity(), text);
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 显示Loading框
     *
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int res) {
        String msg = getResources().getString(res);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
