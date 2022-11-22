package com.slzhibo.library.ui.view.widget.rxbinding2.view;

import android.view.View;
import android.view.ViewTreeObserver;

import com.slzhibo.library.ui.view.widget.rxbinding2.internal.Notification;
import com.slzhibo.library.ui.view.widget.rxbinding2.internal.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.view.ViewTreeObserverGlobalLayoutObservable */
/* loaded from: classes6.dex */
final class ViewTreeObserverGlobalLayoutObservable extends Observable<Object> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverGlobalLayoutObservable(View view) {
        this.view = view;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.getViewTreeObserver().addOnGlobalLayoutListener(listener);
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.view.ViewTreeObserverGlobalLayoutObservable$Listener */
    /* loaded from: classes6.dex */
    static final class Listener extends MainThreadDisposable implements ViewTreeObserver.OnGlobalLayoutListener {
        private final Observer<? super Object> observer;
        private final View view;

        Listener(View view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        @Override // io.reactivex.android.MainThreadDisposable
        protected void onDispose() {
            this.view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }
}
