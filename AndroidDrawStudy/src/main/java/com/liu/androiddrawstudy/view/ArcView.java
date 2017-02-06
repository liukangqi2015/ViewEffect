package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 画弧形的View
 * Created by liu on 2017/2/6.
 */

public class ArcView extends View {
    private Paint mPaint=new Paint();
    private RectF rectF01 = new RectF(200, 300, 500, 700);
    private RectF rectF02= new RectF(400,300,700,700);
    private RectF rectF03=new RectF(200,700,500,1100);
    private RectF rectF04=new RectF(400,700,700,1100);
    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawArc(rectF01,0f,90f,true,mPaint);
        canvas.drawArc(rectF02,0f,90f,false,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF03,0f,90f,true,mPaint);
        canvas.drawArc(rectF04,0f,90f,false,mPaint);
    }
}
