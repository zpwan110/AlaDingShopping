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
package com.alading.library.exception;

/**
 * @Title TAlException
 * @package com.alading.library.core.exception
 * @Description ThinkAndroid 所有异常的基类
 * @author 白猫
 * @date 2013-1-6
 * @version V1.0
 */
public class TAException extends Exception
{
	private static final long serialVersionUID = 1L;

	public TAException()
	{
		super();
	}

	public TAException(String detailMessage)
	{
		super(detailMessage);
	}
}