package com.aserbao.homepager.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.aserbao.homepager.R;
import com.aserbao.homepager.customview.flowlayout.HorizationFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.flow_layout)
    HorizationFlowLayout mFlowLayout;
    @BindView(R.id.scroll_flow_layout)
    HorizontalScrollView mScrollFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScrollFlowLayout.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
