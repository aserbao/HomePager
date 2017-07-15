package com.aserbao.homepager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


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
    @BindView(R.id.custom_relative_layout)
    CustomRelativalayout mCustomRelativeLayout;
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
        init();
    }

    private void init() {
        otherBackgroundAnimation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCustomRelativeLayout.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void otherBackgroundAnimation() {
        int sleepTime = 10000;
        startAnimators(mOneStar, "rotation", sleepTime, 0f, 360f);
        startAnimators(mOneStarBackground, "rotation", sleepTime, 360f, 0f);
        startAnimators(mTwoStar, "rotation", sleepTime, -15f, 15f, -15f);
        startAnimators(mThreeStar, "rotation", sleepTime, 0f, 360f);
        startAnimators(mThreeStarLight, "alpha", 5000, 1f, 0f,1f);
        startAnimators(mThreeStarLightSpot, "scaleX", sleepTime, 1f, 1.3f,1f);
        startAnimators(mThreeStarLightSpot, "scaleY", sleepTime, 1f, 1.3f,1f);
        startAnimators(mThreeStarAperture, "rotation", sleepTime, -5f, 5f,-5f);

        startAnimators(mFourStar, "rotation", sleepTime, 360f, 0f);

    }

    private void startAnimators(Object object, String s, int duration, float... vales) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(object, s, vales);
        rotation.setRepeatCount(-1);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setDuration(duration);
        rotation.start();
    }

}
