package com.liu.vieweffect.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liu.vieweffect.R;

import java.util.List;

/**
 * 展示海报PagerAdapter
 * Created by liu on 2016/11/17.
 */
public class GalleryPagerAdapter extends PagerAdapter {
    private OnPagerClickListener onPagerClickListener;
    private List<Integer> mDatas;
    private Context mContext;
    private int layoutId;
    private LayoutInflater inflater;

    public GalleryPagerAdapter(List<Integer> mDatas, Context mContext, int layoutId) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.layoutId = layoutId;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=inflater.inflate(layoutId,null);
        ImageView poster_iv= (ImageView) view.findViewById(R.id.poster_iv);
        poster_iv.setImageResource(mDatas.get(position));
        ViewCompat.setTransitionName(poster_iv,"image"+position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPagerClickListener!=null){
                    onPagerClickListener.onPagerClick(v,position);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=(View) object;
        container.removeView(view);
    }

    public void setOnPagerClickListener(OnPagerClickListener onPagerClickListener){
        this.onPagerClickListener=onPagerClickListener;
    }


    public interface OnPagerClickListener{
        void onPagerClick(View view,int position);
    }
}
