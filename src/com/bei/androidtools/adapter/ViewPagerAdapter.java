package com.bei.androidtools.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mPageViews;

    public ViewPagerAdapter(List<View> pageViews) {
        this.mPageViews = pageViews;
    }

    @Override
    public void destroyItem(View v, int position, Object arg2) {
        ((ViewPager) v).removeView(mPageViews.get(position));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public int getCount() {
        return mPageViews.size();
    }

    @Override
    public Object instantiateItem(View v, int position) {
        ((ViewPager) v).addView(mPageViews.get(position));
        return mPageViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View v, Object arg1) {
        return v.equals(arg1);
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
    
    
    
    

}
