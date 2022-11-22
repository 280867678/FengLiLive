package com.slzhibo.library.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.StringUtils;

public class RecommendAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    public RecommendAdapter(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, StringUtils.formatStrLen(anchorEntity.nickname, 6)).setText(R.id.tv_attention, anchorEntity.isAttention() ? R.string.fq_home_btn_attention_yes : R.string.fq_home_btn_attention).setVisible(R.id.iv_live, AppUtils.isLiving(anchorEntity.isLiving)).addOnClickListener(R.id.tv_attention).getView(R.id.tv_attention).setSelected(anchorEntity.isAttention());
        GlideUtils.loadAvatar(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        GlideUtils.loadLivingGif(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_live));
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_gender_sex);
        if (AppUtils.getGenderRes(anchorEntity.sex) != -1) {
            imageView.setImageResource(AppUtils.getGenderRes(anchorEntity.sex));
        }
    }
}
