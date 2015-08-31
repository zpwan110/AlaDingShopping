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
        initImageLoader(this);
        Bundle bundle = getIntent().getExtras();
        List<ProductDetails.ProductImage> images = (List<ProductDetails.ProductImage>) bundle.getSerializable(IMAGES);
        int pagerPosition = bundle.getInt(IMAGE_POSITION, 0);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        pager = (HackyViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ImagePagerAdapter(images,this));
        pager.setCurrentItem(pagerPosition);

    }

    public  void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private List<ProductDetails.ProductImage> images;
        private LayoutInflater inflater;
        private Context mContext;

        ImagePagerAdapter(List<ProductDetails.ProductImage> images,Context context) {
            this.images = images;
            this.mContext=context;
            inflater = getLayoutInflater();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public void finishUpdate(View container) {
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);

            PhotoView imageView = (PhotoView) imageLayout.findViewById(R.id.image);
            final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
            PhotoView photoView = new PhotoView(view.getContext());
//            photoView.setImageResource(sDrawables[position]);
            LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG+images.get(position).getImg(),photoView);
            // Now just add PhotoView to ViewPager and return it
            view.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            ((ViewPager) view).addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View container) {
        }
    }
}