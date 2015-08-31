package com.alading.shopping.ui.activity;
import com.alading.library.util.http.RequestParams;
//import com.alading.shopping.R;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.modle.constant.HttpRequestUrl;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.base.BaseActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

//	@TAInjectView(id = R.id.main_btn)
	Button synGetButton;
	
	private HttpResponseHandler requstHandler;
	private RequestParams params;
	@Override
	protected void onPreOnCreate(Bundle savedInstanceState) {
		super.onPreOnCreate(savedInstanceState);
	}
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		//areaCode=410100
		requstHandler =new HttpResponseHandler(this, this);
		params =new RequestParams();
		params.put("areaCode", "410100");
		synGetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}


	@Override
	public void httpStart(String requset) {
		super.httpStart(requset);
		Log.i("onSuccess", "start" + requset);
		Toast.makeText(getApplication(),"requset",Toast.LENGTH_LONG);
	}

	@Override
	public void httpSuccess(String requset,int statusCode, Object obj) {
		super.httpSuccess(requset,statusCode, obj);
		Log.i("onSuccess", requset + "----" + obj.toString());
		Toast.makeText(getApplication(), "requset", Toast.LENGTH_LONG);
	}

	@Override
	public void httpFailed(String requset, String errorInfo,String content) {
		super.httpFailed(requset, errorInfo,content);
//		Log.i("onSuccess", requset+"----"+errorInfo.toString());
		Toast.makeText(getApplication(),"requset",Toast.LENGTH_LONG);
	}
}
