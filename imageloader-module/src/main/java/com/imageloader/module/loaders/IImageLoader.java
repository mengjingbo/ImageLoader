package com.imageloader.module.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.imageloader.module.request.ImageOption;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public interface IImageLoader {

    /**
     * 加载Image
     */
    void loadImage(@NonNull ImageOption builder);

    /**
     * 暂停请求
     */
    void pauseRequests(@NonNull Context context);

    /**
     * 暂停所有请求
     */
    void pauseAllRequests(@NonNull Context context);

    /**
     * 恢复请求
     */
    void resumeRequests(@NonNull Context context) ;

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache(@NonNull Context context) ;

    /**
     * 清除View
     */
    void clear(@NonNull View view) ;

    /**
     * 清除内存缓存
     */
    void clearMemory(@NonNull Context context);

    /**
     * 根据设定的级别，将内存缓存自动调节到合适的大小。
     */
    void trimMemory(@NonNull Context context, int level);
}
