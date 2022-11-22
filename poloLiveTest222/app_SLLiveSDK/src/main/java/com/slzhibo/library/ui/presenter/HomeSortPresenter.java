package com.slzhibo.library.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.ui.view.iview.IHomeSortView;
import com.slzhibo.library.ui.view.widget.StateView;

public class HomeSortPresenter extends BasePresenter<IHomeSortView> {
    public HomeSortPresenter(Context context, IHomeSortView iHomeSortView) {
        super(context, iHomeSortView);
    }

    public void getLiveList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        Log.e("HomeSortPresenter：","getLiveList ");
        addMapSubscription(this.mApiService.getSortListService(new RequestParams().getTagPageListParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeSortPresenter.AnonymousClass1 */

            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                Log.e("HomeSortPresenter：","getLiveList  onSuccess");
                if (!HomeSortPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeSortPresenter：","getLiveList  onError");
                if (!HomeSortPresenter.this.isViewNull()) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListFail(z2);
                }
            }
        }, stateView, z, false));
    }

    public void getFeeLiveList(StateView stateView, int i, boolean z, final boolean z2) {
        Log.e("HomeSortPresenter："," getFeeLiveList");
        addMapSubscription(this.mApiService.getFeeTagListService(new RequestParams().getPageListParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeSortPresenter.AnonymousClass2 */

            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                Log.e("HomeSortPresenter：","getFeeLiveList   onSuccess");
                if (!HomeSortPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeSortPresenter：","getFeeLiveList   onError");
                if (!HomeSortPresenter.this.isViewNull()) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListFail(z2);
                }
            }
        }, stateView, z, false));
    }

    public void getBluetoothLiveList(StateView stateView, int i, boolean z, final boolean z2) {
        Log.e("HomeSortPresenter："," getBluetoothLiveList");
        addMapSubscription(this.mApiService.getBluetoothListTagListService(new RequestParams().getPageListParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            /* class com.slzhibo.library.ui.presenter.HomeSortPresenter.AnonymousClass3 */

            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                Log.e("HomeSortPresenter：","getBluetoothLiveList  onSuccess");
                if (!HomeSortPresenter.this.isViewNull() && httpResultPageModel != null) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                Log.e("HomeSortPresenter：","getBluetoothLiveList   onError");
                if (!HomeSortPresenter.this.isViewNull()) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onDataListFail(z2);
                }
            }
        }, stateView, z, false));
    }
}
