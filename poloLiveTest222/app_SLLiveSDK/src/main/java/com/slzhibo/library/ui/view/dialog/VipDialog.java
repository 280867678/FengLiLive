package com.slzhibo.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.slzhibo.library.R;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.ui.adapter.LiveVipAdapter;
import com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback;
import com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment;
import com.slzhibo.library.ui.view.emptyview.VipEmptyView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.RxViewUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VipDialog extends BaseBottomDialogFragment {
    private AnchorEntity anchorInfoItem;
    private int liveType = 2;
    private LinearLayout llContentBg;
    private LinearLayout llFooterBg;
    private LiveVipAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private OnLivePusherInfoCallback onLivePusherInfoCallback;
    private ProgressBar progressBar;
    private TextView tvCount;
    private TextView tvOpen;
    private long vipCount = 0;

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public float getDimAmount() {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public double getHeightScale() {
        return 0.74d;
    }

    public static VipDialog newInstance(AnchorEntity anchorEntity, long j, int i, OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        Bundle bundle = new Bundle();
        VipDialog vipDialog = new VipDialog();
        bundle.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
        bundle.putLong(ConstantUtils.RESULT_COUNT, j);
        bundle.putInt(ConstantUtils.RESULT_FLAG, i);
        vipDialog.setArguments(bundle);
        vipDialog.setOnLivePusherInfoCallback(onLivePusherInfoCallback2);
        return vipDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.anchorInfoItem = (AnchorEntity) bundle.getParcelable(ConstantUtils.RESULT_ITEM);
        this.vipCount = bundle.getLong(ConstantUtils.RESULT_COUNT, 0);
        this.liveType = bundle.getInt(ConstantUtils.RESULT_FLAG, 2);
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_vip_top;
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.tvCount = (TextView) view.findViewById(R.id.tv_vip_count);
        this.tvOpen = (TextView) view.findViewById(R.id.tv_open);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        this.llContentBg = (LinearLayout) view.findViewById(R.id.ll_content_bg);
        this.llFooterBg = (LinearLayout) view.findViewById(R.id.ll_footer_bg);
        this.tvCount.setText(getString(R.string.fq_vip_place_count, AppUtils.formatLiveVipCount(this.vipCount)));
        this.tvOpen.setText(AppUtils.isNobilityUser() ? R.string.fq_nobility_renewal_fee : R.string.fq_nobility_open);
        int i = 4;
        if (!AppUtils.isEnableNobility()) {
            this.tvOpen.setVisibility(View.INVISIBLE);
        } else {
            TextView textView = this.tvOpen;
            if (AppUtils.isAudienceLiveType(this.liveType)) {
                i = 0;
            }
            textView.setVisibility(i);
        }
        initAdapter();
        sendRequest();
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initListener(View view) {
        super.initListener(view);
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$VipDialog$IqH3Qoz0rg85WEXs2IJUe6u9N6E */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipDialog.this.lambda$initListener$0$VipDialog(baseQuickAdapter, view, i);
            }
        });
        RxViewUtils.getInstance().throttleFirst(this.tvOpen, 500, new RxViewUtils.RxViewAction() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$VipDialog$3x6oTKCtzsHuIDAune8YBC51Jog */

            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
            public final void action(Object obj) {
                VipDialog.this.lambda$initListener$1$VipDialog(obj);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$VipDialog(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        OnLivePusherInfoCallback onLivePusherInfoCallback2;
        UserEntity userEntity = (UserEntity) baseQuickAdapter.getItem(i);
        if (userEntity != null && (onLivePusherInfoCallback2 = this.onLivePusherInfoCallback) != null) {
            onLivePusherInfoCallback2.onClickUserAvatarListener(userEntity);
        }
    }

    public /* synthetic */ void lambda$initListener$1$VipDialog(Object obj) {
        toNobilityOpenActivity();
    }

    private void initAdapter() {
        this.mAdapter = new LiveVipAdapter(R.layout.fq_item_list_vip);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new VipEmptyView(this.mContext));
    }

    private void sendRequest() {
        AnchorEntity anchorEntity = this.anchorInfoItem;
        if (anchorEntity != null) {
            ApiRetrofit.getInstance().getApiService().getVipSeatListService(new RequestParams().getVipSeatListParams(anchorEntity.liveId)).map(new ServerResultFunction<List<UserEntity>>() {
                /* class com.slzhibo.library.ui.view.dialog.VipDialog.AnonymousClass2 */
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new Observer<List<UserEntity>>() {
                /* class com.slzhibo.library.ui.view.dialog.VipDialog.AnonymousClass1 */

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    VipDialog.this.showLoading(true);
                }

                public void onNext(List<UserEntity> list) {
                    if (list != null) {
                        VipDialog.this.mAdapter.setNewData(list);
                        VipDialog.this.llFooterBg.setVisibility(list.isEmpty() ? View.GONE : View.VISIBLE);
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    VipDialog.this.showLoading(false);
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    VipDialog.this.showLoading(false);
                }
            });
        }
    }

    public void setOnLivePusherInfoCallback(OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        this.onLivePusherInfoCallback = onLivePusherInfoCallback2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint("WrongConstant")
    private void showLoading(boolean z) {
        int i = 0;
        this.progressBar.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        LinearLayout linearLayout = this.llContentBg;
        if (z) {
            i = 4;
        }
        linearLayout.setVisibility(i);
    }

    private void toNobilityOpenActivity() {
        if (AppUtils.isAudienceLiveType(this.liveType)) {
            AppUtils.toNobilityOpenActivity(this.mContext, this.anchorInfoItem);
        }
    }
}
