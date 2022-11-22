package com.slzhibo.library.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.R.color;
import com.slzhibo.library.R.drawable;
import com.slzhibo.library.R.string;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.ui.view.custom.UserNickNameGradeView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.UserInfoManager;

public class DedicateTopAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    private boolean isDialog = false;

    private boolean isShowBrand(int i) {
        return i == 0 || i == 1 || i == 2;
    }

    public DedicateTopAdapter(int i) {
        super(i);
    }

    public DedicateTopAdapter(int i, boolean z) {
        super(i);
        this.isDialog = z;
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        Drawable drawable;
        int adapterPosition = baseViewHolder.getAdapterPosition();
        boolean isRankHideBoolean = anchorEntity.isRankHideBoolean();
        int i = 0;
        baseViewHolder.setText(R.id.tv_income, this.mContext.getString(string.fq_sl_money_reward, anchorEntity.expend)).setBackgroundRes(R.id.fl_avatar_bg, getAvatarBgResId(adapterPosition)).setImageResource(R.id.iv_brand, getBrandResId(adapterPosition)).setVisible(R.id.iv_brand, isShowBrand(adapterPosition)).setVisible(R.id.tv_num, true ^ isShowBrand(adapterPosition)).setVisible(R.id.ll_mystery_bg, isRankHideBoolean).setVisible(R.id.tv_me, isRankHideBoolean && TextUtils.equals(anchorEntity.userId, UserInfoManager.getInstance().getUserId()));
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.ll_content_bg);
        if (isRankHideBoolean) {
            drawable = ContextCompat.getDrawable(this.mContext, this.isDialog ? R.drawable.fq_shape_nobility_stealth_top_bg_2 : R.drawable.fq_shape_nobility_stealth_top_bg);
        } else {
            drawable = new ColorDrawable(ContextCompat.getColor(this.mContext, color.fq_color_transparent));
        }
        linearLayout.setBackground(drawable);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_avatar);
        if (isRankHideBoolean) {
            imageView.setImageResource(R.drawable.fq_ic_nobility_top_stealth);
        } else {
            GlideUtils.loadAvatar(this.mContext, imageView, anchorEntity.avatar);
        }
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) baseViewHolder.getView(R.id.user_nickname);
        if (isRankHideBoolean) {
            i = 8;
        }
        userNickNameGradeView.setVisibility(i);
        userNickNameGradeView.initData(anchorEntity.nickname, this.isDialog ? color.fq_colorWhite : color.fq_colorBlack, anchorEntity.sex, anchorEntity.expGrade, anchorEntity.nobilityType);
        AppUtils.formatTvNumTypeface(this.mContext, (TextView) baseViewHolder.getView(R.id.tv_num), getNumStr(adapterPosition));
    }

    private String getNumStr(int i) {
        if (i >= 3) {
            return "" + (i + 1);
        }
        return "" + i;
    }

    private int getAvatarBgResId(int i) {
        if (i == 0) {
            return drawable.fq_shape_top_tag_gold_circle;
        }
        if (i == 1) {
            return drawable.fq_shape_top_tag_silver_circle;
        }
        if (i != 2) {
            return drawable.fq_shape_top_tag_gray_circle;
        }
        return drawable.fq_shape_top_tag_copper_circle;
    }

    private int getBrandResId(int i) {
        if (i == 0) {
            return drawable.fq_ic_top_brand_no_1;
        }
        if (i == 1) {
            return drawable.fq_ic_top_brand_no_2;
        }
        if (i != 2) {
            return drawable.fq_ic_top_brand_no_3;
        }
        return drawable.fq_ic_top_brand_no_3;
    }
}
