package com.imageloader.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.imageloader.module.loaders.IImageLoader;
import com.imageloader.module.request.ImageOption;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public class ImageLoader {

    private volatile static ImageLoader INTEREST;
    private IImageLoader mILoader;

    private ImageLoader(){

    }

    public static ImageLoader getDefault(){
        if (INTEREST == null){
            synchronized (ImageLoader.class){
                if (INTEREST == null){
                    INTEREST = new ImageLoader();
                }
            }
        }
        return INTEREST;
    }

    public void init(@NonNull IImageLoader loader){
        this.mILoader = loader;
    }

    public ImageOption.Builder with(Context context){
        if (isInitLoader()) throw new NullPointerException("ImageLoader not initialized !");
        return getBuilder().setContext(context).setImageLoader(getLoader());
    }

    private boolean isInitLoader(){
        return mILoader == null;
    }

    public ImageOption getImageOption() {
        return getBuilder().build();
    }

    public ImageOption.Builder getBuilder() {
        return new ImageOption.Builder();
    }

    private IImageLoader getLoader() {
        return mILoader;
    }

    public void pauseRequests(Context context) {
        mILoader.pauseRequests(context);
    }

    public void pauseAllRequests(Context context) {
        mILoader.pauseAllRequests(context);
    }

    public void resumeRequests(@NonNull Context context) {
        mILoader.resumeRequests(context);
    }

    public void clearDiskCache(Context context) {
        mILoader.clearDiskCache(context);
    }

    public void clear(View view) {
        mILoader.clear(view);
    }

    public void clearMemory(Context context) {
        mILoader.clearMemory(context);
    }

    public void trimMemory(Context context, int level) {
        mILoader.trimMemory(context, level);
    }
}
