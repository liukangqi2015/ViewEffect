package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 演示画点的View
 * Created by liu on 2016/12/12.
 */

public class PointView extends View {
    private Paint mPaint=new Paint();
    private float[] pointCoordinates=new float[]{500,400,500,500,500,600};

    public PointView(Context context) {
        this(context,null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);//画笔风格为填充
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(300,300,mPaint);//在坐标（300,300）画一个点
        canvas.drawPoints(pointCoordinates,mPaint);//绘制一组点，坐标位置由float数组指定
    }
}
