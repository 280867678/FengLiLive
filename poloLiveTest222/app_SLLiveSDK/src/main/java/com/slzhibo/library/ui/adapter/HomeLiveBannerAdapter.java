package com.slzhibo.library.ui.adapter;


import android.annotation.SuppressLint;
import android.content.Context;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.model.LiveListItemEntity;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.StringUtils;


/* renamed from: e.t.a.h.b.h */
/* loaded from: classes2.dex */
public class HomeLiveBannerAdapter implements BGABanner.Adapter<View, LiveListItemEntity> {

    /* renamed from: a */
    public Context f18024a;

    public HomeLiveBannerAdapter(Context context) {
        this.f18024a = context;
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    @SuppressLint("WrongConstant")
    public void fillBannerItem(BGABanner bGABanner, View view, @Nullable LiveListItemEntity liveListItemEntity, int i) {
        ((TextView) view.findViewById(R.id.tv_live_title)).setText(StringUtils.formatStrLen(liveListItemEntity.topic, 15));
        TextView textView = (TextView) view.findViewById(R.id.tv_personal);
        textView.setText(liveListItemEntity.getLivePopularityStr());
        textView.setVisibility(!liveListItemEntity.isAd ? View.VISIBLE : View.INVISIBLE);
        view.findViewById(R.id.tv_ad).setVisibility(liveListItemEntity.isAd ? View.VISIBLE : View.INVISIBLE);
        ((TextView) view.findViewById(R.id.tv_nick_name)).setText(liveListItemEntity.nickname);
        int i2 = 8;
        view.findViewById(R.id.iv_live_dot).setVisibility(!liveListItemEntity.isAd ? View.VISIBLE : View.GONE);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_pendant);
        String str = liveListItemEntity.pendantUrl;
        imageView.setVisibility(TextUtils.isEmpty(str) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(str)) {
            GlideUtils.loadImageNormal(this.f18024a, imageView, str);
        }
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_label);
        String str2 = liveListItemEntity.markerUrl;
        imageView2.setVisibility(TextUtils.isEmpty(str2) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(str2)) {
            GlideUtils.loadImageNormal(this.f18024a, imageView2, str2);
        }
        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv_cover_identity);
        String coverIdentityUrl = liveListItemEntity.getCoverIdentityUrl();
        if (!TextUtils.isEmpty(coverIdentityUrl)) {
            i2 = 0;
        }
        imageView3.setVisibility(i2);
        if (!TextUtils.isEmpty(coverIdentityUrl)) {
            GlideUtils.loadImageNormal(this.f18024a, imageView3, coverIdentityUrl);
        }
        ImageView imageView4 = (ImageView) view.findViewById(R.id.iv_cover);
        String str3 = TextUtils.isEmpty(liveListItemEntity.liveCoverUrl) ? liveListItemEntity.avatar : liveListItemEntity.liveCoverUrl;
        if (liveListItemEntity.isAd) {
            GlideUtils.loadAdBannerImageForRoundView(this.f18024a, imageView4, str3, R.drawable.fq_ic_placeholder_corners);
            return;
        }
        GlideUtils.loadImage(this.f18024a, imageView4, str3, R.drawable.fq_ic_placeholder_corners);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_pay_ticket);
        if (!liveListItemEntity.isPayLiveTicket() || !liveListItemEntity.isOnLiving()) {
            textView2.setVisibility(View.INVISIBLE);
            return;
        }
        textView2.setVisibility(View.VISIBLE);
        Context context = this.f18024a;
        textView2.setText(context.getString(R.string.fq_pay_ticket_price, AppUtils.formatMoneyUnitStr(context, liveListItemEntity.getPayLivePrice(), false)));
    }
}

