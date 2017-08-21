package com.aserbao.alibrary.particle.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.alibrary.particle.effect.base.EffectScence;
import com.aserbao.aslibrary.R;

/**
 * 效果动画基类
 * @author xianfeng
 *
 */
@SuppressLint("NewApi") public abstract class EffectAnimation extends View {

	protected final String TAG = "EffectAnimation";
	
	private Thread spriteThread;
	private boolean running = false;
	protected EffectScence scence;
	//item attribute
	private int itemNum;
	private int itemColor = 0xffffffff;
	private boolean randColor = false;
	//clip 
	private boolean clip_scale = false;
	private int clip_radius = 0;
	private int clip_step = 0;
	private int clip_dur_time = 2000;
	
	public EffectAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public EffectAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EffectAnimation);
		itemNum = a.getInt(R.styleable.EffectAnimation_itemNum, 0);
		itemColor = a.getColor(R.styleable.EffectAnimation_itemColor, 0xffffffff);
		randColor = a.getBoolean(R.styleable.EffectAnimation_randColor, false);
		clip_scale = a.getBoolean(R.styleable.EffectAnimation_clipScale, false);
		a.recycle();
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	public EffectAnimation(Context context) {
		super(context);
	}

	/**
	 * 需要初始话的效果场景
	 * @return
	 */
	protected abstract EffectScence initScence(int itemNum);

	/**
	 * need init scence
	 * @param itemNum
	 * @param itemColor
	 * @return
	 */
	protected abstract EffectScence initScence(int itemNum, int itemColor);
	
	/**
	 * need init scence
	 * @param itemNum
	 * @param itemColor
	 * @param randColor
	 * @return
	 */
	protected abstract EffectScence initScence(int itemNum, int itemColor, boolean randColor);
	
	private void init(){
		running = false;
		if(itemNum == 0){
			itemNum = 100;
		}
		
		clip_step = getWidth() < getHeight() ? getHeight() : getWidth();
		clip_step /= 2;
		clip_radius = 0;
		
		scence = initScence(itemNum, itemColor, randColor);
		spriteThread = new Thread(run);
		spriteThread.start();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(scence != null){
			if(clip_scale){
				Path path = new Path();
				path.addCircle(getWidth() / 2, getHeight() / 2, clip_radius, Direction.CCW);
				canvas.save();
				canvas.clipPath(path);
				scence.draw(canvas);
				canvas.restore();
			}else{
				scence.draw(canvas);
			}
		}
		else{
			init();
		}
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		running = false;
	}
	
	private Runnable run = new Runnable(){
		public void run() {
			running = true;
			long curTime = 0;
			while (running) {
				curTime = System.currentTimeMillis();
				animLogic();
				mHandler.sendEmptyMessage(0);
				curTime = System.currentTimeMillis() - curTime;
				if(curTime < 30){
					try {
						Thread.sleep(30 - curTime);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		}
	};
	
	private void animLogic(){
		if(scence != null){
			scence.move();
		}
		
		clip_radius += 30 * clip_step / clip_dur_time ;
		if(clip_radius > clip_step){
			clip_scale = false;
		}
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			invalidate();
		};
	};
	
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	};
}
