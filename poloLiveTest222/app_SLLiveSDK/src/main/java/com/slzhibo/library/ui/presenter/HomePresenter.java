package com.slzhibo.library.ui.presenter;



import android.content.Context;
import android.util.Log;

import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.LabelEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.ui.view.iview.IHomeView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.ui.view.iview.IHomeView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.slzhibo.library.ui.presenter.HomePresenter */
/* loaded from: classes10.dex */
public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(Context context, IHomeView iHomeView) {
        super(context, iHomeView);
    }

    public void sendInitRequest(StateView stateView, boolean z, LifecycleTransformer lifecycleTransformer) {
        sendInitRequest(true, stateView, z, lifecycleTransformer);
    }

    public void sendInitRequest(final boolean z, final StateView stateView, final boolean z2, final LifecycleTransformer lifecycleTransformer) {
        if (isApiService()) {
            ApiRetrofit.getInstance().getApiService().getUpdateTokenService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<UserEntity>() { // from class: com.slzhibo.library.ui.presenter.HomePresenter.3
            }).onErrorResumeNext(new HttpResultFunction<UserEntity>() { // from class: com.slzhibo.library.ui.presenter.HomePresenter.2
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new SimpleRxObserver<UserEntity>(getContext(), false) { // from class: com.slzhibo.library.ui.presenter.HomePresenter.1
                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    StateView stateView2;
                    super.onSubscribe(disposable);
                    Log.e("HomePresenter:","sendInitRequest  onSubscribe");
                    if (z2 && (stateView2 = stateView) != null) {
                        stateView2.showLoading();
                    }
                }

                @Override
                public void accept(UserEntity userEntity) {
                    Log.e("HomePresenter:","sendInitRequest accept 1");
                    if (userEntity == null) {
                        StateView stateView2 = stateView;
                        Log.e("HomePresenter:","sendInitRequest accept 2");
                        if (stateView2 != null) {
                            stateView2.showRetry();
                            Log.e("HomePresenter:","sendInitRequest accept 3");
                            return;
                        }
                        return;
                    }
                    Log.e("HomePresenter:","sendInitRequest accept getToken"+userEntity.getToken());
                    UserInfoManager.getInstance().setToken(userEntity.getToken());
                    HomePresenter.this.initRequest(z, stateView, false, lifecycleTransformer);
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    Log.e("HomePresenter:","sendInitRequest accept 4");
                    HomePresenter.this.initRequest(z, stateView, false, lifecycleTransformer);
                }
            });
        } else if (stateView != null) {
            stateView.showRetry();
        }
    }

    public void getTagList(final StateView stateView, final boolean z, LifecycleTransformer lifecycleTransformer) {
        ApiRetrofit.getInstance().getApiService().getLabelListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<LabelEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomePresenter.6
        }).onErrorResumeNext(new HttpResultFunction<List<LabelEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomePresenter.5
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new SimpleRxObserver<List<LabelEntity>>(getContext(), false) { // from class: com.slzhibo.library.ui.presenter.HomePresenter.4
            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                Log.e("HomePresenter:","getTagList onSubscribe 1");
                StateView stateView2;
                super.onSubscribe(disposable);
                if (z && (stateView2 = stateView) != null) {
                    Log.e("HomePresenter:","getTagList onSubscribe 2");
                    stateView2.showLoading();
                }
            }

            @Override
            public void accept(List<LabelEntity> list) {
                StateView stateView2 = stateView;
                Log.e("HomePresenter:","getTagList accept 1");
                if (stateView2 != null) {
                    Log.e("HomePresenter:","getTagList accept 2");
                    stateView2.showContent();
                }
                if (!HomePresenter.this.isViewNull()) {
                    Log.e("HomePresenter:","getTagList accept 3");
                    ((IHomeView) HomePresenter.this.getView()).onTagListSuccess(list);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                StateView stateView2 = stateView;
                Log.e("HomePresenter:","getTagList onError 1");
                if (stateView2 != null) {
                    stateView2.showContent();
                    Log.e("HomePresenter:","getTagList onError 2");
                }
                if (!HomePresenter.this.isViewNull()) {
                    Log.e("HomePresenter:","getTagList onError 3");
                    ((IHomeView) HomePresenter.this.getView()).onTagListFail();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRequest(boolean z, StateView stateView, boolean z2, LifecycleTransformer lifecycleTransformer) {
        SLLiveSDK.getSingleton().initSysConfig();
//        SLLiveSDK.getSingleton().onSendMLCallUrlRequest();

        Log.e("HomePresenter:","sendInitRequest accept initRequest");
        if (z) {
            Log.e("HomePresenter:","sendInitRequest accept getTagList");
            getTagList(stateView, z2, lifecycleTransformer);
            return;
        }
        if (stateView != null) {
            stateView.showContent();
        }
        if (!isViewNull()) {
            getView().onTagListSuccess(new ArrayList());
        }
    }

    public final void m3983b(StateView stateView, boolean z, LifecycleTransformer lifecycleTransformer) {
        SLLiveSDK.getSingleton().initSysConfig();
        getTagList(stateView, z, lifecycleTransformer);
    }

//    public void m3986a(StateView stateView, boolean z, LifecycleTransformer lifecycleTransformer) {
//        ApiRetrofit.getInstance().getApiService().getLabelListService(new RequestParams().getDefaultParams()).m484c(new C5343f(this)).m479d(new C5342e(this)).m489b(Schedulers.m549b()).m507a(AndroidSchedulers.m453a()).m508a((ObservableTransformer) lifecycleTransformer).mo118a((Observer) new C5341d(m4432c(), false, z, stateView));
//    }























































    /* renamed from: c */
//    public void m3981c(StateView stateView, boolean z, LifecycleTransformer lifecycleTransformer) {
//        if (isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getUpdateTokenService(new RequestParams().getDefaultParams()).map(new C5340c(this)).onErrorResumeNext(new C5339b(this)).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).compose( lifecycleTransformer).subscribe( new C5338a(getContext(), false, z, stateView, lifecycleTransformer));
//        } else if (stateView != null) {
//            stateView.showRetry();
//        }
//    }
//
//
//    public class C5340c extends ServerResultFunction<UserEntity> {
//        public C5340c(HomePresenter d0Var) {
//        }
//    }
//
//    public class C5339b extends HttpResultFunction<UserEntity> {
//        public C5339b(HomePresenter d0Var) {
//        }
//    }
//
//    public class C5338a extends SimpleRxObserver<UserEntity> {
//
//        /* renamed from: b */
//        public final /* synthetic */ boolean f18246b;
//
//        /* renamed from: c */
//        public final /* synthetic */ StateView f18247c;
//
//        /* renamed from: d */
//        public final /* synthetic */ LifecycleTransformer f18248d;
//
//        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
//        public C5338a(Context context, boolean z, boolean z2, StateView stateView, LifecycleTransformer lifecycleTransformer) {
//            super(context, z);
//            this.f18246b = z2;
//            this.f18247c = stateView;
//            this.f18248d = lifecycleTransformer;
//        }
//
//        /* renamed from: a */
//        public void accept(UserEntity userEntity) {
//            if (userEntity != null) {
//                UserInfoManager.getInstance().setToken(userEntity.getToken());
//                HomePresenter.this.initRequest(this.f18247c, false, this.f18248d);
//            }
//        }
//
//        @Override // com.slzhibo.library.utils.live.SimpleRxObserver, p481f.p482a.Observer
//        public void onError(Throwable th) {
//            super.onError(th);
//            HomePresenter.this.initRequest(this.f18247c, false, this.f18248d);
//        }
//
//        @Override // com.slzhibo.library.utils.live.SimpleRxObserver, p481f.p482a.Observer
//        public void onSubscribe(Disposable bVar) {
//            StateView stateView;
//            super.onSubscribe(bVar);
//            if (this.f18246b && (stateView = this.f18247c) != null) {
//                stateView.showLoading();
//            }
//        }
//    }



}
