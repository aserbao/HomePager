package com.aserbao.homepager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_background)
    ImageView mHomeBackground;
    @BindView(R.id.home_vortex66)
    ImageView mHomeVortex66;
    @BindView(R.id.home_bubble)
    ImageView mHomeBubble;
    @BindView(R.id.one_star_background)
    ImageView mOneStarBackground;
    @BindView(R.id.one_star)
    ImageView mOneStar;
    @BindView(R.id.one_frame_layout)
    FrameLayout mOneFrameLayout;
    @BindView(R.id.two_star_light)
    ImageView mmTwoStarLight;
    @BindView(R.id.two_star_light_spot)
    ImageView mmTwoStarLightSpot;
    @BindView(R.id.two_star)
    ImageView mTwoStar;
    @BindView(R.id.two_star_aperture)
    ImageView mTwoStarAperture;
    @BindView(R.id.two_frame_layout)
    FrameLayout mTwoFrameLayout;
    @BindView(R.id.three_star)
    ImageView mThreeStar;
    @BindView(R.id.four_star)
    ImageView mFourStar;
    @BindView(R.id.four_star_halo)
    ImageView mFourStarHalo;
    @BindView(R.id.four_frame_layout)
    FrameLayout mFourFrameLayout;
    @BindView(R.id.star_collection_relative_layout)
    RelativeLayout mStarCollectionRelativeLayout;
    @BindView(R.id.hvScrollView)
    HVScrollViewTwo mHvScrollView;
    @BindView(R.id.custom_relative_layout)
    CustomRelativalayout mCustomRelativeLayout;
    @BindView(R.id.hvScrollView1)
    HVScrollViewTwo mHvScrollView1;
    private BackgroundScrollView mBackGround;
    private XingxingScrollView mXingxing;
    private HVScrollViewTwo mHvScrollViewTwo;
//===================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHvScrollViewTwo = (HVScrollViewTwo) findViewById(R.id.hvScrollView1);
        init();
    }

    private void init() {
//        otherBackgroundAnimation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHvScrollView.onTouchEvent(event);
        mHvScrollViewTwo.onTouchEvent(event);
        mCustomRelativeLayout.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void otherBackgroundAnimation() {
        LinearInterpolator lir = new LinearInterpolator();

        RotateAnimation rotateAnimation = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, rotateAnimation, 30000, true);
        mOneStarBackground.setAnimation(rotateAnimation);

        RotateAnimation oneStarAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, oneStarAnimation, 30000, true);
        mOneStar.setAnimation(oneStarAnimation);

//          MyAnimationUtils.loadStarsAnim(this, mmTwoStarLight);


        RotateAnimation twoStarAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, twoStarAnimation, 25000, true);
        mTwoStar.setAnimation(twoStarAnimation);
        final Animation upAnimation = AnimationUtils.loadAnimation(this, R.anim.two_star_scale_up);
        final Animation downAnimation = AnimationUtils.loadAnimation(this, R.anim.two_star_scale_down);
        mmTwoStarLightSpot.startAnimation(upAnimation);
        downAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mmTwoStarLightSpot.startAnimation(upAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        upAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mmTwoStarLightSpot.startAnimation(downAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final RotateAnimation twoStarApertureUpAnimation = new RotateAnimation(0, 30, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final RotateAnimation twoStarApertureDownAnimation = new RotateAnimation(30, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, twoStarApertureUpAnimation, 15000, false);
        normalAnimationSetting(lir, twoStarApertureDownAnimation, 15000, false);
        mTwoStarAperture.startAnimation(twoStarApertureUpAnimation);
        twoStarApertureUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTwoStarAperture.startAnimation(twoStarApertureDownAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        twoStarApertureDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTwoStarAperture.startAnimation(twoStarApertureUpAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final RotateAnimation threeStarUpAnimation = new RotateAnimation(0, 15, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final RotateAnimation threeStarDownAnimation = new RotateAnimation(15, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, threeStarUpAnimation, 10000, false);
        normalAnimationSetting(lir, threeStarDownAnimation, 10000, false);
        threeStarUpAnimation.setFillAfter(true);
        threeStarDownAnimation.setFillAfter(true);
        mThreeStar.startAnimation(threeStarDownAnimation);
        threeStarUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mThreeStar.startAnimation(threeStarDownAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        threeStarDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mThreeStar.startAnimation(threeStarUpAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        RotateAnimation fourRotateAnimation = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, fourRotateAnimation, 30000, true);
        mFourStarHalo.setAnimation(fourRotateAnimation);
        RotateAnimation fourStarAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        normalAnimationSetting(lir, fourStarAnimation, 30000, true);
        mFourStar.setAnimation(fourStarAnimation);

    }

    private void normalAnimationSetting(LinearInterpolator lir, Animation oneStarAnimation, long duration, boolean isRepeat) {
        oneStarAnimation.setDuration(duration);
        oneStarAnimation.setInterpolator(lir);
        if (isRepeat) {
            oneStarAnimation.setRepeatCount(-1);
        }
    }
}
