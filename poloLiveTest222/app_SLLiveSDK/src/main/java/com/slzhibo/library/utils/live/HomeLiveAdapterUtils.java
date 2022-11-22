package com.slzhibo.library.utils.live;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.StringUtils;

public class HomeLiveAdapterUtils {
    @SuppressLint("WrongConstant")
    public static void convert(Context context, BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        baseViewHolder.setText(R.id.tv_live_title, StringUtils.formatStrLen(liveEntity.topic, 15)).setText(R.id.tv_personal, liveEntity.getLivePopularityStr()).setText(R.id.tv_nick_name, liveEntity.nickname).setText(R.id.tv_pre_notice, getPreNoticeStr(liveEntity)).setVisible(R.id.rl_live_status, !isLiving(liveEntity)).setGone(R.id.iv_live_dot, !liveEntity.isAd).setVisible(R.id.rl_pre_notice_bg, isPreNotice(liveEntity)).setVisible(R.id.tv_ad, liveEntity.isAd).setVisible(R.id.tv_personal, !liveEntity.isAd);
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
        if (!liveEntity.isPayLiveTicket() || !isLiving(liveEntity) || liveEntity.isAd) {
            textView.setVisibility(View.INVISIBLE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getString(R.string.fq_pay_ticket_price, AppUtils.formatMoneyUnitStr(context, liveEntity.getPayLivePrice(), false)));
    }

    public static boolean isPreNotice(LiveEntity liveEntity) {
        return !liveEntity.isAd && !TextUtils.isEmpty(liveEntity.herald) && !liveEntity.isOnLiving();
    }

    public static String getPreNoticeStr(LiveEntity liveEntity) {
        if (liveEntity.isAd) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(liveEntity.herald);
        if (!TextUtils.isEmpty(liveEntity.publishTime)) {
            sb.append("\n");
            sb.append("(");
            sb.append(DateUtils.getShortTime(NumberUtils.string2long(liveEntity.publishTime) * 1000));
            sb.append(")");
        }
        return sb.toString();
    }

    public static boolean isLiving(LiveEntity liveEntity) {
        if (liveEntity.isAd) {
            return true;
        }
        return liveEntity.isOnLiving();
    }
}
