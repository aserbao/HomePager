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

public class GameUI extends SurfaceView implements SurfaceHolder.Callback{
    private Context mContext ;
    private SurfaceHolder mHolder;
    private boolean hasSurface = false;
    private Random mRandom;
    private RenderThread mRenderThread;

    public GameUI(Context context) {
        this(context,null);
    }

    public GameUI(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GameUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mHolder = this.getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hasSurface = true;
        resume();
    }

    private void resume() {
        if (mRenderThread == null) {
            mRenderThread = new RenderThread();
            if(hasSurface) mRenderThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        pause();
    }

    private void pause() {
        // 停止图像更新线程
        if (mRenderThread != null)
        {
            mRenderThread.requestExitAndWait();
            mRenderThread = null;
        }
    }

    private class RenderThread extends Thread {
        private boolean done;

        public RenderThread() {
            this.done = false;
        }

        @Override
        public void run() {
            // 不停绘制界面
            while (!done) {
                drawUI();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }

        public void requestExitAndWait() {
            done = true;
            try
            {
                join();
            }
            catch (InterruptedException ex){}
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
