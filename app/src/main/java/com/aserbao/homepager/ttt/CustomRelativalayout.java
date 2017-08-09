package com.aserbao.homepager.ttt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * description:
 * Created by aserbao on 2017/7/12.
 */


public class CustomRelativalayout extends RelativeLayout implements GestureDetector.OnGestureListener {
    private GestureDetector mGestureDetector;
    private FrameLayout mOneFragment;
    private FrameLayout mThreeFrameLayout;
    private FrameLayout mTwoFrameLayout;
    private FrameLayout mFourFragment;
    private int mOneCutTwoTop;
    private ImageView mOne;
    private boolean mIsFirstCreat = true;

    public CustomRelativalayout(Context context) {
        this(context,null);
    }

    public CustomRelativalayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRelativalayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(this);
        init();
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mOneFragment = (FrameLayout) getChildAt(0);
        mTwoFrameLayout = (FrameLayout) getChildAt(1);
        mThreeFrameLayout = (FrameLayout) getChildAt(2);
        mFourFragment = (FrameLayout) getChildAt(3);
        if(mIsFirstCreat) {
            mOneCutTwoTop = mThreeFrameLayout.getTop() - mOneFragment.getTop();
            mIsFirstCreat = false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if(mOneFragment.getTop() < getHeight() || distanceY > 0 ){
            if(mOneFragment.getLeft() > 0 || distanceX <0) {
                if(mOneFragment.getRight() < getWidth() || distanceX > 0 ) {
                    if (mOneFragment != null && mThreeFrameLayout.getTop() - mOneFragment.getTop() > mOneCutTwoTop - 20 || distanceY < 0) {
                        mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop() - (int) distanceY, mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom() - (int) distanceY);
                        mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop() - (int) (distanceY * 2), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom() - (int) (distanceY * 2));
                        mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop() - (int) (distanceY * 4), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom() - (int) (distanceY * 4));
                        mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop() - (int) (distanceY * 6), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom() - (int) (distanceY * 6));
                    } else {
                        mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop(), mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom());
                        mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop(), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom());
                        mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop(), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom());
                        mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop(), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom());
                    }
                }
            }
        }

        Toast.makeText(getContext(), String.valueOf(getHeight()), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
