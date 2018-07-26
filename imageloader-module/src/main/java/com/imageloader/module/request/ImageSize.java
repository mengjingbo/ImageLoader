package com.imageloader.module.request;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public class ImageSize {

    public final int width;
    public final int height;

    public ImageSize(int size) {
        this.width = size;
        this.height = size;
    }

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
