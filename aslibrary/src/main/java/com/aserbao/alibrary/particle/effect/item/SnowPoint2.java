package com.aserbao.alibrary.particle.effect.item;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.aserbao.alibrary.particle.effect.base.EffectItem;

public class SnowPoint2 extends EffectItem{

	private Paint paint = new Paint();
	private final int size = 36; // 长度在0-50像素
	private int lenSize;// 边长
	private int mul;// 倍数
	private Point point; // 雪点
	private Point speed; // 雪点x,y方向速度

	public SnowPoint2(int width, int height) {
		super(width, height);
		point = new Point();
		speed = new Point();
		paint.setColor(0xffffffff);
		reset();
	}
	public SnowPoint2(int width, int height, int color) {
		super(width, height, color);
		point = new Point();
		speed = new Point();
		paint.setColor(color);
		reset();
	}
	public SnowPoint2(int width, int height, int color, boolean randColor) {
		super(width, height, color, randColor);
		point = new Point();
		speed = new Point();
		reset();
	}
	public void draw(Canvas canvas) {
		// 变长小于等于８绘制圆形
		if (lenSize <= 10) {
			canvas.drawCircle(point.x, point.y, lenSize / 2, paint);
		} else {
			// 绘制雪花形状
			drawSknow(canvas);
		}
	}

	int count = 0;

	public void move() {
		point.x += speed.x;
		point.y += speed.y;
		count++;
		if (count > 5) {
			count = 0;
			speed.x = rand.nextInt(size);
			speed.x = speed.x > speed.y ? speed.y : speed.x;
			speed.x = rand.nextBoolean() ? -speed.x : speed.x;
			speed.y += rand.nextBoolean() ? 1 : 0;
		}

		if (point.x < 0 || point.x > width || point.y > height) {
			reset();
		}
	}

	private void reset() {
		point.x = rand.nextInt(width);
		point.y = rand.nextInt(height);
		lenSize = rand.nextInt(size);

		lenSize += lenSize % 5;
		mul = lenSize / 5;

		int speedX = rand.nextInt(size);
		int speedY = rand.nextInt(size);
		/*
		 * int speedX = w; int speedY = h;
		 */

		speedX = speedX == 0 ? 1 : speedX;
		speedY = speedY == 0 ? 1 : speedY;
		speedX = speedX > speedY ? speedY : speedX;

		speed.x = speedX;
		speed.y = speedY;
		randomColor();
		paint.setColor(color);
	}

	private void drawSknow(Canvas canvas) {
		int y = mul * 3;
		int x = mul * 4;

		if (speed.x > 0) {
			// 竖雪花
			canvas.drawLine(point.x, point.y - (float) lenSize / 2, point.x,
					point.y + (float) lenSize / 2, paint);
			canvas.drawLine(point.x - (float) x / 2, point.y - (float) y / 2,
					point.x + (float) x / 2, point.y + (float) y / 2, paint);
			canvas.drawLine(point.x - (float) x / 2, point.y + (float) y / 2,
					point.x + (float) x / 2, point.y - (float) y / 2, paint);
		} else {
			// 横雪花
			canvas.drawLine(point.x - (float) lenSize / 2, point.y, point.x
					+ (float) lenSize / 2, point.y, paint);
			canvas.drawLine(point.x - (float) y / 2, point.y - (float) x / 2,
					point.x + (float) y / 2, point.y + (float) x / 2, paint);
			canvas.drawLine(point.x - (float) y / 2, point.y + (float) x / 2,
					point.x + (float) y / 2, point.y - (float) x / 2, paint);
		}

	}

}
