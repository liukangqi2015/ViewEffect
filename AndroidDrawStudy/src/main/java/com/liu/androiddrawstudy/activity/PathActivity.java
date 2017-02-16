package com.liu.androiddrawstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liu.androiddrawstudy.R;
import com.liu.androiddrawstudy.fragment.BezierViewFragment;
import com.liu.androiddrawstudy.fragment.PathBasicGraphicsFragment;
import com.liu.androiddrawstudy.fragment.PathLineViewFragment;
import com.liu.androiddrawstudy.fragment.TouchPathViewFragment;

/**
 * 演示Path的基本操作的Activity
 * Created by liu on 2017/2/14.
 */

public class PathActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mViewToolbar;
    private ListView left_drawer_list;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] titles = {"Path-直线", "Path-基本图形", "Path-贝塞尔曲线","Path-绘制手势轨迹的View"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewToolbar= (Toolbar) findViewById(R.id.view_toolbar);
        left_drawer_list= (ListView) findViewById(R.id.left_drawer);

        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, mViewToolbar, R.string.open,
                R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void setListener() {
        left_drawer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        switchFragment(0,new PathLineViewFragment());
                        break;
                    case 1:
                        switchFragment(1,new PathBasicGraphicsFragment());
                        break;
                    case 2:
                        switchFragment(2,new BezierViewFragment());
                        break;
                    case 3:
                        switchFragment(3,new TouchPathViewFragment());
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
            }
        });
    }


    private void initData() {
        //设置菜单列表
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        left_drawer_list.setAdapter(arrayAdapter);
        switchFragment(0,new PathLineViewFragment());
    }

    private void switchFragment(int position,Fragment fragment){
        mViewToolbar.setTitle(titles[position]);
        replaceFragment(fragment);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fl,fragment).commit();
    }
}
