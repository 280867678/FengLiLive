package com.slzhibo.library.ui.adapter;

//import android.support.annotation.DrawableRes;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

//import com.slzhibo.library.R$drawable;
//import com.slzhibo.library.R$id;
import androidx.annotation.DrawableRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.ui.view.widget.rxbinding2.view.RxView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class UserAvatarListAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {
    private UserListClickListener listener;

    /* loaded from: classes3.dex */
    public interface UserListClickListener {
        void onUserClick(UserEntity userEntity);
    }

    public UserAvatarListAdapter(int i) {
        super(i);
    }

    @SuppressLint("WrongConstant")
    public void convert(BaseViewHolder baseViewHolder, final UserEntity userEntity) {
        RxView.clicks(baseViewHolder.itemView).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(new SimpleRxObserver<Object>() { // from class: com.slzhibo.library.ui.adapter.UserAvatarListAdapter.1
            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                if (UserAvatarListAdapter.this.listener != null) {
                    UserAvatarListAdapter.this.listener.onUserClick(userEntity);
                }
            }
        });
        if (userEntity != null) {
            GlideUtils.loadAvatar(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_user_avatar), userEntity.getAvatar());
            ImageView imageView = (ImageView) baseViewHolder.getView(R.id.fq_year_guard_icon);
            ImageView imageView2 = (ImageView) baseViewHolder.getView(R.id.iv_badge);
            int i = 0;
            imageView.setVisibility(userEntity.getGuardType() > NumberUtils.string2int("0") ? View.VISIBLE : View.INVISIBLE);
            imageView.setImageResource(userEntity.getGuardType() == NumberUtils.string2int("3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard);
            if (!AppUtils.isNobilityUser(userEntity.getNobilityType())) {
                i = 4;
            }
            imageView2.setVisibility(i);
            imageView2.setImageResource(getBadgeDrawableRes(userEntity.getNobilityType()));
        }
    }

    @DrawableRes
    private int getBadgeDrawableRes(int i) {
        switch (i) {
            case 1:
                return R.drawable.fq_ic_nobility_avatar_label_1;
            case 2:
                return R.drawable.fq_ic_nobility_avatar_label_2;
            case 3:
                return R.drawable.fq_ic_nobility_avatar_label_3;
            case 4:
                return R.drawable.fq_ic_nobility_avatar_label_4;
            case 5:
                return R.drawable.fq_ic_nobility_avatar_label_5;
            case 6:
                return R.drawable.fq_ic_nobility_avatar_label_6;
            case 7:
                return R.drawable.fq_ic_nobility_avatar_label_7;
            default:
                return R.drawable.fq_ic_nobility_avatar_label_1;
        }
    }

    public void setOnItemClickListener(UserListClickListener userListClickListener) {
        this.listener = userListClickListener;
    }
}
