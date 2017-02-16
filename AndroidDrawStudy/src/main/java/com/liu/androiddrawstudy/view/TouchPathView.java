package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 显示手势轨迹的View
 * Created by liu on 2017/2/16.
 */

public class TouchPathView extends View {
    private Paint mPaint=new Paint();
    private Path mPath=new Path();
    private float mPreX,mPreY;

    public TouchPathView(Context context) {
        this(context,null);
    }

    public TouchPathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(12f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                //使用lineTo方法不够平滑
//                mPath.lineTo(event.getX(),event.getY());
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY =event.getY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    public void reset(){
        mPath.reset();
        invalidate();
    }
}
