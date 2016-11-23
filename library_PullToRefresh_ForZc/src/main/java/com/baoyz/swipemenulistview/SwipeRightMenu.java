package com.baoyz.swipemenulistview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * 
 * @author baoyz
 * @date 2014-8-23
 * 
 */
public class SwipeRightMenu {

	private Context mContext;
	private List<SwipeRightMenuItem> mItems;
	private int mViewType;

	public SwipeRightMenu(Context context) {
		mContext = context;
		mItems = new ArrayList<SwipeRightMenuItem>();
	}

	public Context getContext() {
		return mContext;
	}

	public void addMenuItem(SwipeRightMenuItem item) {
		mItems.add(item);
	}

	public void removeMenuItem(SwipeRightMenuItem item) {
		mItems.remove(item);
	}

	public List<SwipeRightMenuItem> getMenuItems() {
		return mItems;
	}

	public SwipeRightMenuItem getMenuItem(int index) {
		return mItems.get(index);
	}

	public int getViewType() {
		return mViewType;
	}

	public void setViewType(int viewType) {
		this.mViewType = viewType;
	}

}
