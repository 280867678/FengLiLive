package com.ibase.glide.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;


public final class GlideApp {
    @NonNull
    /* renamed from: a */
    public static GlideRequests m4954a(@NonNull Context context) {
        return (GlideRequests) Glide.with(context);
    }
}