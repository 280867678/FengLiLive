package com.slzhibo.library.ui.view.dialog;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.slzhibo.library.R;

//import com.example.boluouitest2zhibo.R;


/* loaded from: classes11.dex */
public class LoadingDialog extends Dialog {
    private boolean cancelFlag;
    private Context mContext;
    private String tips;

    public LoadingDialog(@NonNull Context context) {
        this(context, (String) null);
    }

    public LoadingDialog(@NonNull Context context, boolean z) {
        this(context, null, z);
    }

    public LoadingDialog(@NonNull Context context, String str) {
        this(context, str, true);
    }

    public LoadingDialog(@NonNull Context context, String str, boolean z) {
        super(context, R.style.fq_GeneralDialogStyle);
        this.cancelFlag = true;
        this.tips = str;
        this.mContext = context;
        this.cancelFlag = z;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        Activity ownerActivity;
        super.onCreate(bundle);
        View inflate = View.inflate(this.mContext, R.layout.fq_dialog_loading, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_tips);
        if (!TextUtils.isEmpty(this.tips)) {
            textView.setText(this.tips);
        }
        Window window = getWindow();
        if (!(window == null || (ownerActivity = getOwnerActivity()) == null)) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ownerActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            double d = displayMetrics.widthPixels;
            Double.isNaN(d);
            window.setLayout((int) (d * 0.7d), -2);
            window.setGravity(17);
            window.setDimAmount(0.7f);
        }
        setCancelable(this.cancelFlag);
        setCanceledOnTouchOutside(this.cancelFlag);
        setContentView(inflate);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

