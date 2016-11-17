package com.liu.vieweffect.adpter;

import android.content.Context;

import java.util.List;

/**
 *  抽象的PagerAdapter实现类,封装了内容为View,数据为List类型的适配器实现.
 * Created by liu on 2016/11/17.
 */
public abstract class AbsListPagerAdapter<T> extends AbsPagerAdapter{
    protected Context mContext;
    protected List<T> mData;

    public AbsListPagerAdapter(Context context,List<T> data) {
        mData = data;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    public T getItem(int position) {
        return mData.get(position);
    }
}
