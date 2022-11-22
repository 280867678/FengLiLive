package com.slzhibo.library.ui.view.widget.rxbinding2.view;



import android.annotation.SuppressLint;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.slzhibo.library.ui.view.widget.rxbinding2.InitialValueObservable;
import com.slzhibo.library.ui.view.widget.rxbinding2.internal.Functions;
import com.slzhibo.library.ui.view.widget.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;

/* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.view.RxView */
/* loaded from: classes6.dex */
public final class RxView {
    @NonNull
    @CheckResult
    public static Observable<Object> clicks(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewClickObservable(view);
    }

    @NonNull
    @CheckResult
    public static Observable<DragEvent> drags(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewDragObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    @NonNull
    @CheckResult
    public static Observable<DragEvent> drags(@NonNull View view, @NonNull Predicate<? super DragEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewDragObservable(view, predicate);
    }

    @NonNull
    @CheckResult
    @RequiresApi(16)
    public static Observable<Object> draws(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTreeObserverDrawObservable(view);
    }

    @NonNull
    @CheckResult
    public static InitialValueObservable<Boolean> focusChanges(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewFocusChangeObservable(view);
    }

    @NonNull
    @CheckResult
    public static Observable<Object> globalLayouts(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTreeObserverGlobalLayoutObservable(view);
    }

    @NonNull
    @CheckResult
    public static Observable<MotionEvent> hovers(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewHoverObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    @NonNull
    @CheckResult
    public static Observable<MotionEvent> hovers(@NonNull View view, @NonNull Predicate<? super MotionEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewHoverObservable(view, predicate);
    }

    @NonNull
    @CheckResult
    public static Observable<Object> longClicks(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewLongClickObservable(view, Functions.CALLABLE_ALWAYS_TRUE);
    }

    @NonNull
    @CheckResult
    public static Observable<Object> longClicks(@NonNull View view, @NonNull Callable<Boolean> callable) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(callable, "handled == null");
        return new ViewLongClickObservable(view, callable);
    }

    @NonNull
    @CheckResult
    public static Observable<Object> preDraws(@NonNull View view, @NonNull Callable<Boolean> callable) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(callable, "proceedDrawingPass == null");
        return new ViewTreeObserverPreDrawObservable(view, callable);
    }

    @NonNull
    @CheckResult
    public static Observable<Integer> systemUiVisibilityChanges(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewSystemUiVisibilityChangeObservable(view);
    }

    @NonNull
    @CheckResult
    public static Observable<MotionEvent> touches(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTouchObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    @NonNull
    @CheckResult
    public static Observable<MotionEvent> touches(@NonNull View view, @NonNull Predicate<? super MotionEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewTouchObservable(view, predicate);
    }

    @NonNull
    @CheckResult
    public static Observable<KeyEvent> keys(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewKeyObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    @NonNull
    @CheckResult
    public static Observable<KeyEvent> keys(@NonNull View view, @NonNull Predicate<? super KeyEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewKeyObservable(view, predicate);
    }

    @NonNull
    @CheckResult
    @Deprecated
    public static Consumer<? super Boolean> activated(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$NtkGcUoes2eULElo9uCOeuJRkmY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                view.setActivated(((Boolean) obj).booleanValue());
            }
        };
    }

    @NonNull
    @CheckResult
    @Deprecated
    public static Consumer<? super Boolean> clickable(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$uaXr2Liy6tHu8F4PUEq82PN4yuU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                view.setClickable(((Boolean) obj).booleanValue());
            }
        };
    }

    @NonNull
    @CheckResult
    @Deprecated
    public static Consumer<? super Boolean> enabled(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$7sli7apx3NyCWzGvWKC5phvd_II
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                view.setEnabled(((Boolean) obj).booleanValue());
            }
        };
    }

    @NonNull
    @CheckResult
    @Deprecated
    public static Consumer<? super Boolean> pressed(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$pfx1HM5rr67c0a1FOZ-Gn_GvG1U
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                view.setPressed(((Boolean) obj).booleanValue());
            }
        };
    }

    @NonNull
    @CheckResult
    @Deprecated
    public static Consumer<? super Boolean> selected(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$TOfBQzwhTy9B8KBrE9uATKdzdy4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                view.setSelected(((Boolean) obj).booleanValue());
            }
        };
    }

    @NonNull
    @CheckResult
    public static Consumer<? super Boolean> visibility(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return visibility(view, 8);
    }

    @NonNull
    @CheckResult
    public static Consumer<? super Boolean> visibility(@NonNull final View view, final int i) {
        Preconditions.checkNotNull(view, "view == null");
        if (i == 0) {
            throw new IllegalArgumentException("Setting visibility to VISIBLE when false would have no effect.");
        } else if (i == 4 || i == 8) {
            return new Consumer() { // from class: com.slzhibo.library.ui.view.widget.rxbinding2.view.-$$Lambda$RxView$RLAMmPdn0wqDO2CmlQHpH9Fj5-Q
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) throws Exception {
                    RxView.lambda$visibility$0(view, i, (Boolean) obj);
                }
            };
        } else {
            throw new IllegalArgumentException("Must set visibility to INVISIBLE or GONE when false.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint("WrongConstant")
    public static /* synthetic */ void lambda$visibility$0(@NonNull View view, int i, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}

