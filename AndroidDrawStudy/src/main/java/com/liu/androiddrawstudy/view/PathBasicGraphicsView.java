package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示Path添加基本图形,和之前的Canvas绘制基本图形相似
 * // 圆形
 * public void addCircle (float x, float y, float radius, Path.Direction dir)
 * // 椭圆
 * public void addOval (RectF oval, Path.Direction dir)
 * // 矩形
 * public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
 * public void addRect (RectF rect, Path.Direction dir)
 * // 圆角矩形
 * public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
 * public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
 * Created by liu on 2017/2/15.
 */

public class PathBasicGraphicsView extends View {
    private Paint mPaint = new Paint();
    private Path path = new Path();
    private RectF rectF=new RectF(-200f,-200f,200f,200f);
    // 宽高
    private int mWidth, mHeight;

    public PathBasicGraphicsView(Context context) {
        this(context,null);
    }

    public PathBasicGraphicsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathBasicGraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(12f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        path.addRect(rectF, Path.Direction.CW);
        canvas.drawPath(path,mPaint);
    }
}
