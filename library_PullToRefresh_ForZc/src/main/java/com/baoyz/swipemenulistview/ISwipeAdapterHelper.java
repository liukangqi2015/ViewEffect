package com.baoyz.swipemenulistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public interface ISwipeAdapterHelper
{

	public View createView(int position, View convertView, ViewGroup parent);

	public void fillData(int position, View convertView, ViewGroup parent);

	public BaseAdapter getBaseAdapter();

}
