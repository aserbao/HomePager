package com.aserbao.alibrary.particle.effect.base;

import java.util.Random;

/**
 * 效果基础类
 * @author xianfeng
 *
 */
public abstract class EffectItem implements EffectBase{
	/**
	 * 显示区域的宽度
	 */
	protected int width;
	/**
	 * 显示区域的高度
	 */
	protected int height;
	/**
	 * 效果元素的随机对象
	 */
	protected Random rand;
	/**
	 * item color
	 */
	protected int color;
	/**
	 * rand item color
	 */
	protected boolean randColor;
	
	public EffectItem(int width, int height){
		this.width = width;
		this.height = height;
		rand =new Random();
	}
	
	public EffectItem(int width, int height, int color){
		this.width = width;
		this.height = height;
		this.color = color;
		rand =new Random();
	}
	
	public EffectItem(int width, int height, int color, boolean randColor){
		this.width = width;
		this.height = height;
		this.color = color;
		this.randColor = randColor;
		rand =new Random();
	}
	
	protected void randomColor(){
		if(randColor){
			int alpha = 200;
			int r = rand.nextInt(255);
			int g = rand.nextInt(255);
			int b = rand.nextInt(255);
			color = alpha << 24 | r << 16 | g << 8 | b; 
		}
	}
}
