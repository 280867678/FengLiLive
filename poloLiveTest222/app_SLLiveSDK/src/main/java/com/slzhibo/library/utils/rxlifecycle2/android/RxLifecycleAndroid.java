package com.slzhibo.library.utils.rxlifecycle2.android;



import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
import com.slzhibo.library.utils.rxlifecycle2.OutsideLifecycleException;
import com.slzhibo.library.utils.rxlifecycle2.RxLifecycle;
import com.slzhibo.library.utils.rxlifecycle2.internal.Preconditions;
//import com.trello.rxlifecycle2.OutsideLifecycleException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/* loaded from: classes12.dex */
public class RxLifecycleAndroid {
    private static final Function<ActivityEvent, ActivityEvent> ACTIVITY_LIFECYCLE = new Function<ActivityEvent, ActivityEvent>() { // from class: com.slzhibo.library.utils.rxlifecycle2.android.RxLifecycleAndroid.1
        public ActivityEvent apply(ActivityEvent activityEvent) throws Exception {
            switch (C82813.f3228x99cd0954[activityEvent.ordinal()]) {
                case 1:
                    return ActivityEvent.DESTROY;
                case 2:
                    return ActivityEvent.STOP;
                case 3:
                    return ActivityEvent.PAUSE;
                case 4:
                    return ActivityEvent.STOP;
                case 5:
                    return ActivityEvent.DESTROY;
                case 6:
                    throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + activityEvent + " not yet implemented");
            }
        }
    };
    private static final Function<FragmentEvent, FragmentEvent> FRAGMENT_LIFECYCLE = new Function<FragmentEvent, FragmentEvent>() { // from class: com.slzhibo.library.utils.rxlifecycle2.android.RxLifecycleAndroid.2
        public FragmentEvent apply(FragmentEvent fragmentEvent) throws Exception {
            switch (C82813.f3229xa45e7ab3[fragmentEvent.ordinal()]) {
                case 1:
                    return FragmentEvent.DETACH;
                case 2:
                    return FragmentEvent.DESTROY;
                case 3:
                    return FragmentEvent.DESTROY_VIEW;
                case 4:
                    return FragmentEvent.STOP;
                case 5:
                    return FragmentEvent.PAUSE;
                case 6:
                    return FragmentEvent.STOP;
                case 7:
                    return FragmentEvent.DESTROY_VIEW;
                case 8:
                    return FragmentEvent.DESTROY;
                case 9:
                    return FragmentEvent.DETACH;
                case 10:
                    throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + fragmentEvent + " not yet implemented");
            }
        }
    };

    private RxLifecycleAndroid() {
        throw new AssertionError("No instances");
    }

    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindActivity(@NonNull Observable<ActivityEvent> observable) {
        return RxLifecycle.bind(observable, ACTIVITY_LIFECYCLE);
    }

    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindFragment(@NonNull Observable<FragmentEvent> observable) {
        return RxLifecycle.bind(observable, FRAGMENT_LIFECYCLE);
    }

    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindView(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return RxLifecycle.bind(Observable.create(new ViewDetachesOnSubscribe(view)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.slzhibo.library.utils.rxlifecycle2.android.RxLifecycleAndroid$3 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class C82813 {

        /* renamed from: $SwitchMap$com$slzhibo$library$utils$rxlifecycle2$android$ActivityEvent */
        static final /* synthetic */ int[] f3228x99cd0954;

        /* renamed from: $SwitchMap$com$slzhibo$library$utils$rxlifecycle2$android$FragmentEvent */
        static final /* synthetic */ int[] f3229xa45e7ab3 = new int[FragmentEvent.values().length];

        static {
            try {
                f3229xa45e7ab3[FragmentEvent.ATTACH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.CREATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.CREATE_VIEW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.START.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.PAUSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.STOP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.DESTROY_VIEW.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.DESTROY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3229xa45e7ab3[FragmentEvent.DETACH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            f3228x99cd0954 = new int[ActivityEvent.values().length];
            try {
                f3228x99cd0954[ActivityEvent.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3228x99cd0954[ActivityEvent.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3228x99cd0954[ActivityEvent.RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3228x99cd0954[ActivityEvent.PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3228x99cd0954[ActivityEvent.STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f3228x99cd0954[ActivityEvent.DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }
}
