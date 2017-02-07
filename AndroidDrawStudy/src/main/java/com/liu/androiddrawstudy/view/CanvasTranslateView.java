package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;


/**
 * 演示Canvas位移的View
 * Created by liu on 2017/2/7.
 */

public class CanvasTranslateView extends View {
    private Paint mPaint=new Paint();
    private Paint translatePaint=new Paint();

    public CanvasTranslateView(Context context) {
        this(context,null);
    }

    public CanvasTranslateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasTranslateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//画笔风格为描边
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
        translatePaint.setAntiAlias(true);
        translatePaint.setColor(getResources().getColor(R.color.colorAccent));
        translatePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        translatePaint.setStrokeWidth(12f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200f,200f,100f,mPaint);
        //向X正方向平移，画一个圆
        canvas.translate(500f,0f);
        canvas.drawCircle(200f,200f,100f,translatePaint);
        //向Y正方向平移，画一个圆
        canvas.translate(0f,500f);
        canvas.drawCircle(200f,200f,100f,translatePaint);
    }
}
