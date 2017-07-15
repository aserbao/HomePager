package com.aserbao.homepager.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.aserbao.homepager.R;
import com.aserbao.homepager.aScollView.TestScollView;

/**
 * 
 * @author zxy
 * 
 */
public class MainActivity extends Activity {

	private TestScollView mTestScollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_main);
		mTestScollView = (TestScollView) findViewById(R.id.test_scoll_view);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mTestScollView.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
}
