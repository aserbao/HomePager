package com.aserbao.homepager.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;

import com.aserbao.homepager.R;
import com.aserbao.homepager.customview.flowlayout.HorizationFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalFloatLayoutActivity extends AppCompatActivity {

    @BindView(R.id.flow_layout)
    HorizationFlowLayout mFlowLayout;
    @BindView(R.id.scroll_flow_layout)
    HorizontalScrollView mScrollFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

}
