package com.aserbao.homepager.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.aserbao.homepager.R;
import com.aserbao.homepager.activitys.TestActivity;
import com.aserbao.homepager.customview.flowlayout.HorizationFlowLayout;
import com.aserbao.homepager.customview.snowball.ohtersnowball.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HorizontalFloatLayoutActivity extends AppCompatActivity implements View.OnLayoutChangeListener {

    @BindView(R.id.flow_layout)
    HorizationFlowLayout mFlowLayout;
    @BindView(R.id.scroll_flow_layout)
    HorizontalScrollView mScrollFlowLayout;
    @BindView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Toast.makeText(this, "gfhjda", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.help_left, R.id.image_view, R.id.help_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_left:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.image_view:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.help_right:
                startActivity(new Intent(this, com.aserbao.homepager.MainActivity.class));
                break;
        }
    }
}
