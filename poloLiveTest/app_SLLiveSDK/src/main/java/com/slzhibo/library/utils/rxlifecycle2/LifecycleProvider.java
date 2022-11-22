package com.slzhibo.library.utils.rxlifecycle2;



import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;


/* loaded from: classes.dex */
public interface LifecycleProvider<E> {
    @CheckReturnValue
    @NonNull
    <T> LifecycleTransformer<T> bindToLifecycle();

    @CheckReturnValue
    @NonNull
    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull E e);

    @CheckReturnValue
    @NonNull
    Observable<E> lifecycle();
}