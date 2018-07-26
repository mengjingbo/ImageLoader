package com.imageloader.module.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.imageloader.module.ImageLoader;
import com.imageloader.module.request.ImageOption;

/**
 * date        ：2018/7/23
 * author      ：蒙景博
 * description ：GlideModule采用LRU 算法
 * 内存缓存：默认大小10MB
 * 磁盘缓存：缓存在特定目录中，大小为250MB
 * MemorySizeCalculator: 一个计算器，基于某些常数和设备屏幕密度，宽度和高度，尝试智能地确定给定设备的高速缓存大小。
 */
@GlideModule
public class DiskCacheModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        ImageOption mImageOption = ImageLoader.getDefault().getImageOption();
        MemorySizeCalculator mCalculator = new MemorySizeCalculator.Builder(context)
                .setBitmapPoolScreens(mImageOption.getBitmapPoolSize())
                .setMemoryCacheScreens(mImageOption.getMemoryCacheSize())
                .build();
        // Bitmap池, LruBitmapPool:负责控制缓存
        builder.setBitmapPool(new LruBitmapPool(mCalculator.getBitmapPoolSize()));
        // 内存缓存
        builder.setMemoryCache(new LruResourceCache(mCalculator.getMemoryCacheSize()));
        // 磁盘缓存
        builder.setDiskCache(new DiskLruCacheFactory(mImageOption.getDiskCachePath(), mImageOption.getDiskCacheName(), mImageOption.getDiskCacheSize()));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        // 返回false,表示禁用AndroidManifest解析改善 Glide 的初始启动时间。
        return false;
    }
}
