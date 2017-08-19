package com.aserbao.homepager.customview.meteorprocession;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aserbao on 2017 2017/8/19.7:59
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class Meteor extends View {
    public Meteor(Context context) {
        this(context,null);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Meteor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            resize(w, h);
        }
    }

    private void resize(int w, int h) {

    }
}
