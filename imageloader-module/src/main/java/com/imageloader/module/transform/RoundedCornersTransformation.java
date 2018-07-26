package com.imageloader.module.transform;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * date        ：2018/5/11
 * author      ：蒙景博
 * description ：圆角处理
 */
public class RoundedCornersTransformation extends BitmapTransformation {

    private int radius;
    private int margin;

    public RoundedCornersTransformation(int radius, int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(new RectF(margin, margin, width - margin, height - margin), radius, radius, paint);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
