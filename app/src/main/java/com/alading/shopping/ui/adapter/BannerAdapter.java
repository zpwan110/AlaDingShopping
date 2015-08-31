package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.HomeIndexAdverts;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.activity.ProductDetailsActivity;
import com.alading.shopping.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/26.
 */
public class BannerAdapter extends PagerAdapter {

    private List<View> views = new ArrayList<View>();
    private List<HomeIndexAdverts> bannerList;
    private Context mContext;
    private Intent intent;

    public BannerAdapter(List<HomeIndexAdverts> bannerList ,Context context) {
        super();
        this.bannerList = bannerList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // container.addView(data.get(position));
        View view = LayoutInflater.from(mContext).inflate(R.layout.advertsement_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.centent_iv);
        TextView textview = (TextView) view.findViewById(R.id.centent_text);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(bannerList.size()>0){
            LoaderImage.loadPhoto(
                    HttpServerPort.PUBLIC_IMG+ bannerList.get(position % bannerList.size()).getIcon(),
                    imageView);

        // View view = data.get(position % data.size());
        if (view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        views.add(view);
        ((ViewPager) container).addView(view, position % bannerList.size());
        imageView.setTag(bannerList.get(position % bannerList.size()));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeIndexAdverts bannerHomeIndex = (HomeIndexAdverts) v.getTag();
//                index = (Integer) msg.obj;
//                if( home_IndexAdverts_data.get(index).getType() == 0){
//                    mContext, bannerHomeIndex.getContent()
//                }else {
//                    MainWebActivity.callMe(getActivity(),home_IndexAdverts_data.get(index).getContent(),"商品详情");
//                }

                switch (bannerHomeIndex.getType()){
                    case 0:
                    case 1:
                        intent =new Intent(mContext, ProductDetailsActivity.class);
                        intent.putExtra("productId", bannerHomeIndex.getContent() + "");
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(mContext, WebViewActivity.class);
                        intent.putExtra("weburl",bannerHomeIndex.getContent()+"");
                        intent.putExtra("webtitle","百度一下");
                        mContext.startActivity(intent);
                        break;
                }

            }
        });
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position % views.size()));
    }

}
