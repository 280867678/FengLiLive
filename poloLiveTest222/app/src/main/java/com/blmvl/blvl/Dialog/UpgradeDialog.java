package com.blmvl.blvl.Dialog;

//package p067e.p103c.p104a.p108f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
//import com.alibaba.fastjson.asm.Label;
import com.blmvl.blvl.Config;
import com.blmvl.blvl.R;
import com.blmvl.blvl.bean.VersionBean;
import com.blmvl.blvl.httpUtil.VersionUtil;
import com.blmvl.blvl.util.CustomWordUtil;
import com.blmvl.blvl.util.LogUtil;
import com.blmvl.blvl.util.SpecialTxtColorUtil;
import com.blmvl.blvl.util.ToastUtil;
import com.comod.baselib.view.CustomTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.base.Request;
//import com.tencent.qcloud.core.util.IOUtils;
import java.io.File;
//import p067e.p103c.p104a.Config;
//import p067e.p103c.p104a.p113k.CustomWordUtil;
//import p067e.p103c.p104a.p113k.LogUtil;
//import p067e.p103c.p104a.p113k.SpecialTxtColorUtil;
//import p067e.p130f.p131a.p132a.AbsDialog;
//import p067e.p130f.p131a.p135d.ToastUtil;
//import p067e.p130f.p131a.p135d.VersionUtil;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.f.n0 */
/* loaded from: classes.dex */
public class UpgradeDialog extends AbsDialog {

    /* renamed from: b */
    public CustomTextView f12818b;

    /* renamed from: c */
    public TextView f12819c;

    /* renamed from: d */
    public TextView f12820d;

    /* renamed from: e */
    public TextView f12821e;

    /* renamed from: f */
    public VersionBean f12822f;

    /* renamed from: g */
    public AbstractC4147b f12823g;

    /* renamed from: h */
    public ProgressBar f12824h;

    /* renamed from: i */
    public TextView f12825i;

    /* renamed from: j */
    public LinearLayout f12826j;

    /* renamed from: k */
    public LinearLayout f12827k;

    /* compiled from: UpgradeDialog.java */
    /* renamed from: e.c.a.f.n0$a */
    /* loaded from: classes.dex */
    public class C4146a extends FileCallback {
        public C4146a(String str, String str2) {
            super(str, str2);
        }

        @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
        public void downloadProgress(Progress progress) {
            super.downloadProgress(progress);
            try {
                int i = (int) ((progress.currentSize * 100) / progress.totalSize);
                UpgradeDialog.this.f12824h.setProgress(i);
                UpgradeDialog.this.f12825i.setText(String.format(UpgradeDialog.this.getContext().getString(R.string.str_apk_download_ing), i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
        public void onError(Response<File> response) {
            super.onError(response);
            UpgradeDialog.this.m9883h();
            ToastUtil.m9102c(UpgradeDialog.this.getContext(), UpgradeDialog.this.getContext().getString(R.string.str_apk_download_exception));
        }

        @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
        public void onFinish() {
            super.onFinish();
            UpgradeDialog.this.m9883h();
        }

        @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
        public void onStart(Request<File, ? extends Request> request) {
            super.onStart(request);
            UpgradeDialog.this.m9882i();
        }

        @Override // com.lzy.okgo.callback.Callback
        public void onSuccess(Response<File> response) {
            UpgradeDialog n0Var = UpgradeDialog.this;
            n0Var.m9894a(n0Var.getContext(), response.body());
            ToastUtil.m9102c(UpgradeDialog.this.getContext(), UpgradeDialog.this.getContext().getString(R.string.str_apk_download_finish));
        }
    }

    /* compiled from: UpgradeDialog.java */
    /* renamed from: e.c.a.f.n0$b */
    /* loaded from: classes.dex */
    public interface AbstractC4147b {
        /* renamed from: a */
        void mo9881a(String str);

        void onCancel();
    }

    public UpgradeDialog(@NonNull Context context, VersionBean versionBean) {
        this(context, R.style.UpgradeDialog);
        this.f12822f = versionBean;
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: a */
    public boolean mo9246a() {
        return false;
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: b */
    public boolean mo9244b() {
        return false;
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: d */
    public int mo9241d() {
        return R.layout.dialog_version_upgrade;
    }

    /* renamed from: h */
    public final void m9883h() {
        this.f12826j.setVisibility(View.INVISIBLE);
        this.f12827k.setVisibility(View.VISIBLE);
    }

    /* renamed from: i */
    public final void m9882i() {
        this.f12824h.setProgress(0);
        this.f12826j.setVisibility(View.VISIBLE);
        this.f12827k.setVisibility(View.INVISIBLE);
        this.f12825i.setText(getContext().getString(R.string.str_wait_download_hint));
    }

    @Override // p067e.p130f.p131a.p132a.AbsDialog
    /* renamed from: b */
    public void mo9243b(Window window) {
        m9886c(window);
        LogUtil.m9410a("BL_DIALOG_VERSION_UPGRADE");
    }

    /* renamed from: c */
    public final void m9886c(Window window) {
        if (window != null) {
            try {
                this.f12818b = window.findViewById(R.id.tv_version_name);
                this.f12819c = window.findViewById(R.id.tv_desc);
                this.f12819c.setAutoLinkMask(15);
                this.f12819c.setMovementMethod(ScrollingMovementMethod.getInstance());
                this.f12820d = window.findViewById(R.id.btn_confirm);
                this.f12821e = window.findViewById(R.id.btn_cancel);
                this.f12824h = window.findViewById(R.id.progress_bar);
                this.f12825i = window.findViewById(R.id.tv_progress_hint);
                this.f12826j = window.findViewById(R.id.progress_layout);
                this.f12827k = window.findViewById(R.id.fun_layout);
                m9883h();
                if (this.f12822f != null) {
                    if (!TextUtils.isEmpty(this.f12822f.getVersion())) {
                        this.f12818b.setText(String.format("V%s", this.f12822f.getVersion()));
                    }
                    if (!TextUtils.isEmpty(this.f12822f.getTips())) {
                        this.f12819c.setText(SpecialTxtColorUtil.m9512a(this.f12822f.getTips().trim().replaceAll("#", "\n"), getContext().getResources().getColor(R.color.color_ffc100)));
                    }
                    if (this.f12822f.getMust() == 1) {
                        this.f12821e.setVisibility(View.GONE);
                    } else {
                        this.f12821e.setVisibility(View.VISIBLE);
                    }
                    this.f12820d.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.f.o
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UpgradeDialog.this.m9893a(view);
                        }
                    });
                    this.f12821e.setOnClickListener(new View.OnClickListener() { // from class: e.c.a.f.n
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UpgradeDialog.this.m9888b(view);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UpgradeDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    public void m9892a(AbstractC4147b bVar) {
        this.f12823g = bVar;
    }

    /* renamed from: a */
    public /* synthetic */ void m9893a(View view) {
        try {
            if (this.f12823g != null) {
                this.f12823g.mo9881a(this.f12822f.getApk());
            }
            m9889a(CustomWordUtil.m9462a(this.f12822f.getApk()));
            LogUtil.m9410a("BL_DIALOG_VERSION_UPGRADE_CONFIRM");
        } catch (Exception unused) {
            ToastUtil.m9102c(getContext(), getContext().getString(R.string.str_apk_download_exception));
        }
    }

    /* renamed from: b */
    public /* synthetic */ void m9888b(View view) {
        AbstractC4147b bVar = this.f12823g;
        if (bVar != null) {
            bVar.onCancel();
        }
        LogUtil.m9410a("BL_DIALOG_VERSION_UPGRADE_CANCEL");
    }

    /* renamed from: a */
    public final void m9889a(String str) {
        File file = new File(Config.f12184e);
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, "boluoapp.apk");
            if (!file2.exists() || file2.delete()) {
                ((GetRequest) OkGo.get(str).tag("download_apk")).execute(new C4146a(file.getAbsolutePath(), "boluoapp.apk"));
            } else {
                ToastUtil.m9102c(getContext(), getContext().getString(R.string.str_apk_download_exception));
            }
        } else {
            ToastUtil.m9102c(getContext(), getContext().getString(R.string.str_apk_download_exception));
        }
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public final void m9894a(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            if (Build.VERSION.SDK_INT >= 24) {
                Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                intent.addFlags(3);
                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
            VersionUtil.m9099a(context, "com.iboluo.boluovl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

