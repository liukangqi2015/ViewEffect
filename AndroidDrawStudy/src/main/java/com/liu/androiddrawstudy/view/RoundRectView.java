package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 画圆角矩形的View
 * Created by liu on 2017/2/6.
 */

public class RoundRectView extends View {
    private Paint mPaint=new Paint();
    private RectF rectF=new RectF(300,700F,500,900);
    //生成圆角的椭圆的X轴半径
    private float rx=30f;
    //生成圆角的椭圆的Y轴半径
    private float ry=30f;
    public RoundRectView(Context context) {
        this(context,null);
    }

    public RoundRectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);//画笔风格为描边
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //这个方法在API>=21才能使用
            canvas.drawRoundRect(300,300,500,500,rx,ry,mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF,rx,ry,mPaint);
    }
}
