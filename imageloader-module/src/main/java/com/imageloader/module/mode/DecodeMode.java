package com.imageloader.module.mode;

import com.bumptech.glide.load.DecodeFormat;

/**
 * date        ：2018/7/23
 * author      ：蒙景博
 * description ：解码格式
 */
public enum DecodeMode {

    DEFAULT(DecodeFormat.DEFAULT),
    PREFER_RGB_565(DecodeFormat.PREFER_RGB_565),
    PREFER_ARGB_8888(DecodeFormat.PREFER_ARGB_8888);

    private DecodeFormat mode;

    DecodeMode(DecodeFormat mode) {
        this.mode = mode;
    }

    public DecodeFormat getMode() {
        return mode;
    }
}
