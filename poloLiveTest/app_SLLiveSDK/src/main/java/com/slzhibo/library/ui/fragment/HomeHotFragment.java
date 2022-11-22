package com.slzhibo.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.slzhibo.library.R;

import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.IndexRankEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveHelperAppConfigEntity;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.model.event.LabelMenuEvent;
import com.slzhibo.library.model.event.ListDataUpdateEvent;
import com.slzhibo.library.ui.activity.AnchorAuthResultActivity;
import com.slzhibo.library.ui.adapter.HomeLiveAllAdapter;
import com.slzhibo.library.ui.presenter.HomeHotPresenter;
import com.slzhibo.library.ui.view.dialog.AnchorAuthDialog;
import com.slzhibo.library.ui.view.dialog.WarnDialog;
import com.slzhibo.library.ui.view.divider.RVDividerLive;
import com.slzhibo.library.ui.view.headview.HomeHotHeadView;
import com.slzhibo.library.ui.view.iview.IHomeHotView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.ui.view.widget.rxbinding2.view.RxView;



import com.slzhibo.library.utils.AnimUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.live.PlayManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

import com.blankj.utilcode.util.SPUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;


/**
 * 推荐
 */
public class HomeHotFragment extends BaseFragment<HomeHotPresenter> implements IHomeHotView {

    private HomeHotHeadView headView;
    private ImageView ivStartLive;
    private HomeLiveAllAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mScrollThreshold;
    private SmartRefreshLayout mSmartRefreshLayout;
    private PlayManager playManager;
    private final List<LiveEntity> mListData = new ArrayList();
    private boolean isRouterFlag = false;

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

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onLiveHelperAppConfigFail() {
    }

    static /* synthetic */ int access$608(HomeHotFragment homeHotFragment) {
        Log.e("HomeHotFragment：","access$608");
        int i = homeHotFragment.pageNum;
        homeHotFragment.pageNum = i + 1;
        return i;
    }

    public static HomeHotFragment newInstance() {
        Log.e("HomeHotFragment：","newInstance");
        Bundle bundle = new Bundle();
        HomeHotFragment homeHotFragment = new HomeHotFragment();
        homeHotFragment.setArguments(bundle);
        return homeHotFragment;
    }

    public static HomeHotFragment newInstance(boolean z) {
//        Log.e("HomeHotFragment：","newInstance2");
        Bundle bundle = new Bundle();
        HomeHotFragment homeHotFragment = new HomeHotFragment();
        bundle.putBoolean(ConstantUtils.RESULT_FLAG, z);
        homeHotFragment.setArguments(bundle);
        return homeHotFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.base.BaseFragment
    public HomeHotPresenter createPresenter() {
//        Log.e("HomeHotFragment：","createPresenter");
        return new HomeHotPresenter(this.mContext, this);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
//        Log.e("HomeHotFragment：","getBundle");
        Log.e("HomeHotFragment：getBundle", String.valueOf(bundle.getBoolean(ConstantUtils.RESULT_FLAG, false)));
        this.isRouterFlag = bundle.getBoolean(ConstantUtils.RESULT_FLAG, false);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public int getLayoutId() {
//        Log.e("HomeHotFragment：","getLayoutId");
        return R.layout.fq_fragment_home_hot;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public View injectStateView(View view) {
        Log.e("HomeHotFragment：","injectStateView");
        return view.findViewById(R.id.fl_content_view);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initView(View view, @Nullable Bundle bundle) {
        Log.e("HomeHotFragment：","initView");
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.ivStartLive = (ImageView) view.findViewById(R.id.fab);
        this.playManager = new PlayManager(this.mContext);
        ((HomeHotPresenter) this.mPresenter).getAllLiveList(bindToLifecycle());
        this.mScrollThreshold = getResources().getDimensionPixelOffset(R.dimen.fq_fab_scroll_threshold);
        initAdapter();
        if (this.isRouterFlag) {
            sendRequest(true);
            this.ivStartLive.setVisibility(View.INVISIBLE);
        }
        this.ivStartLive.setImageResource(R.drawable.fq_ic_add_live_new);
    }

    @Override // com.slzhibo.library.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
//        Log.e("HomeHotFragment：","setUserVisibleHint");
        if (z) {
            Log.e("HomeHotFragment：","setUserVisibleHint     111111");
            lambda$onDataListSuccess$4$HomeHotFragment();
        } else {
//            Log.e("HomeHotFragment：","setUserVisibleHint      2222222");
            pausePlay();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
//        Log.e("HomeHotFragment：","onResume");
        if (getUserVisibleHint()) {
            lambda$onDataListSuccess$4$HomeHotFragment();
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

    private void initAdapter() {
        Log.e("HomeHotFragment：","initAdapter");
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.headView = new HomeHotHeadView(this.mContext);
        this.mAdapter = new HomeLiveAllAdapter(this.mListData);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite, true, true));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.headView);
        this.mAdapter.setEmptyView(R.layout.fq_layout_empty_view_warp, this.mRecyclerView);
        this.mAdapter.setHeaderAndEmpty(true);
        AnimUtils.playLiveScaleAnim(this.ivStartLive);
        this.playManager.initRecyclerViewPlayManager(this.mRecyclerView);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initListener(View view) {
        super.initListener(view);
        Log.e("HomeHotFragment：","initListener");
        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$orYdTyTBVli0U3MpulIjVB4PRhY
            @Override // com.slzhibo.library.ui.view.widget.StateView.OnRetryClickListener
            public final void onRetryClick() {
                HomeHotFragment.this.lambda$initListener$0$HomeHotFragment();
            }
        });
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$jKvJup8c-fnfMHpqoBMRmfD1mXo
            @Override // com.slzhibo.library.ui.view.widget.smartrefreshlayout.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HomeHotFragment.this.lambda$initListener$1$HomeHotFragment(refreshLayout);
            }
        });
        RxView.clicks(this.ivStartLive).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new SimpleRxObserver<Object>() { // from class: com.slzhibo.library.ui.fragment.HomeHotFragment.1
            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                if (AppUtils.isLogin(( HomeHotFragment.this).mContext)) {
                    ((HomeHotPresenter) ( HomeHotFragment.this).mPresenter).onAnchorAuth();
                }
            }
        });
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$Xpbn1airQwFDE0WQo76GEvnXrIA
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                HomeHotFragment.this.lambda$initListener$2$HomeHotFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$vAC9IqRgZBIjfHsoOLPJIiDHWH4
            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                return HomeHotFragment.this.lambda$initListener$3$HomeHotFragment(baseQuickAdapter, view2, i);
            }
        });
        this.mRecyclerView.addOnScrollListener(new AnonymousClass2());
    }

    public /* synthetic */ void lambda$initListener$0$HomeHotFragment() {
        Log.e("HomeHotPresenter：","lambda$initListener$0$HomeHotFragment");
        if (!SPUtils.getInstance().getBoolean(ConstantUtils.LIVE_LABEL_MENU, true)) {
            EventBus.getDefault().post(new LabelMenuEvent());
        } else {
            sendRequest(true);
        }
    }

    public /* synthetic */ void lambda$initListener$1$HomeHotFragment(RefreshLayout refreshLayout) {
        Log.e("HomeHotFragment：","lambda$initListener$1$HomeHotFragment");
        if (!this.isRouterFlag) {
            this.ivStartLive.setVisibility(View.VISIBLE);
        }
        if (!SPUtils.getInstance().getBoolean(ConstantUtils.LIVE_LABEL_MENU, true)) {
            EventBus.getDefault().post(new LabelMenuEvent());
            refreshLayout.finishRefresh();
            return;
        }
        refreshLayout.resetNoMoreData();
        ((HomeHotPresenter) this.mPresenter).getAllLiveList(bindToLifecycle());
        sendRequest(false);
        refreshLayout.finishRefresh();
    }

    public /* synthetic */ void lambda$initListener$2$HomeHotFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Log.e("HomeHotFragment：","lambda$initListener$2$HomeHotFragment");
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (liveEntity != null) {
            if (liveEntity.isAd) {
                AppUtils.onLiveListClickAdEvent(this.mContext, liveEntity);
            } else if (liveEntity.isToLiveRoomByItemType()) {
                AppUtils.startSLLiveActivity(this.mContext, liveEntity, "1", getString(R.string.fq_hot_list));
            }
        }
    }

    public /* synthetic */ boolean lambda$initListener$3$HomeHotFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Log.e("HomeHotFragment：","lambda$initListener$3$HomeHotFragment");
        PlayManager playManager;
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (!(liveEntity == null || !liveEntity.isCoverPreview() || (playManager = this.playManager) == null)) {
            playManager.playVideoByPosition(i + baseQuickAdapter.getHeaderLayoutCount());
        }
        return true;
    }

    @Override
    public void onResultError(int i) {
        Log.e("HomeHotFragment：","onResultError");
    }

    /* renamed from: com.slzhibo.library.ui.fragment.HomeHotFragment$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends RecyclerView.OnScrollListener {
        AnonymousClass2() {
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            Log.e("HomeHotFragment：AnonymousClass2","onScrolled");
            if (!HomeHotFragment.this.isRouterFlag) {
                if (Math.abs(i2) > HomeHotFragment.this.mScrollThreshold) {
                    if (i2 > 0) {
                        HomeHotFragment.this.ivStartLive.setVisibility(View.INVISIBLE);
                    } else {
                        HomeHotFragment.this.ivStartLive.setVisibility(View.VISIBLE);
                    }
                }
            }
            super.onScrolled(recyclerView, i, i2);
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Log.e("HomeHotFragment：AnonymousClass2","onScrollStateChanged");
            if (i == 0) {
                recyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$2$a8sWZ0BwAtv3PtiRmLMw-jjeQ7s
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeHotFragment.AnonymousClass2.this.lambda$onScrollStateChanged$0$HomeHotFragment$2();
                    }
                }, 500L);
            }
            if (HomeHotFragment.this.isAutoPreLoadingMore(recyclerView)) {
                ( HomeHotFragment.this).isLoadingMore = true;
                HomeHotFragment.access$608(HomeHotFragment.this);
                ((HomeHotPresenter) ( HomeHotFragment.this).mPresenter).getLiveList(( HomeHotFragment.this).mStateView, ( HomeHotFragment.this).pageNum, false, false, HomeHotFragment.this.bindToLifecycle());
            }
            super.onScrollStateChanged(recyclerView, i);
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$HomeHotFragment$2() {
            Log.e("HomeHotFragment：AnonymousClass2","lambda$onScrollStateChanged$0$HomeHotFragment$2");
            if (HomeHotFragment.this.playManager != null) {
                HomeHotFragment.this.playManager.onScrolled();
                HomeHotFragment.this.playManager.onScrollStateChanged();
            }
        }
    }

    private void sendRequest(boolean z) {
        Log.e("HomeHotPresenter：","HomeHotPresenter");
        this.pageNum = 1;
        ((HomeHotPresenter) this.mPresenter).getLiveList(this.mStateView, this.pageNum, z, true, bindToLifecycle());
        ((HomeHotPresenter) this.mPresenter).getBannerList("1");
        ((HomeHotPresenter) this.mPresenter).getTopList();
        CacheUtils.updateCacheVersion();
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onAnchorAuthSuccess(AnchorEntity anchorEntity) {
        Log.e("HomeHotFragment：","onAnchorAuthSuccess");
        if (anchorEntity != null) {
            int i = anchorEntity.isChecked;
            if (i == -2) {
                AnchorAuthDialog.newInstance().show(getChildFragmentManager());
            } else if (i == -1 || i == 0) {
                Intent intent = new Intent(this.mContext, AnchorAuthResultActivity.class);
                intent.putExtra(ConstantUtils.AUTH_TYPE, anchorEntity.isChecked);
                startActivity(intent);
            } else if (i == 1) {
                if (anchorEntity.isFrozenFlag()) {
                    WarnDialog.newInstance("FROZEN_TIP").show(getChildFragmentManager());
                } else {
                    ((HomeHotPresenter) this.mPresenter).getLiveHelperAppConfig();
                }
            }
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        Log.e("HomeHotFragment：","onDataListSuccess");
        stopPlay();
        if (z2) {
            this.mRecyclerView.postDelayed(new Runnable() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeHotFragment$vn1xgAXTz6bX0-C8-MEIX6l8yyc
                @Override // java.lang.Runnable
                public final void run() {
                    HomeHotFragment.this.lambda$onDataListSuccess$4$HomeHotFragment();
                }
            }, 800L);
            this.mAdapter.setNewData(list);
        } else {
            this.isLoadingMore = false;
            Observable.just(AppUtils.removeDuplicateList(this.mAdapter.getData(), list)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new SimpleRxObserver<List<LiveEntity>>(this.mContext, false) { // from class: com.slzhibo.library.ui.fragment.HomeHotFragment.3
                public void accept(List<LiveEntity> list2) {
                    HomeHotFragment.this.mAdapter.addData((Collection) list2);
                }
            });
        }
        this.isNoMoreData = z;
        AppUtils.updateRefreshLayoutFinishStatus(this.mSmartRefreshLayout, z, z2);
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onDataListFail(boolean z) {
        Log.e("HomeHotFragment：","HomeHotPresenter");
        if (!z) {
            this.isLoadingMore = false;
            this.mSmartRefreshLayout.finishLoadMore();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onAutoRefreshData() {
        super.onAutoRefreshData();
        Log.e("HomeHotFragment：","HomeHotPresenter");
        this.pageNum = 1;
        ((HomeHotPresenter) this.mPresenter).getLiveList(this.mStateView, this.pageNum, false, true, bindToLifecycle());
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onBannerListSuccess(List<BannerEntity> list) {
        Log.e("HomeHotPresenter：","onBannerListSuccess");
        HomeHotHeadView homeHotHeadView = this.headView;
        if (homeHotHeadView != null) {
            homeHotHeadView.initBannerImages(list);
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onTopListSuccess(List<IndexRankEntity> list) {
        Log.e("HomeHotFragment：","onTopListSuccess");
        HomeHotHeadView homeHotHeadView = this.headView;
        if (homeHotHeadView != null) {
            homeHotHeadView.initTopList(list);
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeHotView
    public void onLiveHelperAppConfigSuccess(LiveHelperAppConfigEntity liveHelperAppConfigEntity) {
        Log.e("HomeHotFragment：","onLiveHelperAppConfigSuccess");
        if (liveHelperAppConfigEntity == null) {
            showToast(R.string.fq_start_live_helper_error_tips);
        } else if (TextUtils.isEmpty(liveHelperAppConfigEntity.androidPackageName)) {
            showToast(R.string.fq_start_live_helper_error_tips);
        } else {
            AppUtils.toLiveHelperApp(this.mContext, liveHelperAppConfigEntity.androidPackageName, liveHelperAppConfigEntity.startLiveAppDownloadUrl, getChildFragmentManager());
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        Log.e("HomeHotFragment：","onEventMainThreadSticky");
        super.onEventMainThreadSticky(baseEvent);
        if (!(baseEvent instanceof ListDataUpdateEvent)) {
            return;
        }
        if (!((ListDataUpdateEvent) baseEvent).isAutoRefresh) {
            sendRequest(true);
        } else if (getUserVisibleHint()) {
            onAutoRefreshData();
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public String getPageStayTimerType() {
        Log.e("HomeHotFragment：","getPageStayTimerType");
        return getString(R.string.fq_hot_list);
    }

    private void stopPlay() {
        Log.e("HomeHotFragment：","stopPlay");
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.stopPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: resumePlay */
    public void lambda$onDataListSuccess$4$HomeHotFragment() {
//        Log.e("HomeHotFragment：","lambda$onDataListSuccess$4$HomeHotFragment");
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.onRecyclerViewResume();
        }
    }

    private void pausePlay() {
//        Log.e("HomeHotFragment：","pausePlay");
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.onRecyclerViewPause();
        }
    }







//    /* renamed from: b */
//    public SmartRefreshLayout f5760b;
//
//    /* renamed from: c */
//    public RecyclerView f5761c;
//
//    /* renamed from: d */
//    public ImageView f5762d;
//
//    /* renamed from: e */
//    public HomeHotHeadView f5763e;
//
//    /* renamed from: f */
//    public HomeLiveAllAdapter f5764f;
//
//    /* renamed from: h */
//    public PlayManager f5766h;
//
//    /* renamed from: i */
//    public int f5767i;
//
//    /* renamed from: g */
//    public List<LiveEntity> f5765g = new ArrayList();
//
//    /* renamed from: j */
//    public boolean f5768j = false;
//
//    @Override
//    public HomeHotPresenter createPresenter() {
//        return new HomeHotPresenter(this.mContext, this);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fq_fragment_home_hot;
//    }
//
//    @Override
//    public void initView(View view, @Nullable Bundle bundle) {
//        this.f5760b = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
//        this.f5761c = (RecyclerView) view.findViewById(R.id.recycler_view);
//        this.f5762d = (ImageView) view.findViewById(R.id.fab);
//        this.f5766h = new PlayManager(this.mContext);
//        ((HomeHotPresenter) this.mPresenter).m4170a(bindToLifecycle());
//        this.f5767i = getResources().getDimensionPixelOffset(R.dimen.fq_fab_scroll_threshold);
//        m16790q();
//        if (this.f5768j) {
//            m16799c(true);
//            this.f5762d.setVisibility(View.INVISIBLE);
//        }
//        this.f5762d.setImageResource(R.drawable.fq_ic_add_live_new);
//    }
//
//
//    /* renamed from: q */
//
//    /**
//     * initAdapter
//     */
//    public final void m16790q() {
//        ((DefaultItemAnimator) this.f5761c.getItemAnimator()).setSupportsChangeAnimations(false);
//        this.f5763e = new HomeHotHeadView(this.mContext);
//        this.f5764f = new HomeLiveAllAdapter(this.f5765g);
//        this.f5761c.setLayoutManager(new GridLayoutManager(this.mContext, 2));
//        this.f5761c.setHasFixedSize(true);
//        this.f5761c.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite, true, true));
//        this.f5761c.setAdapter(this.f5764f);
//        this.f5764f.bindToRecyclerView(this.f5761c);
//        this.f5764f.addHeaderView(this.f5763e);
//        this.f5764f.setEmptyView(R.layout.fq_layout_empty_view_warp, this.f5761c);
//        this.f5764f.setHeaderAndEmpty(true);
//        AnimUtils.m1590b(this.f5762d);
//        this.f5766h.initRecyclerViewPlayManager(this.f5761c);
//    }
//
//    /* renamed from: c */
//    public final void m16799c(boolean z) {
//        this.pageNum = 1;
//        ((HomeHotPresenter) this.mPresenter).m4171a(this.mStateView, this.pageNum, z, true, bindToLifecycle());
//        ((HomeHotPresenter) this.mPresenter).m4167a("1");
//        ((HomeHotPresenter) this.mPresenter).m4156j();
//        CacheUtils.updateCacheVersion();
//    }
//
//
//    @Override
//    public void onResultError(int i) {
//
//    }
//
//    /**
//     * onAnchorAuthSuccess
//     * @param anchorEntity
//     */
//    @Override
//    public void mo2978a(AnchorEntity anchorEntity) {
//
//        if(anchorEntity == null){
//            Log.e("HomeHotFragment:","onAnchorAuthSuccess == null");
//        }
//        if (anchorEntity != null) {
//            int i = anchorEntity.isChecked;
//            if (i == -2) {
//                AnchorAuthDialog.newInstance().show(getChildFragmentManager());
//            } else if (i == -1 || i == 0) {
//                Intent intent = new Intent(this.mContext, AnchorAuthResultActivity.class);
//                intent.putExtra(ConstantUtils.AUTH_TYPE, anchorEntity.isChecked);
//                startActivity(intent);
//            } else if (i == 1) {
//                if (anchorEntity.isFrozenFlag()) {
//                    WarnDialog.m14685a("FROZEN_TIP").show(getChildFragmentManager());
//                } else {
//                    ((HomeHotPresenter) this.mPresenter).m4158i();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void mo2977a(LiveHelperAppConfigEntity liveHelperAppConfigEntity) {
//        if (liveHelperAppConfigEntity == null) {
//            showToast(R.string.fq_start_live_helper_error_tips);
//            Log.e("Home_hotFragmet::","开播APP参数获取失败，请联系管理员");
//        } else if (TextUtils.isEmpty(liveHelperAppConfigEntity.androidPackageName)) {
//            showToast(R.string.fq_start_live_helper_error_tips);
//            Log.e("Home_hotFragmet::","开播APP参数获取失败，请联系管理员");
//        } else {
//            Log.e("Home_hotFragmet::","liveHelperAppConfigEntity"+liveHelperAppConfigEntity.startLiveAppDownloadUrl);
//            AppUtils.toLiveHelperApp(this.mContext, liveHelperAppConfigEntity.androidPackageName, liveHelperAppConfigEntity.startLiveAppDownloadUrl, getChildFragmentManager());
//        }
//    }
//
//    /**
//     * onDataListSuccess
//     * @param list
//     * @param z
//     * @param z2
//     */
//    @Override
//    public void mo2976a(List<LiveEntity> list, boolean z, boolean z2) {
//        m16807A();
//        if (z2) {
//            this.f5761c.postDelayed(new Runnable() { // from class: e.t.a.h.c.u
//                @Override // java.lang.Runnable
//                public final void run() {
//                    HomeHotFragment.this.m16788t();
//                }
//            }, 800L);
//            this.f5764f.setNewData(list);
//        } else {
//            this.isLoadingMore = false;
//            Observable.just(AppUtils.removeDuplicateList(this.f5764f.getData(), list)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose( bindToLifecycle()).subscribe( new C2263c(this.mContext, false));
//        }
//        this.isNoMoreData = z;
//        AppUtils.updateRefreshLayoutFinishStatus(this.f5760b, z, z2);
//    }
//
//    /* renamed from: A */
//    public final void m16807A() {
//        PlayManager lVar = this.f5766h;
//        if (lVar != null) {
//            lVar.stopPlayByRecyclerMode();
//        }
//    }
//
//    /* renamed from: x */
//    public final void m16788t() {
//        PlayManager lVar = this.f5766h;
//        if (lVar != null) {
//            lVar.onRecyclerViewResume();
//        }
//    }
//
//    /* renamed from: com.slzhibo.library.ui.fragment.HomeHotFragment$c */
//    /* loaded from: classes2.dex */
//    public class C2263c extends SimpleRxObserver<List<LiveEntity>> {
//        public C2263c(Context context, boolean z) {
//            super(context, z);
//        }
//
//        public void accept(List<LiveEntity> list) {
//            Log.e("YYYYYYYYYYYYYYYYYYYY::", String.valueOf(list.size()));
//            HomeHotFragment.this.f5764f.addData((Collection) list);
//        }
//    }
//
//
//    /**
//     * onDataListFail
//     * @param z
//     */
//    @Override
//    public void mo2975a(boolean z) {
//        if (!z) {
//            this.isLoadingMore = false;
//            this.f5760b.finishLoadMore();
//        }
//    }
//    /**
//     * onLiveHelperAppConfigFail
//     */
//    @Override
//    public void mo2974e() {
//    }
//    /**
//     * onBannerListSuccess
//     * @param list
//     */
//    @Override
//    public void mo2973o(List<BannerEntity> list) {
//        HomeHotHeadView homeHotHeadView = this.f5763e;
//        if(list == null || list.size() ==0){
//            Log.e("HomeHotFragment:","onBannerListSuccess =list= null");
//        }
//        if (homeHotHeadView != null) {
//            homeHotHeadView.m14542b(list);
//        }
//    }
//    /**
//     * onTopListSuccess
//     * @param list
//     */
//    @Override
//    public void mo2972p(List<IndexRankEntity> list) {
//        if(list == null || list.size() ==0){
//            Log.e("HomeHotFragment:","onTopListSuccess =list= null");
//        }
//        HomeHotHeadView homeHotHeadView = this.f5763e;
//        if (homeHotHeadView != null) {
//            homeHotHeadView.m14540c(list);
//        }
//    }
//
//
//
//
//
//
//
//    public static HomeHotFragment newInstance(boolean z) {
//        Bundle bundle = new Bundle();
//        HomeHotFragment homeHotFragment = new HomeHotFragment();
//        bundle.putBoolean(ConstantUtils.RESULT_FLAG, z);
//        homeHotFragment.setArguments(bundle);
//        return homeHotFragment;
//    }
//
//
//
//    @Override // com.slzhibo.library.base.BaseFragment, android.support.p001v4.app.Fragment
//    public void setUserVisibleHint(boolean z) {
//        super.setUserVisibleHint(z);
//        if (z) {
//            m16788t();
//        } else {
//            m16786w();
//        }
//    }
//    /* renamed from: w */
//    public final void m16786w() {
//        PlayManager lVar = this.f5766h;
//        if (lVar != null) {
//            lVar.onRecyclerViewPause();
//        }
//    }
//
//
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
//    public void onResume() {
//        super.onResume();
//        if (getUserVisibleHint()) {
//            m16788t();
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
//    public void onPause() {
//        super.onPause();
//        m16786w();
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void onEventMainThreadSticky(BaseEvent baseEvent) {
//        super.onEventMainThreadSticky(baseEvent);
//        if (!(baseEvent instanceof ListDataUpdateEvent)) {
//            return;
//        }
//        if (!((ListDataUpdateEvent) baseEvent).isAutoRefresh) {
//            m16799c(true);
//        } else if (getUserVisibleHint()) {
//            onAutoRefreshData();
//        }
//    }
//
//
//
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
//    public void onDestroy() {
//        super.onDestroy();
//        m16787v();
//    }
//    /* renamed from: v */
//    public final void m16787v() {
//    }
//
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void onAutoRefreshData() {
//        super.onAutoRefreshData();
//        this.pageNum = 1;
//        ((HomeHotPresenter) this.mPresenter).m4171a(this.mStateView, this.pageNum, false, true, bindToLifecycle());
//    }
//
//
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public View injectStateView(View view) {
//        return view.findViewById(R.id.fl_content_view);
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public boolean isAutoRefreshDataEnable() {
//        return true;
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public boolean isEnablePageStayReport() {
//        return true;
//    }
//
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void initListener(View view) {
//        super.initListener(view);
//        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() { // from class: e.t.a.h.c.s
//            @Override // com.slzhibo.library.p018ui.view.widget.StateView.AbstractC2856e
//            /* renamed from: a */
//            public final void onRetryClick() {
//                HomeHotFragment.this.m16789r();
//            }
//        });
//        this.f5760b.setOnRefreshListener(new OnRefreshListener() { // from class: e.t.a.h.c.r
//            @Override // com.slzhibo.library.p018ui.view.widget.smartrefreshlayout.layout.listener.OnRefreshListener
//            public final void onRefresh(RefreshLayout refreshLayout) {
//                HomeHotFragment.this.m16804a(refreshLayout);
//            }
//        });
//        RxView.clicks(this.f5762d).throttleFirst(2L, TimeUnit.SECONDS).subscribe( new C2261a());
//        this.f5764f.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: e.t.a.h.c.q
//            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
//            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
//                HomeHotFragment.this.m16803a(baseQuickAdapter, view2, i);
//            }
//        });
//        this.f5764f.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: e.t.a.h.c.t
//            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemLongClickListener
//            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
//                return HomeHotFragment.this.m16801b(baseQuickAdapter, view2, i);
//            }
//        });
//        this.f5761c.addOnScrollListener(new C2262b());
//    }
//
//    /* renamed from: r */
//    public /* synthetic */ void m16789r() {
//        if (!SPUtils.getInstance().getBoolean(ConstantUtils.LIVE_LABEL_MENU, true)) {
////            EventBus.m309d().m320a(new LabelMenuEvent());
//            EventBus.getDefault().post(new LabelMenuEvent());
//        } else {
//            m16799c(true);
//        }
//    }
//
//    /* renamed from: a */
//    public /* synthetic */ void m16804a(RefreshLayout refreshLayout) {
//        if (!this.f5768j) {
//            this.f5762d.setVisibility(View.VISIBLE);
//        }
////        m10252a().m10244a
////        m10252a().m10244a==.getInstance().getBoolean
//        if (!SPUtils.getInstance().getBoolean(ConstantUtils.LIVE_LABEL_MENU, true)) {
////            EventBus.m309d().m320a(new LabelMenuEvent());
//            EventBus.getDefault().post(new LabelMenuEvent());
//            refreshLayout.finishRefresh();
//            return;
//        }
//        refreshLayout.resetNoMoreData();
//        ((HomeHotPresenter) this.mPresenter).m4170a(bindToLifecycle());
//        m16799c(false);
//        refreshLayout.finishRefresh();
//    }
//
//    /* renamed from: a */
//    public /* synthetic */ void m16803a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
//        if (liveEntity != null) {
//            if (liveEntity.isAd) {
//                AppUtils.onLiveListClickAdEvent(this.mContext, liveEntity);
//            } else if (liveEntity.isToLiveRoomByItemType()) {
//                AppUtils.startSLLiveActivity(this.mContext, liveEntity, "1", getString(R.string.fq_hot_list));
//            }
//        }
//    }
//
//    /* renamed from: b */
//    public /* synthetic */ boolean m16801b(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//        PlayManager lVar;
//        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
//        if (!(liveEntity == null || !liveEntity.isCoverPreview() || (lVar = this.f5766h) == null)) {
//            lVar.playVideoByPosition(i + baseQuickAdapter.getHeaderLayoutCount());
//        }
//        return true;
//    }
//
//
//
//
//
//    /* renamed from: com.slzhibo.library.ui.fragment.HomeHotFragment$a */
//    /* loaded from: classes2.dex */
//    public class C2261a extends SimpleRxObserver<Object> {
//        public C2261a() {
//        }
//
//        @Override // com.slzhibo.library.utils.live.SimpleRxObserver
//        public void accept(Object obj) {
//            Log.e("YYYYYYYYYYYYYYYYYYYY::", String.valueOf(obj.toString()));
//            if (AppUtils.isLogin(HomeHotFragment.this.mContext)) {
//
//                ((HomeHotPresenter) HomeHotFragment.this.mPresenter).m4154k();
//            }
//        }
//    }
//
//
//    /* renamed from: com.slzhibo.library.ui.fragment.HomeHotFragment$b */
//    /* loaded from: classes2.dex */
//    public class C2262b extends RecyclerView.OnScrollListener {
//        public C2262b() {
//        }
//
//        /* renamed from: a */
//        public /* synthetic */ void m16784a() {
//            if (HomeHotFragment.this.f5766h != null) {
//                HomeHotFragment.this.f5766h.onScrolled();
//                HomeHotFragment.this.f5766h.onScrollStateChanged();
//            }
//        }
//
//        @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
//        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
//            if (i == 0) {
//                recyclerView.postDelayed(new Runnable() { // from class: e.t.a.h.c.p
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        HomeHotFragment.C2262b.this.m16784a();
//                    }
//                }, 500L);
//            }
//            if (HomeHotFragment.this.isAutoPreLoadingMore(recyclerView)) {
//                HomeHotFragment.this.isLoadingMore = true;
//                HomeHotFragment.m16794h(HomeHotFragment.this);
//                ((HomeHotPresenter) HomeHotFragment.this.mPresenter).m4171a(HomeHotFragment.this.mStateView, HomeHotFragment.this.pageNum, false, false, HomeHotFragment.this.bindToLifecycle());
//            }
//            super.onScrollStateChanged(recyclerView, i);
//        }
//
//        @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
//        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//            if (!HomeHotFragment.this.f5768j) {
//                if (Math.abs(i2) > HomeHotFragment.this.f5767i) {
//                    if (i2 > 0) {
//                        HomeHotFragment.this.f5762d.setVisibility(View.INVISIBLE);
//                    } else {
//                        HomeHotFragment.this.f5762d.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//            super.onScrolled(recyclerView, i, i2);
//        }
//    }
//
//    /* renamed from: h */
//    public static /* synthetic */ int m16794h(HomeHotFragment homeHotFragment) {
//        int i = homeHotFragment.pageNum;
//        homeHotFragment.pageNum = i + 1;
//        return i;
//    }
//
//
//
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public String getPageStayTimerType() {
//        return getString(R.string.fq_hot_list);
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void getBundle(Bundle bundle) {
//        super.getBundle(bundle);
//        this.f5768j = bundle.getBoolean(ConstantUtils.RESULT_FLAG, false);
//    }

}