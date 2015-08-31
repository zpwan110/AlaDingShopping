package com.alading.shopping.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alading.library.util.http.RequestParams;
import com.alading.shopping.R;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.common.util.JsonUtil;
import com.alading.shopping.modle.bean.Product;
import com.alading.shopping.modle.bean.UserRating;
import com.alading.shopping.modle.constant.HttpRequestUrl;
import com.alading.shopping.ui.adapter.UserRatingAdapter;
import com.alading.shopping.ui.appwidget.PullToRefreshLayout;
import com.alading.shopping.ui.appwidget.PullableListView;
import com.alading.shopping.ui.base.BaseActivity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

//import com.alading.shopping.R;

public class RatingActivity extends BaseActivity implements OnClickListener, PullToRefreshLayout.OnRefreshListener {


	private HttpResponseHandler requstHandler;
	private RequestParams params;
	private int flag_load_more = 1;//页码

	private int pageSize = 1;//每页数量
	private int mPid;
	private int refreshStatue=1;
	private PullToRefreshLayout ratingRefeshView;
	private PullableListView ratingList;
	private TextView actionbar_Left;

	private List<UserRating> userRatings = new ArrayList<UserRating>();
	private UserRatingAdapter ratingAdapter;

	@Override
	protected void onPreOnCreate(Bundle savedInstanceState) {
		super.onPreOnCreate(savedInstanceState);
		mPid = getIntent().getIntExtra("productId",0);
	}
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		requstHandler =new HttpResponseHandler(this, this);
		initActionBar();
		initView();
	}
	private void initActionBar() {
		actionbar_Left = (TextView) findViewById(R.id.back_title);
		actionbar_Left.setVisibility(View.VISIBLE);
		actionbar_Left.setText(R.string.product);
		actionbar_Left.setOnClickListener(this);
		TextView title = (TextView) findViewById(R.id.actionbar_title);
		title.setText(this.getResources().getString(R.string.user_rating));
	}
	private void initView(){
		ratingRefeshView = (PullToRefreshLayout) findViewById(R.id.ratingRefeshView);
		ratingRefeshView.setOnRefreshListener(this);
		ratingList = (PullableListView) findViewById(R.id.ratingList);
		ratingAdapter = new UserRatingAdapter(this,userRatings);
		requestRating();
	}

private void requestRating(){
	showLoading();
	flag_load_more = 1;
	params=new RequestParams();
	params.put("pid", mPid+"");
	params.put("currentPage",flag_load_more+"");
	params.put("lineSize", pageSize+"");
	asyncHttpClient.post(HttpRequestUrl.USER_EVALUATE, params, requstHandler);
}
	private void loadMoreRating(){
		showLoading();
		flag_load_more++;
		params=new RequestParams();
		params.put("pid", mPid+"");
		params.put("currentPage",flag_load_more+"");
		params.put("lineSize", pageSize+"");
		asyncHttpClient.post(HttpRequestUrl.USER_EVALUATE, params, requstHandler);
	}
	@Override
	public void onClick(View v) {
			switch (v.getId()){
				case R.id.back_title:
					finish();
					break;
			}
	}
	private void initData(){
		ratingList.setAdapter(ratingAdapter);
		ratingAdapter.notifyDataSetChanged();
	}
	@Override
	public void httpStart(String requset) {
		super.httpStart(requset);

	}

	@Override
	public void httpSuccess(String requset,int statusCode, Object obj) {
		super.httpSuccess(requset,statusCode, obj);
		hideLoading();
		analyticalJson(obj.toString());
	}

	@Override
	public void httpFailed(String requset, String errorInfo,String content) {
		super.httpFailed(requset, errorInfo,content);
		hideLoading();
		showToast(errorInfo);
		if(refreshStatue==1){
			ratingRefeshView.refreshFinish(PullToRefreshLayout.FAIL);
		}
		if(refreshStatue==2){
			ratingRefeshView.loadmoreFinish(PullToRefreshLayout.FAIL);
		}
	}

	private void analyticalJson(String json){
		if(JsonUtil.getJSONType(json)== JsonUtil.JSON_TYPE.JSON_TYPE_ARRAY){
			List<UserRating> _RatingList =  baseGson.fromJson(json,new TypeToken<List<UserRating>>() {
			}.getType());
			if(refreshStatue==1){
				ratingRefeshView.refreshFinish(PullToRefreshLayout.SUCCEED);
			}else{
				ratingRefeshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			}
			if(_RatingList.size()>0){
				if(refreshStatue==1){
					userRatings.clear();
					userRatings.addAll(_RatingList);
				}else{
					userRatings.addAll(userRatings.size(),_RatingList);
				}
				initData();
			}
			Log.i("thread", "thread" + Thread.currentThread().getName());
		}else{
			if(refreshStatue==1){
				ratingRefeshView.refreshFinish(PullToRefreshLayout.FAIL);
			}
			if (refreshStatue==2){
				ratingRefeshView.loadmoreFinish(PullToRefreshLayout.FAIL);
			}
		}
	}
	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		requestRating();
		refreshStatue=1;
	}

	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		loadMoreRating();
		refreshStatue=2;
	}
}
