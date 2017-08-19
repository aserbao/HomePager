package com.aserbao.homepager.customview.meteorprocession;

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

    public MeteroFlake(Random random, Point position, Paint paint) {
        this.random = random;
        this.position = position;
        this.paint = paint;
    }

    public static MeteroFlake crete(int width, int height, Paint paint){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        Point point = new Point(x, y);
        return new MeteroFlake(random,point,paint);
    }
}
