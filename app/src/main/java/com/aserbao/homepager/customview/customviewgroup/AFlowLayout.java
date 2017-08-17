package com.aserbao.homepager.customview.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

/**
 * description:
 * Created by aserbao on 2017/8/17.
 */


public class AFlowLayout extends ViewGroup {
    public AFlowLayout(Context context) {
        this(context,null);
    }

    public AFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
