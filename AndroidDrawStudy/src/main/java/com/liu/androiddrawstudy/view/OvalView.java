package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 画椭圆的View
 * Created by liu on 2017/2/6.
 */

public class OvalView extends View {
    private Paint mPaint=new Paint();
    private  RectF rectF = new RectF(300, 300, 600, 700);
    public OvalView(Context context) {
        this(context,null);
    }

    public OvalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OvalView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        canvas.drawRect(rectF, mPaint);//画矩形
        canvas.drawOval(rectF, mPaint);//矩形内画椭圆
    }
}
