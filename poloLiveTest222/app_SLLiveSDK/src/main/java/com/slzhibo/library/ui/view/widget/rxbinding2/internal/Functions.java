package com.slzhibo.library.ui.view.widget.rxbinding2.internal;



import androidx.annotation.RestrictTo;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.internal.Functions */
/* loaded from: classes6.dex */
public final class Functions {
    private static final Always ALWAYS_TRUE = new Always(true);
    public static final Callable<Boolean> CALLABLE_ALWAYS_TRUE;
    public static final Predicate<Object> PREDICATE_ALWAYS_TRUE;

    static {
        Always always = ALWAYS_TRUE;
        CALLABLE_ALWAYS_TRUE = always;
        PREDICATE_ALWAYS_TRUE = always;
    }

    /* renamed from: com.slzhibo.library.ui.view.widget.rxbinding2.internal.Functions$Always */
    /* loaded from: classes6.dex */
    private static final class Always implements Callable<Boolean>, Predicate<Object> {
        private final Boolean value;

        Always(Boolean bool) {
            this.value = bool;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            return this.value;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) {
            return this.value.booleanValue();
        }
    }

    private Functions() {
        throw new AssertionError("No instances.");
    }
}

