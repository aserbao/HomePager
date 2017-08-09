package com.aserbao.homepager.ttt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.aserbao.homepager.CustomRelativalayout;
import com.aserbao.homepager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollActivityOld extends AppCompatActivity {
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
    FrameLayout mOneStarFrameLayout;
    @BindView(R.id.two_star_line)
    ImageView mTwoStarLine;
    @BindView(R.id.two_star_text)
    ImageView mTwoStarText;
    @BindView(R.id.two_star_linear_layout)
    LinearLayout mTwoStarLinearLayout;
    @BindView(R.id.two_star)
    ImageView mTwoStar;
    @BindView(R.id.two_star_frame_layout)
    FrameLayout mTwoStarFrameLayout;
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
    FrameLayout mThreeStarFrameLayout;
    @BindView(R.id.four_star_line)
    ImageView mFourStarLine;
    @BindView(R.id.four_star_text)
    ImageView mFourStarText;
    @BindView(R.id.four_star_linear_layout)
    LinearLayout mFourStarLinearLayout;
    @BindView(R.id.four_star)
    ImageView mFourStar;
    @BindView(R.id.four_star_frame_layout)
    FrameLayout mFourStarFrameLayout;
    @BindView(R.id.custom_relativa_layout)
    CustomRelativalayout mCustomRelativaLayout;
    private VelocityTracker mVelocityTracker;


    private FrameLayout mOneFragment;
    private FrameLayout mTwoFrameLayout;
    private FrameLayout mThreeFrameLayout;
    private FrameLayout mFourFragment;
    private int mOneCutTwoTop;
    private Scroller mScroller;
    private float mLastMotionY;
    private float mLastMotionX;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int mActivePointerId = INVALID_POINTER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scroll);
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
    public boolean onTouchEvent(MotionEvent event) {
        mCustomRelativaLayout.onTouchEvent(event);
        mOneCutTwoTop = mTwoFrameLayout.getTop() - mOneFragment.getTop();
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
}
