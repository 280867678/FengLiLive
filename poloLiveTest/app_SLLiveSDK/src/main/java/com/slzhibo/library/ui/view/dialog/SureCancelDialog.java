package com.slzhibo.library.ui.view.dialog;



import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;


/* renamed from: com.slzhibo.library.ui.view.dialog.confirm.SureCancelDialog */
/* loaded from: classes2.dex */
public class SureCancelDialog extends BaseDialogFragment {

    /* renamed from: b */
    public View.OnClickListener f7683b;

    /* renamed from: c */
    public View.OnClickListener f7684c;

    /* renamed from: a */
    public static SureCancelDialog m14655a(String str, View.OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        SureCancelDialog sureCancelDialog = new SureCancelDialog();
        sureCancelDialog.setArguments(bundle);
        bundle.putString("tips", str);
        sureCancelDialog.setOnSureListener(onClickListener);
        return sureCancelDialog;
    }

    /* renamed from: b */
    public /* synthetic */ void m14648b(View view) {
        if (this.f7683b != null) {
            dismiss();
            this.f7683b.onClick(view);
        }
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_sure);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_cancel);
        String argumentsString = getArgumentsString("title");
        String argumentsString2 = getArgumentsString("tips");
        String argumentsString3 = getArgumentsString("btnCancel");
        String argumentsString4 = getArgumentsString("btnSure");
        if (getArgumentsBoolean("isCancelBtnRedColor", false)) {
            textView4.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorRed));
            textView3.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_black));
        } else {
            textView4.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_black));
            textView3.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorRed));
        }
        if (getArgumentsInt("content_color", -1) != -1) {
            textView2.setTextColor(ContextCompat.getColor(this.mContext, getArgumentsInt("content_color")));
        }
        if (!TextUtils.isEmpty(argumentsString)) {
            textView.setText(argumentsString);
        }
        textView2.setText(argumentsString2);
        if (!TextUtils.isEmpty(argumentsString4)) {
            textView3.setText(argumentsString4);
        } else {
            textView3.setText(getString(R.string.fq_btn_sure));
        }
        if (!TextUtils.isEmpty(argumentsString3)) {
            textView4.setText(argumentsString3);
        }
        textView4.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.d4.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SureCancelDialog.this.m14656a(view2);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.d4.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SureCancelDialog.this.m14648b(view2);
            }
        });
    }

    public void setOnCancelListener(View.OnClickListener onClickListener) {
        this.f7684c = onClickListener;
    }

    public void setOnSureListener(View.OnClickListener onClickListener) {
        this.f7683b = onClickListener;
    }

    /* renamed from: a */
    public static SureCancelDialog m14653a(String str, String str2, String str3, View.OnClickListener onClickListener) {
        return m14652a(str, str2, null, str3, R.color.fq_text_black, onClickListener);
    }

    /* renamed from: a */
    public static SureCancelDialog m14654a(String str, String str2, @ColorRes int i, View.OnClickListener onClickListener) {
        return m14652a(str, str2, null, null, i, onClickListener);
    }

    /* renamed from: a */
    public static SureCancelDialog m14649a(String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        return m14652a(str, str2, str3, str4, R.color.fq_text_gray, onClickListener);
    }

    /* renamed from: a */
    public static SureCancelDialog m14652a(String str, String str2, String str3, String str4, @ColorRes int i, View.OnClickListener onClickListener) {
        return m14651a(str, str2, str3, str4, i, null, onClickListener);
    }

    /* renamed from: a */
    public static SureCancelDialog m14651a(String str, String str2, String str3, String str4, @ColorRes int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return m14650a(str, str2, str3, str4, i, false, onClickListener, onClickListener2);
    }

    /* renamed from: a */
    public static SureCancelDialog m14650a(String str, String str2, String str3, String str4, @ColorRes int i, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        Bundle bundle = new Bundle();
        SureCancelDialog sureCancelDialog = new SureCancelDialog();
        sureCancelDialog.setArguments(bundle);
        bundle.putString("title", str);
        bundle.putString("tips", str2);
        bundle.putString("btnCancel", str3);
        bundle.putString("btnSure", str4);
        bundle.putInt("content_color", i);
        bundle.putBoolean("isCancelBtnRedColor", z);
        sureCancelDialog.setOnCancelListener(onClickListener);
        sureCancelDialog.setOnSureListener(onClickListener2);
        return sureCancelDialog;
    }

    /* renamed from: a */
    public /* synthetic */ void m14656a(View view) {
        dismiss();
        View.OnClickListener onClickListener = this.f7684c;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}

