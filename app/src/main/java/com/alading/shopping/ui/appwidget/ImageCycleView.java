package com.alading.shopping.ui.appwidget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.ProductDetails;
import com.alading.shopping.modle.constant.HttpServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zp
 *
 */
public class ImageCycleView extends FrameLayout {
    // 自动轮播启用开关
    private final static boolean isAutoPlay = true;
    // 自定义轮播图的资源
    private List<ProductDetails.ProductImage> adList;
    // 放圆点的View的list
    private List<View> dotViewsList;
    private ChildViewPager viewPager;
    // 当前轮播页
    private int currentItem = 0;
//    private TextView tv_title;

    private MyPagerAdapter myPagerAdapter = null;
    // 定时任务
    private ScheduledExecutorService scheduledExecutorService;
    private Context context;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem, true);
        }
    };

    public ImageCycleView(Context context) {
        this(context, null);
    }

    public ImageCycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageCycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    private Handler mHandler;
    private int clickFlag = 0;

    public int getClickFlag() {
        return clickFlag;
    }

    public void setClickFlag(int clickFlag) {
        this.clickFlag = clickFlag;
    }

    public void init(Handler mHandler, List<ProductDetails.ProductImage> adList) {
        this.mHandler = mHandler;
        this.adList = adList;
        myPagerAdapter = new MyPagerAdapter();
        stopPlay();
        initData();
        if (isAutoPlay) {
            startPlay();
        }
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        dotViewsList = new ArrayList<View>();
        initUI(context);
    }

    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.product_banner, this, true);
        LinearLayout dotLayout = (LinearLayout) rootView.findViewById(R.id.dotLayout);
//        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        dotLayout.removeAllViews();
        if (myPagerAdapter.listImagesView.size() > 0) {
            myPagerAdapter.listImagesView.clear();
            dotLayout.removeAllViews();
            dotViewsList.clear();
            myPagerAdapter.notifyDataSetChanged();
        }

//        if (adList != null || adList.size() > 0) {
//            tv_title.setText(adList.get(0).getTitle());
//        }

        // 热点个数与图片特殊相等
        for (int i = 0; i < adList.size(); i++) {
            final ImageView view = new ImageView(context);

            view.setTag(i);
            if (i == 0) { // 给一个默认图
                view.setBackgroundResource(R.drawable.banner_load);
            }
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG +adList.get(i).getImg(),view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = clickFlag;
                    msg.obj = view.getTag();
                    mHandler.sendMessage(msg);
                }
            });
            myPagerAdapter.listImagesView.add(view);
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }
        viewPager = (ChildViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        private List<ImageView> listImagesView;

        public MyPagerAdapter() {
            listImagesView = new ArrayList<ImageView>();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ChildViewPager) container).removeView(listImagesView.get(position));
        }

        @Override
        public Object instantiateItem(View container, final int position) {
            ((ChildViewPager) container).addView(listImagesView.get(position));
            return listImagesView.get(position);
        }

        @Override
        public int getCount() {
            return listImagesView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    /**
     * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ChildViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int pos) {
//            tv_title.setText(adList.get(pos).getTitle());
            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewsList.get(pos)).setBackgroundResource(R.drawable.splash_dot2_w);
                } else {
                    ((View) dotViewsList.get(i)).setBackgroundResource(R.drawable.splash_dot1_w);
                }
            }
        }

    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {
        @Override
        public void run() {
            synchronized (viewPager) {
                if (null != myPagerAdapter.listImagesView && myPagerAdapter.listImagesView.size() > 1) {
                    currentItem = (currentItem + 1) % myPagerAdapter.listImagesView.size();
                    handler.obtainMessage().sendToTarget();
                }
            }
        }

    }
}
