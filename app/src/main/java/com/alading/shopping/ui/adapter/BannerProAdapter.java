package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.alading.shopping.modle.bean.ProductDetails;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.activity.ImagePagerActivity;
import com.alading.shopping.ui.activity.ProductDetailsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/26.
 */
public class BannerProAdapter extends PagerAdapter {

    private List<View> views = new ArrayList<View>();
    private List<ProductDetails.ProductImage> bannerList;
    private Context mContext;

    public BannerProAdapter(List<ProductDetails.ProductImage> bannerList, Context context) {
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
                    HttpServerPort.PUBLIC_IMG+ bannerList.get(position % bannerList.size()).getImg(),
                    imageView);
        }
        // View view = data.get(position % data.size());
        if (view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        views.add(view);
        ((ViewPager) container).addView(view, position % bannerList.size());
        imageView.setTag(position % bannerList.size());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) ProductDetailsActivity.mProductDetails.getProductImgs());
                bundle.putInt("image_index", (Integer) v.getTag());
                intent.putExtras(bundle);
                intent.setClass(mContext, ImagePagerActivity.class);
                mContext.startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position % views.size()));
    }

}
