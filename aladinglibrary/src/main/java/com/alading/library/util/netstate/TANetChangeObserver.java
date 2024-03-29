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
package com.alading.library.util.netstate;

import com.alading.library.util.netstate.TANetWorkUtil.netType;

/**
 * @Title TANetChangeObserver
 * @Package com.alading.library.util.netstate
 * @Description 是检测网络改变的观察者
 * @author 白猫
 * @date 2013-1-22 下午 9:35
 * @version V1.0
 */
public  interface TANetChangeObserver
{
	/**
	 * 网络连接连接时调用
	 */
	 void onConnect(netType type);

	/**
	 * 当前没有网络连接
	 */
	 void onDisConnect();
}
