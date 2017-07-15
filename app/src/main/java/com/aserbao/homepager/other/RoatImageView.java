package com.aserbao.homepager.other;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自动实现内容的旋转
 * 
 * @author zxy
 * 
 */
public class RoatImageView extends ImageView {
	private float degrees = 500;// 当前需要旋转的角度
	private int angleOneTime = 1;// 每次旋转的角度
	private long delayMillis = 100;// 转动一个角度的时间 单位毫秒
	private boolean isClockwise = true;// 转动角度的方向 是否是顺时针旋转 true表示顺时针旋转
										// false表示逆时针旋转
	public RoatImageView(Context context) {
		super(context);
		initView(context);
	}

	public RoatImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public RoatImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	// 初始化
	private void initView(Context context) {
	
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(isReady()){
			this.setRotation(degrees);
		}
		super.onDraw(canvas);
	}
	
	

	/**
	 * 判断当前情况是否能执行手势相关计算
	 *
	 * 包括:是否有图片,图片是否有尺寸,控件是否有尺寸.
	 *
	 * @return 是否能执行手势相关计算
	 */
	private boolean isReady() {
		return getDrawable() != null && getDrawable().getIntrinsicWidth() > 0 && getDrawable().getIntrinsicHeight() > 0
				&& getWidth() > 0 && getHeight() > 0;
	}

	Handler handler = new Handler();

	// 开始旋转
	public void startRotation() {
		stopRotation();
		handler.postDelayed(myRun, delayMillis);
	}

	// 停止旋转
	public void stopRotation() {
		handler.removeCallbacks(myRun);
	}

	private Runnable myRun = new Runnable() {

		@Override
		public void run() {
			if (isClockwise) {
				degrees += angleOneTime;
				if (degrees < -720) {
					degrees += 720;
				}
			} else {
				degrees -= angleOneTime;
				if (degrees > 720) {
					degrees -= 720;
				}
			}
			invalidate();
			handler.postDelayed(myRun, delayMillis);
		}
	};

	/*--------------------相关设置--------------------*/
	// 设置每次旋转的角度
	public void setAngleOneTime(int angleOneTime) {
		this.angleOneTime = Math.abs(angleOneTime);// 确保是正的 免得和旋转方向重复
	}

	// 设置转动一个角度的时间
	public void setDelayMillis(long delayMillis) {
		this.delayMillis = Math.max(1, delayMillis);// 避免为负数或一直绘制
	}

	// 设置顺时针还是逆时针旋转
	public void setClockwise(boolean isClockwise) {
		if (this.isClockwise != isClockwise) {
			this.isClockwise = isClockwise;
		}
	}
}
