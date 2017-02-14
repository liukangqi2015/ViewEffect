package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示方法：
 * public void lineTo (float x, float y)
 * public void moveTo (float x, float y)
 * public void setLastPoint (float dx, float dy)
 * public void close ()
 * Created by liu on 2017/2/14.
 */

public class PathView01 extends View {
    private Paint mPaint = new Paint();
    private Path path = new Path();
    // 宽高
    private int mWidth, mHeight;

    public PathView01(Context context) {
        this(context, null);
    }

    public PathView01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView01(Context context, AttributeSet attrs, int defStyleAttr) {
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

        path.lineTo(200f, 200f);//默认的起点为原点，路径从原点到（200,200）
        path.lineTo(200f, 0);//路径从（200,200）到（200,0）
        path.moveTo(200f, -200f);//路径起点移动到（200，-200）
        path.lineTo(0, 0);//路径从（200，-200）到（0,0）

        path.lineTo(-200f, 200f);//路径从（0,0）到（-200,200）
        path.setLastPoint(-200f, 100f);//设置上一次操作最后一个点的位置为（-200,100）;
        path.lineTo(-200f, 0);//路径从(-200,100)从(-200,0)
        path.close();//封闭路径

        canvas.drawPath(path, mPaint);//绘制路径
    }
}
