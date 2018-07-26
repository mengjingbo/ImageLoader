package com.imageloader.module.cache;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;

import java.security.MessageDigest;

/**
 * date        ：2018/7/23
 * author      ：蒙景博
 * description ：用来设置内存和磁盘缓存key，使调用者可以更好地控制缓存数据何时失效。
 */
public class CacheMemoryKey implements Key {

    private final Object object;

    public CacheMemoryKey(@NonNull Object object) {
        this.object = Preconditions.checkNotNull(object);
    }

    @Override
    public String toString() {
        return "CacheMemoryKey{" + "object=" + object + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CacheMemoryKey) {
            CacheMemoryKey other = (CacheMemoryKey) o;
            return object.equals(other.object);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return object.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(object.toString().getBytes(CHARSET));
    }
}
