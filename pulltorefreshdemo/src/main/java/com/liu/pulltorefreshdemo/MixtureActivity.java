package com.liu.pulltorefreshdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 *
 * Created by liu on 2016/11/25.
 */
public class MixtureActivity extends AppCompatActivity{
    private PtrClassicFrameLayout ultra_ptr_frame;
    private ListAdapter mAdapter;
    private LinkedList<String> mData = new LinkedList<>();
    private static final String HEAD_STRING="MUKR";
    private PullToRefreshListView mPullToRefreshListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_mixture);
        initView();
        setData();
    }

    private void initView() {
        ultra_ptr_frame= (PtrClassicFrameLayout) findViewById(R.id.ultra_ptr_frame);
        mPullToRefreshListView= (PullToRefreshListView) findViewById(R.id.ptr_lv);
//        final StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
//        header.setTextColor(Color.BLACK);
//        header.initWithString(HEAD_STRING);
//        ultra_ptr_frame.setLoadingMinTime(1000);
//        ultra_ptr_frame.setHeaderView(header);
//        ultra_ptr_frame.addPtrUIHandler(header);

        ultra_ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                Log.e("TAG","child是否可以向上滑动"+PtrDefaultHandler.canChildScrollUp(content));
                //这里的滚动应该交给包裹的ListView去判断，不能使用content（因为它是一个PullToRefreshListView，继承LinearLayout，是无法滑动的）
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,mPullToRefreshListView.getRefreshableView(),header);
            }
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.addFirst("新数据");
                        frame.refreshComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });


        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                mPullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add("加载数据");
                        mPullToRefreshListView.onRefreshComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                },2000);
            }
        });
    }

    private void setData() {
        for (int i = 0; i < 30; i++) {
            mData.add("测试数据" + i);
        }
        mAdapter = new ListAdapter(mData, this);
        mPullToRefreshListView.setAdapter(mAdapter);
    }
}
