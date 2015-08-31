package com.alading.shopping.ui.appwidget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

/**
 * Created by Administrator on 2015/8/26.
 */
public class BannerPagerView extends ViewPager {

    /** 触摸时按下的点 **/
    PointF downP = new PointF();
    /** 触摸时当前的点 **/
//    PointF curP = new PointF();
    private Context context;
    private BannerPagerView viewpager;
    private Timer mCycleTimer;
    private TimerTask mCycleTask;

    private Long mydelay = 4000l;
    private float curP;

    public BannerPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.viewpager = this;
    }

    public BannerPagerView(Context context) {
        super(context);
        this.context = context;
        this.viewpager = this;
    }

    /**
     * 处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        switch (arg0.getAction()) {
            case MotionEvent.ACTION_DOWN:
                releaseTimer();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startAutoCycle();
                break;
        }
        return super.onTouchEvent(arg0);
    }
    float firstY ,lastY ;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        float firstX = arg0.getX();

        switch (arg0.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstY = arg0.getY();
                releaseTimer();
                break;
            case MotionEvent.ACTION_MOVE:
                lastY= arg0.getY();

               break;
            case MotionEvent.ACTION_CANCEL:
                startAutoCycle();
                break;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    private void releaseTimer() {
        if(mCycleTimer!=null){
            mCycleTimer.cancel();
        }
        if(mCycleTask!=null){
            mCycleTask.cancel();
        }
    }

    public void startAutoCycle() {
        if (mCycleTimer != null)
            mCycleTimer.cancel();
        if (mCycleTask != null)
            mCycleTask.cancel();
        mCycleTimer = new Timer();
        mCycleTask = new TimerTask() {
            @Override
            public void run() {
                mh.sendEmptyMessage(0);
            }
        };
        mCycleTimer.schedule(mCycleTask, mydelay, mydelay);
    }

    Handler mh = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            moveNextPosition(true);
        }
    };
    /**
     * move to next banner.
     */
    public void moveNextPosition(boolean smooth) {
        if (viewpager != null) {
            if (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1) {
                viewpager.setCurrentItem(0);
            } else {
                viewpager
                        .setCurrentItem(viewpager.getCurrentItem() + 1, smooth);
            }
        }
    }



}
