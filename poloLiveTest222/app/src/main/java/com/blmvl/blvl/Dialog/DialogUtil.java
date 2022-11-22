package com.blmvl.blvl.Dialog;

//package p067e.p103c.p104a.p113k;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blmvl.blvl.R;
//import p067e.p103c.p104a.p113k.DialogUtil;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.k.p */
/* loaded from: classes.dex */
public class DialogUtil {

    /* compiled from: DialogUtil.java */
    /* renamed from: e.c.a.k.p$a */
    /* loaded from: classes.dex */
    public static class C4241a {

        /* renamed from: a */
        public final Context f13038a;

        /* renamed from: b */
        public String f13039b;

        /* renamed from: c */
        public String f13040c;

        /* renamed from: d */
        public String f13041d;

        /* renamed from: e */
        public String f13042e;

        /* renamed from: f */
        public boolean f13043f;

        /* renamed from: g */
        public boolean f13044g;

        /* renamed from: h */
        public boolean f13045h;

        /* renamed from: i */
        public String f13046i;

        /* renamed from: j */
        public int f13047j;

        /* renamed from: k */
        public int f13048k;

        /* renamed from: l */
        public boolean f13049l = true;

        /* renamed from: m */
        public AbstractC4244d f13050m;

        public C4241a(Context context) {
            this.f13038a = context;
        }

        /* renamed from: a */
        public C4241a m9446a(String str) {
            this.f13042e = str;
            return this;
        }

        /* renamed from: b */
        public C4241a m9444b(String str) {
            this.f13041d = str;
            return this;
        }

        /* renamed from: c */
        public C4241a m9442c(String str) {
            this.f13040c = str;
            return this;
        }

        /* renamed from: d */
        public C4241a m9440d(String str) {
            this.f13039b = str;
            return this;
        }

        /* renamed from: a */
        public C4241a m9445a(boolean z) {
            this.f13044g = z;
            return this;
        }

        /* renamed from: b */
        public C4241a m9443b(boolean z) {
            this.f13043f = z;
            return this;
        }

        /* renamed from: c */
        public C4241a m9441c(boolean z) {
            this.f13049l = z;
            return this;
        }

        /* renamed from: a */
        public C4241a m9447a(AbstractC4244d dVar) {
            this.f13050m = dVar;
            return this;
        }

        /* renamed from: a */
        public Dialog m9449a() {
            try {
                final Dialog dialog = new Dialog(this.f13038a, this.f13044g ? R.style.dialog : R.style.dialog2);
                dialog.setContentView(this.f13045h ? R.layout.dialog_input : R.layout.dialog_simple);
                dialog.setCancelable(this.f13043f);
                dialog.setCanceledOnTouchOutside(this.f13043f);
                TextView textView = dialog.findViewById(R.id.title);
                if (!TextUtils.isEmpty(this.f13039b)) {
                    textView.setText(this.f13039b);
                }
                final EditText editText = dialog.findViewById(R.id.content);
                if (!TextUtils.isEmpty(this.f13046i)) {
                    editText.setHint(this.f13046i);
                }
                if (!TextUtils.isEmpty(this.f13040c)) {
                    editText.setText(this.f13040c);
                }
                if (this.f13047j == 1) {
                    editText.setInputType(2);
                } else if (this.f13047j == 2) {
                    editText.setInputType(18);
                } else if (this.f13047j == 3) {
                    editText.setInputType(128);
                }
                if (this.f13048k > 0) {
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.f13048k)});
                }
                TextView textView2 = dialog.findViewById(R.id.btn_confirm);
                if (!TextUtils.isEmpty(this.f13041d)) {
                    textView2.setText(this.f13041d);
                }
                TextView textView3 = dialog.findViewById(R.id.btn_cancel);
                if (!TextUtils.isEmpty(this.f13042e)) {
                    textView3.setText(this.f13042e);
                }
                ImageView imageView = dialog.findViewById(R.id.img_close);
                if (this.f13049l) {
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
                View.OnClickListener dVar = new View.OnClickListener() { // from class: e.c.a.k.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DialogUtil.C4241a.this.m9448a(dialog, editText, view);
                    }
                };
                textView2.setOnClickListener(dVar);
                textView3.setOnClickListener(dVar);
                imageView.setOnClickListener(dVar);
                return dialog;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        public /* synthetic */ void m9448a(Dialog dialog, EditText editText, View view) {
            if (view.getId() == R.id.btn_confirm) {
                AbstractC4244d dVar = this.f13050m;
                if (dVar == null) {
                    dialog.dismiss();
                } else if (this.f13045h) {
                    dVar.mo9437a(dialog, editText.getText().toString());
                } else {
                    dialog.dismiss();
                    this.f13050m.mo9437a(dialog, "");
                }
            } else if (view.getId() == R.id.btn_cancel) {
                dialog.dismiss();
                AbstractC4244d dVar2 = this.f13050m;
                if (dVar2 instanceof AbstractC4242b) {
                    ((AbstractC4242b) dVar2).mo9439b();
                }
            } else if (view.getId() == R.id.img_close) {
                dialog.dismiss();
                AbstractC4244d dVar3 = this.f13050m;
                if (dVar3 instanceof AbstractC4243c) {
                    ((AbstractC4243c) dVar3).mo9438a();
                }
            }
        }
    }

    /* compiled from: DialogUtil.java */
    /* renamed from: e.c.a.k.p$b */
    /* loaded from: classes.dex */
    public interface AbstractC4242b extends AbstractC4244d {
        /* renamed from: b */
        void mo9439b();
    }

    /* compiled from: DialogUtil.java */
    /* renamed from: e.c.a.k.p$c */
    /* loaded from: classes.dex */
    public interface AbstractC4243c extends AbstractC4242b {
        /* renamed from: a */
        void mo9438a();
    }

    /* compiled from: DialogUtil.java */
    /* renamed from: e.c.a.k.p$d */
    /* loaded from: classes.dex */
    public interface AbstractC4244d {
        /* renamed from: a */
        void mo9437a(Dialog dialog, String str);
    }

    /* renamed from: a */
    public static Dialog m9458a(Context context) {
        return m9456a(context, "");
    }

    /* renamed from: a */
    public static Dialog m9456a(Context context, String str) {
        TextView textView;
        Dialog dialog = new Dialog(context, R.style.dialog_style);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(str) && (textView = dialog.findViewById(R.id.text)) != null) {
            textView.setText(str);
        }
        return dialog;
    }

    /* renamed from: a */
    public static void m9457a(Context context, Dialog dialog) {
        if (dialog != null && !dialog.isShowing() && !((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    /* renamed from: a */
    public static void m9459a(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* renamed from: a */
    public static void m9455a(Context context, String str, AbstractC4244d dVar) {
        m9450a(context, str, true, dVar);
    }

    /* renamed from: a */
    public static void m9450a(Context context, String str, boolean z, AbstractC4244d dVar) {
        m9451a(context, null, str, z, dVar);
    }

    /* renamed from: a */
    public static void m9454a(Context context, String str, String str2, String str3, AbstractC4244d dVar) {
        m9452a(context, str, str2, str3, false, false, dVar);
    }

    /* renamed from: a */
    public static void m9452a(Context context, String str, String str2, String str3, boolean z, boolean z2, AbstractC4244d dVar) {
        m9453a(context, "", str, str2, str3, z, z2, dVar);
    }

    /* renamed from: a */
    public static void m9451a(Context context, String str, String str2, boolean z, AbstractC4244d dVar) {
        try {
            C4241a aVar = new C4241a(context);
            aVar.m9440d(str);
            aVar.m9442c(str2);
            aVar.m9443b(z);
            aVar.m9447a(dVar);
            aVar.m9445a(true);
            aVar.m9449a().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m9453a(Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, AbstractC4244d dVar) {
        try {
            C4241a aVar = new C4241a(context);
            aVar.m9440d(str);
            aVar.m9442c(str2);
            aVar.m9446a(str3);
            aVar.m9444b(str4);
            aVar.m9441c(z);
            aVar.m9443b(z2);
            aVar.m9447a(dVar);
            aVar.m9445a(true);
            aVar.m9449a().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

