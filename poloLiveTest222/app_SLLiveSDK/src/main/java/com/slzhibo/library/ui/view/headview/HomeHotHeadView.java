package com.slzhibo.library.ui.view.headview;



import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.IndexRankEntity;
import com.slzhibo.library.ui.adapter.MyRankBannerAdapter;
import com.slzhibo.library.ui.view.widget.RoundRelativeLayout;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.transformations.RoundedCornersTransformation;
//import com.slzhibo.library.utils.transformations.RoundedCornersTransformation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/* renamed from: com.slzhibo.library.ui.view.headview.HomeHotHeadView */
/* loaded from: classes2.dex */
public class HomeHotHeadView extends LinearLayout {

    private List<BannerEntity> bannerList;
    private BGABanner bannerTop;
    private BGABanner bannerView;
    private ImageView ivDefaultCover;
    private Context mContext;

    public HomeHotHeadView(Context context) {
        this(context, null);
    }

    public HomeHotHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeHotHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout.inflate(getContext(), R.layout.fq_layout_head_view_home_hot, this);
        this.mContext = context;
        RoundRelativeLayout roundRelativeLayout = (RoundRelativeLayout) findViewById(R.id.rl_banner_view_bg);
        this.bannerView = (BGABanner) findViewById(R.id.banner);
        this.bannerTop = (BGABanner) findViewById(R.id.banner_top);
        this.ivDefaultCover = (ImageView) findViewById(R.id.iv_default_cover);
    }

    public void initBannerImages(List<BannerEntity> list) {
        boolean z = false;
        if (list == null || list.isEmpty()) {
            this.bannerView.setVisibility(View.INVISIBLE);
            this.ivDefaultCover.setVisibility(View.VISIBLE);
            GlideUtils.loadRoundCornersImage(this.mContext, this.ivDefaultCover, R.drawable.fq_shape_default_banner_cover_bg, 6, RoundedCornersTransformation.CornerType.ALL);
            return;
        }
        this.bannerView.setVisibility(View.VISIBLE);
        this.ivDefaultCover.setVisibility(View.INVISIBLE);
        this.bannerList = AppUtils.getImgBannerItem(list);
        List<BannerEntity> list2 = this.bannerList;
        if (list2 != null && list2.size() != 0) {
            this.bannerView.setAdapter(new BGABanner.Adapter() {
                /* class com.slzhibo.library.ui.view.headview.$$Lambda$HomeHotHeadView$nh5KongnKafyahBQlQ37vxUvzQ */

                @Override // com.slzhibo.library.ui.view.widget.bgabanner.BGABanner.Adapter
                public final void fillBannerItem(BGABanner bGABanner, View view, Object obj, int i) {
                    HomeHotHeadView.this.lambda$initBannerImages$0$HomeHotHeadView(bGABanner, (ImageView) view, (BannerEntity) obj, i);
                }
            });
            BGABanner bGABanner = this.bannerView;
            if (this.bannerList.size() > 1) {
                z = true;
            }
            bGABanner.setAutoPlayAble(z);
            this.bannerView.setData(this.bannerList, null);
            this.bannerView.setDelegate(new BGABanner.Delegate() {
                /* class com.slzhibo.library.ui.view.headview.$$Lambda$HomeHotHeadView$0EQNBwxLskHrJfu0IfNOWq_7W00 */

                @Override // com.slzhibo.library.ui.view.widget.bgabanner.BGABanner.Delegate
                public final void onBannerItemClick(BGABanner bGABanner, View view, Object obj, int i) {
                    HomeHotHeadView.this.lambda$initBannerImages$1$HomeHotHeadView(bGABanner, (ImageView) view, (BannerEntity) obj, i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initBannerImages$0$HomeHotHeadView(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
        GlideUtils.loadAdBannerImageForRoundView(this.mContext, imageView, bannerEntity.img, R.drawable.fq_shape_default_banner_cover_bg);
    }

    public /* synthetic */ void lambda$initBannerImages$1$HomeHotHeadView(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
        AppUtils.clickBannerEvent(this.mContext, bannerEntity);
    }

    public void initTopList(List<IndexRankEntity> list) {
        final ArrayList arrayList = new ArrayList();
        Observable.just(list).map(new Function() { // from class: com.slzhibo.library.ui.view.headview.-$$Lambda$HomeHotHeadView$DaE5-jOxvmos_Zl-VHR2a9v-JZE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return HomeHotHeadView.this.lambda$initTopList$2$HomeHotHeadView(arrayList, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.slzhibo.library.ui.view.headview.-$$Lambda$HomeHotHeadView$A1FecNs0-FTGKbD273i1EMGc3uY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                HomeHotHeadView.this.lambda$initTopList$3$HomeHotHeadView(arrayList, (List) obj);
            }
        });
    }

    public /* synthetic */ List lambda$initTopList$2$HomeHotHeadView(List list, List list2) throws Exception {
        int size = list2.size();
        for (int i = 0; i < size; i += 2) {
            list.add("index:" + i);
        }
        dealAvatarList(list2);
        return list2;
    }

    public /* synthetic */ void lambda$initTopList$3$HomeHotHeadView(List list, List list2) throws Exception {
        BGABanner bGABanner = this.bannerTop;
        boolean z = list.size() > 1;
        bGABanner.setAutoPlayAble(z);
        this.bannerTop.setData(R.layout.fq_layout_head_view_rank_enter, list, (List<String>) null);
        this.bannerTop.setAdapter(new MyRankBannerAdapter(list2, getContext()));
    }

    private List<IndexRankEntity> dealAvatarList(List<IndexRankEntity> list) {
        for (IndexRankEntity indexRankEntity : list) {
            if (TextUtils.equals(indexRankEntity.getType(), ConstantUtils.RANK_TYPE_WEEKSTAR)) {
                List<String> avatars = indexRankEntity.getAvatars();
                if (avatars.size() > 5) {
                    avatars = avatars.subList(0, 4);
                }
                Collections.reverse(avatars);
                indexRankEntity.setAvatars(avatars);
            } else {
                List<String> avatars2 = indexRankEntity.getAvatars();
                if (avatars2.size() > 3) {
                    avatars2 = avatars2.subList(0, 2);
                }
                Collections.reverse(avatars2);
                indexRankEntity.setAvatars(avatars2);
            }
        }
        return list;
    }




//
//    /* renamed from: b */
//    public BGABanner f7826b;
//
//    /* renamed from: c */
//    public BGABanner f7827c;
//
//    /* renamed from: d */
//    public ImageView f7828d;
//
//    /* renamed from: e */
//    public Context f7829e;
//
//    /* renamed from: f */
//    public List<BannerEntity> f7830f;
//
//    public HomeHotHeadView(Context context) {
//        this(context, null);
//    }
//
//    /* renamed from: a */
//    public final void m14547a(Context context) {
//        LinearLayout.inflate(getContext(), R.layout.fq_layout_head_view_home_hot, this);
//        this.f7829e = context;
//        RoundRelativeLayout roundRelativeLayout = (RoundRelativeLayout) findViewById(R.id.rl_banner_view_bg);
//        this.f7826b = (BGABanner) findViewById(R.id.banner);
//        this.f7827c = (BGABanner) findViewById(R.id.banner_top);
//        this.f7828d = (ImageView) findViewById(R.id.iv_default_cover);
//    }
//
//    /* renamed from: b */
//    public void m14542b(List<BannerEntity> list) {
//        boolean z = false;
//        if (list == null || list.isEmpty()) {
//            this.f7826b.setVisibility(View.INVISIBLE);
//            this.f7828d.setVisibility(View.VISIBLE);
//            GlideUtils.loadRoundCornersImage(this.f7829e, this.f7828d, R.drawable.fq_shape_default_banner_cover_bg, 6, RoundedCornersTransformation.CornerType.ALL);
//            return;
//        }
//        this.f7826b.setVisibility(View.VISIBLE);
//        this.f7828d.setVisibility(View.INVISIBLE);
//        this.f7830f = AppUtils.getImgBannerItem(list);
//        List<BannerEntity> list2 = this.f7830f;
//        if (list2 != null && list2.size() != 0) {
//            this.f7826b.setAdapter(new BGABanner.Adapter() { // from class: e.t.a.h.f.e.d
//                @Override // com.slzhibo.library.p018ui.view.widget.bgabanner.BGABanner.Adapter
//                /* renamed from: a */
//                public final void fillBannerItem(BGABanner bGABanner, View view, Object obj, int i) {
//                    HomeHotHeadView.this.m14546a(bGABanner, (ImageView) view, (BannerEntity) obj, i);
//                }
//            });
//            BGABanner bGABanner = this.f7826b;
//            if (this.f7830f.size() > 1) {
//                z = true;
//            }
//            bGABanner.setAutoPlayAble(z);
//            this.f7826b.setData(this.f7830f, null);
//            this.f7826b.setDelegate(new BGABanner.Delegate() { // from class: e.t.a.h.f.e.a
//                @Override // com.slzhibo.library.p018ui.view.widget.bgabanner.BGABanner.AbstractC2870d
//                /* renamed from: a */
//                public final void onBannerItemClick(BGABanner bGABanner2, View view, Object obj, int i) {
//                    HomeHotHeadView.this.m14543b(bGABanner2, (ImageView) view, (BannerEntity) obj, i);
//                }
//            });
//        }
//    }
//
//    /* renamed from: c */
//    public void m14540c(List<IndexRankEntity> list) {
//        final ArrayList arrayList = new ArrayList();
//        Observable.just(list).map(new Function() { // from class: e.t.a.h.f.e.c
//            @Override // p481f.p482a.p483a0.Function
//            public final Object apply(Object obj) throws Exception {
//                return HomeHotHeadView.this.m14544a(arrayList, (List) obj);
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: e.t.a.h.f.e.b
//            @Override // p481f.p482a.p483a0.Consumer
//            public final void accept(Object obj) throws Exception {
//                HomeHotHeadView.this.m14541b(arrayList, (List) obj);
//            }
//        });
//    }
//
//    public HomeHotHeadView(Context context, AttributeSet attributeSet) {
//        this(context, attributeSet, 0);
//    }
//
//    public HomeHotHeadView(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//        m14547a(context);
//    }
//
//    /* renamed from: a */
//    public /* synthetic */ void m14546a(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
//        GlideUtils.loadAdBannerImageForRoundView(this.f7829e, imageView, bannerEntity.img, R.drawable.fq_shape_default_banner_cover_bg);
//    }
//
//    /* renamed from: a */
//    public /* synthetic */ List m14544a(List list, List list2) throws Exception {
//        int size = list2.size();
//        for (int i = 0; i < size; i += 2) {
//            list.add("index:" + i);
//        }
//        m14545a(list2);
//        return list2;
//    }
//
//    /* renamed from: a */
//    public final List<IndexRankEntity> m14545a(List<IndexRankEntity> list) {
//        for (IndexRankEntity indexRankEntity : list) {
//            if (TextUtils.equals(indexRankEntity.getType(), ConstantUtils.RANK_TYPE_WEEKSTAR)) {
//                List<String> avatars = indexRankEntity.getAvatars();
//                if (avatars.size() > 5) {
//                    avatars = avatars.subList(0, 4);
//                }
//                Collections.reverse(avatars);
//                indexRankEntity.setAvatars(avatars);
//            } else {
//                List<String> avatars2 = indexRankEntity.getAvatars();
//                if (avatars2.size() > 3) {
//                    avatars2 = avatars2.subList(0, 2);
//                }
//                Collections.reverse(avatars2);
//                indexRankEntity.setAvatars(avatars2);
//            }
//        }
//        return list;
//    }
//
//    /* renamed from: b */
//    public /* synthetic */ void m14543b(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
//        AppUtils.clickBannerEvent(this.f7829e, bannerEntity);
//    }
//
//    /* renamed from: b */
//    public /* synthetic */ void m14541b(List list, List list2) throws Exception {
//        BGABanner bGABanner = this.f7827c;
//        boolean z = true;
//        if (list.size() <= 1) {
//            z = false;
//        }
//        bGABanner.setAutoPlayAble(z);
//        this.f7827c.setData(R.layout.fq_layout_head_view_rank_enter, list, (List<String>) null);
//        this.f7827c.setAdapter(new MyRankBannerAdapter(list2, getContext()));
//    }
}

