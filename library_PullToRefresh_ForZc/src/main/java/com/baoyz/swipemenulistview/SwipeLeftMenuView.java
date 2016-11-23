package com.baoyz.swipemenulistview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.R;

/**
 * 
 * @author baoyz
 * @date 2014-8-23
 * 
 */
public class SwipeLeftMenuView extends LinearLayout
{
	private SwipeMenuLayout mLayout;
	private int position;
	private CheckBox cb;

	public CheckBox getCb()
	{
		return cb;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public SwipeLeftMenuView(Context context)
	{
		super(context);
		addItem();
	}

	private void addItem()
	{
		LayoutParams params = new LayoutParams(dp2px(45), LayoutParams.MATCH_PARENT);
		LinearLayout parent = new LinearLayout(getContext());
		parent.setId(0);
		parent.setGravity(Gravity.CENTER);
		parent.setOrientation(LinearLayout.VERTICAL);
		parent.setLayoutParams(params);
		parent.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		parent.addView(createCheckBox());
		addView(parent);
	}

	private CheckBox createCheckBox()
	{
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		cb = new CheckBox(getContext());
		cb.setLayoutParams(params);
		cb.setGravity(Gravity.CENTER);
		cb.setButtonDrawable(R.drawable.selector_sex_radiobutton);
		cb.setFocusable(false);
		return cb;
	}

	public void setLayout(SwipeMenuLayout mLayout)
	{
		this.mLayout = mLayout;
	}

	private int dp2px(int dp)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

}
