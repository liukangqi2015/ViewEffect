package com.liu.circleprogress;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 圆形的进度条
 * Created by liu on 2017/2/23.
 */

public class CircleProgress extends View {
    //内层圆的画笔
    private Paint mCirclePaint;
    //圆弧进度的画笔
    private Paint mArcPaint;
    //圆的半径
    private float mRadius;
    //圆的圆心
    private float mCenterX,mCenterY;
    //圆的外接矩形
    private RectF mArcRectF=new RectF();
    //最大值
    private float max=100;
    //进度
    private float progress;
    //当前的进度
    private float currentProgress;
    //动画是否播放完成
    private boolean animateFinish;
    //绘制进度文字的画笔
    private Paint mTextPaint;
    private float roundWidth;
    private int roundColor;
    private int progressColor;
    private float textSize;
    private int textColor;

    public CircleProgress(Context context) {
        this(context,null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("TAG", "CircleProgress");
        initAttrs(context, attrs);
        initPaint();
    }

    /**
     * 初始化属性
     */
    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.CircleProgress);
        roundWidth = Utils.dp2px(getResources(),array.getDimension(R.styleable.CircleProgress_roundWidth,10));
        roundColor = array.getColor(R.styleable.CircleProgress_roundColor, Color.LTGRAY);
        progressColor = array.getColor(R.styleable.CircleProgress_progressColor, Color.RED);
        textSize = array.getDimension(R.styleable.CircleProgress_textSize, Utils.dp2px(getResources(),14));
        textColor = array.getColor(R.styleable.CircleProgress_textColor, Color.RED);
        max=array.getInteger(R.styleable.CircleProgress_max,100);
        progress=array.getInteger(R.styleable.CircleProgress_progress,0);
        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //抗锯齿
        mCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStrokeWidth(roundWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(roundColor);
        mArcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStrokeWidth(roundWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(progressColor);
        mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(textColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("TAG","onMeasure");
        setMeasuredDimension(measure(widthMeasureSpec),measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = (int) Utils.dp2px(getResources(),100);
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    /**
     * 初始化计算绘制view时需要的参数
     */
    private void initParam() {
//        Log.e("TAG","getLeft:"+getLeft()+"getTop:"+getTop()+"getRight:"+getRight()+"getBottom"+getBottom());
//        Log.e("TAG","getWidth:"+getWidth());
        //计算圆心坐标
        mCenterX=getWidth()/2;
        mCenterY=getHeight()/2;
        //计算半径
        mRadius=Math.min(mCenterX,mCenterY)-mCirclePaint.getStrokeWidth();
        //计算圆弧所需的矩形
        mArcRectF.set(mCenterX-mRadius,mCenterY-mRadius,mCenterX+mRadius,mCenterY+mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("TAG","onDraw");
        //初始化参数
        initParam();
        //绘制圆
        canvas.drawCircle(mCenterX,mCenterY,mRadius,mCirclePaint);
        //绘制圆弧
        canvas.drawArc(mArcRectF,270,getProgressAngle(currentProgress),false,mArcPaint);
        //绘制进度文字
        String mText=(int)(currentProgress*100/max)+"%";
        float drawTextWidth = mTextPaint.measureText(mText);
        canvas.drawText(mText,mCenterX-drawTextWidth/2,mCenterY-(mTextPaint.descent()+mTextPaint.ascent())/2,mTextPaint);
        if (!animateFinish){
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("TAG","onLayout");
    }

    /**
     * 将进度转换为角度
     * @param progress 进度
     * @return 角度
     */
    private float getProgressAngle(float progress) {
        return progress /  max * 360f;
    }

    public float getProgress() {
        return progress;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    /**
     * 设置进度条的宽度
     * @param roundWidth 进度条的宽度
     */
    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
        initPaint();
        invalidate();
    }

    public float getTextSize() {
        return textSize;
    }

    /**
     * 设置字体大小
     * @param textSize 字体大小
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
        initPaint();
        invalidate();
    }

    public int getProgressColor() {
        return progressColor;
    }

    /**
     * 设置进度的颜色
     * @param progressColor 进度的颜色
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        initPaint();
        invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    /**
     * 设置字体颜色
     * @param textColor 字体颜色
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        initPaint();
        invalidate();
    }

    /**
     * 设置圆的颜色
     * @param roundColor 圆的颜色
     */
    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
        initPaint();
        invalidate();
    }

    /**
     * 设置进度
     * @param progress 进度
     */
    public void setProgress(float progress) {
        this.progress = progress;
        if (this.progress > getMax()) {
            this.progress %= getMax();
        }
        setAnimation(0,progress,1000);
    }

    public float getMax() {
        return max;
    }

    /**
     *
     * @param max
     */
    public void setMax(float max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }

    private void setAnimation(float start, float end, int during) {
        ValueAnimator animator = ValueAnimator.ofFloat(start,end);
        animator.setDuration(during);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentProgress= (Float) animation.getAnimatedValue();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animateFinish=true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animateFinish = false;
        postInvalidate();
        animator.start();
    }
}
