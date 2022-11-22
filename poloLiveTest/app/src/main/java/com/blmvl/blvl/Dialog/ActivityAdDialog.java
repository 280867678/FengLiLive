package com.blmvl.blvl.Dialog;

//package p067e.p103c.p104a.p108f;

import android.content.Context;
//import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.blmvl.blvl.ImgLoader.ImgLoader;
import com.blmvl.blvl.R;
import com.blmvl.blvl.bean.ConfigBean;
import com.blmvl.blvl.util.CustomWordUtil;
import com.blmvl.blvl.util.JumpUtil;
import com.blmvl.blvl.util.LogUtil;
//import p067e.p103c.p104a.p113k.CustomWordUtil;
//import p067e.p103c.p104a.p113k.ImgLoader;
//import p067e.p103c.p104a.p113k.JumpUtil;
//import p067e.p103c.p104a.p113k.LogUtil;
//import p067e.p130f.p131a.p132a.AbsDialog;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.f.w */
/* loaded from: classes.dex */
public class ActivityAdDialog extends AbsDialog implements View.OnClickListener {

    /* renamed from: b */
    public Context f12898b;

    /* renamed from: c */
    public ConfigBean f12899c;

    /* renamed from: d */
    public ImageView f12900d;

    /* renamed from: e */
    public AbstractC4171a f12901e;

    /* compiled from: ActivityAdDialog.java */
    /* renamed from: e.c.a.f.w$a */
    /* loaded from: classes.dex */
    public interface AbstractC4171a {
        /* renamed from: c */
        void m9816c();
    }

    public ActivityAdDialog(@NonNull Context context, ConfigBean configBean) {
        this(context, (int) R.style.CustomDialogWithBg);
        this.f12898b = context;
        this.f12899c = configBean;
    }

    /* renamed from: a */
    public /* synthetic */ void m9817a(View view) {
        AbstractC4171a aVar = this.f12901e;
        if (aVar != null) {
            aVar.m9816c();
        }
        dismiss();
        LogUtil.m9410a("BL_DIALOG_ACTIVITY_AD_CLOSE");
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: b */
    public void mo9243b(Window window) {
        ConfigBean configBean;
        LogUtil.m9410a("BL_DIALOG_ACTIVITY_AD");
        if (window != null && (configBean = this.f12899c) != null && !TextUtils.isEmpty(configBean.getActivityImg())) {
            this.f12900d = (ImageView) window.findViewById(R.id.img_activity_ad);
            ImgLoader.m9430a(this.f12898b, this.f12899c.getActivityImg(), this.f12900d, R.mipmap.img_activity_cover_default);
            this.f12900d.setOnClickListener(this);
            ((ImageView) window.findViewById(R.id.img_cancel)).setOnClickListener(new View.OnClickListener() { // from class: e.c.a.f.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAdDialog.this.m9817a(view);
                }
            });
        }
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: d */
    public int mo9241d() {
        return R.layout.dialog_activity_ad;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            dismiss();
            if (this.f12901e != null) {
                this.f12901e.m9816c();
            }
            if (this.f12899c != null) {
                JumpUtil.m9417a().m9413a(this.f12898b, this.f12899c.getActivityType(), CustomWordUtil.m9462a(this.f12899c.getActivityUrl()));
            }
            LogUtil.m9410a("BL_DIALOG_ACTIVITY_AD_CLICK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActivityAdDialog(@NonNull Context context, int i) {
        super(context, i);
    }
}

