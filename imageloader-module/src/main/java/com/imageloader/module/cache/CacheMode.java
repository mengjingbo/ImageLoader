package com.imageloader.module.cache;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public enum  CacheMode {

    /**
     * 缓存所有版本的图片
     */
    ALL(DiskCacheStrategy.ALL),
    /**
     * 不缓存数据。
     */
    NONE(DiskCacheStrategy.NONE),
    /**
     * 在解码之前将检索到的数据直接写入磁盘缓存。
     */
    DATA(DiskCacheStrategy.DATA),
    /**
     * 将资源解码后写入到磁盘。
     */
    RESOURCE(DiskCacheStrategy.RESOURCE),
    /**
     * 默认缓存策略，缓存原始数据
     */
    AUTOMATIC(DiskCacheStrategy.AUTOMATIC);

    private DiskCacheStrategy mode;

    CacheMode(DiskCacheStrategy mode) {
        this.mode = mode;
    }

    public DiskCacheStrategy getMode() {
        return mode;
    }
}
