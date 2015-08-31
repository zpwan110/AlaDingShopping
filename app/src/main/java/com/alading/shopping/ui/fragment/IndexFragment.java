package com.alading.shopping.ui.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alading.library.util.http.RequestParams;
import com.alading.library.util.netstate.TANetWorkUtil;
import com.alading.shopping.R;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.common.util.JsonUtil;
import com.alading.shopping.modle.bean.GlobalSale;
import com.alading.shopping.modle.bean.GlobalSaleDetail;
import com.alading.shopping.modle.bean.HomeCategorys;
import com.alading.shopping.modle.bean.HomeIndexAdverts;
import com.alading.shopping.modle.bean.HomeMobileAdverts;
import com.alading.shopping.modle.bean.Product;
import com.alading.shopping.modle.constant.HttpRequestUrl;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.adapter.BannerAdapter;
import com.alading.shopping.ui.adapter.EntranceSaleAdapter;
import com.alading.shopping.ui.adapter.GlobalSaleAdapter;
import com.alading.shopping.ui.adapter.GlobalSelectedAdapter;
import com.alading.shopping.ui.adapter.MobileAdvertsAdapter;
import com.alading.shopping.ui.appwidget.BannerPagerView;
import com.alading.shopping.ui.appwidget.MyGridView;
import com.alading.shopping.ui.appwidget.MyListView;
import com.alading.shopping.ui.appwidget.NumberClock;
import com.alading.shopping.ui.appwidget.PullToRefreshLayout;
import com.alading.shopping.ui.appwidget.PullableScrollView;
import com.alading.shopping.ui.base.BaseFragment;
import com.google.gson.reflect.TypeToken;

import org.apache.http.protocol.HttpRequestHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/26.
 */
public class IndexFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {
    private View mainView;
    private Context mContext;
    private boolean isfirst = true;
    private PullableScrollView mPullRefreshScrollView;
    private LinearLayout mobile_Relative;
    private BannerPagerView mShowView;
    private PullToRefreshLayout mRefreshView;
    private HttpResponseHandler requstHandler;
    private RequestParams params;
    private int flag_load_more = 1;//页码

    private int pageSize = 1;//每页数量
    private String indexFirstLineUrl;//索引首页

    private List<HomeIndexAdverts>  bannerAdverts = new ArrayList<HomeIndexAdverts>();
    private List<HomeMobileAdverts> mobileAdvertsList =new ArrayList<HomeMobileAdverts>();
    private List<HomeCategorys> categorysList = new ArrayList<HomeCategorys>();
    private GlobalSaleDetail globalSaleDetail;
    private List<GlobalSale> globalSaleList =new ArrayList<GlobalSale>();
    private List<Product> globalSelected =new ArrayList<Product>();
    private LinearLayout advertisement_dots;
    private BannerAdapter viewAdapter;
    private ListView mobileList;
    private MobileAdvertsAdapter maAdapter;
    private GridView categorysGide;
    private EntranceSaleAdapter categorysAdapter;
    private LinearLayout globalSaleTitle;
    private TextView global_Deals;
    private TextView global_Deals_time;
    private MyListView qqtmList;
    private GlobalSaleAdapter qqtmAdapter;
    private GlobalSelectedAdapter qqSelectedAdapter;
    private MyListView qqSelectedList;
    private int refreshStatue =1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        requstHandler = new HttpResponseHandler(this, mContext);

        if (mainView == null) {
            mainView = inflater.inflate(R.layout.index_fragment_layout, null);
            mContext = inflater.getContext();
            initView();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) mainView.getParent();
        if (parent != null) {
            parent.removeAllViewsInLayout();
        }
        return mainView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isfirst) {
            startReqeustIndex();
            isfirst = false;
        }
    }

    public void initView() {
        mRefreshView = (PullToRefreshLayout) mainView.findViewById(R.id.mRefeshView);
        mPullRefreshScrollView = (PullableScrollView) mainView.findViewById(R.id.pull_refresh_scrollview);

        mobile_Relative = (LinearLayout) mainView.findViewById(R.id.mobile_relative);
        mShowView = (BannerPagerView) mainView.findViewById(R.id.advertisement);
        advertisement_dots = (LinearLayout)mainView.findViewById(R.id.advertisement_dots);
        mobileList= (MyListView)mainView.findViewById(R.id.mobileAdverts_List);
        categorysGide = (MyGridView)mainView.findViewById(R.id.entrance_sale);
        globalSaleTitle = (LinearLayout)mainView.findViewById(R.id.global_linear1);
        qqtmList = (MyListView)mainView.findViewById(R.id.global_deals_list);
        global_Deals = (TextView)mainView.findViewById(R.id.global_deals);
        global_Deals_time = (TextView)mainView.findViewById(R.id.global_deals_time);
        qqSelectedList = (MyListView)mainView.findViewById(R.id.global_list);

        mRefreshView.setOnRefreshListener(this);

        maAdapter = new MobileAdvertsAdapter(mContext,mobileAdvertsList);
        categorysAdapter = new EntranceSaleAdapter(mContext,categorysList);
        qqtmAdapter =new GlobalSaleAdapter(mContext,globalSaleList);
        qqSelectedAdapter = new GlobalSelectedAdapter(mContext,globalSelected);

        mobileList.setAdapter(maAdapter);
        categorysGide.setAdapter(categorysAdapter);
        qqtmList.setAdapter(qqtmAdapter);
        qqSelectedList.setAdapter(qqSelectedAdapter);
    }
    private void initBannerPager(List<HomeIndexAdverts> bannerList) {
        initDots(bannerList.size());
        viewAdapter = new BannerAdapter(bannerList,mContext);
        mShowView.setAdapter(viewAdapter);
        mShowView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                int index = arg0%advertisement_dots.getChildCount();
                for (int i = 0; i < advertisement_dots.getChildCount(); i++) {
                    if (i == index) {
                        advertisement_dots.getChildAt(i).setSelected(true);
                    } else {
                        advertisement_dots.getChildAt(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        mShowView.startAutoCycle();
    }

    private void initDots(int count) {
        advertisement_dots.removeAllViews();
        for (int j = 0; j < count; j++) {
            advertisement_dots.addView(initDot());
        }
        if(count>0){
            advertisement_dots.getChildAt(0).setSelected(true);
        }
    }

    private View initDot() {
        return LayoutInflater.from(mContext).inflate(R.layout.dot_layout, null);
    }

    /**
     * 首页
     */
    private void startReqeustIndex() {
        if(TANetWorkUtil.isNetworkAvailable(mContext)){
            showLoading();
            params = new RequestParams();
            asyncHttpClient.post(HttpRequestUrl.HOME_TOP_IMAGE_URL, params, requstHandler);
        }else{
            showToast("当前网络不可用");
            if(refreshStatue==1){
                mRefreshView.refreshFinish(PullToRefreshLayout.FAIL);
            }
        }
    }

    /**
     * 上拉加载更多
     */
    private void startReqeustLoadMore(){
        if(TANetWorkUtil.isNetworkAvailable(mContext)){
            showLoading();
            flag_load_more++;
            params = new RequestParams();
            params.put("currentPage", String.valueOf(flag_load_more));
            params.put("lineSize", String.valueOf(pageSize));
            asyncHttpClient.post(HttpRequestUrl.HOME_UP_IMAGE_URL, params, requstHandler);
        }else{
            showToast("当前网络不可用");
            if(refreshStatue==2){
                mRefreshView.loadmoreFinish(PullToRefreshLayout.FAIL);
            }
        }

    }


    private void initData() {
        initBannerPager(bannerAdverts);//banner广告
        maAdapter.notifyDataSetChanged();
        categorysAdapter.notifyDataSetChanged();
        if(globalSaleDetail!=null){
            globalSaleTitle.setVisibility(View.VISIBLE);
            global_Deals.setText("全球特卖  " + globalSaleDetail.getInfo());
            global_Deals_time.setText("还剩" + globalSaleDetail.getDays()+ "天" + globalSaleDetail.getHour()+ "时" + globalSaleDetail.getMinute() + "分");
        }else{
            globalSaleTitle.setVisibility(View.GONE);
        }
        qqtmAdapter.notifyDataSetChanged();
        qqSelectedAdapter.notifyDataSetChanged();
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
        Log.i("onSuccess", "start" + obj);
        if(HttpRequestUrl.HOME_TOP_IMAGE_URL.equals(urlPath)){
            analyticalIndexJson(obj.toString());
        }
        if(HttpRequestUrl.HOME_UP_IMAGE_URL.equals(urlPath)){
            analyticalLoadMoreJson(obj.toString());
        }
    }

    /**
     * 解析首页json数据
     * @param strJson
     */
    private void analyticalIndexJson(String strJson){
        try {
            //顶部广告
            JSONObject obj = new JSONObject(strJson.toString());
                if(obj==null){
                    return;
                }
            //首页轮播广告
            if(obj.has("indexAdverts")){
                bannerAdverts.clear();
                String indexAdverts = obj.getString("indexAdverts");
                List<HomeIndexAdverts> _bannerAdverts  = baseGson.fromJson(indexAdverts,new TypeToken<List<HomeIndexAdverts>>() {
                }.getType());
                bannerAdverts.addAll(_bannerAdverts);
            }
            //轮播下面的图片链接
            if(obj.has("indexFirstLineUrl")){
                indexFirstLineUrl = obj.getString("indexFirstLineUrl");
            }
            //移动广告

            if(obj.has("mobileAdverts")){
                mobileAdvertsList.clear();
                String mobileAdverts = obj.getString("mobileAdverts");
                List<HomeMobileAdverts> _mobileAdvertsList =  baseGson.fromJson(mobileAdverts,new TypeToken<List<HomeMobileAdverts>>() {
                }.getType());
                mobileAdvertsList.addAll(_mobileAdvertsList);
            }
            //分类

            if(obj.has("categorys")){
                categorysList.clear();
                String categorys = obj.getString("categorys");
                List<HomeCategorys> _categorysList =  baseGson.fromJson(categorys,new TypeToken<List<HomeCategorys>>() {
                }.getType());
                categorysList.addAll(_categorysList);
            }
            //全球特卖详情
            if(obj.has("qqtmInfo")){
                String qqtmInfo = obj.getString("qqtmInfo");
                globalSaleDetail =  baseGson.fromJson(qqtmInfo, GlobalSaleDetail.class);
            }
            //全球特卖

            if(obj.has("qqtmList")){
                globalSaleList.clear();
                String qqtmList = obj.getString("qqtmList");
                List<GlobalSale> _globalSaleList =  baseGson.fromJson(qqtmList,new TypeToken<List<GlobalSale>>() {
                }.getType());
                globalSaleList.addAll(_globalSaleList);
            }
            //全球精选

            if(obj.has("recommendList")){
                globalSelected.clear();
                String recommendList = obj.getString("recommendList");
                List<Product> _globalSelected =  baseGson.fromJson(recommendList,new TypeToken<List<Product>>() {
                }.getType());
                globalSelected.addAll(_globalSelected);
            }
            if(refreshStatue==1){
                mRefreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
            initData();
        } catch (JSONException e) {
            e.printStackTrace();
            if(refreshStatue==1){
                mRefreshView.refreshFinish(PullToRefreshLayout.FAIL);
            }
        }
    }

    /**
     * 解析上拉加载更多
     */
    private void analyticalLoadMoreJson(String arrayList){

        if(JsonUtil.getJSONType(arrayList)== JsonUtil.JSON_TYPE.JSON_TYPE_ARRAY){
            List<Product> _globalSelected =  baseGson.fromJson(arrayList,new TypeToken<List<Product>>() {
            }.getType());
            if(refreshStatue==2&&_globalSelected.size()>0){
                mRefreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                initData();
            }
            if(refreshStatue==2&&_globalSelected.size()==0){
                mRefreshView.loadmoreFinish(PullToRefreshLayout.NOMORE);
            }
            globalSelected.addAll(globalSelected.size(),_globalSelected);
        }else{
            if(refreshStatue==2){
                mRefreshView.loadmoreFinish(PullToRefreshLayout.FAIL);
            }
        }

    }

    @Override
    public void httpFailed(String urlPath, String errorInfo, String content) {
        super.httpFailed(urlPath, errorInfo, content);
        hideLoading();
        showToast(errorInfo);
        if(refreshStatue==1){
            mRefreshView.refreshFinish(PullToRefreshLayout.FAIL);
        }
        if(refreshStatue==2){
            mRefreshView.loadmoreFinish(PullToRefreshLayout.FAIL);
        }
    }


    @Override
    public void httpFinish(String urlPath) {
        hideLoading();
        super.httpFinish(urlPath);
    }


    /**
     * 刷新
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        startReqeustIndex();
        refreshStatue=1;
    }
    /**
     * 加载更多
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        startReqeustLoadMore();
        refreshStatue=2;
    }
}
