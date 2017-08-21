package com.aserbao.homepager.customview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by aserbao on 2017 2017/8/17.23:47
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class MeteorView extends ImageView {
    public static final int DELAY = 10;
//    private short final int SPEED = 10
    private int NUM = 5;
    private Random mRandom;
    private Handler mHandler;
    private Meteor[] mMeteors ;
    private List<Integer> speed = new ArrayList<>();
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            resize(w, h);
        }
    }

    private void resize(int w, int h) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        mMeteors = new Meteor[NUM];
        for (int i = 0; i < mMeteors.length; i++) {
            mMeteors[i] = Meteor.create(getWidth(),getHeight(),paint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mMeteors.length; i++) {
            mMeteors[i].draw(canvas,getResources(),i);
        }
        getHandler().postDelayed(mRunnable, DELAY);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
}
