package com.slzhibo.library.ui.interfaces;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
//import android.support.annotation.Nullable;

public interface OnGlideDownloadCallback<T> {
    void onLoadFailed(@Nullable Drawable drawable);

    void onLoadStarted(@Nullable Drawable drawable);

    void onLoadSuccess(T t);
}
