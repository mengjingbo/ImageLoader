package com.imageloader.module.mode;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public enum ScaleType {
    /**
     * 以完整显示图片为目标, 不剪裁 ,当显示不下的时候将缩放,能够显示的情况下不缩放
     */
    CENTER_INSIDE(0),
    /**
     * 以填满整个控件为目标,等比缩放,超过控件时将被 裁剪 ( 宽高都要填满 ,所以只要图片宽高比与控件宽高比不同时,一定会被剪裁)
     */
    CENTER_CROP(1),
    /**
     * 自适应控件, 不剪裁 ,在不超过控件的前提下,等比 缩放 到 最大 ,居中显示
     */
    FIT_CENTER(2),
    /**
     * 圆形裁剪
     */
    CIRCLE_CROP(3);

    public int type;

    ScaleType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
