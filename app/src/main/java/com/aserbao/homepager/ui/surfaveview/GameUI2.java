package com.aserbao.homepager.ui.surfaveview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.aserbao.homepager.R;

import java.util.Random;

/**
 * Created by aserbao on 2017 2017/8/21.7:18
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class GameUI2 extends SurfaceView implements SurfaceHolder.Callback{
    private Context mContext ;
    private SurfaceHolder mHolder;
    private boolean isDraw  = false;
    private Random mRandom;
    private RenderThread mRenderThread;

    public GameUI2(Context context) {
        this(context,null);
    }

    public GameUI2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GameUI2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mRandom = new Random();
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        mRenderThread = new RenderThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDraw = true;
        mRenderThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDraw = false;
    }

    private class RenderThread extends Thread {
        @Override
        public void run() {
            // 不停绘制界面
            while (isDraw) {
                drawUI();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }
    /**
     * 界面绘制
     */
    public void drawUI() {
        Canvas canvas = mHolder.lockCanvas();
        try {
            drawCanvas(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void drawCanvas(Canvas canvas) {
        // 在 canvas 上绘制需要的图形
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor);
        canvas.drawBitmap(bitmap,mRandom.nextInt(1080),mRandom.nextInt(1920),null);
        mHolder.unlockCanvasAndPost(canvas);
    }
}
