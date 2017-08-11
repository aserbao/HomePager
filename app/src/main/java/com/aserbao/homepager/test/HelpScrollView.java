package com.aserbao.homepager.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * description:
 * Created by aserbao on 2017/8/11.
 */


public class HelpScrollView extends HorizontalScrollView {
    public HelpScrollView(Context context) {
        this(context,null);
    }

    public HelpScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HelpScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 滑动事件
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }

}
