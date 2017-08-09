package com.aserbao.homepager.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.aserbao.homepager.CustomRelativalayout;
import com.aserbao.homepager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollActivity extends AppCompatActivity {
    private static final int INVALID_POINTER = -1;
    @BindView(R.id.backgroud_scroll_view)
    BackgroundScollView mBackground;
    @BindView(R.id.xingxing_scroll_view)
    BackgroundScollView mXingxing;
    @BindView(R.id.one_star_background)
    ImageView mOneStarBackground;
    @BindView(R.id.one_star)
    ImageView mOneStar;
    @BindView(R.id.one_star_frame_layout)
    FrameLayout mOneFragment;
    @BindView(R.id.two_star_line)
    ImageView mTwoStarLine;
    @BindView(R.id.two_star_text)
    ImageView mTwoStarText;
    @BindView(R.id.two_star_linear_layout)
    LinearLayout mTwoStarLinearLayout;
    @BindView(R.id.two_star)
    ImageView mTwoStar;
    @BindView(R.id.two_star_frame_layout)
    FrameLayout mTwoFrameLayout;
    @BindView(R.id.three_star_line)
    ImageView mThreeStarLine;
    @BindView(R.id.three_star_text)
    ImageView mThreeStarText;
    @BindView(R.id.three_star_linear_layout)
    LinearLayout mThreeStarLinearLayout;
    @BindView(R.id.three_star_light)
    ImageView mThreeStarLight;
    @BindView(R.id.three_star_light_spot)
    ImageView mThreeStarLightSpot;
    @BindView(R.id.three_star)
    ImageView mThreeStar;
    @BindView(R.id.three_star_aperture)
    ImageView mThreeStarAperture;
    @BindView(R.id.three_star_frame_layout)
    FrameLayout mThreeFrameLayout;
    @BindView(R.id.four_star_line)
    ImageView mFourStarLine;
    @BindView(R.id.four_star_text)
    ImageView mFourStarText;
    @BindView(R.id.four_star_linear_layout)
    LinearLayout mFourStarLinearLayout;
    @BindView(R.id.four_star)
    ImageView mFourStar;
    @BindView(R.id.four_star_frame_layout)
    FrameLayout mFourFragment;
    @BindView(R.id.custom_relativa_layout)
    CustomRelativalayout mCustomRelativaLayout;
    private VelocityTracker mVelocityTracker;


    private float mOneCutTwoTop;
    private Scroller mScroller;
    private float mLastMotionY;
    private float mLastMotionX;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int mActivePointerId = INVALID_POINTER;
    private boolean mIsFirstCreat = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().getDecorView().animate().scaleX(2.0f).scaleY(2.0f).setDuration(5000).start();*/
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scroll);

        Window window = getWindow();// 设置宽度为屏宽, 靠近屏幕底部。
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT; // 高度持平
        window.setAttributes(lp);

        getWindow().setWindowAnimations(R.style.myAnimation);
        ButterKnife.bind(this);
        mScroller = new Scroller(this);
        final ViewConfiguration configuration = ViewConfiguration.get(this);
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

        mOneFragment = (FrameLayout) mCustomRelativaLayout.getChildAt(0);
        mTwoFrameLayout = (FrameLayout) mCustomRelativaLayout.getChildAt(1);
        mThreeFrameLayout = (FrameLayout) mCustomRelativaLayout.getChildAt(2);
        mFourFragment = (FrameLayout) mCustomRelativaLayout.getChildAt(3);
    }

    @Override
    protected void onPause() {
        this.overridePendingTransition(0,0);
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mIsFirstCreat) {
            mOneCutTwoTop = mTwoFrameLayout.getTop() - mOneFragment.getTop();
            mIsFirstCreat = false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = event.getX();
                final float y = event.getY();
                mBackground.actionDown(x, y);
                mXingxing.actionDown(x, y);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastMotionY = y;
                mLastMotionX = x;
                mActivePointerId = event.getPointerId(0);
                break;
            }
            case MotionEvent.ACTION_MOVE:
                final int activePointerIndex = event.findPointerIndex(mActivePointerId);
                final float y = event.getY(activePointerIndex);
                final int deltaY = (int) (mLastMotionY - y);
                mLastMotionY = y;

                final float x = event.getX(activePointerIndex);
                final int deltaX = (int) (mLastMotionX - x);
                mLastMotionX = x;
                mBackground.actionMove(deltaX, deltaY);
                mXingxing.actionMove(deltaX, deltaY);
                ballMove(deltaX,deltaY);
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocitx = (int) velocityTracker.getXVelocity();
                int initialVelocity = (int) velocityTracker.getYVelocity();
                mBackground.actionUp(initialVelocitx, initialVelocity);
                mXingxing.actionUp(initialVelocitx, initialVelocity);
                mActivePointerId = INVALID_POINTER;
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER;
                mBackground.actionCancel();
                mXingxing.actionCancel();
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(event);
                break;
        }
        return true;
    }


    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>
                MotionEvent.ACTION_POINTER_ID_SHIFT;
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose a new
            // active pointer and adjust accordingly.
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mLastMotionX = ev.getX(newPointerIndex);
            mLastMotionY = ev.getY(newPointerIndex);
            mActivePointerId = ev.getPointerId(newPointerIndex);
            if (mVelocityTracker != null) {
                mVelocityTracker.clear();
            }
        }
    }



    private void ballMove(int distanceX, int distanceY) {
        if (mOneFragment != null && mTwoFrameLayout.getTop() - mOneFragment.getTop() > mOneCutTwoTop - 20 || distanceY < 0) {
            float v = (mTwoFrameLayout.getTop() - distanceY - mOneFragment.getTop()) / mOneCutTwoTop;
            if(v > 1 || distanceY < 0) {
                mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop() - (int) distanceY, mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom() - (int) distanceY);
                mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop() - (int) (distanceY * 2), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom() - (int) (distanceY * 2));
                mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop() - (int) (distanceY * 4), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom() - (int) (distanceY * 4));
                mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop() - (int) (distanceY * 6), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom() - (int) (distanceY * 6));
                Toast.makeText(this, String.valueOf(v) + "distanceY" + distanceY, Toast.LENGTH_SHORT).show();
                loadScaleAnim(v);
            }
        }else{
            mOneFragment.layout(mOneFragment.getLeft() - (int) distanceX, mOneFragment.getTop(), mOneFragment.getRight() - (int) distanceX, mOneFragment.getBottom());
            mTwoFrameLayout.layout(mTwoFrameLayout.getLeft() - (int) (distanceX * 2), mTwoFrameLayout.getTop(), mTwoFrameLayout.getRight() - (int) (distanceX * 2), mTwoFrameLayout.getBottom());
            mThreeFrameLayout.layout(mThreeFrameLayout.getLeft() - (int) (distanceX * 3), mThreeFrameLayout.getTop(), mThreeFrameLayout.getRight() - (int) (distanceX * 3), mThreeFrameLayout.getBottom());
            mFourFragment.layout(mFourFragment.getLeft() - (int) (distanceX * 4), mFourFragment.getTop(), mFourFragment.getRight() - (int) (distanceX * 4), mFourFragment.getBottom());
        }
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
