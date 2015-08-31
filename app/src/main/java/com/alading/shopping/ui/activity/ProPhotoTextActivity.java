package com.alading.shopping.ui.activity;


import android.content.Context;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.alading.shopping.R;
import com.alading.shopping.modle.bean.ProductAttributes;
import com.alading.shopping.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
public class ProPhotoTextActivity extends BaseActivity implements OnClickListener{


	private TextView actionbar_Left;
	private ViewPager mPager;
	private TextView tv_Photo;
	private TextView tv_Parma;
	private TextView tv_FAQ;
	private ImageView iv_bottom;

	private ParamAdapter myAdapter;
	private LayoutInflater layoutInflater;
	private View photoView;
	private View paramView;
	private View faqView;
	private String phototext;
	private ArrayList<ProductAttributes> paramList;
	private WebView webView;
	private ListView paramListView;
	private MyPagerAdapter pagAdapter;
	private WebSettings mWebSettings;
	private ProPhotoTextActivity context;
	private WebSettings webSettings;
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private int offset;
	private ArrayList<View> listView;

	@Override
	protected void onPreOnCreate(Bundle savedInstanceState) {
		super.onPreOnCreate(savedInstanceState);
		Bundle mbundle= getIntent().getBundleExtra("productDetails");
		phototext = mbundle.getString("phototext");
		paramList = mbundle.getParcelableArrayList(ProductDetailsActivity.PAR_KEY);
		this.context = this;
//		faq = getIntent().getStringExtra("faq");
	}
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		setContentView(R.layout.product_details_html);
		initActionBar();
		initView();
	}
	private void initActionBar() {
		actionbar_Left = (TextView) findViewById(R.id.back_title);
		actionbar_Left.setVisibility(View.VISIBLE);
		actionbar_Left.setText(R.string.product);
		actionbar_Left.setOnClickListener(this);
		TextView title = (TextView) findViewById(R.id.actionbar_title);
		title.setText(this.getResources().getString(R.string.phototext));
	}
	private void initView(){
		layoutInflater = LayoutInflater.from(ProPhotoTextActivity.this);
		photoView = layoutInflater.inflate(R.layout.pro_photo, null);
		webView = (WebView)photoView.findViewById(R.id.webViewPhoto);
		webSettings = webView.getSettings();
		webSettings.setDisplayZoomControls(false);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
//		webView.setInitialScale(100);
		webSettings.setJavaScriptEnabled(true);
		//扩大比例的缩放
		webSettings.setUseWideViewPort(true);
//自适应屏幕
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		webView.loadData(phototext, "text/html", "UTF-8");
		paramView = layoutInflater.inflate(R.layout.pro_param, null);
		paramListView = (ListView)paramView.findViewById(R.id.paramListView);
		myAdapter = new ParamAdapter(paramList,this);
		paramListView.setAdapter(myAdapter);
		faqView = layoutInflater.inflate(R.layout.pro_faq, null);

		listView = new ArrayList<View>();
		listView.add(photoView);
		listView.add(paramView);
		listView.add(faqView);
		pagAdapter = new MyPagerAdapter(listView);
		mPager = (ViewPager)findViewById(R.id.contentLayout);
		mPager.setAdapter(pagAdapter);
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			@Override
			public void onPageSelected(int position) {
				changStatue(position);
			}
			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		tv_Photo = (TextView)findViewById(R.id.tv_Photo);
		tv_Parma = (TextView)findViewById(R.id.tv_Parma);
		tv_FAQ = (TextView)findViewById(R.id.tv_FAQ);

		tv_Photo.setOnClickListener(this);
		tv_Parma.setOnClickListener(this);
		tv_FAQ.setOnClickListener(this);
		initImageView();
		mPager.setCurrentItem(0);
	}

	@Override
	public void onClick(View v) {
			switch (v.getId()){
				case R.id.back_title:
					finish();
					break;
			}
	}
	/**
	 2      * 初始化动画
	 3 */

	private void initImageView() {
		iv_bottom= (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.select_bttom).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW /3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		iv_bottom.setImageMatrix(matrix);// 设置动画初始位置
	}



	private void changStatue(int state){
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
		Animation animation = null;
		switch (state) {
			case 0:
				tv_Photo.setTextColor(context.getResources().getColor(R.color.title_bg));
				tv_Parma.setTextColor(context.getResources().getColor(R.color.black));
				tv_FAQ.setTextColor(context.getResources().getColor(R.color.black));
				break;
			case 1:
				tv_Photo.setTextColor(context.getResources().getColor(R.color.black));
				tv_Parma.setTextColor(context.getResources().getColor(R.color.title_bg));
				tv_FAQ.setTextColor(context.getResources().getColor(R.color.black));
				break;
			case 2:
				tv_Photo.setTextColor(context.getResources().getColor(R.color.black));
				tv_Parma.setTextColor(context.getResources().getColor(R.color.black));
				tv_FAQ.setTextColor(context.getResources().getColor(R.color.title_bg));
				break;
		}
		animation = new TranslateAnimation(one*currIndex, one*state, 0, 0);
		currIndex = state;
		animation.setFillAfter(true);// True:图片停在动画结束位置
		animation.setDuration(300);
		iv_bottom.startAnimation(animation);
	}

	class  ParamAdapter extends BaseAdapter {
		private final ArrayList<ProductAttributes> paramList;
		private final Context context;

		public ParamAdapter(ArrayList<ProductAttributes> paramList,Context context){
			this.paramList = paramList;
			this.context = context;
		}
		@Override
		public int getCount() {
			return paramList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return paramList.get(position).getAid();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder =null;
			if(convertView==null){
				holder = new Holder();
				convertView = LayoutInflater.from(context).inflate(R.layout.param_item,null);
				holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
				holder.tvValue = (TextView)convertView.findViewById(R.id.tvValue);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.tvTitle.setText(paramList.get(position).getName());
			holder.tvValue.setText(paramList.get(position).getValue());
			return convertView;
		}

		class Holder{
			TextView tvTitle,tvValue;
		}
	}
	class MyPagerAdapter extends PagerAdapter {

		private List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{
			container.removeView(mListViews.get(position));
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}
		@Override
		public int getCount() {
			return  mListViews.size();
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
	};

}
