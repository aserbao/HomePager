package com.aserbao.homepager.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.homepager.MainActivity;
import com.aserbao.homepager.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    private Random mRandom;
    private int curosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
//        init();
    }

    private void init() {
        final int num = 2;
        for (int i = 0; i < num; i++) {
            mRandom = new Random();
            final View view = LayoutInflater.from(this).inflate(R.layout.image_view, mFrameLayout, false);
            int width = 540 + mRandom.nextInt(540);
            int height = mRandom.nextInt(960);
//            view.layout(width,height,width+view.getWidth(),height+view.getHeight());

            view.setX(width);
            view.setY(height);
            mFrameLayout.addView(view);
            PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", width, -100);
            PropertyValuesHolder y = PropertyValuesHolder.ofFloat("translationY", height, height + width + 100);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, x, y);
//            animator.setDuration(mRandom.nextInt(1500)).setRepeatCount(1);
            int duration = 500 + mRandom.nextInt(1000);
            animator.setDuration(duration).setRepeatCount(1);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                        mFrameLayout.removeView(view);
                }
            });
            animator.start();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        },1500);
    }

    public void jump(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
