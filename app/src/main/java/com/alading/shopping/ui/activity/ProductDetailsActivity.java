package com.alading.shopping.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alading.library.util.http.RequestParams;
import com.alading.shopping.AladingApplication;
import com.alading.shopping.common.http.HttpResponseHandler;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.common.util.StringUtil;
import com.alading.shopping.modle.bean.ProductDetails;
import com.alading.shopping.modle.constant.HttpRequestUrl;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.adapter.BannerProAdapter;
import com.alading.shopping.ui.appwidget.BannerPagerView;
import com.alading.shopping.ui.appwidget.MyAlertDialog;
import com.alading.shopping.ui.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import com.alading.shopping.R;

/**
 * 商品详情
 */

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = ProductDetailsActivity.class.getSimpleName();
    private final static int AD_ONCLICK = 0X44;//图片点击节点

//    private PopupWindow popupWindow;
    /**
     * 注解加载主页界面
     **/
    private BannerPagerView productImagePagger;//商品图片轮播
    private LinearLayout advertisement_dots;//轮播点
    private TextView tvSelected;// 已选数量
    private TextView tvFreight;//配送费
    private TextView tvTariff;//关税
    private TextView tvCommentNum;//评论数
    private ImageView ivShopCarPar;//查看购物袋
    private Button btnPurchase;//确认购买
    private Button btnJoinShopCar;//加入购物车
    private LinearLayout linComment;//查看评论
    private LinearLayout linProDetails;//查看图文详情
    private LinearLayout linSpecial;//承诺保证书
    private TextView actionbar_Left;//返回上一层
    private ImageView actionbar_Right;//分享
    private TextView tvWarehouse;//仓库
    private ImageView ivCountry;//国旗
    private TextView tvProName;//商品名称
    private TextView tvProPrice;//商品价格
    private TextView tvAlaPrice;//啊啦原价
    private TextView tvDomPrice;//国内价格
    private TextView shopcar_num;//购物车数量
    private LinearLayout ll_bottom;//底部


    /**
     * 弹窗
     **/
    private ImageView ivShopCarChi;//查看购物袋
    private ImageView ivProduct;//商品图片
    private ImageView ivClosePopu;//关闭弹窗
    private ImageView btnSubtPro;//减
    private ImageView btnAddPro;//加
    private Button btnConfirBuy;//确认购买
    private TextView tvTotalPrice;//商品总价
    private TextView tvSalesRestriction;//限购
    private TextView tvStock;//仅剩
    private TextView ivShopcarNum;//购物车数量


    /**
     * 属性
     **/
    private int mPid;//商品号
    public static ProductDetails mProductDetails;//商品详情实例
    private ProductDetailsActivity mContext;//上下文
    private String mProcurementLocation;//采购地点
    private int mDeliveryType;//	发货类型
    private int mPfid;//商品Id
    private String orginPrice;//参考价
    private int ADDORREDUCE = 1;//商品数量
    private HttpResponseHandler requstHandler;
    private RequestParams params;
    private BannerProAdapter viewAdapter;
    private MyAlertDialog popupWindow;
    private TextView tvBuyNum;
    private int mProBuyNum;
    private RelativeLayout llJoin;
    private LinearLayout ll_xuangou_num;
    private Intent intent;

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState) {
        super.onAfterOnCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        requstHandler = new HttpResponseHandler(this, this);
        mPid = getIntent().getIntExtra("productId", 0);
        mContext = this;
        initActionBar();
        initView();
        startRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        productImagePagger = (BannerPagerView) findViewById(R.id.advertisement);
        advertisement_dots = (LinearLayout) findViewById(R.id.advertisement_dots);
        tvSelected = (TextView) findViewById(R.id.tvSelected);
        tvFreight = (TextView) findViewById(R.id.freight);
        tvTariff = (TextView) findViewById(R.id.tariffs_detail);
        tvCommentNum = (TextView) findViewById(R.id.user_rating_num);
        ivShopCarPar = (ImageView) findViewById(R.id.goShopCar);
        btnPurchase = (Button) findViewById(R.id.purchase);
        btnJoinShopCar = (Button) findViewById(R.id.shopping_car);
        linComment = (LinearLayout) findViewById(R.id.ll_user_rating);
        linProDetails = (LinearLayout) findViewById(R.id.ll_view_product_details);
        linSpecial = (LinearLayout) findViewById(R.id.special_index);
        ll_xuangou_num = (LinearLayout)findViewById(R.id.ll_xuangou_num);

        tvWarehouse = (TextView) findViewById(R.id.tvWarehouse);
        ivCountry = (ImageView) findViewById(R.id.ivCountry);
        tvProName = (TextView) findViewById(R.id.tvProName);
        tvProPrice = (TextView) findViewById(R.id.tvProPrice);
        tvAlaPrice = (TextView) findViewById(R.id.tvAlaPrice);
        tvDomPrice = (TextView) findViewById(R.id.tvDomPrice);
        shopcar_num = (TextView) findViewById(R.id.shopcar_num);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_purchase);
        initPopupWindow();
        initClick();
    }

    private void initActionBar() {
        actionbar_Left = (TextView) findViewById(R.id.back_title);
        actionbar_Right = (ImageView) findViewById(R.id.actionbar_right);
        actionbar_Left.setVisibility(View.VISIBLE);
        actionbar_Right.setVisibility(View.VISIBLE);
        actionbar_Left.setText(R.string.index_main);
        actionbar_Right.setImageResource(R.drawable.share);
        actionbar_Left.setOnClickListener(this);
        actionbar_Right.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.actionbar_title);
        title.setText(this.getResources().getString(R.string.product));
    }


    private void initPopupWindow() {
        View contentView = LayoutInflater.from(ProductDetailsActivity.this).inflate(R.layout.product_shop_purchase, null);
        btnConfirBuy = (Button) contentView.findViewById(R.id.text_shop);
        btnSubtPro = (ImageView) contentView.findViewById(R.id.subtraction);
        btnAddPro = (ImageView) contentView.findViewById(R.id.addition);
        ivShopCarChi = (ImageView) contentView.findViewById(R.id.toShopCar);
        tvTotalPrice = (TextView) contentView.findViewById(R.id.tvTotalPrice);
        ivShopcarNum = (TextView) contentView.findViewById(R.id.shopcar_num_pop);
        tvSalesRestriction = (TextView) contentView.findViewById(R.id.sales_restriction);
        tvStock = (TextView) contentView.findViewById(R.id.stock);
        ivProduct = (ImageView) contentView.findViewById(R.id.purchase_img);
        ivClosePopu = (ImageView) contentView.findViewById(R.id.purchase_close);
        tvBuyNum = (TextView) contentView.findViewById(R.id.value);
        llJoin = (RelativeLayout) contentView.findViewById(R.id.btn_shopping_car);
        ivClosePopu.setOnClickListener(this);
        ivProduct.setOnClickListener(this);
        btnSubtPro.setOnClickListener(this);
        ivShopCarChi.setOnClickListener(this);
        btnAddPro.setOnClickListener(this);
        popupWindow = new MyAlertDialog(this, R.style.loading,contentView);
        popupWindow.setCanceledOnTouchOutside(true);
    }

    private void initClick() {
        ivShopCarPar.setOnClickListener(this);
        btnPurchase.setOnClickListener(this);
        btnJoinShopCar.setOnClickListener(this);
        ll_xuangou_num.setOnClickListener(this);

        linComment.setOnClickListener(this);
        linProDetails.setOnClickListener(this);
        linSpecial.setOnClickListener(this);
        actionbar_Left.setOnClickListener(this);
        actionbar_Right.setOnClickListener(this);
    }
    public  final static String PAR_KEY = "ProductAttributes.par";
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.shopping_car:
                llJoin.setVisibility(View.VISIBLE);
                btnConfirBuy.setText("确认加入");
                btnConfirBuy.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        joinChopCar();
                    }
                });
                popupWindow.show();
                break;
            case R.id.purchase:
                llJoin.setVisibility(View.GONE);
                btnConfirBuy.setText("确认购买");
                btnConfirBuy.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                popupWindow.show();
                break;
            case R.id.purchase_close:
                popupWindow.dismiss();
                break;
            case R.id.subtraction:
                mProBuyNum--;
                if (mProBuyNum < 1) {
                    mProBuyNum = 1;
                }
                tvBuyNum.setText(mProBuyNum + "");
                break;
            case R.id.addition:
                if(mProductDetails==null){
                    return;
                }
                mProBuyNum++;
                if (mProBuyNum > mProductDetails.getProduct().getSalesRestriction()) {
                    mProBuyNum = mProductDetails.getProduct().getSalesRestriction();
                }
                tvBuyNum.setText(mProBuyNum + "");
                break;
            case R.id.toShopCar:
                shopcar_num.setText("");
                ivShopcarNum.setText("");
                shopcar_num.setVisibility(View.GONE);
                ivShopcarNum.setVisibility(View.GONE);
                break;
            case R.id.ll_xuangou_num:
                llJoin.setVisibility(View.GONE);
                btnConfirBuy.setText("确认购买");
                btnConfirBuy.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                popupWindow.show();
                break;
            case R.id.ll_user_rating:
                if(mProductDetails==null){
                    return;
                }
                 intent =new Intent(mContext, RatingActivity.class);
                intent.putExtra("productId",mPid);
                mContext.startActivity(intent);
                break;
            case R.id.ll_view_product_details:
                if(mProductDetails==null){
                    return;
                }
                intent =new Intent(mContext, ProPhotoTextActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelableArrayList(PAR_KEY,mProductDetails.getProductAttributes());
                mBundle.putString("phototext",mProductDetails.getDetalis());
                intent.putExtra("productDetails",mBundle);
                mContext.startActivity(intent);
                break;
            case R.id.advertisement:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) mProductDetails.getProductImgs());
                bundle.putInt("image_index", (Integer) v.getTag());
                intent.putExtras(bundle);
                intent.setClass(mContext, ImagePagerActivity.class);
                startActivity(intent);
                break;
        }
    }
    /**
     * 查询商品详情
     */
    private void startRequest() {
//        showLoading();
        params = new RequestParams();
        params.put("pid", String.valueOf(mPid));
        asyncHttpClient.post(HttpRequestUrl.USER_PRODUCTDETAILS, params, requstHandler);
    }

    /**
     * 确认购买
     */
    private void toPayfor(){
        asyncHttpClient.post(HttpRequestUrl.SHOPPINGCAR, params, requstHandler);
    }
    /**
     * 添加到购物车
     */
    private void joinChopCar() {
        showLoading();
        params = new RequestParams();
        if (mProductDetails.getTmInfo() != null && mProductDetails.getTmInfo().getPfid() > 0) {
            params.put("pfid", mProductDetails.getTmInfo().getPfid() + "");
        }
        params.put("pid", String.valueOf(mProductDetails.getProduct().getPid()));
        params.put("quantity", mProBuyNum + "");
        params.put("device", AladingApplication.getDevice(getApplication()));
        params.put("procurementLocation", mProductDetails.getProduct().getProcurementLocation() + "");
        params.put("deliveryType", mProductDetails.getProduct().getDeliveryType() + "");
        asyncHttpClient.post(HttpRequestUrl.SHOPPINGCAR, params, requstHandler);
    }

    private void initData() {
        if (mProductDetails.getProduct() != null) {
            initBannerPager(mProductDetails.getProductImgs());
            tvWarehouse.setText(mProductDetails.getProduct().getDeliveryInformation());//仓库名称
            LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG + mProductDetails.getProduct().getProductOrigin().getIcon(), ivCountry);//国旗
            tvProName.setText(mProductDetails.getProduct().getName());//商品名称
            tvProPrice.setText(String.valueOf(mProductDetails.getProduct().getPrice()));//商品价
            tvAlaPrice.setText("阿拉原价" + String.valueOf(mProductDetails.getProduct().getOriginalPrice()));//阿拉原价
            tvDomPrice.setText("(国内参考价)" + String.valueOf(mProductDetails.getProduct().getReferencePrice()));//参考价

            tvSelected.setText("1");//已选数量
            String destination = mContext.getResources().getString(R.string.destination);
            tvFreight.setText(mProductDetails.getProduct().getProductOrigin().getName() + "  到  " + destination + " 运费:" + mProductDetails.getProduct().getDistributionPrice());//运费
            tvTariff.setText(mProductDetails.getProduct().getTariffsDetail() + "");//关税
            tvCommentNum.setText(mProductDetails.getEvaluateCount() + "");


            mProBuyNum = Integer.parseInt(tvBuyNum.getText().toString());
            tvTotalPrice.setText(mProductDetails.getProduct().getPrice() + "");
            tvStock.setText(mProductDetails.getProduct().getStock() + "");
            tvSalesRestriction.setText(mProductDetails.getProduct().getSalesRestriction() + "");
            LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG + mProductDetails.getProduct().getIcon(), ivProduct);
        }
    }

    private void initBannerPager(List<ProductDetails.ProductImage> bannerList) {
        initDots(bannerList.size());
        viewAdapter = new BannerProAdapter(bannerList, mContext);
        productImagePagger.setAdapter(viewAdapter);
        productImagePagger.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                int index = arg0 % advertisement_dots.getChildCount();
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
        productImagePagger.startAutoCycle();
    }

    private void initDots(int count) {
        for (int j = 0; j < count; j++) {
            advertisement_dots.addView(initDot());
        }
        if (count > 0) {
            advertisement_dots.getChildAt(0).setSelected(true);
        }
    }

    private View initDot() {
        return LayoutInflater.from(mContext).inflate(R.layout.dot_layout, null);
    }



    @Override
    public void httpStart(String urlPath) {
        super.httpStart(urlPath);

    }

    @Override
    public void httpSuccess(String urlPath, int statusCode, Object obj) {
        super.httpSuccess(urlPath, statusCode, obj);
        if (HttpRequestUrl.USER_PRODUCTDETAILS.equals(urlPath)) {
            analyticalProductJson(obj.toString());
        }
        if (HttpRequestUrl.SHOPPINGCAR.equals(urlPath)) {
            String strCount = shopcar_num.getText().toString();
            int count = StringUtil.isNumeric(strCount) ? Integer.parseInt(strCount) : 0;
            shopcar_num.setText((mProBuyNum+count)+"");
            ivShopcarNum.setText((mProBuyNum+count)+"");
            shopcar_num.setVisibility(View.VISIBLE);
            ivShopcarNum.setVisibility(View.VISIBLE);
            popupWindow.dismiss();
        }
    }

    @Override
    public void httpFailed(String requset, String errorInfo, String content) {
        super.httpFailed(requset, errorInfo, content);
        showToast(errorInfo);
    }

    @Override
    public void httpFinish(String urlPath) {
        super.httpFinish(urlPath);
        hideLoading();
    }

    private void analyticalProductJson(String json) {
        try {
            JSONObject obj = new JSONObject(json.toString());
            if (obj == null) {
                return;
            }
            //商品详情实例
            mProductDetails = baseGson.fromJson(json, ProductDetails.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initData();
    }


}
