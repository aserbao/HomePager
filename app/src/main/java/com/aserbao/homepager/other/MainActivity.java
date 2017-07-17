package com.aserbao.homepager.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.aserbao.homepager.R;
import com.aserbao.homepager.aScollView.ScollViewOne;
import com.aserbao.homepager.aScollView.ScollViewTwo;

/**
 * 
 * @author zxy
 * 
 */
public class MainActivity extends Activity {

	private ScollViewOne mScollViewOne;
	private ScollViewTwo mScollViewTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_main);
		mScollViewOne = (ScollViewOne) findViewById(R.id.test_scoll_view);
		mScollViewTwo = (ScollViewTwo) findViewById(R.id.test_scoll_view_one);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mScollViewOne.onTouchEvent(event);
		mScollViewTwo.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	public void btn_click(View view) {
		Toast.makeText(this, "点什么店？", Toast.LENGTH_SHORT).show();
	}
}
