package com.imageloader.module.transform;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * date        ：2018/5/11
 * author      ：蒙景博
 * description ：灰度处理
 */
public class GrayScaleTransformation extends BitmapTransformation {

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap.Config config = toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = pool.get(toTransform.getWidth(), toTransform.getHeight(), config);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), config);
        }
        Canvas canvas = new Canvas(bitmap);
        ColorMatrix saturation = new ColorMatrix();
        saturation.setSaturation(0f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(saturation));
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
