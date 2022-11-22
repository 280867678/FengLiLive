package com.slzhibo.library.utils.rxlifecycle2;



import androidx.annotation.NonNull;

import com.slzhibo.library.utils.rxlifecycle2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
//import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/* loaded from: classes12.dex */
public class RxLifecycle {
    private RxLifecycle() {
        throw new AssertionError("No instances");
    }

    @CheckReturnValue
    @NonNull
    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@NonNull Observable<R> observable, @NonNull R r) {
        Preconditions.checkNotNull(observable, "lifecycle == null");
        Preconditions.checkNotNull(r, "event == null");
        return bind(takeUntilEvent(observable, r));
    }

    private static <R> Observable<R> takeUntilEvent(Observable<R> observable, final R r) {
        return observable.filter(new Predicate<R>() { // from class: com.slzhibo.library.utils.rxlifecycle2.RxLifecycle.1
            @Override // io.reactivex.functions.Predicate
            public boolean test(R r2) throws Exception {
                return r2.equals(r);
            }
        });
    }

    @CheckReturnValue
    public static <T, R> LifecycleTransformer<T> bind(Observable<R> observable) {
        return new LifecycleTransformer<>(observable);
    }

    @CheckReturnValue
    public static <T, R> LifecycleTransformer<T> bind(Observable<R> observable, Function<R, R> function) {
        Preconditions.checkNotNull(observable, "lifecycle == null");
        Preconditions.checkNotNull(function, "correspondingEvents == null");
        return bind(takeUntilCorrespondingEvent(observable.share(), function));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <R> Observable<Boolean> takeUntilCorrespondingEvent(Observable<R> observable, Function<R, R> function) {
        return Observable.combineLatest(observable.take(1L).map(function), observable.skip(1L), new BiFunction<R, R, Boolean>() { // from class: com.slzhibo.library.utils.rxlifecycle2.RxLifecycle.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.BiFunction
            public Boolean apply(R r, R r2) throws Exception {
                return Boolean.valueOf(r2.equals(r));
            }
        }).onErrorReturn(Functions.RESUME_FUNCTION).filter(Functions.SHOULD_COMPLETE);
    }
}
