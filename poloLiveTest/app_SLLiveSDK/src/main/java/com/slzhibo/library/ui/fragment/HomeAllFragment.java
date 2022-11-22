package com.slzhibo.library.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.slzhibo.library.R;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.model.event.ListDataUpdateEvent;
import com.slzhibo.library.ui.adapter.HomeLiveAllAdapter;
import com.slzhibo.library.ui.view.divider.RVDividerLiveAll;
import com.slzhibo.library.ui.view.emptyview.RecyclerEmptyView;
import com.slzhibo.library.ui.view.iview.IHomeAllView;
import com.slzhibo.library.ui.view.widget.StateView;

import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.live.PlayManager;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 全部
 */
public class HomeAllFragment extends BaseFragment<HomeAllPresenter> implements IHomeAllView {
    private RVDividerLiveAll itemDecoration;
    private HomeLiveAllAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private PlayManager playManager;
    private List<LiveEntity> mListData = new ArrayList();
    private int bannerSpanPosition = 4;

    private void onDestroyPlay() {
    }

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

    @Override // com.slzhibo.library.base.BaseView
    public void onResultError(int i) {
    }

    static /* synthetic */ int access$308(HomeAllFragment homeAllFragment) {
        int i = homeAllFragment.pageNum;
        homeAllFragment.pageNum = i + 1;
        return i;
    }

    public static HomeAllFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeAllFragment homeAllFragment = new HomeAllFragment();
        homeAllFragment.setArguments(bundle);
        return homeAllFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.base.BaseFragment
    public HomeAllPresenter createPresenter() {
        return new HomeAllPresenter(this.mContext, this);
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
        this.itemDecoration = new RVDividerLiveAll(this.mContext, R.color.fq_colorWhite);
        this.mAdapter = new HomeLiveAllAdapter(this.mListData);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.addItemDecoration(this.itemDecoration);
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
            lambda$onDataListSuccess$4$HomeAllFragment();
        } else {
            pausePlay();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            lambda$onDataListSuccess$4$HomeAllFragment();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        pausePlay();
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        onDestroyPlay();
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initListener(View view) {
        super.initListener(view);
        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$FfR87_b4XoGNDG1HM4Ut6sii4Vg
            @Override // com.slzhibo.library.p115ui.view.widget.StateView.OnRetryClickListener
            public final void onRetryClick() {
                Toast.makeText(mActivity, "onItemLongClick777", Toast.LENGTH_SHORT).show();
                HomeAllFragment.this.lambda$initListener$0$HomeAllFragment();
            }
        });
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.slzhibo.library.ui.fragment.HomeAllFragment.1
            @Override // com.slzhibo.library.p115ui.view.widget.smartrefreshlayout.layout.listener.OnRefreshListener
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                ((BaseFragment) HomeAllFragment.this).pageNum = 1;
                HomeAllFragment.this.sendRequest(false, true);
                refreshLayout.finishRefresh();
            }
        });
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$jC5iyaib9lag9Za8VOHxaVs4ohc
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                Toast.makeText(mActivity, "onItemLongClick999", Toast.LENGTH_SHORT).show();
                HomeAllFragment.this.lambda$initListener$1$HomeAllFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$OUpWCpZP9ivmyhCl_fvy3-vCm60
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                Toast.makeText(mActivity, "onItemLongClick22222", Toast.LENGTH_SHORT).show();
                return HomeAllFragment.this.lambda$initListener$2$HomeAllFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mRecyclerView.addOnScrollListener(new C66592());
    }

    public /* synthetic */ void lambda$initListener$0$HomeAllFragment() {
        this.pageNum = 1;
        sendRequest(true, true);
    }

    public /* synthetic */ void lambda$initListener$1$HomeAllFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AppUtils.startSLLiveActivityByHomeList(this.mContext, (LiveEntity) baseQuickAdapter.getItem(i), getString(R.string.fq_all_list));
    }

    public /* synthetic */ boolean lambda$initListener$2$HomeAllFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        PlayManager playManager;
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (!(liveEntity == null || !liveEntity.isCoverPreview() || (playManager = this.playManager) == null)) {
            Log.e("GGGG","$%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            Toast.makeText(getContext(), "playManager.playVideoByPosition", Toast.LENGTH_SHORT).show();


            Toast.makeText(getContext(), "全部playManager:214:"+"\n"+i+"\n"+ baseQuickAdapter.getHeaderLayoutCount(), Toast.LENGTH_SHORT).show();
            playManager.playVideoByPosition(i + baseQuickAdapter.getHeaderLayoutCount());
        }
        return true;
    }

    /* renamed from: com.slzhibo.library.ui.fragment.HomeAllFragment$2 */
    /* loaded from: classes6.dex */
    class C66592 extends RecyclerView.OnScrollListener {
        C66592() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                recyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$2$saUOl8FBVM9xBHAXUcHRmlelovQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeAllFragment.C66592.this.lambda$onScrollStateChanged$0$HomeAllFragment$2();
                    }
                }, 300L);
            }
            if (HomeAllFragment.this.isAutoPreLoadingMore(recyclerView)) {
                ((BaseFragment) HomeAllFragment.this).isLoadingMore = true;
                HomeAllFragment.access$308(HomeAllFragment.this);
                ((HomeAllPresenter) ((BaseFragment) HomeAllFragment.this).mPresenter).getLiveList(((BaseFragment) HomeAllFragment.this).mStateView, ((BaseFragment) HomeAllFragment.this).pageNum, false, false, HomeAllFragment.this.bindToLifecycle());
            }
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$HomeAllFragment$2() {
            if (HomeAllFragment.this.playManager != null) {
                HomeAllFragment.this.playManager.onScrolled();
                HomeAllFragment.this.playManager.onScrollStateChanged();
            }
        }
    }

    @Override // com.slzhibo.library.p115ui.view.iview.IHomeAllView
    public void onDataListSuccess(List<LiveEntity> list, final boolean z, boolean z2, boolean z3) {
        stopPlay();
        if (z3) {
            if (this.mRecyclerView.getItemDecorationCount() > 0) {
                this.mRecyclerView.removeItemDecoration(this.itemDecoration);
            }
            this.itemDecoration.setHasBanner(z);
            this.itemDecoration.setBannerSpanPosition(this.bannerSpanPosition);
            this.mRecyclerView.addItemDecoration(this.itemDecoration);
            this.mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$8tohl6ysIhtPbfPh_Zjt11hiP1Y
                @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.SpanSizeLookup
                public final int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                    return HomeAllFragment.this.lambda$onDataListSuccess$3$HomeAllFragment(z, gridLayoutManager, i);
                }
            });
            this.mAdapter.setNewData(list);
            this.mRecyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAllFragment$44rI6OwmzBqIRo6N7JEF_vjPMwM
                @Override // java.lang.Runnable
                public final void run() {
                    HomeAllFragment.this.lambda$onDataListSuccess$4$HomeAllFragment();
                }
            }, 800L);
        } else {
            this.isLoadingMore = false;
            Observable.just(AppUtils.removeDuplicateList(this.mAdapter.getData(), list)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new SimpleRxObserver<List<LiveEntity>>(this.mContext, false) { // from class: com.slzhibo.library.ui.fragment.HomeAllFragment.3
                public void accept(List<LiveEntity> list2) {
                    HomeAllFragment.this.mAdapter.addData((Collection) list2);
                }
            });
        }
        this.isNoMoreData = z2;
        AppUtils.updateRefreshLayoutFinishStatus(this.mSmartRefreshLayout, z2, z3);
    }

    public /* synthetic */ int lambda$onDataListSuccess$3$HomeAllFragment(boolean z, GridLayoutManager gridLayoutManager, int i) {
        return (!z || i != this.bannerSpanPosition) ? 1 : 2;
    }

    @Override // com.slzhibo.library.p115ui.view.iview.IHomeAllView
    public void onDataListFail(boolean z) {
        if (!z) {
            this.isLoadingMore = false;
            this.mSmartRefreshLayout.finishLoadMore();
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
        return getString(R.string.fq_all_list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRequest(boolean z, boolean z2) {
        if (z2) {
            SLLiveSDK.getSingleton().onAllLiveListUpdate(bindToLifecycle());
            ((HomeAllPresenter) this.mPresenter).getLiveListFirst(this.mStateView, this.pageNum, z, true, bindToLifecycle());
            return;
        }
        ((HomeAllPresenter) this.mPresenter).getLiveList(this.mStateView, this.pageNum, z, false, bindToLifecycle());
    }

    private void stopPlay() {
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.stopPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: resumePlay */
    public void lambda$onDataListSuccess$4$HomeAllFragment() {
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.onRecyclerViewResume();
        }
    }

    private void pausePlay() {
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.onRecyclerViewPause();
        }
    }
}