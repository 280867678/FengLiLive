package com.slzhibo.library.ui.presenter;

import android.content.Context;

import com.slzhibo.library.R;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.ui.view.iview.IHomeAttentionView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomeAttentionPresenter extends BasePresenter<IHomeAttentionView> {
    public HomeAttentionPresenter(Context context, IHomeAttentionView iHomeAttentionView) {
        super(context, iHomeAttentionView);
    }

    public void getAttentionAnchorListList(StateView stateView, int i, boolean z, boolean z2, LifecycleTransformer lifecycleTransformer) {
        initObservableZip(stateView, z, z2, this.mApiService.getAttentionAnchorListService(new RequestParams().getAdvChannelPageListParams(i)).map(new ServerResultFunction<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAttentionPresenter.1
        }).onErrorResumeNext(new HttpResultFunction()), this.mApiService.productSubscribeListService(new RequestParams().getPageListParams(1)).map(new ServerResultFunction<HttpResultPageModel<HJProductEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAttentionPresenter.3
        }).flatMap(new Function<HttpResultPageModel<HJProductEntity>, ObservableSource<List<BannerEntity>>>() { // from class: com.slzhibo.library.ui.presenter.HomeAttentionPresenter.2
            public ObservableSource<List<BannerEntity>> apply(HttpResultPageModel<HJProductEntity> httpResultPageModel) throws Exception {
                List<HJProductEntity> list;
                ArrayList arrayList = new ArrayList();
                if (!(httpResultPageModel == null || (list = httpResultPageModel.dataList) == null)) {
                    for (HJProductEntity hJProductEntity : list) {
                        BannerEntity bannerEntity = new BannerEntity();
                        bannerEntity.id = hJProductEntity.productId;
                        bannerEntity.content = HomeAttentionPresenter.this.getContext().getString(R.string.fq_hj_attention_dynamic_subscribe_tips, hJProductEntity.anchorName, DateUtils.getShortTime(NumberUtils.string2long(hJProductEntity.createTime) * 1000), hJProductEntity.productName);
                        arrayList.add(bannerEntity);
                    }
                }
                return Observable.just(arrayList);
            }
        }).onErrorResumeNext(new HttpResultFunction()), lifecycleTransformer);
    }

    public void getRecommendAnchorList(StateView stateView, boolean z) {
        addMapSubscription(this.mApiService.getRecommendAnchorService(new RequestParams().getPageListParams(1, 3)), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeAttentionPresenter.AnonymousClass4 */

            public void onSuccess(List<AnchorEntity> list) {
                if (!HomeAttentionPresenter.this.isViewNull() && list != null) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onRecommendListSuccess(list);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                if (!HomeAttentionPresenter.this.isViewNull()) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onRecommendListFail();
                }
            }
        }, stateView, z, false));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.HomeAttentionPresenter.AnonymousClass5 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                if (!HomeAttentionPresenter.this.isViewNull()) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionSuccess();
                }
            }
        }));
    }

    private void initObservableZip(StateView stateView, boolean z, final boolean z2, Observable<HttpResultPageModel<LiveEntity>> observable, Observable<List<BannerEntity>> observable2, LifecycleTransformer lifecycleTransformer) {
        Observable.zip(observable, observable2, new BiFunction() { // from class: com.slzhibo.library.ui.presenter.-$$Lambda$HomeAttentionPresenter$LSS_j7UNJ5Ik-UMVjpRO2HD8UfI
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) throws Exception {
                return HomeAttentionPresenter.this.lambda$initObservableZip$0$HomeAttentionPresenter(z2, (HttpResultPageModel) obj, (List) obj2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(lifecycleTransformer).subscribe(new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.ui.presenter.HomeAttentionPresenter.6
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (!HomeAttentionPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionListSuccess(httpResultPageModel.dataList, httpResultPageModel.isHasBanner, httpResultPageModel.isMorePage(), z2, httpResultPageModel.adapterSpanPosition);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                if (!HomeAttentionPresenter.this.isViewNull()) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionListFail(z2);
                }
            }
        }, stateView, z, false));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, com.slzhibo.library.model.LiveEntity] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public /* synthetic */ HttpResultPageModel lambda$initObservableZip$0$HomeAttentionPresenter(boolean z, HttpResultPageModel httpResultPageModel, List list) throws Exception {
        if (list == null) {
            httpResultPageModel.isHasBanner = false;
            return httpResultPageModel;
        }
        httpResultPageModel.isHasBanner = false;
        List<LiveEntity> list2 = httpResultPageModel.dataList;
        int adapterSpanPosition = getAdapterSpanPosition(list2);
        if (z && list2 != null && adapterSpanPosition > -1) {
            List<LiveEntity> list3 = (List<LiveEntity>) formatSpanPositionList(list2);
            httpResultPageModel.isHasBanner = true;
            httpResultPageModel.adapterSpanPosition = adapterSpanPosition;
            LiveEntity liveEntity = new LiveEntity();
            liveEntity.itemType = 3;
            liveEntity.bannerList = list;
            list3.add(adapterSpanPosition, liveEntity);
            httpResultPageModel.dataList = list3;
        }
        return httpResultPageModel;
    }

    private int getAdapterSpanPosition(List<LiveEntity> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            if (size <= 2) {
                return 2;
            }
            if (size > 4 && size < 4) {
                return -1;
            }
            return 4;
        }
        return -1;
    }

    private List<LiveEntity> formatSpanPositionList(List<LiveEntity> list) {
        int size = list.size();
        int adapterSpanPosition = getAdapterSpanPosition(list);
        if (size >= 4) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        int size2 = arrayList.size() % adapterSpanPosition;
        if (size2 <= 0) {
            return arrayList;
        }
        int i = adapterSpanPosition - size2;
        for (int i2 = 0; i2 < i; i2++) {
            LiveEntity liveEntity = new LiveEntity();
            liveEntity.itemType = 4;
            arrayList.add(liveEntity);
        }
        return arrayList;
    }
}
