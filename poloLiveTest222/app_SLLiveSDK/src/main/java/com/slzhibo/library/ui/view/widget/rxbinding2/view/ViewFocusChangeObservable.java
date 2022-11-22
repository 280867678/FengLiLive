package com.slzhibo.library.ui.view.widget.rxbinding2.view;

import android.view.View;

import com.slzhibo.library.ui.view.widget.rxbinding2.InitialValueObservable;

import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.view.ViewFocusChangeObservable */
/* loaded from: classes6.dex */
final class ViewFocusChangeObservable extends InitialValueObservable<Boolean> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewFocusChangeObservable(View view) {
        this.view = view;
    }

    @Override // com.slzhibo.library.p115ui.view.widget.rxbinding2.InitialValueObservable
    protected void subscribeListener(Observer<? super Boolean> observer) {
        Listener listener = new Listener(this.view, observer);
        observer.onSubscribe(listener);
        this.view.setOnFocusChangeListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.slzhibo.library.p115ui.view.widget.rxbinding2.InitialValueObservable
    public Boolean getInitialValue() {
        return Boolean.valueOf(this.view.hasFocus());
    }

    /* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.view.ViewFocusChangeObservable$Listener */
    /* loaded from: classes6.dex */
    static final class Listener extends MainThreadDisposable implements View.OnFocusChangeListener {
        private final Observer<? super Boolean> observer;
        private final View view;

        Listener(View view, Observer<? super Boolean> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (!isDisposed()) {
                this.observer.onNext(Boolean.valueOf(z));
            }
        }

        @Override // io.reactivex.android.MainThreadDisposable
        protected void onDispose() {
            this.view.setOnFocusChangeListener(null);
        }
    }
}
