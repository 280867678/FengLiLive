package com.blmvl.blvl.Dialog;

//package p067e.p103c.p104a.p108f;

import android.content.Context;
//import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blmvl.blvl.R;
import com.blmvl.blvl.util.LogUtil;
import com.blmvl.blvl.util.SpecialTxtColorUtil;
//import com.tencent.qcloud.core.util.IOUtils;
//import p067e.p103c.p104a.p113k.LogUtil;
//import p067e.p103c.p104a.p113k.SpecialTxtColorUtil;
//import p067e.p130f.p131a.p132a.AbsDialog;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.f.i0 */
/* loaded from: classes.dex */
public class NoticeMsgDialog extends AbsDialog {

    /* renamed from: b */
    public String f12779b;

    /* renamed from: c */
    public TextView f12780c;

    /* renamed from: d */
    public TextView f12781d;

    /* renamed from: e */
    public TextView f12782e;

    /* renamed from: f */
    public AbstractC4135a f12783f;

    /* compiled from: NoticeMsgDialog.java */
    /* renamed from: e.c.a.f.i0$a */
    /* loaded from: classes.dex */
    public interface AbstractC4135a {
        /* renamed from: a */
        void mo9911a();

        /* renamed from: c */
        void mo9910c();
    }

    public NoticeMsgDialog(@NonNull Context context, String str) {
        this(context, (int) R.style.CustomDialogWithBg);
        this.f12779b = str;
    }

    /* renamed from: a */
    public void m9913a(AbstractC4135a aVar) {
        this.f12783f = aVar;
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: b */
    public void mo9243b(Window window) {
        LogUtil.m9410a("BL_DIALOG_NOTICE_MSG");
        if (window != null) {
            this.f12780c = (TextView) window.findViewById(R.id.tv_content);
            this.f12781d = (TextView) window.findViewById(R.id.btn_app_center);
            this.f12782e = (TextView) window.findViewById(R.id.btn_confirm);
            if (!TextUtils.isEmpty(this.f12779b)) {
                this.f12780c.setText(SpecialTxtColorUtil.m9512a(this.f12779b.replaceAll("#", "\n"), -206539));
            }
            this.f12780c.setAutoLinkMask(1);
            this.f12780c.setMovementMethod(ScrollingMovementMethod.getInstance());
            this.f12782e.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.f.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NoticeMsgDialog.this.m9914a(view);
                }
            });
            this.f12781d.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.f.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NoticeMsgDialog.this.m9912b(view);
                }
            });
        }
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: d */
    public int mo9241d() {
        return R.layout.dialog_notice_msg;
    }

    /* renamed from: a */
    public /* synthetic */ void m9914a(View view) {
        dismiss();
        LogUtil.m9410a("BL_DIALOG_NOTICE_MSG_CONFIRM");
        AbstractC4135a aVar = this.f12783f;
        if (aVar != null) {
            aVar.mo9910c();
        }
    }

    public NoticeMsgDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    /* renamed from: b */
    public /* synthetic */ void m9912b(View view) {
        dismiss();
        LogUtil.m9410a("BL_DIALOG_NOTICE_MSG_APP_CENTER");
        AbstractC4135a aVar = this.f12783f;
        if (aVar != null) {
            aVar.mo9911a();
        }
    }
}

