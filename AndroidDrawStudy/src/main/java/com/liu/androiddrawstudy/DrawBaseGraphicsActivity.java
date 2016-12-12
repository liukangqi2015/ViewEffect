package com.liu.androiddrawstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liu.androiddrawstudy.fragment.DrawPointFragment;

/**
 * 绘制基本图形
 * Created by liu on 2016/12/1.
 */
public class DrawBaseGraphicsActivity extends AppCompatActivity{
    private DrawerLayout mDrawerlayout;
    private Toolbar mViewToolbar;
    private NavigationView mViewNavigation;
    private ActionBarDrawerToggle mDrawerToggle;
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
                        replaceFragment(new DrawPointFragment());
                        break;
                    case R.id.draw_line:
                        break;
                    default:
                        break;

                }
                mDrawerlayout.closeDrawers();
                return true;
            }
        });
        //默认选中第一项
        mViewNavigation.setCheckedItem(R.id.draw_point);
        mViewNavigation.getMenu().performIdentifierAction(R.id.draw_point, 0);

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
