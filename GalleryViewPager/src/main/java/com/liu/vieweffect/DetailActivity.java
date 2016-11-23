package com.liu.vieweffect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * 详情页面
 * Created by liu on 2016/11/18.
 */
public class DetailActivity extends AppCompatActivity{
    public static final String IMAGE_ID="image_id";
    public static final String BUNDLE="bundle";
    public static final int DURATION = 300;
    private static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();



    private ImageView poster_detail_iv;
    //上一个页面传来的参数
    private int imgId,left,top,width,height;
    private int deltaX;
    private int deltaY;
    private float scaleX;
    private float scaleY;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        poster_detail_iv= (ImageView) findViewById(R.id.poster_detail_iv);

        // 初始化场景
        initial();

        // 设置入场动画
        runEnterAnim();
    }

    private void initial() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(BUNDLE);
        //获取上个页面传来的参数
        imgId=bundle.getInt(DetailActivity.IMAGE_ID,0);
        left=bundle.getInt("left");
        top=bundle.getInt("top");
        width=bundle.getInt("width");
        height=bundle.getInt("height");
        Picasso.with(this).load(imgId).into(poster_detail_iv, new Callback() {
            @Override
            public void onSuccess() {
                onUIReady();
            }

            @Override
            public void onError() {

            }
        });
//        poster_detail_iv.setImageResource(imgId);

    }

    private void onUIReady() {
        poster_detail_iv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                // remove previous listener
                poster_detail_iv.getViewTreeObserver().removeOnPreDrawListener(this);
                // prep the scene
                prepareScene();
                // run the animation
                runEnterAnim();
                return true;
            }
        });
    }

    private void prepareScene() {
        int[] screenLocation = new int[2];
        poster_detail_iv.getLocationOnScreen(screenLocation);
        //移动到起始view位置
        deltaX = left - screenLocation[0];
        deltaY = top - screenLocation[1];
        poster_detail_iv.setTranslationX(deltaX);
        poster_detail_iv.setTranslationY(deltaY);
        //缩放到起始view大小
        scaleX = (float) width / poster_detail_iv.getWidth();
        scaleY = (float) height / poster_detail_iv.getHeight();
        poster_detail_iv.setScaleX(scaleX);
        poster_detail_iv.setScaleY(scaleY);
    }


    /**
     * 模拟入场动画
     */
    private void runEnterAnim() {
        poster_detail_iv.setVisibility(View.VISIBLE);
        //执行动画
        poster_detail_iv.animate()
                .setDuration(DURATION)
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .scaleX(1f)
                .scaleY(1f)
                .translationX(0)
                .translationY(0)
                .start();
    }

    /**
     * 模拟退场动画
     */
    private void runExitAnim() {
        poster_detail_iv.animate()
                .setDuration(DURATION)
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .translationX(deltaX)
                .translationY(deltaY)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                }).start();
    }

    @Override
    public void onBackPressed() {
        runExitAnim();
    }


}
