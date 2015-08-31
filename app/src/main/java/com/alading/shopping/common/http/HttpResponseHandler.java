package com.alading.shopping.common.http;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.alading.library.util.http.AsyncHttpResponseHandler;
import com.alading.library.util.http.JsonHttpResponseHandler;
import com.alading.shopping.common.util.JsonUtil;
import com.alading.shopping.modle.constant.ErrorMassge;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
@SuppressWarnings("deprecation")
public class HttpResponseHandler extends AsyncHttpResponseHandler {
	/** http连接超时 */
	protected static final int HTTP_CONNECTOR_START = 0;
	/** http连接错误 */
	protected static final int HTTP_CONNECTOR_ERROR = 1;
	/** http连接成功 */
	protected static final int HTTP_CONNECTOR_SUCCEED = 2;


	private Context context;
	private HttpProcessListener httpLister;
	public interface HttpProcessListener {
		// http访问开始时被调用
		void httpStart(String urlPath);

		// http访问成功被调用，同时返回数据用于UI显示
		void httpSuccess(String urlPath, int statusCode, Object obj);

		// 访问失败时被调用
		void httpFailed(String urlPath, String errorInfo, String content);

		// 访问完成
		void httpFinish(String urlPath);
	}
	public HttpResponseHandler(HttpProcessListener listener, Context context) {
		this.httpLister = listener;
		this.context = context;
	}
	@Override
	public void onStart(String urlPath) {
		super.onStart(urlPath);
		httpLister.httpStart(urlPath);
	}
	@Override
	public void onFinish(String urlPath) {
		super.onFinish(urlPath);
		httpLister.httpFinish(urlPath);
	}
	@Override
	public void onSuccess(String urlPath, int statusCode, String content) {
		super.onSuccess(urlPath, statusCode, content);
		JSONObject obj = null;
		try {
			obj = new JSONObject(content.toString());
			if(obj!=null&&obj.has("errorCode")){
				if (obj.getInt("errorCode")==1){
					if(obj.has("data")){
						httpLister.httpSuccess(urlPath, statusCode, obj.getString("data"));
					}else{
						httpLister.httpSuccess(urlPath, statusCode,obj.toString());
					}
				}else{
					if(obj.has("errorMsg")){
						httpLister.httpFailed(urlPath,obj.getString("errorMsg"), content);
					}else{
						httpLister.httpFailed(urlPath, obj.toString(), content);
					}

				}
			}else{
				httpLister.httpFailed(urlPath, ErrorMassge.ABHORMAL_JSON, content);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			httpLister.httpFailed(urlPath, ErrorMassge.ABHORMAL_JSON, content);
		}
	}
	@Override
	public void onFailure(String urlPath, Throwable error, String content) {
		super.onFailure(urlPath, error, content);
		httpLister.httpFailed(urlPath, error.getMessage(), content);
	}



}
