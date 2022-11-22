package com.slzhibo.library.ui.fragment;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;


import com.slzhibo.library.R;
import com.gyf.immersionbar.ImmersionBar;
import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.LabelEntity;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.model.event.LabelMenuEvent;
import com.slzhibo.library.model.event.ListDataUpdateEvent;
import com.slzhibo.library.ui.activity.SearchActivity;
import com.slzhibo.library.ui.view.dialog.LiveKickOutDialog;
import com.slzhibo.library.ui.view.widget.PagerTitleView.ScaleTransitionPagerTitleView;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.ui.presenter.HomePresenter;
import com.slzhibo.library.ui.view.dialog.alert.TokenInvalidDialog;
import com.slzhibo.library.ui.view.iview.IHomeView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.LogEventUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;
//import com.slzhibo.library.utils.immersionbar.ImmersionBar;
//import com.slzhibo.library.utils.live.H265CheckPlayManage;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import com.slzhibo.library.ui.adapter.HomeMenuTagAdapter;

/* renamed from: com.slzhibo.library.ui.fragment.LiveHomeFragment */
/* loaded from: classes10.dex */
public class LiveHomeFragment extends BaseFragment<HomePresenter> implements IHomeView{
    String TAG=this.getClass().getName();

    public CommonNavigator commonNavigator;
    public CommonNavigatorAdapter commonNavigatorAdapter;
    public ImageView ivSearch;
    public ViewPager mViewPager;
    public MagicIndicator magicIndicator;
    public HomeMenuTagAdapter menuTagAdapter;
    public MyKickOutBroadCastReceiver myKickOutBroadCastReceiver;
    public MyTokenInvalidBroadCastReceiver myTokenInvalidBroadCastReceiver;
    public List<BaseFragment> fragmentList = new ArrayList();
    public List<LabelEntity> labelList = new ArrayList();
    public boolean isEnableImmersionBar = true;

    /* renamed from: com.slzhibo.library.ui.fragment.LiveHomeFragment.a */
    /* loaded from: classes2.dex */
    public class C2267a extends CommonNavigatorAdapter {

        public C2267a() {
        }

        /* renamed from: a */
        public /* synthetic */ void m16756a(int i, View view) {
            LiveHomeFragment.this.mViewPager.setCurrentItem(i, false);
        }


        @Override
        public int getCount() {
            if (LiveHomeFragment.this.fragmentList == null) {
                return 0;
            }
            return LiveHomeFragment.this.fragmentList.size();
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
//            Log.e(TAG,"C2267a  getIndicator");
            return LiveHomeFragment.this.getIPagerIndicator(context);
        }

        @Override
        public IPagerTitleView getTitleView(Context context, int i) {
//            Log.e(TAG,"C2267a  getTitleView");
            String str = ((LabelEntity) LiveHomeFragment.this.labelList.get(i)).name;
            ScaleTransitionPagerTitleView scaleTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
            scaleTransitionPagerTitleView.setText(str);
            scaleTransitionPagerTitleView.setTextSize(20.0f);
            scaleTransitionPagerTitleView.setNormalColor(LiveHomeFragment.this.getTabTextNormalColorRes());
            scaleTransitionPagerTitleView.setSelectedColor(LiveHomeFragment.this.getTabTextSelectedColorRes());
            if (TextUtils.equals(str, LiveHomeFragment.this.getString(R.string.fq_ly_interactive_tag))) {
                scaleTransitionPagerTitleView.setBackground(ContextCompat.getDrawable(LiveHomeFragment.this.mContext, R.drawable.fq_ic_ly_home_decorate));
            } else {
                scaleTransitionPagerTitleView.setBackground(null);
            }
            scaleTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.c.e0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveHomeFragment.C2267a.this.m16756a(i, view);
                }
            });
            return scaleTransitionPagerTitleView;
        }
    }

    /* renamed from: com.slzhibo.library.ui.fragment.LiveHomeFragment.b */
    /* loaded from: classes2.dex */
    public class MyKickOutBroadCastReceiver extends BroadcastReceiver {
        public MyKickOutBroadCastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG,"MyKickOutBroadCastReceiver  onReceive");
            if (intent != null && TextUtils.equals(intent.getAction(), ConstantUtils.LIVE_KICK_OUT_ACTION)) {
                LiveHomeFragment.this.showKickOutDialog(intent.getStringExtra(ConstantUtils.RESULT_ITEM));
            }
        }

        public /* synthetic */ MyKickOutBroadCastReceiver(LiveHomeFragment liveHomeFragment, C2267a aVar) {
            this();
        }
    }

    /* renamed from: com.slzhibo.library.ui.fragment.LiveHomeFragment.c */
    /* loaded from: classes2.dex */
    public class MyTokenInvalidBroadCastReceiver extends BroadcastReceiver {
        public MyTokenInvalidBroadCastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG,"MyTokenInvalidBroadCastReceiver  onReceive");
            if (intent != null && TextUtils.equals(intent.getAction(), ConstantUtils.LIVE_TOKEN_INVALID_ACTION)) {
                LiveHomeFragment.this.showTokenInvalidDialog();
            }
        }

        public /* synthetic */ MyTokenInvalidBroadCastReceiver(LiveHomeFragment liveHomeFragment, C2267a aVar) {
            this();
        }
    }

    private void addFragment(BaseFragment baseFragment, String str) {
//        Log.e(TAG,"addFragment");
        if (this.fragmentList == null) {
            this.fragmentList = new ArrayList();
        }
        if (this.labelList == null) {
            this.labelList = new ArrayList();
        }
        if (baseFragment != null && !baseFragment.isAdded()) {
            this.fragmentList.add(baseFragment);
            this.labelList.add(new LabelEntity(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IPagerIndicator getIPagerIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setColors(Integer.valueOf(ContextCompat.getColor(context, R.color.fq_tab_menu_text_select_color)));
        linePagerIndicator.setLineHeight(ConvertUtils.dp2px(2.0f));
        linePagerIndicator.setRoundRadius(UIUtil.dip2px(context, 3.0d));
        linePagerIndicator.setLineWidth(ConvertUtils.dp2px(18.0f));
        linePagerIndicator.setMode(2);
        return linePagerIndicator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTabTextNormalColorRes() {
//        Log.e(TAG,"getTabTextNormalColorRes");
        return ContextCompat.getColor(this.mContext, R.color.fq_tab_menu_text_color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTabTextSelectedColorRes() {
//        Log.e(TAG,"getTabTextSelectedColorRes");
        return ContextCompat.getColor(this.mContext, R.color.fq_tab_menu_text_select_color);
    }

    private void initFragmentLabelList(List<LabelEntity> list) {
//        Log.e(TAG,"initFragmentLabelList");
        List<BaseFragment> list2 = this.fragmentList;
        if (list2 != null) {
            list2.clear();
        }
        List<LabelEntity> list3 = this.labelList;
        if (list3 != null) {
            list3.clear();
        }
//        Log.e("LiveHomeFragment:::","initFragmentLabelList:::");
        if (SysConfigInfoManager.getInstance().isEnableFeeTag()) {
            //付费
            addFragment(HomeSortFragment.newInstance(2), getString(R.string.fq_home_fee_tag));
        }
        //关注
        addFragment(HomeAttentionFragment.newInstance(), getString(R.string.fq_home_attention));
        //推荐
        addFragment(HomeHotFragment.newInstance(true), getString(R.string.fq_home_hot));
        //全部
        addFragment(HomeAllFragment.newInstance(), getString(R.string.fq_home_all));
        if (SysConfigInfoManager.getInstance().isEnableBluetooth()) {
            //互动体验
            addFragment(HomeSortFragment.newInstance(3), getString(R.string.fq_ly_interactive_tag));
        }
        if (list != null && list.size() > 0) {
            for (LabelEntity labelEntity : list) {

                addFragment(HomeSortFragment.newInstance(labelEntity.name), labelEntity.name);
            }
        }
    }

    private void initTitleBarStyle(View view) {
        Log.e(TAG,"initTitleBarStyle");
        if (this.isEnableImmersionBar) {
            try {
                ImmersionBar.with(this).titleBar(view.findViewById(R.id.title_top_view)).statusBarDarkFont(true).init();
            } catch (Exception unused) {
            }
        }
    }

    private void initViewPager() {
        this.menuTagAdapter = new HomeMenuTagAdapter(this.fragmentList, this.labelList, getChildFragmentManager());
        this.commonNavigator = new CommonNavigator(this.mContext);
        this.commonNavigatorAdapter = new C2267a();
        this.commonNavigator.setAdapter(this.commonNavigatorAdapter);
        this.magicIndicator.setNavigator(this.commonNavigator);
        ViewPagerHelper.bind(this.magicIndicator, this.mViewPager);
        this.mViewPager.setAdapter(this.menuTagAdapter);
    }

    public static LiveHomeFragment newInstance() {
        Log.e("LiveHomeFragment","newInstance");
        return newInstance(true);
    }

    private void notifyDataSetChangedViewPager() {
//        Log.e(TAG,"notifyDataSetChangedViewPager");
        int i = (!SysConfigInfoManager.getInstance().isEnableFeeTag() || !TextUtils.equals(this.labelList.get(0).name, getString(R.string.fq_home_fee_tag))) ? 1 : 2;
        this.commonNavigatorAdapter.notifyDataSetChanged();
        this.menuTagAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.fragmentList.size());
        this.mViewPager.setCurrentItem(i, false);
//        EventBus.m309d().m320a(new ListDataUpdateEvent());
        EventBus.getDefault().post(new ListDataUpdateEvent());
    }

    private void registerKickDialogReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConstantUtils.LIVE_KICK_OUT_ACTION);
        this.myKickOutBroadCastReceiver = new MyKickOutBroadCastReceiver(this, null);
        this.mContext.registerReceiver(this.myKickOutBroadCastReceiver, intentFilter);
    }

    private void registerTokenDialogReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConstantUtils.LIVE_TOKEN_INVALID_ACTION);
        this.myTokenInvalidBroadCastReceiver = new MyTokenInvalidBroadCastReceiver(this, null);
        this.mContext.registerReceiver(this.myTokenInvalidBroadCastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKickOutDialog(String str) {
        Log.e(TAG,"showKickOutDialog");
        LiveKickOutDialog.m14688a(str).show(getChildFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTokenInvalidDialog() {
        Log.e(TAG,"showTokenInvalidDialog");
        TokenInvalidDialog.newInstance().show(getChildFragmentManager());
    }

    private void unRegisterKickDialogReceiver() {
        MyKickOutBroadCastReceiver bVar = this.myKickOutBroadCastReceiver;
        if (bVar != null) {
            this.mContext.unregisterReceiver(bVar);
        }
    }

    private void unRegisterTokenDialogReceiver() {
        MyTokenInvalidBroadCastReceiver cVar = this.myTokenInvalidBroadCastReceiver;
        if (cVar != null) {
            this.mContext.unregisterReceiver(cVar);
        }
    }

    /* renamed from: a */
    public /* synthetic */ void m16758a(View view) {
        Log.e(TAG,"m16758a");
        startActivity(SearchActivity.class);
        LogEventUtils.uploadSearchButtonClick();
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.isEnableImmersionBar = bundle.getBoolean(ConstantUtils.RESULT_FLAG, true);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public int getLayoutId() {
        return R.layout.fq_fragment_live_home;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initListener(View view) {
        this.mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() { // from class: e.t.a.h.c.f0
            @Override // com.slzhibo.library.p018ui.view.widget.StateView.AbstractC2856e
            /* renamed from: a */
            public final void onRetryClick() {
                LiveHomeFragment.this.m16757q();
            }
        });
        this.ivSearch.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.c.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LiveHomeFragment.this.m16758a(view2);
            }
        });
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void initView(View view, @Nullable Bundle bundle) {
        initTitleBarStyle(view);
        this.ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        this.magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        initViewPager();
        ((HomePresenter) this.mPresenter).sendInitRequest(this.mStateView, true, bindToLifecycle());
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof LabelMenuEvent) {
            ((HomePresenter) this.mPresenter).getTagList(this.mStateView, false, bindToLifecycle());
        }
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onPause() {
        super.onPause();
        unRegisterKickDialogReceiver();
        unRegisterTokenDialogReceiver();
    }

    @Override // p067e.p304t.p305a.p311e.BaseView
    public void onResultError(int i) {
        Log.e(TAG,"onResultError");
    }

    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        registerKickDialogReceiver();
        registerTokenDialogReceiver();
    }

    @Override // p067e.p304t.p305a.p316h.p328f.p341g.IHomeView
    public void onTagListFail() {
        Log.e(TAG,"onTagListFail");
        try {
            initFragmentLabelList(null);
            SPUtils.getInstance().put(ConstantUtils.LIVE_LABEL_MENU, false);
            notifyDataSetChangedViewPager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p067e.p304t.p305a.p316h.p328f.p341g.IHomeView
    public void onTagListSuccess(List<LabelEntity> list) {
//        Log.e(TAG,"onTagListSuccess");
        try {
            initFragmentLabelList(list);
            SPUtils.getInstance().put(ConstantUtils.LIVE_LABEL_MENU, true);
            notifyDataSetChangedViewPager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: q */
    public /* synthetic */ void m16757q() {
        Log.e(TAG,"m16757q");
        ((HomePresenter) this.mPresenter).sendInitRequest(this.mStateView, true, bindToLifecycle());
    }

    public static LiveHomeFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        LiveHomeFragment liveHomeFragment = new LiveHomeFragment();
        bundle.putBoolean(ConstantUtils.RESULT_FLAG, z);
        liveHomeFragment.setArguments(bundle);
        return liveHomeFragment;
    }

    @Override // com.slzhibo.library.base.BaseFragment
    public HomePresenter createPresenter() {
        return new HomePresenter(this.mContext, this);
    }
}
