package com.alading.shopping.ui.appwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import com.alading.shopping.R;

/**
 * Created by Administrator on 2015/8/26.
 */

public class NumberClock extends LinearLayout {
    private View view;
    private LayoutInflater inflater;
    private TextView tv_hour_decade;// 小时，十位
    private TextView tv_hour_unit;// 小时，个位
    private TextView tv_min_decade;// 分钟，十位
    private TextView tv_min_unit;// 分钟，个位
    private TextView tv_sec_decade;// 秒，十位
    private TextView tv_sec_unit;// 秒，个位
    private Context context;

    private int hour_decade;
    private int hour_unit;
    private int min_decade;
    private int min_unit;
    private int sec_decade;
    private int sec_unit;
    private Timer timer;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            countDown();
        }
    };

    /**
     * 时钟
     */
    @SuppressLint("HandlerLeak")
    public NumberClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.common_home_clock, this);

    }

    private void initView() {
        if (!this.isInEditMode()) {
            return;
        }
        tv_hour_decade = (TextView) view.findViewById(R.id.tv_hour_decade);
        tv_hour_unit = (TextView) view.findViewById(R.id.tv_hour_unit);
        tv_min_decade = (TextView) view.findViewById(R.id.tv_min_decade);
        tv_min_unit = (TextView) view.findViewById(R.id.tv_min_unit);
        tv_sec_decade = (TextView) view.findViewById(R.id.tv_sec_decade);
        tv_sec_unit = (TextView) view.findViewById(R.id.tv_sec_unit);
    }

    //开始计时
    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }

    //停止计时
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //设置倒计时的时长
    public void setTime(int hour, int min, int sec) {

        if (hour >= 60 || min >= 60 || sec >= 60 || hour < 0 || min < 0
                || sec < 0) {
            throw new RuntimeException("Time format is error,please check out your code");
        }

        hour_decade = hour / 10;
        hour_unit = hour - hour_decade * 10;

        min_decade = min / 10;
        min_unit = min - min_decade * 10;

        sec_decade = sec / 10;
        sec_unit = sec - sec_decade * 10;

        tv_hour_decade.setText(hour_decade + "");
        tv_hour_unit.setText(hour_unit + "");
        tv_min_decade.setText(min_decade + "");
        tv_min_unit.setText(min_unit + "");
        tv_sec_decade.setText(sec_decade + "");
        tv_sec_unit.setText(sec_unit + "");

    }

    //倒计时
    private void countDown() {
        if (isCarry4Unit(tv_sec_unit)) {
            if (isCarry4Decade(tv_sec_decade)) {
                if (isCarry4Unit(tv_min_unit)) {
                    if (isCarry4Decade(tv_min_decade)) {
                        if (isCarry4Unit(tv_hour_unit)) {
                            if (isCarry4Decade(tv_hour_decade)) {
//                                Toast.makeText(context, "时间到了", Toast.LENGTH_SHORT).show();
                                stop();
                            }
                        }
                    }
                }
            }
        }
    }

    //变化十位，并判断是否需要进位
    private boolean isCarry4Decade(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    //变化个位，并判断是否需要进位
    private boolean isCarry4Unit(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }
}
