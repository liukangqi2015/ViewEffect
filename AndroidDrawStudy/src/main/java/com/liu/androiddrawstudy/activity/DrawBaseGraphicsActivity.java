package com.liu.androiddrawstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liu.androiddrawstudy.R;
import com.liu.androiddrawstudy.fragment.DrawArcFragment;
import com.liu.androiddrawstudy.fragment.DrawCircleFragment;
import com.liu.androiddrawstudy.fragment.DrawLineFragment;
import com.liu.androiddrawstudy.fragment.DrawOvalFragment;
import com.liu.androiddrawstudy.fragment.DrawPointFragment;
import com.liu.androiddrawstudy.fragment.DrawRectFragment;
import com.liu.androiddrawstudy.fragment.DrawRoundRectFragment;

/**
 * 绘制基本图形
 * Created by liu on 2016/12/1.
 */
public class DrawBaseGraphicsActivity extends AppCompatActivity{
    private DrawerLayout mDrawerlayout;
    private Toolbar mViewToolbar;
    private NavigationView mViewNavigation;
    private ActionBarDrawerToggle mDrawerToggle;
    private int positions[]=new int[]{0,1,2,3,4,5,6};
    private int titles[]=new int[]{R.string.draw_point,R.string.draw_line,R.string.draw_rect,R.string.draw_round_rect,R.string.draw_circle,R.string.draw_oval,R.string.draw_arc};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_draw_base_graphics);
        initView();
    }

    private void initView() {
        mDrawerlayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewToolbar= (Toolbar) findViewById(R.id.view_toolbar);
        mViewNavigation= (NavigationView) findViewById(R.id.navigation);
        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerlayout, mViewToolbar, R.string.open,
                R.string.close);

        // 添加侧边菜单的点击事件
        mViewNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.draw_point:
                        switchFragment(0,new DrawPointFragment());
                        break;
                    case R.id.draw_line:
                        switchFragment(1,new DrawLineFragment());
                        break;
                    case R.id.draw_rect:
                        switchFragment(2,new DrawRectFragment());
                        break;
                    case R.id.draw_round_rect:
                        switchFragment(3,new DrawRoundRectFragment());
                        break;
                    case R.id.draw_circle:
                        switchFragment(4,new DrawCircleFragment());
                        break;
                    case R.id.draw_oval:
                        switchFragment(5,new DrawOvalFragment());
                        break;
                    case R.id.draw_arc:
                        switchFragment(6,new DrawArcFragment());
                        break;
                    default:
                        break;

                }
                mDrawerlayout.closeDrawers();
                return true;
            }
        });
        //默认选中第一项
//        mViewNavigation.getMenu().performIdentifierAction(R.id.draw_point, 0);
        switchFragment(0,new DrawPointFragment());

    }

    private void switchFragment(int position,Fragment fragment){
        mViewToolbar.setTitle(titles[position]);
        for (int i = 0; i <positions.length ; i++) {
            if (position==positions[i]){
                mViewNavigation.getMenu().getItem(positions[i]).setChecked(true);
            }else {
                mViewNavigation.getMenu().getItem(positions[i]).setChecked(false);
            }
        }
        replaceFragment(fragment);
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,fragment).commit();
    }
}
