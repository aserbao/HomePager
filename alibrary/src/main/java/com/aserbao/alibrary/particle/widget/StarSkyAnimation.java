package com.lxf.particle.widget;
import android.content.Context;
import android.util.AttributeSet;

import com.lxf.particle.effect.base.EffectScence;
import com.lxf.particle.effect.scene.StarSkyScence;


public class StarSkyAnimation extends EffectAnimation {
	
	public StarSkyAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public StarSkyAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StarSkyAnimation(Context context) {
		super(context);
	}
	
	@Override
	protected EffectScence initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();
		
		return new StarSkyScence(width, height, itemNum);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new StarSkyScence(width, height, itemNum, itemColor);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor,
			boolean randColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new StarSkyScence(width, height, itemNum, itemColor, randColor);
	}
	
}
