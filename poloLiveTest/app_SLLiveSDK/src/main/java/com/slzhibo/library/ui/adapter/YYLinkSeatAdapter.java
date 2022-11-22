package com.slzhibo.library.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.ui.view.widget.MicVoiceView;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.StringUtils;

public class YYLinkSeatAdapter extends BaseQuickAdapter<YYLinkApplyEntity, BaseViewHolder> {
    public YYLinkSeatAdapter(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, YYLinkApplyEntity yYLinkApplyEntity) {
        if (yYLinkApplyEntity != null) {
            baseViewHolder.setText(R.id.tv_seat_num, yYLinkApplyEntity.seat).setText(R.id.tv_name, getNameStr(yYLinkApplyEntity)).setVisible(R.id.iv_anchor_flag, TextUtils.equals(yYLinkApplyEntity.seat, "1")).setVisible(R.id.iv_mute, yYLinkApplyEntity.isMuteStatus()).setVisible(R.id.iv_mute_bg, yYLinkApplyEntity.isMuteStatus()).setVisible(R.id.iv_lock, yYLinkApplyEntity.isLockSeatStatus()).addOnClickListener(R.id.rl_item_view);
            TextView textView = (TextView) baseViewHolder.getView(R.id.tv_like);
            ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_avatar);
            ImageView imageView2 = (ImageView) baseViewHolder.getView(R.id.iv_safa);
            MicVoiceView micVoiceView = (MicVoiceView) baseViewHolder.getView(R.id.mic_view);
            if (TextUtils.isEmpty(yYLinkApplyEntity.userId) || yYLinkApplyEntity.isLockSeatStatus()) {
                textView.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(yYLinkApplyEntity.likeCount);
                imageView2.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                Context context = this.mContext;
                GlideUtils.loadAvatar(context, imageView, yYLinkApplyEntity.userAvatar, 2, ContextCompat.getColor(context, R.color.fq_colorWhite));
            }
            if (!yYLinkApplyEntity.isSpeak || yYLinkApplyEntity.isMuteStatus() || TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
                micVoiceView.setVisibility(View.INVISIBLE);
                micVoiceView.stop();
                return;
            }
            micVoiceView.setVisibility(View.VISIBLE);
            micVoiceView.start();
        }
    }

    private String getNameStr(YYLinkApplyEntity yYLinkApplyEntity) {
        if (yYLinkApplyEntity == null || TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
            return this.mContext.getString(R.string.fq_text_list_empty_waiting);
        }
        return StringUtils.formatStrLen(yYLinkApplyEntity.userName, 5);
    }

    @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter, android.support.v7.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return (long) this.mData.get(i).hashCode();
    }
}
