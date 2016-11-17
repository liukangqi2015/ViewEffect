package com.liu.vieweffect.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 海报Adapter
 * Created by liu on 2016/11/17.
 */
public class PostersPagerAdapter extends AbsListPagerAdapter<Integer>{
    private LayoutInflater inflater;
    public PostersPagerAdapter(Context context,List<Integer> data) {
        super(context,data);
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public View newView(ViewGroup container,int position) {
        ImageView poster_iv= new ImageView(mContext);
        poster_iv.setImageResource(mData.get(position));
        return poster_iv;
    }
}
