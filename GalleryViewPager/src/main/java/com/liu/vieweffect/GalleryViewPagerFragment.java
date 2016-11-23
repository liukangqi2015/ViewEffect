package com.liu.vieweffect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
    private FrameLayout main_ll;
    private int[] img_resId={R.mipmap.x,R.mipmap.sharenhuiyi,R.mipmap.iron_man,R.mipmap.wolverine};
    private List<Integer> mData=new ArrayList<>();
    private GalleryPagerAdapter galleryPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery,container,false);
        posterViewPager= (ViewPager) view.findViewById(R.id.poster_vp);
        main_ll= (FrameLayout) view.findViewById(R.id.main_fl);
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
        Intent intent=new Intent(getActivity(),DetailActivity.class);
        Bundle bundle=captureValues(view.findViewById(R.id.poster_iv));
        bundle.putInt(DetailActivity.IMAGE_ID,mData.get(position));
        intent.putExtra(DetailActivity.BUNDLE,bundle);
        startActivity(intent);
        //去除默认转场的动画
        getActivity().overridePendingTransition(0,0);
    }

    public static Bundle captureValues(@NonNull View view) {
        int[] screenLocation = new int[2];
        view.getLocationOnScreen(screenLocation);
        Bundle b = new Bundle();
        int left = screenLocation[0];
        int top = screenLocation[1];
        int width = view.getWidth();
        int height = view.getHeight();
        b.putInt("left", left);
        b.putInt("top", top);
        b.putInt("width", width);
        b.putInt("height", height);
        return b;
    }
}
