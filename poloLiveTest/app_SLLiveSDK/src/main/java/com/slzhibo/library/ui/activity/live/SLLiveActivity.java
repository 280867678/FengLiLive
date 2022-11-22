package com.slzhibo.library.ui.activity.live;

import android.os.Bundle;

//package com.slzhibo.library.ui.activity.live;

//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;

import com.slzhibo.library.R;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BaseActivity;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.ui.activity.live.SLLiveFragment;
import com.slzhibo.library.ui.view.widget.CustomVerticalViewPager;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.live.LiveManagerUtils;
import com.slzhibo.library.utils.permission.PermissionX;
import com.slzhibo.library.utils.permission.callback.RequestCallback;
import java.util.ArrayList;
import java.util.List;




/* loaded from: classes3.dex */
public class SLLiveActivity extends BaseActivity<BasePresenter> implements SLLiveFragment.OnFragmentInteractionListener {
    private SLLiveFragment SLLiveFragment;
    private String liveEnterWay;
    private LiveEntity liveInfoItem;
    private FrameLayout mFragmentContainer;
    private FragmentManager mFragmentManager;
    private ArrayList<LiveEntity> mLiveList;
    private MyPagerAdapter mPagerAdapter;
    private RelativeLayout mRoomContainer;
    private CustomVerticalViewPager mViewPager;
    private int mViewPagerCurrentPosition;
    private boolean mIsFirstLoading = false;
    private int mViewPagerLastPosition = -1;
    private int mLiveListPosition = -1;

    @Override // com.slzhibo.library.base.BaseActivity
    public BasePresenter createPresenter() {
        return null;
    }

    @Override // com.slzhibo.library.base.BaseActivity
    protected int getLayoutId() {
        return R.layout.fq_activity_sl_live;
    }

    @Override // com.slzhibo.library.base.BaseActivity, com.slzhibo.library.utils.rxlifecycle2.components.support.RxAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.mLiveList = LiveManagerUtils.getInstance().getLiveList();
        this.liveInfoItem = LiveManagerUtils.getInstance().getCurrentLiveItem();
        this.mLiveListPosition = LiveManagerUtils.getInstance().getCurrentLivePosition();
        this.liveEnterWay = getIntent().getStringExtra(ConstantUtils.RESULT_FLAG);
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @Override // com.slzhibo.library.base.BaseActivity
    public void initView(Bundle bundle) {
        setTitle(getString(R.string.fq_title_user_watch_live));
        this.mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.fq_view_room_container, (ViewGroup) null);
        this.mFragmentContainer = (FrameLayout) this.mRoomContainer.findViewById(R.id.fragment_container);
        this.mViewPager = (CustomVerticalViewPager) findViewById(R.id.view_pager);
        this.SLLiveFragment = SLLiveFragment.newInstance(this.liveInfoItem, this.liveEnterWay);
        this.mFragmentManager = getSupportFragmentManager();
        this.mPagerAdapter = new MyPagerAdapter();
        this.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveActivity.1
            @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                SLLiveActivity.this.mViewPagerCurrentPosition = i;
            }
        });
        this.mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveActivity$q6Tlr0LAZ7LPSOedGhXaU6-4hT8
            @Override // android.support.v4.view.ViewPager.PageTransformer
            public final void transformPage(View view, float f) {
                SLLiveActivity.this.lambda$initView$0$SLLiveActivity(view, f);
            }
        });
        this.mViewPager.setAdapter(this.mPagerAdapter);
        if (this.mLiveList.size() > 1) {
            this.mViewPager.setCurrentItem((this.mLiveList.size() * 1000) + this.mLiveListPosition);
        }
        initPermission();
    }

    public /* synthetic */ void lambda$initView$0$SLLiveActivity(View view, float f) {
        View findViewById;
        ViewGroup viewGroup = (ViewGroup) view;
        if (!(f >= 0.0f || viewGroup.getId() == this.mViewPagerCurrentPosition || (findViewById = viewGroup.findViewById(R.id.room_container)) == null || findViewById.getParent() == null || !(findViewById.getParent() instanceof ViewGroup))) {
            SLLiveFragment sLLiveFragment = this.SLLiveFragment;
            if (sLLiveFragment != null && sLLiveFragment.isAdded()) {
                this.SLLiveFragment.onFragmentPageChangeListener();
            }
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        }
        int id = viewGroup.getId();
        int i = this.mViewPagerCurrentPosition;
        if (id == i && f == 0.0f && i != this.mViewPagerLastPosition) {
            if (this.mRoomContainer.getParent() != null && (this.mRoomContainer.getParent() instanceof ViewGroup)) {
                ((ViewGroup) this.mRoomContainer.getParent()).removeView(this.mRoomContainer);
            }
            loadVideoAndChatRoom(viewGroup, this.mViewPagerCurrentPosition);
        }
    }

    private void loadVideoAndChatRoom(ViewGroup viewGroup, int i) {
        if (!this.mIsFirstLoading) {
            this.mFragmentManager.beginTransaction().add(this.mFragmentContainer.getId(), this.SLLiveFragment).commitAllowingStateLoss();
            this.mIsFirstLoading = true;
        } else if (this.mLiveList.size() > 0) {
            this.SLLiveFragment.resetLiveRoom(getCurrentItem(i), "1");
        }
        viewGroup.addView(this.mRoomContainer);
        this.mViewPagerLastPosition = i;
    }

    @Override // com.slzhibo.library.base.BaseActivity, com.slzhibo.library.service.NetworkChangeReceiver.NetChangeListener
    public void onNetChangeListener(int i) {
        try {
            if (i != -1) {
                if (i == 0) {
                    showMobileNetDialog();
                }
            } else if (this.SLLiveFragment != null && this.SLLiveFragment.isAdded()) {
                this.SLLiveFragment.onNetNone();
            }
        } catch (Exception unused) {
        }
    }

    private void showMobileNetDialog() {
        if (!SPUtils.getInstance().getBoolean(ConstantUtils.SHOW_MOBIE_TIP, false)) {
            SLLiveFragment sLLiveFragment = this.SLLiveFragment;
            if (sLLiveFragment != null && sLLiveFragment.isAdded()) {
                this.SLLiveFragment.on4G();
            }
        } else if (!BaseActivity.hasRemindTraffic) {
            BaseActivity.hasRemindTraffic = true;
            showToast(R.string.fq_mobile_tip);
        }
    }

    private int formatLiveListPosition(int i) {
        if (i < 0 || i >= this.mLiveList.size()) {
            return 0;
        }
        return i;
    }

    public LiveEntity getCurrentItem(int i) {
        ArrayList<LiveEntity> arrayList = this.mLiveList;
        return arrayList.get(formatLiveListPosition(i % arrayList.size()));
    }

    private void initPermission() {
        PermissionX.init(this).permissions("android.permission.RECORD_AUDIO").request(new RequestCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveActivity.2
            @Override // com.slzhibo.library.utils.permission.callback.RequestCallback
            public void onResult(boolean z, List<String> list, List<String> list2) {
            }
        });
    }

    @Override // com.slzhibo.library.ui.activity.live.SLLiveFragment.OnFragmentInteractionListener
    public void setViewPagerScroll(boolean z) {
        this.mViewPager.setScroll(z);
    }

    @Override // com.slzhibo.library.ui.activity.live.SLLiveFragment.OnFragmentInteractionListener
    public void updateLiveRoomInfo() {
        SLLiveSDK.getSingleton().onAllLiveListUpdate(bindToLifecycle(), new ResultCallBack<List<LiveEntity>>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveActivity.3
            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            public void onSuccess(List<LiveEntity> list) {
                SLLiveActivity.this.mLiveList = new ArrayList(list);
                SLLiveActivity.this.mPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    /* loaded from: classes3.dex */
    public class MyPagerAdapter extends PagerAdapter {
        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private MyPagerAdapter() {
//            SLLiveActivity.this = r1;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return (SLLiveActivity.this.mLiveList.isEmpty() || SLLiveActivity.this.mLiveList.size() == 1) ? 1 : Integer.MAX_VALUE;
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fq_view_room_item, (ViewGroup) null);
            inflate.setId(i);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.anchor_img);
            if (SLLiveActivity.this.mLiveList.size() > 0) {
                LiveEntity currentItem = SLLiveActivity.this.getCurrentItem(i);
                if (currentItem != null) {
                    GlideUtils.loadImageBlur(( SLLiveActivity.this).mContext, imageView, TextUtils.isEmpty(currentItem.liveCoverUrl) ? currentItem.avatar : currentItem.liveCoverUrl, R.drawable.fq_shape_default_cover_bg);
                } else {
                    GlideUtils.loadImageBlur(( SLLiveActivity.this).mContext, imageView, null, R.drawable.fq_shape_default_cover_bg);
                }
            }
            viewGroup.addView(inflate);
            return inflate;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(viewGroup.findViewById(i));
        }
    }
}
