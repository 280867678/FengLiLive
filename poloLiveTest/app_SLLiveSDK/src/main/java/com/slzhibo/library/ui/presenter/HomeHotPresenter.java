package com.slzhibo.library.ui.presenter;



import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.IndexRankEntity;
import com.slzhibo.library.model.LiveAdEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveHelperAppConfigEntity;
import com.slzhibo.library.ui.view.iview.IHomeHotView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.GsonUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.google.gson.reflect.TypeToken;
/* renamed from: e.t.a.h.e.c0 */
/* loaded from: classes2.dex */
public class HomeHotPresenter extends BasePresenter<IHomeHotView> {
    public HomeHotPresenter(Context context, IHomeHotView iHomeHotView) {
        super(context, iHomeHotView);
    }

    public void getLiveList(StateView stateView, int i, boolean z, final boolean z2, LifecycleTransformer lifecycleTransformer) {
        Log.e("HomeHotPresenter：","getLiveList");
        this.mApiService.getV03RecommendListService(new RequestParams().getAdvChannelPageListParams(i)).map(new ServerResultFunction<HttpResultPageModel<LiveAdEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass2 */
        }).flatMap($$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg.INSTANCE).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass1 */

            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                Log.e("HomeHotPresenter：","getLiveList onSuccess");
                if (!HomeHotPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeHotPresenter：","getLiveList onError");
                if (!HomeHotPresenter.this.isViewNull()) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onDataListFail(z2);
                }
            }
        }, stateView, z, false));
    }

    public void onAnchorAuth() {
        Log.e("HomeHotPresenter：","onAnchorAuth");
        addMapSubscription(this.mApiService.getAnchorAuthService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass3 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeHotPresenter：","onAnchorAuth onError");
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                Log.e("HomeHotPresenter：","onAnchorAuth  onSuccess");
                if (!HomeHotPresenter.this.isViewNull() && anchorEntity != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onAnchorAuthSuccess(anchorEntity);
                }
            }
        }, true));
    }

    public void getBannerList(final String str) {
        Log.e("HomeHotPresenter：","getBannerList");
        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            initBannerList(str);
        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            SLLiveSDK.getSingleton().sdkCallbackListener.onAdvChannelListener(getContext(), str, new SLLiveSDK.OnAdvChannelCallbackListener() {
                /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass4 */

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataSuccess(Context context, String str) {
                    Log.e("HomeHotPresenter：","getBannerList  onAdvDataSuccess");
                    try {
                        if (TextUtils.isEmpty(str)) {
                            HomeHotPresenter.this.initBannerList(str);
                            return;
                        }
                        List<BannerEntity> list = (List) GsonUtils.fromJson(str, new TypeToken<List<BannerEntity>>() {
                            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass4.AnonymousClass1 */
                        }.getType());
                        if (list != null) {
                            if (!list.isEmpty()) {
                                if (!HomeHotPresenter.this.isViewNull()) {
                                    ((IHomeHotView) HomeHotPresenter.this.getView()).onBannerListSuccess(list);
                                    return;
                                }
                                return;
                            }
                        }
                        HomeHotPresenter.this.initBannerList(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        HomeHotPresenter.this.initBannerList(str);
                    }
                }

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataFail(int i, String str) {
                    Log.e("HomeHotPresenter：","getBannerList  onAdvDataFail");
                    HomeHotPresenter.this.initBannerList(str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initBannerList(final String str) {
        Log.e("HomeHotPresenter：","initBannerList");
        if (CacheUtils.isBannerListByCacheDisk(str)) {
            Observable.just(CacheUtils.getBannerListByCacheDisk(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<BannerEntity>>() {
                /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass5 */

                public void accept(List<BannerEntity> list) {
                    Log.e("HomeHotPresenter：","initBannerList  accept");
                    if (!HomeHotPresenter.this.isViewNull()) {
                        ((IHomeHotView) HomeHotPresenter.this.getView()).onBannerListSuccess(list);
                    }
                }
            });
        } else {
            addMapSubscription(this.mApiService.getBannerListService(new RequestParams().getBannerListParams(str)), new HttpRxObserver(getContext(), (ResultCallBack) new ResultCallBack<List<BannerEntity>>() {
                /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass6 */

                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i, String str) {
                    Log.e("HomeHotPresenter：","initBannerList  onError");
                }

                public void onSuccess(List<BannerEntity> list) {
                    Log.e("HomeHotPresenter：","initBannerList  onSuccess");
                    CacheUtils.saveBannerListByCacheDisk(str, list);
                    if (!HomeHotPresenter.this.isViewNull()) {
                        ((IHomeHotView) HomeHotPresenter.this.getView()).onBannerListSuccess(list);
                    }
                }
            }, false, false));
        }
    }

    public void getTopList() {
        Log.e("HomeHotPresenter：","getTopList");
        addMapSubscription(this.mApiService.getIndexRankService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), (ResultCallBack) new ResultCallBack<List<IndexRankEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass7 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeHotPresenter：","getTopList  onError");
            }

            public void onSuccess(List<IndexRankEntity> list) {
                Log.e("HomeHotPresenter：","getTopList  onSuccess");
                if (!HomeHotPresenter.this.isViewNull() && list != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onTopListSuccess(list);
                }
            }
        }, false, false));
    }

    public void getAllLiveList(LifecycleTransformer lifecycleTransformer) {
        Log.e("HomeHotPresenter：","getAllLiveList");
        SLLiveSDK.getSingleton().onAllLiveListUpdate(lifecycleTransformer);
    }

    public void getLiveHelperAppConfig() {
        Log.e("HomeHotPresenter：","getLiveHelperAppConfig");
        addMapSubscription(this.mApiService.getStartLiveAppConfigService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<LiveHelperAppConfigEntity>() {
            /* class com.slzhibo.library.ui.presenter.HomeHotPresenter.AnonymousClass8 */

            public void onSuccess(LiveHelperAppConfigEntity liveHelperAppConfigEntity) {
                Log.e("HomeHotPresenter：","getLiveHelperAppConfig  onSuccess");
                if (!HomeHotPresenter.this.isViewNull()) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onLiveHelperAppConfigSuccess(liveHelperAppConfigEntity);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeHotPresenter：","getLiveHelperAppConfig  onError");
                if (!HomeHotPresenter.this.isViewNull()) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onLiveHelperAppConfigFail();
                }
            }
        }));
    }


//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$a */
//    /* loaded from: classes2.dex */
//    public class C5210a implements ResultCallBack<HttpResultPageModel<LiveEntity>> {
//
//        /* renamed from: a */
//        public final /* synthetic */ boolean f18128a;
//
//        public C5210a(boolean z) {
//            this.f18128a = z;
//        }
//
//        /* renamed from: a */
//        public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
//            if (!HomeHotPresenter.this.isViewNull() && httpResultPageModel != null) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2976a(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), this.f18128a);
//            }
//        }
//
//        @Override // com.slzhibo.library.http.ResultCallBack
//        public void onError(int i, String str) {
//            if (!HomeHotPresenter.this.isViewNull()) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2975a(this.f18128a);
//            }
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$b */
//    /* loaded from: classes2.dex */
//    public class C5211b extends ServerResultFunction<HttpResultPageModel<LiveAdEntity>> {
//        public C5211b(HomeHotPresenter c0Var) {
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$c */
//    /* loaded from: classes2.dex */
//    public class C5212c implements ResultCallBack<AnchorEntity> {
//        public C5212c() {
//        }
//
//        /* renamed from: a */
//        public void onSuccess(AnchorEntity anchorEntity) {
//            if (!HomeHotPresenter.this.isViewNull() && anchorEntity != null) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2978a(anchorEntity);
//            }
//        }
//
//        @Override // com.slzhibo.library.http.ResultCallBack
//        public void onError(int i, String str) {
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$d */
//    /* loaded from: classes2.dex */
//    public class C5213d implements SLLiveSDK.OnAdvChannelCallbackListener {
//
//        /* renamed from: a */
//        public final /* synthetic */ String f18131a;
//
//        /* compiled from: HomeHotPresenter.java */
//        /* renamed from: e.t.a.h.e.c0$d$a */
//        /* loaded from: classes2.dex */
//        public class C5214a extends TypeToken<List<BannerEntity>> {
//            public C5214a(C5213d dVar) {
//            }
//        }
//
//        public C5213d(String str) {
//            this.f18131a = str;
//        }
//
//        @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
//        public void onAdvDataFail(int i, String str) {
//            HomeHotPresenter.this.m4165b(this.f18131a);
//        }
//
//        @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
//        public void onAdvDataSuccess(Context context, String str) {
//            try {
//                if (TextUtils.isEmpty(str)) {
//                    HomeHotPresenter.this.m4165b(this.f18131a);
//                    return;
//                }
//                List<BannerEntity> list = (List) GsonUtils.fromJson(str, new C5214a(this).getType());
//                if (list != null && !list.isEmpty()) {
//                    if (!HomeHotPresenter.this.isViewNull()) {
//                        ((IHomeHotView) HomeHotPresenter.this.getView()).mo2973o(list);
//                        return;
//                    }
//                    return;
//                }
//                HomeHotPresenter.this.m4165b(this.f18131a);
//            } catch (Exception e) {
//                e.printStackTrace();
//                HomeHotPresenter.this.m4165b(this.f18131a);
//            }
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$e */
//    /* loaded from: classes2.dex */
//    public class C5215e extends SimpleRxObserver<List<BannerEntity>> {
//        public C5215e() {
//        }
//
//        public void accept(List<BannerEntity> list) {
//            if (!HomeHotPresenter.this.isViewNull()) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2973o(list);
//            }
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$f */
//    /* loaded from: classes2.dex */
//    public class C5216f implements ResultCallBack<List<BannerEntity>> {
//
//        /* renamed from: a */
//        public final /* synthetic */ String f18134a;
//
//        public C5216f(String str) {
//            this.f18134a = str;
//        }
//
//        /* renamed from: a */
//        public void onSuccess(List<BannerEntity> list) {
//            CacheUtils.saveBannerListByCacheDisk(this.f18134a, list);
//            if (!HomeHotPresenter.this.isViewNull()) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2973o(list);
//            }
//        }
//
//        @Override // com.slzhibo.library.http.ResultCallBack
//        public void onError(int i, String str) {
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$g */
//    /* loaded from: classes2.dex */
//    public class C5217g implements ResultCallBack<List<IndexRankEntity>> {
//        public C5217g() {
//        }
//
//        /* renamed from: a */
//        public void onSuccess(List<IndexRankEntity> list) {
//            if (!HomeHotPresenter.this.isViewNull() && list != null) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2972p(list);
//            }
//        }
//
//        @Override // com.slzhibo.library.http.ResultCallBack
//        public void onError(int i, String str) {
//        }
//    }
//
//    /* compiled from: HomeHotPresenter.java */
//    /* renamed from: e.t.a.h.e.c0$h */
//    /* loaded from: classes2.dex */
//    public class C5218h implements ResultCallBack<LiveHelperAppConfigEntity> {
//        public C5218h() {
//        }
//
//        /* renamed from: a */
//        public void onSuccess(LiveHelperAppConfigEntity liveHelperAppConfigEntity) {
//            if (!HomeHotPresenter.this.isViewNull()) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2977a(liveHelperAppConfigEntity);
//            }
//        }
//
//        @Override // com.slzhibo.library.http.ResultCallBack
//        public void onError(int i, String str) {
//            if (!HomeHotPresenter.this.isViewNull()) {
//                ((IHomeHotView) HomeHotPresenter.this.getView()).mo2974e();
//            }
//        }
//    }
//
//    public HomeHotPresenter(Context context, IHomeHotView wVar) {
//        super(context, wVar);
//    }
//
//    /* renamed from: b */
//
//    /**
//     * initBannerList
//     * @param str
//     */
//    public final void m4165b(String str) {
//        if (CacheUtils.isBannerListByCacheDisk(str)) {
//            Observable.just(CacheUtils.getBannerListByCacheDisk(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe( new C5215e());
//        } else {
//            addMapSubscription(this.mApiService.getBannerListService(new RequestParams().getBannerListParams(str)), new HttpRxObserver(getContext(), (ResultCallBack) new C5216f(str), false, false));
//        }
//    }
//
//    /* renamed from: i */
//    public void m4158i() {
//        addMapSubscription(this.mApiService.getStartLiveAppConfigService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new C5218h()));
//    }
//
//    /* renamed from: j */
//    public void m4156j() {
//        addMapSubscription(this.mApiService.getIndexRankService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), (ResultCallBack) new C5217g(), false, false));
//    }
//
//    /* renamed from: k */
//    public void m4154k() {
//        addMapSubscription(this.mApiService.getAnchorAuthService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new C5212c(), true));
//    }
//
//    /* renamed from: a */
//
//    /**
//     * getLiveList
//     * @param stateView
//     * @param i
//     * @param z
//     * @param z2
//     * @param lifecycleTransformer
//     */
//    public void m4171a(StateView stateView, int i, boolean z, boolean z2, LifecycleTransformer lifecycleTransformer) {
//        this.mApiService.getV03RecommendListService(new RequestParams().getAdvChannelPageListParams(i)).map(new C5211b(this)).flatMap(C5352f.f18267b).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose( lifecycleTransformer).subscribe( new HttpRxObserver(getContext(), new C5210a(z2), stateView, z, false));
//    }
//
//    /* renamed from: a */
//    public void m4167a(String str) {
//        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
//            m4165b(str);
//        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
//            SLLiveSDK.getSingleton().sdkCallbackListener.onAdvChannelListener(getContext(), str, new C5213d(str));
//        }
//    }
//
//    /* renamed from: a */
//    public void m4170a(LifecycleTransformer lifecycleTransformer) {
//        SLLiveSDK.getSingleton().onAllLiveListUpdate(lifecycleTransformer);
//    }
}

