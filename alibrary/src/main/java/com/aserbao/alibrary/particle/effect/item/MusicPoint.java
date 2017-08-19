package com.lxf.particle.effect.item;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lxf.particle.effect.base.EffectItem;

public class MusicPoint extends EffectItem{
	
	private Paint paint = new Paint();
	private int x;
	private int centerY;
	private int maxH;
	private int distance;
	private int itemSize;
	private int num;
	
	public MusicPoint(int x, int width, int height){
		this(width, height);
		this.x = x;
	}
	
	public MusicPoint(int x, int width, int height, int color){
		this(x, width, height);
		this.color = color;
		paint.setColor(color);
	}
	public MusicPoint(int x, int width, int height, int color, boolean randColor){
		super(width, height, color, randColor);
		this.x = x;
		centerY = height / 2;
		maxH = height / 3;
		itemSize = maxH / 15;
		distance = itemSize / 2;
		randomColor();
		paint.setColor(this.color);
	}
	public MusicPoint(int width, int height) {
		super(width, height);
		
		centerY = height / 2;
		maxH = height / 3;
		itemSize = maxH / 15;
		distance = itemSize / 2;
		
		paint.setColor(0xffffffff);
	}

	@Override
	public void draw(Canvas canvas) {
		//绘制向上的音符块
		paint.setAlpha(100);
		for(int i = 0; i < num; i ++){
			canvas.drawRect(x + distance, centerY - (i + 1) * itemSize - i * distance, x + itemSize + distance, centerY - i * (itemSize + distance), paint);
		}
		//绘制向下的镜像音符块
		paint.setAlpha(50);
		for(int i = 0; i < num; i ++){
			canvas.drawRect(x + distance, centerY + i * (itemSize + distance), x + itemSize + distance, centerY + (i + 1) * itemSize + i * distance, paint);
		}
	}

	int count = 0;
	@Override
	public void move() {
		count ++;
		if(count >= 6){
			count = 0;
			num = 1 + rand.nextInt(10);
		}
	}
	
}
