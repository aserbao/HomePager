package com.aserbao.homepager.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * description:
 * Created by aserbao on 2017/7/30.
 */


public class CirclrImageView extends ImageView {
    private int secondRadiu = 58;
    private int mCostTime = 10 * 1000;
    private float curPostion = 0;
    private int mSleepTime = 50;

    public CirclrImageView(Context context) {
        this(context,null);
    }

    public CirclrImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CirclrImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(curPostion <= 360){
                    curPostion = curPostion +   (360 * mSleepTime) / mCostTime;
                    try {
                        Thread.sleep(mSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2,getHeight()/2,50,paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth()/2,getHeight()/2,secondRadiu,paint);
        RectF rectF = new RectF(getWidth() / 2 - secondRadiu, getHeight() / 2 - secondRadiu, getWidth() / 2 + secondRadiu, getHeight() / 2 + secondRadiu);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF,-90,curPostion,false,paint);
        super.onDraw(canvas);
    }
}
