package com.liu.pulltorefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private static final int PULL_DOWN = 0;
    private static final int PULL_UP = 1;
    private PullToRefreshListView mPullToRefreshListView;
    private ListAdapter mAdapter;
    private LinkedList<String> mData = new LinkedList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PULL_DOWN:
                    Toast.makeText(MainActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
                    break;
                case PULL_UP:
                    Toast.makeText(MainActivity.this,"上拉加载",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            mAdapter.notifyDataSetChanged();
            mPullToRefreshListView.onRefreshComplete();

        }
    };

    //标记下拉index
    private int pullDownIndex = 0;

    //标记上拉index
    private int pullUpIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.ptr_lv);
        mPullToRefreshListView.setOnRefreshListener(this);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mData.add("测试数据" + i);
        }
        mAdapter = new ListAdapter(mData, this);
        mPullToRefreshListView.setAdapter(mAdapter);
    }

    /**
     * 下拉刷新添加数据到List集合
     */
    public void onPullDown() {
        mData.addFirst("下拉刷新数据" + pullDownIndex);
        pullDownIndex++;

    }

    /**
     * 上拉加载添加数据到List集合
     */
    public void onPullUp() {
        mData.addLast("上拉加载数据" + pullUpIndex);
        pullUpIndex++;

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        onPullDown();
        mHandler.sendEmptyMessageDelayed(PULL_DOWN, 2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        onPullUp();
        mHandler.sendEmptyMessageDelayed(PULL_UP, 2000);
    }
}
