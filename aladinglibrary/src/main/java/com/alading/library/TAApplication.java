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

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Stack;

import com.alading.library.exception.TAAppException;
import com.alading.library.exception.TANoSuchCommandException;
import com.alading.library.mvc.command.TACommandExecutor;
import com.alading.library.mvc.command.TAICommand;
import com.alading.library.mvc.command.TAIdentityCommand;
import com.alading.library.mvc.common.TAIResponseListener;
import com.alading.library.mvc.common.TARequest;
import com.alading.library.mvc.common.TAResponse;
import com.alading.library.mvc.controller.ActivityStackInfo;
import com.alading.library.mvc.controller.NavigationDirection;
import com.alading.library.util.TAInjector;
import com.alading.library.util.TALogger;
import com.alading.library.util.cache.TAFileCache;
import com.alading.library.util.cache.TAFileCache.TACacheParams;
import com.alading.library.util.config.TAIConfig;
import com.alading.library.util.config.TAPreferenceConfig;
import com.alading.library.util.config.TAPropertiesConfig;
import com.alading.library.util.db.TASQLiteDatabasePool;
import com.alading.library.util.layoutloader.TAILayoutLoader;
import com.alading.library.util.layoutloader.TALayoutLoader;
import com.alading.library.util.netstate.TANetChangeObserver;
import com.alading.library.util.netstate.TANetWorkUtil.netType;
import com.alading.library.util.netstate.TANetworkStateReceiver;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

//public class TAApplication extends Application implements TAIResponseListener
public class TAApplication extends Application
{
	/** 配置器 为Preference */
	public final static int PREFERENCECONFIG = 0;
	/** 配置器 为PROPERTIESCONFIG */
	public final static int PROPERTIESCONFIG = 1;

	private final static String TAIDENTITYCOMMAND = "TAIdentityCommand";
	/** 配置器 */
	private TAIConfig mCurrentConfig;
	/** 获取布局文件ID加载器 */
	private TAILayoutLoader mLayoutLoader;
	/** 加载类注入器 */
	private TAInjector mInjector;
	/** App异常崩溃处理器 */
	private UncaughtExceptionHandler uncaughtExceptionHandler;
	private static TAApplication application;
	private TACommandExecutor mCommandExecutor;
	private TAActivity currentActivity;
	private final HashMap<String, Class<? extends TAActivity>> registeredActivities = new HashMap<String, Class<? extends TAActivity>>();
//	private Stack<ActivityStackInfo> activityStack = new Stack<ActivityStackInfo>();
	private NavigationDirection currentNavigationDirection;
	/** ThinkAndroid 文件缓存 */
	private TAFileCache mFileCache;
	/** ThinkAndroid数据库链接池 */
	private TASQLiteDatabasePool mSQLiteDatabasePool;
	/** ThinkAndroid 应用程序运行Activity管理器 */
	private TAAppManager mAppManager;
	private Boolean networkAvailable = false;
	private static final String SYSTEMCACHE = "thinkandroid";
	private TANetChangeObserver taNetChangeObserver;

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		onPreCreateApplication();
		super.onCreate();
		doOncreate();
		onAfterCreateApplication();
		getAppManager();
	}

	private void doOncreate()
	{
		// TODO Auto-generated method stub
		this.application = this;
		// 注册App异常崩溃处理器
		Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler());
//		TANetworkStateReceiver.registerObserver(taNetChangeObserver);
		// 注册activity启动控制控制器
//		registerCommand(TAIDENTITYCOMMAND, TAIdentityCommand.class);
	}

	/**
	 * 获取Application
	 * 
	 * @return
	 */
	public static TAApplication getApplication()
	{
		return application;
	}

	protected void onAfterCreateApplication()
	{
		// TODO Auto-generated method stub

	}

	protected void onPreCreateApplication()
	{
		// TODO Auto-generated method stub
	}

	public TAIConfig getPreferenceConfig()
	{
		return getConfig(PREFERENCECONFIG);
	}

	public TAIConfig getPropertiesConfig()
	{
		return getConfig(PROPERTIESCONFIG);
	}

	public TAIConfig getConfig(int confingType)
	{
		if (confingType == PREFERENCECONFIG)
		{
			mCurrentConfig = TAPreferenceConfig.getPreferenceConfig(this);

		} else if (confingType == PROPERTIESCONFIG)
		{
			mCurrentConfig = TAPropertiesConfig.getPropertiesConfig(this);
		} else
		{
			mCurrentConfig = TAPropertiesConfig.getPropertiesConfig(this);
		}
		if (!mCurrentConfig.isLoadConfig())
		{
			mCurrentConfig.loadConfig();
		}
		return mCurrentConfig;
	}

	public TAIConfig getCurrentConfig()
	{
		if (mCurrentConfig == null)
		{
			getPreferenceConfig();
		}
		return mCurrentConfig;
	}

	public TAILayoutLoader getLayoutLoader()
	{
		if (mLayoutLoader == null)
		{
			mLayoutLoader = TALayoutLoader.getInstance(this);
		}
		return mLayoutLoader;
	}

	public void setLayoutLoader(TAILayoutLoader layoutLoader)
	{
		this.mLayoutLoader = layoutLoader;
	}

	/**
	 * 设置 App异常崩溃处理器
	 * 
	 * @param uncaughtExceptionHandler
	 */
	public void setUncaughtExceptionHandler(
			UncaughtExceptionHandler uncaughtExceptionHandler)
	{
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	private UncaughtExceptionHandler getUncaughtExceptionHandler()
	{
		if (uncaughtExceptionHandler == null)
		{
			uncaughtExceptionHandler = TAAppException.getInstance(this);
		}
		return uncaughtExceptionHandler;
	}

	public TAInjector getInjector()
	{
		if (mInjector == null)
		{
			mInjector = TAInjector.getInstance();
		}
		return mInjector;
	}

	public void setInjector(TAInjector injector)
	{
		this.mInjector = injector;
	}

	public TAFileCache getFileCache()
	{
		if (mFileCache == null)
		{
			TACacheParams cacheParams = new TACacheParams(this, SYSTEMCACHE);
			TAFileCache fileCache = new TAFileCache(cacheParams);
			application.setFileCache(fileCache);

		}
		return mFileCache;
	}

	public void setFileCache(TAFileCache fileCache)
	{
		this.mFileCache = fileCache;
	}


	public TASQLiteDatabasePool getSQLiteDatabasePool()
	{
		if (mSQLiteDatabasePool == null)
		{
			mSQLiteDatabasePool = TASQLiteDatabasePool.getInstance(this);
			mSQLiteDatabasePool.createPool();
		}
		return mSQLiteDatabasePool;
	}

	public void setSQLiteDatabasePool(TASQLiteDatabasePool sqliteDatabasePool)
	{
		this.mSQLiteDatabasePool = sqliteDatabasePool;
	}

	public TAAppManager getAppManager()
	{
		if (mAppManager == null)
		{
			mAppManager = TAAppManager.getAppManager();
		}
		return mAppManager;
	}

	/**
	 * 退出应用程序
	 * 
	 * @param isBackground
	 *            是否开开启后台运行,如果为true则为后台运行
	 */
	public void exitApp(Boolean isBackground)
	{
		mAppManager.AppExit(this, isBackground);
	}

	/**
	 * 获取当前网络状态，true为网络连接成功，否则网络连接失败
	 *
	 * @return
	 */
	public Boolean isNetworkAvailable()
	{
		return networkAvailable;
	}

}
