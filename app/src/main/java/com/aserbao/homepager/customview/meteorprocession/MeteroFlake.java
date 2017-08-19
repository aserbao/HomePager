package com.aserbao.homepager.customview.meteorprocession;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.homepager.R;

import java.util.Random;

/**
 * Created by aserbao on 2017 2017/8/19.8:01
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class MeteroFlake extends ViewGroup{


    private Random mRandom;

    public MeteroFlake(Context context) {
        this(context,null);
    }

    public MeteroFlake(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MeteroFlake(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        for (int i = 0; i < 15; i++) {
            View meteor = LayoutInflater.from(context).inflate(R.layout.metor_layout, this, false);
            LayoutParams layoutParams = meteor.getLayoutParams();
            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            meteor.setLayoutParams(layoutParams);
            this.addView(meteor);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int l1 = getWidth() / 2 + mRandom.nextInt(getWidth() / 2);
            int t1 = mRandom.nextInt(getHeight() / 2);
            child.layout(l1, t1,l1+child.getWidth(),t1+ child.getHeight());
        }
    }
}
