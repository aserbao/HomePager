package com.aserbao.homepager.customview.flowlayout;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import com.aserbao.homepager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HorizationFlowLayout extends ViewGroup
{
	String[] sName = {"大黄","铳刘","走走","油菜","回家"};
	private static final String TAG = "FlowLayout";


	public HorizationFlowLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context,attrs);
	}

	private void init(Context mContext,AttributeSet attrs) {
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			RadioButton rb = new RadioButton(mContext);
			int anInt = random.nextInt(sName.length);
			rb.setText(sName[anInt]);
			rb.setMaxLines(1);
			rb.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
			rb.setButtonDrawable(null);
			/*MarginLayoutParams layoutParams = new MarginLayoutParams(mContext,attrs);
			layoutParams.setMargins(random.nextInt(300),random.nextInt(100),0,0);
			rb.setLayoutParams(layoutParams);*/
			rb.setPadding(random.nextInt(300),random.nextInt(100),0,0);
			/*rb.setWidth(290);
			rb.setHeight(391);*/
//			rb.setHeight(190+random.nextInt(300));
			Drawable drawable = getResources().getDrawable(R.drawable.help_dian);
			rb.setCompoundDrawablesWithIntrinsicBounds(null,null,null,drawable);
			this.addView(rb);
		}
	}

	@Override
	protected LayoutParams generateLayoutParams(
			LayoutParams p)
	{
		return new MarginLayoutParams(p);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs)
	{
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams()
	{
		return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
//		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	/**
	 * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获得它的父容器为它设置的测量模式和大小
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		Log.e(TAG, sizeWidth + "," + sizeHeight);

		// 如果是warp_content情况下，记录宽和高
		int width = 0;
		int height = 0;
		/**
		 * 记录每一行的宽度，width不断取最大宽度
		 */
		int lineWidth = 0;
		/**
		 * 每一行的高度，累加至height
		 */
		int lineHeight = 0;

		int cCount = getChildCount();
		Random random = new Random();
		// 遍历每个子元素
		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
			// 测量每一个child的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			// 得到child的lp
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			/*lp.setMargins(random.nextInt(300),random.nextInt(100),0,0);
			child.setLayoutParams(lp);*/
			// 当前子空间实际占据的宽度
			int childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			// 当前子空间实际占据的高度
			int childHeight = child.getMeasuredHeight() + lp.topMargin
					+ lp.bottomMargin;
			/**
			 * 如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
			 */
			if (lineHeight + childHeight > sizeHeight)
			{
				height = Math.max(lineHeight, childHeight);// 取最大的
				lineHeight = childHeight; // 重新开启新行，开始记录
				// 叠加当前高度，
				width += lineWidth;
				// 开启记录下一行的高度
				Log.e("onlayout  + onMeasure", "第" + i + "  lineWidth:=====" + lineWidth);
				lineWidth = childWidth;
			} else
			// 否则累加值lineWidth,lineHeight取最大高度
			{
				lineHeight += childHeight;
				lineWidth = Math.max(lineWidth, childWidth);
			}
			// 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
			if (i == cCount - 1)
			{
				height = Math.max(height, lineHeight);
				width += lineWidth;
			}
		}
//		setMeasuredDimension(3647,1905);
		Log.e("onlayout  + onMeasure", "第" +"width:=====" + width+ "height:"+height +"final====================");
		setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth - (1 * cCount)
				: width - (1 * cCount) , (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
				: height);

	}
	/**
	 * 存储所有的View，按行记录
	 */
	private List<List<View>> mAllViews = new ArrayList<List<View>>();
	/**
	 * 记录每一行的最大高度
	 */
	private List<Integer> mLineWidth = new ArrayList<Integer>();
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mAllViews.clear();
		mLineWidth.clear();

		int width = getWidth();
		int height = getHeight();

		int lineWidth = 0;
		int lineHeight = 0;
		// 存储每一行所有的childView
		List<View> lineViews = new ArrayList<View>();
		int cCount = getChildCount();
		// 遍历所有的孩子
		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			// 当前子空间实际占据的高度
			int childHeight = child.getMeasuredHeight() + lp.topMargin
					+ lp.bottomMargin;

			// 如果已经需要换行
			if (childHeight + lineHeight > height)
			{
				// 记录这一行所有的View以及最大宽度
				mLineWidth.add(lineWidth);
				// 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
				mAllViews.add(lineViews);
				lineWidth = 0;
				lineHeight = 0;// 重置行宽
				lineViews = new ArrayList<View>();
			}else {
				lineHeight += childHeight;
				lineWidth = Math.max(lineWidth, childWidth);
				lineViews.add(child);
			}
		}
		// 记录最后一行
		mLineWidth.add(lineWidth);
		mAllViews.add(lineViews);

		int left = 0;
		int top = 0;
		// 得到总行数
		int columnNums = mAllViews.size();
		for (int i = 0; i < columnNums; i++)
		{
			// 每一行的所有的views
			lineViews = mAllViews.get(i);
			// 当前行的最大高度
			lineWidth = mLineWidth.get(i);


			// 遍历当前行所有的View
			for (int j = 0; j < lineViews.size(); j++)
			{
				View child = lineViews.get(j);
				if (child.getVisibility() == View.GONE)
				{
					continue;
				}
				MarginLayoutParams lp = (MarginLayoutParams) child
						.getLayoutParams();

				//计算childView的left,top,right,bottom
				int lc = left + lp.leftMargin;
				int tc = top + lp.topMargin;
				int rc =lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();

				Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
						+ rc + " , b = " + bc);
				Random random = new Random();
				int i5 = random.nextInt(10);
				child.layout(lc+i5, tc, rc+i5, bc);
				
				top += child.getMeasuredHeight() + lp.topMargin
						+ lp.bottomMargin;
			}
			top = 0;
			left += lineWidth;
			Log.e("onlayout", "第" + i + "   lineWidth:=====" + lineWidth + "left =====" + left);
		}

	}
}
