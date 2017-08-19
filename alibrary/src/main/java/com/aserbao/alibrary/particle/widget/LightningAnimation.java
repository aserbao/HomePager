package com.lxf.particle.widget;

import java.util.Random;

import com.lxf.particle.effect.scene.Lightning;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class LightningAnimation extends View {

	private boolean running = false;
	private Lightning lightning;
	private Random rand;
	private int countTime = 0;
	private int during = 3000;

	public LightningAnimation(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public LightningAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LightningAnimation(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(lightning == null){
			initLightning();
		}
		else{
			lightning.draw(canvas);
		}
	}

	private void initLightning() {
		int width = getWidth();
		int height = getHeight();

		rand = new Random();
		lightning = new Lightning(width, height);
		spriteThread.start();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		running = false;
	}

	private Thread spriteThread = new Thread() {
		public void run() {
			running = true;
			while (running) {
				lightning.move();

				try {
					Thread.sleep(50);
					countTime += 50;
					if (countTime > during) {
						countTime = 0;
						during = 3000 + rand.nextInt(6000);
						lightning.reset();
					}
				} catch (InterruptedException e) {
					break;
				}
				mHandler.sendEmptyMessage(0);
			}

		}
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			invalidate();
		};
	};

}
