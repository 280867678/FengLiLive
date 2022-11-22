package com.slzhibo.library.utils;



import android.content.Context;
import android.view.View;
import com.slzhibo.library.ui.view.widget.rxbinding2.view.RxView;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.slzhibo.library.utils.rxlifecycle2.android.ActivityEvent;
import com.slzhibo.library.utils.rxlifecycle2.components.support.RxAppCompatActivity;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class RxViewUtils {

    /* loaded from: classes6.dex */
    public interface RxViewAction {
        void action(Object obj);
    }

    private RxViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final RxViewUtils INSTANCE = new RxViewUtils();

        private SingletonHolder() {
        }
    }

    public static RxViewUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void throttleFirst(View view, int i, RxViewAction rxViewAction) {
        throttleFirst(view, i, TimeUnit.MILLISECONDS, rxViewAction);
    }

    public void throttleFirst(View view, long j, TimeUnit timeUnit, final RxViewAction rxViewAction) {
        if (view != null) {
            Context context = view.getContext();
            if (context instanceof RxAppCompatActivity) {
                RxView.clicks(view).throttleFirst(j, timeUnit).compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new SimpleRxObserver<Object>() { // from class: com.slzhibo.library.utils.RxViewUtils.1
                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void accept(Object obj) {
                        RxViewAction rxViewAction2 = rxViewAction;
                        if (rxViewAction2 != null) {
                            rxViewAction2.action(obj);
                        }
                    }
                });
            } else {
                RxView.clicks(view).throttleFirst(j, timeUnit).subscribe(new SimpleRxObserver<Object>() { // from class: com.slzhibo.library.utils.RxViewUtils.2
                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void accept(Object obj) {
                        RxViewAction rxViewAction2 = rxViewAction;
                        if (rxViewAction2 != null) {
                            rxViewAction2.action(obj);
                        }
                    }
                });
            }
        }
    }
}

