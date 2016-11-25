package com.liu.pulltorefreshdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.LinkedList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 *
 * Created by liu on 2016/11/24.
 */
public class UltraPullToRefreshActivity extends AppCompatActivity{
    private PtrClassicFrameLayout ultra_ptr_frame;
//    private LoadMoreListViewContainer loadMoreListViewContainer;

    private LoadMoreListView loadMoreListView;
    private ListAdapter mAdapter;
    private LinkedList<String> mData = new LinkedList<>();
    private static final String HEAD_STRING="MUKR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_pull_to_refresh);
        initView();
        setData();
    }

    private void initView() {
        loadMoreListView= (LoadMoreListView) findViewById(R.id.load_more_small_image_list_view);
        ultra_ptr_frame= (PtrClassicFrameLayout) findViewById(R.id.ultra_ptr_frame);
//        loadMoreListViewContainer= (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);

        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
        header.setTextColor(Color.BLACK);
        header.initWithString(HEAD_STRING);
//        ultra_ptr_frame.setLoadingMinTime(1000);
        ultra_ptr_frame.setHeaderView(header);
        ultra_ptr_frame.addPtrUIHandler(header);
//        ultra_ptr_frame.setLastUpdateTimeRelateObject(this);

        // the following are default settings
//        ultra_ptr_frame.setResistance(1.7f);
//        ultra_ptr_frame.setRatioOfHeaderHeightToRefresh(1.2f);
//        ultra_ptr_frame.setDurationToClose(200);
//        ultra_ptr_frame.setDurationToCloseHeader(1000);
//        // default is false
//        ultra_ptr_frame.setPullToRefresh(false);
//        // default is true
//        ultra_ptr_frame.setKeepHeaderWhenRefresh(true);
//        ultra_ptr_frame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ultra_ptr_frame.autoRefresh();
//            }
//        }, 100);
        ultra_ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
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
//        loadMoreListViewContainer.useDefaultFooter();
//        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
//            @Override
//            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                loadMore();
//            }
//        });
        loadMoreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add("加载数据");
                        loadMoreListView.setLoadCompleted();
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
        loadMoreListView.setAdapter(mAdapter);
    }
}
