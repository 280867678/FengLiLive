package com.slzhibo.library.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.slzhibo.library.R;
import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.model.event.ListDataUpdateEvent;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.Collection;
import java.util.List;

import com.slzhibo.library.ui.adapter.HomeLiveAdapter;
import com.slzhibo.library.ui.presenter.HomeSortPresenter;
import com.slzhibo.library.ui.view.divider.RVDividerLive;
import com.slzhibo.library.ui.view.emptyview.RecyclerEmptyView;
import com.slzhibo.library.ui.view.iview.IHomeSortView;
import com.slzhibo.library.ui.view.widget.StateView;

import com.slzhibo.library.utils.live.PlayManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import com.slzhibo.library.R$color;
//import com.slzhibo.library.R$id;
//import com.slzhibo.library.R$layout;
//import com.slzhibo.library.R$string;


/**
 * 付费
 */
public class HomeSortFragment extends BaseFragment<HomeSortPresenter> implements IHomeSortView {
    private boolean isFeeTag = false;
    private HomeLiveAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private PlayManager playManager;
    private String tagId;
    private int tagType;

    @Override // com.slzhibo.library.base.BaseFragment
    public boolean isAutoRefreshDataEnable() {
        return true;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public boolean isEnablePageStayReport() {
        return true;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public boolean isLazyLoad() {
        return true;
    }

    static /* synthetic */ int access$308(HomeSortFragment homeSortFragment) {
        int i = homeSortFragment.pageNum;
        homeSortFragment.pageNum = i + 1;
        return i;
    }

    public static HomeSortFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        HomeSortFragment homeSortFragment = new HomeSortFragment();
        bundle.putString(ConstantUtils.TAB_TAG_ID, str);
        bundle.putInt(ConstantUtils.RESULT_FLAG, 1);
        homeSortFragment.setArguments(bundle);
        return homeSortFragment;
    }

    public static HomeSortFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        HomeSortFragment homeSortFragment = new HomeSortFragment();
        bundle.putInt(ConstantUtils.RESULT_FLAG, i);
        homeSortFragment.setArguments(bundle);
        return homeSortFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.base.BaseFragment
    public HomeSortPresenter createPresenter() {
        return new HomeSortPresenter(this.mContext, this);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.tagId = bundle.getString(ConstantUtils.TAB_TAG_ID);
        boolean z = true;
        this.tagType = bundle.getInt(ConstantUtils.RESULT_FLAG, 1);
        if (this.tagType != 2) {
            z = false;
        }
        this.isFeeTag = z;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public int getLayoutId() {
        return R.layout.fq_fragment_home_sort;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.playManager = new PlayManager(this.mContext);
        initAdapter();
    }

    private void initAdapter() {
        this.mAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerEmptyView(this.mContext));
        this.playManager.initRecyclerViewPlayManager(this.mRecyclerView);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onLazyLoad() {
        sendRequest(true, true);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onFragmentVisible(boolean z) {
        super.onFragmentVisible(z);
        if (z) {
            lambda$onDataListSuccess$3$HomeSortFragment();
        } else {
            pausePlay();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            lambda$onDataListSuccess$3$HomeSortFragment();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        pausePlay();
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        onDestroyPlay();
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initListener(View view) {
        super.initListener(view);
        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeSortFragment$b1PYqf9P516rG7CpUO7O3_n3j0g
            @Override // com.slzhibo.library.ui.view.widget.StateView.OnRetryClickListener
            public final void onRetryClick() {
                HomeSortFragment.this.lambda$initListener$0$HomeSortFragment();
            }
        });
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.slzhibo.library.ui.fragment.HomeSortFragment.1
            @Override // com.slzhibo.library.ui.view.widget.smartrefreshlayout.layout.listener.OnRefreshListener
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                ( HomeSortFragment.this).pageNum = 1;
                HomeSortFragment.this.sendRequest(false, true);
                refreshLayout.finishRefresh();
            }
        });
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeSortFragment$AbV6p3OQaSsyR2-Co3pvStdMHzk
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                HomeSortFragment.this.lambda$initListener$1$HomeSortFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeSortFragment$hKytnJSoJ1R6lwPcK5gcUwe_8mc
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                return HomeSortFragment.this.lambda$initListener$2$HomeSortFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mRecyclerView.addOnScrollListener(new AnonymousClass2());
    }

    public /* synthetic */ void lambda$initListener$0$HomeSortFragment() {
        this.pageNum = 1;
        sendRequest(true, true);
    }

    public /* synthetic */ void lambda$initListener$1$HomeSortFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (liveEntity != null) {
            AppUtils.startSLLiveActivity(this.mContext, liveEntity, "1", getString(R.string.fq_other_list));
        }
    }

    public /* synthetic */ boolean lambda$initListener$2$HomeSortFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        PlayManager playManager;
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (!(liveEntity == null || !liveEntity.isCoverPreview() || (playManager = this.playManager) == null)) {
            Toast.makeText(getContext(), "推荐Sort;playManager:214:"+"\n"+i+"\n"+ baseQuickAdapter.getHeaderLayoutCount(), Toast.LENGTH_SHORT).show();
            playManager.playVideoByPosition(i + baseQuickAdapter.getHeaderLayoutCount());
        }
        return true;
    }

    @Override
    public void onResultError(int i) {

    }

    /* renamed from: com.slzhibo.library.ui.fragment.HomeSortFragment$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends RecyclerView.OnScrollListener {
        AnonymousClass2() {
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                recyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeSortFragment$2$AyZJVDGoYsQJPIGtYc23RC959b8
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeSortFragment.AnonymousClass2.this.lambda$onScrollStateChanged$0$HomeSortFragment$2();
                    }
                }, 300L);
            }
            if (HomeSortFragment.this.isAutoPreLoadingMore(recyclerView)) {
                ( HomeSortFragment.this).isLoadingMore = true;
                HomeSortFragment.access$308(HomeSortFragment.this);
                HomeSortFragment.this.sendRequest(false, false);
            }
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$HomeSortFragment$2() {
            if (HomeSortFragment.this.playManager != null) {
                HomeSortFragment.this.playManager.onScrolled();
                HomeSortFragment.this.playManager.onScrollStateChanged();
            }
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeSortView
    public void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        stopPlay();
        if (z2) {
            this.mRecyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeSortFragment$23yh6eG42Whml1E5-A0K0q1iIVQ
                @Override // java.lang.Runnable
                public final void run() {
                    HomeSortFragment.this.lambda$onDataListSuccess$3$HomeSortFragment();
                }
            }, 800L);
            this.mAdapter.setNewData(list);
        } else {
            this.isLoadingMore = false;
            Observable.just(AppUtils.removeDuplicateList(this.mAdapter.getData(), list)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new SimpleRxObserver<List<LiveEntity>>(this.mContext, false) { // from class: com.slzhibo.library.ui.fragment.HomeSortFragment.3
                public void accept(List<LiveEntity> list2) {
                    HomeSortFragment.this.mAdapter.addData((Collection) list2);
                }
            });
        }
        this.isNoMoreData = z;
        AppUtils.updateRefreshLayoutFinishStatus(this.mSmartRefreshLayout, z, z2);
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeSortView
    public void onDataListFail(boolean z) {
        if (!z) {
            this.isLoadingMore = false;
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        if ((baseEvent instanceof ListDataUpdateEvent) && ((ListDataUpdateEvent) baseEvent).isAutoRefresh && getUserVisibleHint()) {
            onAutoRefreshData();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onAutoRefreshData() {
        super.onAutoRefreshData();
        this.pageNum = 1;
        sendRequest(false, true);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public String getPageStayTimerType() {
        return this.tagId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRequest(boolean z, boolean z2) {
        int i = this.tagType;
        if (i == 1) {
            ((HomeSortPresenter) this.mPresenter).getLiveList(this.mStateView, this.tagId, this.pageNum, z, z2);
        } else if (i == 2) {
            ((HomeSortPresenter) this.mPresenter).getFeeLiveList(this.mStateView, this.pageNum, z, z2);
        } else if (i == 3) {
            ((HomeSortPresenter) this.mPresenter).getBluetoothLiveList(this.mStateView, this.pageNum, z, z2);
        }
    }

    private void stopPlay() {
        PlayManager playManager;
        if (!this.isFeeTag && (playManager = this.playManager) != null) {
            playManager.stopPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: resumePlay */
    public void lambda$onDataListSuccess$3$HomeSortFragment() {
        PlayManager playManager;
        if (!this.isFeeTag && (playManager = this.playManager) != null) {
            playManager.onRecyclerViewResume();
        }
    }

    private void pausePlay() {
        PlayManager playManager;
        if (!this.isFeeTag && (playManager = this.playManager) != null) {
            playManager.onRecyclerViewPause();
        }
    }

    private void onDestroyPlay() {
        if (this.isFeeTag) {
        }
    }
}
