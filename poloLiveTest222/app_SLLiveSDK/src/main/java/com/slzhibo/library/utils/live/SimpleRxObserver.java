package com.slzhibo.library.utils.live;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.slzhibo.library.http.exception.ApiException;
import com.slzhibo.library.utils.AppUtils;
import io.reactivex.Observer;
//import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public abstract class SimpleRxObserver<T> implements Observer<T> {
    private WeakReference<Context> context;
    private boolean isToastErrorMsg;



    public abstract void accept(T t);

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    public void onError(int i, String str) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(@NonNull Disposable disposable) {
    }

    public SimpleRxObserver() {
        this.isToastErrorMsg = true;
    }

    public SimpleRxObserver(Context context) {
        this(context, true);
    }



    public SimpleRxObserver(Context context, boolean z) {
        this.isToastErrorMsg = true;
        this.context = new WeakReference<>(context);
        this.isToastErrorMsg = z;
    }

    public SimpleRxObserver(boolean z) {
        this.isToastErrorMsg = true;
        this.isToastErrorMsg = z;
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        Context context;
        th.printStackTrace();
        if (th instanceof ApiException) {
            ApiException apiException = (ApiException) th;
            int code = apiException.getCode();
            onError(code, apiException.getMsg());
            if (AppUtils.isTokenInvalidErrorCode(code)) {
                WeakReference<Context> weakReference = this.context;
                if (weakReference != null && weakReference.get() != null && (context = this.context.get()) != null && (context instanceof FragmentActivity)) {
                    AppUtils.handlerTokenInvalid(context);
                }
            } else if (this.isToastErrorMsg && AppUtils.isToastAPIRequestErrorMsg(code, apiException.getMsg())) {
                ToastUtils.showShort(apiException.getMsg());
            }
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(@NonNull T t) {
        accept(t);
    }
}

