package com.imageloader.module.loaders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.imageloader.module.cache.CacheMemoryKey;
import com.imageloader.module.request.ImageOption;
import com.imageloader.module.request.OnRequestCallback;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
@SuppressLint("CheckResult")
public class GlideLoader implements IImageLoader {

    @Override
    public void loadImage(@NonNull final ImageOption option) {
        RequestBuilder mBuilder = Glide.with(option.getContext()).load(option.getModel());
        RequestOptions mRequestOptions = new RequestOptions();
        // 设置占位符
        if (option.getPlaceHolderResId() > 0) {
            mRequestOptions.placeholder(option.getPlaceHolderResId());
        }
        if (option.getPlaceHolderDrawable() != null) {
            mRequestOptions.placeholder(option.getPlaceHolderDrawable());
        }
        // 设置错符
        if (option.getErrorResId() > 0) {
            mRequestOptions.error(option.getErrorResId());
        }
        if (option.getErrorDrawable() != null) {
            mRequestOptions.error(option.getErrorDrawable());
        }
        // 设置备用回调符
        if (option.getFallbackResId() > 0) {
            mRequestOptions.fallback(option.getFallbackResId());
        }
        if (option.getFallbackDrawable() != null) {
            mRequestOptions.fallback(option.getErrorDrawable());
        }
        // 设置缩略倍数
        if (option.getThumbnail() > 0) {
            mBuilder.thumbnail(option.getThumbnail());
        }
        // 设置缩略图
        if (option.getThumbnailUri() != null && !option.getThumbnailUri().isEmpty()) {
            mBuilder.thumbnail(Glide.with(option.getContext()).load(option.getThumbnailUri()));
        }
        // 是否仅从缓存加载数据
        mRequestOptions.onlyRetrieveFromCache(option.isJustFromCacheLoad());
        // 设置是否跳过内存缓存
        mRequestOptions.skipMemoryCache(option.isSkipMemoryCache());
        // 设置是否磁盘缓存
        if (option.getDiskCacheStrategy() != null){
            mRequestOptions.diskCacheStrategy(option.getDiskCacheStrategy().getMode());
        }
        // 设置优先级
        if (option.getPriority() != null){
            mRequestOptions.priority(option.getPriority().getMode());
        }
        // 设置裁剪类型
        switch (option.getScaleType()) {
            case FIT_CENTER:
                mRequestOptions.fitCenter();
                break;
            case CENTER_CROP:
                mRequestOptions.centerCrop();
                break;
            case CIRCLE_CROP:
                mRequestOptions.circleCrop();
                break;
            case CENTER_INSIDE:
                mRequestOptions.centerInside();
                break;
        }
        // 高斯模糊处理
        if (option.getBlurTransformation() != null) {
            mRequestOptions.transform(option.getBlurTransformation());
        }
        // 圆形图片处理
        if (option.getCircleTransformation() != null){
            mRequestOptions.transform(option.getCircleTransformation());
        }
        // 旋转处理
        if (option.getRotateTransformation() != null){
            mRequestOptions.transform(option.getRotateTransformation());
        }
        // 灰度处理
        if (option.getGrayScaleTransformation() != null){
            mRequestOptions.transform(option.getGrayScaleTransformation());
        }
        // 圆角
        if (option.getRoundedCornersTransformation() != null){
            mRequestOptions.transform(option.getRoundedCornersTransformation());
        }
        // 设置编码格式
        if (option.getEncodeFormat() != null) {
            mRequestOptions.encodeFormat(option.getEncodeFormat());
        }
        // 设置解码格式
        if (option.getDecodeFormat() != null) {
            mRequestOptions.format(option.getDecodeFormat().getMode());
        }
        // 设置图片质量
        if (option.getEncodeQuality() > 0) {
            mRequestOptions.encodeQuality(option.getEncodeQuality());
        }
        // 设置图片大小
        if (option.getSize() != null && (option.getSize().getWidth() > 0 && option.getSize().getHeight() > 0)) {
            mRequestOptions.override(option.getSize().getWidth(), option.getSize().getHeight());
        }else {
            mRequestOptions.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        }
        // 设置内存和磁盘缓存key，使调用者可以更好地控制缓存数据何时失效, 如果Key为空，则使用图片地址做key。
        if (option.getKey() != null && !option.getKey().isEmpty()) {
            mRequestOptions.signature(new CacheMemoryKey(option.getKey()));
        } else {
            mRequestOptions.signature(new CacheMemoryKey(option.getModel()));
        }
        mBuilder.apply(mRequestOptions);
        // 设置视图
        if (option.getView() != null) {
            mBuilder.into(option.getView());
        }
        // 设置视图和监听事件
        if (option.getCallback() != null && option.getView() != null) {
            final OnRequestCallback mCallback = option.getCallback();
            if (mCallback == null) return;
            mBuilder.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return mCallback.onSuccess(resource, model, isFirstResource);
                }

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                    return mCallback.onFailed(e, model, isFirstResource);
                }
            }).into(option.getView());
        }
    }

    @Override
    public void pauseRequests(@NonNull Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void pauseAllRequests(@NonNull Context context) {
        Glide.with(context).pauseAllRequests();
    }

    @Override
    public void resumeRequests(@NonNull final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Glide.with(context).resumeRequests();
            }
        });
    }

    @Override
    public void clearDiskCache(@NonNull final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }

    @Override
    public void clear(@NonNull View view) {
        Glide.with(view).clear(view);
    }

    @Override
    public void clearMemory(@NonNull Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void trimMemory(@NonNull Context context, int level) {
        Glide.get(context).trimMemory(level);
    }
}
