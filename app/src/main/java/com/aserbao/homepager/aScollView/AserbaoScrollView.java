package com.aserbao.homepager.aScollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * description:
 * Created by aserbao on 2017/7/17.
 */


public class AserbaoScrollView extends FrameLayout {
    private int mTouchSlop;
    private float mLastMotionY;
    private float mLastMotionX;
    private boolean mIsBeingDragged = false;

    public AserbaoScrollView(Context context) {
        this(context,null);
    }

    public AserbaoScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AserbaoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }
    //让内部的LinearLayout高度可以很大很大
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {

        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin
                        + widthUsed, lp.width);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY =  event.getY();
                mLastMotionX =  event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX =  (int)(event.getY() - mLastMotionY);
                int deltaY =  (int)(event.getY() - mLastMotionY);
                if (mIsBeingDragged) {
                    scrollBy(deltaX, -deltaY);
                    mLastMotionY=  event.getY();
                    mLastMotionX=  event.getX();
                } else if (Math.abs(deltaY) > mTouchSlop) {
                    mIsBeingDragged = true;
                    mLastMotionY=  event.getY();
                    mLastMotionX=  event.getX();
                    scrollBy(deltaX, -deltaY);
                }
                break;

            case MotionEvent.ACTION_UP:
                mIsBeingDragged = false;
                break;
        }

        return true;
    }
}
