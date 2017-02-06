package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示画矩形的View
 * Created by liu on 2017/2/6.
 */

public class RectView extends View {
    private Paint mPaint=new Paint();
    private Rect rect=new Rect(300,700,500,900);
    public RectView(Context context) {
        this(context,null);
    }

    public RectView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);//画笔风格为填充
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(300,300,500,500,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect,mPaint);
    }
}
