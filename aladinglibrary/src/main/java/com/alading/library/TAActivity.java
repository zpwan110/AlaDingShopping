/*
 * Copyright (C) 2013  WhiteCat 白猫 (www.thinkandroid.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alading.library;

import com.alading.library.mvc.command.TAIdentityCommand;
import com.alading.library.mvc.common.TAIResponseListener;
import com.alading.library.mvc.common.TARequest;
import com.alading.library.mvc.common.TAResponse;
import com.alading.library.util.TALogger;
import com.alading.library.util.netstate.TANetWorkUtil.netType;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract class TAActivity extends Activity
{
	/** 模块的名字 */
	private String moduleName = "";
	/** 布局文件的名字 */
	private String layouName = "";
	private static final int DIALOG_ID_PROGRESS_DEFAULT = 0x174980;
	private static final String TAIDENTITYCOMMAND = "taidentitycommand";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		notifiyApplicationActivityCreating();
		onPreOnCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		getTAApplication().getAppManager().addActivity(this);
		// 加载类注入器
		initInjector();
		onAfterOnCreate(savedInstanceState);
		notifiyApplicationActivityCreated();
	}

	public TAApplication getTAApplication()
	{
		return (TAApplication) getApplication();
	}

	private void notifiyApplicationActivityCreating()
	{

	}

	private void notifiyApplicationActivityCreated()
	{

	}

	protected void onPreOnCreate(Bundle savedInstanceState)
	{

	}

	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * 初始化注入器
	 */
	private void initInjector()
	{
		// TODO Auto-generated method stub
		getTAApplication().getInjector().injectResource(this);
		getTAApplication().getInjector().inject(this);
	}

	/**
	 * 退出应用程序
	 * 
	 * @param isBackground
	 *            是否开开启后台运行,如果为true则为后台运行
	 */
	public void exitApp(Boolean isBackground)
	{
		getTAApplication().exitApp(isBackground);
	}

	/**
	 * 退出应用程序
	 * 
	 */
	public void exitApp()
	{
		getTAApplication().exitApp(false);
	}

	/**
	 * 退出应用程序，且在后台运行
	 * 
	 */
	public void exitAppToBackground()
	{
		getTAApplication().exitApp(true);
	}

}
