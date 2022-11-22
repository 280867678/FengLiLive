package com.slzhibo.library.ui.view.widget.rxbinding2;



import io.reactivex.Observable;
import io.reactivex.Observer;

/* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.InitialValueObservable */
/* loaded from: classes6.dex */
public abstract class InitialValueObservable<T> extends Observable<T> {
    protected abstract T getInitialValue();

    protected abstract void subscribeListener(Observer<? super T> observer);

    @Override // io.reactivex.Observable
    protected final void subscribeActual(Observer<? super T> observer) {
        subscribeListener(observer);
        observer.onNext(getInitialValue());
    }

    public final Observable<T> skipInitialValue() {
        return new Skipped();
    }

    /* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.InitialValueObservable$Skipped */
    /* loaded from: classes6.dex */
    private final class Skipped extends Observable<T> {
        Skipped() {
        }

        @Override // io.reactivex.Observable
        protected void subscribeActual(Observer<? super T> observer) {
            InitialValueObservable.this.subscribeListener(observer);
        }
    }
}

