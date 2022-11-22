package com.slzhibo.library.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ToastUtils;
//import com.example.boluouitest2zhibo.R;
//import com.slzhibo.library.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.slzhibo.library.R;
import com.slzhibo.library.model.event.BaseEvent;
import com.slzhibo.library.ui.view.widget.StateView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.LogEventUtils;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleProvider;
import com.slzhibo.library.utils.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
//import timber.log.Timber;

import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public abstract class BaseFragment<T extends BasePresenter> extends RxFragment {
    public String TAG;
    public CompositeDisposable compositeDisposable;
    public boolean isLazyLoaded;
    public boolean isLoadingMore;
    public boolean isNoMoreData;
    public boolean isPrepared;
    public Activity mActivity;
    public Context mContext;
    public View mFragmentRootView;
    public ImmersionBar mImmersionBar;
    public T mPresenter;
    public StateView mStateView;
    public String pageStayTimer;
    public int pageNum = 1;
    public boolean isDownRefresh = false;

    /* renamed from: com.slzhibo.library.base.BaseFragment$a */
    /* loaded from: classes2.dex */
//    public class C1934a implements Consumer<Long> {
//        public C1934a() {
//        }
//
//        /* renamed from: a */
//        public void accept(Long l) throws Exception {
//            if (BaseFragment.this.getUserVisibleHint()) {
//                BaseFragment.this.onAutoRefreshData();
//            }
//        }
//    }

    private void lazyLoad() {
        if (getUserVisibleHint() && this.isPrepared && !this.isLazyLoaded) {
            onLazyLoad();
            this.isLazyLoaded = true;
        }
    }

    private void onReleaseDisposable() {
        CompositeDisposable aVar = this.compositeDisposable;
        if (aVar != null) {
            aVar.clear();
            this.compositeDisposable = null;
        }
    }

    public abstract T createPresenter();

    public void getBundle(Bundle bundle) {
    }

    @LayoutRes
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    public LifecycleProvider<FragmentEvent> getLifecycleProvider() {
        return this;
    }

    public String getPageStayTimerType() {
        return getString(R.string.fq_hot_list);
    }

    public void initListener(View view) {
    }

    public abstract void initView(View view, @Nullable Bundle bundle);

    public View injectStateView(View view) {
        return null;
    }

    public boolean isAutoPreLoadingMore(RecyclerView recyclerView) {
        if (!this.isLoadingMore && !this.isNoMoreData) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
            int itemCount = baseQuickAdapter.getItemCount();
            int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            int childCount = recyclerView.getChildCount();
            int headerLayoutCount = baseQuickAdapter.getHeaderLayoutCount();
            int spanCount = gridLayoutManager.getSpanCount();
            int i = (itemCount - headerLayoutCount) % spanCount;
            if (i != 0) {
                spanCount = i;
            }
            int i2 = itemCount - 1;
            int i3 = i2 - spanCount;
            if ((findLastVisibleItemPosition == i3 || findLastVisibleItemPosition == i3 - 2 || findLastVisibleItemPosition == i2) && childCount > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isAutoRefreshDataEnable() {
        return false;
    }

    public boolean isConsumptionPermissionUser() {
        return AppUtils.isConsumptionPermissionUser();
    }

    public boolean isConsumptionPermissionUserToLogin() {
        return AppUtils.isConsumptionPermissionUser(this.mContext);
    }

    public boolean isEnablePageStayReport() {
        return false;
    }

    public boolean isLazyLoad() {
        return false;
    }

    public boolean isLoginUser() {
        return AppUtils.isLogin(this.mContext);
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (isLazyLoad()) {
            this.isPrepared = true;
            lazyLoad();
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    public void onAttachToContext(Context context) {
        this.mContext = context;
        this.mActivity = getActivity() == null ? (Activity) context : getActivity();
    }

    public void onAutoRefreshData() {
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.TAG = getClass().getSimpleName();
        this.mPresenter = createPresenter();
        getBundle(getArguments());
//        EventBus.m309d().m310c(this);
        EventBus.getDefault().register(this);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getLayoutView() != null) {
            return getLayoutView();
        }
        Log.e("BaseFragment.java:205)", String.valueOf(getLayoutId()));
        Log.e("BaseFragment.java:205)", String.valueOf(layoutInflater.inflate(getLayoutId(), viewGroup, false)));
//        Log.e("BaseFragment.java:205)",);
        this.mFragmentRootView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        return this.mFragmentRootView;
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
//        EventBus.m309d().m308d(this);
        EventBus.getDefault().unregister(this);
        onReleaseDisposable();
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent) {
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventMainThreadSticky(BaseEvent baseEvent) {
    }

    public void onFragmentVisible(boolean z) {
    }

    @UiThread
    public void onLazyLoad() {
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (isAutoRefreshDataEnable()) {
            onReleaseDisposable();
            if (this.compositeDisposable == null) {
                this.compositeDisposable = new CompositeDisposable();
            }
//            this.compositeDisposable.mo446b(Observable.m530a(300L, 300L, TimeUnit.SECONDS).m507a(AndroidSchedulers.m453a()).m494b(new C1934a()));
            this.compositeDisposable.add(Observable.interval(300L, 300L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.slzhibo.library.base.BaseFragment.1
                public void accept(Long l) throws Exception {
                    Log.e("BaseFragment：onPause accept", String.valueOf(l));
                    if (BaseFragment.this.getUserVisibleHint()) {
                        BaseFragment.this.onAutoRefreshData();
                    }
                }
            }));
        }
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (isAutoRefreshDataEnable()) {
            onReleaseDisposable();
        }
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onStart() {
        try {
            super.onStart();
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (injectStateView(view) != null) {
            this.mStateView = StateView.inject(injectStateView(view));
        }
        initView(view, bundle);
        initListener(view);
    }

    @Override // android.support.p001v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (isEnablePageStayReport()) {
            if (z) {
                this.pageStayTimer = LogEventUtils.startLiveListDuration();
//                Timber.e(LogEventUtils.startLiveListDuration());
            } else if (isAdded()) {
                LogEventUtils.uploadLiveListDuration(this.pageStayTimer, getPageStayTimerType());
                Log.e("BaseFragment：","LogEventUtils.uploadLiveListDuration");
            }
        }
        if (isLazyLoad()) {
            lazyLoad();
        }
        if (this.isLazyLoaded) {
            onFragmentVisible(z);
        }
    }

    public void showToast(String str) {
        ToastUtils.showShort(str);
    }

    public void startActivity(Class<? extends Activity> cls) {
        Log.e("BaseFragment：startActivity",cls.getSimpleName());
        startActivity(new Intent(this.mContext, cls));
    }

    public void showToast(@StringRes int i) {
        showToast(getString(i));
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }

    public void startActivity(Class<? extends Activity> cls, String str, int i) {
        Log.e("BaseFragment：startActivity2",cls.getSimpleName());
        Intent intent = new Intent(this.mContext, cls);
        intent.putExtra(str, i);
        startActivity(intent);
    }
}

