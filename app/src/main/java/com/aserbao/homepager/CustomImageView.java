package com.aserbao.homepager;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

/**
 * description:
 * Created by aserbao on 2017/7/20.
 */


public class CustomImageView extends ImageView {
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(getRootView(), "alpha", 1, 0, 1);
        alpha.setDuration(1000);
        alpha.setRepeatCount(-1);
        alpha.setInterpolator(new CycleInterpolator(0.5f));
        alpha.start();
    }
}
