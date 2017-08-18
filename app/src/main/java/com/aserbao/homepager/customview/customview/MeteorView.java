package com.aserbao.homepager.customview.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aserbao.homepager.R;

import java.util.Random;


/**
 * Created by aserbao on 2017 2017/8/17.23:47
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class MeteorView extends ImageView implements ValueAnimator.AnimatorListener{

    private Random mRandom;
    private Handler mHandler;
    private int mAddWidth = 5;
    private int mAddHeight = 100;

    public MeteorView(Context context) {
        this(context,null);
    }

    public MeteorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MeteorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", getWidth()/2 + mAddWidth, 0);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("translationY", mAddHeight, getWidth()/2+2*mAddHeight);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, x, y);
        animator.addListener(this);
        animator.setDuration(1000);
        animator.start();
        super.onDraw(canvas);
    }
    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mAddWidth = mRandom.nextInt(1080);
        mAddHeight = mRandom.nextInt(1080);
        postInvalidate();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
