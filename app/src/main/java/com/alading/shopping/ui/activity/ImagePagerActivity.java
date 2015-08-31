package com.alading.shopping.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alading.library.util.extend.draw.DensityUtils;
import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.ProductDetails;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.appwidget.viewpagerindicator.HackyViewPager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Administrator on 2015/8/30.
 */
public class ImagePagerActivity extends Activity {

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private static final String STATE_POSITION = "STATE_POSITION";
    private static final String IMAGES = "images";
    private static final String IMAGE_POSITION = "image_index";

    HackyViewPager pager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        Bundle bundle = getIntent().getExtras();
        List<ProductDetails.ProductImage> images = (List<ProductDetails.ProductImage>) bundle.getSerializable(IMAGES);
        int pagerPosition = bundle.getInt(IMAGE_POSITION, 0);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        pager = (HackyViewPager) findViewById(R.id.pager);
        pager.setAdapter(new SamplePagerAdapter(images,this));
        pager.setCurrentItem(pagerPosition);

    }
    static class SamplePagerAdapter extends PagerAdapter {
        private final Context mContext;
        private List<ProductDetails.ProductImage> images;
        public SamplePagerAdapter(List<ProductDetails.ProductImage> images,Context context) {
            this.images = images;
            this.mContext=context;

        }
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG+images.get(position).getImg(),photoView);
            photoView.setPadding(DensityUtils.dipTopx(mContext,10),0,DensityUtils.dipTopx(mContext,10),0);
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}