package com.alading.shopping.modle.constant;

public class HttpRequestUrl {


	public static final String HOME_TOP_IMAGE_URL =HttpServerPort.APPURL + "/api/api.do?action=indexLoad";//首页
	public static final String HOME_UP_IMAGE_URL =HttpServerPort.APPURL + "/api/api.do?action=qqjxSplitLoad";//上拉加载更多


	public static final String USER_LOGIN =HttpServerPort.APPURL + "/api/api.do?action=login";//登陆
	public static final String USER_REGISTER =HttpServerPort.APPURL + "/api/api.do?action=registValidateCode";
	public static final String USER_CODE =HttpServerPort.APPURL + "/api/api.do?action=registCheckValidate";
	public static final String USER_INFO =HttpServerPort.APPURL + "/api/api.do?action=registFinish";
	public static final String USER_ALL =HttpServerPort.APPURL + "/api//member/ordersList.do";
	public static final String USER_PRODUCTDETAILS =HttpServerPort.APPURL + "/api/api.do?action=productDetailLoad";//商品详情
	public static final String SHOPPINGCAR =HttpServerPort.APPURL + "/api/api.do?action=addShopcar";
	public static final String CONFIRMORDER = HttpServerPort.APPURL + "/api/member/preparedSubmitOrders.do";
	public static final String SUBMITORDER =HttpServerPort.APPURL +  "/api/member/submitOrders.do";
	public static final String ZHIFUBAO_ORDER =HttpServerPort.APPURL + "/api/member/preparedAlipay.do";
	public static final String LIANLIANZHIFU_ORDER = HttpServerPort.APPURL + "/api/member/preparedLcpay.do";
	public static final String WEIXIN_ORDER =HttpServerPort.APPURL + "/api/member/preparedWeiXinPay.do";
	public static final String SHOPINGCAR =HttpServerPort.APPURL + "/api/api.do?action=shopcarList";
	public static final String DELETE_SHOPINGCAR =HttpServerPort.APPURL + "/api/api.do?action=deleteAllShopcar";
	public static final String USER_EVALUATE = HttpServerPort.APPURL +  "/api/api.do?action=evaluateList";//评价列表

	public static final String MY_ORDER_STATU = HttpServerPort.APPURL + "/api/member/loadMemberInfo.do";//我的订单状态
	public static final String MY_ORDER_ALL =HttpServerPort.APPURL + "/api/member/ordersList.do";//我的订单所有
	public static final String MY_CENTER_PRODUCT = HttpServerPort.APPURL + "/api/member/findOrdersDetail.do";//我的订单详情
	public static final String MY_CENTER_CANCEL = HttpServerPort.APPURL +  "/api/member/cancelOrders.do";//取消订单

	public static final String MY_CENTER_FEED =HttpServerPort.APPURL +  "/api/api.do?action=addFeedback";//反馈
	public static final String MY_CENTER_CHANGE_PWD =HttpServerPort.APPURL + "/api/member/changePassword.do";//修改密码
	public static final String MY_CENTER_ABOUT = HttpServerPort.APPURL +  "/api/fwxy.htm";//关于
	public static final String MY_CENTER_ADRESS =HttpServerPort.APPURL + "/api/member/addAddress.do";//添加地址
	public static final String MY_CENTER_ADRESS_ALL =HttpServerPort.APPURL + "/api/member/loadAddressList.do";//地址列表
	public static final String MY_CENTER_ADRESS_DELETE =  HttpServerPort.APPURL + "/api/member/deleteAddress.do";//删除地址
}
