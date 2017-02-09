package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示Canvas错切的View
 * Created by liu on 2017/2/9.
 */

public class CanvasSkewView extends View {
    private Paint mPaint=new Paint();
    private Paint translatePaint=new Paint();
    // 宽高
    private int mWidth, mHeight;
    private RectF rectF=new RectF(0,0,200f,200f);

    public CanvasSkewView(Context context) {
        this(context,null);
    }

    public CanvasSkewView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasSkewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);//画笔风格为描边
        mPaint.setStrokeWidth(5f);//设置画笔宽度为12px
        translatePaint.setAntiAlias(true);
        translatePaint.setColor(getResources().getColor(R.color.colorAccent));
        translatePaint.setStyle(Paint.Style.STROKE);
        translatePaint.setStrokeWidth(5f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawRect(rectF,mPaint);
        canvas.skew(1,0);// 水平错切 <- 45度
        canvas.drawRect(rectF,translatePaint);
    }
}
