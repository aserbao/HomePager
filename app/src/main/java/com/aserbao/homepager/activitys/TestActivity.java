package com.aserbao.homepager.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.homepager.R;
import com.aserbao.homepager.customview.snowball.ohtersnowball.MainActivity;
import com.bumptech.glide.Glide;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.image_view)
    ImageView mImageView;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.rocketloading).into(mImageView);
//        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        }, 1500);
    }

    public void jump(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
    }


}
