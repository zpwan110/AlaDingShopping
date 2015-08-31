package com.alading.shopping;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alading.library.util.netstate.TANetChangeObserver;
import com.alading.library.util.netstate.TANetWorkUtil;
import com.alading.library.util.netstate.TANetworkStateReceiver;
import com.alading.shopping.ui.adapter.FragmentPagerAdpter;
import com.alading.shopping.ui.appwidget.MyPagerView;
import com.alading.shopping.ui.base.BaseActivity;
import com.alading.shopping.ui.fragment.IndexFragment;
import com.alading.shopping.ui.fragment.MyCenterFragment;
import com.alading.shopping.ui.fragment.ShopcarFragment;
import com.alading.shopping.R;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{


    private ViewPager viewPager;
    private ImageView ivIndex,ivShopcar,ivCenter;
    private TextView title;
    private TextView right_set;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        Log.i("tag","networkInfo  是否有网络连接"+ TANetWorkUtil.isNetworkConnected(this));
//        Log.i("tag", "networkInfo 获取当前的网络状态" + TANetWorkUtil.getAPNType(this));
//        Log.i("tag","networkInfo 获取当前网络连接的类型"+TANetWorkUtil.getConnectedType(this));
//        Log.i("tag","networkInfo 判断MOBILE网络是否可用"+TANetWorkUtil.isMobileConnected(this));
//        Log.i("tag","networkInfo 判断WIFI网络是否可用"+TANetWorkUtil.isWifiConnected(this));
//        Log.i("tag","networkInfo 网络是否可用"+TANetWorkUtil.isNetworkAvailable(this));
    }

    private void initView() {
        right_set = (TextView) findViewById(R.id.actionbar_right_text);
        title = (TextView)findViewById(R.id.actionbar_title);
        title.setText("阿拉海购");
        viewPager = (MyPagerView) findViewById(R.id.contentLayout);
        ivIndex = (ImageView) findViewById(R.id.iv_index);
        ivShopcar = (ImageView) findViewById(R.id.iv_shopcar);
        ivCenter = (ImageView) findViewById(R.id.iv_center);
        FragmentPagerAdpter adpter = new FragmentPagerAdpter(this);
        adpter.addTab(IndexFragment.class, null);
        adpter.addTab(ShopcarFragment.class, null);
        adpter.addTab(MyCenterFragment.class, null);
        viewPager.setAdapter(adpter);

        viewPager.setOnPageChangeListener(this);
        ivIndex.setOnClickListener(this);
        ivShopcar.setOnClickListener(this);
        ivCenter.setOnClickListener(this);
        viewPager.setCurrentItem(0);
        setTitleStatus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_index:
                title.setText("阿拉海购");
                right_set.setText("");
                right_set.setEnabled(false);
                viewPager.setCurrentItem(0);
                break;
            case R.id.iv_shopcar:
                title.setText("购物袋");
                right_set.setText("编辑");
                right_set.setEnabled(true);
                right_set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewPager.setCurrentItem(1);
                break;
            case R.id.iv_center:
                title.setText("个人中心");
                right_set.setText("设置");
                right_set.setEnabled(true);
                right_set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewPager.setCurrentItem(2);
                break;

        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setTitleStatus();
    }

    // 设置按钮的选中和未选中资源
    private void setTitleStatus() {
        if (viewPager.getCurrentItem() == 0) {
            ivIndex.setImageResource(R.drawable.sea_highlight);
            ivShopcar.setImageResource(R.drawable.shopping_cart_normal);
            ivCenter.setImageResource(R.drawable.aladdin_normal);
        } else if (viewPager.getCurrentItem() == 1) {
            ivIndex.setImageResource(R.drawable.sea_normal);
            ivShopcar.setImageResource(R.drawable.shopping_cart_highlight);
            ivCenter.setImageResource(R.drawable.aladdin_normal);
        } else {
            ivIndex.setImageResource(R.drawable.sea_normal);
            ivShopcar.setImageResource(R.drawable.shopping_cart_normal);
            ivCenter.setImageResource(R.drawable.aladdin_highlight);
        }
    }


}
