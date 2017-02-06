package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 画圆形的View
 * Created by liu on 2017/2/6.
 */

public class CircleView extends View {
    private Paint mPaint=new Paint();
    //圆的半径
    private float radius=100f;
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);//画笔风格为描边
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(400f,400f,100f,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(400f,800f,100f,mPaint);
    }
}
