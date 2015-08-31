package com.alading.shopping.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alading.shopping.ui.appwidget.PullToRefreshLayout;
import com.alading.shopping.ui.base.BaseFragment;

/**
 * Created by Administrator on 2015/8/26.
 */
public class ShopcarFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void httpStart(String urlPath) {
        super.httpStart(urlPath);
    }

    @Override
    public void httpSuccess(String urlPath, int statusCode, Object obj) {
        super.httpSuccess(urlPath, statusCode, obj);
    }

    @Override
    public void httpFailed(String urlPath, String errorInfo, String content) {
        super.httpFailed(urlPath, errorInfo, content);
    }

    @Override
    public void httpFinish(String urlPath) {
        super.httpFinish(urlPath);
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }
}
