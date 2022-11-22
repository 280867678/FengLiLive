package com.slzhibo.library.utils;



import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.LiveEntity;


/* renamed from: e.t.a.i.k0.h */
/* loaded from: classes2.dex */
public class HomeLiveAdapterUtils {
    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public static void m1334a(Context context, BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        baseViewHolder.setText(R.id.tv_live_title, C5970b0.m1622a(liveEntity.topic, 15)).setText(R.id.tv_personal, liveEntity.getLivePopularityStr()).setText(R.id.tv_nick_name, liveEntity.nickname).setText(R.id.tv_pre_notice, m1333a(liveEntity)).setVisible(R.id.rl_live_status, !m1332b(liveEntity)).setGone(R.id.iv_live_dot, !liveEntity.isAd).setVisible(R.id.rl_pre_notice_bg, m1331c(liveEntity)).setVisible(R.id.tv_ad, liveEntity.isAd).setVisible(R.id.tv_personal, !liveEntity.isAd);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_pendant);
        String str = liveEntity.pendantUrl;
        int i = 8;
        imageView.setVisibility(TextUtils.isEmpty(str) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(str)) {
            GlideUtils.loadImageNormal(context, imageView, str);
        }
        ImageView imageView2 = (ImageView) baseViewHolder.getView(R.id.iv_label);
        String str2 = liveEntity.markerUrl;
        imageView2.setVisibility(TextUtils.isEmpty(str2) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(str2)) {
            GlideUtils.loadImageNormal(context, imageView2, str2);
        }
        ImageView imageView3 = (ImageView) baseViewHolder.getView(R.id.iv_cover_identity);
        String coverIdentityUrl = liveEntity.getCoverIdentityUrl();
        if (!TextUtils.isEmpty(coverIdentityUrl)) {
            i = 0;
        }
        imageView3.setVisibility(i);
        if (!TextUtils.isEmpty(coverIdentityUrl)) {
            GlideUtils.loadImageNormal(context, imageView3, coverIdentityUrl);
        }
        GlideUtils.loadAdBannerImageForRoundView(context, (ImageView) baseViewHolder.getView(R.id.iv_cover), TextUtils.isEmpty(liveEntity.liveCoverUrl) ? liveEntity.avatar : liveEntity.liveCoverUrl, R.drawable.fq_ic_placeholder_corners);
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_pay_ticket);
        if (!liveEntity.isPayLiveTicket() || !m1332b(liveEntity) || liveEntity.isAd) {
            textView.setVisibility(4);
            return;
        }
        textView.setVisibility(0);
        textView.setText(context.getString(R.string.fq_pay_ticket_price, AppUtils.formatMoneyUnitStr(context, liveEntity.getPayLivePrice(), false)));
    }

    /* renamed from: b */
    public static boolean m1332b(LiveEntity liveEntity) {
        if (liveEntity.isAd) {
            return true;
        }
        return liveEntity.isOnLiving();
    }

    /* renamed from: c */
    public static boolean m1331c(LiveEntity liveEntity) {
        return !liveEntity.isAd && !TextUtils.isEmpty(liveEntity.herald) && !liveEntity.isOnLiving();
    }

    /* renamed from: a */
    public static String m1333a(LiveEntity liveEntity) {
        if (liveEntity.isAd) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(liveEntity.herald);
        if (!TextUtils.isEmpty(liveEntity.publishTime)) {
            sb.append("\n");
            sb.append("(");
            sb.append(C5991i.m1487b(NumberUtils.string2long(liveEntity.publishTime) * 1000));
            sb.append(")");
        }
        return sb.toString();
    }
}

