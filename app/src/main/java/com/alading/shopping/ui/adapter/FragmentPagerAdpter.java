package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.alading.shopping.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/25.
 */
public class FragmentPagerAdpter extends  FragmentPagerAdapter{
    private final Context mContext;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

    static final class TabInfo {

        private final Class<?> mClss;
        private final Bundle mArgs;

        TabInfo(Class<?> aClass, Bundle args) {
            mClss = aClass;
            mArgs = args;
        }
    }

    public FragmentPagerAdpter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        return BaseFragment.instantiate(mContext, info.mClss.getName(), info.mArgs);
    }

    public void addTab(Class<?> clss, Bundle args) {
        TabInfo info = new TabInfo(clss, args);
        mTabs.add(info);
        notifyDataSetChanged();
    }


}
