package com.liu.androiddrawstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.liu.androiddrawstudy.R;
import com.liu.androiddrawstudy.fragment.SimpleCardFragment;

import java.util.ArrayList;

/**
 * 演示画布操作的Activity
 * Created by liu on 2017/2/7.
 */

public class CanvasOperationActivity extends AppCompatActivity{
    private TabLayout tab_layout;
    private ViewPager view_pager;

    private String[] mTitles = {"位移", "缩放", "旋转"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_operation);
        initView();
        initData();
    }

    private void initView() {
        tab_layout= (TabLayout) findViewById(R.id.tab_layout);
        view_pager= (ViewPager) findViewById(R.id.vp);
    }

    private void initData() {
        for (int i = 0; i <mTitles.length ; i++) {
            SimpleCardFragment simpleCardFrag=SimpleCardFragment.getInstance(mTitles[i]);
            mFragments.add(simpleCardFrag);
        }
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}
