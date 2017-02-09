package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示canvas
 * Created by liu on 2017/2/7.
 */

public class CanvasScaleView extends View {
    private Paint mPaint=new Paint();
    private Paint translatePaint=new Paint();
    // 宽高
    private int mWidth, mHeight;
    private RectF rectF=new RectF(0,-200f,200f,0);

    public CanvasScaleView(Context context) {
        this(context,null);
    }

    public CanvasScaleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.translate(mWidth / 2, mHeight / 2);// 将坐标系原点移动到画布正中心
        canvas.drawRect(rectF,mPaint);
        canvas.scale(2f,2f);// 扩大为两倍
        canvas.drawRect(rectF,translatePaint);
        canvas.scale(-1f,-1f);//缩放倍数不变，但是x与y方向变为相反（关于原点对称）
        canvas.drawRect(rectF,translatePaint);
        canvas.scale(0.5f,0.5f,200f,0);//缩放中心变为（200,0），x与y方向变为0.5倍
        canvas.drawRect(rectF,mPaint);
    }
}
