package com.slzhibo.library.ui.fragment;



import android.content.Context;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.LiveAdEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.ui.view.iview.IHomeAllView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.GsonUtils;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/* renamed from: e.t.a.h.e.a0 */
/* loaded from: classes2.dex */
public class HomeAllPresenter extends BasePresenter<IHomeAllView> {
    public final int bannerSpanPosition = 4;

    public HomeAllPresenter(Context context, IHomeAllView iHomeAllView) {
        super(context, iHomeAllView);
    }

    public void getLiveList(StateView stateView, int i, boolean z, final boolean z2, LifecycleTransformer lifecycleTransformer) {
        if (isApiService()) {
            getLiveObservable(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.1
                public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                    if (!HomeAllPresenter.this.isViewNull() && httpResultPageModel != null) {
                        ((IHomeAllView) HomeAllPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, false, httpResultPageModel.isMorePage(), z2);
                    }
                }

                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i2, String str) {
                    if (!HomeAllPresenter.this.isViewNull()) {
                        ((IHomeAllView) HomeAllPresenter.this.getView()).onDataListFail(z2);
                    }
                }
            }, stateView, z, false));
        }
    }

    public void getLiveListFirst(final StateView stateView, final int i, final boolean z, final boolean z2, final LifecycleTransformer lifecycleTransformer) {
        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            initLiveListFirst(stateView, i, z, z2, lifecycleTransformer);
        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            SLLiveSDK.getSingleton().sdkCallbackListener.onAdvChannelListener(getContext(), "5", new SLLiveSDK.OnAdvChannelCallbackListener() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.2
                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataSuccess(Context context, String str) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            HomeAllPresenter.this.initLiveListFirst(stateView, i, z, z2, lifecycleTransformer);
                            return;
                        }
                        List list = (List) GsonUtils.fromJson(str, new TypeToken<List<BannerEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.2.1
                        }.getType());
                        if (list != null && !list.isEmpty()) {
                            if (HomeAllPresenter.this.isApiService()) {
                                HomeAllPresenter.this.initObservableZip(stateView, z, z2, HomeAllPresenter.this.getLiveObservable(i), Observable.just(list), lifecycleTransformer);
                                return;
                            }
                            return;
                        }
                        HomeAllPresenter.this.initLiveListFirst(stateView, i, z, z2, lifecycleTransformer);
                    } catch (Exception unused) {
                        HomeAllPresenter.this.initLiveListFirst(stateView, i, z, z2, lifecycleTransformer);
                    }
                }

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataFail(int i2, String str) {
                    HomeAllPresenter.this.initLiveListFirst(stateView, i, z, z2, lifecycleTransformer);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLiveListFirst(StateView stateView, int i, boolean z, boolean z2, LifecycleTransformer lifecycleTransformer) {
        Observable<List<BannerEntity>> observable;
        if (isApiService()) {
            Observable<HttpResultPageModel<LiveEntity>> liveObservable = getLiveObservable(i);
            if (CacheUtils.isBannerListByCacheDisk("5")) {
                observable = Observable.just(CacheUtils.getBannerListByCacheDisk("5"));
            } else {
                observable = this.mApiService.getBannerListService(new RequestParams().getBannerListParams("5")).map(new ServerResultFunction<List<BannerEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.3
                }).onErrorResumeNext(new HttpResultFunction());
            }
            initObservableZip(stateView, z, z2, liveObservable, observable, lifecycleTransformer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initObservableZip(StateView stateView, boolean z, final boolean z2, Observable<HttpResultPageModel<LiveEntity>> observable, Observable<List<BannerEntity>> observable2, LifecycleTransformer lifecycleTransformer) {
        Observable.zip(observable, observable2, new BiFunction() { // from class: com.slzhibo.library.ui.presenter.-$$Lambda$HomeAllPresenter$tsova41xSl5ShKtOj2QfShG2-A4
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) throws Exception {
                return HomeAllPresenter.this.lambda$initObservableZip$0$HomeAllPresenter((HttpResultPageModel) obj, (List) obj2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.4
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (!HomeAllPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeAllView) HomeAllPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isHasBanner, httpResultPageModel.isMorePage(), z2);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                if (!HomeAllPresenter.this.isViewNull()) {
                    ((IHomeAllView) HomeAllPresenter.this.getView()).onDataListFail(z2);
                }
            }
        }, stateView, z, false));
    }

    public /* synthetic */ HttpResultPageModel lambda$initObservableZip$0$HomeAllPresenter(HttpResultPageModel httpResultPageModel, List list) throws Exception {
        if (list == null || list.isEmpty()) {
            httpResultPageModel.isHasBanner = false;
            return httpResultPageModel;
        }
        httpResultPageModel.isHasBanner = false;
        List<LiveEntity> list2 = httpResultPageModel.dataList;
        if (list2.size() != 0 && list2.size() == 3) {
            LiveEntity liveEntity = new LiveEntity();
            liveEntity.itemType = 4;
            list2.add(liveEntity);
        }
        if (list2.size() != 0 && list2.size() >= 4) {
            httpResultPageModel.isHasBanner = true;
            LiveEntity liveEntity2 = new LiveEntity();
            liveEntity2.itemType = 2;
            liveEntity2.bannerList = AppUtils.getImgBannerItem(list);
            list2.add(4, liveEntity2);
            CacheUtils.saveBannerListByCacheDisk("5", liveEntity2.bannerList);
        }
        return httpResultPageModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<HttpResultPageModel<LiveEntity>> getLiveObservable(int i) {
        return this.mApiService.getV03AllListService(new RequestParams().getAdvChannelPageListParams(i)).map(new ServerResultFunction<HttpResultPageModel<LiveAdEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAllPresenter.5
        }).flatMap($$Lambda$HomeAllPresenter$Jlh7Z1fikrHSgEg5x9QoFkMQm8w.INSTANCE).onErrorResumeNext(new HttpResultFunction());
    }
}







final /* synthetic */ class $$Lambda$HomeAllPresenter$Jlh7Z1fikrHSgEg5x9QoFkMQm8w implements Function {
    public static final /* synthetic */ $$Lambda$HomeAllPresenter$Jlh7Z1fikrHSgEg5x9QoFkMQm8w INSTANCE = new $$Lambda$HomeAllPresenter$Jlh7Z1fikrHSgEg5x9QoFkMQm8w();

    private /* synthetic */ $$Lambda$HomeAllPresenter$Jlh7Z1fikrHSgEg5x9QoFkMQm8w() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ObservableSource just;
        just = Observable.just(AppUtils.formatHttpResultPageModel((HttpResultPageModel) obj));
        return just;
    }
}
