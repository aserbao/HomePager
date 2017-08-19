package com.aserbao.homepager.customview.meteorprocession;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.aserbao.homepager.R;

import java.util.Random;

/**
 * Created by aserbao on 2017 2017/8/19.7:59
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class Meteor extends android.support.v7.widget.AppCompatImageView {
    public static final int NUM_METEROR = 5;
    public static final int DELAY = 10;
    private Random mRandom;
    private Paint mPaint;

    public Meteor(Context context) {
        this(context,null);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            translation(w, h);
        }
    }

    private void translation(int w, int h) {
        int mRw = getWidth() / 2 + mRandom.nextInt(getWidth() / 2);
        int width = mRw;
        int height = mRandom.nextInt(getHeight());
        PropertyValuesHolder translateX = PropertyValuesHolder.ofFloat("translationX", width, 0);
        PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat("translationY", height, height + mRw);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, translateX, translateY);
        objectAnimator.setDuration(mRandom.nextInt(1500));
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                translation(getWidth(),getHeight());
            }
        });
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }
}
