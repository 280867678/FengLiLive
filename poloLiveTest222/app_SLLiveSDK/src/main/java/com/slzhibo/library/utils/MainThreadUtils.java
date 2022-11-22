package com.slzhibo.library.utils;



import android.annotation.SuppressLint;
import com.slzhibo.library.utils.MainThreadUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/* loaded from: classes12.dex */
public class MainThreadUtils {

    /* loaded from: classes12.dex */
    public interface Action {
        void action();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class SingletonHolder {
        private static final MainThreadUtils INSTANCE = new MainThreadUtils();

        private SingletonHolder() {
        }
    }

    public static MainThreadUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @SuppressLint({"CheckResult"})
    public void executeOnMainThread(final Action action) {
        Observable.just(true).observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.slzhibo.library.utils.-$$Lambda$MainThreadUtils$Y9CWo0-dVt5-bQstu2J-IidbUCw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
//                MainThreadUtils.lambda$executeOnMainThread$0(MainThreadUtils.Action.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$executeOnMainThread$0(Action action, Boolean bool) throws Exception {
        if (action != null) {
            action.action();
        }
    }
}
