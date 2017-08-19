package com.aserbao.homepager.customview.meteorprocession;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aserbao on 2017 2017/8/19.7:59
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class Meteor extends View {
    public static final int NUM_METEROR = 5;
    public static final int DELAY = 10;

    public Meteor(Context context) {
        this(context,null);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private MeteroFlake[] mMeteroFlakes ;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            resize(w, h);
        }
    }

    private void resize(int w, int h) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        mMeteroFlakes = new MeteroFlake[NUM_METEROR];
        for (int i = 0; i < NUM_METEROR; i++) {
            mMeteroFlakes[i] = MeteroFlake.crete(w,h,paint);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (MeteroFlake meteroFlake : mMeteroFlakes) {
            meteroFlake.draw(canvas);
        }
//        getHandler().postDelayed(mRunnable,DELAY);
    }
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
}
