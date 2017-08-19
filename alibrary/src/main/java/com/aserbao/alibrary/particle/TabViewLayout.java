package com.lxf.particle;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabViewLayout extends HorizontalScrollView implements OnPageChangeListener {

	private LinearLayout tabContainer;
	private ViewPager mViewPager;
	
	public TabViewLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabViewLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(getContext()).inflate(R.layout.tab_container, this);
	}

	public TabViewLayout(Context context) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.tab_container, this);
	}

	public void setViewPager(ViewPager viewPager){
		mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(this);
		initTab();
	}
	
	private void initTab(){
		tabContainer = (LinearLayout) findViewById(R.id.tab_container);
		PagerAdapter adapter = mViewPager.getAdapter();
		int childCount = adapter.getCount();
		for(int i = 0; i < childCount; i ++){
			final int index = i;
			TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_view, null);
			view.setText(adapter.getPageTitle(i));
			view.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if(mViewPager != null){
						mViewPager.setCurrentItem(index);
					}
				}
			});
			tabContainer.addView(view);
		}
	}
	
//	@Override
//	protected void onFinishInflate() {
//		super.onFinishInflate();
//		tabContainer = (LinearLayout) findViewById(R.id.tab_container);
//		
//		int childCount = tabContainer.getChildCount();
//		for(int i = 0; i < childCount; i ++){
//			final int index = i;
//			View view = tabContainer.getChildAt(i);
//			view.setOnClickListener(new View.OnClickListener() {
//				public void onClick(View v) {
//					if(mViewPager != null){
//						mViewPager.setCurrentItem(index);
//					}
//				}
//			});
//		}
//	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
	
}
