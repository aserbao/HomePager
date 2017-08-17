package com.aserbao.homepager.other;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
		chooseVideoFromLocalBeforeKitKat();
	/*	if (Build.VERSION.SDK_INT >= 19) {
			chooseVideoFromLocalKitKat();
		} else {
			chooseVideoFromLocalBeforeKitKat();
		}*/
	}

	/**
	 * API19 之后选择视频
	 */
	protected void chooseVideoFromLocalKitKat() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
//		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
		intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
		try {
			startActivityForResult(intent, 0);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "没找到", Toast.LENGTH_SHORT).show();
		} catch (SecurityException e) {

		}
	}
	protected void chooseVideoFromLocalBeforeKitKat() {
		Intent mIntent = new Intent(Intent.ACTION_GET_CONTENT);
		mIntent.setType("video/*;image/*");
		mIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
		try {
			startActivityForResult(mIntent, 0);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "djks", Toast.LENGTH_SHORT).show();
		}
	}
}
