package com.imageloader.module.mode;

import com.bumptech.glide.Priority;

/**
 * date        ：2018/7/20
 * author      ：蒙景博
 * description ：
 */
public enum PriorityMode {

    HIGH(Priority.HIGH),
    LOW(Priority.LOW),
    NORMAL(Priority.NORMAL),
    IMMEDIATE(Priority.IMMEDIATE);

    private Priority mode;

    PriorityMode(Priority mode) {
        this.mode = mode;
    }

    public Priority getMode() {
        return mode;
    }
}
