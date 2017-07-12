package com.aserbao.homepager;

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
    private FrameLayout mTwoFrameLayout;
    private ImageView mThreeImageView;
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
        mThreeImageView = (ImageView) getChildAt(2);
        mFourFragment = (FrameLayout) getChildAt(3);
        if(mIsFirstCreat) {
            mOneCutTwoTop = mTwoFrameLayout.getTop() - mOneFragment.getTop();
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
        if (mOneFragment != null && mTwoFrameLayout.getTop() - mOneFragment.getTop() > mOneCutTwoTop - 20 || distanceY < 0) {
            mOneFragment.layout(mOneFragment.getLeft()-(int)distanceX , mOneFragment.getTop()-(int)distanceY, mOneFragment.getRight()-(int)distanceX, mOneFragment.getBottom()-(int)distanceY);
            mThreeImageView.layout(mThreeImageView.getLeft()-(int)(distanceX*2), mThreeImageView.getTop()-(int)(distanceY*2), mThreeImageView.getRight()-(int)(distanceX*2), mThreeImageView.getBottom()-(int)(distanceY*2));
            mTwoFrameLayout.layout(mTwoFrameLayout.getLeft()-(int)(distanceX*3), mTwoFrameLayout.getTop()-(int)(distanceY*4), mTwoFrameLayout.getRight()-(int)(distanceX*3), mTwoFrameLayout.getBottom()-(int)(distanceY*4));
            mFourFragment.layout(mFourFragment.getLeft()-(int)(distanceX*4), mFourFragment.getTop()-(int)(distanceY*6), mFourFragment.getRight()-(int)(distanceX*4), mFourFragment.getBottom()-(int)(distanceY*6));
        }else {
            mOneFragment.layout(mOneFragment.getLeft()-(int)distanceX , mOneFragment.getTop(), mOneFragment.getRight()-(int)distanceX, mOneFragment.getBottom());
            mThreeImageView.layout(mThreeImageView.getLeft()-(int)(distanceX*2), mThreeImageView.getTop(), mThreeImageView.getRight()-(int)(distanceX*2), mThreeImageView.getBottom());
            mTwoFrameLayout.layout(mTwoFrameLayout.getLeft()-(int)(distanceX*3), mTwoFrameLayout.getTop(), mTwoFrameLayout.getRight()-(int)(distanceX*3), mTwoFrameLayout.getBottom());
            mFourFragment.layout(mFourFragment.getLeft()-(int)(distanceX*4), mFourFragment.getTop(), mFourFragment.getRight()-(int)(distanceX*4), mFourFragment.getBottom());
        }


        Toast.makeText(getContext(), String.valueOf(distanceX), Toast.LENGTH_SHORT).show();
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
