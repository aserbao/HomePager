package com.aserbao.homepager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
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
        if(Math.abs(distanceY) < 100) {
            if (mOneFragment.getTop() < getHeight() + 500 || distanceY > 0) {
                if (mOneFragment.getLeft() + 500 > 0 || distanceX < 0) {
                    if (mOneFragment.getRight() - 300 < getWidth() || distanceX > 0) {
                        if (mOneFragment != null && mTwoFrameLayout.getTop() - mOneFragment.getTop() > mOneCutTwoTop - 20 || distanceY < 0) {
                            float v = (mTwoFrameLayout.getTop() - distanceY - mOneFragment.getTop()) / mOneCutTwoTop;
                            if(v > 1 || distanceY < 0) {
                                mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop() - (int) distanceY, mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom() - (int) distanceY);
                                mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop() - (int) (distanceY * 2), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom() - (int) (distanceY * 2));
                                mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop() - (int) (distanceY * 4), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom() - (int) (distanceY * 4));
                                mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop() - (int) (distanceY * 6), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom() - (int) (distanceY * 6));
                                loadScaleAnim(v);
                            }
                        } else {
                            mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop(), mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom());
                            mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop(), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom());
                            mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop(), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom());
                            mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop(), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom());
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    private ScaleAnimation scaleOneAnimation;
    private ScaleAnimation scaleTwoAnimation;
    private ScaleAnimation scaleThreeAnimation;
    private ScaleAnimation scaleFourAnimation;
    private float scaleTwoDegress;
    private float scaleThreeDegress;
    private float scaleFourDegress;
    private float lastScaleDegress = 1.0f;
    private float lastTwoScaleDegress = 1.0f;
    private float lastThreeScaleDegress = 1.0f;
    private float lastFourScaleDegress = 1.0f;
    private float scaleDegress = 1.0f;
    private void loadScaleAnim(float scaleDegres) {
        if(scaleDegres > 1){
            scaleDegress = 1+(float)(scaleDegres * 0.3) ;
        }else{
            scaleDegress = scaleDegres;
        }
        scaleTwoDegress = scaleDegress;
        scaleThreeDegress = scaleDegress;
        scaleFourDegress = scaleDegress;
        if (scaleDegress == lastScaleDegress) {
            return;
        } else if (scaleDegress > lastScaleDegress){
            //创建缩放动画
            scaleOneAnimation = new ScaleAnimation(lastScaleDegress, scaleDegress, lastScaleDegress, scaleDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleTwoAnimation = new ScaleAnimation(lastTwoScaleDegress, scaleTwoDegress, lastTwoScaleDegress, scaleTwoDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleThreeAnimation = new ScaleAnimation(lastThreeScaleDegress, scaleThreeDegress, lastThreeScaleDegress, scaleThreeDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleFourAnimation = new ScaleAnimation(lastFourScaleDegress, scaleFourDegress, lastFourScaleDegress, scaleFourDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }else {
            scaleOneAnimation = new ScaleAnimation(scaleDegress, lastScaleDegress, scaleDegress, lastScaleDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleTwoAnimation = new ScaleAnimation(scaleTwoDegress, lastTwoScaleDegress, scaleTwoDegress, lastTwoScaleDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleThreeAnimation = new ScaleAnimation(scaleThreeDegress, lastThreeScaleDegress, scaleThreeDegress, lastThreeScaleDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleFourAnimation = new ScaleAnimation(scaleFourDegress, lastFourScaleDegress, scaleFourDegress, lastFourScaleDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        lastScaleDegress = scaleDegress;
        lastTwoScaleDegress = scaleTwoDegress;
        lastThreeScaleDegress = scaleThreeDegress;
        lastFourScaleDegress = scaleFourDegress;

        scaleOneAnimation.setFillAfter(true);
        scaleTwoAnimation.setFillAfter(true);
        scaleThreeAnimation.setFillAfter(true);
        scaleFourAnimation.setFillAfter(true);
        mOneFragment.setAnimation(scaleOneAnimation);
        mTwoFrameLayout.setAnimation(scaleOneAnimation);
        mThreeFrameLayout.setAnimation(scaleOneAnimation);
        mFourFragment.setAnimation(scaleOneAnimation);
    }
}
