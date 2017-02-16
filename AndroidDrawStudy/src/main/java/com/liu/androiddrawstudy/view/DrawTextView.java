package com.liu.androiddrawstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liu.androiddrawstudy.R;

/**
 * 演示drawText方法
 * Created by liu on 2017/2/16.
 */

public class DrawTextView extends View {
    private Paint mPaint;
    private String mText="Hello World";
    // 宽高
    private int mWidth, mHeight;

    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
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
        canvas.translate(mWidth / 2, mHeight / 2);
        //画基线
        canvas.drawLine(0,0,800f,0,mPaint);
        //写文字
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setTextSize(100f);
        canvas.drawText(mText,0,0,mPaint);
    }
}
