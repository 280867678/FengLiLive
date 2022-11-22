package com.slzhibo.library.ui.adapter;



import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.SystemUtils;

/* renamed from: com.slzhibo.library.ui.adapter.RankEnterAvatarsAdapter */
/* loaded from: classes2.dex */
public class RankEnterAvatarsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RankEnterAvatarsAdapter(int i) {
        super(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0088  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public void convert(com.slzhibo.library.utils.adapter.BaseViewHolder r12, java.lang.String r13) {
//        /*
//            r11 = this;
//            int r1 = com.slzhibo.library.R$id.fl_avatar_bg
//            android.view.View r6 = r12.getView(r1)
//            int r1 = com.slzhibo.library.R$id.iv_avatar
//            android.view.View r1 = r12.getView(r1)
//            r7 = r1
//            android.widget.ImageView r7 = (android.widget.ImageView) r7
//            r1 = 1077936128(0x40400000, float:3.0)
//            float r1 = p067e.p304t.p305a.p432i.SystemUtils.m1600a(r1)
//            java.util.List r2 = r11.getData()
//            int r2 = r2.size()
//            r3 = 1084227584(0x40a00000, float:5.0)
//            r4 = 1
//            r5 = 0
//            r8 = 0
//            r9 = 0
//            if (r2 != r4) goto L_0x0034
//            float r1 = p067e.p304t.p305a.p432i.SystemUtils.m1600a(r3)
//            android.content.Context r2 = r11.mContext
//            int r3 = com.slzhibo.library.R$drawable.fq_ic_top_crown
//            android.graphics.drawable.Drawable r5 = android.support.p001v4.content.ContextCompat.getDrawable(r2, r3)
//        L_0x0031:
//            r9 = r1
//            r10 = 0
//            goto L_0x0064
//        L_0x0034:
//            int r2 = r12.getLayoutPosition()
//            if (r2 != 0) goto L_0x003d
//        L_0x003a:
//            r9 = r1
//            r10 = 1
//            goto L_0x0064
//        L_0x003d:
//            int r2 = r12.getLayoutPosition()
//            java.util.List r8 = r11.getData()
//            int r8 = r8.size()
//            int r8 = r8 - r4
//            r10 = -1046478848(0xffffffffc1a00000, float:-20.0)
//            if (r2 != r8) goto L_0x005f
//            android.content.Context r1 = r11.mContext
//            int r2 = com.slzhibo.library.R$drawable.fq_ic_top_crown
//            android.graphics.drawable.Drawable r5 = android.support.p001v4.content.ContextCompat.getDrawable(r1, r2)
//            float r1 = p067e.p304t.p305a.p432i.SystemUtils.m1600a(r3)
//            float r8 = p067e.p304t.p305a.p432i.SystemUtils.m1600a(r10)
//            goto L_0x0031
//        L_0x005f:
//            float r8 = p067e.p304t.p305a.p432i.SystemUtils.m1600a(r10)
//            goto L_0x003a
//        L_0x0064:
//            r6.setBackground(r5)
//            int r1 = com.slzhibo.library.R$id.fl_root
//            android.view.View r1 = r12.getView(r1)
//            r2 = 0
//            r3 = 0
//            int r4 = (int) r8
//            r5 = 0
//            r0 = r11
//            r0.m16911a(r1, r2, r3, r4, r5)
//            int r0 = (int) r9
//            r11.m16912a(r6, r0)
//            if (r10 == 0) goto L_0x0088
//            android.content.Context r0 = r11.mContext
//            r1 = 4
//            int r2 = com.slzhibo.library.R$color.fq_colorWhite
//            int r2 = android.support.p001v4.content.ContextCompat.getColor(r0, r2)
//            com.slzhibo.library.utils.GlideUtils.loadAvatar(r0, r7, r13, r1, r2)
//            goto L_0x008f
//        L_0x0088:
//            android.content.Context r0 = r11.mContext
//            int r1 = com.slzhibo.library.R$drawable.fq_ic_placeholder_avatar
//            com.slzhibo.library.utils.GlideUtils.loadAvatar(r0, r7, r13, r1)
//        L_0x008f:
//            return
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.p018ui.adapter.RankEnterAvatarsAdapter.convert(com.slzhibo.library.utils.adapter.BaseViewHolder, java.lang.String):void");
//    }




    public void convert(BaseViewHolder baseViewHolder, String str) {
        boolean z;
        float f;
        View view = baseViewHolder.getView(R.id.fl_avatar_bg);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_avatar);
        float dp2px = SystemUtils.dp2px(3.0f);
        Drawable drawable = null;
        float f2 = 0.0f;
        if (getData().size() == 1) {
            f = SystemUtils.dp2px(5.0f);
            drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_top_crown);
        } else {
            if (baseViewHolder.getLayoutPosition() != 0) {
                if (baseViewHolder.getLayoutPosition() == getData().size() - 1) {
                    drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_top_crown);
                    f = SystemUtils.dp2px(5.0f);
                    f2 = SystemUtils.dp2px(-20.0f);
                } else {
                    f2 = SystemUtils.dp2px(-20.0f);
                }
            }
            f = dp2px;
            z = true;
            view.setBackground(drawable);
            setMargins(baseViewHolder.getView(R.id.fl_root), 0, 0, (int) f2, 0);
            setPadding(view, (int) f);
            if (!z) {
                Context context = this.mContext;
                GlideUtils.loadAvatar(context, imageView, str, 4, ContextCompat.getColor(context, R.color.fq_colorWhite));
                return;
            }
            GlideUtils.loadAvatar(this.mContext, imageView, str, R.drawable.fq_ic_placeholder_avatar);
            return;
        }
        z = false;
        view.setBackground(drawable);
        setMargins(baseViewHolder.getView(R.id.fl_root), 0, 0, (int) f2, 0);
        setPadding(view, (int) f);
        if (!z) {
        }
    }
    
    
    





    /**
     * m16911a
     * @param view
     * @param i
     * @param i2
     * @param i3
     * @param i4
     */
    public void setMargins(View view, int i, int i2, int i3, int i4) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(i, i2, i3, i4);
            view.requestLayout();
        }
    }

    /**
     * m16912a
     * @param view
     * @param i
     */
    public void setPadding(View view, int i) {
        view.setPadding(i, i, i, i);
    }
    
    
}

