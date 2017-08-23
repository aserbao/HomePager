package com.aserbao.homepager.customview.customview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.aserbao.homepager.R;

import java.util.Random;


/**
 * Created by aserbao on 2017 2017/8/20.21:35
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class Meteor {
    private final Random random;
    private final Point position;
    private final Paint mPaint;
    private int speed = 25;

    public static Meteor create(int width, int height, Paint paint){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        Point position = new Point(x, y);
        return new Meteor(random , position, paint);
    }
    Meteor(Random random,Point position,Paint paint){
        this.random = random;
        this.position = position;
        this.speed = 10 + random.nextInt(40);
        mPaint = paint;
    }

    public void draw(Canvas canvas, Resources r,int i){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Bitmap bitmap = BitmapFactory.decodeResource(r, R.drawable.meteor);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        move(width, height,bitmapWidth,bitmapHeight,i);
        canvas.drawBitmap(bitmap,position.x,position.y,mPaint);
    }

    private void move(int width, int height,int bitmapWidth,int bitmapHeight,int i) {
        int x = position.x - speed; 
        int y = position.y + speed;
        position.set(x,y);
        if(!isInside(width,height,bitmapWidth,bitmapHeight)){
            reset(width,height,i);
        }
    }

    private void reset(int width, int height,int i) {
        speed = 10+random.nextInt(40);
        position.x = random.nextInt(width);
        position.y = random.nextInt(height);
    }

    private boolean isInside(int width, int height,int bitmapWidth,int bitmapHeight) {
        boolean b = position.x + bitmapWidth > 0 && position.y < height;
        return b;
    }

}
