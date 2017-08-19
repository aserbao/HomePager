package com.lxf.particle.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lxf.particle.effect.base.EffectScence;
import com.lxf.particle.effect.scene.Snow2Scence;

public class SnowAnimation2 extends EffectAnimation {

	public SnowAnimation2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SnowAnimation2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SnowAnimation2(Context context) {
		super(context);
	}

	@Override
	protected EffectScence initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();

		return new Snow2Scence(width, height, itemNum);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor) {
		int width = getWidth();
		int height = getHeight();

		return new Snow2Scence(width, height, itemNum, itemColor);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor,
			boolean randColor) {
		int width = getWidth();
		int height = getHeight();

		return new Snow2Scence(width, height, itemNum, itemColor, randColor);
	}

}
