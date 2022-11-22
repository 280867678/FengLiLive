package com.slzhibo.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.slzhibo.library.R;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.ui.adapter.DedicateTopAdapter;
import com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback;
import com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment;
import com.slzhibo.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.UserInfoManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DedicateTopAllDialog extends BaseBottomDialogFragment {
    private String anchorId;
    private AnchorEntity anchorInfoItem;
    private int liveType = 2;
    private DedicateTopAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private OnLivePusherInfoCallback onLivePusherInfoCallback;
    private ProgressBar progressBar;

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public float getDimAmount() {
        return 0.0f;
    }

    public static DedicateTopAllDialog newInstance(int i, AnchorEntity anchorEntity, OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        Bundle bundle = new Bundle();
        DedicateTopAllDialog dedicateTopAllDialog = new DedicateTopAllDialog();
        bundle.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, i);
        bundle.putParcelable("anchorId_key", anchorEntity);
        dedicateTopAllDialog.setArguments(bundle);
        dedicateTopAllDialog.setOnLivePusherInfoCallback(onLivePusherInfoCallback2);
        return dedicateTopAllDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_dedicate_top_all;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.liveType = getArgumentsInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
        this.anchorInfoItem = (AnchorEntity) bundle.getParcelable("anchorId_key");
        AnchorEntity anchorEntity = this.anchorInfoItem;
        this.anchorId = anchorEntity != null ? anchorEntity.userId : "";
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new DedicateTopAdapter(R.layout.fq_item_list_dedicate_top_live, true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 38));
        sendRequest(this.anchorId);
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initListener(View view) {
        super.initListener(view);
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopAllDialog$W6CtcSDhXpVCCHpWXgndcabSAnk */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DedicateTopAllDialog.this.lambda$initListener$1$DedicateTopAllDialog(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$1$DedicateTopAllDialog(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
        if (anchorEntity != null) {
            if (!anchorEntity.isRankHideBoolean() || TextUtils.equals(anchorEntity.userId, UserInfoManager.getInstance().getUserId())) {
                OnLivePusherInfoCallback onLivePusherInfoCallback2 = this.onLivePusherInfoCallback;
                if (onLivePusherInfoCallback2 != null) {
                    onLivePusherInfoCallback2.onClickUserAvatarListener(AppUtils.formatUserEntity(anchorEntity));
                    return;
                }
                return;
            }
            NobilityOpenTipsDialog.newInstance(13, new View.OnClickListener() {
                /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopAllDialog$QU7VH3GXdTgBXRG3Y3qgNht6xco */

                public final void onClick(View view) {
                    DedicateTopAllDialog.this.lambda$null$0$DedicateTopAllDialog(view);
                }
            }).show(getChildFragmentManager());
        }
    }

    public /* synthetic */ void lambda$null$0$DedicateTopAllDialog(View view) {
        OnLivePusherInfoCallback onLivePusherInfoCallback2;
        if (!AppUtils.isAnchorLiveType(this.liveType) && (onLivePusherInfoCallback2 = this.onLivePusherInfoCallback) != null) {
            onLivePusherInfoCallback2.onNobilityOpenListener();
        }
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void sendRequest(String str) {
        ApiRetrofit.getInstance().getApiService().getDedicateTopListService(new RequestParams().getContributionListParams("all", str)).map(new ServerResultFunction<List<AnchorEntity>>() {
            /* class com.slzhibo.library.ui.view.dialog.DedicateTopAllDialog.AnonymousClass2 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new Observer<List<AnchorEntity>>() {
            /* class com.slzhibo.library.ui.view.dialog.DedicateTopAllDialog.AnonymousClass1 */

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                DedicateTopAllDialog.this.showLoading(true);
            }

            public void onNext(List<AnchorEntity> list) {
                if (list != null) {
                    DedicateTopAllDialog.this.mAdapter.setNewData(list);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                DedicateTopAllDialog.this.showLoading(false);
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                DedicateTopAllDialog.this.showLoading(false);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint("WrongConstant")
    private void showLoading(boolean z) {
        int i = 0;
        this.progressBar.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        RecyclerView recyclerView = this.mRecyclerView;
        if (z) {
            i = 4;
        }
        recyclerView.setVisibility(i);
    }

    public void setOnLivePusherInfoCallback(OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        this.onLivePusherInfoCallback = onLivePusherInfoCallback2;
    }
}
