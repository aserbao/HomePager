package com.aserbao.homepager.other;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.aserbao.homepager.R;

/**
 * Created by zxy on 2017/5/24.
 */

public class MoveFrameLayout extends FrameLayout {
	private int screenWidth;// 屏幕的宽 px
	private int screenHeight;// 屏幕整体高度（包括手机状态栏）px

	private RoatImageView roatImageView1;
	private RoatImageView roatImageView1_1;
	private int iv1_magin_top_default;//
	private int iv1_magin_left_default;//
	private int iv1_magin_top;//
	private int iv1_magin_left;//
	private RoatImageView roatImageView2;
	private int iv2_magin_top_default;//
	private int iv2_magin_left_default;//
	private int iv2_magin_top;//
	private int iv2_magin_left;//
	private RoatImageView roatImageView3;
	private int iv3_magin_top_default;//
	private int iv3_magin_left_default;//
	private int iv3_magin_top;//
	private int iv3_magin_left;//
	private RoatImageView roatImageView4;
	private int iv4_magin_top_default;//
	private int iv4_magin_left_default;//
	private int iv4_magin_top;//
	private int iv4_magin_left;//

	public MoveFrameLayout(Context context) {
		this(context, null);
	}

	public MoveFrameLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MoveFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		// 获取屏幕的宽高
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		// w h 比率
		float wRatio = 1.0f * screenWidth / 1080;
		float hRatio = 1.0f * screenHeight / 1920;
		roatImageView1 = new RoatImageView(context);
		roatImageView1.setImageResource(R.drawable.home_star);
		roatImageView1.setEnabled(false);
		roatImageView1.startRotation();
		LayoutParams lp1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(roatImageView1, lp1);

		roatImageView1_1 = new RoatImageView(context);
		roatImageView1_1.setImageResource(R.drawable.home_star_halo);
		roatImageView1_1.setEnabled(false);
		roatImageView1_1.setClockwise(false);
		roatImageView1_1.startRotation();
		LayoutParams lp1_1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(roatImageView1_1, lp1_1);

		roatImageView2 = new RoatImageView(context);
		roatImageView2.setImageResource(R.drawable.home_rankinglist);
		roatImageView2.setEnabled(false);
		roatImageView2.setClockwise(false);
		roatImageView2.startRotation();
		LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(roatImageView2, lp2);

		roatImageView3 = new RoatImageView(context);
		roatImageView3.setImageResource(R.drawable.home_qiuhuan);
		roatImageView3.setEnabled(false);
		roatImageView3.startRotation();
		LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(roatImageView3, lp3);

		roatImageView4 = new RoatImageView(context);
		roatImageView4.setImageResource(R.drawable.home_maillist);
		roatImageView4.setEnabled(false);
		roatImageView1.setAngleOneTime(2);
		roatImageView4.startRotation();
		LayoutParams lp4 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(roatImageView4, lp4);

		MoveView iv = new MoveView(context);
		iv.setEnabled(true);
		LayoutParams lpBg = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		iv.setMoveListener(bgMoveListener);
		addView(iv, 0, lpBg);

		iv1_magin_top_default = (int) (60 * hRatio);
		iv1_magin_left_default = (int) (150 * wRatio);
		iv2_magin_top_default = (int) (205 * hRatio);
		iv2_magin_left_default = (int) (519 * wRatio);
		iv3_magin_top_default = (int) (607 * hRatio);
		iv3_magin_left_default = (int) (188 * wRatio);
		iv4_magin_top_default = (int) (1100 * hRatio);
		iv4_magin_left_default = (int) (555 * wRatio);
		moveOntherView();
	}

	@Override
	public boolean onInterceptHoverEvent(MotionEvent event) {
		return super.onInterceptHoverEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	// 计算其他空间的
	private void moveOntherView() {
		LayoutParams lp1 = (LayoutParams) roatImageView1.getLayoutParams();
		lp1.setMargins(iv1_magin_left, iv1_magin_top, 0, 0);
		roatImageView1.setLayoutParams(lp1);
		LayoutParams lp1_1 = (LayoutParams) roatImageView1_1.getLayoutParams();
		lp1_1.setMargins(iv1_magin_left, iv1_magin_top, 0, 0);
		roatImageView1_1.setLayoutParams(lp1_1);

		LayoutParams lp2 = (LayoutParams) roatImageView2.getLayoutParams();
		lp2.setMargins(iv2_magin_left, iv2_magin_top, 0, 0);
		roatImageView2.setLayoutParams(lp2);

		LayoutParams lp3 = (LayoutParams) roatImageView3.getLayoutParams();
		lp3.setMargins(iv3_magin_left, iv3_magin_top, 0, 0);
		roatImageView3.setLayoutParams(lp3);

		LayoutParams lp4 = (LayoutParams) roatImageView4.getLayoutParams();
		lp4.setMargins(iv4_magin_left, iv4_magin_top, 0, 0);
		roatImageView4.setLayoutParams(lp4);

	}

	// 背景移动监听
	private MoveView.MoveListener bgMoveListener = new MoveView.MoveListener() {

		@Override
		public void move(float x, float y, float xTotal, float yTotal,
				float xStartDefault, float ystartDefault) {
			iv1_magin_top = (int) (iv1_magin_top_default + (-y + ystartDefault) / 2);
			iv1_magin_left = (int) (iv1_magin_left_default + (-x + xStartDefault) / 2);

			iv2_magin_top = (int) (iv2_magin_top_default + (-y + ystartDefault) / 2);
			iv2_magin_left = (int) (iv2_magin_left_default + (-x + xStartDefault) / 2);

			iv3_magin_top = (int) (iv3_magin_top_default + (-y + ystartDefault) / 2);
			iv3_magin_left = (int) (iv3_magin_left_default + (-x + xStartDefault) / 2);

			iv4_magin_top = (int) (iv4_magin_top_default + (-y + ystartDefault) / 2);
			iv4_magin_left = (int) (iv4_magin_left_default + (-x + xStartDefault) / 2);
			moveOntherView();
		}
	};
}
