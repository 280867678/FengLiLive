package com.slzhibo.library.http;



import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.http.utils.RetryWithDelayUtils;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleProvider;
import com.slzhibo.library.utils.rxlifecycle2.android.ActivityEvent;
import com.slzhibo.library.utils.rxlifecycle2.android.FragmentEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/* loaded from: classes6.dex */
public class HttpRxObservable {
    public static Observable getObservable(Observable observable) {
        return observable.map(new ServerResultFunction()).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable getObservable(Observable observable, int i, int i2) {
        return observable.map(new ServerResultFunction()).onErrorResumeNext(new HttpResultFunction()).retryWhen(new RetryWithDelayUtils(i, i2)).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable getObservable(Observable observable, LifecycleProvider lifecycleProvider) {
        if (lifecycleProvider != null) {
            return observable.map(new ServerResultFunction()).compose(lifecycleProvider.bindToLifecycle()).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        }
        return getObservable(observable);
    }

    public static Observable getObservable(Observable observable, LifecycleProvider lifecycleProvider, int i, int i2) {
        if (lifecycleProvider != null) {
            return observable.map(new ServerResultFunction()).compose(lifecycleProvider.bindToLifecycle()).onErrorResumeNext(new HttpResultFunction()).retryWhen(new RetryWithDelayUtils(i, i2)).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        }
        return getObservable(observable, i, i2);
    }

    public static Observable getObservable(Observable observable, LifecycleProvider<ActivityEvent> lifecycleProvider, ActivityEvent activityEvent) {
        if (lifecycleProvider != null) {
            return observable.map(new ServerResultFunction()).compose(lifecycleProvider.bindUntilEvent(activityEvent)).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        }
        return getObservable(observable);
    }

    public static Observable getObservable(Observable observable, LifecycleProvider<FragmentEvent> lifecycleProvider, FragmentEvent fragmentEvent) {
        if (lifecycleProvider != null) {
            return observable.map(new ServerResultFunction()).compose(lifecycleProvider.bindUntilEvent(fragmentEvent)).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        }
        return getObservable(observable);
    }
}
