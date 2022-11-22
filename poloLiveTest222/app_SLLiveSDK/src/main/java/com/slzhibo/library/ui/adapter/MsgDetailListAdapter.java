package com.slzhibo.library.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.R.color;
import com.slzhibo.library.R.drawable;
import com.slzhibo.library.R.layout;
import com.slzhibo.library.model.db.MsgDetailListEntity;
import com.slzhibo.library.ui.activity.mylive.AwardHistoryActivity;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class MsgDetailListAdapter extends BaseMultiItemQuickAdapter<MsgDetailListEntity, BaseViewHolder> {
    public MsgDetailListAdapter(List<MsgDetailListEntity> list) {
        super(list);
        addItemType();
    }

    private void addItemType() {
        addItemType(1, layout.fq_item_my_msg);
        addItemType(2, layout.fq_item_other_msg);
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void convert(BaseViewHolder baseViewHolder, MsgDetailListEntity msgDetailListEntity) {
        int itemViewType = baseViewHolder.getItemViewType();
        if (itemViewType == 1) {
            int i = 0;
            if (msgDetailListEntity.status == -1 && System.currentTimeMillis() - NumberUtils.string2long(msgDetailListEntity.time) > 10000) {
                msgDetailListEntity.status = 0;
            }
            ImageView imageView = (ImageView) baseViewHolder.getView(R.id.fq_loading_img);
            imageView.setImageResource(msgDetailListEntity.status == -1 ? drawable.fq_ic_private_msg_loading_anim : drawable.fq_ic_private_msg_send_fail);
            if (msgDetailListEntity.status == 1) {
                i = 4;
            }
            imageView.setVisibility(i);
            TextView textView = (TextView) baseViewHolder.getView(R.id.tv_my_msg_content);
            if (msgDetailListEntity.isRedLabelFlag()) {
                textView.setText(getSpannableString(msgDetailListEntity));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            textView.setText(msgDetailListEntity.msg);
        } else if (itemViewType == 2) {
            GlideUtils.loadAvatar(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), msgDetailListEntity.targetAvatar, 6, ContextCompat.getColor(this.mContext, color.fq_colorWhite));
            TextView textView2 = (TextView) baseViewHolder.getView(R.id.tv_other_msg_content);
            if (msgDetailListEntity.isRedLabelFlag()) {
                textView2.setText(getSpannableString(msgDetailListEntity));
                textView2.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            textView2.setText(msgDetailListEntity.msg);
        }
    }

    private void ensureMessageListNotOver(MsgDetailListEntity msgDetailListEntity) {
        if (this.mData.size() + 1 >= 150) {
            this.mData.removeAll(new ArrayList(this.mData.subList(0, 50)));
            notifyItemRangeRemoved(getHeaderLayoutCount() + 0, 50);
            DBUtils.deleteOldPrivateMsgDetailList(msgDetailListEntity.targetId);
        }
    }

    public void addMsg(MsgDetailListEntity msgDetailListEntity) {
        synchronized (this) {
            ensureMessageListNotOver(msgDetailListEntity);
            this.mData.add(msgDetailListEntity);
            notifyItemInserted(this.mData.size());
            if (!getRecyclerView().canScrollVertically(1)) {
                ((LinearLayoutManager) getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mData.size() - 1, 0);
            }
        }
    }

    public void changeMsgStatus(String str, int i) {
        synchronized (this) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.mData.size()) {
                    break;
                }
                MsgDetailListEntity msgDetailListEntity = (MsgDetailListEntity) this.mData.get(i2);
                if (TextUtils.equals(msgDetailListEntity.messageId, str)) {
                    msgDetailListEntity.status = i;
                    notifyItemChanged(i2 + getHeaderLayoutCount());
                    break;
                }
                i2++;
            }
        }
    }

    private SpannableString getSpannableString(MsgDetailListEntity msgDetailListEntity) {
        String str = msgDetailListEntity.msg;
        SpannableString spannableString = new SpannableString(str);
        try {
            String str2 = msgDetailListEntity.flagContent;
            int indexOf = str.indexOf(str2);
            spannableString.setSpan(new ClickableSpan() {
                /* class com.slzhibo.library.ui.adapter.MsgDetailListAdapter.AnonymousClass1 */

                public void updateDrawState(@NonNull TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#FF5F52"));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(@NonNull View view) {
                    Intent intent = new Intent(( MsgDetailListAdapter.this).mContext, AwardHistoryActivity.class);
                    intent.putExtra(ConstantUtils.RESULT_FLAG, true);
                    ( MsgDetailListAdapter.this).mContext.startActivity(intent);
                }
            }, indexOf, str2.length() + indexOf, 33);
        } catch (Exception unused) {
        }
        return spannableString;
    }
}
