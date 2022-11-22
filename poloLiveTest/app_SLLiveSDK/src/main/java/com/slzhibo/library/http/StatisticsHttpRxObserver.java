package com.slzhibo.library.http;

import androidx.annotation.NonNull;

import com.slzhibo.library.http.exception.ApiException;

import io.reactivex.Observer;

import io.reactivex.disposables.Disposable;

/* loaded from: classes6.dex */
public class StatisticsHttpRxObserver<T> implements Observer<T> {
    private ResultCallBack callBack;

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(@NonNull Disposable disposable) {
    }

    public StatisticsHttpRxObserver(ResultCallBack<T> resultCallBack) {
        this.callBack = resultCallBack;
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        th.printStackTrace();
        try {
            ApiException apiException = (ApiException) th;
            if (this.callBack != null) {
                this.callBack.onError(apiException.getCode(), apiException.getMsg());
            }
        } catch (Exception unused) {
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(@NonNull T t) {
        ResultCallBack resultCallBack = this.callBack;
        if (resultCallBack != null) {
            resultCallBack.onSuccess(t);
        }
    }
}
