package com.imageloader.module.request;

import android.support.annotation.Nullable;

/**
 * date        ：2018/7/23
 * author      ：蒙景博
 * description ：
 */
public interface OnRequestCallback {

    boolean onSuccess(Object resource, Object model, boolean isFirstResource);

    boolean onFailed(@Nullable Exception e, Object model, boolean isFirstResource);
}
