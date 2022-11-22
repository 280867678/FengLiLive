package com.slzhibo.library.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
//import com.slzhibo.library.R$color;
//import com.slzhibo.library.R$id;
//import com.slzhibo.library.R$layout;
//import com.slzhibo.library.R$string;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.slzhibo.library.R;
import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.event.AttentionEvent;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.model.event.ListDataUpdateEvent;
import com.slzhibo.library.model.event.LoginEvent;
import com.slzhibo.library.model.event.LogoutEvent;
import com.slzhibo.library.ui.activity.shelf.HJProductBuyDetailActivity;
import com.slzhibo.library.ui.activity.shelf.HJProductDynamicActivity;
import com.slzhibo.library.ui.adapter.HomeLiveAllAdapter;
import com.slzhibo.library.ui.adapter.RecommendAdapter;
import com.slzhibo.library.ui.presenter.HomeAttentionPresenter;
import com.slzhibo.library.ui.view.divider.RVDividerLiveAll;
import com.slzhibo.library.ui.view.divider.RVDividerRecommendGrid;
import com.slzhibo.library.ui.view.emptyview.AttentionEmptyView;
import com.slzhibo.library.ui.view.emptyview.RecyclerEmptyView;
import com.slzhibo.library.ui.view.headview.RecommendHeadView;
import com.slzhibo.library.ui.view.iview.IHomeAttentionView;
import com.slzhibo.library.ui.view.widget.StateView;

import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.LogEventUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 关注
 */
public class HomeAttentionFragment extends BaseFragment<HomeAttentionPresenter> implements IHomeAttentionView {
    private boolean isRouterFlag = false;
    private RVDividerLiveAll itemDecoration;
    private HomeLiveAllAdapter mAttentionAdapter;
    private AttentionEmptyView mAttentionEmptyView;
    private final List<LiveEntity> mListData = new ArrayList();
    private RecommendAdapter mRecommendAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewRecommend;
    private SmartRefreshLayout mSmartRefreshLayout;

    static /* synthetic */ int lambda$onAttentionListSuccess$4(boolean z, int i, GridLayoutManager gridLayoutManager, int i2) {
        return (!z || i2 != i) ? 1 : 2;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public boolean isEnablePageStayReport() {
        return true;
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeAttentionView
    public void onAttentionSuccess() {
    }

    static /* synthetic */ int access$008(HomeAttentionFragment homeAttentionFragment) {
        int i = homeAttentionFragment.pageNum;
        homeAttentionFragment.pageNum = i + 1;
        return i;
    }

    public static HomeAttentionFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeAttentionFragment homeAttentionFragment = new HomeAttentionFragment();
        homeAttentionFragment.setArguments(bundle);
        return homeAttentionFragment;
    }

    public static HomeAttentionFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        HomeAttentionFragment homeAttentionFragment = new HomeAttentionFragment();
        bundle.putBoolean(ConstantUtils.RESULT_FLAG, z);
        homeAttentionFragment.setArguments(bundle);
        return homeAttentionFragment;
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.base.BaseFragment
    public HomeAttentionPresenter createPresenter() {
        return new HomeAttentionPresenter(this.mContext, this);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.isRouterFlag = bundle.getBoolean(ConstantUtils.RESULT_FLAG, false);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public int getLayoutId() {
        return R.layout.fq_fragment_home_attention;
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.base.BaseFragment
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.mRecyclerViewRecommend = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        initOpenAdapter();
        initRecommendAdapter();
        if (this.isRouterFlag) {
            sendRequest(true, true);
        }
    }

    private void initOpenAdapter() {
        this.mAttentionAdapter = new HomeLiveAllAdapter(this.mListData);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.mAttentionAdapter);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mAttentionAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAttentionAdapter.setEmptyView(new RecyclerEmptyView(this.mContext));
    }

    private void initRecommendAdapter() {
        this.mAttentionEmptyView = new AttentionEmptyView(this.mContext);
        this.mRecyclerViewRecommend.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mRecyclerViewRecommend.addItemDecoration(new RVDividerRecommendGrid(this.mContext, R.color.fq_colorWhite));
        this.mRecommendAdapter = new RecommendAdapter(R.layout.fq_item_list_recommend);
        this.mRecyclerViewRecommend.setAdapter(this.mRecommendAdapter);
        this.mRecommendAdapter.bindToRecyclerView(this.mRecyclerViewRecommend);
        this.mRecommendAdapter.setEmptyView(this.mAttentionEmptyView);
        this.mRecommendAdapter.addHeaderView(new RecommendHeadView(this.mContext));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendRequest(boolean z, boolean z2) {
        if (AppUtils.isConsumptionPermissionUser()) {
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, z, z2, bindToLifecycle());
        } else {
            ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, z);
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initListener(View view) {
        super.initListener(view);
        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            /* class com.slzhibo.library.ui.fragment.$$Lambda$HomeAttentionFragment$ODGuME8GDjAXLCPnqvWpQL93VE */

            @Override // com.slzhibo.library.ui.view.widget.StateView.OnRetryClickListener
            public final void onRetryClick() {
                HomeAttentionFragment.this.lambda$initListener$0$HomeAttentionFragment();
            }
        });
        this.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            /* class com.slzhibo.library.ui.fragment.HomeAttentionFragment.AnonymousClass1 */

            @Override // com.slzhibo.library.ui.view.widget.smartrefreshlayout.layout.listener.OnLoadMoreListener
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HomeAttentionFragment.access$008(HomeAttentionFragment.this);
                HomeAttentionFragment.this.sendRequest(false, false);
            }

            @Override // com.slzhibo.library.ui.view.widget.smartrefreshlayout.layout.listener.OnRefreshListener
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                ( HomeAttentionFragment.this).pageNum = 1;
                HomeAttentionFragment.this.sendRequest(false, true);
                refreshLayout.finishRefresh();
            }
        });
        this.mAttentionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            /* class com.slzhibo.library.ui.fragment.$$Lambda$HomeAttentionFragment$3iE8uzq98EXW__HBeiOo3GsOeU */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeAttentionFragment.this.lambda$initListener$1$HomeAttentionFragment(baseQuickAdapter, view, i);
            }
        });
        this.mAttentionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            /* class com.slzhibo.library.ui.fragment.HomeAttentionFragment.AnonymousClass2 */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
                if (liveEntity != null) {
                    List<BannerEntity> list = liveEntity.bannerList;
                    int id = view.getId();
                    if (id == R.id.tv_dynamic_more) {
                        HomeAttentionFragment.this.startActivity(HJProductDynamicActivity.class);
                    } else if (id == R.id.tv_dynamic_content) {
                        HomeAttentionFragment.this.toHJProductBuyDetailActivity(list, 0);
                    } else if (id == R.id.tv_dynamic_content_2) {
                        HomeAttentionFragment.this.toHJProductBuyDetailActivity(list, 1);
                    }
                }
            }
        });
        this.mRecommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            /* class com.slzhibo.library.ui.fragment.$$Lambda$HomeAttentionFragment$h55xQGwN7YN8EOgcyp67HnMg0_c */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeAttentionFragment.this.lambda$initListener$2$HomeAttentionFragment(baseQuickAdapter, view, i);
            }
        });
        this.mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            /* class com.slzhibo.library.ui.fragment.$$Lambda$HomeAttentionFragment$Mr483ilT9IaCh6YrLVQkEnfknAg */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeAttentionFragment.this.lambda$initListener$3$HomeAttentionFragment(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$HomeAttentionFragment() {
        this.pageNum = 1;
        sendRequest(true, true);
    }

    public /* synthetic */ void lambda$initListener$1$HomeAttentionFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (liveEntity != null && liveEntity.isToLiveRoomByItemType()) {
            AppUtils.startSLLiveActivity(this.mContext, liveEntity, "2", getString(R.string.fq_attention_list));
        }
    }

    public /* synthetic */ void lambda$initListener$2$HomeAttentionFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnchorEntity anchorEntity;
        if (view.getId() == R.id.tv_attention && (anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i)) != null && AppUtils.isAttentionUser(this.mContext, anchorEntity.anchor_id)) {
            boolean z = !view.isSelected();
            view.setSelected(z);
            showToast(z ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
            ((HomeAttentionPresenter) this.mPresenter).attentionAnchor(anchorEntity.anchor_id, z ? 1 : 0);
            LogEventUtils.uploadFollow(anchorEntity.openId, anchorEntity.appId, getString(R.string.fq_home_attention), anchorEntity.expGrade, anchorEntity.nickname, getString(R.string.fq_attention_list), z, anchorEntity.liveId);
            DBUtils.attentionAnchor(anchorEntity.anchor_id, z);
        }
    }

    public /* synthetic */ void lambda$initListener$3$HomeAttentionFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
        if (anchorEntity != null) {
            AppUtils.startSLLiveActivity(this.mContext, AppUtils.formatLiveEntity(anchorEntity), "2", getString(R.string.fq_live_enter_source_attention_hot));
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeAttentionView
    public void onAttentionListSuccess(List<LiveEntity> list, final boolean z, boolean z2, boolean z3, final int i) {
        if (list == null || list.size() == 0) {
            this.mRecyclerView.setVisibility(View.INVISIBLE);
            this.mRecyclerViewRecommend.setVisibility(View.VISIBLE);
            ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, false);
            return;
        }
        this.mRecyclerView.setVisibility(View.VISIBLE);
        this.mRecyclerViewRecommend.setVisibility(View.INVISIBLE);
        this.mSmartRefreshLayout.setEnableLoadMore(true);
        if (z3) {
            if (this.itemDecoration == null) {
                this.itemDecoration = new RVDividerLiveAll(this.mContext, R.color.fq_colorWhite);
            }
            this.itemDecoration.setHasBanner(z);
            this.itemDecoration.setBannerSpanPosition(i);
            if (this.mRecyclerView.getItemDecorationCount() > 0) {
                this.mRecyclerView.removeItemDecoration(this.itemDecoration);
            }
            this.mRecyclerView.addItemDecoration(this.itemDecoration);
            this.mAttentionAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() { // from class: com.slzhibo.library.ui.fragment.-$$Lambda$HomeAttentionFragment$8UyH2Te7jv6lJjQU245G2jUlIiA
                @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.SpanSizeLookup
                public final int getSpanSize(GridLayoutManager gridLayoutManager, int i2) {
                    return HomeAttentionFragment.lambda$onAttentionListSuccess$4(z, i, gridLayoutManager, i2);
                }
            });
            this.mAttentionAdapter.setNewData(list);
        } else {
            this.mAttentionAdapter.addData((Collection) list);
        }
        AppUtils.updateRefreshLayoutFinishStatus(this.mSmartRefreshLayout, z2, z3);
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeAttentionView
    public void onAttentionListFail(boolean z) {
        if (!z) {
            this.mSmartRefreshLayout.finishLoadMore();
        }
        ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, false);
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeAttentionView
    public void onRecommendListSuccess(List<AnchorEntity> list) {
        this.mRecyclerView.setVisibility(View.INVISIBLE);
        boolean z = false;
        this.mRecyclerViewRecommend.setVisibility(View.VISIBLE);
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        if (list != null) {
            if (list.size() > 0) {
                z = true;
            }
            this.mAttentionEmptyView.hideRecommendTextView(!z);
            this.mRecommendAdapter.setNewData(list);
        }
    }

    @Override // com.slzhibo.library.ui.view.iview.IHomeAttentionView
    public void onRecommendListFail() {
        this.mRecyclerView.setVisibility(View.INVISIBLE);
        this.mRecyclerViewRecommend.setVisibility(View.VISIBLE);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof ListDataUpdateEvent) {
            if (!((ListDataUpdateEvent) baseEvent).isAutoRefresh) {
                sendRequest(true, true);
            } else if (getUserVisibleHint()) {
                sendRequest(true, true);
            }
        } else if (baseEvent instanceof AttentionEvent) {
            this.pageNum = 1;
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, true, true, bindToLifecycle());
        } else if (baseEvent instanceof LoginEvent) {
            this.pageNum = 1;
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, true, true, bindToLifecycle());
        } else if (baseEvent instanceof LogoutEvent) {
            this.mRecyclerView.setVisibility(View.INVISIBLE);
            this.mRecyclerViewRecommend.setVisibility(View.VISIBLE);
            this.mSmartRefreshLayout.setEnableLoadMore(false);
            this.pageNum = 1;
            ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, true);
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public String getPageStayTimerType() {
        return getString(R.string.fq_attention_list);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void toHJProductBuyDetailActivity(List<BannerEntity> list, int i) {
        String str;
        if (list != null && !list.isEmpty()) {
            if (i == 0) {
                str = list.get(0).id;
            } else {
                str = (i != 1 || list.size() < 2) ? "" : list.get(1).id;
            }
            if (!TextUtils.isEmpty(str)) {
                Intent intent = new Intent(this.mContext, HJProductBuyDetailActivity.class);
                intent.putExtra(ConstantUtils.RESULT_ID, str);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onResultError(int i) {

    }
}
