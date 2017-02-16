package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示贝塞尔曲线（二阶）的View
 * Created by liu on 2017/2/15.
 */

public class BezierView extends View {
    private Paint mPaint=new Paint();
    private int centerX, centerY;//中心点的横坐标，纵坐标
    private PointF start, end, control;//起始点，结束点，控制点
    private Path path=new Path();

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPoint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 初始化起始点，结束点，控制点
     */
    private void initPoint() {
        start=new PointF(0,0);
        end=new PointF(0,0);
        control=new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;

        //初始化起始点，结束点，控制点的位置
        start.x=centerX-300;
        start.y=centerY;
        end.x=centerX+300;
        end.y=centerY;
        control.x=centerX;
        control.y=centerY-200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制起始点，结束点，控制点
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(20f);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);
        //绘制辅助线
        mPaint.setStrokeWidth(5f);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        mPaint.setStrokeWidth(10f);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        //重置路径
        path.reset();
        path.moveTo(start.x,start.y);
        //绘制贝塞尔曲线
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mPaint);
    }
}
