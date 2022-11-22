package com.slzhibo.library.ui.adapter;

//import android.support.v4.content.ContextCompat;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.R.color;
import com.slzhibo.library.R.drawable;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.ui.view.custom.UserGradeView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;

public class LiveVipAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {
    public LiveVipAdapter(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void convert(BaseViewHolder baseViewHolder, UserEntity userEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, userEntity.getName()).setImageResource(R.id.iv_badge, AppUtils.getNobilityBadgeDrawableRes(userEntity.getNobilityType())).setImageResource(R.id.iv_guard, AppUtils.isYearGuard(userEntity.getGuardType()) ? drawable.fq_ic_live_msg_year_guard_big : drawable.fq_ic_live_msg_mouth_guard_big);
        int i = 0;
        ((ImageView) baseViewHolder.getView(R.id.iv_badge)).setVisibility(AppUtils.isNobilityUser(userEntity.getNobilityType()) ? 0 : 8);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_guard);
        if (!AppUtils.isGuardUser(userEntity.getGuardType())) {
            i = 8;
        }
        imageView.setVisibility(i);
        GlideUtils.loadAvatar(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), userEntity.getAvatar(), 3, ContextCompat.getColor(this.mContext, color.fq_colorWhite));
        ((UserGradeView) baseViewHolder.getView(R.id.grade_view)).initUserGradeMsg(userEntity.getExpGrade(), true);
    }
}
