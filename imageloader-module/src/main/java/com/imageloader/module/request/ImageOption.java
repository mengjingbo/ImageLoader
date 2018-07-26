package com.imageloader.module.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.imageloader.module.cache.CacheMode;
import com.imageloader.module.loaders.IImageLoader;
import com.imageloader.module.mode.DecodeMode;
import com.imageloader.module.mode.PriorityMode;
import com.imageloader.module.mode.ScaleType;
import com.imageloader.module.transform.BlurTransformation;
import com.imageloader.module.transform.CircleTransformation;
import com.imageloader.module.transform.GrayScaleTransformation;
import com.imageloader.module.transform.RotateTransformation;
import com.imageloader.module.transform.RoundedCornersTransformation;

import java.io.File;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public class ImageOption {

    private Context context;
    private IImageLoader loader;
    private Object model;
    private int placeHolderResId;
    private Drawable placeHolderDrawable;
    private int errorResId;
    private Drawable errorDrawable;
    private int fallbackResId;
    private Drawable fallbackDrawable;
    private float thumbnail;
    private String thumbnailUri;
    private ImageSize size;
    private ScaleType scaleType;
    private int encodeQuality;
    private Bitmap.CompressFormat encodeFormat;
    private DecodeMode decodeFormat;
    private BlurTransformation blurTransformation;
    private CircleTransformation circleTransformation;
    private RotateTransformation rotateTransformation;
    private GrayScaleTransformation grayScaleTransformation;
    private RoundedCornersTransformation roundedCornersTransformation;
    private PriorityMode priority;
    private boolean isJustFromCacheLoad;
    private boolean skipMemoryCache;
    private CacheMode diskCacheStrategy;
    private String key;
    private int memoryCacheSize;
    private int bitmapPoolSize;
    private long diskCacheSize;
    private String diskCacheName;
    private ImageView view;
    private OnRequestCallback callback;

    public ImageOption(Builder builder) {
        this.context = builder.context;
        this.loader = builder.loader;
        this.model = builder.model;
        this.placeHolderResId = builder.placeHolderResId;
        this.placeHolderDrawable = builder.placeHolderDrawable;
        this.errorResId = builder.errorResId;
        this.errorDrawable = builder.errorDrawable;
        this.fallbackResId = builder.fallbackResId;
        this.fallbackDrawable = builder.fallbackDrawable;
        this.thumbnail = builder.thumbnail;
        this.thumbnailUri = builder.thumbnailUri;
        this.size = builder.size;
        this.scaleType = builder.scaleType;
        this.encodeQuality = builder.encodeQuality;
        this.encodeFormat = builder.encodeFormat;
        this.decodeFormat = builder.decodeFormat;
        this.blurTransformation = builder.blurTransformation;
        this.circleTransformation = builder.circleTransformation;
        this.rotateTransformation = builder.rotateTransformation;
        this.grayScaleTransformation = builder.grayScaleTransformation;
        this.roundedCornersTransformation = builder.roundedCornersTransformation;
        this.priority = builder.priority;
        this.isJustFromCacheLoad = builder.isJustFromCacheLoad;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.key = builder.key;
        this.memoryCacheSize = builder.memoryCacheSize;
        bitmapPoolSize = 3;
        this.bitmapPoolSize = builder.bitmapPoolSize;
        this.diskCacheSize = builder.diskCacheSize;
        this.diskCacheName = builder.diskCacheName;
    }

    public Context getContext() {
        return context;
    }

    public IImageLoader getLoader() {
        return loader;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public Drawable getPlaceHolderDrawable() {
        return placeHolderDrawable;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public int getFallbackResId() {
        return fallbackResId;
    }

    public Drawable getFallbackDrawable() {
        return fallbackDrawable;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public ImageSize getSize() {
        return size;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public int getEncodeQuality() {
        return encodeQuality;
    }

    public Bitmap.CompressFormat getEncodeFormat() {
        return encodeFormat;
    }

    public DecodeMode getDecodeFormat() {
        return decodeFormat;
    }

    public BlurTransformation getBlurTransformation() {
        return blurTransformation;
    }

    public CircleTransformation getCircleTransformation() {
        return circleTransformation;
    }

    public RotateTransformation getRotateTransformation() {
        return rotateTransformation;
    }

    public GrayScaleTransformation getGrayScaleTransformation() {
        return grayScaleTransformation;
    }

    public RoundedCornersTransformation getRoundedCornersTransformation() {
        return roundedCornersTransformation;
    }

    public PriorityMode getPriority() {
        return priority;
    }

    public boolean isJustFromCacheLoad() {
        return isJustFromCacheLoad;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public CacheMode getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public String getKey() {
        return key;
    }

    public int getMemoryCacheSize() {
        return memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return bitmapPoolSize;
    }

    public long getDiskCacheSize() {
        return diskCacheSize;
    }

    public String getDiskCacheName() {
        return diskCacheName == null || diskCacheName.isEmpty() ? "image-cache" : diskCacheName;
    }

    /**
     * 磁盘地址
     */
    public String getDiskCachePath(){
        return Environment.getExternalStorageDirectory().toString() + File.separator;
    }

    public Object getModel() {
        return model;
    }

    public ImageView getView() {
        return view;
    }

    public OnRequestCallback getCallback() {
        return callback;
    }

    public void into(ImageView image) {
        this.view = image;
        this.loader.loadImage(this);
    }

    public void into(ImageView image, OnRequestCallback callback) {
        this.view = image;
        this.callback = callback;
        this.loader.loadImage(this);
    }

    public static class Builder {

        private IImageLoader loader;
        private Context context;
        /**
         * 资源地址
         */
        private Object model;
        /**
         * 默认占位资源
         */
        private int placeHolderResId;
        private Drawable placeHolderDrawable;
        /**
         * 错误符
         */
        private int errorResId;
        private Drawable errorDrawable;
        /**
         * 备用回调符
         */
        private int fallbackResId;
        private Drawable fallbackDrawable;
        /**
         * 设置缩略图的缩放比例0.0f-1.0f,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
         */
        private float thumbnail;
        /**
         * 设置缩略图的url,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
         */
        private String thumbnailUri;
        /**
         * 图片最终显示在ImageView上的宽高度像素
         */
        private ImageSize size;
        /**
         * 裁剪类型, 以填满整个控件为目标,等比缩放,超过控件时将被裁剪
         */
        private ScaleType scaleType = ScaleType.CENTER_CROP;
        /**
         * 设置图片质量
         */
        private int encodeQuality = 90;
        /**
         * 设置编码格式
         */
        private Bitmap.CompressFormat encodeFormat = Bitmap.CompressFormat.PNG;
        /**
         * 设置图片模式
         */
        private DecodeMode decodeFormat = DecodeMode.PREFER_ARGB_8888;
        /**
         * 高斯模糊
         */
        private BlurTransformation blurTransformation;
        /**
         * 圆角
         */
        private CircleTransformation circleTransformation;
        /**
         * 旋转
         */
        private RotateTransformation rotateTransformation;
        /**
         * 灰度
         */
        private GrayScaleTransformation grayScaleTransformation;
        /**
         * 圆角
         */
        private RoundedCornersTransformation roundedCornersTransformation;
        /**
         * 加载优先级策略
         */
        private PriorityMode priority;
        /**
         * 是否仅从缓存中加载数据，默认false
         */
        private boolean isJustFromCacheLoad;
        /**
         * true,跳过内存缓存,默认true
         */
        private boolean skipMemoryCache = true;
        /**
         * 硬盘缓存,默认为NONE类型
         */
        private CacheMode diskCacheStrategy = CacheMode.NONE;
        /**
         * 对图片进行标识,用来设置内存和磁盘缓存key，使调用者可以更好地控制缓存数据何时失效。
         */
        private String key;
        /**
         * 内存缓存, 默认内存缓存, 值范围 1-2
         */
        private int memoryCacheSize = 1;
        /**
         * Bitmap池, Bitmap池，值范围 1-3
         */
        private int bitmapPoolSize = 3;
        /**
         * 磁盘缓存，磁盘缓存不同于内存缓存和Bitmap池，因此这里默认最大缓存100MB
         */
        private long diskCacheSize = 100 * 1024 * 1024;
        /**
         * 磁盘缓存文件目录名
         */
        private String diskCacheName;

        public Builder setContext(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder setImageLoader(@NonNull IImageLoader loader) {
            this.loader = loader;
            return this;
        }

        /**
         * 设置资源地址
         */
        public Builder load(@Nullable Object model) {
            this.model = model;
            return this;
        }

        /**
         * 设置占位符
         */
        public Builder placeHolder(@DrawableRes int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        /**
         * 设置占位符
         */
        public Builder placeHolder(Drawable placeHolderDrawable) {
            this.placeHolderDrawable = placeHolderDrawable;
            return this;
        }

        /**
         * 设置错误占位符
         */
        public Builder error(@DrawableRes int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        /**
         * 设置错误占位符
         */
        public Builder error(Drawable errorDrawable) {
            this.errorDrawable = errorDrawable;
            return this;
        }

        /**
         * 设置备用占位符
         */
        public Builder fallback(@DrawableRes int fallbackResId) {
            this.fallbackResId = fallbackResId;
            return this;
        }

        /**
         * 设置备用占位符
         */
        public Builder fallback(Drawable fallbackDrawable) {
            this.fallbackDrawable = fallbackDrawable;
            return this;
        }

        /**
         * 设置缩略倍数
         * 值范围0.0f-1.0f
         */
        public Builder thumbnail(@FloatRange(from = 0.0f, to = 1.0f) float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        /**
         * 设置缩略图Uri
         */
        public Builder thumbnailUri(@NonNull String thumbnailUri) {
            this.thumbnailUri = thumbnailUri;
            return this;
        }

        /**
         * 设置加载指定尺寸的资源,设置后资源会被裁剪压缩
         */
        public Builder override(int width, int height) {
            this.size = new ImageSize(width, height);
            return this;
        }

        /**
         * 设置加载指定尺寸的资源,设置后资源会被裁剪压缩
         */
        public Builder override(ImageSize size) {
            this.size = size;
            return this;
        }

        /**
         * 设置图片显示Type
         */
        public Builder scaleType(ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        /**
         * 设置加载的图片质量
         */
        public Builder encodeQuality(@IntRange(from = 1, to = 100) int quality) {
            this.encodeQuality = quality;
            return this;
        }

        /**
         * 设置图片编码格式，png或者jpg
         */
        public Builder encodeFormat(Bitmap.CompressFormat format) {
            this.encodeFormat = format;
            return this;
        }

        /**
         * 设置图片的解码格式， default，PREFER_RGB_565， PREFER_ARGB_8888
         */
        public Builder decodeFormat(DecodeMode format) {
            this.decodeFormat = format;
            return this;
        }

        /**
         * 高斯模糊
         */
        public Builder blur(BlurTransformation transformation) {
            this.blurTransformation = transformation;
            return this;
        }

        /**
         * 圆角处理
         */
        public Builder circle(CircleTransformation transformation) {
            this.circleTransformation = transformation;
            return this;
        }

        /**
         * 旋转
         */
        public Builder rotate(RotateTransformation rotateTransformation) {
            this.rotateTransformation = rotateTransformation;
            return this;
        }

        /**
         * 灰度
         */
        public Builder grayScale(GrayScaleTransformation grayScaleTransformation) {
            this.grayScaleTransformation = grayScaleTransformation;
            return this;
        }

        /**
         * 圆角
         */
        public Builder roundedCorners(RoundedCornersTransformation roundedCornersTransformation) {
            this.roundedCornersTransformation = roundedCornersTransformation;
            return this;
        }

        /**
         * 设置加载代理优先级
         */
        public Builder priority(PriorityMode mode) {
            this.priority = mode;
            return this;
        }

        /**
         * 设置仅从缓存加载
         */
        public Builder justFromCacheLoad(boolean justFromCacheLoad) {
            this.isJustFromCacheLoad = justFromCacheLoad;
            return this;
        }

        /**
         * 是否跳过内存缓存
         */
        public Builder skipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        /**
         * 设置缓存模式
         */
        public Builder diskCacheStrategy(CacheMode mode) {
            this.diskCacheStrategy = mode;
            return this;
        }

        /**
         * 设置缓存key
         */
        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        /**
         * 设置内存缓存大小
         */
        public Builder setMemoryCacheSize(@IntRange(from = 1, to = 2) int size) {
            this.memoryCacheSize = size;
            return this;
        }

        /**
         * 设置Bitmap池
         */
        public Builder setBitmapPoolSize(@IntRange(from = 1, to = 3) int poolSize) {
            this.bitmapPoolSize = poolSize;
            return this;
        }

        /**
         * 设置磁盘缓存大小
         */
        public Builder setDiskCacheSize(long diskCacheSize) {
            this.diskCacheSize = diskCacheSize;
            return this;
        }

        /**
         * 设置磁盘缓存文件名
         */
        public Builder setDiskCacheName(String diskCacheName) {
            this.diskCacheName = diskCacheName;
            return this;
        }

        public ImageOption build() {
            return new ImageOption(this);
        }
    }
}
