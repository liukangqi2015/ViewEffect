package com.liu.vieweffect.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liu.vieweffect.R;

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
        View view=inflater.inflate(R.layout.posters_view_layout,container,false);
        ImageView poster_iv= (ImageView) view.findViewById(R.id.poster_iv);
        poster_iv.setImageResource(mData.get(position));
        return poster_iv;
    }
}
