package com.slzhibo.library.http.function;



import androidx.annotation.NonNull;

import com.slzhibo.library.http.exception.ExceptionEngine;
import io.reactivex.Observable;
//import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/* loaded from: classes6.dex */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    public Observable<T> apply(@NonNull Throwable th) {
        return Observable.error(ExceptionEngine.handleException(th));
    }
}