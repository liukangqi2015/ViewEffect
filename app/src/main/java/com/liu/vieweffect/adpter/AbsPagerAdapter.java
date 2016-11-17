package com.liu.vieweffect.adpter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 抽象的PagerAdapter实现类,封装了内容为View的公共操作.
 * Created by liu on 2016/11/17.
 */
public abstract class AbsPagerAdapter extends PagerAdapter{
    protected SparseArray<View> mViews;

    public AbsPagerAdapter() {
        mViews = new SparseArray<>();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = newView(container,position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    public abstract View newView(ViewGroup container,int position);

    public void notifyUpdateView(int position) {
        View view = updateView(mViews.get(position), position);
        mViews.put(position, view);
        notifyDataSetChanged();
    }

    public View updateView(View view, int position) {
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }
}
