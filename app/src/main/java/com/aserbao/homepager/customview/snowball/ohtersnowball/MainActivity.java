package com.aserbao.homepager.customview.snowball.ohtersnowball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.aserbao.homepager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_snow_ball_activity_main);
        ButterKnife.bind(this);
        mRandom = new Random();
        for (int i = 0; i < 5; i++) {
            View meteor = LayoutInflater.from(this).inflate(R.layout.metor_layout,mFrameLayout,false);
            ViewGroup.LayoutParams layoutParams = meteor.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            meteor.setLayoutParams(layoutParams);
            mFrameLayout.addView(meteor);

            int mRw = 1080 / 2 + mRandom.getRandom(540);
            int width = mRw;
            int height = mRandom.getRandom(4920);
            PropertyValuesHolder translateX = PropertyValuesHolder.ofFloat("translationX", width, 0);
            PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat("translationY", height, height + mRw);
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, translateX, translateY);
            objectAnimator.setDuration(mRandom.getRandom(1500));
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.start();
        }
    }
}
