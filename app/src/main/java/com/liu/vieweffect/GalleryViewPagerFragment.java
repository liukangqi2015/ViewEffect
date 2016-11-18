package com.liu.vieweffect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liu.vieweffect.adpter.GalleryPagerAdapter;
import com.liu.vieweffect.animation.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * GalleryFragment
 * Created by liu on 2016/11/18.
 */
public class GalleryViewPagerFragment extends Fragment implements GalleryPagerAdapter.OnPagerClickListener{
    private ViewPager posterViewPager;
    private LinearLayout main_ll;
    private int[] img_resId={R.mipmap.x,R.mipmap.sharenhuiyi,R.mipmap.iron_man,R.mipmap.wolverine};
    private List<Integer> mData=new ArrayList<>();
    private GalleryPagerAdapter galleryPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery,container,false);
        posterViewPager= (ViewPager) view.findViewById(R.id.poster_vp);
        main_ll= (LinearLayout) view.findViewById(R.id.main_ll);
        for (int i=0;i<img_resId.length;i++){
            mData.add(img_resId[i]);
        }
        //设置缓存的页面数量
        posterViewPager.setOffscreenPageLimit(2);
        posterViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        galleryPagerAdapter=new GalleryPagerAdapter(mData,getActivity(),R.layout.posters_view_layout);
        galleryPagerAdapter.setOnPagerClickListener(this);
        //让用户滑动ViewPager边缘也能切换Pager
        main_ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return posterViewPager.dispatchTouchEvent(event);
            }
        });
        posterViewPager.setAdapter(galleryPagerAdapter);
        return view;
    }

    @Override
    public void onPagerClick(View view, int position) {
        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
    }
}
