package com.baoyz.swipemenulistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.baoyz.swipemenulistview.SwipeMenuListView.OnRightMenuOnClickListener;
import com.baoyz.swipemenulistview.SwipeRightMenuView.OnSwipeRightMenuOnClickListener;

/**
 * 
 * @author baoyz
 * @date 2014-8-24
 * 
 */
public class SwipeMenuAdapter extends BaseAdapter implements OnSwipeRightMenuOnClickListener
{
	private ISwipeAdapterHelper mAdapterHelper;
	private BaseAdapter mAdapter;
	private Context mContext;
	private OnRightMenuOnClickListener mOnRightMenuOnClickListener;

	public SwipeMenuAdapter(Context context, ISwipeAdapterHelper adapter)
	{
		mAdapterHelper = adapter;
		mAdapter = adapter.getBaseAdapter();
		mContext = context;

	}

	@Override
	public int getCount()
	{
		return mAdapter.getCount();
	}

	@Override
	public Object getItem(int position)
	{
		return mAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position)
	{
		return mAdapter.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		SwipeMenuLayout layout = null;

		if (convertView == null)
		{
			View contentView = mAdapterHelper.createView(position, convertView, parent);
			SwipeRightMenu menu = new SwipeRightMenu(mContext);
			menu.setViewType(mAdapter.getItemViewType(position));
			createMenu(menu);
			SwipeRightMenuView rightMenuView = new SwipeRightMenuView(menu, (SwipeMenuListView) parent);
			rightMenuView.setOnSwipeRightMenuOnClickListener(this);
			SwipeLeftMenuView leftMenuView = new SwipeLeftMenuView(mContext);
			SwipeMenuListView listView = (SwipeMenuListView) parent;
			layout = new SwipeMenuLayout(contentView, rightMenuView, leftMenuView, listView.getCloseInterpolator(), listView.getOpenInterpolator());
			layout.setPosition(position);

		} else
		{
			layout = (SwipeMenuLayout) convertView;
			layout.setPosition(position);
			// View view = mAdapter.getView(position, layout, parent);
		}

		mAdapterHelper.fillData(position, layout, parent);

		return layout;
	}

	public void createMenu(SwipeRightMenu menu)
	{
		// Test Code
		SwipeRightMenuItem item = new SwipeRightMenuItem(mContext);
		item.setTitle("Item 1");
		item.setBackground(new ColorDrawable(Color.GRAY));
		item.setWidth(300);
		menu.addMenuItem(item);

		item = new SwipeRightMenuItem(mContext);
		item.setTitle("Item 2");
		item.setBackground(new ColorDrawable(Color.RED));
		item.setWidth(300);
		menu.addMenuItem(item);
	}

	@Override
	public void onRightMenuOnClick(SwipeRightMenuView view, SwipeRightMenu menu, int index)
	{
		if (mOnRightMenuOnClickListener != null)
		{
			mOnRightMenuOnClickListener.onRightMenuOnClick(view.getPosition(), menu, index);
		}
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer)
	{
		mAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer)
	{
		mAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public boolean areAllItemsEnabled()
	{
		return mAdapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position)
	{
		return mAdapter.isEnabled(position);
	}

	@Override
	public boolean hasStableIds()
	{
		return mAdapter.hasStableIds();
	}

	@Override
	public int getItemViewType(int position)
	{
		return mAdapter.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount()
	{
		return mAdapter.getViewTypeCount();
	}

	@Override
	public boolean isEmpty()
	{
		return mAdapter.isEmpty();
	}

	public ListAdapter getWrappedAdapter()
	{
		return this;
	}

}
