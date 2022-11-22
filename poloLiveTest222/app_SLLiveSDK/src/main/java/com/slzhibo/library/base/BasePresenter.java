package com.slzhibo.library.base;



import android.content.Context;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.ApiService;
import com.slzhibo.library.http.HttpRxObservable;
import com.slzhibo.library.ui.view.dialog.LoadingDialog;
import com.slzhibo.library.ui.view.dialog.LoadingDialog;
import com.slzhibo.library.utils.MainThreadUtils;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class BasePresenter<V> {

    protected CompositeDisposable baseCompositeDisposable;
    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    protected WeakReference<Context> mContextRef;
    protected WeakReference<V> mViewRef;

    public BasePresenter(Context context, V v) {
        if (this.baseCompositeDisposable == null) {
            this.baseCompositeDisposable = new CompositeDisposable();
        }
        attachView(context, v);
    }

    public void attachView(Context context, V v) {
        this.mContextRef = new WeakReference<>(context);
        this.mViewRef = new WeakReference<>(v);
    }

    public void detachView() {
        clearBaseCompositeDisposable();
        WeakReference<Context> weakReference = this.mContextRef;
        if (weakReference != null) {
            weakReference.clear();
            this.mContextRef = null;
        }
        WeakReference<V> weakReference2 = this.mViewRef;
        if (weakReference2 != null) {
            weakReference2.clear();
            this.mViewRef = null;
        }
    }

    public boolean isAttached() {
        WeakReference<V> weakReference = this.mViewRef;
        return weakReference != null && weakReference.get() != null;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        WeakReference<Context> weakReference = this.mContextRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public V getView() {
        WeakReference<V> weakReference = this.mViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isViewNull() {
        return getView() == null;
    }

    public LifecycleProvider getLifecycleProvider() {
        V view = getView();
        if (view == null || !(view instanceof LifecycleProvider)) {
            return null;
        }
        return (LifecycleProvider)view;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }

    public void addMapSubscription(Observable observable, Observer observer) {
        if (isAttached()) {
            HttpRxObservable.getObservable(observable, getLifecycleProvider()).subscribe(observer);
        }
    }

    public void clearBaseCompositeDisposable() {
        if (this.baseCompositeDisposable == null) {
            this.baseCompositeDisposable = new CompositeDisposable();
        }
        this.baseCompositeDisposable.clear();
    }

    public void baseCompositeDisposableAdd(Disposable disposable) {
        if (this.baseCompositeDisposable == null) {
            this.baseCompositeDisposable = new CompositeDisposable();
        }
        if (disposable != null) {
            this.baseCompositeDisposable.add(disposable);
        }
    }


//    protected CompositeDisposable baseCompositeDisposable;
//    private LoadingDialog loadingDialog = null;
//    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
//    protected WeakReference<Context> mContextRef;
//    protected WeakReference<V> mViewRef;
//
//    public BasePresenter(Context context, V v) {
//        if (this.baseCompositeDisposable == null) {
//            this.baseCompositeDisposable = new CompositeDisposable();
//        }
//        attachView(context, v);
//    }
//
//    public void attachView(Context context, V v) {
//        this.mContextRef = new WeakReference<>(context);
//        this.mViewRef = new WeakReference<>(v);
//    }
//
//    public void detachView() {
//        clearBaseCompositeDisposable();
//        WeakReference<Context> weakReference = this.mContextRef;
//        if (weakReference != null) {
//            weakReference.clear();
//        }
//        WeakReference<V> weakReference2 = this.mViewRef;
//        if (weakReference2 != null) {
//            weakReference2.clear();
//        }
//    }
//
//    public boolean isAttached() {
//        WeakReference<V> weakReference = this.mViewRef;
//        return (weakReference == null || weakReference.get() == null) ? false : true;
//    }
//
//    /* JADX INFO: Access modifiers changed from: protected */
//    public Context getContext() {
//        WeakReference<Context> weakReference = this.mContextRef;
//        if (weakReference != null) {
//            return weakReference.get();
//        }
//        return null;
//    }
//
//    /* JADX INFO: Access modifiers changed from: protected */
//    public V getView() {
//        WeakReference<V> weakReference = this.mViewRef;
//        if (weakReference != null) {
//            return weakReference.get();
//        }
//        return null;
//    }
//
//    /* JADX INFO: Access modifiers changed from: protected */
//    public boolean isViewNull() {
//        return getView() == null;
//    }
//
//    public LifecycleProvider getLifecycleProvider() {
//        V view = getView();
//        if (view == null || !(view instanceof LifecycleProvider)) {
//            return null;
//        }
//        return (LifecycleProvider) view;
//    }
//
//    public boolean isApiService() {
//        return this.mApiService != null;
//    }
//
//    public void addMapSubscription(Observable observable, Observer observer) {
//        if (isAttached()) {
//            HttpRxObservable.getObservable(observable, getLifecycleProvider()).subscribe(observer);
//        }
//    }
//
//    public void addMapSubscription(Observable observable, Observer observer, int i, int i2) {
//        if (isAttached()) {
//            HttpRxObservable.getObservable(observable, getLifecycleProvider(), i, i2).subscribe(observer);
//        }
//    }
//
//    public void clearBaseCompositeDisposable() {
//        if (this.baseCompositeDisposable == null) {
//            this.baseCompositeDisposable = new CompositeDisposable();
//        }
//        this.baseCompositeDisposable.clear();
//    }
//
//    public void baseCompositeDisposableAdd(Disposable disposable) {
//        if (this.baseCompositeDisposable == null) {
//            this.baseCompositeDisposable = new CompositeDisposable();
//        }
//        if (disposable != null) {
//            this.baseCompositeDisposable.add(disposable);
//        }
//    }
//
//    public void showLoadingDialog() {
//        MainThreadUtils.getInstance().executeOnMainThread(new MainThreadUtils.Action() { // from class: com.slzhibo.library.base.-$$Lambda$BasePresenter$UnGnXJBqcZnCFSW2Lgg1i3xTaMY
//            @Override // com.slzhibo.library.utils.MainThreadUtils.Action
//            public final void action() {
//                BasePresenter.this.lambda$showLoadingDialog$0$BasePresenter();
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$showLoadingDialog$0$BasePresenter() {
//        if (this.loadingDialog == null) {
//            this.loadingDialog = new LoadingDialog(getContext());
//        }
//        LoadingDialog loadingDialog = this.loadingDialog;
//        if (loadingDialog != null && !loadingDialog.isShowing()) {
//            this.loadingDialog.show();
//        }
//    }
//
//    public void dismissLoadingDialog() {
//        MainThreadUtils.getInstance().executeOnMainThread(new MainThreadUtils.Action() { // from class: com.slzhibo.library.base.-$$Lambda$BasePresenter$NWJMJdzGUJF-1Zbmfp9Z4OuWEq0
//            @Override // com.slzhibo.library.utils.MainThreadUtils.Action
//            public final void action() {
//                BasePresenter.this.lambda$dismissLoadingDialog$1$BasePresenter();
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$dismissLoadingDialog$1$BasePresenter() {
//        try {
//            if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
//                this.loadingDialog.dismiss();
//            }
//        } catch (Exception unused) {
//        }
//    }
}
