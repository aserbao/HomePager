package com.lxf.particle.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lxf.particle.effect.base.EffectScence;
import com.lxf.particle.effect.scene.SnowScence;

/**
 * 基本雪花效果
 * @author xianfeng
 * 2015年4月23日	上午11:37:58
 */
public class SnowAnimation extends EffectAnimation {

	public SnowAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SnowAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SnowAnimation(Context context) {
		super(context);
	}
	
	@Override
	@Deprecated
	protected EffectScence initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();
		
		return new SnowScence(width, height, itemNum);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new SnowScence(width, height, itemNum, itemColor);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor,
			boolean randColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new SnowScence(width, height, itemNum, itemColor, randColor);
	}
}
