package com.aserbao.homepager.aScollView;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.aserbao.homepager.R;

public class AcrollViewActivity extends AppCompatActivity {

    private AserbaoScrollView mScrollView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acroll_view);
        mScrollView = (AserbaoScrollView) findViewById(R.id.aserbao);
        mButton = (Button) findViewById(R.id.btn);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScrollView.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void btn_lcick(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 0.5f, 0.5f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mButton, "scaleX", 1.0f, 2.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mButton, "scaleY", 1.0f, 2.0f);
        mButton.setPivotX(0.5f);
        mButton.setPivotY(0.5f);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);
        scaleX.start();
        scaleY.start();

        Toast.makeText(this, String.valueOf(mButton.getTop()), Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(AcrollViewActivity.this, AnimaActivity.class));
//        overridePendingTransition(R.anim.one_start_enter,R.anim.exit);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.enter,R.anim.exit);
    }
}
