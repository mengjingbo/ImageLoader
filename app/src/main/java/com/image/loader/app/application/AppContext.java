package com.image.loader.app.application;

import android.app.Application;

import com.imageloader.module.ImageLoader;
import com.imageloader.module.loaders.GlideLoader;

/**
 * date        ：2018/7/24
 * author      ：蒙景博
 * description ：
 */
public class AppContext extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化ImageLoader
        ImageLoader.getDefault().init(new GlideLoader());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getDefault().trimMemory(getApplicationContext(), level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getDefault().clearMemory(getApplicationContext());
    }
}
