package com.baoyz.swipemenulistview;

import zrc.widget.ZrcListView;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * 
 * @author baoyz
 * @date 2014-8-18
 * 
 */
public class SwipeMenuListView extends ZrcListView
{

	private static final int TOUCH_STATE_NONE = 0;
	private static final int TOUCH_STATE_X = 1;
	private static final int TOUCH_STATE_Y = 2;

	private int MAX_Y = 5;
	private int MAX_X = 3;
	private float mDownX;
	private float mDownY;
	private int mTouchState;
	private int mTouchPosition;
	private SwipeMenuLayout mTouchView;
	private OnSwipeListener mOnSwipeListener;

	private SwipeRightMenuCreator mRightMenuCreator;
	private OnRightMenuOnClickListener mOnRightMenuOnClickListener;
	private Interpolator mCloseInterpolator;
	private Interpolator mOpenInterpolator;
	public SwipeMenuAdapter mAdapter = null;

	public SwipeMenuListView(Context context)
	{
		this(context, null);
	}

	public SwipeMenuListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}

	public SwipeMenuListView(Context context, AttributeSet attrs)
	{
		this(context, attrs, android.R.attr.listViewStyle);
	}

	private void init()
	{
		MAX_X = dp2px(MAX_X);
		MAX_Y = dp2px(MAX_Y);
		mTouchState = TOUCH_STATE_NONE;
	}
/**
 * 
 * @param 
 * 这一段注释是原本都有的，原作者注释2015-9-29
 */
//	@Override
//	public void setAdapter(ListAdapter adapter) {
//		mAdapter = new SwipeMenuAdapter(getContext(), adapter) {
//			@Override
//			public void onRightMenuOnClick(SwipeRightMenuView view, int index) {
//				boolean flag = false;
//				if (mOnRightMenuOnClickListener != null) {
//					flag = mOnRightMenuOnClickListener.onRightMenuOnClick(
//							view.getPosition(), index);
//				}
//				if (mTouchView != null && !flag) {
//					mTouchView.smoothCloseRightMenu();
//				}
//			}
//
//		};
//		super.setAdapter(mAdapter);
//	}

	public void setBaseAdapter(ISwipeAdapterHelper adapter)
	{
		mAdapter = new SwipeMenuAdapter(getContext(), adapter)
		{

			@Override
			public void createMenu(SwipeRightMenu menu)
			{
				if (mRightMenuCreator != null)
				{
					mRightMenuCreator.create(menu);
				}
			}

			@Override
			public void onRightMenuOnClick(SwipeRightMenuView view, SwipeRightMenu menu, int index)
			{
				boolean flag = false;
				if (mOnRightMenuOnClickListener != null)
				{
					flag = mOnRightMenuOnClickListener.onRightMenuOnClick(view.getPosition(), menu, index);
				}
				if (mTouchView != null && !flag)
				{
					mTouchView.smoothCloseRightMenu();
				}
			}
		};
		super.setAdapter(mAdapter);

	}

	public void setCloseInterpolator(Interpolator interpolator)
	{
		mCloseInterpolator = interpolator;
	}

	public void setOpenInterpolator(Interpolator interpolator)
	{
		mOpenInterpolator = interpolator;
	}

	public Interpolator getOpenInterpolator()
	{
		return mOpenInterpolator;
	}

	public Interpolator getCloseInterpolator()
	{
		return mCloseInterpolator;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		if (ev.getAction() != MotionEvent.ACTION_DOWN && mTouchView == null)
			return super.onTouchEvent(ev);
		int action = MotionEventCompat.getActionMasked(ev);
		action = ev.getAction();
		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			int oldPos = mTouchPosition;
			mDownX = ev.getX();
			mDownY = ev.getY();
			mTouchState = TOUCH_STATE_NONE;

			mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());

			if (mTouchPosition == oldPos && mTouchView != null && mTouchView.isOpen())
			{
				mTouchState = TOUCH_STATE_X;
				mTouchView.onSwipe(ev);
				return true;
			}

			View view = getChildAt(mTouchPosition - getFirstVisiblePosition());

			if (mTouchView != null && mTouchView.isOpen())
			{
				mTouchView.smoothCloseRightMenu();
				mTouchView = null;
				return super.onTouchEvent(ev);
			}
			if (view instanceof SwipeMenuLayout)
			{
				mTouchView = (SwipeMenuLayout) view;
			}
			if (mTouchView != null)
			{
				mTouchView.onSwipe(ev);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float dy = Math.abs((ev.getY() - mDownY));
			float dx = Math.abs((ev.getX() - mDownX));
			if (mTouchState == TOUCH_STATE_X)
			{
				if (mTouchView != null)
				{
					mTouchView.onSwipe(ev);
				}
				getSelector().setState(new int[] { 0 });
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			} else if (mTouchState == TOUCH_STATE_NONE)
			{
				if (Math.abs(dy) > MAX_Y)
				{
					mTouchState = TOUCH_STATE_Y;
				} else if (dx > MAX_X)
				{
					mTouchState = TOUCH_STATE_X;
					if (mOnSwipeListener != null)
					{
						mOnSwipeListener.onSwipeStart(mTouchPosition);
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mTouchState == TOUCH_STATE_X)
			{
				if (mTouchView != null)
				{
					mTouchView.onSwipe(ev);
					if (!mTouchView.isOpen())
					{
						mTouchPosition = -1;
						mTouchView = null;
					}
				}
				if (mOnSwipeListener != null)
				{
					mOnSwipeListener.onSwipeEnd(mTouchPosition);
				}
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	public void smoothOpenMenu(int position)
	{
		if (position >= getFirstVisiblePosition() && position <= getLastVisiblePosition())
		{
			View view = getChildAt(position - getFirstVisiblePosition());
			if (view instanceof SwipeMenuLayout)
			{
				mTouchPosition = position;
				if (mTouchView != null && mTouchView.isOpen())
				{
					mTouchView.smoothCloseRightMenu();
				}
				mTouchView = (SwipeMenuLayout) view;
				mTouchView.smoothOpenRightMenu();
			}
		}
	}

	public void smoothCloseMenu(int position)
	{
		if (position >= getFirstVisiblePosition() && position <= getLastVisiblePosition())
		{
			View view = getChildAt(position - getFirstVisiblePosition());
			if (view instanceof SwipeMenuLayout)
			{
				mTouchPosition = position;
				mTouchView = (SwipeMenuLayout) view;
				mTouchView.smoothCloseRightMenu();
			}
		}
	}

	private int dp2px(int dp)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
	}

	public void setOnRightMenuOnClickListener(OnRightMenuOnClickListener onRightMenuOnClickListener)
	{
		this.mOnRightMenuOnClickListener = onRightMenuOnClickListener;
	}

	public void setOnSwipeListener(OnSwipeListener onSwipeListener)
	{
		this.mOnSwipeListener = onSwipeListener;
	}

	public void setRightMenuCreator(SwipeRightMenuCreator rightMenuCreator)
	{
		this.mRightMenuCreator = rightMenuCreator;
	}

	public static interface OnRightMenuOnClickListener
	{
		boolean onRightMenuOnClick(int position, SwipeRightMenu menu, int index);
	}

	public static interface OnSwipeListener
	{
		void onSwipeStart(int position);

		void onSwipeEnd(int position);
	}

}
