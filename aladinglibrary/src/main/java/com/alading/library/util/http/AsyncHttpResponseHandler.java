package com.alading.library.util.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.apache.http.Header;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class AsyncHttpResponseHandler
{
	protected static final int SUCCESS_MESSAGE = 4;
	protected static final int FAILURE_MESSAGE = 1;
	protected static final int START_MESSAGE = 2;
	protected static final int FINISH_MESSAGE = 3;
	protected static final int PROGRESS_MESSAGE = 0;
	private Handler handler;

	public AsyncHttpResponseHandler()
	{
		if (Looper.myLooper() != null)
		{
			handler = new Handler()
			{
				@Override
				public void handleMessage(Message msg)
				{
					AsyncHttpResponseHandler.this.handleMessage(msg);
				}
			};
		}
	}

	public void onStart(String urlPath)
	{
	}

	public void onFinish(String urlPath)
	{
	}

	public void onSuccess(String urlPath,String content)
	{

	}

	public void onProgress(long totalSize, long currentSize, long speed)
	{

	}
	public void onSuccess(String urlPath, int statusCode,String content)
	{
		onSuccess(urlPath, content);
	}

	public void onFailure(String urlPath,Throwable error)
	{
		onFinish(urlPath);
	}

	public void onFailure(String urlPath,Throwable error, String content)
	{
		onFailure(urlPath,error);
	}
	
	
	
	
	/**************************/
	protected void sendSuccessMessage(String urlPath,int statusCode,
			String responseBody)
	{
		sendMessage(obtainMessage(SUCCESS_MESSAGE, new Object[]
		{urlPath, new Integer(statusCode),responseBody }));
	}
	
	protected void sendFailureMessage(Throwable e, String responseBody,String requset)
	{
		sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]
		{requset, e, responseBody }));
	}

	protected void sendFailureMessage(Throwable e, byte[] responseBody,String requset)
	{
		sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]
		{requset, e, responseBody }));
	}

	protected void sendStartMessage(String requset)
	{
		sendMessage(obtainMessage(START_MESSAGE,new Object[]
				{requset}));
	}

	protected void sendFinishMessage(String requset)
	{
		sendMessage(obtainMessage(FINISH_MESSAGE, new Object[]
				{requset}));
	}
	/**************************/
	
	
	
	
	
	protected void handleSuccessMessage(String urlPath, int statusCode,
			String responseBody)
	{
		onSuccess(urlPath, statusCode, responseBody);
	}

	protected void handleFailureMessage(String urlPath,Throwable e, String responseBody)
	{
		onFailure(urlPath,e, responseBody);
	}

	protected void handleProgressMessage(long totalSize, long currentSize,
			long speed)
	{
		onProgress(totalSize, currentSize, speed);
	}

	protected void handleMessage(Message msg)
	{
		Object[] response;
		switch (msg.what)		
		{
		case PROGRESS_MESSAGE:
			response = (Object[]) msg.obj;
			handleProgressMessage((Long) response[0], (Long) response[1],
					(Long) response[2]);
			break;
		case SUCCESS_MESSAGE:
			response = (Object[]) msg.obj;
			handleSuccessMessage((String)response[0],
					((Integer) response[1]).intValue(), (String) response[2]);
			break;
		case FAILURE_MESSAGE:
			response = (Object[]) msg.obj;
			handleFailureMessage((String)response[0],(Throwable) response[1], (String) response[2]);
			break;
		case START_MESSAGE:
			response = (Object[]) msg.obj;
			onStart((String)response[0]);
			break;
		case FINISH_MESSAGE:
			response = (Object[]) msg.obj;
			onFinish((String)response[0]);
			break;

		}
	}

	protected void sendMessage(Message msg)
	{
		if (handler != null)
		{
			handler.sendMessage(msg);
		} else
		{
			handleMessage(msg);
		}
	}

	protected Message obtainMessage(int responseMessage, Object response)
	{
		Message msg = null;
		if (handler != null)
		{
			msg = this.handler.obtainMessage(responseMessage, response);
		} else
		{
			msg = Message.obtain();
			msg.what = responseMessage;
			msg.obj = response;
		}
		return msg;
	}

	protected void sendResponseMessage(HttpResponse response,String requset)
	{
		StatusLine status = response.getStatusLine();
		String responseBody = null;
		try
		{
			HttpEntity entity = null;
			HttpEntity temp = response.getEntity();
			if (temp != null)
			{
				entity = new BufferedHttpEntity(temp);
				responseBody = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e)
		{
			sendFailureMessage(e, (String) null,requset);
		}

		if (status.getStatusCode() >= 300)
		{
			sendFailureMessage(new HttpResponseException(
					status.getStatusCode(), status.getReasonPhrase()),
					responseBody,requset);
		} else
		{
			sendSuccessMessage(requset,status.getStatusCode(),
					 responseBody);
		}
	}
}
