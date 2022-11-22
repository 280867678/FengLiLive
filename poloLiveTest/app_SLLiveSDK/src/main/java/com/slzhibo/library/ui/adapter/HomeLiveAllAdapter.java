package com.slzhibo.library.ui.adapter;




import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveListItemEntity;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
//import com.slzhibo.library.utils.adapter.BaseMultiItemQuickAdapter;
import com.slzhibo.library.utils.HomeLiveAdapterUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;


/* renamed from: com.slzhibo.library.ui.adapter.HomeLiveAllAdapter */
/* loaded from: classes2.dex */
public class HomeLiveAllAdapter extends BaseMultiItemQuickAdapter<LiveEntity, BaseViewHolder> {

    /* renamed from: com.slzhibo.library.ui.adapter.HomeLiveAllAdapter.a */
    /* loaded from: classes2.dex */
    public class C2246a implements BGABanner.Delegate {
        public C2246a() {
        }

        @Override // com.slzhibo.library.p018ui.view.widget.bgabanner.BGABanner.AbstractC2870d
        /* renamed from: a */
        public void onBannerItemClick(BGABanner bGABanner, View view, @Nullable Object obj, int i) {
            LiveListItemEntity liveListItemEntity;
            if ((obj instanceof LiveListItemEntity) && (liveListItemEntity = (LiveListItemEntity) obj) != null) {
                AppUtils.startSLLiveActivity(HomeLiveAllAdapter.this.mContext, HomeLiveAllAdapter.this.m17005a(liveListItemEntity), "1", HomeLiveAllAdapter.this.mContext.getString(R.string.fq_hot_list));
            }
        }
    }

    public HomeLiveAllAdapter(List<LiveEntity> list) {
        super(list);
        m17006a();
    }

    /* renamed from: b */
    public /* synthetic */ void m16999b(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
        AppUtils.clickBannerEvent(this.mContext, bannerEntity);
    }

    /* renamed from: a */
    public final void m17006a() {
        addItemType(1, R.layout.fq_item_list_live_view_new);
        addItemType(2, R.layout.fq_layout_home_all_banner_view);
        addItemType(3, R.layout.fq_layout_home_all_dynamic_view);
        addItemType(4, R.layout.fq_item_list_live_view_new_empty);
        addItemType(5, R.layout.fq_layout_home_all_live_banner_view);
    }

    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        if (liveEntity != null) {
            int itemViewType = baseViewHolder.getItemViewType();
            boolean z = true;
            if (itemViewType == 1) {
                HomeLiveAdapterUtils.m1334a(this.mContext, baseViewHolder, liveEntity);
            } else if (itemViewType == 2) {
                BGABanner bGABanner = (BGABanner) baseViewHolder.getView(R.id.iv_banner);
                List<BannerEntity> imgBannerItem = AppUtils.getImgBannerItem(liveEntity.bannerList);
                if (imgBannerItem != null && !imgBannerItem.isEmpty()) {
                    bGABanner.setAdapter(new BGABanner.Adapter() { // from class: e.t.a.h.b.b
                        @Override // com.slzhibo.library.p018ui.view.widget.bgabanner.BGABanner.AbstractC2868b
                        /* renamed from: a */
                        public final void fillBannerItem(BGABanner bGABanner2, View view, Object obj, int i) {
                            HomeLiveAllAdapter.this.m17002a(bGABanner2, (ImageView) view, (BannerEntity) obj, i);
                        }
                    });
                    bGABanner.setData(imgBannerItem, null);
                    if (imgBannerItem.size() <= 1) {
                        z = false;
                    }
                    bGABanner.setAutoPlayAble(z);
                    bGABanner.setDelegate(new BGABanner.Delegate() { // from class: e.t.a.h.b.c
                        @Override // com.slzhibo.library.p018ui.view.widget.bgabanner.BGABanner.AbstractC2870d
                        /* renamed from: a */
                        public final void onBannerItemClick(BGABanner bGABanner2, View view, Object obj, int i) {
                            HomeLiveAllAdapter.this.m16999b(bGABanner2, (ImageView) view, (BannerEntity) obj, i);
                        }
                    });
                }
            } else if (itemViewType == 3) {
                List<BannerEntity> list = liveEntity.bannerList;
                TextView textView = (TextView) baseViewHolder.getView(R.id.tv_dynamic_content);
                TextView textView2 = (TextView) baseViewHolder.getView(R.id.tv_dynamic_content_2);
                TextView textView3 = (TextView) baseViewHolder.getView(R.id.tv_dynamic_empty);
                TextView textView4 = (TextView) baseViewHolder.getView(R.id.tv_dynamic_more);
                if (list == null || list.isEmpty()) {
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView4.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                } else {
                    textView4.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textView.setText(list.get(0).content);
                    if (list.size() >= 2) {
                        textView2.setVisibility(View.VISIBLE);
                        textView2.setText(list.get(1).content);
                    }
                }
                baseViewHolder.addOnClickListener(R.id.tv_dynamic_content);
                baseViewHolder.addOnClickListener(R.id.tv_dynamic_content_2);
                baseViewHolder.addOnClickListener(R.id.tv_dynamic_more);
            } else if (itemViewType == 5) {
                BGABanner bGABanner2 = (BGABanner) baseViewHolder.getView(R.id.iv_live_banner);
                List<LiveListItemEntity> list2 = liveEntity.liveCarousel;
                if (list2 != null && !list2.isEmpty()) {
                    bGABanner2.setIndicatorVisibility(false);
                    bGABanner2.setAllowUserScrollable(false);
                    if (list2.size() <= 1) {
                        z = false;
                    }
                    bGABanner2.setAutoPlayAble(z);
                    bGABanner2.setData(R.layout.fq_item_list_live_view_new, list2, (List<String>) null);
                    bGABanner2.setAdapter(new HomeLiveBannerAdapter(this.mContext));
                    bGABanner2.setDelegate(new C2246a());
                }
            }
        }
    }

    /* renamed from: a */
    public /* synthetic */ void m17002a(BGABanner bGABanner, ImageView imageView, BannerEntity bannerEntity, int i) {
        GlideUtils.loadAdBannerImageForRoundView(this.mContext, imageView, bannerEntity.img, R.drawable.fq_shape_default_banner_cover_bg);
    }

    /* renamed from: a */
    public final LiveEntity m17005a(LiveListItemEntity liveListItemEntity) {
        LiveEntity liveEntity = new LiveEntity();
        liveEntity.markerUrl = liveListItemEntity.markerUrl;
        liveEntity.expGrade = liveListItemEntity.expGrade;
        liveEntity.openId = liveListItemEntity.openId;
        liveEntity.liveType = liveListItemEntity.liveType;
        liveEntity.chargeType = liveListItemEntity.chargeType;
        liveEntity.coverIdentityUrl = liveListItemEntity.coverIdentityUrl;
        liveEntity.liveCount = liveListItemEntity.liveCount;
        liveEntity.pullStreamUrl = liveListItemEntity.pullStreamUrl;
        liveEntity.appId = liveListItemEntity.appId;
        liveEntity.popularity = liveListItemEntity.popularity;
        liveEntity.nickname = liveListItemEntity.nickname;
        liveEntity.tag = liveListItemEntity.tag;
        liveEntity.liveStatus = liveListItemEntity.liveStatus;
        liveEntity.ticketPrice = liveListItemEntity.ticketPrice;
        liveEntity.sex = liveListItemEntity.sex;
        liveEntity.avatar = liveListItemEntity.avatar;
        liveEntity.userId = liveListItemEntity.userId;
        liveEntity.liveId = liveListItemEntity.liveId;
        liveEntity.topic = liveListItemEntity.topic;
        liveEntity.liveCoverUrl = liveListItemEntity.liveCoverUrl;
        liveEntity.otherChannelCoverIdentityUrl = liveListItemEntity.otherChannelCoverIdentityUrl;
        liveEntity.isPrivateAnchor = liveListItemEntity.isPrivateAnchor;
        liveEntity.privateAnchorPrice = liveListItemEntity.privateAnchorPrice;
        return liveEntity;
    }
}

