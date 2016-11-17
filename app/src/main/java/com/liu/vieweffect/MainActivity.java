package com.liu.vieweffect;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.liu.vieweffect.adpter.PostersPagerAdapter;
import com.liu.vieweffect.animation.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager posterViewPager;
    private int[] img_resId={R.mipmap.x,R.mipmap.sharenhuiyi,R.mipmap.iron_man,R.mipmap.wolverine};
    private List<Integer> mData=new ArrayList<>();
    private PostersPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posterViewPager= (ViewPager) findViewById(R.id.poster_vp);
        for (int i=0;i<img_resId.length;i++){
            mData.add(img_resId[i]);
        }
        //设置缓存的页面数量
        posterViewPager.setOffscreenPageLimit(2);
        posterViewPager.setPageTransformer(false, new ZoomOutPageTransformer());
        mAdapter=new PostersPagerAdapter(this,mData);
        posterViewPager.setAdapter(mAdapter);
    }
}
