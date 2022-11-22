package com.blmvl.blvl.activity;



import android.content.Context;
import android.os.Bundle;

import com.blmvl.blvl.Dialog.ActivityAdDialog;
import com.blmvl.blvl.Dialog.DialogUtil;
import com.blmvl.blvl.Dialog.NoticeMsgDialog;
import com.blmvl.blvl.ImgLoader.ImgLoader;
import com.blmvl.blvl.PagerHelper.ComViewPagerHelper;
import com.blmvl.blvl.R;
import com.blmvl.blvl.httpUtil.HttpUtil;
import com.blmvl.blvl.util.CustomWordUtil;
import com.blmvl.blvl.util.HttpCallback;
import com.blmvl.blvl.util.IntentUtil;


//package com.blmvl.blvl.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
//import android.support.p001v4.app.FragmentManager;
//import android.support.p001v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.bean.AppConfig;
import com.blmvl.blvl.bean.ConfigBean;
import com.blmvl.blvl.fragment.GameFragment;
import com.blmvl.blvl.fragment.HomeFragment;
import com.blmvl.blvl.fragment.MainVideoFeaturedFragment;
import com.blmvl.blvl.fragment.MineFragment;
import com.blmvl.blvl.util.LogUtil;
import com.blmvl.blvl.util.ObjUtil;
import com.blmvl.blvl.util.SpUtil;
import com.blmvl.blvl.util.ToastUtil;
import com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import com.comod.view.magicindicator.buildins.commonnavigator.titles.IPagerTitleView;
import com.gyf.immersionbar.ImmersionBar;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.ui.fragment.LiveHomeFragment;
import com.slzhibo.library.ui.fragment.LiveHomeFragment;
import java.util.ArrayList;
import java.util.List;
//import p067e.p103c.p104a.p108f.ActivityAdDialog;
//import p067e.p103c.p104a.p108f.NoticeMsgDialog;
//import p067e.p103c.p104a.p111i.HttpCallback;
//import p067e.p103c.p104a.p111i.HttpUtil;
//import p067e.p103c.p104a.p113k.CustomWordUtil;
//import p067e.p103c.p104a.p113k.DialogUtil;
//import p067e.p103c.p104a.p113k.ImgLoader;
//import p067e.p103c.p104a.p113k.LogUtil;
//import p067e.p103c.p104a.p113k.SpUtil;
//import p067e.p130f.p131a.p135d.ComViewPagerHelper;
//import p067e.p130f.p131a.p135d.IntentUtil;
//import p067e.p130f.p131a.p135d.ObjUtil;
//import p067e.p130f.p131a.p135d.ToastUtil;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView;
//import p067e.p248k.p249a.ImmersionBar;
//import us.ceubf.qeqeff.R;

/* loaded from: classes.dex */
public class MainActivity extends AbsActivity {

    /* renamed from: e */
    public ComViewPagerHelper f598e;

    /* renamed from: f */
    public ImageView f599f;

    /* renamed from: g */
    public ImageView f600g;

    /* renamed from: h */
    public FrameLayout f601h;

    /* renamed from: b */
    public long f595b = 0;

    /* renamed from: c */
    public List<String> f596c = new ArrayList();

    /* renamed from: d */
    public List<Integer> f597d = new ArrayList();

    /* renamed from: i */
    public int f602i = 0;

    /* renamed from: j */
    public boolean f603j = false;

    /* renamed from: com.blmvl.blvl.activity.MainActivity$b */
    /* loaded from: classes.dex */
    public class C0641b implements NoticeMsgDialog.AbstractC4135a {
        public C0641b() {
        }

        @Override // p067e.p103c.p104a.p108f.NoticeMsgDialog.AbstractC4135a
        /* renamed from: a */
        public void mo9911a() {
            MainActivity.this.m21038U();
            AppCenterActivity.m21268a(MainActivity.this);
        }

        @Override // p067e.p103c.p104a.p108f.NoticeMsgDialog.AbstractC4135a
        /* renamed from: c */
        public void mo9910c() {
            MainActivity.this.m21038U();
        }
    }

    /* renamed from: com.blmvl.blvl.activity.MainActivity$c */
    /* loaded from: classes.dex */
    public class C0642c extends HttpCallback {
        public C0642c() {
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onError() {
            super.onError();
            MainActivity.this.m21037V();
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onException(int i, String str) {
            super.onException(i, str);
            MainActivity.this.m21037V();
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onNetworkError() {
            super.onNetworkError();
            MainActivity.this.m21037V();
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onSuccess(String str, String str2, boolean z, boolean z2) {
            super.onSuccess(str, str2, z, z2);
            try {
                MainActivity.this.m21037V();
            } catch (Exception e) {
                e.printStackTrace();
                MainActivity.this.m21037V();
            }
        }
    }

    @Override // com.blmvl.blvl.activity.AbsActivity
    /* renamed from: B */
    public void mo20620B() {
        super.mo20620B();
//        ImmersionBar b = ImmersionBar.m5078b(this);
//        b.m5105D();
//        b.m5082a(true, R.color.white);
//        b.m5075b(true, 0.2f);
//        b.m5072c(R.color.white);
//        b.m5045w();

        ImmersionBar mBarParams = ImmersionBar.with(this);
        mBarParams.reset();
        mBarParams.fitsSystemWindows(true, R.color.white);
        mBarParams.navigationBarDarkIcon(true, 0.2f);
        mBarParams.navigationBarColor(R.color.white);      ////////
        mBarParams.init();

    }

    /* renamed from: G */
    public final void m21042G() {
        this.f596c.add(CustomWordUtil.m9463a((int) R.string.str_home));
        this.f596c.add(CustomWordUtil.m9463a((int) R.string.featured));
        if (SpUtil.m9573D().m9515y()) {
            this.f596c.add(CustomWordUtil.m9463a((int) R.string.live));
        }
        if (SpUtil.m9573D().m9574C() == 1) {
            this.f596c.add(CustomWordUtil.m9463a((int) R.string.str_game));
        }
        this.f596c.add(CustomWordUtil.m9463a((int) R.string.mine));
        ArrayList arrayList = new ArrayList();
        arrayList.add(HomeFragment.newInstance());
        arrayList.add(MainVideoFeaturedFragment.newInstance());
        if (SpUtil.m9573D().m9515y()) {
            arrayList.add(LiveHomeFragment.newInstance(false));
        }
        if (SpUtil.m9573D().m9574C() == 1) {
            arrayList.add(GameFragment.m20456b("/api/game/index", 2));
        }
        arrayList.add(MineFragment.newInstance());
        this.f597d.add(Integer.valueOf((int) R.drawable.ic_video_seletor));
        this.f597d.add(Integer.valueOf((int) R.drawable.ic_featured_seletor));
        if (SpUtil.m9573D().m9515y()) {
            this.f597d.add(Integer.valueOf((int) R.drawable.ic_live_seletor));
        }
        if (SpUtil.m9573D().m9574C() == 1) {
            this.f597d.add(Integer.valueOf((int) R.drawable.ic_game_seletor));
        }
        this.f597d.add(Integer.valueOf((int) R.drawable.ic_mine_seletor));
        this.f598e = new C0639a(this, this, this.f596c, arrayList, this.f597d, getSupportFragmentManager());
    }

    /* renamed from: N */
    public void m21041N() {
        if (System.currentTimeMillis() - this.f595b > 2000) {
            ToastUtil.m9102c(this, CustomWordUtil.m9463a((int) R.string.exit_app_hint));
            this.f595b = System.currentTimeMillis();
            return;
        }
        AppContext.m21299a().m21298b();
    }

    /* renamed from: Q */
    public final void m21040Q() {
        try {
            ConfigBean config = AppConfig.getInstance().getConfig();
            if (config != null) {
                int maintainSwitch = config.getMaintainSwitch();
                String maintainTips = config.getMaintainTips();
                if (maintainSwitch != 1 || TextUtils.isEmpty(maintainTips)) {
                    m21038U();
                } else {
                    NoticeMsgDialog i0Var = new NoticeMsgDialog(this, maintainTips);
                    i0Var.m9913a(new C0641b());
                    DialogUtil.m9457a(this, i0Var);
                }
            } else {
                m21038U();
            }
        } catch (Exception e) {
            e.printStackTrace();
            m21038U();
        }
    }

    /* renamed from: R */
    public final void m21039R() {
        this.f599f = (ImageView) findViewById(R.id.img_game);
        this.f600g = (ImageView) findViewById(R.id.img_close);
        this.f600g.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.c.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.m21035a(view);
            }
        });
        this.f601h = (FrameLayout) findViewById(R.id.layout_game);
        this.f601h.setVisibility(View.GONE);
        if (SpUtil.m9573D().m9574C() == 1) {
            if (!(AppConfig.getInstance().getConfig() == null || AppConfig.getInstance().getConfig().getGame_float() == null || TextUtils.isEmpty(AppConfig.getInstance().getConfig().getGame_float().getIcon()))) {
                this.f601h.setVisibility(View.VISIBLE);
                ImgLoader.m9424b(AppConfig.getInstance().getConfig().getGame_float().getIcon(), this.f599f, (int) R.drawable.bg_square_default);
            }
            this.f601h.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.c.h0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.this.m21032b(view);
                }
            });
        }
    }

    /* renamed from: U */
    public final void m21038U() {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager.getPrimaryClip() != null) {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                if (primaryClip != null) {
                    int itemCount = primaryClip.getItemCount();
                    if (itemCount > 0) {
                        String str = "";
                        int i = 0;
                        while (true) {
                            if (i >= itemCount) {
                                break;
                            }
                            ClipData.Item itemAt = primaryClip.getItemAt(i);
                            if (itemAt != null && !TextUtils.isEmpty(itemAt.getText())) {
                                String charSequence = itemAt.getText().toString();
                                if (!TextUtils.isEmpty(charSequence)) {
                                    if (!charSequence.startsWith("bluo_aff:")) {
                                        if (charSequence.startsWith("#") && charSequence.endsWith("#")) {
                                            str = charSequence.substring(1, charSequence.length() - 1);
                                            break;
                                        }
                                    } else {
                                        str = charSequence.substring(9);
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            }
                            i++;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            m21026h(str);
                        } else {
                            m21037V();
                        }
                    } else {
                        m21037V();
                    }
                } else {
                    m21037V();
                }
            } else {
                m21037V();
            }
        } catch (Exception e) {
            e.printStackTrace();
            m21037V();
        }
    }

    /* renamed from: V */
    public final void m21037V() {
        try {
            ConfigBean config = AppConfig.getInstance().getConfig();
            if (config != null && !TextUtils.isEmpty(config.getActivityImg())) {
                DialogUtil.m9457a(this, new ActivityAdDialog(this, config));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: h */
    public final void m21026h(String str) {
        HttpUtil.m9705d(str, new C0642c());
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        m21041N();
        return true;
    }

    @Override // com.blmvl.blvl.activity.AbsActivity
    /* renamed from: z */
    public int mo20588z() {
        return R.layout.activity_main;
    }

    /* renamed from: b */
    public /* synthetic */ void m21032b(View view) {
        if (ObjUtil.m9224a(this.f598e)) {
            this.f598e.m9145c(this.f602i);
        }
    }

    /* renamed from: a */
    public static void m21036a(Context context) {
        IntentUtil.m9081a(context, MainActivity.class);
    }

    @Override // com.blmvl.blvl.activity.AbsActivity
    /* renamed from: a */
    public void mo20592a(Bundle bundle) {
        m21042G();
        m21039R();
        m21040Q();
        SLLiveSDK.getSingleton().loadOperationActivityDialog(this);
        SLLiveSDK.getSingleton().initAnim();
        LogUtil.m9410a("BL_MAIN_PAGE");
    }

    /* renamed from: a */
    public /* synthetic */ void m21035a(View view) {
        this.f603j = true;
        if (this.f601h.getVisibility() == View.VISIBLE) {
            this.f601h.setVisibility(View.GONE);
        }
    }

    /* renamed from: com.blmvl.blvl.activity.MainActivity$a */
    /* loaded from: classes.dex */
    public class C0639a extends ComViewPagerHelper {

        /* renamed from: com.blmvl.blvl.activity.MainActivity$a$a */
        /* loaded from: classes.dex */
        public class C0640a implements CommonPagerTitleView.AbstractC0922b {

            /* renamed from: a */
            public final /* synthetic */ ImageView f605a;

            /* renamed from: b */
            public final /* synthetic */ TextView f606b;

            public C0640a(C0639a aVar, ImageView imageView, TextView textView) {
                this.f605a = imageView;
                this.f606b = textView;
            }

            @Override // com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.AbstractC0922b
            /* renamed from: a */
            public void mo19970a(int i, int i2) {
                this.f605a.setSelected(false);
                this.f606b.setSelected(false);
            }

            @Override // com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.AbstractC0922b
            /* renamed from: a */
            public void mo19969a(int i, int i2, float f, boolean z) {
            }

            @Override // com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.AbstractC0922b
            /* renamed from: b */
            public void mo19968b(int i, int i2) {
                this.f605a.setSelected(true);
                this.f606b.setSelected(true);
            }

            @Override // com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.AbstractC0922b
            /* renamed from: b */
            public void mo19967b(int i, int i2, float f, boolean z) {
            }
        }

        public C0639a(Context context, Activity activity, List list, List list2, List list3, FragmentManager fragmentManager) {
            super(context, activity, list, list2, list3, fragmentManager);
        }

        @Override // p067e.p130f.p131a.p135d.ComViewPagerHelper
        /* renamed from: a */
        public IPagerTitleView mo9153a(Context context, final int i, ViewPager viewPager, List<String> list, List<Integer> list2) {
            CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
            View inflate = LayoutInflater.from(context).inflate(R.layout.view_main_bottom_tab, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_tab);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_tab);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.img_badge);
            textView.setText((CharSequence) MainActivity.this.f596c.get(i));
            if (((String) MainActivity.this.f596c.get(i)).equals(CustomWordUtil.m9463a((int) R.string.str_game))) {
                MainActivity.this.f602i = i;
                imageView2.setVisibility(View.VISIBLE);
            } else {
                imageView2.setVisibility(View.INVISIBLE);
            }
            textView.setTextColor(MainActivity.this.getResources().getColorStateList(R.color.main_tab_text_dark_selector));
            imageView.setImageResource(list2.get(i).intValue());
            commonPagerTitleView.setContentView(inflate);
            commonPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.c.g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.C0639a.this.m21025a(i, view);
                }
            });
            commonPagerTitleView.setOnPagerTitleChangeListener(new C0640a(this, imageView, textView));
            return commonPagerTitleView;
        }

        @Override // p067e.p130f.p131a.p135d.ComViewPagerHelper
        /* renamed from: a */
        public boolean mo9157a() {
            return true;
        }

        @Override // p067e.p130f.p131a.p135d.ComViewPagerHelper
        /* renamed from: b */
        public void mo9148b(int i) {
            super.mo9148b(i);
            if (SpUtil.m9573D().m9574C() == 1) {
                if (i == MainActivity.this.f602i) {
                    if (MainActivity.this.f601h.getVisibility() == View.VISIBLE) {
                        MainActivity.this.f601h.setVisibility(View.GONE);
                    }
                } else if (!MainActivity.this.f603j && MainActivity.this.f601h.getVisibility() == View.GONE) {
                    if (AppConfig.getInstance().getConfig() == null || AppConfig.getInstance().getConfig().getGame_float() == null || TextUtils.isEmpty(AppConfig.getInstance().getConfig().getGame_float().getIcon())) {
                        MainActivity.this.f601h.setVisibility(View.GONE);
                    } else {
                        MainActivity.this.f601h.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (i == m9146c() - 1) {
//                ImmersionBar b = ImmersionBar.m5078b(MainActivity.this);
//                b.m5105D();
//                b.m5075b(true, 0.8f);
//                b.m5072c(R.color.white);
//                b.m5045w();
                ImmersionBar mBarParams = ImmersionBar.with(MainActivity.this);
                mBarParams.reset();
                mBarParams.navigationBarDarkIcon(true, 0.8f);
                mBarParams.navigationBarColor(R.color.white);      ////////
                mBarParams.init();
                return;
            }
//            ImmersionBar b2 = ImmersionBar.m5078b(MainActivity.this);
//            b2.m5105D();
//            b2.m5082a(true, R.color.white);
//            b2.m5075b(true, 0.2f);
//            b2.m5072c(R.color.white);
//            b2.m5045w();
            ImmersionBar mBarParams2 = ImmersionBar.with(MainActivity.this);
            mBarParams2.reset();
            mBarParams2.fitsSystemWindows(true, R.color.white);
            mBarParams2.navigationBarDarkIcon(true, 0.2f);      ////////
            mBarParams2.navigationBarColor(R.color.white);      ///////
            mBarParams2.init();
        }

        /* renamed from: a */
        public /* synthetic */ void m21025a(int i, View view) {
            m9145c(i);
        }
    }
}
