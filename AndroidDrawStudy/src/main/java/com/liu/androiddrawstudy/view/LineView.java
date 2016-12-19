package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 画线的View
 * Created by 刘康祺 on 2016/12/19 0019.
 */

public class LineView extends View {
    private Paint mPaint=new Paint();
    private float[] lineCoordinates=new float[]{300,600,500,600,300,700,500,1000};

    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.FILL);//画笔风格为填充
        mPaint.setStrokeWidth(12f);//设置画笔宽度为12px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(300,300,500,500,mPaint);
        canvas.drawLines(lineCoordinates,mPaint);
    }
}
