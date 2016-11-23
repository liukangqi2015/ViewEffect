package com.baoyz.swipemenulistview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.FrameLayout;

/**
 * 
 * @author baoyz
 * @date 2014-8-23
 * 
 */
public class SwipeMenuLayout extends FrameLayout
{

	private static final int CONTENT_VIEW_ID = 1;
	private static final int MENU_VIEW_ID = 2;
	private static final int LEFT_MENU_VIEW_ID = 3;

	private boolean rightMenuClose = false;
	private boolean rightMenuOpen = false;
	private boolean leftMenuClose = false;
	private boolean leftMenuOpen = false;

	private View mContentView;
	public SwipeRightMenuView mRightMenuView;
	public SwipeLeftMenuView mLeftMenuView;
	private int mDownX;
	private GestureDetectorCompat mGestureDetector;
	private OnGestureListener mGestureListener;
	private boolean isFling;
	private int MIN_FLING = dp2px(15);
	private int MAX_VELOCITYX = -dp2px(500);
	private ScrollerCompat mOpenScroller;
	private ScrollerCompat mCloseScroller;
	private int mBaseX;
	private int position;
	private Interpolator mCloseInterpolator;
	private Interpolator mOpenInterpolator;

	public SwipeMenuLayout(View contentView, SwipeRightMenuView menuView, SwipeLeftMenuView leftMenuView)
	{
		this(contentView, menuView, leftMenuView, null, null);
	}

	public SwipeMenuLayout(View contentView, SwipeRightMenuView rightMenuView, SwipeLeftMenuView leftMenuView, Interpolator closeInterpolator,
			Interpolator openInterpolator)
	{
		super(contentView.getContext());
		mCloseInterpolator = closeInterpolator;
		mOpenInterpolator = openInterpolator;
		mContentView = contentView;
		mRightMenuView = rightMenuView;
		mRightMenuView.setLayout(this);
		mLeftMenuView = leftMenuView;
		mLeftMenuView.setLayout(this);
		init();
	}

	private SwipeMenuLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private SwipeMenuLayout(Context context)
	{
		super(context);
		init();
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
		mRightMenuView.setPosition(position);
		mLeftMenuView.setPosition(position);
	}

	private void init()
	{
		setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		mGestureListener = new SimpleOnGestureListener()
		{
			@Override
			public boolean onDown(MotionEvent e)
			{
				isFling = false;
				return true;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
			{
				
				if ((e1.getX() - e2.getX()) > MIN_FLING && velocityX < MAX_VELOCITYX)
				{
					isFling = true;
				}
				return super.onFling(e1, e2, velocityX, velocityY);
			}
		};
		mGestureDetector = new GestureDetectorCompat(getContext(), mGestureListener);

		if (mCloseInterpolator != null)
		{
			mCloseScroller = ScrollerCompat.create(getContext(), mCloseInterpolator);
		} else
		{
			mCloseScroller = ScrollerCompat.create(getContext());
		}
		if (mOpenInterpolator != null)
		{
			mOpenScroller = ScrollerCompat.create(getContext(), mOpenInterpolator);
		} else
		{
			mOpenScroller = ScrollerCompat.create(getContext());
		}

		LayoutParams contentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mContentView.setLayoutParams(contentParams);
		if (mContentView.getId() < 1)
		{
			mContentView.setId(CONTENT_VIEW_ID);
		}

		mRightMenuView.setId(MENU_VIEW_ID);
		mRightMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mLeftMenuView.setId(LEFT_MENU_VIEW_ID);
		mLeftMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		addView(mLeftMenuView);
		addView(mRightMenuView);
		addView(mContentView);

	}

	@Override
	protected void onAttachedToWindow()
	{
		super.onAttachedToWindow();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public boolean onSwipe(MotionEvent event)
	{
		mGestureDetector.onTouchEvent(event);
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			mDownX = (int) event.getX();
			isFling = false;
			break;
		case MotionEvent.ACTION_MOVE:
			int dis = (int) (mDownX - event.getX());
			if (rightMenuOpen)
			{
				dis += mRightMenuView.getWidth();
			}

			if (!leftMenuOpen)
			{
				swipe(dis);
			}

			break;
		case MotionEvent.ACTION_UP:
			if (!leftMenuOpen)
			{
				if (isFling || (mDownX - event.getX()) > (mRightMenuView.getWidth() / 2))
				{
					// open
					smoothOpenRightMenu();
				} else
				{
					// close
					smoothCloseRightMenu();
					return false;
				}
			}

			break;
		}
		return true;
	}

	public boolean isOpen()
	{
		return rightMenuOpen;
	}

	public boolean isLeftOpen()
	{
		return leftMenuOpen;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return super.onTouchEvent(event);
	}

	private void swipe(int dis)
	{
		if (dis > mRightMenuView.getWidth())
		{
			dis = mRightMenuView.getWidth();
		}
		if (dis < 0)
		{
			dis = 0;
		}

		mContentView.layout(-dis, mContentView.getTop(), mContentView.getWidth() - dis, getMeasuredHeight());
		// mRightMenuView.layout(mContentView.getWidth() - dis,
		// mRightMenuView.getTop(), mContentView.getWidth() +
		// mRightMenuView.getWidth() - dis,
		// mRightMenuView.getBottom());
		mLeftMenuView.layout(-mLeftMenuView.getWidth() - dis, mLeftMenuView.getTop(), -dis, mLeftMenuView.getBottom());
	}

	@Override
	public void computeScroll()
	{
		if (rightMenuOpen)
		{
			if (mOpenScroller.computeScrollOffset())
			{
				swipe(mOpenScroller.getCurrX());
				postInvalidate();
			}
		}

		if (rightMenuClose)
		{
			if (mCloseScroller.computeScrollOffset())
			{
				swipe(mBaseX - mCloseScroller.getCurrX());
				postInvalidate();
			}
		}

		if (leftMenuOpen)
		{
			if (mOpenScroller.computeScrollOffset())
			{
				scrollTo(mOpenScroller.getCurrX(), 0);
				postInvalidate();
			}
		}

		if (leftMenuClose)
		{
			if (mCloseScroller.computeScrollOffset())
			{
				scrollTo(mCloseScroller.getCurrX(), 0);
				postInvalidate();
			}
		}
	}

	public void smoothCloseRightMenu()
	{
		mRightMenuView.setInterceptTouch(true);
		rightMenuOpen = false;
		rightMenuClose = true;
		leftMenuOpen = false;
		leftMenuClose = false;
		mBaseX = -mContentView.getLeft();
		mCloseScroller.startScroll(0, 0, mBaseX, 0, 350);
		postInvalidate();
	}

	public void smoothOpenRightMenu()
	{
		mRightMenuView.setInterceptTouch(false);
		rightMenuOpen = true;
		rightMenuClose = false;
		leftMenuOpen = false;
		leftMenuClose = false;
		mOpenScroller.startScroll(-mContentView.getLeft(), 0, mRightMenuView.getWidth(), 0, 350);
		postInvalidate();
	}

	public void smoothCloseLeftMenu()
	{
		rightMenuOpen = false;
		rightMenuClose = false;
		leftMenuOpen = false;
		leftMenuClose = true;
		mBaseX = mContentView.getLeft();
		mCloseScroller.startScroll(-mBaseX, 0, mBaseX, 0, 350);
		postInvalidate();
	}

	public void smoothOpenLeftMenu(int time)
	{
		rightMenuOpen = false;
		rightMenuClose = false;
		leftMenuOpen = true;
		leftMenuClose = false;
		mOpenScroller.startScroll(0, 0, -dp2px(45), 0, time);
		postInvalidate();
	}

	public CheckBox getLeftMenuCheckBox()
	{
		CheckBox cb = null;
		cb = getLeftMenuView().getCb();
		return cb;
	}

	public void closeMenu()
	{
		if (mCloseScroller.computeScrollOffset())
		{
			mCloseScroller.abortAnimation();
		}
		if (rightMenuOpen)
		{
			rightMenuOpen = false;
			swipe(0);
		}
	}

	public void openMenu()
	{
		if (rightMenuClose)
		{
			rightMenuClose = false;
			swipe(mRightMenuView.getWidth());
		}
	}

	public View getContentView()
	{
		return mContentView;
	}

	public SwipeRightMenuView getMenuView()
	{
		return mRightMenuView;
	}

	public SwipeLeftMenuView getLeftMenuView()
	{
		return mLeftMenuView;
	}

	private int dp2px(int dp)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mRightMenuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
		mLeftMenuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
		// mRightMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() +
		// mRightMenuView.getMeasuredWidth(), mContentView.getMeasuredHeight());
		mRightMenuView.layout(getMeasuredWidth() - mRightMenuView.getMeasuredWidth(), 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
		mRightMenuView.setInterceptTouch(true);
		mLeftMenuView.layout(-mLeftMenuView.getMeasuredWidth(), 0, 0, mLeftMenuView.getMeasuredHeight());
	}

}
