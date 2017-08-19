package com.lxf.particle.effect.item;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.lxf.particle.effect.base.EffectItem;

public class RainPoint extends EffectItem{

	private Paint paint = new Paint();
	private	final int size = 50;	//长度在0-50像素
	private Rect point;		//雨点
	private Point speed;		//雨点x,y方向速度
	
	public RainPoint(int width, int height){
		super(width, height);
		point = new Rect();
		speed = new Point();

		paint.setColor(0xffffffff);
		reset();
	}
	
	public RainPoint(int width, int height, int color){
		super(width, height, color);
		point = new Rect();
		speed = new Point();

		paint.setColor(color);
		reset();
	}
	
	public RainPoint(int width, int height, int color, boolean randColor){
		super(width, height, color, randColor);
		point = new Rect();
		speed = new Point();
		reset();
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawLine(point.left, point.top, point.right, point.bottom, paint);
	}
	
	public void move(){
		point.left += speed.x;
		point.top += speed.y;
		point.right = point.right + speed.x;
		point.bottom = point.bottom + speed.y;
		
		if(point.left < 0 || point.left > width || point.bottom > height){
			reset();
		}
		speed.y += rand.nextBoolean() ? 1 : 0;
	}
	
	private void reset(){
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		int w = rand.nextInt(size / 2);
		int h = rand.nextInt(size);
		
		w = w > h ? h : w;
		
		point.left = x;
		point.top = y;
		point.right = x - w;
		point.bottom = y + h;
		
//		int speedX = rand.nextInt(size / 2);
//		int speedY = rand.nextInt(size);
		int speedX = w;
		int speedY = h;
		
		speedX = speedX == 0  ? 1 : speedX;
		speedY = speedY == 0  ? 1 : speedY;
		speedX = speedX > speedY ? speedY : speedX;
		
		speed.x = -speedX;
		speed.y = speedY;
		randomColor();
		paint.setColor(color);
	}
	
	public void printPosition(){
		Log.d("rainPoint", "x : " + point.left + " y : " + point.top + " r : " + point.right + " b : " + point.bottom);
	}
	
}
