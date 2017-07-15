package com.aserbao.homepager.other;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.aserbao.homepager.R;

/**
 * Created by zxy on 2017/5/24.
 */

public class MoveView extends View {
	 private static final float FLING_DAMPING_FACTOR = 0.9f;// 惯性动画衰减参数
	    private PointF mLastMovePoint = new PointF();// 手指最后的的位置
	    private Bitmap bgBitmat;// 背景图片
	    private float bgWidth;// 背景图片的宽度
	    private float bgHeitht;// 背景图片的高度
	    private float xStartDefault;//默认开始的位置
	    private float yStartDefault;//默认开始的位置
	    private float xStart;// 背景图片开始取的x位置
	    private float yStart;// 背景图片开始取的y位置
	    private float mTotalWidth;// 控件总的宽度
	    private float mTotalHeitht;// 控件总的高度
	    private Rect src = new Rect();// 背景图片取的范围
	    private Rect dst = new Rect();// 要画view的范围 就是总的view的大小
	    private float xMoveTotal;//x总共能移动的距离
	    private float yMoveTotal;//y总共能够移动的距离

	    private MoveListener moveListener;//移动监听

	    public void setMoveListener(MoveListener moveListener){
	        this.moveListener=moveListener;
	    }

	    public MoveView(Context context) {
	        super(context);
	        initView(context);
	    }

	    public MoveView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        initView(context);
	    }

	    public MoveView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	        initView(context);
	    }

	    private void initView(Context context) {
	        bgBitmat = BitmapFactory.decodeResource(context.getResources(), R.drawable.home_background);
	        bgWidth = bgBitmat.getWidth();
	        bgHeitht = bgBitmat.getHeight();
	    }

	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }

	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        super.onSizeChanged(w, h, oldw, oldh);
	        mTotalWidth = w;
	        mTotalHeitht = h;
	        if(mTotalHeitht>bgHeitht||mTotalWidth>bgWidth){
	            throw new RuntimeException("bg height or width too small");
	        }

	        dst.set(0, 0, w, h);

	        xStartDefault=(bgWidth-mTotalWidth)/2;
	        yStartDefault=(bgHeitht-mTotalHeitht)/2;


	        xStart=xStartDefault;
	        yStart=yStartDefault;


	        xMoveTotal=bgWidth-w;
	        yMoveTotal=bgHeitht-h;

	        src.set((int) xStart, (int) yStart, (int) (xStart + mTotalWidth),
	                (int) (yStart + mTotalHeitht));


	    }

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        canvas.drawBitmap(bgBitmat, src, dst, null);
	        if(moveListener!=null){
	            moveListener.move(xStart, yStart, xMoveTotal, yMoveTotal,xStartDefault,yStartDefault);
	        }
	    }

	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        int action = event.getAction();
	        if (action == MotionEvent.ACTION_DOWN) {
	            // 停止所有动画
	            cancelAllAnimator();
	            // 保存触发点用于move计算差值
	            mLastMovePoint.set(event.getX(), event.getY());
	        } else if (action == MotionEvent.ACTION_MOVE) {
	            // 每次移动产生一个差值累积到图片位置上
	            scrollBy(event.getX() - mLastMovePoint.x, event.getY()- mLastMovePoint.y);
	            // 记录新的移动点
	            mLastMovePoint.set(event.getX(), event.getY());
	        }
	        // 无论如何都处理各种外部手势
	        mGestureDetector.onTouchEvent(event);
	        return true;
	    }

	    /**
	     * 让图片移动一段距离
	     *
	     * 不能移动超过可移动范围,超过了就到可移动范围边界为止.
	     *
	     * @param xDiff
	     *            移动距离
	     * @param yDiff
	     *            移动距离
	     * @return 是否改变了位置
	     */
	    float xStartTemp;// 变化之前取背景bitmap的x开始位置
	    float yStartTemp;// 变化之前取背景bitmap的y开始位置

	    private boolean scrollBy(float xDiff, float yDiff) {
	        xStartTemp = xStart;
	        yStartTemp = yStart;
	        xStart -= xDiff;
	        yStart -= yDiff;
	        if (xStart + mTotalWidth > bgWidth) {
	            xStart = bgWidth - mTotalWidth;
	        }
	        if (yStart + mTotalHeitht > bgHeitht) {
	            yStart = bgHeitht - mTotalHeitht;
	        }
	        xStart = Math.max(0, xStart);
	        yStart = Math.max(0, yStart);
	        // 检查是否有变化
	        boolean isChange = !(xStart == xStartTemp && yStart == yStartTemp);
	        if (isChange) {
	            src.set((int) xStart, (int) yStart, (int) (xStart + mTotalWidth),
	                    (int) (yStart + mTotalHeitht));
	            invalidate();
	        }
	        return isChange;
	    }

	    /**
	     * 常用手势处理
	     *
	     * 在onTouchEvent末尾被执行.
	     */
	    private GestureDetector mGestureDetector = new GestureDetector(
	            MoveView.this.getContext(),
	            new GestureDetector.SimpleOnGestureListener() {

	                public boolean onFling(MotionEvent e1, MotionEvent e2,
	                                       float velocityX, float velocityY) {
	                    fling(velocityX, velocityY);
	                    return true;
	                }

	                public void onLongPress(MotionEvent e) {
	                    // 触发长按

	                }

	                public boolean onDoubleTap(MotionEvent e) {

	                    return true;
	                }

	                public boolean onSingleTapConfirmed(MotionEvent e) {
	                    // 触发点击

	                    return true;
	                }
	            });

	    // 动画
	    private FlingAnimator mFlingAnimator;

	    /**
	     * 执行惯性动画
	     *
	     * 动画在遇到不能移动就停止. 动画速度衰减到很小就停止.
	     *
	     * 其中参数速度单位为 像素/秒
	     *
	     * @param vx
	     *            x方向速度
	     * @param vy
	     *            y方向速度
	     */
	    private void fling(float vx, float vy) {
	        // 清理当前可能正在执行的动画
	        cancelAllAnimator();
	        // 创建惯性动画
	        // FlingAnimator单位为 像素/帧,一秒60帧
	        mFlingAnimator = new FlingAnimator(vx / 60f, vy / 60f);
	        mFlingAnimator.start();
	    }

	    /**
	     * 停止所有手势动画
	     */
	    private void cancelAllAnimator() {
	        if (mFlingAnimator != null) {
	            mFlingAnimator.cancel();
	            mFlingAnimator = null;
	        }
	    }

	    /**
	     * 惯性动画
	     *
	     * 速度逐渐衰减,每帧速度衰减为原来的FLING_DAMPING_FACTOR,当速度衰减到小于1时停止. 当图片不能移动时,动画停止.
	     */
	    private class FlingAnimator extends ValueAnimator implements
	            ValueAnimator.AnimatorUpdateListener {

	        /**
	         * 速度向量
	         */
	        private float[] mVector;

	        /**
	         * 创建惯性动画
	         *
	         * 参数单位为 像素/帧
	         *
	         * @param vectorX
	         *            速度向量
	         * @param vectorY
	         *            速度向量
	         */
	        public FlingAnimator(float vectorX, float vectorY) {
	            super();
	            setFloatValues(0, 1f);
	            setDuration(1000000);
	            addUpdateListener(this);
	            mVector = new float[] { vectorX, vectorY };
	        }

	        @Override
	        public void onAnimationUpdate(ValueAnimator animation) {
	            // 移动图像并给出结果
	            boolean result = scrollBy(mVector[0], mVector[1]);
	            // 衰减速度
	            mVector[0] *= FLING_DAMPING_FACTOR;
	            mVector[1] *= FLING_DAMPING_FACTOR;
	            // 速度太小或者不能移动了就结束
	            if (!result || getDistance(0, 0, mVector[0], mVector[1]) < 1f) {
	                animation.cancel();
	            }
	        }

	        /**
	         * 获取两点之间距离
	         *
	         * @param x1
	         *            点1
	         * @param y1
	         *            点1
	         * @param x2
	         *            点2
	         * @param y2
	         *            点2
	         * @return 距离
	         */
	        private float getDistance(float x1, float y1, float x2, float y2) {
	            float x = x1 - x2;
	            float y = y1 - y2;
	            return (float) Math.sqrt(x * x + y * y);
	        }

	    }



	    public interface MoveListener{
	        /**
	         *
	         * @param x x方向开始绘制的地方
	         * @param y  y方向开始绘制的地方
	         * @param xTotal x方向总共可以移动的距离
	         * @param yTotal y方向总共可以移动的距离
	         * @param xStartDefault 默认x开始绘制的位置
	         * @param ystartDefault  默认y开始绘制的位置
	         */

	        void move(float x, float y, float xTotal, float yTotal, float xStartDefault, float ystartDefault);
	    };
}
