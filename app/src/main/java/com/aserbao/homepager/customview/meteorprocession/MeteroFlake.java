package com.aserbao.homepager.customview.meteorprocession;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by aserbao on 2017 2017/8/19.8:01
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class MeteroFlake {

    private final Random random;
    private final Point position;
    private float angle;
    private final Paint paint;
    private int flakeSize = 15;

    public MeteroFlake(Random random, Point position, Paint paint) {
        this.random = random;
        this.position = position;
        this.paint = paint;
    }

    public static MeteroFlake crete(int width, int height, Paint paint){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(1920);
        Point point = new Point(x, y);
        return new MeteroFlake(random,point,paint);
    }

    public void draw(Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawCircle(position.x, position.y, flakeSize, paint);
    }

    private void move(int width, int height) {
        double x = position.x - 50;
        double y = position.y + 50;
        position.set((int)x,(int) y);
        if(!isInside(width, height)){
            reset(width);
        }
    }

    private void reset(int width) {
        position.x = random.nextInt(width);
        position.y = (int)(-flakeSize - 1);
    }

    private boolean isInside(int width, int height) {
        int x = position.x;
        int y = position.y;
        return x >= -flakeSize - 1 && x + flakeSize <= width && y >= -flakeSize - 1 && y - flakeSize < height;
    }
}
