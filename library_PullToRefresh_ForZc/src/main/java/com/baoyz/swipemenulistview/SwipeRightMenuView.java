package com.baoyz.swipemenulistview;

import java.util.List;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author baoyz
 * @date 2014-8-23
 * 
 */
public class SwipeRightMenuView extends LinearLayout implements OnClickListener
{

	private SwipeMenuListView mListView;
	private SwipeRightMenu mMenu;
	private SwipeMenuLayout mLayout;
	private OnSwipeRightMenuOnClickListener onSwipeRightMenuOnClickListener;
	private int position;
	private boolean isInterceptTouch;

	public boolean isInterceptTouch()
	{
		return isInterceptTouch;
	}

	public void setInterceptTouch(boolean isInterceptTouch)
	{
		this.isInterceptTouch = isInterceptTouch;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public SwipeRightMenuView(SwipeRightMenu menu, SwipeMenuListView listView)
	{
		super(menu.getContext());
		mListView = listView;
		mMenu = menu;
		List<SwipeRightMenuItem> items = menu.getMenuItems();
		int id = 0;
		for (SwipeRightMenuItem item : items)
		{
			addItem(item, id++);
		}
	}

	@SuppressWarnings("deprecation")
	private void addItem(SwipeRightMenuItem item, int id)
	{
		LayoutParams params = new LayoutParams(item.getWidth(), LayoutParams.MATCH_PARENT);
		LinearLayout parent = new LinearLayout(getContext());
		parent.setId(id);
		parent.setGravity(Gravity.CENTER);
		parent.setOrientation(LinearLayout.VERTICAL);
		parent.setLayoutParams(params);
		parent.setBackgroundDrawable(item.getBackground());
		parent.setOnClickListener(this);
		addView(parent);

		if (item.getIcon() != null)
		{
			parent.addView(createIcon(item));
		}
		if (!TextUtils.isEmpty(item.getTitle()))
		{
			parent.addView(createTitle(item));
		}

	}

	private ImageView createIcon(SwipeRightMenuItem item)
	{
		ImageView iv = new ImageView(getContext());
		iv.setImageDrawable(item.getIcon());
		return iv;
	}

	private TextView createTitle(SwipeRightMenuItem item)
	{
		TextView tv = new TextView(getContext());
		tv.setText(item.getTitle());
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(item.getTitleSize());
		tv.setTextColor(item.getTitleColor());
		return tv;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		return isInterceptTouch;
	}

	@Override
	public void onClick(View v)
	{
		if (onSwipeRightMenuOnClickListener != null && mLayout.isOpen())
		{
			onSwipeRightMenuOnClickListener.onRightMenuOnClick(this, mMenu, v.getId());
		}
	}

	public OnSwipeRightMenuOnClickListener getOnSwipeItemClickListener()
	{
		return onSwipeRightMenuOnClickListener;
	}

	public void setOnSwipeRightMenuOnClickListener(OnSwipeRightMenuOnClickListener onSwipeRightMenuOnClickListener)
	{
		this.onSwipeRightMenuOnClickListener = onSwipeRightMenuOnClickListener;
	}

	public void setLayout(SwipeMenuLayout mLayout)
	{
		this.mLayout = mLayout;
	}

	public static interface OnSwipeRightMenuOnClickListener
	{
		void onRightMenuOnClick(SwipeRightMenuView view, SwipeRightMenu menu, int index);
	}

}
